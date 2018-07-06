package cn.candy.utils.communication.phone;

import java.text.SimpleDateFormat;
import java.util.Date;

import org.apache.log4j.Logger;

import com.aliyuncs.DefaultAcsClient;
import com.aliyuncs.IAcsClient;
import com.aliyuncs.dysmsapi.model.v20170525.QuerySendDetailsRequest;
import com.aliyuncs.dysmsapi.model.v20170525.QuerySendDetailsResponse;
import com.aliyuncs.dysmsapi.model.v20170525.SendSmsRequest;
import com.aliyuncs.dysmsapi.model.v20170525.SendSmsResponse;
import com.aliyuncs.exceptions.ClientException;
import com.aliyuncs.profile.DefaultProfile;
import com.aliyuncs.profile.IClientProfile;
import com.candy.commons.settings.DefaultSettings;

import cn.candy.utils.TextUtil;

/**
 * 阿里云短信服务接口封装
 * 
 * Created on 17/6/7.
 * 短信API产品的DEMO程序,工程中包含了一个SmsDemo类，直接通过
 * 执行main函数即可体验短信产品API功能(只需要将AK替换成开通了云通信-短信产品功能的AK即可)
 * 工程依赖了2个jar包(存放在工程的libs目录下)
 * 1:aliyun-java-sdk-core.jar
 * 2:aliyun-java-sdk-dysmsapi.jar
 *
 * 备注:Demo工程编码采用UTF-8
 * 有备注无需修改的位置请勿改动。
 * 国际短信发送请勿参照此DEMO
 * 
 * @author jx003
 *
 */
public class AliDysms {
	
	private static AliDysms SINGLE_CASE = null;
	
	private static final Logger log = Logger.getLogger(AliDysms.class);
	
	// 产品名称:云通信短信API产品,开发者无需替换
    static final String product = DefaultSettings.get("AliSendSms.product");
    // 产品域名,开发者无需替换
    static final String domain = DefaultSettings.get("AliSendSms.domain");

    // 此处需要替换成开发者自己的AK(在阿里云访问控制台寻找)
    static final String accessKeyId = DefaultSettings.get("AliSendSms.accessKeyId");
    static final String accessKeySecret = DefaultSettings.get("AliSendSms.accessKeySecret");

    
	private AliDysms() {
	}

	public static synchronized AliDysms getInstance() {
		if(SINGLE_CASE == null) {
			SINGLE_CASE = new AliDysms();
		}
		return SINGLE_CASE;
	}
	
	/**
     * 用于短信发送
     * 
     * @param phoneNumbers 必填：待发送手机号 支持以逗号分隔的形式进行批量调用
     * @param templateCode 必填：模板编码，由于此类是公用的，所以模板编码每次传递，配置在配置文件中
     * @param templateParam 可选：模板对应参数 json格式 例：{\"name\":\"Tom\", \"code\":\"123\"}
     * @param smsUpExtendCode 可选：上行短信扩展码(扩展码字段控制在7位或以下，无特殊需求用户请忽略此字段)
     * @param outId 可选：outId为提供给业务方扩展字段,最终在短信回执消息中将此值带回给调用者
     * @return
     * @throws ClientException
     */
	public SendSmsResponse sendSms(String phoneNumbers, String templateCode, String templateParam, String smsUpExtendCode, String outId) throws ClientException {
		
		// 发短信
        SendSmsResponse response = toSendSms(phoneNumbers, templateCode, templateParam, smsUpExtendCode, outId);
        if(response.getCode() != null && response.getCode().equals("OK")) {
			// 请求成功
        	log.info("短信接口请求成功----------------");
        }
        
        if ("true".equalsIgnoreCase(DefaultSettings.get("AliSendSms.sendSms.printLog"))) {
	        System.out.println("短信接口返回的数据----------------");
	        System.out.println("Code=" + response.getCode());
	        System.out.println("Message=" + response.getMessage());
	        System.out.println("RequestId=" + response.getRequestId());
	        System.out.println("BizId=" + response.getBizId());
        }
        
		return response;
	}
	
	private SendSmsResponse toSendSms(String phoneNumbers, String templateCode, String templateParam, String smsUpExtendCode, String outId) throws ClientException {

        // 可自助调整超时时间
        System.setProperty("sun.net.client.defaultConnectTimeout", DefaultSettings.get("AliSendSms.defaultConnectTimeout"));
        System.setProperty("sun.net.client.defaultReadTimeout", DefaultSettings.get("AliSendSms.defaultReadTimeout"));

		// 初始化acsClient,暂不支持region化（请勿修改）
        IClientProfile profile = DefaultProfile.getProfile("cn-hangzhou", accessKeyId, accessKeySecret);
        DefaultProfile.addEndpoint("cn-hangzhou", "cn-hangzhou", product, domain);
        IAcsClient acsClient = new DefaultAcsClient(profile);

        // 组装请求对象-具体描述见控制台-文档部分内容
        SendSmsRequest request = new SendSmsRequest();
		// 必填:待发送手机号。
        // 支持以逗号分隔的形式进行批量调用，批量上限为1000个手机号码,批量调用相对于单条调用及时性稍有延迟,验证码类型的短信推荐使用单条调用的方式；
        // 发送国际/港澳台消息时，接收号码格式为00+国际区号+号码，如“0085200000000”
		request.setPhoneNumbers(phoneNumbers);
		// 必填:短信签名-可在短信控制台中找到
		request.setSignName(DefaultSettings.get("AliSendSms.signName"));
		// 必填:短信模板-可在短信控制台中找到
		request.setTemplateCode(templateCode);
		// 可选:模板中的变量替换JSON串,如模板内容为"亲爱的${name},您的验证码为${code}"时,此处的值为
		// 友情提示:如果JSON中需要带换行符,请参照标准的JSON协议对换行符的要求,比如短信内容中包含\r\n的情况在JSON中需要表示成\\r\\n,否则会导致JSON在服务端解析失败
		if(TextUtil.isNotNull(templateParam)) {
			request.setTemplateParam(templateParam);
		}

		// 可选-上行短信扩展码(扩展码字段控制在7位或以下，无特殊需求用户请忽略此字段)
		if(TextUtil.isNotNull(smsUpExtendCode)) {
			request.setSmsUpExtendCode(smsUpExtendCode);
		}

		// 可选:outId为提供给业务方扩展字段,最终在短信回执消息中将此值带回给调用者
		if (TextUtil.isNotNull(outId)) {
			request.setOutId(outId);
		}

		// hint 此处可能会抛出异常，注意catch
        // 请求失败这里会抛ClientException异常
        SendSmsResponse sendSmsResponse = acsClient.getAcsResponse(request);

        return sendSmsResponse;
    }


    /**
     * 查询发送回执明细
     * 
     * @param phoneNumber 必填：短信接收号码
     * @param bizId	可选：发送流水号,从调用发送接口返回值中获取
     * @param sendDate 必填：短信发送日期格式yyyyMMdd,支持最近30天记录查询
     * @param pageSize 必填：页大小Max=50
     * @param currentPage 必填：当前页码
     * @return
     * @throws ClientException
     */
    public QuerySendDetailsResponse querySendDetails(String phoneNumber, String bizId, Date sendDate,Long pageSize,Long currentPage) throws ClientException {
    	
    	// 查明细
    	QuerySendDetailsResponse querySendDetailsResponse = toQuerySendDetails(phoneNumber, bizId, sendDate, pageSize, currentPage);
    	
		if (querySendDetailsResponse.getCode() != null && querySendDetailsResponse.getCode().equals("OK")) {
			// 代表请求成功
			log.info("短信明细查询接口请求成功----------------");
		}
    	
		// 获取并打印返回结果
    	if ("true".equalsIgnoreCase(DefaultSettings.get("AliSendSms.querySendDetails.printLog"))) {
            System.out.println("短信明细查询接口返回的数据----------------");
            System.out.println("Code=" + querySendDetailsResponse.getCode());
            System.out.println("Message=" + querySendDetailsResponse.getMessage());
            int i = 0;
            for(QuerySendDetailsResponse.SmsSendDetailDTO smsSendDetailDTO : querySendDetailsResponse.getSmsSendDetailDTOs())
            {
                System.out.println("SmsSendDetailDTO["+i+"]:");
                System.out.println("Content=" + smsSendDetailDTO.getContent());
                System.out.println("ErrCode=" + smsSendDetailDTO.getErrCode());
                System.out.println("OutId=" + smsSendDetailDTO.getOutId());
                System.out.println("PhoneNum=" + smsSendDetailDTO.getPhoneNum());
                System.out.println("ReceiveDate=" + smsSendDetailDTO.getReceiveDate());
                System.out.println("SendDate=" + smsSendDetailDTO.getSendDate());
                System.out.println("SendStatus=" + smsSendDetailDTO.getSendStatus());
                System.out.println("Template=" + smsSendDetailDTO.getTemplateCode());
            }
            System.out.println("TotalCount=" + querySendDetailsResponse.getTotalCount());
            System.out.println("RequestId=" + querySendDetailsResponse.getRequestId());
    	}
    	
    	return querySendDetailsResponse;
    }
    
    private QuerySendDetailsResponse toQuerySendDetails(String phoneNumber, String bizId, Date sendDate,Long pageSize,Long currentPage) throws ClientException {

		// 可自助调整超时时间
        System.setProperty("sun.net.client.defaultConnectTimeout", DefaultSettings.get("AliSendSms.defaultConnectTimeout"));
        System.setProperty("sun.net.client.defaultReadTimeout", DefaultSettings.get("AliSendSms.defaultReadTimeout"));

		// 初始化acsClient,暂不支持region化
        IClientProfile profile = DefaultProfile.getProfile("cn-hangzhou", accessKeyId, accessKeySecret);
        DefaultProfile.addEndpoint("cn-hangzhou", "cn-hangzhou", product, domain);
        IAcsClient acsClient = new DefaultAcsClient(profile);

		// 组装请求对象
		QuerySendDetailsRequest request = new QuerySendDetailsRequest();
		// 必填-号码
		// 短信接收号码,如果需要查询国际短信,号码前需要带上对应国家的区号,区号的获取详见国际短信支持国家信息查询API接口
		request.setPhoneNumber(phoneNumber);
		// 可选-流水号
		if (TextUtil.isNotNull(bizId)) {
			request.setBizId(bizId);
		}
		// 必填-发送日期 支持30天内记录查询，格式yyyyMMdd
		SimpleDateFormat ft = new SimpleDateFormat("yyyyMMdd");
		request.setSendDate(ft.format(sendDate));
		// 必填-页大小
		request.setPageSize(pageSize);
		// 必填-当前页码从1开始计数
		request.setCurrentPage(currentPage);

		// hint 此处可能会抛出异常，注意catch
		QuerySendDetailsResponse querySendDetailsResponse = acsClient.getAcsResponse(request);

        return querySendDetailsResponse;
    }

}
