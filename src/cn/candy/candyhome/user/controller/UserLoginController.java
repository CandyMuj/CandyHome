package cn.candy.candyhome.user.controller;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpSession;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;

import com.candy.commons.controller.SuperController;

import cn.candy.candyhome.user.service.iface.IUserLoginService;

@Controller
@RequestMapping("/login")
public class UserLoginController extends SuperController {

	@Autowired
	private IUserLoginService userLoginService;

	/**
	 * 登录页面跳转
	 * 
	 * @return
	 * @throws Exception
	 */
	public String toLoginPage() throws Exception {

		return "";
	}

	/**
	 * 登录成功 跳转到首页
	 * 
	 * @return
	 * @throws Exception
	 */
	public String toIndexPage() throws Exception {

		return "";
	}

	/**
	 * 用户注册的方法
	 * 
	 * @return
	 * @throws Exception
	 */
	public String regist() throws Exception {

		return "";
	}

	/**
	 * 用户登录的方法
	 * 
	 * @param request
	 * @param session
	 * @return
	 * @throws Exception
	 */
	@RequestMapping(value = "/login", method = RequestMethod.POST)
	public String login(HttpServletRequest request, HttpSession session) throws Exception {

		return "";
	}

	/**
	 * 验证手机号和邮箱
	 * 	
	 * @param checkType 验证类型 手机：phone  邮箱：email
	 * @return
	 * @throws Exception
	 */
	public String active(HttpServletRequest request, String checkType) throws Exception {

		return "";
	}

	/**
	 * 校验用户名是否存在
	 * 
	 * @return
	 * @throws Exception
	 */
	public String checkUserName() throws Exception {

		return "";
	}

	/**
	 * 退出登录
	 * 
	 * @return
	 * @throws Exception
	 */
	public String exit() throws Exception {

		return "";
	}

}
