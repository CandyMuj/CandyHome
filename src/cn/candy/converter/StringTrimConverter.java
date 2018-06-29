package cn.candy.converter;

import org.springframework.core.convert.converter.Converter;

/**
 * 去掉字符串两边的空格，如果去除后为空设置为null，否则返回去除空格后的字符串
 * 
 * @author jx003
 *
 */
public class StringTrimConverter implements Converter<String, String> {

	@Override
	public String convert(String source) {
		try {
			// 进行转换
			return (source != null && !source.trim().equals("")) ? source.trim() : null;
		} catch (Exception e) {
			e.printStackTrace();
		}
		return null;
	}

}
