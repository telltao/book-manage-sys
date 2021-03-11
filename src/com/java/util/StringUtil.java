package com.java.util;

public class StringUtil {

	/**
	 * 判断是否为空
	 *
	 * @param str
	 * @return
	 */
	public static boolean isEmpty(String str) {
		if (str == null || "".equals(str.trim())) {
			return true;
		} else {
			return false;
		}
	}

	/**
	 * 判断是否不是空
	 */
	public static boolean isNotEmpty(String str) {
		//注意这里的逻辑判断条件
		if (str != null && !"".equals(str.trim())) {
			return true;
		} else {
			return false;
		}
	}
}
