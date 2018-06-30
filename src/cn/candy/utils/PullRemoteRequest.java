package cn.candy.utils;

import java.io.IOException;
import java.io.UnsupportedEncodingException;
import java.net.MalformedURLException;
import java.net.URL;
import java.util.ArrayList;
import java.util.List;
import java.util.Map;

import javax.xml.rpc.ParameterMode;

import org.apache.axis.client.Call;
import org.apache.axis.client.Service;
import org.apache.axis.encoding.XMLType;
import org.apache.commons.httpclient.HttpClient;
import org.apache.commons.httpclient.HttpStatus;
import org.apache.commons.httpclient.methods.PostMethod;
import org.apache.commons.httpclient.methods.StringRequestEntity;
import org.apache.http.HttpEntity;
import org.apache.http.NameValuePair;
import org.apache.http.client.entity.UrlEncodedFormEntity;
import org.apache.http.client.methods.CloseableHttpResponse;
import org.apache.http.client.methods.HttpGet;
import org.apache.http.client.methods.HttpPost;
import org.apache.http.impl.client.CloseableHttpClient;
import org.apache.http.impl.client.HttpClients;
import org.apache.http.message.BasicNameValuePair;
import org.apache.http.util.EntityUtils;
import org.apache.log4j.Logger;
import org.codehaus.xfire.client.Client;

/**
 * @author jx003
 * 
 * 采用单例模式，线程安全的
 * 
 * 此类实现远程接口调用(支持URL跨域请求)
 * 	webservice接口
 * 	HTTP POST接口
 * 	HTTP GET接口
 */
public class PullRemoteRequest {
	private static final Logger log = Logger.getLogger(PullRemoteRequest.class);
	private static PullRemoteRequest PULL_REMOTE_REQUEST = null;

	// 私有化构造方法
	private PullRemoteRequest() {
	};

	/**
	 * 单例实现
	 * @return
	 */
	public static synchronized PullRemoteRequest getInstance() {
		if (null == PULL_REMOTE_REQUEST) {
			PULL_REMOTE_REQUEST = new PullRemoteRequest();
		}
		return PULL_REMOTE_REQUEST;
	}

	/*
	 * 下面方法开始 ================================
	 */
	/**
	 * 2.0 (推荐优先使用1.0)
	 * 重载写另一种调用Web Service的方式
	 * 实现功能：传递对应的参数信息，通过使用Axis进行指定接口方法调用，并返回调用结果
	 * 
	 * @param address	 Web Service接口地址 需要去掉?wsdl
	 * @param methodName 接口方法名
	 * @param paramNames 接口参数名
	 * @param params	 接口参数值
	 * @throws Exception
	 * @return 接口调用结果
	 */
	public String pullWebServiceRequest(String address, String methodName, String[] paramNames, Object... params)
			throws Exception {
		String responseContent = null;
		try {
			log.info("2.0 调用Web Service接口...... " + address + "\n---> " + methodName + "\n---> " + paramNames
					+ "\n---> " + params);
			Service s = new Service();
			Call call = (Call) s.createCall();
			// 调用的方法名
			call.setOperationName(methodName);
			// 设置调用的目标url
			call.setTargetEndpointAddress(address);
			// 指定输入参数类型
			for (String paramName : paramNames) {
				call.addParameter(paramName, XMLType.XSD_STRING, ParameterMode.IN);
			}
			// 指定返回类型
			call.setReturnType(XMLType.XSD_STRING);
			// 输入参数并调用接口方法,设置调用方法中的参数
			responseContent = (String) call.invoke(params);
			// log.debug("Invoke The Web Service[" + url + "," + methodName + "] result:" +
			// result);
		} catch (Exception e) {
			log.error(e.getMessage());
			e.printStackTrace();
			throw new Exception(e.getMessage());
		}
		return responseContent;
	}

	/**
	 * 1.0 (推荐优先使用)
	 * 调用Web Service 接口,第一种调用方式
	 * 
	 * @param address	webservice的地址，地址必须加上'?wsdl'后缀
	 * @param method	调用的方法名
	 * @param params	参数数组，按照接口定义的参数顺序依次添加
	 * @return Object[] 对象数组，一般都是取obj[0]
	 */
	public Object[] pullWebServiceRequest(String address, String method, String[] params) {
		Object[] obj = null;
		try {
			log.info("1.0 调用Web Service接口...... " + address + "\n---> " + method + "\n---> " + params);
			Client client = new Client(new URL(address));
			try {
				obj = (client).invoke(method, params);
			} finally {
				client.close();
			}
		} catch (MalformedURLException e) {
			e.printStackTrace();
		} catch (Exception e) {
			e.printStackTrace();
		}
		return obj;
	}

	/**
	 * 通过GET方式发起http请求(支持URL跨域请求)
	 * 
	 * @param reqURL	url
	 * @param decodeCharset	解码字符集 传入null，默认为UTF-8
	 * @return
	 */
	public String pullGetRequest(String reqURL, String decodeCharset) {
		String responseContent = null;
		// 创建默认的httpClient实例
		CloseableHttpClient httpClient = getHttpClient();
		try {
			// 用get方法发送http请求
			HttpGet get = new HttpGet(reqURL);
			log.info("执行GET请求...... " + get.getURI());
			CloseableHttpResponse httpResponse = null;
			// 发送get请求
			httpResponse = httpClient.execute(get);
			// 设置响应头，可实现跨域请求
			httpResponse.setHeader("Access-Control-Allow-Origin", "*");
			httpResponse.setHeader("Access-Control-Allow-Methods", "POST, GET, OPTIONS, DELETE");
			httpResponse.setHeader("Access-Control-Max-Age", "3600");
			httpResponse.setHeader("Access-Control-Allow-Headers", "x-requested-with");
			try {
				// response实体
				HttpEntity entity = httpResponse.getEntity();
				if (null != entity) {
					log.info("响应状态码: " + httpResponse.getStatusLine());
					log.info("-------------------------------------------------");
					responseContent = EntityUtils.toString(entity, decodeCharset == null ? "UTF-8" : decodeCharset);
					// TODO 注意下面调用的这个方法，不知道干什么的，网上没有调，如果有错就注释了
					EntityUtils.consume(entity);
					log.info("响应内容: " + responseContent);
					log.info("-------------------------------------------------");
				}
			} finally {
				httpResponse.close();
			}
		} catch (Exception e) {
			e.printStackTrace();
		} finally {
			try {
				closeHttpClient(httpClient);
			} catch (IOException e) {
				e.printStackTrace();
			}
		}
		return responseContent;
	}

	/**
	 * POST方式发起http请求(支持URL跨域请求)
	 * 
	 * @param reqURL	url
	 * @param params	参数
	 * @param encodeCharset	编码字符集格式 传入null，默认为UTF-8
	 * @param decodeCharset	解码字符集格式 传入null，默认为UTF-8
	 * @return
	 */
	public String pullPostRequest(String reqURL, Map<String, String> params, String encodeCharset,
			String decodeCharset) {
		String responseContent = null;
		CloseableHttpClient httpClient = getHttpClient();
		try {
			HttpPost httpPost = new HttpPost(reqURL);
			List<NameValuePair> formParams = new ArrayList<NameValuePair>(); // 创建参数队列,创建参数列表
			for (Map.Entry<String, String> entry : params.entrySet()) {
				formParams.add(new BasicNameValuePair(entry.getKey(), entry.getValue()));
			}
			// url格式编码
			UrlEncodedFormEntity uefEntity = new UrlEncodedFormEntity(formParams,encodeCharset == null ? "UTF-8" : encodeCharset);
			httpPost.setEntity(uefEntity);
			log.info("POST 请求...... " + httpPost.getURI());
			// 执行请求
			CloseableHttpResponse httpResponse = httpClient.execute(httpPost);
			// 设置响应头，可实现跨域请求
			httpResponse.setHeader("Access-Control-Allow-Origin", "*");
			httpResponse.setHeader("Access-Control-Allow-Methods", "POST, GET, OPTIONS, DELETE");
			httpResponse.setHeader("Access-Control-Max-Age", "3600");
			httpResponse.setHeader("Access-Control-Allow-Headers", "x-requested-with");
			try {
				HttpEntity entity = httpResponse.getEntity();
				if (null != entity) {
					log.info("-------------------------------------------------------");
					responseContent = EntityUtils.toString(entity, decodeCharset == null ? "UTF-8" : decodeCharset);
					EntityUtils.consume(entity);
					log.info("响应内容: " + responseContent);
					log.info("-------------------------------------------------------");
				}
			} finally {
				httpResponse.close();
			}
		} catch (UnsupportedEncodingException e) {
			e.printStackTrace();
		} catch (IOException e) {
			e.printStackTrace();
		} finally {
			try {
				closeHttpClient(httpClient);
			} catch (Exception e) {
				e.printStackTrace();
			}
		}
		return responseContent;
	}

	/**
	 * 通过json调用POST请求(不支持URL跨域请求)
	 * 
	 * @param url
	 * @param json
	 * @return
	 * @throws Exception
	 */
	public String doPost(String url, String json) throws Exception {
		log.info("通过json调用POST请求..... " + url + "  json: " + json);
		String result = null;
		HttpClient httpClient = new HttpClient();
		PostMethod postMethod = new PostMethod(url);
		try {
			StringRequestEntity requestEntity = new StringRequestEntity(json, "application/json", "UTF-8");
			postMethod.setRequestEntity(requestEntity);
			/* 发送请求，并获取响应对象 */
			int statusCode = httpClient.executeMethod(postMethod);
			if (statusCode == HttpStatus.SC_OK) {
				result = postMethod.getResponseBodyAsString();
			} else {
				System.out.println("Method failed: " + postMethod.getStatusLine());
			}
		} catch (Exception e) {
			e.printStackTrace();
		} finally {
			postMethod.releaseConnection();
		}
		log.info("响应内容: " + result);
		return result;
	}

	private CloseableHttpClient getHttpClient() {
		return HttpClients.createDefault();
	}

	private void closeHttpClient(CloseableHttpClient client) throws IOException {
		if (client != null) {
			client.close();
		}
	}
}
