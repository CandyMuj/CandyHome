package cn.candy.utils.communication.mail;

import java.io.UnsupportedEncodingException;
import java.util.Properties;

import javax.mail.MessagingException;
import javax.mail.internet.MimeMessage;

import org.springframework.mail.javamail.JavaMailSenderImpl;
import org.springframework.mail.javamail.MimeMessageHelper;

import com.candy.commons.settings.DefaultSettings;

/**
 * 自定义的邮件发送程序
 * 未使用任何方提供的接口，直接用java内置的方法发送，若后续上线需要邮箱服务再做修改，目前仅用于项目连通开发测试
 * 原理：SMTP,与平常发送邮件一样，用我的邮箱给别人发邮件，只不过现在由java来发
 * 
 * @author jx003
 *
 */
public class CustomMailService {

	private static CustomMailService SINGLE_CASE = null;

	private CustomMailService() {
	}

	/**
	 * 单例模式
	 * 线程安全的
	 * @return
	 */
	public static synchronized CustomMailService getInstance() {
		if (SINGLE_CASE == null) {
			SINGLE_CASE = new CustomMailService();
		}
		return SINGLE_CASE;
	}

	/**
	 * 发送邮件
	 *
	 * @param toUser 接受人
	 * @param subject 主题
	 * @param html 发送内容
	 * @throws MessagingException 异常
	 * @throws UnsupportedEncodingException 异常
	 */
	public void sendHtmlMail(String toUser, String subject, String html) throws MessagingException, UnsupportedEncodingException {
		JavaMailSenderImpl mailSender = createMailSender();
		MimeMessage mimeMessage = mailSender.createMimeMessage();
		// 设置utf-8或GBK编码，否则邮件会有乱码
		MimeMessageHelper messageHelper = new MimeMessageHelper(mimeMessage, true, DefaultSettings.get("CustomMailService.encode"));
		messageHelper.setFrom(DefaultSettings.get("CustomMailService.mailFrom"), DefaultSettings.get("CustomMailService.personal"));
		messageHelper.setTo(toUser);
		messageHelper.setSubject(subject);
		messageHelper.setText(html, true);
		mailSender.send(mimeMessage);
	}

	/**
	 * 邮件发送器
	 * 
	 * @return 配置好的工具
	 */
	private JavaMailSenderImpl createMailSender() {
		JavaMailSenderImpl sender = new JavaMailSenderImpl();
		Properties p = new Properties();
		sender.setHost(DefaultSettings.get("CustomMailService.host"));
		sender.setPort(Integer.parseInt(DefaultSettings.get("CustomMailService.port")));
		sender.setUsername(DefaultSettings.get("CustomMailService.username"));
		sender.setPassword(DefaultSettings.get("CustomMailService.password"));
		sender.setDefaultEncoding(DefaultSettings.get("CustomMailService.encode"));
		p.setProperty("mail.smtp.timeout", DefaultSettings.get("CustomMailService.timeout"));
		p.setProperty("mail.smtp.auth", DefaultSettings.get("CustomMailService.auth"));
		sender.setJavaMailProperties(p);
		return sender;
	}

}
