package cn.candy.test;

import java.io.File;
import java.io.FileInputStream;
import java.util.Properties;

import org.junit.Test;

public class JunitTest {

	private static final Properties properties = new Properties();
	
	@Test
	public void test() {
		
		try {
			properties.load(new FileInputStream(new File("D:\\Eclipse\\Workspace\\CandyWorkspace-OXYGEN\\CandyHome\\config\\properties\\commons\\candy2.properties")));
		} catch (Exception e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		
	}
	
}
