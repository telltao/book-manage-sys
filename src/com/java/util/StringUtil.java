package com.java.util;

import java.sql.Date;
import java.text.SimpleDateFormat;
import java.util.regex.Pattern;

public class StringUtil {
	//日期格式化工具 使用静态可以永久存在
	private static SimpleDateFormat dataFormat = new SimpleDateFormat("yyyy-MM-dd");

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

	/**
	 * 将日期类型进行转换 2021-05-12 21:36:57 转换为 2021-05-12
	 *
	 * @return
	 */
	public static String dataFormat(Date date) {
		String format = dataFormat.format(date);
		return format;
	}

}
