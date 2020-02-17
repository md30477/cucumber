package com.qa.utils;

import java.io.File;
import java.text.SimpleDateFormat;
import java.util.Date;



/**
 * @author madhavi.dokiparthi
 *
 */
public class ReportsFolderPath {
	static PropertyFileReader propertyFileReader = new PropertyFileReader();
	
	  public static String createScreenShotDir(){ 
		 
		   String ImagesPath = propertyFileReader.getValue("REPORT_PATH");
	         String newScrnShotPath=ImagesPath+getTimeStamp();
	         CucumberTestAutomationLogger.writeToLog("newScrnShotPath", "CaptureScreen()", " method called");
	         boolean success = (new File(newScrnShotPath)).mkdir();      
	         if(success)
	       CucumberTestAutomationLogger.writeToLog("newScrnShotPath", "CaptureScreen()", " method called");
			return newScrnShotPath;
	   }
	  
	  public static String getTimeStamp(){
	         Date dateNow = new Date();
		//  SimpleDateFormat dateformatYYYYMMDD = new SimpleDateFormat("dd-MM-yyyy HH-mm-ss.SSS");
	         SimpleDateFormat dateformatYYYYMMDD = new SimpleDateFormat("yyyy-MMM-dd");	   
	       String timeStamp = new String(dateformatYYYYMMDD.format(dateNow));
	       return timeStamp;
	   }
	  
}
