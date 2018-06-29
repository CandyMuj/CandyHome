package com.candy.commons.settings;

import com.candy.commons.settings.component.iface.IDefaultSettingsComponent;
import com.candy.commons.springhelp.SpringContextHelper;

/**
 * 此类仅用于获取配置文件的配置
 * 	只会在启动时被初始化一次（或项目第一次被访问时-就是系统重启后无论第一次访问什么这个时候初始化）
 * 
 * @author jx003
 *
 */
public class DefaultSettings {

	/**
	 * 获取配置文件的配置 根据key获取
	 * 
	 * @param source
	 * @return
	 */
	public static String get(String key) {
		return SpringContextHelper.getBean(IDefaultSettingsComponent.class).get(key);
	}

}
