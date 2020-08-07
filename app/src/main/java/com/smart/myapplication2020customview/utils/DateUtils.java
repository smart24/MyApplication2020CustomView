package com.smart.myapplication2020customview.utils;

import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.Locale;

public class DateUtils {

	private static final SimpleDateFormat SDF_DISPLAY = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss", Locale.getDefault());
	private static final SimpleDateFormat SDF_DISPLAY_SHORT = new SimpleDateFormat("yyyy-MM-dd", Locale.getDefault());

	public static String toDateString(Date date) {
		if (date == null) {
			return null;
		}
		return SDF_DISPLAY.format(date);
	}

	public static String toDateShortString(Date date) {
		if (date == null) {
			return null;
		}
		return SDF_DISPLAY_SHORT.format(date);
	}

	public static String stampToDate(String timeStampString){
		long timeStamp = Long.parseLong(timeStampString);
		return stampToDate(timeStamp);
	}

	public static String stampToDate(long timeStamp){
		Date date = new Date(timeStamp);
		return SDF_DISPLAY.format(date);
	}

	public static long dateToStamp(String s){
		Date date = null;
		try {
			date = SDF_DISPLAY.parse(s);
		} catch (ParseException e) {
			e.printStackTrace();
		}
		return date.getTime();
	}

	/**
	 * 秒转化为时分秒
	 */
	public static String secondToTime(int time) {
		String timeStr = null;
		int hour = 0;
		int minute = 0;
		int second = 0;
		if (time <= 0)
			return "00:00";
		else {
			minute = time / 60;
			if (minute < 60) {
				second = time % 60;
				timeStr = "00:" + formatNumber(minute) + ":" + formatNumber(second);
			} else {
				hour = minute / 60;
				if (hour > 99)
					return "99:59:59";
				minute = minute % 60;
				second = time - hour * 3600 - minute * 60;
				timeStr = formatNumber(hour) + ":" + formatNumber(minute) + ":" + formatNumber(second);
			}
		}
		return timeStr;
	}

	private static String formatNumber(int i) {
		String retStr = null;
		if (i >= 0 && i < 10)
			retStr = "0" + Integer.toString(i);
		else
			retStr = "" + i;
		return retStr;
	}

}
