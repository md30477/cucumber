package com.qa.utils;

import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.IOException;
import java.util.Properties;

/**
 * @author madhavi.dokiparthi
 *
 */
public class PropertyFileReader {
	
	Properties props = null;
	//String path = ".\\src\\com\\accenture\\npi\\properties\\application.properties";
	//String path = "C:\\Selenium\\properties\\application.properties";
	//String path = ".\\src\\main\\java\\com\\qa\\properties\\application.properties";
	String path = System.getProperty("user.dir")+"/src/main/java/com/qa/config/config.properties";
	public PropertyFileReader(){
		
		try
		{
			System.out.println("path is here --------------->"+path);
			FileInputStream fis = new FileInputStream(path);
			props = new Properties(); 
			props.load(fis); 
			System.out.println("madvi im here --------------->");
			fis.close();
		}
		catch(Exception ex)
		{
			ex.printStackTrace();
		}
	}
	
	public String getValue(String key){
		String value = props.getProperty(key);		
		if(value!=null){
			return value;
		}
		else{
			return null;	
		}
	}
	
	public void setValue(String key,String value){
	
		try {
			
			props.setProperty(key, value);
			props.store(new FileOutputStream(""),null);
			
		} catch (FileNotFoundException e) {
			e.printStackTrace();
		} catch (IOException e) {
			e.printStackTrace();
		}
        
	}
	public void removeKey(String keyName) {
		props.remove(keyName);
		
	}

}
