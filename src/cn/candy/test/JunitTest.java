package cn.candy.test;

import org.junit.Test;

import cn.candy.utils.FileUtil;

public class JunitTest {

	@Test
	public void test() throws Exception{
		System.out.println(FileUtil.readFile("D:\\Eclipse\\Workspace\\CandyWorkspace-OXYGEN\\CandyHome\\WebContent\\front\\jsp\\info\\exceptionError.jsp"));
		
	}
	
}
