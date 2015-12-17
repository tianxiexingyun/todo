package com.zhyea.todo.utils;

import java.text.SimpleDateFormat;
import java.util.Date;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

/**
 * @ClassName: DateUtils 
 * @Description: 日期操作工具类
 * @Site: www.zhyea.com 
 * @author robin
 * @date 2015年11月29日 上午10:40:35
 */
public class DateUtils {

	public static final String DEFAULT_FULL_DATE_FORMAT = "yyyy-MM-dd HH:mm:ss";

	private static Logger logger = LoggerFactory.getLogger(DateUtils.class);

	/**
	 * 格式化日期
	 * @param date
	 * 			日期对象
	 * @return
	 * 			格式化后的日期字符串
	 */
	public static String format(Date date) {
		SimpleDateFormat format = new SimpleDateFormat(DEFAULT_FULL_DATE_FORMAT);
		return format.format(date);
	}

	/**
	 * 将格式为yyyy-MM-dd HH:mm:ss的日期字符串转为日起对象
	 * @param dateStr
	 * 			日期字符串
	 * @return
	 */
	public static Date parse(String dateStr) {
		try {
			SimpleDateFormat format = new SimpleDateFormat(DEFAULT_FULL_DATE_FORMAT);
			return format.parse(dateStr);
		} catch (Exception e) {
			logger.error("parse date use format '" + DEFAULT_FULL_DATE_FORMAT + "' error...", e);
			return null;
		}
	}
}
