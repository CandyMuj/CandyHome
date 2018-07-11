package cn.candy.candyhome.user.po;

import cn.candy.candyhome.user.po.generator.UserAccount;
import cn.candy.candyhome.user.po.generator.UserInfo;

/**
 * 用户信息包装类
 * 
 * @author jx003
 *
 */
public class CandyUserInfo {

	private UserAccount userAccount;

	private UserInfo userInfo;

	public UserAccount getUserAccount() {
		return userAccount;
	}

	public void setUserAccount(UserAccount userAccount) {
		this.userAccount = userAccount;
	}

	public UserInfo getUserInfo() {
		return userInfo;
	}

	public void setUserInfo(UserInfo userInfo) {
		this.userInfo = userInfo;
	}

}
