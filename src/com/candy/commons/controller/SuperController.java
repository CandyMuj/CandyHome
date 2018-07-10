package com.candy.commons.controller;

import java.io.IOException;

import javax.servlet.http.HttpServletResponse;

import org.springframework.web.context.request.RequestContextHolder;
import org.springframework.web.context.request.ServletRequestAttributes;

import com.candy.commons.settings.DefaultSettings;

import cn.candy.candyhome.user.po.CandyUserInfo;
import cn.candy.candyhome.user.po.UserSession;

/**
 * 自定义的Controller类，实现一些通用的方法
 * 
 * @author jx003
 *
 */
public class SuperController {
	
	/**
	 * 获取用户信息包装类
	 * 
	 * @param session
	 * @return
	 */
	protected CandyUserInfo getUserInfo() throws Exception {
		return getUserSession().getCandyUserInfo();
	}

	/**
	 * 获取用户session
	 * 
	 * @param session
	 * @return
	 */
	protected UserSession getUserSession() throws Exception{
		return (UserSession) ((ServletRequestAttributes)RequestContextHolder.getRequestAttributes()).getRequest().getSession().getAttribute(DefaultSettings.get("user.session.name"));
	}

	/**
	 * 回送页面的功能
	 * 默认不支持跨域
	 * 
	 * @param str
	 * @param response
	 */
	protected void write2Page(String str, HttpServletResponse response) {
		this.write2Page(str, response, false);
	}

	/**
	 * 回送页面的功能
	 * 
	 * @param str
	 * @param response
	 * @param region 跨域返回支持
	 */
	protected void write2Page(String str, HttpServletResponse response, boolean region) {
		try {
			// System.out.println("返回页面的内容------------>> "+str);
			if (region) { // 设置跨域支持
				response.setHeader("Cache-Control", "no-cache");
				response.setHeader("Access-Control-Allow-Origin", "*");
			}
			response.setContentType("text/html;charset=utf-8");
			response.getWriter().write(str);
		} catch (IOException e) {
			e.printStackTrace();
		} finally {
			try {
				response.getWriter().close();
			} catch (IOException e) {
				e.printStackTrace();
			}
		}
	}

}
