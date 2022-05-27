package com.github.akagawatsurunaki.roujinfamily.util;

import java.time.format.DateTimeFormatter;

public class GlobalFormatter {
	public static final DateTimeFormatter timeFormatter = DateTimeFormatter.ofPattern("HH:mm");
	public static final DateTimeFormatter dateFormatter = DateTimeFormatter.ofPattern("yyyy-M-d");
}
