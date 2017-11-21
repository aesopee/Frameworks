package com.slickcode.basevalidatorframework;

import com.slickcode.baseframework.utils.StringUtilities;

public class NumericUtilities {
	private NumericUtilities() {

	}

	public static boolean isInteger(String value) {
		if (StringUtilities.isEmpty(value)) {
			return false;
		}
		try {
			Integer.parseInt(value);
			return true;
		} catch (Exception exception) {
			return false;
		}
	}

	public static boolean isAmount(String value) {
		if (StringUtilities.isEmpty(value)) {
			return false;
		}
		return value.matches("[0-9]+([,.][0-9]{1,2})?");
	}
}
