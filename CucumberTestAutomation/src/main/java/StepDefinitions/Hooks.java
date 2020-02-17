package StepDefinitions;

import org.openqa.selenium.remote.service.DriverService;

import com.qa.Base.TestBase;
import com.relevantcodes.extentreports.ExtentReports;
import com.relevantcodes.extentreports.ExtentTest;

import cucumber.api.Scenario;
import cucumber.api.java.After;
import cucumber.api.java.Before;

public class Hooks extends TestBase{
	
	private	 static ExtentReports extentReports ;
	 private static  ExtentTest extentTestReport;
	 
	 private static boolean isReportRunning;
	 //private DriverService services;
	 
//	 Hooks(DriverService services)
//	 {
//		 this.services =services;
//	 }
	
	@Before
	public void setUp(Scenario scenario)
	{
		if(!isReportRunning)
		{
			extentReports =ExtentReportManager.Instance();
			 
			isReportRunning =true;
			
		}
		extentTestReport=  extentReports.startTest(scenario.getName(), "");
		System.out.println("in before method");
		intialization();
		 driver.get("https://ui.freecrm.com/");
		// extentReports.flush();
	}
	
	
	@After
	public void tearDown()
	{
		//extentReports.endTest(extentTestReport);
	   
	//	extentReports.close();
		driver.quit();
		System.out.println("in after method");
	}
	
/*	@Before
	public void beforeScenario(Scenario scenario)
	{
		System.out.println("in beforeScenario method");
		extentReports =ExtentReportManager.Instance();
		// ExtentTest extentTestReport;
		 extentTestReport=  extentReports.startTest(scenario.getName(), scenario.getName());
	}
	@Before
	public void endScenario()
	{
		
		System.out.println("in endScenario method");
//		extentReports.endTest(extentTestReport);
//	    extentReports.flush(); 
	   
	}*/
}
