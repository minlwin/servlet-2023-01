package com.jdc.shop.utilities;

public class Integers {

	public static int parse(String str) {
		return str == null || str.isBlank() ? 0 : Integer.parseInt(str);
	}
}
