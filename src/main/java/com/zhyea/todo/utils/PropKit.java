package com.zhyea.todo.utils;

import java.io.IOException;
import java.io.InputStream;
import java.util.Hashtable;
import java.util.Map;
import java.util.Properties;

/**
 * @ClassName: PropKit 
 * @Description: 属性文件工具类
 * @Site: www.zhyea.com 
 * @author robin
 * @date 2015年11月28日 上午11:59:35
 */
public class PropKit {

	private static final Map<Object, Object> m = new Hashtable<Object, Object>();

	/**
	 * 加载properties资源
	 * @param propertyFileName
	 * 			properties文件路径
	 */
	public static void load(String propertyFileName) {
		Properties p = new Properties();
		InputStream in = null;
		try {
			in = PropKit.class.getResourceAsStream(propertyFileName);
			if (in != null) {
				p.load(in);
				m.putAll(p);
			}
		} catch (IOException e) {
			e.printStackTrace();
		} finally {
			if (in != null) {
				try {
					in.close();
				} catch (IOException e) {
					e.printStackTrace();
				}
			}
		}
	}

	public static String getProp(String key, String defaultValue) {
		Object oval = m.get(key);
		String sval = (oval instanceof String) ? (String) oval : null;
		return (sval == null) ? defaultValue : sval;
	}

	public static String getProp(String key) {
		return getProp(key, null);
	}

	public static Object setProp(String key, String value) {
		return m.put(key, value);
	}

	public static Integer getInt(String key, Integer defaultValue) {
		String value = getProp(key);
		return (value != null) ? Integer.parseInt(value) : defaultValue;
	}

	public static Integer getInt(String key) {
		return getInt(key, null);
	}

	public static Double getDouble(String key, Double defaultValue) {
		String value = getProp(key);
		return (value != null) ? Double.parseDouble(value) : defaultValue;
	}

	public static Double getDouble(String key) {
		return getDouble(key, null);
	}

	public static Boolean getBoolean(String key, Boolean defaultValue) {
		String value = getProp(key);
		return (value != null) ? Boolean.parseBoolean(value) : defaultValue;
	}

	public static Boolean getBoolean(String key) {
		return getBoolean(key, null);
	}
}
