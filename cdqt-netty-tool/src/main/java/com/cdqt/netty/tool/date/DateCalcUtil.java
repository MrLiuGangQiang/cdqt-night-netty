package com.cdqt.netty.tool.date;

import java.security.InvalidParameterException;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Calendar;
import java.util.Date;

/**
 * 日期工具类
 * 
 * @author LiuGangQiang Create in 2020/03/01
 */
public class DateCalcUtil {
	/**
	 * 毫秒到秒的换算值
	 *
	 * @author LiuGangQiang Create in 2020/03/01
	 */
	private static final long MILLIS_IN_A_SECOND = 1000;

	/**
	 * 秒到分钟的换算值
	 *
	 * @author LiuGangQiang Create in 2020/03/01
	 */
	private static final long SECONDS_IN_A_MINUTE = 60;

	/**
	 * 月到年的换算值
	 *
	 * @author LiuGangQiang Create in 2020/03/01
	 */
	private static final int MONTHS_IN_A_YEAR = 12;

	/**
	 * 私有化构造器
	 *
	 * @author LiuGangQiang Create in 2020/03/01
	 */
	private DateCalcUtil() {
	}

	/**
	 * Copyright © 2019 ChengDu RongXing Technology Co.Ltd All Rights Reserved.
	 * 
	 * @author LiuGangQiang Create in 2020/03/01
	 */
	private static class SingleDateCalcUtilHolder {
		/**
		 * {@link DateCalcUtil} 静态常量实例
		 *
		 * @author LiuGangQiang Create in 2020/03/01
		 */
		private static final DateCalcUtil INSTANCE = new DateCalcUtil();
	}

	/**
	 * 获取{@link SingleDateCalcUtilHolder#INSTANCE} 静态常量实例
	 *
	 * @author LiuGangQiang Create in 2020/03/01
	 * @return {@link SingleDateCalcUtilHolder#INSTANCE} 静态常量实例
	 */
	public static DateCalcUtil getInstance() {
		return SingleDateCalcUtilHolder.INSTANCE;
	}

	/**
	 * 判断某个年份是否是闰年
	 *
	 * @author LiuGangQiang Create in 2020/03/01
	 * @param year 年份数
	 * @return 是否是闰年
	 */
	public boolean isLeapYear(int year) {
		if ((year % 4 == 0 && year % 100 != 0) || year % 400 == 0) {
			return true;
		} else {
			return false;
		}
	}

	/**
	 * 获取一年中最后的日期（精确到秒） 例如2019 —— 2019-12-31 23:59:59
	 *
	 * @author LiuGangQiang Create in 2020/03/01
	 * @param date 目标日期
	 * @return 一年中最后的日期（精确到秒）
	 */
	public Date getCurrYearLast(Date date) {
		Calendar currCal = Calendar.getInstance();
		currCal.setTime(date);
		int currentYear = currCal.get(Calendar.YEAR);
		return getYearLastTime(currentYear);
	}

	/**
	 * 获取某个年份最后的日期（精确到秒） 例如2019 —— 2019-12-31 23:59:59
	 *
	 * @author LiuGangQiang Create in 2020/03/01
	 * @param year 年份数
	 * @return 一年中最后的日期（精确到秒）
	 */
	private Date getYearLastTime(int year) {
		Calendar calendar = Calendar.getInstance();
		calendar.clear();
		calendar.set(Calendar.YEAR, year);
		calendar.roll(Calendar.DAY_OF_YEAR, -1);
		calendar.set(Calendar.HOUR_OF_DAY, 23);
		calendar.set(Calendar.MINUTE, 59);
		calendar.set(Calendar.SECOND, 59);
		Date currYearLast = calendar.getTime();
		return currYearLast;
	}

	/**
	 * 获取某年某月有多少天 例如2019-02 —— 28
	 *
	 * @author LiuGangQiang Create in 2020/03/01
	 * @param year  年份数
	 * @param month 月份数
	 * @return 天数
	 */
	public int getMonthDays(int year, int month) {
		Boolean isLeapYear = isLeapYear(year);
		int days = 31;
		switch (month) {
		case 1:
			if (isLeapYear) {
				days = 29;
			} else {
				days = 28;
			}
			break;
		case 3:
			days = 30;
			break;
		case 5:
			days = 30;
			break;
		case 8:
			days = 30;
			break;
		case 10:
			days = 30;
			break;
		default:
			break;
		}
		return days;
	}

	/**
	 * 获取某个日期之后的日期
	 *
	 * @author LiuGangQiang Create in 2020/03/01
	 * @param target   目标日期
	 * @param amount   差量
	 * @param timeUnit 单位 请使用{@link Calendar}内的常量
	 * @return 目标日期差量之后的时间
	 */
	public Date dateAfter(Date target, int amount, int timeUnit) {
		Calendar calendar = Calendar.getInstance();
		calendar.setTime(target);
		calendar.add(timeUnit, amount);
		return calendar.getTime();
	}

	/**
	 * 获取某个日期之前的日期
	 *
	 * @author LiuGangQiang Create in 2020/03/01
	 * @param target   目标日期
	 * @param amount   差量
	 * @param timeUnit 单位 请使用{@link Calendar}内的常量
	 * @return 目标日期差量之前的时间
	 */
	public Date dateBefore(Date target, int amount, int timeUnit) {
		Calendar calendar = Calendar.getInstance();
		calendar.setTime(target);
		calendar.add(timeUnit, -amount);
		return calendar.getTime();
	}

	/**
	 * 计算两个日期之间的周年差
	 *
	 * @author LiuGangQiang Create in 2020/03/01
	 * @param startDate 开始日期
	 * @param endDate   结束日期
	 * @return 两个日期之间的周年差
	 */
	public int getYearDiff(Date startDate, Date endDate) {
		if (startDate == null || endDate == null) {
			throw new InvalidParameterException("startDate and endDate cannot be null!");
		}
		if (startDate.after(endDate)) {
			throw new InvalidParameterException("startDate cannot be after endDate!");
		}

		Calendar calendar = Calendar.getInstance();
		calendar.setTime(startDate);
		int year1 = calendar.get(Calendar.YEAR);
		int month1 = calendar.get(Calendar.MONTH);
		int day1 = calendar.get(Calendar.DATE);

		calendar.setTime(endDate);
		int year2 = calendar.get(Calendar.YEAR);
		int month2 = calendar.get(Calendar.MONTH);
		int day2 = calendar.get(Calendar.DATE);

		int result = year2 - year1;
		if (month2 < month1) {
			result--;
		} else if (month2 == month1 && day2 < day1) {
			result--;
		}
		return result;
	}

	/**
	 * 计算两个日期之间的月份差
	 *
	 * @author LiuGangQiang Create in 2020/03/01
	 * @param startDate 开始日期
	 * @param endDate   结束日期
	 * @return 两个日期之间的月份差
	 */
	public int getMonthDiff(Date startDate, Date endDate) {
		if (startDate == null || endDate == null) {
			throw new InvalidParameterException("startDate and endDate cannot be null!");
		}
		if (startDate.after(endDate)) {
			throw new InvalidParameterException("startDate cannot be after endDate!");
		}

		Calendar calendar = Calendar.getInstance();
		calendar.setTime(startDate);
		int year1 = calendar.get(Calendar.YEAR);
		int month1 = calendar.get(Calendar.MONTH);
		int day1 = calendar.get(Calendar.DAY_OF_MONTH);

		calendar.setTime(endDate);
		int year2 = calendar.get(Calendar.YEAR);
		int month2 = calendar.get(Calendar.MONTH);
		int day2 = calendar.get(Calendar.DAY_OF_MONTH);

		int tempY = year2 - year1;
		int tempM = month2 - month1;
		if (month2 < month1) {
			tempY--;
			tempM += 12;
		}
		if (day2 < day1 && getMonthDays(year2, month2) != day2) {
			tempM--;
		}
		return tempY * MONTHS_IN_A_YEAR + tempM;
	}

	/**
	 * 计算两个日期之间的天数差
	 *
	 * @author LiuGangQiang Create in 2020/03/01
	 * @param startDate 开始日期
	 * @param endDate   结束日期
	 * @return 两个日期之间的天数差
	 */
	public int getDayDiff(Date startDate, Date endDate) {
		if (startDate == null || endDate == null) {
			throw new InvalidParameterException("startDate and endDate cannot be null!");
		}
		SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM-dd");
		Date smdate;
		try {
			smdate = sdf.parse(sdf.format(startDate));
			Date bdate = sdf.parse(sdf.format(endDate));
			Calendar cal = Calendar.getInstance();
			cal.setTime(smdate);
			long time1 = cal.getTimeInMillis();
			cal.setTime(bdate);
			long time2 = cal.getTimeInMillis();
			long between_days = (time2 - time1) / (1000 * 3600 * 24);
			return Integer.parseInt(String.valueOf(between_days));
		} catch (ParseException e) {
			e.printStackTrace();
		}
		return 0;
	}

	/**
	 * 计算两个日期之前的分钟差（忽略年月日）
	 *
	 * @author LiuGangQiang Create in 2020/03/01
	 * @param startTime 开始日期
	 * @param endTime   结束日期
	 * @return 两个日期之前的分钟差（忽略年月日）
	 */
	public int getMinuteDiffByTime(Date startTime, Date endTime) {
		long startMil = 0;
		long endMil = 0;
		Calendar calendar = Calendar.getInstance();
		calendar.setTime(startTime);
		calendar.set(1900, 1, 1);
		startMil = calendar.getTimeInMillis();
		calendar.setTime(endTime);
		calendar.set(1900, 1, 1);
		endMil = calendar.getTimeInMillis();
		return (int) ((endMil - startMil) / MILLIS_IN_A_SECOND / SECONDS_IN_A_MINUTE);
	}

	/**
	 * 判断两个日期是否是同一天
	 *
	 * @author LiuGangQiang Create in 2020/03/01
	 * @param dateA 日期A
	 * @param dateB 日期B
	 * @return 是否是同一天
	 */
	public boolean areSameDay(Date dateA, Date dateB) {
		Calendar calDateA = Calendar.getInstance();
		calDateA.setTime(dateA);
		Calendar calDateB = Calendar.getInstance();
		calDateB.setTime(dateB);
		return calDateA.get(Calendar.YEAR) == calDateB.get(Calendar.YEAR) && calDateA.get(Calendar.MONTH) == calDateB.get(Calendar.MONTH) && calDateA.get(Calendar.DAY_OF_MONTH) == calDateB.get(Calendar.DAY_OF_MONTH);
	}

	/**
	 * 判断某个日期是否是未来的日期
	 *
	 * @author LiuGangQiang Create in 2020/03/01
	 * @param target 目标日期
	 * @return 是否是未来日期
	 */
	public boolean isBeforeNow(Date target) {
		if (target == null) {
			return false;
		}
		Calendar nowTime = Calendar.getInstance();
		Calendar endTime = Calendar.getInstance();
		endTime.setTime(target);
		return nowTime.before(endTime);
	}

	/**
	 * 判断某个日期是否在两个日期之间
	 *
	 * @author LiuGangQiang Create in 2020/03/01
	 * @param target    目标日期
	 * @param startTime 开始日期
	 * @param endTime   结束日期
	 * @return 是否在开始和结束日期之间
	 */
	public boolean isEffectiveDate(Date target, Date startTime, Date endTime) {
		if (target.getTime() == startTime.getTime() || target.getTime() == endTime.getTime()) {
			return true;
		}

		Calendar date = Calendar.getInstance();
		date.setTime(target);

		Calendar begin = Calendar.getInstance();
		begin.setTime(startTime);

		Calendar end = Calendar.getInstance();
		end.setTime(endTime);

		if (date.after(begin) && date.before(end)) {
			return true;
		} else {
			return false;
		}
	}

	/**
	 * 计算当前日期某毫秒之后的日期
	 *
	 * @author LiuGangQiang Create in 2019/06/05
	 * @param millisecond 毫秒数
	 * @return 当前日期某毫秒之后的日期
	 */
	public Date getMillisecondAfter(Long millisecond) {
		Date nowDate = new Date();
		Calendar calendar = Calendar.getInstance();
		calendar.setTime(nowDate);
		calendar.add(Calendar.MILLISECOND, millisecond.intValue());
		Date updateDate = calendar.getTime();
		return updateDate;
	}

	/**
	 * 计算当前时间某天之后的日期
	 *
	 * @author LiuGangQiang Create in 2019/06/05
	 * @param day 天数
	 * @return 当前时间某天之后的日期
	 */
	public Date getDayAfter(int day) {
		Date nowDate = new Date();
		Calendar calendar = Calendar.getInstance();
		calendar.setTime(nowDate);
		calendar.add(Calendar.DAY_OF_MONTH, day);
		Date updateDate = calendar.getTime();
		return updateDate;
	}

	/**
	 * 计算两个日期的秒数差
	 *
	 * @author LiuGangQiang Create in 2019/06/05
	 * @param startDate 开始日期
	 * @param endDate   结束日期
	 * @return 两个日期的秒数差
	 */
	public long calLastedTimes(Date startDate, Date endDate) {
		long timeEnd = endDate.getTime();
		long timeStart = startDate.getTime();
		long times = (timeEnd - timeStart) / 1000;
		return times;
	}
}