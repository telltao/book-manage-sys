package com.java.util;

import java.util.regex.Pattern;

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

	/**
	 * 校验字符是否为正整数
	 *
	 * @param string
	 * @return
	 */
	public static boolean isNumbers(String string) {
		Pattern pattern = Pattern.compile("[0-9]*");
		return pattern.matcher(string).matches();
	}

}
