package StepDefinitions;

import java.io.File;
import java.io.IOException;
import java.nio.file.Files;
import java.text.SimpleDateFormat;
import java.util.Date;

import org.apache.commons.io.FileUtils;
import org.openqa.selenium.OutputType;
import org.openqa.selenium.TakesScreenshot;
import org.openqa.selenium.WebDriver;

import com.qa.utils.CucumberTestAutomationLogger;
import com.qa.utils.ReportsFolderPath;
import com.relevantcodes.extentreports.ExtentReports;

import com.qa.Base.BaseUtil;

/**
 * @author madhavi.dokiparthi
 *
 */
@SuppressWarnings({"deprecation"})
public class ExtentReportUtil extends BaseUtil {
	 static ExtentReports extents;
	// String path = ReportsFolderPath.createScreenShotDir();
	 private static ExtentReportUtil extentReportManager =null;
	// @SuppressWarnings("static-access")
	 public ExtentReportUtil() {
		 if(extentReportManager ==null)
		 {
			 extentReportManager = new ExtentReportUtil();
			 extentReportManager.Instance();
		 }
	 }
	public static ExtentReports Instance()
	   {
		if(extents==null)
		{
			CucumberTestAutomationLogger.writeToLog("ExtentReportManager", "Instance()", " method called");
			 String path = ReportsFolderPath.createScreenShotDir(); 
		    // extent = new ExtentReports(path+"\\"+"AWCTestSuiteReport"+".html", false);
			String  reportName="Cucumber TestSuite Results"+getTimeStamp();
			extents = new ExtentReports(path+"\\"+reportName+".html", false);
			extents.config().documentTitle("Automation Report").reportName("CucumberTestSuiteResults");
		}
		

	  return extents;
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
	  public void ExtentReportScreenshot() throws IOException {

	        File scr = ((TakesScreenshot)driver).getScreenshotAs(OutputType.FILE);
	        Files.copy(scr.toPath(), new File(reportLocation + "screenshot.png").toPath());
	        scenarioDef.fail("details").addScreenCaptureFromPath(reportLocation + "screenshot.png");
	    }
	  public void FlushReport(){
	        extent.flush();
	    }
	 
}
