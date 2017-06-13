package com.rucdm.oneteacher.oneteacher.utils;

import android.content.Context;
import android.content.SharedPreferences;

public class SpUtils {
	public static final String NAME = "NAME";
	public static final String PASSWORD = "PASSWORD";
	public static final String CONFIGURE = "CONFIGURE";
	public static final String SIM_NUMBER = "SIM_NUMBER";
	public static final String SAFE_NUMBER = "SAFE_NUMBER";
	public static final String PROTECT = "PROTECT";
	public static final String LEFT = "LEFT";
	public static final String TOP = "TOP";
	public static final String STYLE_INDEX = "STYLE_INDEX";
	public static final String SHORT_CUT = "SHORT_CUT";

	private static SharedPreferences sp;
	private static SpUtils instance;

	private SpUtils() {
	}

	public static SpUtils getInstance(Context context) {
		if (instance == null) {
			sp = context.getSharedPreferences("PhoneGuard",
					Context.MODE_PRIVATE);
			instance = new SpUtils();
		}
		return instance;
	}

	public void save(String key, Object value) {
		if (value instanceof String) {
			sp.edit().putString(key, (String) value).commit();
		} else if (value instanceof Integer) {
			sp.edit().putInt(key, (Integer) value).commit();
		} else if (value instanceof Boolean) {
			sp.edit().putBoolean(key, (Boolean) value).commit();
		} else if (value instanceof Long) {
			sp.edit().putLong(key, (Long) value).commit();
		}
	}

	public <T> T getValue(String key, T defValue) {
		T t = null;
		if (defValue == null || defValue instanceof Long) {
			Long value = sp.getLong(key, (Long) defValue);
			t = (T) value;
		}
		if (defValue == null || defValue instanceof String) {
			t = (T) sp.getString(key, (String) defValue);
		}

		if (defValue instanceof Integer) {// 0/-1
			Integer value = sp.getInt(key, (Integer) defValue);
			t = (T) value;
		}

		if (defValue instanceof Boolean) {// false
			Boolean value = sp.getBoolean(key, (Boolean) defValue);
			t = (T) value;
		}
		return t;
	}

	public void remove(String key) {
		sp.edit().remove(key).commit();
	}
}
