package com.slickcode.baseframework.components;

import java.awt.Component;
import java.awt.Container;
import java.awt.FocusTraversalPolicy;
import java.util.ArrayList;
import java.util.List;

import javax.swing.JPanel;

public class BaseFocusTraversalPolicy extends FocusTraversalPolicy {
	private List<Component> order;

	public BaseFocusTraversalPolicy(Container container, List<Component> order) {
		acceptanceCriteria(container, order);
	}

	/**
	 * @param container
	 * @param order
	 */
	private void acceptanceCriteria(Container container, List<Component> order) {
		List<JPanel> panelList = getChildPanelList(container);
		this.order = new ArrayList<Component>();
		for (Component component : order) {
			boolean result = checkComponentExistance(container, panelList,
					component);
			if (result && component.isEnabled()) {
				if (component instanceof BaseComboBox<?>) {
					BaseComboBox<?> baseComboBox = (BaseComboBox<?>) component;
					this.order.add(baseComboBox.getEditor()
							.getEditorComponent());
				} else {
					this.order.add(component);
				}
			}
		}
	}

	/**
	 * @param container
	 * @param panelList
	 * @param component
	 */
	private boolean checkComponentExistance(Container container,
			List<JPanel> panelList, Component component) {
		boolean result = false;
		if (container.getComponentZOrder(component) < 0) {
			for (JPanel panel : panelList) {
				if (panel.getComponentZOrder(component) < 0) {
					result = false;
				} else {
					result = true;
				}
			}
		} else {
			result = true;
		}
		return result;
	}

	/**
	 * @param container
	 * @return
	 */
	private List<JPanel> getChildPanelList(Container container) {
		List<JPanel> panelList = new ArrayList<JPanel>();
		for (Component component : container.getComponents()) {
			if (component instanceof JPanel) {
				panelList.add((JPanel) component);
				panelList.addAll(getChildPanelList((JPanel) component));
			}
		}
		return panelList;
	}

	public Component getComponentAfter(Container container, Component aComponent) {
		int idx = (order.indexOf(aComponent) + 1) % order.size();
		if (!order.get(idx).isEnabled()) {
			return getComponentAfter(container, order.get(idx));
		}
		return order.get(idx);
	}

	public Component getComponentBefore(Container container,
			Component aComponent) {
		int idx = order.indexOf(aComponent) - 1;
		if (idx < 0) {
			idx = order.size() - 1;
		}
		if (!order.get(idx).isEnabled()) {
			return getComponentBefore(container, order.get(idx));
		}
		return order.get(idx);
	}

	public Component getDefaultComponent(Container container) {
		return getFirstComponent(container);
	}

	public Component getLastComponent(Container container) {
		return getComponentBefore(container, order.get(0));
	}

	public Component getFirstComponent(Container container) {
		return getComponentAfter(container, order.get(order.size() - 1));
	}
}
