package cn.candy.candyhome.user.service.iface;

import cn.candy.candyhome.user.po.UserSession;

public interface IUserLoginService {

	/**
	 * 发送验证码
	 * 
	 * @param sendType	发送类型 手机：phone 邮箱：email
	 * @param userSession	当前用户信息
	 * @param isRegist	当前是否是注册，如果是注册在保存注册码的时候会默认指定uid为regist
	 * @return
	 * @throws Exception
	 */
	String sendActiveCode(String sendType, UserSession userSession, boolean isRegist) throws Exception;

}
