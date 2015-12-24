package com.zhyea.todo.utils;

import java.text.SimpleDateFormat;
import java.util.Calendar;
import java.util.Date;
import java.util.GregorianCalendar;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

/**
 * @ClassName: DateUtils 
 * @Description: 日期操作工具类
 * @Site: www.zhyea.com 
 * @author robin
 * @date 2015年11月29日 上午10:40:35
 */
public class DateKit {
	/** 完整日期时间格式 */
	public static final String FORMAT_DATE_TIME = "yyyy-MM-dd HH:mm:ss";
	/** 完整日期时间零时格式 */
	public static final String FORMAT_DATE_TIME_START = "yyyy-MM-dd 00:00:00";
	/** 完整日期时间最后时间格式 */
	public static final String FORMAT_DATE_TIME_END = "yyyy-MM-dd 23:60:60";
	/** 只有日期的格式 */
	public static final String FORMAT_DATE_ONLY = "yyyy-MM-dd";

	private static Logger logger = LoggerFactory.getLogger(DateKit.class);

	/**
	 * 格式化日期
	 * @param date
	 * 			日期对象
	 * @return
	 * 			格式化后的日期字符串
	 */
	public static String format(Date date) {
		return format(date, FORMAT_DATE_TIME);
	}

	/**
	 * 格式化日期
	 * @param date
	 * 			日期对象
	 * @param pattern
	 * 			日期字符串格式
	 * @return
	 * 			格式化后的日期字符串
	 */
	public static String format(Date date, String pattern) {
		SimpleDateFormat format = new SimpleDateFormat(pattern);
		return format.format(date);
	}

	/**
	 * 将格式为yyyy-MM-dd HH:mm:ss的日期字符串转为日期对象
	 * @param dateStr
	 * 			日期字符串
	 * @return
	 * 			日期对象
	 */
	public static Date parse(String dateStr) {
		return parse(dateStr, FORMAT_DATE_TIME);
	}

	/**
	 * 将格式为pattern的日期字符串转为日期对象
	 * @param dateStr
	 * 			日期字符串
	 * @param pattern
	 * 			日期字符串格式
	 * @return
	 * 			日期对象
	 */
	public static Date parse(String dateStr, String pattern) {
		try {
			SimpleDateFormat format = new SimpleDateFormat(pattern);
			return format.parse(dateStr);
		} catch (Exception e) {
			logger.error("parse date use format '" + FORMAT_DATE_TIME + "' error...", e);
			return null;
		}
	}

	/**
	 * 日期比较
	 * 1: src > dest
	 * 0: src = dest
	 * -1: src < dest
	 * @param src
	 * 			源日期
	 * @param dest
	 * 			目标日期
	 * @return
	 */
	public static int compareTo(Date src, Date dest) {
		return src.compareTo(dest);
	}

	/**
	 * 调整日期
	 * @param src
	 * 			源日期
	 * @param dateNum
	 * 			数目
	 * @return
	 */
	public static Date addDays(Date src, int dateNum) {
		GregorianCalendar gCanlendar = new GregorianCalendar();
		gCanlendar.setTime(src);
		gCanlendar.add(Calendar.DAY_OF_MONTH, dateNum);
		return gCanlendar.getTime();
	}

	/**
	 * 调整月份
	 * @param src
	 * 			源日期
	 * @param monthNum
	 * 			月份数目
	 * @return
	 */
	public static Date addMonths(Date src, int monthNum) {
		GregorianCalendar gCanlendar = new GregorianCalendar();
		gCanlendar.setTime(src);
		gCanlendar.add(Calendar.MONTH, monthNum);
		return gCanlendar.getTime();
	}

	/**
	 * 获取当前星期的周一，时间对应当前时间
	 * @return
	 * 		当前星期的周一，时间对应当前时间
	 */
	public static Date getThisMonday() {
		GregorianCalendar gCanlendar = new GregorianCalendar();
		gCanlendar.set(Calendar.DAY_OF_WEEK, Calendar.MONDAY);
		return gCanlendar.getTime();
	}

	/**
	 * 获取本月的第一天
	 * @return
	 * 		本月的第一天
	 */
	public static Date getMonthFirstDay() {
		GregorianCalendar gCanlendar = new GregorianCalendar();
		gCanlendar.set(Calendar.DAY_OF_MONTH, gCanlendar.getActualMinimum(Calendar.DAY_OF_MONTH));
		return gCanlendar.getTime();
	}

	/**
	 * 获取当年第一天
	 * @return
	 * 		今年第一天
	 */
	public static Date getYearFirstDay() {
		GregorianCalendar gCanlendar = new GregorianCalendar();
		gCanlendar.set(Calendar.YEAR, gCanlendar.get(Calendar.YEAR));
		gCanlendar.set(Calendar.MONTH, gCanlendar.getActualMinimum(Calendar.MONTH));
		gCanlendar.set(Calendar.DAY_OF_MONTH, gCanlendar.getActualMinimum(Calendar.DAY_OF_MONTH));
		return gCanlendar.getTime();
	}

	public static void main(String[] args) {
		System.out.println(format(getYearFirstDay(), FORMAT_DATE_TIME));
	}
}
