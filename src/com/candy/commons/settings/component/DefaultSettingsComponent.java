package com.candy.commons.settings.component;

import java.io.BufferedReader;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.util.HashSet;
import java.util.Properties;
import java.util.Set;

import org.apache.log4j.Logger;
import org.springframework.stereotype.Component;

import com.candy.commons.settings.component.iface.IDefaultSettingsComponent;

import cn.candy.utils.TextUtil;

/**
 * 全局配置获取处理类
 * 	要求只被初始化实例化一次，那么就要写构造，并且要加载到spring容器中，这样就只会在启动时加载一次了
 *  如果用继承或者其他方法，都是每次一调用都会被实例化一次，而交给spring管理就只是启动的时候实例化一次
 * 
 * @author jx003
 *
 */
@Component
public class DefaultSettingsComponent implements IDefaultSettingsComponent {
	private static final Logger log = Logger.getLogger(DefaultSettingsComponent.class);

	// 全局配置文件所在路径
	private static final String ROOT_PATH = "properties/commons/";

	// 配置文件
	private static final String INCLUD_FILE = "includconfig.properties";

	private static final Properties properties = new Properties();

	public DefaultSettingsComponent() {
		this.getProperties();
	}

	@Override
	public String get(String key) {
		return (String) properties.get(key);
	}

	/**
	 * 获取解析后的 Properties
	 * 
	 */
	private void getProperties() {
		ClassLoader classLoader = DefaultSettingsComponent.class.getClassLoader();
		Set<String> set = this.getPropertiesFileName(classLoader);
		log.info("Initializing properties size [" + set.size() + "]");

		try {
			for (String str : set) {
				properties.load(classLoader.getResourceAsStream(ROOT_PATH + str));
			}
			log.info("initialized properties size [" + properties.size() + "]");
		} catch (Exception e) {
			e.printStackTrace();
		}
	}

	/**
	 * 获取配置文件列表
	 * 
	 * @return
	 */
	private Set<String> getPropertiesFileName(ClassLoader classLoader) {
		Set<String> set = new HashSet<>();
		InputStream inputStream = null;
		InputStreamReader inputStreamReader = null;
		BufferedReader bufferedReader = null;
		try {
			inputStream = classLoader.getResourceAsStream(ROOT_PATH + INCLUD_FILE);
			inputStreamReader = new InputStreamReader(inputStream);
			bufferedReader = new BufferedReader(inputStreamReader);

			String str = null;
			while ((str = bufferedReader.readLine()) != null) {
				if (str.indexOf("#") == -1) {
					if (TextUtil.isNotNull(str.trim())) {
						set.add(str);
					}
				}
			}
		} catch (Exception e) {
			e.printStackTrace();
		} finally {
			try {
				if (inputStream != null) inputStream.close();
				if (inputStreamReader != null) inputStreamReader.close();
				if (bufferedReader != null) bufferedReader.close();
			} catch (Exception e) {}
		}
		return set;
	}

}
