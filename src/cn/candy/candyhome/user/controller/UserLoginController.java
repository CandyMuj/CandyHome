package cn.candy.candyhome.user.controller;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import org.apache.log4j.Logger;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;

import com.candy.commons.controller.SuperController;
import com.candy.commons.service.CodeService;

import cn.candy.candyhome.user.service.iface.IUserLoginService;
import cn.candy.utils.RandomString;
import cn.candy.utils.TextUtil;

@Controller
@RequestMapping("/login")
public class UserLoginController extends SuperController {
	private static final Logger log = Logger.getLogger(UserLoginController.class);

	@Autowired
	private IUserLoginService userLoginService;

	@Autowired
	private CodeService codeService;

	/**
	 * 登录页面跳转
	 * 
	 * @return
	 * @throws Exception
	 */
	@RequestMapping("/login")
	public String login(Model model) throws Exception {
		// 本次请求唯一id，用于验证码获取使用，标识唯一的一次请求
		RandomString randomString = new RandomString();
		randomString.setPrefix("[login]");
		model.addAttribute("codeuuid", randomString.random2All(12));
		return "/login/login";
	}

	// -------------------------

	/**
	 * 发送验证码
	 * 支持发送邮箱，和手机
	 * 类型
	 * 	手机：phone
	 * 	邮箱：email
	 * 
	 * @throws Exception
	 */
	@RequestMapping("/sendActiveCode")
	public void sendActiveCode(HttpServletRequest request, HttpServletResponse response, String codeuuid, String sendTo) {
		boolean boo = false;
		String msg = "请重试";
		try {
			if (TextUtil.isNotNull(codeuuid) && TextUtil.isNotNull(sendTo)) {
				msg = codeService.sendPhoneActiveCode("regist", codeuuid, sendTo);
				if ("true".equals(msg)) {
					boo = true;
				}
			}
		} catch (Exception e) {
			log.error("发送验证码异常...");
			e.printStackTrace();
		}
		super.write2Page("{\"flag\":" + boo + ",\"msg\":\"" + msg + "\"}", response);
	}

	/**
	 * 用户注册的方法
	 * 
	 * @return
	 * @throws Exception
	 */
	@RequestMapping(value = "/regist")
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
	@RequestMapping(value = "/tologin", method = RequestMethod.POST)
	public String tologin(HttpServletRequest request, HttpSession session) throws Exception {

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
