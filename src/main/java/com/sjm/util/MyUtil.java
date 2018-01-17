package com.sjm.util;

import java.util.List;

public class MyUtil {

	public static boolean StringNull(String str) {
		if(str == null || str.trim().equals("")) {
			return true;
		}
		return false;
	}
	
	public static boolean ListNull(List<?> list) {
		if(list == null || list.size() == 0) {
			return true;
		}
		return false;
	}
}
