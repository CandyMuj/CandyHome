package com.candy.commons.service;

import java.util.Date;
import java.util.List;

import org.apache.log4j.Logger;
import org.junit.Test;
import org.springframework.beans.BeanUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.aliyuncs.dysmsapi.model.v20170525.SendSmsResponse;
import com.candy.commons.settings.DefaultSettings;

import cn.candy.candyhome.user.mapper.generator.UserActivationOperationMapper;
import cn.candy.candyhome.user.po.generator.UserActivationOperation;
import cn.candy.candyhome.user.po.generator.UserActivationOperationExample;
import cn.candy.utils.RandomString;
import cn.candy.utils.communication.phone.AliDysms;

@Service
@Transactional
public class CodeService {
	private static final Logger log = Logger.getLogger(CodeService.class);

	@Autowired
	private UserActivationOperationMapper userActivationOperationMapper;

	/**
	 * 发送手机验证码  6 位验证码
	 * 
	 * @param operationType	验证码用于什么操作
	 * @param onlyKey	登录注册时随机串，其他操作为用户id
	 * @param sendTo	发送给谁
	 * @throws Exception
	 */
	public String sendPhoneActiveCode(String operationType, String onlyKey, String sendTo) throws Exception {
		UserActivationOperation operation = new UserActivationOperation();

		String msg = "获取失败";
		try {
			UserActivationOperation sOperation = userActivationOperationMapper.selectByPrimaryKey(onlyKey);
			if (sOperation != null) {
				operation.setUid(onlyKey);
				operation.setActivationCode(sOperation.getActivationCode());
				operation.setActivationType("phone");
				operation.setSendTo(sendTo);
				operation.setOperationType(operationType);
				operation.setSendnum(sOperation.getSendnum() + 1);
				operation.setSendTime(new Date());
			}else {
				String code = new RandomString().randomOnlyNumber(6);
				operation.setUid(onlyKey);
				operation.setActivationCode(code);
				operation.setActivationType("phone");
				operation.setSendTo(sendTo);
				operation.setOperationType(operationType);
				operation.setSendnum(1);
			}
			
			SendSmsResponse sendSmsResponse = AliDysms.getInstance().sendSms(sendTo,DefaultSettings.get("AliSendSms.templateCode.registe"), "{\"code\":\"" + operation.getActivationCode() + "\"}", null, null);
			if(sendSmsResponse.getCode() != null) {
				if (sendSmsResponse.getCode().equals("OK")) {
					msg = "true";
				}else if (sendSmsResponse.getCode().equals("isv.BUSINESS_LIMIT_CONTROL")) {
					msg = "触发流控:1条/分钟,5条/小时 ,累计10条/天";
				}else {
					operation.setRemark("ali接口返回结果错误");
				}
			}else {
				operation.setRemark("ali接口返回结果为空");
			}
			
			if (sOperation != null) {
				userActivationOperationMapper.updateByPrimaryKeySelective(operation);
			} else {
				userActivationOperationMapper.insertSelective(operation);
			}
			log.info("sendCode success");
		} catch (Exception e) {
			log.info("sendCode error");
			e.printStackTrace();
			operation.setRemark("[Exception]" + (e.getClass() + ": " + e.getMessage()).substring(6));
		}
		return msg;
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
		
		// 获取短信验证码有效时间
		Integer	ii = Integer.valueOf(DefaultSettings.get("timeout.sendcode.phone")); 
		
		List<UserActivationOperation> list = userActivationOperationMapper.selectByExample(example);
		if (list.size() == 1) {
			return true;
		} else {
			return false;
		}
	}

}
