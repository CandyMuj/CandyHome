package cn.candy.utils;

import java.io.BufferedReader;
import java.io.File;
import java.io.FileInputStream;
import java.io.FileReader;
import java.io.InputStream;
import java.io.OutputStream;

import javax.servlet.http.HttpServletResponse;

import org.apache.log4j.Logger;
import org.springframework.web.multipart.MultipartFile;

/**
 * 文件处理类
 * 	主要功能，上传，下载，读取
 * 
 * @author jx003
 *
 */
public class FileUtil {
	
	private static final Logger log = Logger.getLogger(FileUtil.class);
	
	
	/**
	 * 通过springmvc的特性，使用MultipartFile实现文件上传
	 * 可上传任意文件类型，若需要对文件类型进行限制，可在前台或业务层中进行控制
	 * 	控制方式包括但不限于：后缀，文件网络类型等
	 * 
	 * @param uploadFile   上传的文件对象 MultipartFile，使用springmvc的特性可直接进行参数绑定
	 * @param newFileName  上传的文件保存到磁盘中的文件名
	 * @param uploadPath   上传路径
	 * @return 成功：true<br/>失败：false
	 * 
	 */
	public static boolean uploadFileByMvc(MultipartFile uploadFile, String newFileName, String uploadPath) {
		
		boolean result = false;

		try {
			// 上传文件原始名称
			String originalFilename = uploadFile.getOriginalFilename();
			// 文件后缀
			String suffix = originalFilename.substring(originalFilename.lastIndexOf("."));
			// 新文件
			File file = new File(uploadPath + newFileName + suffix);

			// 将内存中的文件写入磁盘
			uploadFile.transferTo(file);
			result = true;
		} catch (Exception e) {
			log.error("--------- > 上传文件出错,发生异常");
			e.printStackTrace();
		}

		return result;
	}
	
	/**
	 * 一个下载文件的方法
	 * 
	 * @param response  传入一个response
	 * @param filePath  传入文件路径，进行下载
	 */
	public static void downloadFile(HttpServletResponse response, String filePath){
		
		log.info("------ > 下载文件 ："+filePath);
		
		OutputStream write = null;
		InputStream input = null;
		try {
			if(TextUtil.isNotNull(filePath)){
				//设置响应头
				response.setHeader("Cache-Control","no-cache");
				response.setHeader("Access-Control-Allow-Origin", "*");
				response.setContentType("application/octet-stream");
				response.setHeader("Content-Disposition", "attachment; filename=\""+java.net.URLEncoder.encode(filePath.substring(filePath.lastIndexOf("/")+1),"UTF-8")+"\"");

				write = response.getOutputStream();
				input = new FileInputStream(new File(filePath));
				byte[] buf = new byte[1024];
				int length = 0;
				log.info("------ > 开始下载...");
				while ((length = input.read(buf, 0, buf.length)) != -1) {
					write.write(buf, 0, length);
				}
				write.flush();
			}
		} catch (Exception e) {
			log.error("------ > 下载失败，发生异常");
			e.printStackTrace();
		} finally{
			try {
				if (write != null) write.close();
				if (input != null) input.close();
			} catch (Exception e) {}
		}
	}
	
	/**
	 * 通过文件路径获取文件内容
	 * 
	 * @param filePath 传入文件路径，进行读取
	 * @return 一个线程安全的StringBuffer
	 */
	public static StringBuffer readFile(String filePath) {
		
		StringBuffer sbuff = new StringBuffer();
		
		FileReader fileReader = null;
		BufferedReader bufferedReader = null;
		try {
			if (TextUtil.isNotNull(filePath)) {
				fileReader = new FileReader(new File(filePath));
				bufferedReader = new BufferedReader(fileReader);
				
				log.info("按行读取文件的内容----------------");
				String str = null;
				while ((str = bufferedReader.readLine()) != null) {
					sbuff.append(str).append("\n");
					// System.out.println(str);
					// log.debug(str);
				}
			}
		} catch (Exception e) {
			log.error("------ > 读取文件失败，发生异常");
			sbuff.setLength(0);
			e.printStackTrace();
		} finally{
			try {
				if (bufferedReader != null) bufferedReader.close();
				if (fileReader != null) fileReader.close();
			} catch (Exception e) {}
		}
		
		return sbuff;
	}

}
