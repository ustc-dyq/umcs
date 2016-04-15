/**
 * @Title：FilesUtil.java 
 * @Copyright © Suzhou Keda Technology Co.Ltd. All Rights Reserved. 
 * @author： 代永强
 * @date: 2016年3月30日
 * @description:
**/
package com.keda.webDemo.umcs.tools;

import java.io.File;
import java.io.IOException;
import java.io.InputStream;
import java.io.OutputStream;

import org.springframework.web.multipart.MultipartFile;

public class FilesUtil {

	public static void uploadFile(MultipartFile file, String newFile) throws IOException {
		
		File tempFile = new File(newFile);		
		if (!tempFile.exists()) {
			tempFile.createNewFile();
		}
		file.transferTo(tempFile);
	}
	
	public static void read(InputStream inputStream, OutputStream outputStream) throws IOException {
		
		byte[] b = new byte[2048];
        int length;
        
        while ((length = inputStream.read(b)) > 0) {
        	outputStream.write(b, 0, length);
        }
        
        outputStream.close();
        inputStream.close();
        
	}
	
	public static void deleteFile(String filePath) {
		File file = new File(filePath);  
	    // 路径为文件且不为空则进行删除  
	    if (file.isFile() && file.exists()) {  
	        file.delete();
	    }  
	}
	
}





