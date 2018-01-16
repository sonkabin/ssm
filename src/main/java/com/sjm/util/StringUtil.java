package com.sjm.util;

public class StringUtil {

	public static boolean NotNull(String str) {
		if(str == null || str.trim().equals("")) {
			return false;
		}
		return true;
	}
}
