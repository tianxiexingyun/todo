package com.zhyea.todo.utils;

/**
 * @ClassName: StringUtils 
 * @Description: 字符串操作工具类
 * @Site: www.zhyea.com 
 * @author robin
 * @date 2015年11月29日 上午10:40:55
 */
public class StringUtils {

	/**
	 * 判断是否为空
	 * @param cs
	 * 			要判断的字符串
	 * @return
	 */
	public static boolean isBlank(CharSequence cs) {
		int strLen;
		if (cs == null || (strLen = cs.length()) == 0) {
			return true;
		}
		for (int i = 0; i < strLen; i++) {
			if (Character.isWhitespace(cs.charAt(i)) == false) {
				return false;
			}
		}
		return true;
	}

	/**
	 * 将字符串转为整型
	 * @param o
	 * 		原始字符串
	 * @return
	 */
	public static int toInt(String o) {
		try {
			return Integer.parseInt(o);
		} catch (Exception e) {
			return 0;
		}
	}

	/**
	 * 将原始字符串转为长整型
	 * @param o
	 * 		原始字符串
	 * @return
	 */
	public static long toLong(String o) {
		try {
			return Long.parseLong(o);
		} catch (Exception e) {
			return 0L;
		}
	}

	/**
	 * 将原始字符串转换为浮点型
	 * @param o
	 * 		原始字符串
	 * @return
	 */
	public static double toDouble(String o) {
		try {
			return Double.parseDouble(o);
		} catch (Exception e) {
			return 0.0;
		}
	}
}
