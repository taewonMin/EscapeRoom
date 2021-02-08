package common.util;

import java.io.File;
import java.io.IOException;
import java.io.Reader;
import java.util.Properties;

import com.ibatis.common.resources.Resources;


public class GetUploadPath {
	private static Properties properties = new Properties();
	static{
		String resource = "properties/uploadPath.properties";		
		try {
			Reader reader = Resources.getResourceAsReader(resource);
			properties.load(reader);				
		
		}catch(IOException e) {
			e.printStackTrace();
		}
	}
	
	public static String getUploadPath(String key) {
		String uploadPath=null;
		uploadPath=properties.getProperty(key);
		uploadPath=uploadPath.replace("/",File.separator);
		return uploadPath;
	}
}
