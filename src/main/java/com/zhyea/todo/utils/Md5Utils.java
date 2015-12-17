package com.zhyea.todo.utils;

import java.io.UnsupportedEncodingException;
import java.security.MessageDigest;
import java.security.NoSuchAlgorithmException;

/**
 * @ClassName: MD5Utils 
 * @Description: MD5计算工具
 * @author robin
 * @date 2015年9月28日 上午10:12:46
 */
public class Md5Utils {

	/**
	 * 对字符串进行MD5计算
	 * @param str
	 * 			要处理的字符串
	 * @return
	 */
	public static String encrypt(String str) {
		MessageDigest digest = null;
		try {
			digest = MessageDigest.getInstance("MD5");
			digest.reset();
			digest.update(str.getBytes("UTF-8"));
		} catch (NoSuchAlgorithmException e) {
			System.exit(-1);
		} catch (UnsupportedEncodingException e) {
			e.printStackTrace();
		}

		byte[] byteArray = digest.digest();
		StringBuffer buffer = new StringBuffer();
		for (int i = 0; i < byteArray.length; i++) {
			if (Integer.toHexString(0xFF & byteArray[i]).length() == 1) {
				buffer.append("0").append(Integer.toHexString(0xFF & byteArray[i]));
			} else {
				buffer.append(Integer.toHexString(0xFF & byteArray[i]));
			}
		}

		return buffer.toString();
	}

	private Md5Utils() {
	}
}
