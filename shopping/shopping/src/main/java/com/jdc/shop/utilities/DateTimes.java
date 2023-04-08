package com.jdc.shop.utilities;

import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;

public class DateTimes {

	public static String format(LocalDateTime dateTime) {
		return null == dateTime ? "" : dateTime.format(DateTimeFormatter.ofPattern("yyyy-MM-dd HH:mm"));
	}
}
