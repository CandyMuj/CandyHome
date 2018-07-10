package cn.candy.utils.communication.email;

import java.io.UnsupportedEncodingException;
import java.util.Date;
import java.util.Properties;

import javax.activation.DataHandler;
import javax.activation.FileDataSource;
import javax.mail.Authenticator;
import javax.mail.BodyPart;
import javax.mail.Message.RecipientType;
import javax.mail.MessagingException;
import javax.mail.Multipart;
import javax.mail.PasswordAuthentication;
import javax.mail.Session;
import javax.mail.Transport;
import javax.mail.internet.InternetAddress;
import javax.mail.internet.MimeBodyPart;
import javax.mail.internet.MimeMessage;
import javax.mail.internet.MimeMultipart;
import javax.mail.internet.MimeUtility;

import com.candy.commons.settings.component.DefaultSettingsComponent;
import com.candy.commons.settings.component.iface.IDefaultSettingsComponent;

/**
 * 发送邮件的测试程序(适用qq邮箱)
 * 通过本人的qq邮箱: xxx@qq.com 发送邮件
 * 
 * 此类用于测试的，因为那个需要启动服务器才行，因为defaultSetting需要spring初始化我没有用静态代码块的方式初始化
 * 
 * @author jx003
 * 
 */
public class EmailTest {

	private static IDefaultSettingsComponent defaultSettings = new DefaultSettingsComponent();

	// 发送的邮箱 内部代码只适用qq邮箱
	private static final String USER = defaultSettings.get("CustomMailService.USER");
	// 授权密码 通过QQ邮箱设置->账户->POP3/IMAP/SMTP/Exchange/CardDAV/CalDAV服务->开启POP3/SMTP服务获取
	private static final String PWD = defaultSettings.get("CustomMailService.PWD");

	/**
	 * 发送邮件
	 * 支持所有邮件地址，只是发送人邮箱只支持qq，如果需要更改，需要修改服务器及端口等信息
	 * <br/>所有格式都用英文逗号","分隔
	 * 
	 * @param to		(必填)接收人，支持批量 格式：new String[] {"xxx@qq.com","xxx@qq.com"}
	 * @param cc		(可选)抄送，支持批量 格式：new String[] {"xxx@qq.com","xxx@qq.com"}
	 * @param bcc		(可选)密送，支持批量 格式：new String[] {"xxx@qq.com","xxx@qq.com"}
	 * @param fileList	(可选)附件，支持批量 格式：new String[] {"file\\附件1.txt","file\\附件2.txt"}<br/>支持附件重命名(可选) 格式：new String[] {"file\\附件1.txt,text1.txt","file\\附件2.txt,text2.txt"}
	 * @param subject	(必填)主题
	 * @param content	(必填-可选)内容，可以用html语言写 （因为发空邮件也没有错，但是没有意义）
	 * @throws MessagingException
	 * @throws UnsupportedEncodingException
	 */
	@SuppressWarnings("static-access")
	public void sendMail(String[] to, String[] cc, String[] bcc, String[] fileList, String subject, String content)
			throws MessagingException, UnsupportedEncodingException {
		// 配置发送邮件的环境属性
		final Properties props = new Properties();
		// 下面两段代码是设置ssl和端口，不设置发送不出去。
		props.setProperty("mail.smtp.socketFactory.class", "javax.net.ssl.SSLSocketFactory");
		// props.setProperty("mail.smtp.port", "465");
		props.setProperty("mail.smtp.socketFactory.port", defaultSettings.get("CustomMailService.port"));
		// 表示SMTP发送邮件，需要进行身份验证
		props.setProperty("mail.transport.protocol", "smtp");// 设置传输协议
		props.put("mail.smtp.timeout", defaultSettings.get("CustomMailService.timeout"));
		props.put("mail.smtp.auth", "true");
		props.put("mail.smtp.host", defaultSettings.get("CustomMailService.host"));// QQ邮箱的服务器 如果是企业邮箱或者其他邮箱得更换该服务器地址
		// 发件人的账号
		props.put("mail.user", USER);
		// 访问SMTP服务时需要提供的密码
		props.put("mail.password", PWD);

		// 构建授权信息，用于进行SMTP进行身份验证
		Authenticator authenticator = new Authenticator() {
			@Override
			protected PasswordAuthentication getPasswordAuthentication() {
				// 用户名、密码
				String userName = props.getProperty("mail.user");
				String password = props.getProperty("mail.password");
				return new PasswordAuthentication(userName, password);
			}
		};
		// 使用环境属性和授权信息，创建邮件会话
		Session mailSession = Session.getInstance(props, authenticator);
		// 开启Session的debug模式，这样就可以查看到程序发送Email的运行状态
		mailSession.setDebug(Boolean.parseBoolean(defaultSettings.get("CustomMailService.session.debug")));
		// 创建邮件消息
		MimeMessage message = new MimeMessage(mailSession);
		BodyPart messageBodyPart = new MimeBodyPart();
		Multipart multipart = new MimeMultipart();
		// 设置发件人
		String setForm = defaultSettings.get("CustomMailService.mailFrom") + "<" + props.getProperty("mail.user") + ">";
		// 如果不一样会报错   com.sun.mail.smtp.SMTPSenderFailedException: 501 mail from address must be same as authorization user
		// String setForm = defaultSettings.get("CustomMailService.mailFrom") + "<766557580@qq.com>";
		InternetAddress form = new InternetAddress(setForm);
		message.setFrom(form);
		// 发送
		if (to != null) {
			String toList = getMailList(to);
			InternetAddress[] iaToList = new InternetAddress().parse(toList);
			message.setRecipients(RecipientType.TO, iaToList); // 收件人
		}
		// 抄送
		if (cc != null) {
			String toListcc = getMailList(cc);
			InternetAddress[] iaToListcc = new InternetAddress().parse(toListcc);
			message.setRecipients(RecipientType.CC, iaToListcc); // 抄送人
		}
		// 密送
		if (bcc != null) {
			String toListbcc = getMailList(bcc);
			InternetAddress[] iaToListbcc = new InternetAddress().parse(toListbcc);
			message.setRecipients(RecipientType.BCC, iaToListbcc); // 密送人
		}
		message.setSentDate(new Date()); // 发送日期 该日期可以随意写,你可以写上昨天的日期（效果很特别,亲测,有兴趣可以试试）,或者抽象出来形成一个参数。
		message.setSubject(subject); // 主题
		message.setText(content); // 内容
		// 显示以html格式的文本内容
		messageBodyPart.setContent(content, "text/html;charset=utf-8");
		multipart.addBodyPart(messageBodyPart);
		// 保存多个附件
		if (fileList != null) {
			addTach(fileList, multipart);
		}
		message.setContent(multipart);
		// 发送邮件
		Transport.send(message);
	}

	/**
	 * 
	 * 
	 * @param mailArray
	 * @return
	 */
	private String getMailList(String[] mailArray) {
		StringBuffer toList = new StringBuffer();
		int length = mailArray.length;
		if (mailArray != null && length < 2) {
			toList.append(mailArray[0]);
		} else {
			for (int i = 0; i < length; i++) {
				toList.append(mailArray[i]);
				if (i != (length - 1)) {
					toList.append(",");
				}
			}
		}
		return toList.toString();
	}

	/**
	 * 添加多个附件
	 * 
	 * @param fileList
	 * @param multipart
	 * @throws MessagingException
	 * @throws UnsupportedEncodingException
	 */
	private void addTach(String[] fileList, Multipart multipart)
			throws MessagingException, UnsupportedEncodingException {
		for (int index = 0; index < fileList.length; index++) {
			MimeBodyPart mailArchieve = new MimeBodyPart();
			String[] file = fileList[index].split(",");
			String filePath = file[0];
			FileDataSource fds = new FileDataSource(filePath);
			String displayname = fds.getName();
			if (file.length == 2) {
				displayname = file[1];
			}
			mailArchieve.setDataHandler(new DataHandler(fds));
			mailArchieve.setFileName(
					MimeUtility.encodeText(displayname, defaultSettings.get("CustomMailService.encode"), "B"));
			multipart.addBodyPart(mailArchieve);
		}
	}

	// 以下是演示demo
	public static void main(String args[]) {
		EmailTest mail = new EmailTest();
		String subject = "测试一下";
		String content = "这个是ssssss";
		// 收件人 可以发给其他邮箱(163等) 下同
		String[] to = new String[] { "candymuj@vip.qq.com" };
		// 抄送
		// mail.setCc(new String[] {"xxx@qq.com","xxx@qq.com"});
		// //密送
		// mail.setBcc(new String[] {"xxx@qq.com","xxx@qq.com"});
		// 发送附件列表 可以写绝对路径 也可以写相对路径(起点是项目根目录)
		String[] fileList = new String[] { "D:\\新建文件夹\\测试.txt,test1.txt" };
		// 发送邮件
		try {
			mail.sendMail(to, null, null, fileList, subject, content);
			System.out.println("发送邮件成功！");
		} catch (Exception e) {
			System.out.println("发送邮件失败！");
			e.printStackTrace();
		}
	}
}
