package StepDefinitions;

import java.io.File;
import java.io.IOException;
import java.text.SimpleDateFormat;
import java.util.Date;

import org.apache.commons.io.FileUtils;
import org.openqa.selenium.OutputType;
import org.openqa.selenium.TakesScreenshot;
import org.openqa.selenium.WebDriver;

import com.qa.utils.CucumberTestAutomationLogger;
import com.qa.utils.ReportsFolderPath;
import com.relevantcodes.extentreports.ExtentReports;

/**
 * @author madhavi.dokiparthi
 *
 */
@SuppressWarnings({"deprecation"})
public class ExtentReportManager {
	 static ExtentReports extent;
	// String path = ReportsFolderPath.createScreenShotDir();
	 private static ExtentReportManager extentReportManager =null;
	// @SuppressWarnings("static-access")
	 ExtentReportManager() {
		 if(extentReportManager ==null)
		 {
			 extentReportManager = new ExtentReportManager();
			 extentReportManager.Instance();
		 }
	 }
	public static ExtentReports Instance()
	   {
		if(extent==null)
		{
			CucumberTestAutomationLogger.writeToLog("ExtentReportManager", "Instance()", " method called");
			 String path = ReportsFolderPath.createScreenShotDir(); 
		    // extent = new ExtentReports(path+"\\"+"AWCTestSuiteReport"+".html", false);
			String  reportName="Cucumber TestSuite Results"+getTimeStamp();
			 extent = new ExtentReports(path+"\\"+reportName+".html", false);
		     extent.config().documentTitle("Automation Report").reportName("CucumberTestSuiteResults");
		}
		

	  return extent;
	     }
	 
	 public static String CaptureScreen(WebDriver driver, String ImagesPath)
	 {
		// newScrnShotPath = createScreenShotDir(ImagesPath);
		 CucumberTestAutomationLogger.writeToLog("ExtentReportManager", "CaptureScreen()", " method called");
	     TakesScreenshot oScn = (TakesScreenshot) driver;
	     File oScnShot = oScn.getScreenshotAs(OutputType.FILE);
		  File oDest = new File(ImagesPath+".png"); //".jpg"); //.jpg commented as it is not displayed in IE
		  try {
		      FileUtils.copyFile(oScnShot, oDest);
		  } catch (IOException e) {
			  System.out.println(e.getMessage());}
		//   ImagesPath = ImagesPath.replace("C:"+ System.getProperty("file.separator")+"Selenium", ".");;
			
		  	return ImagesPath+".png";
		  }
	 
	 public static String CaptureScreenForExcel(WebDriver driver, String sheetname)
	 {
		// newScrnShotPath = createScreenShotDir(ImagesPath);
		 CucumberTestAutomationLogger.writeToLog("ExtentReportManager", "CaptureScreen()", " method called");
	     TakesScreenshot oScn = (TakesScreenshot) driver;
	     File oScnShot = oScn.getScreenshotAs(OutputType.FILE);
		  File oDest = new File(sheetname+".xls"); //".jpg"); //.jpg commented as it is not displayed in IE
		  try {
		       FileUtils.copyFile(oScnShot, oDest);
		  } catch (IOException e) {
			  System.out.println(e.getMessage());}
		  	return sheetname+".xls";
		  }
	 
	  public static String getTimeStamp(){
	         Date dateNow = new Date();
		  SimpleDateFormat dateformatYYYYMMDD = new SimpleDateFormat("dd-MM-yyyy HH-mm-ss.SSS");
	    //     SimpleDateFormat dateformatYYYYMMDD = new SimpleDateFormat("yyyy-MMM-dd");	   
	       String timeStamp = new String(dateformatYYYYMMDD.format(dateNow));
	       return timeStamp;
	   }
	  
	 
}
