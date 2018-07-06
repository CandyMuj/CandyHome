package cn.candy.utils.communication.mail;

import java.util.Date;
import java.util.Properties;

import javax.activation.DataHandler;
import javax.activation.FileDataSource;
import javax.mail.Authenticator;
import javax.mail.Multipart;
import javax.mail.PasswordAuthentication;
import javax.mail.internet.InternetAddress;
import javax.mail.internet.MimeBodyPart;
import javax.mail.internet.MimeMultipart;
import javax.mail.internet.MimeUtility;

/** 
 * 利用java.mail的邮件发送程序 
 * 
 * 参考：http://coderdream.iteye.com/blog/1130464
 * 
 */
public class SendMailTest {
	
	public static void main(String[] args) {
		String title = "测试一下";// 所发送邮件的标题
		String from = "candyhome.cc@qq.com";// 从那里发送
		String sendTo[] = { "766557580@qq.com"};// 发送到那里
		// 邮件的文本内容，可以包含html标记则显示为html页面
		String content = "mail test!!!!!!<br><a href=#>aaa</a>";
		// 所包含的附件，及附件的重新命名
		String fileNames[] = { "D:\\新建文件夹\\测试.html,text1.txt"};
		try {
			sendmail(title, from, sendTo, content, fileNames, "text/html;charset=gb2312");
		} catch (Exception ex) {
			ex.printStackTrace();
		}
	}

	@SuppressWarnings("static-access")
	public static void sendmail(String subject, String from, String[] to, String text, String[] filenames, String mimeType) throws Exception {
		
		// 可以从配置文件读取相应的参数
		Properties props = new Properties();

		String smtp = "smtp.qq.com"; // 设置发送邮件所用到的smtp
		String servername = "candyhome.cc@qq.com";
		String serverpaswd = "bkpudxbwbsaedjgg";

		javax.mail.Session mailSession; // 邮件会话对象
		javax.mail.internet.MimeMessage mimeMsg; // MIME邮件对象

		props = java.lang.System.getProperties(); // 获得系统属性对象
		props.put("mail.smtp.host", smtp); // 设置SMTP主机
		props.put("mail.smtp.auth", "true"); // 是否到服务器用户名和密码验证
		// 到服务器验证发送的用户名和密码是否正确
		Email_Autherticatorbean myEmailAuther = new Email_Autherticatorbean(servername, serverpaswd);
		// 设置邮件会话
		mailSession = javax.mail.Session.getInstance(props, (Authenticator) myEmailAuther);
		// 设置传输协议
		javax.mail.Transport transport = mailSession.getTransport("smtp");
		// 设置from、to等信息
		mimeMsg = new javax.mail.internet.MimeMessage(mailSession);
		if (from != null && !from.equals("")) {
			InternetAddress sentFrom = new InternetAddress(from);
			mimeMsg.setFrom(sentFrom); // 设置发送人地址
		}

		// 收件人
		InternetAddress[] sendTo = new InternetAddress[to.length];
		for (int i = 0; i < to.length; i++) {
			System.out.println("发送到:" + to[i]);
			sendTo[i] = new InternetAddress(to[i]);
		}

		// 抄送
		InternetAddress[] sendToCC = new InternetAddress[2];
		sendToCC[0] = new InternetAddress("username@hotmail.com");
		sendToCC[1] = new InternetAddress("username@gmail.com");

		// 密送
		InternetAddress[] sendToBCC = new InternetAddress[2];
		sendToBCC[0] = new InternetAddress("username@126.com");
		sendToBCC[1] = new InternetAddress("username@gmail.com");

		mimeMsg.setRecipients(javax.mail.internet.MimeMessage.RecipientType.TO, sendTo);
		mimeMsg.setRecipients(javax.mail.internet.MimeMessage.RecipientType.CC, sendToCC);
		mimeMsg.setRecipients(javax.mail.internet.MimeMessage.RecipientType.BCC, sendToBCC);

		mimeMsg.setSubject(subject, "gb2312");

		MimeBodyPart messageBodyPart1 = new MimeBodyPart();
		messageBodyPart1.setContent(text, mimeType);

		Multipart multipart = new MimeMultipart();// 附件传输格式
		multipart.addBodyPart(messageBodyPart1);

		for (int i = 0; i < filenames.length; i++) {
			MimeBodyPart messageBodyPart2 = new MimeBodyPart();
			// 选择出每一个附件名
			String filename = filenames[i].split(",")[0];
			System.out.println("附件名：" + filename);
			String displayname = filenames[i].split(",")[1];
			// 得到数据源
			FileDataSource fds = new FileDataSource(filename);
			// 得到附件本身并设置到BodyPart
			messageBodyPart2.setDataHandler(new DataHandler(fds));
			// 得到文件名同样设置到BodyPart
			messageBodyPart2.setFileName(MimeUtility.encodeText(displayname));
			multipart.addBodyPart(messageBodyPart2);
		}
		mimeMsg.setContent(multipart);
		// 设置信件头的发送日期
		mimeMsg.setSentDate(new Date());
		mimeMsg.saveChanges();

		// 发送邮件
		transport.send(mimeMsg);
		transport.close();
	}

}

/** 
 * 验证类（内部类） 
 * 
 * @author Administrator 
 * 
 */
class Email_Autherticatorbean extends Authenticator {
	private String m_username = null;
	private String m_userpass = null;

	public void setUsername(String username) {
		m_username = username;
	}

	public void setUserpass(String userpass) {
		m_userpass = userpass;
	}

	public Email_Autherticatorbean(String username, String userpass) {
		super();
		setUsername(username);
		setUserpass(userpass);
	}

	public PasswordAuthentication getPasswordAuthentication() {
		return new PasswordAuthentication(m_username, m_userpass);
	}
}