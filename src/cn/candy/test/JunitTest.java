package cn.candy.test;

import org.junit.Test;

import com.candy.commons.settings.component.DefaultSettingsComponent;
import com.candy.commons.settings.component.iface.IDefaultSettingsComponent;

import cn.candy.utils.communication.email.CustomEmailService;

public class JunitTest {

	@Test
	public void test() throws Exception{
//		System.out.println(FileUtil.readFile("D:\\Eclipse\\Workspace\\CandyWorkspace-OXYGEN\\CandyHome\\WebContent\\front\\jsp\\info\\exceptionError.jsp"));
		
		
		
		CustomEmailService.getInstance().sendMail(new String[]{"candymuj@vip.qq.com"}, null, null, new String[]{"D:\\新建文件夹\\测试.txt,test1.txt"}, "测试", "冇问题啊");
		
	}
	
}
