package cn.candy.candyhome.user.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import cn.candy.candyhome.user.mapper.generator.UserActivationOperationMapper;
import cn.candy.candyhome.user.po.UserSession;
import cn.candy.candyhome.user.po.generator.UserActivationOperation;
import cn.candy.candyhome.user.service.iface.IUserLoginService;

@Service
public class UserLoginService implements IUserLoginService {

	@Autowired
	private UserActivationOperationMapper userActivationOperationMapper;

	/**
	 * 发送验证码
	 * 
	 * @param sendType	发送类型 手机：phone 邮箱：email
	 * @param userSession	当前用户信息
	 * @param isRegist	当前是否是注册，如果是注册在保存注册码的时候会默认指定uid为regist
	 * @return
	 * @throws Exception
	 */
	@Override
	public String sendActiveCode(String sendType, UserSession userSession, boolean isRegist) throws Exception {
		UserActivationOperation userActivationOperation = new UserActivationOperation();
		userActivationOperation.setUid("11");
		userActivationOperation.setSendTo("222");
		userActivationOperationMapper.insert(userActivationOperation);

		return null;
	}

}
