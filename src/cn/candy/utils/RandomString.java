package cn.candy.utils;

import java.util.UUID;

/**
 * 用于生产随机字符串
 * 默认生成长度10（除了UUID）
 * 
 * @author jx003
 *
 */
public class RandomString {

	// 设置前缀
	private String prefix = "";

	// 设置后缀
	private String suffix = "";

	// 默认长度
	private int defaultLength = 10;

	/**
	 * 根据长度生成随机字符串
	 * 
	 * @param length 字串长度
	 * @return
	 */
	public String randomOnlyLetter(int length) {
		String keyString = "abcdefghijklmnopqrstuvwxyz";
		StringBuffer sbuff = new StringBuffer(prefix);
		randomByLength(sbuff, keyString, length);
		return sbuff.append(suffix).toString();
	}

	/**
	 * 仅随机大写字母
	 * 
	 * @param length
	 * @return
	 */
	public String randomOnlyUpper(int length) {
		String keyString = "ABCDEFGHIJKLMNOPQRSTUVWXYZ";
		StringBuffer sbuff = new StringBuffer(prefix);
		randomByLength(sbuff, keyString, length);
		return sbuff.append(suffix).toString();
	}

	public String randomOnlyNumber(int length) {
		String keyString = "0123456789";
		StringBuffer sbuff = new StringBuffer(prefix);
		randomByLength(sbuff, keyString, length);
		return sbuff.append(suffix).toString();
	}

	public String random2LU(int length) {
		String keyString = "abcdefghijklmnopqrstuvwxyzABCDEFGHIJKLMNOPQRSTUVWXYZ";
		StringBuffer sbuff = new StringBuffer(prefix);
		randomByLength(sbuff, keyString, length);
		return sbuff.append(suffix).toString();
	}

	public String random2LN(int length) {
		String keyString = "abcdefghijklmnopqrstuvwxyz0123456789";
		StringBuffer sbuff = new StringBuffer(prefix);
		randomByLength(sbuff, keyString, length);
		return sbuff.append(suffix).toString();
	}

	public String random2UN(int length) {
		String keyString = "ABCDEFGHIJKLMNOPQRSTUVWXYZ0123456789";
		StringBuffer sbuff = new StringBuffer(prefix);
		randomByLength(sbuff, keyString, length);
		return sbuff.append(suffix).toString();
	}

	public String random2All(int length) {
		String keyString = "abcdefghijklmnopqrstuvwxyzABCDEFGHIJKLMNOPQRSTUVWXYZ0123456789";
		StringBuffer sbuff = new StringBuffer(prefix);
		randomByLength(sbuff, keyString, length);
		return sbuff.append(suffix).toString();
	}

	/**
	 * 自定义字符串生成
	 * 
	 * @param length
	 * @return
	 */
	public String randomByCustom(String keyString, int length) {
		StringBuffer sbuff = new StringBuffer(prefix);
		randomByLength(sbuff, keyString, length);
		return sbuff.append(suffix).toString();
	}

	private StringBuffer randomByLength(StringBuffer sbuff, String keyString, int length) {
		if (length <= 0) {
			length = defaultLength;
		}
		int len = keyString.length();
		for (int i = 0; i < length; i++) {
			sbuff.append(keyString.charAt((int) Math.round(Math.random() * (len - 1))));
		}
		return sbuff;
	}

	/**
	 * 根据uuid生产随机字串
	 * 
	 * @param format  是否需要剔除 "-"
	 * @return
	 */
	public String randomByUUID(boolean format) {
		return format ? UUID.randomUUID().toString().replace("-", "") : UUID.randomUUID().toString();
	}

	public void setPrefix(String prefix) {
		this.prefix = prefix;
	}

	public void setSuffix(String suffix) {
		this.suffix = suffix;
	}

}
