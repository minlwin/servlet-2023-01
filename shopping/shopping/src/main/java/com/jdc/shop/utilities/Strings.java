package com.jdc.shop.utilities;

public class Strings {

	public static boolean isBlanck(String str) {
		return null == str || str.isBlank();
	}

	public static boolean isNotBlanck(String str) {
		return !isBlanck(str);
	}
}
