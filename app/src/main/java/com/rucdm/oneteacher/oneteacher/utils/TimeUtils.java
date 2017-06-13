package com.rucdm.oneteacher.oneteacher.utils;

import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Date;
import android.annotation.SuppressLint;
import android.content.Context;

@SuppressLint("SimpleDateFormat")
public class TimeUtils {
	private static TimeUtils timeUtils;
	private static Context context;

	private TimeUtils() {
	}

	public static TimeUtils getInstance(Context context) {
		TimeUtils.context = context;
		if (timeUtils == null) {
			timeUtils = new TimeUtils();
		}
		return timeUtils;
	}

	public long StringToLong(String time) {
		SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM-dd");
		Date date = null;
		try {
			date = sdf.parse(time);
		} catch (ParseException e) {
			e.printStackTrace();
		}
		long l = date.getTime();
		return l;
	}
}
