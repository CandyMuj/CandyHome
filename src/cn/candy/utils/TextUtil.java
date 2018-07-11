package cn.candy.utils;

import java.util.List;
import java.util.Map;

@SuppressWarnings("rawtypes")
public class TextUtil {

	public static String clNullStr(String s) {
		return isNull(s) ? "" : s;
	}

	public static String clNullStr(Object s) {
		return isNull(s) ? "" : String.valueOf(s);
	}

	public static boolean isNull(String s) {
		if (s == null || s.length() == 0 || "null".equalsIgnoreCase(s)) {
			return true;
		}
		return false;
	}

	public static boolean isNotNull(String s) {
		return !isNull(s);
	}

	public static boolean isNull(Object o) {
		if (o == null)
			return true;
		if (o instanceof String) {
			String s = String.valueOf(o);
			return isNull(s);
		}
		if (o instanceof String[]) {
			String[] s = (String[]) o;
			if (s.length == 0) {
				return true;
			}
		}
		if (o instanceof List) {
			List l = (List) o;
			if (l.isEmpty()) {
				return true;
			}
		}
		if (o instanceof Map) {
			Map m = (Map) o;
			if (m.isEmpty()) {
				return true;
			}
		}
		return false;
	}

	public static boolean isNotNull(Object o) {
		return !isNull(o);
	}

	/**
	 * 传入一个字符串，如果为空返回 ""
	 * 否则返回之前的，是为了不出现null串
	 * @return
	 */
	public static String getVal(Object obj) {
		return isNotNull(String.valueOf(obj)) ? String.valueOf(obj) : "";
	}

	/**
	 * 一个字符串，如果不为空就返回去除两边空格后的结果，如果为空返回空串
	 * 
	 * @param str
	 * @return
	 */
	public static String getTrim(String str) {
		return isNotNull(str) ? str.trim() : "";
	}

}
