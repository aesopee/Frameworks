package com.slickcode.baseframework.utils;

public class StringUtilities {

	private StringUtilities() {

	}

	public static boolean isEmpty(String value) {
		return (null == value || (value.trim().length() == 0));
	}

	public static boolean containsMandatoryAlphabates(String value) {
		return value.matches("[a-zA-Z][a-zA-Z/s]+");
	}

	public static boolean containsOptionalAlphaNumber(String value) {
		return value.matches("[A-Za-z0-9]+");
	}

	public static boolean containsMandatoryAlphaNumber(String value) {
		return value.matches("^(?=.*[0-9])(?=.*[a-zA-Z])([a-zA-Z0-9]+)$");
	}

	public static boolean containsMandatoryAlphaNumericSpecialChar(String value) {
		return value.matches("/^[a-zA-Z0-9?=.*!@#$%^&*_\\-\\s]+$/");
	}

	public static boolean isNumeric(String str) {
		return str.matches("-?\\d+(\\.\\d+)?"); // match a number with optional '-' and decimal.
	}

}
