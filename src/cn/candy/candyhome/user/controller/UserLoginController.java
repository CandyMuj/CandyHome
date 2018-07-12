package cn.candy.candyhome.user.controller;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
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
	@RequestMapping("/login")
	public String login() throws Exception {
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
	public void sendActiveCode(HttpServletRequest request, HttpServletResponse response, String sendType) throws Exception {
		System.out.println("-------------------------");
		userLoginService.sendActiveCode(sendType, super.getUserSession(), true);
	}
	
	/**
	 * 用户注册的方法
	 * 
	 * @return
	 * @throws Exception
	 */
	@RequestMapping(value = "/regist")
	public String regist() throws Exception {
		
//		测试发送短信
//		AliDysms aliSendSms = AliDysms.getInstance();
//		String phoneNumber = "13551255397";
//		String templateName = DefaultSettings.get("AliSendSms.templateCode.registe");
//		String templateParam = "{\"code\":\"123\"}";
//		String outId = DefaultSettings.get("AliSendSms.outId.registe");
//		SendSmsResponse response = aliSendSms.sendSms(phoneNumber,templateName,templateParam,"","");
//		
//		Thread.sleep(3000L);
//		
//		aliSendSms.querySendDetails(phoneNumber,response.getBizId(),new Date(),10L,1L);
		
		
//		测试发送邮件
//		CustomMailService.getInstance().sendMail("766557580@qq.com", "测试", "测试一下嘻嘻嘻");
		
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
