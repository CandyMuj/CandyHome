package com.candy.commons.service;

import java.util.List;

import org.apache.log4j.Logger;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.aliyuncs.dysmsapi.model.v20170525.SendSmsResponse;
import com.candy.commons.settings.DefaultSettings;

import cn.candy.candyhome.user.mapper.generator.UserActivationOperationMapper;
import cn.candy.candyhome.user.po.generator.UserActivationOperation;
import cn.candy.candyhome.user.po.generator.UserActivationOperationExample;
import cn.candy.utils.RandomString;
import cn.candy.utils.communication.phone.AliDysms;

@Service
public class CodeService {
	private static final Logger log = Logger.getLogger(CodeService.class);

	@Autowired
	private UserActivationOperationMapper userActivationOperationMapper;

	/**
	 * 发送验证码
	 * 
	 * @param operationType	验证码用于什么操作
	 * @param onlyKey	登录注册时随机串，其他操作为用户id
	 * @param sendTo	发送给谁
	 * @throws Exception
	 */
	public void sendPhoneActiveCode(String operationType, String onlyKey, String sendTo) throws Exception {
		UserActivationOperation operation = new UserActivationOperation();
		String code = new RandomString().randomOnlyNumber(7);
		operation.setUid(onlyKey);
		operation.setActivationCode(code);
		operation.setActivationType("phone");
		operation.setSendTo(sendTo);
		operation.setOperationType(operationType);

		try {
			SendSmsResponse sendSmsResponse = AliDysms.getInstance().sendSms(sendTo,
					DefaultSettings.get("AliSendSms.templateCode.registe"), "{\"code\":\"" + code + "\"}", null, null);
			if (sendSmsResponse.getCode() == null || !sendSmsResponse.getCode().equals("OK")) {
				operation.setRemark("ali接口返回结果错误");
			}
		} catch (Exception e) {
			e.printStackTrace();
			operation.setRemark("[Exception]" + (e.getClass() + ": " + e.getMessage()).substring(6));
		}
		userActivationOperationMapper.insertSelective(operation);
		log.info("sendCode success");
	}

	/**
	 * 验证验证码是否正确
	 * 
	 * @param onlyKey 唯一键
	 * @param code	
	 * @return
	 * @throws Exception
	 */
	public boolean checkPhoneActiveCode(String onlyKey, String code) throws Exception {
		UserActivationOperationExample example = new UserActivationOperationExample();
		UserActivationOperationExample.Criteria criteria = example.createCriteria();
		criteria.andUidEqualTo(onlyKey);
		criteria.andActivationCodeEqualTo(code);
		List<UserActivationOperation> list = userActivationOperationMapper.selectByExample(example);
		if (list.size() == 1) {
			return true;
		} else {
			return false;
		}
	}

}
