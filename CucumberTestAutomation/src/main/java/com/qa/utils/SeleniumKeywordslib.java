package com.qa.utils;

import java.awt.Robot;
import java.awt.event.InputEvent;
import java.io.File;
import java.security.Key;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.Calendar;
import java.util.Date;
import java.util.HashMap;
import java.util.Iterator;
import java.util.LinkedList;
import java.util.List;
import java.util.Map;
import java.util.Set;
import java.util.concurrent.TimeUnit;
import java.util.regex.Matcher;


import org.apache.commons.io.FileUtils;
import org.openqa.selenium.Alert;
import org.openqa.selenium.By;
import org.openqa.selenium.Dimension;
import org.openqa.selenium.JavascriptExecutor;
import org.openqa.selenium.Keys;
import org.openqa.selenium.NoSuchElementException;
import org.openqa.selenium.OutputType;
import org.openqa.selenium.Point;
import org.openqa.selenium.TakesScreenshot;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebDriver.Navigation;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.interactions.Actions;

import org.openqa.selenium.support.ui.ExpectedCondition;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.Select;
import org.openqa.selenium.support.ui.WebDriverWait;
import java.util.regex.Pattern;

import com.relevantcodes.extentreports.ExtentReports;
import com.relevantcodes.extentreports.ExtentTest;
import com.relevantcodes.extentreports.LogStatus;

import StepDefinitions.ExtentReportManager;

import javax.crypto.Cipher;
import javax.crypto.spec.SecretKeySpec;
/**
 * Class is used to perform webactions using selenium
 *
 * @author madhavi.dokiparthi
 */

public class SeleniumKeywordslib extends Hooks {
PropertyFileReader propertyFileReader = new PropertyFileReader(); 


/* * ############################################################################################# 
 *  * ' FUNCTION NAME - enterText ' DESCRIPTION - This
 * function will be used to Enter Text in a textbox ' INPUT PARAMETERS -
 * '################################################################################################
 */	
public String enterText(WebDriver webDriver,WebElement objName,String inputValue ,ExtentReports extentReports,ExtentTest extentTestReport){
		
		CucumberTestAutomationLogger.writeToLog("Input","input()"," method called" );
		String status="false";
	    try {
	   WebElement txtField = objName;
			 
		     if (txtField.isDisplayed()) {
		        	
					txtField.clear();
					txtField.click();
					String initText = txtField.getAttribute("value");
					
					if (initText != null && initText.length() > 0) {
						txtField.sendKeys(Keys.END, Keys.SHIFT, Keys.HOME,Keys.DELETE);
						
						for (int i = 1; i <= initText.length(); i++)
							txtField.sendKeys(Keys.DELETE);
						CucumberTestAutomationLogger.writeToLog("Input",	"input() ",	"after deleting the text");
					}
					txtField.sendKeys(inputValue);
					status="true";
					if(txtField.getAttribute("type").equalsIgnoreCase("password"))
					{
						 String key = "****************"; // 128 bit key

					     Key aesKey = new SecretKeySpec(key.getBytes(), "AES");
					     Cipher cipher = Cipher.getInstance("AES");

					     cipher.init(Cipher.ENCRYPT_MODE, aesKey);
					     byte[] encrypted = cipher.doFinal(inputValue.getBytes());
					       extentTestReport.log(LogStatus.PASS, " Enter " +objName +":  " +encrypted);
							CucumberTestAutomationLogger.writeToLog(objName+ " edit box successfully accepted the value ");
					     
					}
					else
					{
						extentTestReport.log(LogStatus.PASS, " Enter " +objName +":  " +inputValue);
						CucumberTestAutomationLogger.writeToLog(objName+ " edit box successfully accepted the value ");
					}
					
					
				}
			
			
		} catch (Exception e) {
			try{
				e.printStackTrace();
				String err[]=e.getMessage().split("\n");
				CucumberTestAutomationLogger.writeToLog(objName+ " unable to enter value in textbox "+e.getMessage().split("\n"));
				status =  "Exception " +err[0].replaceAll("'", "") + " Occurred";
			}
			finally{
				extentTestReport.log(LogStatus.FAIL, "Unable to enter "+objName +" due to Invalid Xpath");
		        extentTestReport.log(LogStatus.FAIL, extentTestReport.addScreenCapture(ExtentReportManager.CaptureScreen(webDriver, propertyFileReader.getValue("SNAPSHOT_FOLDER")+objName+": " +inputValue))); 
		    	// extentReports.endTest(extentTestReport);
		    	// extentReports.flush();
		         //extentReports.close(); 
		        webDriver.close();
		         //webDriver.quit();
		         
			}
	
	}
	    return status;
}
/* * ############################################################################################# 
 *  * ' FUNCTION NAME - enterText ' DESCRIPTION - This
 * function will be used to Enter Text in a textbox ' INPUT PARAMETERS -
 * '################################################################################################
 */	
public String enterTextPCS(WebDriver webDriver,String objName,String selector,String selectorvalue,String inputValue,ExtentReports extentReports,ExtentTest extentTestReport){
		
		CucumberTestAutomationLogger.writeToLog("Input","input()"," method called" );
		String status="false";
	    try {
	    	 
			 List<WebElement> listElements= getElements(webDriver, objName,selector,selectorvalue);
			WebElement txtField = listElements.get(0);
			 
		     if (txtField.isDisplayed()) {
		        	
					txtField.clear();
					//txtField.click();
					String initText = txtField.getAttribute("value");
					
					if (initText != null && initText.length() > 0) {
						txtField.sendKeys(Keys.END, Keys.SHIFT, Keys.HOME,Keys.DELETE);
						
						for (int i = 1; i <= initText.length(); i++)
							txtField.sendKeys(Keys.DELETE);
						CucumberTestAutomationLogger.writeToLog("Input",	"input() ",	"after deleting the text");
					}
					Actions actions = new Actions(webDriver);
					actions.moveToElement(txtField);
					actions.click();
					actions.sendKeys(inputValue);
					actions.build().perform();
				
					status="true";
					if(txtField.getAttribute("type").equalsIgnoreCase("password"))
					{
						 String key = "****************"; // 128 bit key

					     Key aesKey = new SecretKeySpec(key.getBytes(), "AES");
					     Cipher cipher = Cipher.getInstance("AES");

					     cipher.init(Cipher.ENCRYPT_MODE, aesKey);
					     byte[] encrypted = cipher.doFinal(inputValue.getBytes());
					       extentTestReport.log(LogStatus.PASS, " Enter " +objName +":  " +encrypted);
							CucumberTestAutomationLogger.writeToLog(objName+ " edit box successfully accepted the value ");
					     
					}
					else
					{
						extentTestReport.log(LogStatus.PASS, " Enter " +objName +":  " +inputValue);
						CucumberTestAutomationLogger.writeToLog(objName+ " edit box successfully accepted the value ");
					}
					
					
				}
			
			
		} catch (Exception e) {
			try{
				e.printStackTrace();
				String err[]=e.getMessage().split("\n");
				CucumberTestAutomationLogger.writeToLog(objName+ " unable to enter value in textbox "+e.getMessage().split("\n"));
				status =  "Exception " +err[0].replaceAll("'", "") + " Occurred";
			}
			finally{
				extentTestReport.log(LogStatus.FAIL, "Unable to enter "+objName +" due to Invalid Xpath");
		        extentTestReport.log(LogStatus.FAIL, extentTestReport.addScreenCapture(ExtentReportManager.CaptureScreen(webDriver, propertyFileReader.getValue("SNAPSHOT_FOLDER")+objName+": " +inputValue))); 
		    	// extentReports.endTest(extentTestReport);
		    	// extentReports.flush();
		         //extentReports.close(); 
		        webDriver.close();
		         //webDriver.quit();
		         
			}
	
	}
	    return status;
}

/*
 * ############################################################################################# 
 * ' FUNCTION NAME - getElements ' DESCRIPTION - This
 * function will be used to get the list of elements for given selector ' INPUT PARAMETERS -
 * '################################################################################################
 */
public List<WebElement> getElements(WebDriver webDriver, String objName,String selector,String selectorvalue) {

	List<WebElement> elementList = null;
	if (selector != null) {

		
		webDriver.manage().timeouts().pageLoadTimeout(30, TimeUnit.SECONDS);
		
		if (selector.equalsIgnoreCase("ID")) {
			elementList = webDriver.findElements(By.id(selectorvalue));
		} else if (selector.equalsIgnoreCase("NAME")) {
			elementList = webDriver.findElements(By.name(selectorvalue.trim()));
		} else if (selector.equalsIgnoreCase("XPATH")) {
			elementList = webDriver.findElements(By.xpath(selectorvalue));
		} else if (selector.equalsIgnoreCase("CLASS")) {

			elementList = webDriver.findElements(By.cssSelector(selectorvalue));

		} else if (selector.equalsIgnoreCase("PARTIALLINKTEXT")) {
			elementList = webDriver.findElements(By
					.partialLinkText(selectorvalue));
		} else if (selector.equalsIgnoreCase("LINKTEXT")) {
			elementList = webDriver.findElements(By.linkText(selectorvalue));
		}
		
		if (elementList.size() == 0) {
			
		} else {
			
		}
	}
	return elementList;

}
/*
 * ############################################################################################# 
 * ' FUNCTION NAME - getElements ' DESCRIPTION - This
 * function will be used to get the list of elements for given selector ' INPUT PARAMETERS -
 * '################################################################################################
 */
public List<WebElement> getElementsjavascript(WebDriver webDriver, String objName,String selector,String selectorvalue) {

	List<WebElement> elementList = null;
	if (selector != null) {

		
		webDriver.manage().timeouts().pageLoadTimeout(60, TimeUnit.SECONDS);
		
		if (selector.equalsIgnoreCase("ID")) {
			elementList = webDriver.findElements(By.id(selectorvalue));
		} else if (selector.equalsIgnoreCase("NAME")) {
			elementList = webDriver.findElements(By.name(selectorvalue.trim()));
		} else if (selector.equalsIgnoreCase("XPATH")) {
			elementList = webDriver.findElements(By.xpath(selectorvalue));
		} else if (selector.equalsIgnoreCase("CLASS")) {

			elementList = webDriver.findElements(By.cssSelector(selectorvalue));

		} else if (selector.equalsIgnoreCase("PARTIALLINKTEXT")) {
			elementList = webDriver.findElements(By
					.partialLinkText(selectorvalue));
		} else if (selector.equalsIgnoreCase("LINKTEXT")) {
			elementList = webDriver.findElements(By.linkText(selectorvalue));
		}
	
		if (elementList.size() == 0) {
			elementList = null;
		} else {
			
		}
	}
	return elementList;

}


/*
 * ############################################################################################# 
 * ' FUNCTION NAME - closeBrowser ' DESCRIPTION - This
 * function will be used to close the current Browser ' INPUT PARAMETERS -
 * '################################################################################################
 */
public String closeBrowser(WebDriver webDriver,ExtentReports extentReports,ExtentTest extentTestReport) {
	
	CucumberTestAutomationLogger.writeToLog("SeleniumKeywordslib", "closeBrowser() ",	" method called");
	String status ="false";
	try {
		if (webDriver != null) {
			//webDriver.close();
			status="true";
		}
		return status;
	  } catch (Exception e) {
		  try
		  {
			  e.printStackTrace();
				CucumberTestAutomationLogger.writeToLog("SeleniumKeywordslib", "CloseBrowser() ",	"Exception thrown while closing browser");
				String err[]=e.getMessage().split("\n");
				status=  "Exception " +err[0].replaceAll("'", "") + " Occurred";
			  
		  }
		finally
		{
			extentTestReport.log(LogStatus.FAIL, "Unable to close browser ");
	        extentTestReport.log(LogStatus.FAIL, extentTestReport.addScreenCapture(ExtentReportManager.CaptureScreen(webDriver, propertyFileReader.getValue("SNAPSHOT_FOLDER")))); 
	    	// extentReports.endTest(extentTestReport);
	    	// extentReports.flush();
	         //extentReports.close(); 
	        webDriver.close();
	         //webDriver.quit();
	         
		}
	
	}
	return status;
}

/*
 * ############################################################################################# 
 * ' FUNCTION NAME - click ' DESCRIPTION - This
 * function will be used to click on webelement ' INPUT PARAMETERS -
 * '################################################################################################
 */
public String click(WebDriver webDriver,WebElement objname,ExtentReports extentReports,ExtentTest extentTestReport) {
	
	CucumberTestAutomationLogger.writeToLog("Click", " click() "," method called");
	String status = "false";
	try {
		  //List<WebElement> listElements= getElements(webDriver, objname,selector,selectorvalue);
			 
		  WebElement txtField = objname;
		  
		  if (txtField.isDisplayed()) {
			 
			  txtField.click();
			 
			  status = "true";	
			  extentTestReport.log(LogStatus.PASS, "click on  " +objname);
			CucumberTestAutomationLogger.writeToLog("Click", " click() "," button clicked");
			
			
		  }else{
			  status = "false";	
			CucumberTestAutomationLogger.writeToLog("Click ", " click()"," Object not found");
			return status;
		}
	
	}  catch (Exception ex) {
		try
		{
			ex.printStackTrace();
			CucumberTestAutomationLogger.writeToLog("Click ", " click()"," Exception occurred");
			String err[]=ex.getMessage().split("\n");
			 status = "Exception " +err[0].replaceAll("'", "") + " Exception Occurred";
		}
		finally{
			extentTestReport.log(LogStatus.FAIL, "Unable to click "+objname);
	        extentTestReport.log(LogStatus.FAIL, extentTestReport.addScreenCapture(ExtentReportManager.CaptureScreen(webDriver, propertyFileReader.getValue("SNAPSHOT_FOLDER")+objname))); 
	    	// extentReports.endTest(extentTestReport);
	    	// extentReports.flush();
	         //extentReports.close(); 
	        webDriver.close();
	         //webDriver.quit();
	         
		}
	
 }
	return status;
}

/*
 * ############################################################################################# 
 * ' FUNCTION NAME - selectWindow ' DESCRIPTION - This
 * function will be used to switch to new window ' INPUT PARAMETERS -
 * '################################################################################################
 */
public String selectWindow(WebDriver webDriver,ExtentReports extentReports,ExtentTest extentTestReport) {
	
	CucumberTestAutomationLogger.writeToLog("select Window", " selectWindow() ","select Window method called");
	String status = "false";
	
	try {

		if (webDriver == null) {
			CucumberTestAutomationLogger.writeToLog("SeleniumKeywordslib",
					"SelectWindow() ", "Browser is null");
			status = "false";
		}
     	String winHandleBefore = webDriver.getWindowHandle();
		 Set<String> availableWindows = webDriver.getWindowHandles();
		 if (!availableWindows.isEmpty()) {
	      for(String winHandle : webDriver.getWindowHandles()){
	    	  CucumberTestAutomationLogger.writeToLog("SeleniumKeywordslib", "select Window ","No Of windows available" + webDriver.getWindowHandles().size());
	    	  webDriver.switchTo().window(winHandle);
	         	CucumberTestAutomationLogger.writeToLog("SeleniumKeywordslib","select Window() ","'"+ "' =>Document is activated successfully");
	         	webDriver.manage().timeouts().implicitlyWait(70, TimeUnit.SECONDS);
				status = "true";
				extentTestReport.log(LogStatus.PASS, " switch to child window ");
				//return winHandleBefore;
	      }
	      }
		else {
		CucumberTestAutomationLogger.writeToLog("SeleniumKeywordslib","select Window() ","Available windows size is zero");
			status = "false";
			return winHandleBefore;
		}

	}

	 catch (Exception ex) {
		 try
		 {
			    ex.printStackTrace();
				CucumberTestAutomationLogger.writeToLog("SeleniumKeywordslib","select Window() ", "Exeption thrown");
				CucumberTestAutomationLogger.writeToLog("SeleniumKeywordslib","select Window() ", "'" + "'object doesnot exists");
				String err[]=ex.getMessage().split("\n");
				 status = "Exception " +err[0].replaceAll("'", "") + " Exception Occurred";
				return status;
		 }
		 finally{
				extentTestReport.log(LogStatus.FAIL, "Unable to switch to window ");
		        extentTestReport.log(LogStatus.FAIL, extentTestReport.addScreenCapture(ExtentReportManager.CaptureScreen(webDriver, propertyFileReader.getValue("SNAPSHOT_FOLDER")))); 
		    	// extentReports.endTest(extentTestReport);
		    	// extentReports.flush();
		         //extentReports.close(); 
		        webDriver.close();
		         //webDriver.quit();
		         
			}

	}
	return status;
	
}

/*
 * ############################################################################################# 
 * ' FUNCTION NAME - windowActivate ' DESCRIPTION - This
 * function will be used to switch to Parent Window ' INPUT PARAMETERS -
 * '################################################################################################
 */
@SuppressWarnings("rawtypes")
public boolean windowActivate(WebDriver webDriver, String title,ExtentReports extentReports,ExtentTest extentTestReport) {
	CucumberTestAutomationLogger.writeToLog("SeleniumKeywordslib", "windowActivate() ",
			"title - " + title);
	boolean status = false;
	try {

		if (webDriver == null) {
			CucumberTestAutomationLogger.writeToLog("SeleniumKeywordslib","windowActivate() ", "Browser is null");
			return false;
		}
    	Set<String> availableWindows = webDriver.getWindowHandles();
		CucumberTestAutomationLogger.writeToLog("SeleniumKeywordslib","windowActivate() ", "No Of windows available"+ availableWindows.size());
		CucumberTestAutomationLogger.writeToLog("SeleniumKeywordslib","windowActivate() ", "No Of windows available"+ webDriver.getWindowHandles().size());

		if (!availableWindows.isEmpty()) {
			if (title.equals("ActivateLastWindow")) {
				for (Iterator i = availableWindows.iterator(); i.hasNext();) {
					String selectedWindowHandle = i.next().toString();
					webDriver.switchTo().window(selectedWindowHandle);
					CucumberTestAutomationLogger.writeToLog("SeleniumKeywordslib","windowActivate() ","'"+ webDriver.switchTo().window(selectedWindowHandle).getTitle()+ "' =>Window is activated successfully");
					status = true;
					extentTestReport.log(LogStatus.PASS, " switch to child window ");
				}

				return status;

			} else {
				for (Iterator i = availableWindows.iterator(); i.hasNext();) {
					String selectedWindowHandle = i.next().toString();
					webDriver.switchTo().window(selectedWindowHandle);
					status = true;

				}
				for (String windowId : webDriver.getWindowHandles()) {

					CucumberTestAutomationLogger.writeToLog("SeleniumKeywordslib","windowActivate() ", "in for title - "+ webDriver.switchTo().window(windowId).getTitle());
					if (webDriver.switchTo().window(windowId).getTitle().equals(title)) {
						CucumberTestAutomationLogger.writeToLog("SeleniumKeywordslib","windowActivate() ","'"+ title+ "' =>Document is activated successfully");
						status = true;
						return status;
					}
				}
			}


		} else {
			CucumberTestAutomationLogger.writeToLog("SeleniumKeywordslib","windowActivate() ","Available windows size is zero");
			status = false;
			return status;
		}

	}  catch (Exception exception) {
		 try
		 {
			    exception.printStackTrace();
				CucumberTestAutomationLogger.writeToLog("SeleniumKeywordslib","select Window() ", "Exeption thrown");
				CucumberTestAutomationLogger.writeToLog("SeleniumKeywordslib","select Window() ", "'" + title+ "'object doesnot exists");
				return false;
		 }
		 finally{
				extentTestReport.log(LogStatus.FAIL, "Unable to switch to window ");
		        extentTestReport.log(LogStatus.FAIL, extentTestReport.addScreenCapture(ExtentReportManager.CaptureScreen(webDriver, propertyFileReader.getValue("SNAPSHOT_FOLDER")))); 
		    	// extentReports.endTest(extentTestReport);
		    	// extentReports.flush();
		         //extentReports.close(); 
		        webDriver.close();
		         //webDriver.quit();
		         
			}

	}
	return status;
}

/*
 * ############################################################################################# 
 * ' FUNCTION NAME - mouseOver ' DESCRIPTION - This
 * function will be used to focus on Menu or list ' INPUT PARAMETERS -
 * '################################################################################################
 */

 public String mouseOver(WebDriver webDriver, String objName,String selector,String selectorvalue,ExtentReports extentReports,ExtentTest extentTestReport) {
        CucumberTestAutomationLogger.writeToLog("SeleniumKeywordslib", "mouseOver() "," object name" + objName);
        String status = "false";
        try {
                String menuItems=  selectorvalue;
		         //String string = "004-034556";
		         String[] parts = menuItems.split(",");
		          objName = parts[0]; // 004
		         String submenu1 = parts[1];
		         String submenu2 =parts[2];
		         String submenu3 =parts[3];
		         String path = "//*[text()="+ "'"+objName +"'"+"]";
               String submenu1path="//*[text()="+ "'"+submenu1 +"'"+"]";
               String submenu2path="//*[text()="+ "'"+submenu2 +"'"+"]";
               String submenu3path="//*[text()="+ "'"+submenu3 +"'"+"]";
          
          
               List<WebElement> list = getElements(webDriver, objName, selector,path);
             
               if (list.size() == 1) {
                     CucumberTestAutomationLogger.writeToLog("SeleniumKeywordslib", "mouseOver() ", "found more than one element.");
                     Actions act = new Actions(webDriver);
                     WebElement element = list.get(0);
                    

                     if (element.isDisplayed()) {

                            act.moveToElement(element).click(element).build().perform();
                          
                            if(!objName.equals(""))
                            {
                                   
                                 act.moveToElement(webDriver.findElement(By.xpath(submenu1path))).click(webDriver.findElement(By.xpath(submenu1path))).build().perform();
                                 act.moveToElement(webDriver.findElement(By.xpath(submenu2path))).click(webDriver.findElement(By.xpath(submenu2path))).build().perform();
                                 act.moveToElement(webDriver.findElement(By.xpath(submenu3path))).click(webDriver.findElement(By.xpath(submenu3path))).build().perform();
                                 status = "true";	 
                                 extentTestReport.log(LogStatus.PASS, "click on Menu Item" +objName);
                                   
                            }
                            
                            return status;
                     } else {
                            act.moveToElement(element).click().build().perform();
                            act.moveToElement(webDriver.findElement(By.xpath(submenu1path))).click(webDriver.findElement(By.xpath(submenu1path))).build().perform();
                            act.moveToElement(webDriver.findElement(By.xpath(submenu2path))).click(webDriver.findElement(By.xpath(submenu2path))).build().perform();
                            act.moveToElement(webDriver.findElement(By.xpath(submenu3path))).click(webDriver.findElement(By.xpath(submenu3path))).build().perform();
                            CucumberTestAutomationLogger.writeToLog("SeleniumKeywordslib", "mouseOver() ", "'" + objName + "' object was not visible");
                            status = "true";
                            extentTestReport.log(LogStatus.PASS, "click on Menu Item" +objName);
                            return status;
                     }
               } 
               else {
                     CucumberTestAutomationLogger.writeToLog("SeleniumKeywordslib","mouseOver() ", "'" + objName+ "' object was not found");
                     status = "false";
                     extentTestReport.log(LogStatus.FAIL, "click on Menu Item" +objName);
                     CucumberTestAutomationLogger.writeToLog("SeleniumKeywordslib", "mouseOver() ", "object not found");
                     return status;
               }

        } catch (Exception ex) {
        	try{
                ex.printStackTrace();
              // npiCTRMLogger.writeToLog("SeleniumKeywordslib", "mouseOver() ","Exception occurred");
                CucumberTestAutomationLogger.writeToLog("SeleniumKeywordslib", "mouseOver() ","Exception '" + ex.getMessage() + "' Occurred");
                String err[]=ex.getMessage().split("\n");
				status = "Exception " +err[0].replaceAll("'", "") + " Exception Occurred";
				return status;
              
        	}
        	finally {
        		 extentTestReport.addScreenCapture(propertyFileReader.getValue("REPORT_PATH")+"objName");
            	 extentTestReport.log(LogStatus.FAIL, extentTestReport.addScreenCapture(ExtentReportManager.CaptureScreen(webDriver, propertyFileReader.getValue("SNAPSHOT_FOLDER")+"objName"))); 
            	// extentReports.endTest(extentTestReport);
            	// extentReports.flush();
            	 //extentReports.close(); 
        	}
        }
 }


 /* * * ##########################################################################
	 * ################### ' FUNCTION NAME - selectFromList() ' DESCRIPTION
	 * - This function will Select item from the from list box. ' INPUT
	 * PARAMETERS - Obj - Name of the Object ' item - Option of that needs to be
	 * selected
	 * '################################################################################################*/
public String selectFromList(WebDriver webDriver, String objName,String selector, String selectorvalue,String input, String selection,ExtentReports extentReports,ExtentTest extentTestReport) {
		CucumberTestAutomationLogger.writeToLog("SeleniumKeywordslib", "select FromList() "," objName - " + objName + ".. input - " + input+ ".. selection - " + selection);
		String status = "false";

		try {
			   if (selection != null) {
     			waitForExistence(webDriver, objName,selector,selectorvalue);
			    List<WebElement> list = getElements(webDriver, objName, selector, selectorvalue);
				WebElement selectElement = null;
				if (list.size()>0)
					selectElement = list.get(0);

				if (selectElement != null) {
					if (selectElement.isDisplayed()) {
						
						if ((selectElement.getTagName().equalsIgnoreCase("input"))|| (selectElement.getTagName().equalsIgnoreCase("span"))) {
							CucumberTestAutomationLogger.writeToLog("SeleniumKeywordslib","select FromList()--- TagName",selectElement.getTagName());
							selectElement.clear();
							selectElement.sendKeys(input);
							selectElement.sendKeys(Keys.RETURN);
							CucumberTestAutomationLogger.writeToLog("SeleniumKeywordslib","select FromList() ",input+ "=> Item was selected successfully from the"+ "'" + objName + "'"+ "ListBox");
							status = "true";
							 extentTestReport.log(LogStatus.PASS, "select "+input +" option from " +objName);
							return status;

						} else if ((selectElement.getTagName().equalsIgnoreCase("select"))) {
							Select select = new Select(selectElement);
							if (selection.equalsIgnoreCase("value")) {
								CucumberTestAutomationLogger.writeToLog("SeleniumKeywordslib","select FromList() ","by option value");

								select.selectByValue(input);
								webDriver.manage().timeouts().pageLoadTimeout(1, TimeUnit.MINUTES);
								CucumberTestAutomationLogger.writeToLog("SeleniumKeywordslib","select FromList() ",input+ "=> Item was selected successfully from the"+ "'" + objName + "'"+ "ListBox");
								status = "true";
								 extentTestReport.log(LogStatus.PASS, "select "+input +" option from " +objName);
								return status;

							} else if (selection.equalsIgnoreCase("text")) {
								CucumberTestAutomationLogger.writeToLog("SeleniumKeywordslib","select FromList() ", "by label");
								select.selectByVisibleText(input.trim());
								CucumberTestAutomationLogger.writeToLog("SeleniumKeywordslib","select FromList() ",input+ "=> Item was selected successfully from the"+ "'" + objName + "'"+ "ListBox");
								status = "true";
								 extentTestReport.log(LogStatus.PASS, "select "+input +" option from " +objName);
								
							} else if (selection.equalsIgnoreCase("index")) {
								CucumberTestAutomationLogger.writeToLog("SeleniumKeywordslib","select FromList() ","by option index");
								//List<WebElement> oPtionsList = new ArrayList<WebElement>();
							//	oPtionsList = select.getOptions();
								int index= Integer.parseInt(input);
								select.selectByIndex(index);
								/*for (int i = 0; i < oPtionsList.size(); i++) {
                                 	if (oPtionsList.get(i).getText().trim().replaceAll("\\s+", " ").equalsIgnoreCase(input.trim().replaceAll("\\s+", " "))) {
										select.selectByIndex(i);
										status = "true";
										 extentTestReport.log(LogStatus.PASS, "select "+input +"from list dropdown" +objName);
										npiCTRMLogger.writeToLog("SeleniumKeywordslib","select FromList() ",input+ "=> Item was selected successfully from the"+ "'" + objName+ "'"+ "ListBox");
										return status;
									} else {
										status = "false";
									}
								}*/

							} 

						} else {
							CucumberTestAutomationLogger.writeToLog("SeleniumKeywordslib","select FromList()--- TagName is not input neither select",selectElement.getTagName());
							status = "false";
							extentTestReport.log(LogStatus.FAIL, "select "+input +" option from " +objName);
							return status;
						}
					} else if (!selectElement.isDisplayed()) {
                       	if ((selectElement.getTagName().equalsIgnoreCase("input"))|| (selectElement.getTagName().equalsIgnoreCase("span"))) {
							selectElement.clear();
							selectElement.sendKeys(input);
							selectElement.sendKeys(Keys.RETURN);
							status = "true";
							extentTestReport.log(LogStatus.PASS, "select "+input +"from list dropdown" +objName);
							CucumberTestAutomationLogger.writeToLog("SeleniumKeywordslib","select FromList() ",input+ "=> Item was selected successfully from the"+ "'" + objName + "'"+ "ListBox");
						} else {
							Select select = new Select(selectElement);
							if (selection.equalsIgnoreCase("value")) {
								CucumberTestAutomationLogger.writeToLog("SeleniumKeywordslib","select FromList() ","by option value");
                                select.selectByValue(input);
								status = "true";
								 extentTestReport.log(LogStatus.PASS, "select "+input +"from list dropdown" +objName);
								CucumberTestAutomationLogger.writeToLog("SeleniumKeywordslib","select FromList() ",input+ "=> Item was selected successfully from the"+ "'" + objName + "'"+ "ListBox");
							} else if (selection.equalsIgnoreCase("text")) {
								CucumberTestAutomationLogger.writeToLog("SeleniumKeywordslib","select FromList() ", "by label");
								select.selectByVisibleText(input.trim());
								status = "true";
								 extentTestReport.log(LogStatus.PASS, "select "+input +" option from " +objName) ;
								CucumberTestAutomationLogger.writeToLog("SeleniumKeywordslib","select FromList() ",input+ "=> Item was selected successfully from the"+ "'" + objName + "'"+ "ListBox");
							} else if (selection
									.equalsIgnoreCase("index")) {
								CucumberTestAutomationLogger.writeToLog("SeleniumKeywordslib","select FromList() ","by option index");
								select.selectByIndex(Integer.parseInt(input));
								status = "true";
								 extentTestReport.log(LogStatus.PASS, "select "+input +" option from " +objName );
								CucumberTestAutomationLogger.writeToLog("SeleniumKeywordslib","select FromList() ",input+ "=> Item was selected successfully from the"+ "'" + objName + "'"+ "ListBox");
							} 
						}

					}

					else {
						CucumberTestAutomationLogger.writeToLog("SeleniumKeywordslib","select FromList() ", "'" + objName+ "' list was not visible");
						status = "false";
						extentTestReport.log(LogStatus.FAIL, "select "+input +" option from " +objName);
						return status;
					}
				} else {
					CucumberTestAutomationLogger.writeToLog("SeleniumKeywordslib","select FromList() ","Invalid keyword usage. '" + objName+ "' is not a list");
					status = "false";
					extentTestReport.log(LogStatus.FAIL, "select "+input +" option from " +objName);
					return status;
				}

			}
		} 
		catch (Exception ex) {
            try{
                ex.printStackTrace();
                String err[]=ex.getMessage().split("\n");
                CucumberTestAutomationLogger.writeToLog("Input", "input()", " Object not found exeption thrown");
                status = "Exception " +err[0].replaceAll("'", "") + " Occurred";
                return status;
            }

			finally
			{
				 extentTestReport.log(LogStatus.FAIL, "Unable to select "+input +" option from " +objName +" dropdown");
		         extentTestReport.log(LogStatus.FAIL, extentTestReport.addScreenCapture(ExtentReportManager.CaptureScreen(webDriver, propertyFileReader.getValue("SNAPSHOT_FOLDER")))); 
		    	// extentReports.endTest(extentTestReport);
		    	// extentReports.flush();
		         //extentReports.close(); 
		        webDriver.close();
		         //webDriver.quit();
		         
			}
		}
		return status;

	}
/* * * ##########################################################################
 * ################### ' FUNCTION NAME - selectFromList() ' DESCRIPTION
 * - This function will Select item from the from list box. ' INPUT
 * PARAMETERS - Obj - Name of the Object ' item - Option of that needs to be
 * selected
 * '################################################################################################*/
public String selectFromList(WebDriver webDriver, String objName,String selector, String selectorvalue,String input,ExtentReports extentReports,ExtentTest extentTestReport) {
	
	String status = "false";

	try {
		String selectionitems=  input;
        String[] parts = selectionitems.split(",");
        input = parts[0]; 
        String selection = parts[1];
        CucumberTestAutomationLogger.writeToLog("SeleniumKeywordslib", "select FromList() "," objName - " + objName + ".. input - " + input+ ".. selection - " + selection);
		   if (selection != null) 
 			waitForExistence(webDriver, objName,selector,selectorvalue);
		    List<WebElement> list = getElements(webDriver, objName, selector, selectorvalue);
			WebElement selectElement = null;
			if (list.size()>0)
				selectElement = list.get(0);

			if (selectElement != null) {
				if (selectElement.isDisplayed()) {
					
					if ((selectElement.getTagName().equalsIgnoreCase("input"))|| (selectElement.getTagName().equalsIgnoreCase("span"))) {
						CucumberTestAutomationLogger.writeToLog("SeleniumKeywordslib","select FromList()--- TagName",selectElement.getTagName());
						selectElement.clear();
						selectElement.sendKeys(input);
						selectElement.sendKeys(Keys.RETURN);
						CucumberTestAutomationLogger.writeToLog("SeleniumKeywordslib","select FromList() ",input+ "=> Item was selected successfully from the"+ "'" + objName + "'"+ "ListBox");
						status = "true";
						 extentTestReport.log(LogStatus.PASS, "select "+input +" option from " +objName);
						return status;

					} else if ((selectElement.getTagName().equalsIgnoreCase("select"))) {
						Select select = new Select(selectElement);
						if (selection.equalsIgnoreCase("value")) {
							CucumberTestAutomationLogger.writeToLog("SeleniumKeywordslib","select FromList() ","by option value");
							//driver.manage().timeouts().implicitlyWait(30L, TimeUnit.SECONDS);
							select.selectByValue(input);
						//	new WebDriverWait(webDriver, 150).until(ExpectedConditions.visibilityOf(webDriver.findElement(By.xpath("//*[text()="+ "'"+input +"'"+"]"))));
							//webDriver.manage().timeouts().pageLoadTimeout(1, TimeUnit.MINUTES);
							CucumberTestAutomationLogger.writeToLog("SeleniumKeywordslib","select FromList() ",input+ "=> Item was selected successfully from the"+ "'" + objName + "'"+ "ListBox");
							status = "true";
							 extentTestReport.log(LogStatus.PASS, "select "+input +" option from " +objName);
							return status;

						} else if (selection.equalsIgnoreCase("text")) {
							CucumberTestAutomationLogger.writeToLog("SeleniumKeywordslib","select FromList() ", "by label");
						//	new WebDriverWait(webDriver, 50).until(ExpectedConditions.presenceOfElementLocated(By.xpath(selectorvalue)));
							//webDriver.manage().timeouts().pageLoadTimeout(1, TimeUnit.MINUTES);
							WebDriverWait wait = new WebDriverWait(webDriver, 3000);
							wait.until(ExpectedConditions.elementToBeClickable((By.xpath(selectorvalue))));
							select.selectByVisibleText(input.trim());
							Thread.sleep(1500);
							CucumberTestAutomationLogger.writeToLog("SeleniumKeywordslib","select FromList() ",input+ "=> Item was selected successfully from the"+ "'" + objName + "'"+ "ListBox");
							status = "true";
							 extentTestReport.log(LogStatus.PASS, "select "+input +" option from " +objName);
							
						} else if (selection.equalsIgnoreCase("index")) {
							CucumberTestAutomationLogger.writeToLog("SeleniumKeywordslib","select FromList() ","by option index");
							//List<WebElement> oPtionsList = new ArrayList<WebElement>();
						//	oPtionsList = select.getOptions();
							int index= Integer.parseInt(input);
							select.selectByIndex(index);
							/*for (int i = 0; i < oPtionsList.size(); i++) {
                             	if (oPtionsList.get(i).getText().trim().replaceAll("\\s+", " ").equalsIgnoreCase(input.trim().replaceAll("\\s+", " "))) {
									select.selectByIndex(i);
									status = "true";
									 extentTestReport.log(LogStatus.PASS, "select "+input +"from list dropdown" +objName);
									npiCTRMLogger.writeToLog("SeleniumKeywordslib","select FromList() ",input+ "=> Item was selected successfully from the"+ "'" + objName+ "'"+ "ListBox");
									return status;
								} else {
									status = "false";
								}
							}*/

						} 

					} else {
						CucumberTestAutomationLogger.writeToLog("SeleniumKeywordslib","select FromList()--- TagName is not input neither select",selectElement.getTagName());
						status = "false";
						extentTestReport.log(LogStatus.FAIL, "select "+input +" option from " +objName);
						return status;
					}
				} else if (!selectElement.isDisplayed()) {
                   	if ((selectElement.getTagName().equalsIgnoreCase("input"))|| (selectElement.getTagName().equalsIgnoreCase("span"))) {
						selectElement.clear();
						selectElement.sendKeys(input);
						selectElement.sendKeys(Keys.RETURN);
						status = "true";
						extentTestReport.log(LogStatus.PASS, "select "+input +"from list dropdown" +objName);
						CucumberTestAutomationLogger.writeToLog("SeleniumKeywordslib","select FromList() ",input+ "=> Item was selected successfully from the"+ "'" + objName + "'"+ "ListBox");
					} else {
						Select select = new Select(selectElement);
						if (selection.equalsIgnoreCase("value")) {
							CucumberTestAutomationLogger.writeToLog("SeleniumKeywordslib","select FromList() ","by option value");
                            select.selectByValue(input);
							status = "true";
							 extentTestReport.log(LogStatus.PASS, "select "+input +"from list dropdown" +objName);
							CucumberTestAutomationLogger.writeToLog("SeleniumKeywordslib","select FromList() ",input+ "=> Item was selected successfully from the"+ "'" + objName + "'"+ "ListBox");
						} else if (selection.equalsIgnoreCase("text")) {
							CucumberTestAutomationLogger.writeToLog("SeleniumKeywordslib","select FromList() ", "by label");
							//new WebDriverWait(webDriver, 150).until(ExpectedConditions.visibilityOf(webDriver.findElement(By.xpath("//*[text()="+ "'"+input +"'"+"]"))));
							//new WebDriverWait(webDriver, 50).until(ExpectedConditions.presenceOfElementLocated(By.xpath(selectorvalue)));
							
							select.selectByVisibleText(input.trim());
							Thread.sleep(1500);
							status = "true";
							 extentTestReport.log(LogStatus.PASS, "select "+input +" option from " +objName  );
							CucumberTestAutomationLogger.writeToLog("SeleniumKeywordslib","select FromList() ",input+ "=> Item was selected successfully from the"+ "'" + objName + "'"+ "ListBox");
						} else if (selection
								.equalsIgnoreCase("index")) {
							CucumberTestAutomationLogger.writeToLog("SeleniumKeywordslib","select FromList() ","by option index");
							select.selectByIndex(Integer.parseInt(input));
							status = "true";
							 extentTestReport.log(LogStatus.PASS, "select "+input +" option from " +objName );
							CucumberTestAutomationLogger.writeToLog("SeleniumKeywordslib","select FromList() ",input+ "=> Item was selected successfully from the"+ "'" + objName + "'"+ "ListBox");
						} 
					}

				}

				else {
					CucumberTestAutomationLogger.writeToLog("SeleniumKeywordslib","select FromList() ", "'" + objName+ "' list was not visible");
					status = "false";
					extentTestReport.log(LogStatus.FAIL, "select "+input +" option from " +objName);
					return status;
				}
			} else {
				CucumberTestAutomationLogger.writeToLog("SeleniumKeywordslib","select FromList() ","Invalid keyword usage. '" + objName+ "' is not a list");
				status = "false";
				extentTestReport.log(LogStatus.FAIL, "select "+input +" option from " +objName);
				return status;
			}

		}
	 
	catch (Exception ex) {
        try{
            ex.printStackTrace();
            String err[]=ex.getMessage().split("\n");
            CucumberTestAutomationLogger.writeToLog("Input", "input()", " Object not found exeption thrown");
            status = "Exception " +err[0].replaceAll("'", "") + " Occurred";
            return status;
        }

		finally
		{
			 extentTestReport.log(LogStatus.FAIL, "Unable to select "+input +" option from " +objName +" dropdown");
	         extentTestReport.log(LogStatus.FAIL, extentTestReport.addScreenCapture(ExtentReportManager.CaptureScreen(webDriver, propertyFileReader.getValue("SNAPSHOT_FOLDER")))); 
	    	// extentReports.endTest(extentTestReport);
	    	// extentReports.flush();
	         //extentReports.close(); 
	        webDriver.close();
	         //webDriver.quit();
	         
		}
	}
	return status;

}
/*
 * ############################################################################################# 
 * ' FUNCTION NAME - waitForExistence ' DESCRIPTION - This
 * function will be used to wait till elements presence for given selector ' INPUT PARAMETERS -
 * '################################################################################################
 */
public WebElement waitForExistence(WebDriver webDriver, String objName,String selector,String selectorvalue) {
	WebElement elementList = null;
	PropertyFileReader propertyFileReader = new PropertyFileReader();
	int waitTime = Integer.parseInt(propertyFileReader.getValue("MAX_TIME_TO_FIND_OBJECT"));
	webDriver.manage().timeouts().pageLoadTimeout(waitTime, TimeUnit.SECONDS);
	if(selector.contains("xpath")){
		elementList = (new WebDriverWait(webDriver, waitTime)).until(ExpectedConditions.presenceOfElementLocated(By.xpath(selectorvalue)));
	}
	else if(selector.contains("name")){
		elementList = (new WebDriverWait(webDriver, waitTime)).until(ExpectedConditions.presenceOfElementLocated(By.name(selectorvalue)));
		
	}
	else if(selector.contains("id")){
		elementList = (new WebDriverWait(webDriver, waitTime)).until(ExpectedConditions.presenceOfElementLocated(By.id(selectorvalue)));
		
	}
	return elementList;

}


/*
 * ##########################################################################
 * ################### ' FUNCTION NAME - ClickAndSelect ' DESCRIPTION - This
 * function will be used to focus on Menu or list ' INPUT PARAMETERS -
 * spyObj - object
 * '################################################################################################
 */

 public String clickAndSelect(WebDriver webDriver, WebElement objName,String selector,String selectorvalue,String inputtxt,ExtentReports extentReports,ExtentTest extentTestReport) {
        CucumberTestAutomationLogger.writeToLog("SeleniumKeywordslib", "clickAndSelect"," object name" + objName);
        String status = "false";
        try {
        	  Actions act = new Actions(webDriver);
        	  WebElement element =objName;
               String path="//*[text()="+ "'"+inputtxt +"'"+"]";

             //  List<WebElement> list = getElements(webDriver, objName, selector,selectorvalue);
               if (objName.isDisplayed()) {
                     CucumberTestAutomationLogger.writeToLog("SeleniumKeywordslib","clickAndSelect() ", "found more than one element.");
                   

                      
                     if (element.isDisplayed()) {

                            act.moveToElement(element).click(element).build().perform();
                            if(!inputtxt.equals(""))
                            {
                                   
                              act.moveToElement(webDriver.findElement(By.xpath(path))).click(webDriver.findElement(By.xpath(path))).build().perform();
                                   
                            }
                            status = "true";
                            extentTestReport.log(LogStatus.PASS, "select "  +inputtxt +" from "+objName+ " Menu List");
                            CucumberTestAutomationLogger.writeToLog("SeleniumKeywordslib","clickAndSelect() ", "'" + objName+ "' =>Hover is performed sucessfully ");
                            return status;
                     } else {
                            act.moveToElement(element).click(element).build().perform();
                            act.moveToElement(webDriver.findElement(By.xpath(path))).click(webDriver.findElement(By.xpath(path))).build().perform();
                            
                            CucumberTestAutomationLogger.writeToLog("SeleniumKeywordslib", "clickAndSelect() ", "'" + objName + "' object was not visible");
                            status = "true";
                            extentTestReport.log(LogStatus.PASS, "select "  +inputtxt +" from "+objName+ " Menu List");
                            return status;
                     }
               } 
               else {
                   act.moveToElement(element).click(element).build().perform();
                   act.moveToElement(webDriver.findElement(By.xpath(path))).click(webDriver.findElement(By.xpath(path))).build().perform();
                   
                   CucumberTestAutomationLogger.writeToLog("SeleniumKeywordslib", "clickAndSelect() ", "'" + objName + "' object was not visible");
                   status = "true";
                   extentTestReport.log(LogStatus.PASS, "select "  +inputtxt +" from "+objName+ " Menu List");
                   return status;
            }
        } 
        catch (NoSuchElementException ns) {
               ns.printStackTrace();
               CucumberTestAutomationLogger.writeToLog("SeleniumKeywordslib", "clickAndSelect() ","Object not found exeption thrown");
               CucumberTestAutomationLogger.writeToLog("SeleniumKeywordslib", "clickAndSelect() ", "'" + objName + "'object does not exist");
               status = "false";
               extentTestReport.log(LogStatus.FAIL, "select "  +inputtxt +" from "+objName+ "Menu List");
               return status;
        } catch (Exception ex) {
        	try
        	{
               ex.printStackTrace();
               CucumberTestAutomationLogger.writeToLog("SeleniumKeywordslib", "clickAndSelect() ","Exception occurred");
               extentTestReport.log(LogStatus.FAIL, "select "  +inputtxt +" from "+objName+ "Menu List");
               String err[]=ex.getMessage().split("\n");
			   status = "Exception " +err[0].replaceAll("'", "") + " Exception Occurred";
				return status;
        	}
        	finally
        	{
        		 extentTestReport.log(LogStatus.FAIL, "Unable to select "  +inputtxt +" from "+objName+ "Menu List");
		         extentTestReport.log(LogStatus.FAIL, extentTestReport.addScreenCapture(ExtentReportManager.CaptureScreen(webDriver, propertyFileReader.getValue("SNAPSHOT_FOLDER")))); 
		    	// extentReports.endTest(extentTestReport);
		    	// extentReports.flush();
		         //extentReports.close(); 
		        webDriver.close();
		         //webDriver.quit();
		         
        	}
              
        }
        
 }


  /*
   * ##########################################################################
   * ################### ' FUNCTION NAME - getText' DESCRIPTION - This
   * function will be used to get the text with out property name' INPUT PARAMETERS -
   * spyObj - object
   * '################################################################################################
   */
  public String getText(WebDriver webDriver, String objName,String selector,String selectorvalue,ExtentReports extentReports,ExtentTest extentTestReport) {
		CucumberTestAutomationLogger.writeToLog("SeleniumKeywordslib","getText() objName - ", objName + ".. selector - "+ selector);
		String text = null;
		try {
			
			List<WebElement> list = getElements(webDriver, objName, selector,selectorvalue);
		//	String msg[];
			if (list.size() > 1) {
				CucumberTestAutomationLogger.writeToLog("SeleniumKeywordslib", "getText()",list.size() + " number of elements Found");
				WebElement txtField = list.get(0);

				if (txtField != null) {
					if (txtField.isDisplayed()) {
						webDriver.manage().timeouts().implicitlyWait(60L, TimeUnit.SECONDS);
						text = txtField.getText();
						String upToNCharacters = text.substring(0, Math.min(text.length(), 50));
					if (text.equals("") || (text == null)) {
							if (txtField.getTagName().equalsIgnoreCase("input") || (txtField.getTagName().equalsIgnoreCase("a"))|| (txtField.getTagName().equalsIgnoreCase("span"))) {
								text = list.get(0).getAttribute("value");
						//		 msg=text.split("\\n");
								if(text.equals("") || (text == null))
								{
									text="no value";
								}
								else
								{
									CucumberTestAutomationLogger.writeToLog("SeleniumKeywordslib","getText()","'"+ text+ "' =>value was successfully captured from edit box=: "+ objName);
									extentTestReport.log(LogStatus.PASS, "Get Text from " +objName +": " +upToNCharacters);
									return text;
								}
								
							}
						} else
						{
						   CucumberTestAutomationLogger.writeToLog("SeleniumKeywordslib","getText()","'"+ text+ "' =>value was successfully captured from edit box=: "+ objName);
							extentTestReport.log(LogStatus.PASS, "Get Text from " +objName +": " +upToNCharacters);
							return text;
						}
							
					} else {
						CucumberTestAutomationLogger.writeToLog("SeleniumKeywordslib","getText()", "'" + objName+ "' =>does not visible!");
						extentTestReport.log(LogStatus.PASS, "Get Text from " +objName +": " +text);
						return null;
					}
				}

			}else if(list.size() == 1) {
				CucumberTestAutomationLogger.writeToLog("SeleniumKeywordslib", "getText()",list.size() + " number of elements Found");
				WebElement txtField = list.get(0);

				if (txtField != null) {
					//if (txtField.isDisplayed()) {
						webDriver.manage().timeouts().implicitlyWait(60L, TimeUnit.SECONDS);
						text = txtField.getText();
						String upToNCharacters = text.substring(0, Math.min(text.length(), 50));
					if (text.equals("") || (text == null)) {
							if (txtField.getTagName().equalsIgnoreCase("input") ||(txtField.getTagName().equalsIgnoreCase("a"))) {
								text = list.get(0).getAttribute("value");
								text =list.get(0).getText();
						//		 msg=text.split("\\n");
								if(text.equals("") || (text == null))
								{
									text="no value";
								}
								else
								{
									CucumberTestAutomationLogger.writeToLog("SeleniumKeywordslib","getText()","'"+ text+ "' =>value was successfully captured from edit box=: "+ objName);
									extentTestReport.log(LogStatus.PASS, "Get Text from " +objName +": " +upToNCharacters);
								}
								
								return text;
							}
						} else
						{
						CucumberTestAutomationLogger.writeToLog("SeleniumKeywordslib","getText()","'"+ text+ "' =>value was successfully captured from edit box=: "+ objName);
							extentTestReport.log(LogStatus.PASS, "Get Text from " +objName +": " +upToNCharacters);
							return text;
						}
							
				
				}
			
				
			}
			else
			{
				CucumberTestAutomationLogger.writeToLog("SeleniumKeywordslib","getText()", "'" + objName+ "' =>does not visible!");
				extentTestReport.log(LogStatus.PASS, "Get Text from " +objName +": " +text);
				return null;
			}
		} catch (NoSuchElementException ns) {
			ns.printStackTrace();
			CucumberTestAutomationLogger.writeToLog("SeleniumKeywordslib", "getText()","Object not found exeption thrown");
			CucumberTestAutomationLogger.writeToLog("SeleniumKeywordslib", "getText()", "'"+ objName + "'object does not exist");
			extentTestReport.log(LogStatus.FAIL, "Get Text from " +objName);
			return null;
		} catch (Exception ex) {
			try
			{
			ex.printStackTrace();
			CucumberTestAutomationLogger.writeToLog("SeleniumKeywordslib", "getText()","Exception occurred");
		//	npiCTRMLogger.writeToLog("SeleniumKeywordslib", "getText()","Exception '" + ex.getMessage() + "' Occurred");
			String err[]=ex.getMessage().split("\n");
			 text = "Exception " +err[0].replaceAll("'", "") + " Exception Occurred";
			return text;	
			}
			finally
			{
				 extentTestReport.log(LogStatus.FAIL, "Unable to retrive the text from web element ");
		         extentTestReport.log(LogStatus.FAIL, extentTestReport.addScreenCapture(ExtentReportManager.CaptureScreen(webDriver, propertyFileReader.getValue("SNAPSHOT_FOLDER")))); 
		    	// extentReports.endTest(extentTestReport);
		    	// extentReports.flush();
		         //extentReports.close(); 
		        webDriver.close();
		         //webDriver.quit();
		         
			}
		}
		return text;
	}
 

  /*
   * ##########################################################################
   * ################### ' FUNCTION NAME - getText' DESCRIPTION - This
   * function will be used to get the text with out property name' INPUT PARAMETERS -
   * spyObj - object
   * '################################################################################################
   */
  
   public String getText(WebDriver webDriver, String objName,String selector,String selectorvalue, String propName,ExtentReports extentReports,ExtentTest extentTestReport) {
 		String text = null;
 		try {
 			//waitForExistence(webDriver, objName, selector,selectorvalue);
 			List<WebElement> list = getElements(webDriver, objName, selector,selectorvalue);


 			
 			
 				CucumberTestAutomationLogger.writeToLog("SeleniumKeywordslib", "getText()","found " + list.size() + "elements");
 			if (list.size() > 0) {
 				text = list.get(0).getAttribute(propName);
 				if (text == null || text.equals(propName)) {
 					text = "null";
 					
 				} else {
 					extentTestReport.log(LogStatus.PASS, "Get Text from " +objName);
 					CucumberTestAutomationLogger.writeToLog("SeleniumKeywordslib","getText()","'"+ text+ "' =>value was successfully captured from edit box=: "+ objName);
 				}
 			}
 		} catch (NoSuchElementException ns) {
 			ns.printStackTrace();
 			CucumberTestAutomationLogger.writeToLog("SeleniumKeywordslib", "getText()","Object not found exeption thrown");
 			CucumberTestAutomationLogger.writeToLog("SeleniumKeywordslib", "getText()", "'"+ objName + "'object does not exist");
 		} catch (Exception ex) {
 			try{
 				ex.printStackTrace();
 				extentTestReport.log(LogStatus.FAIL, "Get Text from " +objName);
 	 			CucumberTestAutomationLogger.writeToLog("SeleniumKeywordslib", "getText()","Exception occurred");
 	 			String err[]=ex.getMessage().split("\n");
				text = "Exception " +err[0].replaceAll("'", "") + " Exception Occurred";
				return text;
 			}
 			
 			finally
 			{
 				 extentTestReport.log(LogStatus.FAIL, "Unable to get text from " +objName);
		         extentTestReport.log(LogStatus.FAIL, extentTestReport.addScreenCapture(ExtentReportManager.CaptureScreen(webDriver, propertyFileReader.getValue("SNAPSHOT_FOLDER")+"objName"))); 
		    	// extentReports.endTest(extentTestReport);
		    	// extentReports.flush();
		         //extentReports.close(); 
		        webDriver.close();
		         //webDriver.quit();
		         
 			}
			
 		}
 		return text;
 	}
  
   /*
    * ##########################################################################
    * ################### ' FUNCTION NAME - captureScreenshot' DESCRIPTION - This
    * function will be used to capture the screenshot' INPUT PARAMETERS -
    * spyObj - object
    * '################################################################################################
    */
   public String captureScreenshot(WebDriver webDriver,String name,ExtentReports extentReports,ExtentTest extentTestReport) {
		String status = "false";
		PropertyFileReader cfg = new PropertyFileReader();
		String snapShotPath = cfg.getValue("SNAPSHOT_FOLDER");
		CucumberTestAutomationLogger.writeToLog("SeleniumKeywordslib", "captureScreenshot() ","snapShotPath - " + snapShotPath);
		//File sfolder = new File(snapShotPath);
		SimpleDateFormat df = new SimpleDateFormat("dd-MM-yyyy HH-mm-ss.SSS");
    	//String fname = snapShotPath + "CaptureScreen" + "_"+ df.format(new Date()) +"_"+name+ ".png";
		try {

			CucumberTestAutomationLogger.writeToLog("SeleniumKeywordslib", "captureScreenshot() ","Getting generic screenshot");
//			File source = ((TakesScreenshot) webDriver).getScreenshotAs(OutputType.FILE);
//			if (sfolder.exists() == false) {
//				sfolder.mkdirs();
//			}
//			FileUtils.copyFile(source, new File(fname));
			CucumberTestAutomationLogger.writeToLog("SeleniumKeywordslib", "captureScreenshot() ","After getting generic screenshot");
			status = "true";
			extentTestReport.log(LogStatus.PASS, extentTestReport.addScreenCapture(ExtentReportManager.CaptureScreen(webDriver, propertyFileReader.getValue("SNAPSHOT_FOLDER")+name+"_"+ df.format(new Date()))));
			return status;

		} catch (Exception ex) {
           try
           {
        	ex.printStackTrace();
   			CucumberTestAutomationLogger.writeToLog("SeleniumKeywordslib", "captureScreenshot() ",ex.getMessage());
   			String err[]=ex.getMessage().split("\n");
			 status = "Exception " +err[0].replaceAll("'", "") + " Exception Occurred";
			return status;
           }
			finally
			{
				 extentTestReport.log(LogStatus.FAIL, "Unable to capture screenshot ");
		         extentTestReport.log(LogStatus.FAIL, extentTestReport.addScreenCapture(ExtentReportManager.CaptureScreen(webDriver, propertyFileReader.getValue("SNAPSHOT_FOLDER")+name+"_"+ df.format(new Date())))); 
		    	// extentReports.endTest(extentTestReport);
		    	// extentReports.flush();
		         //extentReports.close(); 
		        webDriver.close();
		         //webDriver.quit();
		         
			}
          
		}
		
	}
   /*
    * ##########################################################################
    * ################### ' FUNCTION NAME - captureScreenshot' DESCRIPTION - This
    * function will be used to capture the screenshot' INPUT PARAMETERS -
    * spyObj - object
    * '################################################################################################
    */
   public String captureScreenshot(WebDriver webDriver,ExtentReports extentReports,ExtentTest extentTestReport) {
		String status = "false";
		PropertyFileReader cfg = new PropertyFileReader();
		String snapShotPath = cfg.getValue("SNAPSHOT_FOLDER");
		CucumberTestAutomationLogger.writeToLog("SeleniumKeywordslib", "captureScreenshot() ","snapShotPath - " + snapShotPath);
		File sfolder = new File(snapShotPath);
		SimpleDateFormat df = new SimpleDateFormat("dd-MM-yyyy HH-mm-ss");
    	String fname = snapShotPath + "CaptureScreen" + "_"+ df.format(new Date()) +"_"+""+ ".png";
		try {

			CucumberTestAutomationLogger.writeToLog("SeleniumKeywordslib", "captureScreenshot() ","Getting generic screenshot");
			File source = ((TakesScreenshot) webDriver).getScreenshotAs(OutputType.FILE);
			if (sfolder.exists() == false) {
				sfolder.mkdirs();
			}
			FileUtils.copyFile(source, new File(fname));
			CucumberTestAutomationLogger.writeToLog("SeleniumKeywordslib", "captureScreenshot() ","After getting generic screenshot");
			status = "true";
			extentTestReport.log(LogStatus.PASS, extentTestReport.addScreenCapture(ExtentReportManager.CaptureScreen(webDriver, propertyFileReader.getValue("SNAPSHOT_FOLDER")+"")));
			return status;

		} catch (Exception ex) {
           try
           {
        	ex.printStackTrace();
   			CucumberTestAutomationLogger.writeToLog("SeleniumKeywordslib", "captureScreenshot() ",ex.getMessage());
   			String err[]=ex.getMessage().split("\n");
			 status = "Exception " +err[0].replaceAll("'", "") + " Exception Occurred";
			return status;
           }
			finally
			{
				 extentTestReport.log(LogStatus.FAIL, "Unable to capture screenshot ");
		         extentTestReport.log(LogStatus.FAIL, extentTestReport.addScreenCapture(ExtentReportManager.CaptureScreen(webDriver, propertyFileReader.getValue("SNAPSHOT_FOLDER")+""))); 
		    	// extentReports.endTest(extentTestReport);
		    	// extentReports.flush();
		         //extentReports.close(); 
		        webDriver.close();
		         //webDriver.quit();
		         
			}
          
		}
		
	}
   /*
    * ##########################################################################
    * ################### ' FUNCTION NAME - switchtochildwindow' DESCRIPTION - This
    * function will be used to switch to child window' INPUT PARAMETERS -
    * spyObj - object
    * '################################################################################################
    */
    public String switchtochildwindow(WebDriver webDriver,ExtentReports extentReports,ExtentTest extentTestReport)
     {
    	  String status="";
    	  try
    	  {
    		webDriver.manage().timeouts().implicitlyWait(70, TimeUnit.SECONDS);
    	    Set <String>handles = webDriver.getWindowHandles();//To handle multiple windows
    		String firstWinHandle = webDriver.getWindowHandle(); //To get your main window
    		handles.remove(firstWinHandle);
    		String winHandle=handles.iterator().next();  //To find popup window
    		String secondWinHandle = null;
    		for (String windowId : webDriver.getWindowHandles()) {
    		if (windowId!=firstWinHandle){
    			secondWinHandle=winHandle;
    		}
    		}
    		CucumberTestAutomationLogger.writeToLog("SeleniumKeywordslib", "switchtochildwindow() ","switchtochildwindow");
    		webDriver.switchTo().window(secondWinHandle); //To switch to popup window
    		//webDriver.close();
    		//webDriver.switchTo().window(ge)
    		 return firstWinHandle;
    		
    	  }
    	  catch (Exception ex) {
    		  try
    		  {
    			  ex.printStackTrace();
    	  			CucumberTestAutomationLogger.writeToLog("SeleniumKeywordslib", "switchtochildwindow() ",ex.getMessage());
    	  			String err[]=ex.getMessage().split("\n");
    				 status = "Exception " +err[0].replaceAll("'", "") + " Exception Occurred";
    				return status;
    		  }
    		  finally
    		  {
    			 extentTestReport.log(LogStatus.FAIL, "Window not found ");
 		         extentTestReport.log(LogStatus.FAIL, extentTestReport.addScreenCapture(ExtentReportManager.CaptureScreen(webDriver, propertyFileReader.getValue("SNAPSHOT_FOLDER")))); 
 		    	// extentReports.endTest(extentTestReport);
 		    	// extentReports.flush();
 		         //extentReports.close(); 
 		        webDriver.close();
 		         //webDriver.quit();
 		         
    		  }
    		  
		}
     }
      
    /*
     * ##########################################################################
     * ################### ' FUNCTION NAME - switchtochildwindow' DESCRIPTION - This
     * function will be used to switch to child window' INPUT PARAMETERS -
     * spyObj - object
     * '################################################################################################
     */
     public String switchtochildwindow(WebDriver webDriver,String title,ExtentReports extentReports,ExtentTest extentTestReport)
      {
     	  String status="";
     	  try
     	  {
     		webDriver.manage().timeouts().implicitlyWait(70, TimeUnit.SECONDS);
     	    Set <String>handles = webDriver.getWindowHandles();//To handle multiple windows
     		String firstWinHandle = webDriver.getWindowHandle(); //To get your main window
     		handles.remove(firstWinHandle);
     	//	String winHandle=handles.iterator().next();  //To find popup window
     		String secondWinHandle = null;
     		for (String windowId : webDriver.getWindowHandles()) {
     		
     		if (windowId!=firstWinHandle){
     			secondWinHandle=windowId;
     			if(webDriver.switchTo().window(windowId).getTitle().equals(title))
         		{
         		webDriver.switchTo().window(secondWinHandle);
         		//To switch to popup window
         		}
     		}
     		}
     		CucumberTestAutomationLogger.writeToLog("SeleniumKeywordslib", "switchtochildwindow() ","switchtochildwindow");
     		
     		
     		//webDriver.switchTo().window(ge)
     		 return firstWinHandle;
     		
     	  }
     	  catch (Exception ex) {
     		  try
     		  {
     			  ex.printStackTrace();
     	  			CucumberTestAutomationLogger.writeToLog("SeleniumKeywordslib", "switchtochildwindow() ",ex.getMessage());
     	  			String err[]=ex.getMessage().split("\n");
     				 status = "Exception " +err[0].replaceAll("'", "") + " Exception Occurred";
     				return status;
     		  }
     		  finally
     		  {
     			 extentTestReport.log(LogStatus.FAIL, "Window not found ");
  		         extentTestReport.log(LogStatus.FAIL, extentTestReport.addScreenCapture(ExtentReportManager.CaptureScreen(webDriver, propertyFileReader.getValue("SNAPSHOT_FOLDER")))); 
  		    	// extentReports.endTest(extentTestReport);
  		    	// extentReports.flush();
  		         //extentReports.close(); 
  		        webDriver.close();
  		         //webDriver.quit();
  		         
     		  }
     		  
 		}
      }
       
      /*
       * ##########################################################################
       * ################### ' FUNCTION NAME - stopExecution' DESCRIPTION - This
       * function will be used to Kill the driver servers' INPUT PARAMETERS -
       * spyObj - object
       * '################################################################################################
       */
      
     public void stopExecution(WebDriver webDriver,String browser) {

  		try {
  			
  			webDriver.close();
  			webDriver.quit();
  				////webDriver.quit();
  			
  			if(browser.equalsIgnoreCase("ie"))
  			{
  				Runtime.getRuntime().exec("taskkill /F /IM iedriverserver.exe");
  				//WindowsUtils.killByName("IEDriverServer.exe");
  			}
  			
  			else
  				if(browser.equalsIgnoreCase("chrome"))
  				{
  					Runtime.getRuntime().exec("taskkill /f /im chromedriver.exe");
  					//WindowsUtils.killByName("chromedriver.exe");
  					
  				}
  			//Runtime.getRuntime().exec("taskkill /F /IM iexplore.exe");

  		} catch (Exception e) {
  			
  			e.printStackTrace();
  	  		CucumberTestAutomationLogger.writeToLog("SeleniumKeywordslib", "stopExecution","Exception while ending Session" + e.getMessage());
  	  		e.printStackTrace();
  			CucumberTestAutomationLogger.writeToLog("SeleniumKeywordslib", "stopExecution","Exception while ending Session" + e.getMessage());
  			//webDriver.close();
  			//webDriver.quit();
  		    
  			
  			
  		}

  	}
	public String launchUrl(WebDriver webDriver, String url, ExtentReports extentReports,ExtentTest extentTestReport) {
		
		String status= "false";
		try {
			
			PropertyFileReader propertyFileReader = new PropertyFileReader();
			int waitTime = Integer.parseInt(propertyFileReader.getValue("MAX_TIME_TO_FIND_OBJECT"));
			webDriver.manage().timeouts().implicitlyWait(waitTime, TimeUnit.SECONDS);
			Long w = (Long) ((JavascriptExecutor) webDriver).executeScript("return window.screen.availWidth;");
			Long h = (Long) ((JavascriptExecutor) webDriver).executeScript("return window.screen.availHeight;");
			webDriver.manage().window().setSize(new Dimension(w.intValue(), h.intValue()));
			webDriver.get(url);
		  	
			if (webDriver.getPageSource().contains("Continue to this website (not recommended)")) {
				
				webDriver.get("javascript:document.getElementById('overridelink').click();");
				webDriver.manage().timeouts().implicitlyWait(waitTime, TimeUnit.SECONDS);
				status="true";
			}
			
			
			return status;

		} catch (Exception ex) {
			try
			{
			ex.printStackTrace();
			if (ex.getMessage().toString().contains("Modal dialog present")) {
				
				CucumberTestAutomationLogger.writeToLog("LaunchUrl", "launchUrl() ",	" not able to launch.");
				String err[]=ex.getMessage().split("\n");
				status = "Exception " +err[0].replaceAll("'", "") + "URL opened but popup exists";
				return status;
				
			} else {
				
				CucumberTestAutomationLogger.writeToLog("LaunchUrl", "launchUrl() ",	" not able to launch.");
				String err[]=ex.getMessage().split("\n");
				status = "Exception " +err[0].replaceAll("'", "") + "Exception exeption thrown in launchURL";
				return status;
							
			}
			
		
		}
		
		finally
		{
			     extentTestReport.log(LogStatus.FAIL, "Unable to launch the uRL ");
		         extentTestReport.log(LogStatus.FAIL, extentTestReport.addScreenCapture(ExtentReportManager.CaptureScreen(webDriver, propertyFileReader.getValue("SNAPSHOT_FOLDER")))); 
		    	// extentReports.endTest(extentTestReport);
		    	// extentReports.flush();
		         //extentReports.close(); 
		        webDriver.close();
		         //webDriver.quit();
		         
		}

	}
}
	 /*
     * ##########################################################################
     * ################### ' FUNCTION NAME - clickByJavaScript' DESCRIPTION - This
     * function will be used to click the element by javascript executor' INPUT PARAMETERS -
     * spyObj - object
     * '################################################################################################
     */
	public String clickByJavaScript(WebDriver webDriver, String objName,String selector,String selectorvalue, ExtentReports extentReports,ExtentTest extentTestReport) {
		String status = "false";
		try {
		//	waitForExistence(webDriver, objName, selector,selectorvalue);
			List<WebElement> list = getElementsjavascript(webDriver, objName, selector,selectorvalue);
			if(list== null)
			{
				
			//JavascriptExecutor js = (JavascriptExecutor) webDriver;
			//js.executeScript("arguments[0].click();", list.get(0));
			CucumberTestAutomationLogger.writeToLog("SeleniumKeywordslib","clickByJavaScript() ", "'" + objName+ "' element does not exist");
			status = "false";
			extentTestReport.log(LogStatus.PASS, objName+"  does not exist");
			return status;
			}
			else
			{
				
			
            CucumberTestAutomationLogger.writeToLog("SeleniumKeywordslib","clickByJavaScript() ","web elements size - " + list.size());
			if (list.size() > 0) {
				CucumberTestAutomationLogger.writeToLog("SeleniumKeywordslib","clickByJavaScript() ","found more than one element");
				CucumberTestAutomationLogger.writeToLog("SeleniumKeywordslib","clickByJavaScript() ", "clicking 1st element");
			//	for (WebElement element : list) {
				WebElement element = list.get(0);
					if ((element.isDisplayed())||(!element.isDisplayed())) {
						CucumberTestAutomationLogger.writeToLog("SeleniumKeywordslib","clickByJavaScript() ", "object visible");
						JavascriptExecutor js = (JavascriptExecutor) webDriver;
						js.executeScript("arguments[0].click();", element);
                     	status = "true";
						extentTestReport.log(LogStatus.PASS, "click on "+objName);
						CucumberTestAutomationLogger.writeToLog("SeleniumKeywordslib","clickByJavaScript() ", "'" + objName+ "' => element does not exist");
						return status;
					} else {
						CucumberTestAutomationLogger.writeToLog("SeleniumKeywordslib","clickByJavaScript() ", "'" + objName+ "' element does not exist");
						status = "false";
						extentTestReport.log(LogStatus.FAIL,  objName+"  does not exist" );
						return status;
					}

				//}

			} 
			else if(list.size() == 0)
			{
				WebElement element = list.get(0);
				if ((element.isDisplayed())||(!element.isDisplayed())) {
					CucumberTestAutomationLogger.writeToLog("SeleniumKeywordslib","clickByJavaScript() ", "object visible");
					JavascriptExecutor js = (JavascriptExecutor) webDriver;
					js.executeScript("arguments[0].click();", element);
                 	status = "true";
					extentTestReport.log(LogStatus.PASS, "click on "+objName);
					CucumberTestAutomationLogger.writeToLog("SeleniumKeywordslib","clickByJavaScript() ", "'" + objName+ "' => element does not exist");
					return status;
				} else {
					CucumberTestAutomationLogger.writeToLog("SeleniumKeywordslib","clickByJavaScript() ", "'" + objName+ "' element does not exist");
					status = "false";
					extentTestReport.log(LogStatus.FAIL,  objName+"  does not exist" );
					return status;
				}
			}
			else {
				//JavascriptExecutor js = (JavascriptExecutor) webDriver;
				//js.executeScript("arguments[0].click();", list.get(0));
				CucumberTestAutomationLogger.writeToLog("SeleniumKeywordslib","clickByJavaScript() ", "'" + objName+ "' element does not exist");
				status = "false";
				extentTestReport.log(LogStatus.FAIL, objName+"  does not exist");
				return status;
			}
			}
		} catch (Exception ex) {
			try{
			ex.printStackTrace();
		    CucumberTestAutomationLogger.writeToLog("SeleniumKeywordslib", "clickByJavaScript() ","Exception '" + ex.getMessage() + "' Occurred");
		    String err[]=ex.getMessage().split("\n");
			status = "Exception " +err[0].replaceAll("'", "") + " Exception Occurred";
			return status;
			}
			finally
			{
				     extentTestReport.log(LogStatus.FAIL, "Unable to click on "+objName+"using javascript executor");
			         extentTestReport.log(LogStatus.FAIL, extentTestReport.addScreenCapture(ExtentReportManager.CaptureScreen(webDriver, propertyFileReader.getValue("SNAPSHOT_FOLDER")+objName))); 
			    	// extentReports.endTest(extentTestReport);
			    	// extentReports.flush();
			         //extentReports.close(); 
			        webDriver.close();
			         //webDriver.quit();
			         
			}
		}


	}
	 /*
     * ##########################################################################
     * ################### ' FUNCTION NAME - clickByJavaScript' DESCRIPTION - This
     * function will be used to click the element by javascript executor' INPUT PARAMETERS -
     * spyObj - object
     * '################################################################################################
     */
	public String clickByJavaScriptByText(WebDriver webDriver, String objName,String selector,String selectorvalue,String text, ExtentReports extentReports,ExtentTest extentTestReport) {
		String status = "false";
		try {
		//	waitForExistence(webDriver, objName, selector,selectorvalue);
			 String path = "//a[text()="+ "'"+text +"'"+"]";
             
             List<WebElement> list = getElements(webDriver, objName, selector,path);
			
			if(list== null)
			{
				
			//JavascriptExecutor js = (JavascriptExecutor) webDriver;
			//js.executeScript("arguments[0].click();", list.get(0));
			CucumberTestAutomationLogger.writeToLog("SeleniumKeywordslib","clickByJavaScript() ", "'" + objName+ "' element does not exist");
			status = "false";
			extentTestReport.log(LogStatus.PASS, objName+"  does not exist");
			return status;
			}
			else
			{
				
			
            CucumberTestAutomationLogger.writeToLog("SeleniumKeywordslib","clickByJavaScript() ","web elements siOze - " + list.size());
			if (list.size() > 0) {
				CucumberTestAutomationLogger.writeToLog("SeleniumKeywordslib","clickByJavaScript() ","found more than one element");
				CucumberTestAutomationLogger.writeToLog("SeleniumKeywordslib","clickByJavaScript() ", "clicking 1st element");
			//	for (WebElement element : list) {
				WebElement element = list.get(0);
					if ((element.isDisplayed())||(!element.isDisplayed())) {
						CucumberTestAutomationLogger.writeToLog("SeleniumKeywordslib","clickByJavaScript() ", "object visible");
						JavascriptExecutor js = (JavascriptExecutor) webDriver;
						js.executeScript("arguments[0].click();", element);
                     	status = "true";
						extentTestReport.log(LogStatus.PASS, "click on "+objName);
						CucumberTestAutomationLogger.writeToLog("SeleniumKeywordslib","clickByJavaScript() ", "'" + objName+ "' => element does not exist");
						return status;
					} else {
						CucumberTestAutomationLogger.writeToLog("SeleniumKeywordslib","clickByJavaScript() ", "'" + objName+ "' element does not exist");
						status = "false";
						extentTestReport.log(LogStatus.FAIL,  objName+"  does not exist" );
						return status;
					}

				//}

			} 
			else if(list.size() == 0)
			{
				WebElement element = list.get(0);
				if ((element.isDisplayed())||(!element.isDisplayed())) {
					CucumberTestAutomationLogger.writeToLog("SeleniumKeywordslib","clickByJavaScript() ", "object visible");
					JavascriptExecutor js = (JavascriptExecutor) webDriver;
					js.executeScript("arguments[0].click();", element);
                 	status = "true";
					extentTestReport.log(LogStatus.PASS, "click on "+objName);
					CucumberTestAutomationLogger.writeToLog("SeleniumKeywordslib","clickByJavaScript() ", "'" + objName+ "' => element does not exist");
					return status;
				} else {
					CucumberTestAutomationLogger.writeToLog("SeleniumKeywordslib","clickByJavaScript() ", "'" + objName+ "' element does not exist");
					status = "false";
					extentTestReport.log(LogStatus.FAIL,  objName+"  does not exist" );
					return status;
				}
			}
			else {
				//JavascriptExecutor js = (JavascriptExecutor) webDriver;
				//js.executeScript("arguments[0].click();", list.get(0));
				CucumberTestAutomationLogger.writeToLog("SeleniumKeywordslib","clickByJavaScript() ", "'" + objName+ "' element does not exist");
				status = "false";
				extentTestReport.log(LogStatus.FAIL, objName+"  does not exist");
				return status;
			}
			}
		} catch (Exception ex) {
			try{
			ex.printStackTrace();
		    CucumberTestAutomationLogger.writeToLog("SeleniumKeywordslib", "clickByJavaScript() ","Exception '" + ex.getMessage() + "' Occurred");
		    String err[]=ex.getMessage().split("\n");
			status = "Exception " +err[0].replaceAll("'", "") + " Exception Occurred";
			return status;
			}
			finally
			{
				     extentTestReport.log(LogStatus.FAIL, "Unable to click on "+objName+"using javascript executor");
			         extentTestReport.log(LogStatus.FAIL, extentTestReport.addScreenCapture(ExtentReportManager.CaptureScreen(webDriver, propertyFileReader.getValue("SNAPSHOT_FOLDER")+objName))); 
			    	// extentReports.endTest(extentTestReport);
			    	// extentReports.flush();
			         //extentReports.close(); 
			        webDriver.close();
			         //webDriver.quit();
			         
			}
		}


	}
	public String verifyText(WebDriver webDriver, WebElement objName,String selector, String selectorvalue,String inputTxt, String matchType,ExtentReports extentReports,ExtentTest extentTestReport) {
		CucumberTestAutomationLogger.writeToLog("SeleniumKeywordslib", "verifyText() "," executing verifyText objName - " + objName+ ".. inputTxt - " + inputTxt + ".. matchType - "+ matchType);
		String status = "false";
		String text = null;
		String upToNCharacters ="";
		try {
			WebElement txtField = objName; 
		  // waitForExistence(webDriver, objName, selector,selectorvalue);
		 //  List<WebElement> list = getElements(webDriver, objName, selector,selectorvalue);
			CucumberTestAutomationLogger.writeToLog("SeleniumKeywordslib", "verifyText() ","web elements size - " );
			if (txtField.isDisplayed()) {
				CucumberTestAutomationLogger.writeToLog("SeleniumKeywordslib","verifyText() ", "found more than one element");
				CucumberTestAutomationLogger.writeToLog("SeleniumKeywordslib","verifyText() ", "verifying 1st text field");
				//for (WebElement txtField : list) {
					if (txtField.getTagName().equalsIgnoreCase("INPUT")&& txtField.getAttribute("type") != null&& txtField.getAttribute("type").equalsIgnoreCase("text")) {

						if (txtField.isDisplayed()) {
							text = txtField.getText();
							upToNCharacters = text.substring(0, Math.min(text.length(), 50));
							CucumberTestAutomationLogger.writeToLog("SeleniumKeywordslib","verifyText() ", "Text - " + text);
							if (matchType.equalsIgnoreCase("Exact")) {
								if (inputTxt.trim().equalsIgnoreCase(text.trim())) {
									CucumberTestAutomationLogger.writeToLog("SeleniumKeywordslib","verifyText() ","'"+ text+ "' =>value matches input text  "+ inputTxt);
									status = "true";
									extentTestReport.log(LogStatus.PASS, objName +" matches with " +inputTxt);
									return status;
								} else {
									CucumberTestAutomationLogger.writeToLog("SeleniumKeywordslib","verifyText() ","'"+ text+ "' =>value doesnt match input text  "+ inputTxt);
									status = "false";
									extentTestReport.log(LogStatus.FAIL, objName +" doesnot matches with " +inputTxt);
									return status;
								}

							}

							else if (matchType.equalsIgnoreCase("false")) {
								if (inputTxt.equalsIgnoreCase(text)) {
									CucumberTestAutomationLogger.writeToLog("SeleniumKeywordslib","verifyText() ", "'" + text+ "' =>Object Exists  "+ inputTxt);
									status = "false";
									extentTestReport.log(LogStatus.FAIL, objName +" matches with " +inputTxt);
									return status;
								} else {
									CucumberTestAutomationLogger.writeToLog("SeleniumKeywordslib","verifyText() ","'"+ text+ "' =>Object doesn't exist  "+ inputTxt);
									status = "true";
									extentTestReport.log(LogStatus.PASS, objName +" does not matches with " +inputTxt);
									return status;
								}
							}

					
						} else {
							CucumberTestAutomationLogger.writeToLog("SeleniumKeywordslib","verifyText() ", "'" + objName+ "text field  was not visible");
							status = "false";
							return status;
						}
					} else {
						CucumberTestAutomationLogger.writeToLog("SeleniumKeywordslib","verifyText() ", "'" + objName+ "' is not a text field");
						if (txtField.isDisplayed()) {
							text = txtField.getText();
							upToNCharacters = text.substring(0, Math.min(text.length(), 50));
							CucumberTestAutomationLogger.writeToLog("SeleniumKeywordslib","verifyText() ", "Text - " + text);
							
							if (matchType.equalsIgnoreCase("Exact")) {
								if (inputTxt.trim().equalsIgnoreCase(text.trim())) {
									CucumberTestAutomationLogger.writeToLog("SeleniumKeywordslib","verifyText() ","'"+ text+ "' =>value matches input text  "+ inputTxt);
									status = "true";
									extentTestReport.log(LogStatus.PASS, objName +" matches with " +inputTxt);
									return status;
								} else {
									CucumberTestAutomationLogger.writeToLog("SeleniumKeywordslib","verifyText() ","'"+ text+ "' =>value doesnt match input text  "+ inputTxt);
									status = "false";
									extentTestReport.log(LogStatus.FAIL, objName +" does not matches with " +inputTxt);
									return status;
								}

							} else if (matchType.equalsIgnoreCase("false")) {
								if (inputTxt.equalsIgnoreCase(text)) {
									CucumberTestAutomationLogger.writeToLog("SeleniumKeywordslib","verifyText() ", "'" + text+ "' =>Object Exists  "+ inputTxt);
									status = "false";
									extentTestReport.log(LogStatus.FAIL, objName +" matches with " +inputTxt);
									return status;
								} else {
									CucumberTestAutomationLogger.writeToLog("SeleniumKeywordslib","verifyText() ","'"+ text+ "' =>Object doesn't exist  "+ inputTxt);
									status = "true";
									extentTestReport.log(LogStatus.PASS, objName +" does not matches with " +inputTxt);
									return status;
								}
							}

							else if (matchType.equalsIgnoreCase("contains")) {
								if (text.trim().contains(inputTxt.trim())||(inputTxt.trim().contains(text.trim()))) {
									CucumberTestAutomationLogger.writeToLog("SeleniumKeywordslib","verifyText() ","'"+ text+ "' =>Object Text contains  "+ inputTxt);
									status = "true";
									extentTestReport.log(LogStatus.PASS, objName +" contains " +upToNCharacters);
									return status;
								} else {
									CucumberTestAutomationLogger.writeToLog("SeleniumKeywordslib","verifyText() ","'"+ text+ "' =>Object Text doesnt contain  "+ inputTxt);
									status = "false";
									extentTestReport.log(LogStatus.FAIL, objName +" does not contains " +upToNCharacters);
									return status;
								}
							} 
							else if (matchType.equalsIgnoreCase("not contains")) {
								if (text.trim().contains(inputTxt.trim())||(inputTxt.trim().contains(text.trim()))) {
									CucumberTestAutomationLogger.writeToLog("SeleniumKeywordslib","verifyText() ","'"+ text+ "' =>Object Text contains  "+ inputTxt);
									status = "true";
									extentTestReport.log(LogStatus.FAIL, objName +" contains " +upToNCharacters);
									return status;
								} else {
									CucumberTestAutomationLogger.writeToLog("SeleniumKeywordslib","verifyText() ","'"+ text+ "' =>Object Text doesnt contain  "+ inputTxt);
									status = "false";
									extentTestReport.log(LogStatus.PASS, objName +" does not contains " +upToNCharacters);
									return status;
								}
							}
							else {
							CucumberTestAutomationLogger.writeToLog("SeleniumKeywordslib","verifyText() ", "'" + objName+ "text field  was not visible");
							status = "false";
							extentTestReport.log(LogStatus.FAIL, objName +" does not match with  " +upToNCharacters);
							return status;
						}
							
					}
						
				}

			//} 
	//}
					

	}
			return status;
		}
		catch (Exception ex) {
            try{
                ex.printStackTrace();
                String err[]=ex.getMessage().split("\n");
                CucumberTestAutomationLogger.writeToLog("Input", "input()", " Text not found exeption thrown");
                status = "Exception " +err[0].replaceAll("'", "") + " Occurred";
                return status;
            }

		finally
			{
				 extentTestReport.log(LogStatus.FAIL, " "+inputTxt +"  from " +objName +" dropdown");
		         extentTestReport.log(LogStatus.FAIL, extentTestReport.addScreenCapture(ExtentReportManager.CaptureScreen(webDriver, propertyFileReader.getValue("SNAPSHOT_FOLDER")))); 
		    	// extentReports.endTest(extentTestReport);
		    	// extentReports.flush();
		         //extentReports.close(); 
		        webDriver.close();
		         //webDriver.quit();
		         
			}
            
		}
		//return status;
		
	  }
	
	/*
	 * ##########################################################################
	 * ################### ' FUNCTION NAME - selectCheckbox() ' DESCRIPTION -
	 * This function will Select/Deselect the CHECK BOX . ' INPUT PARAMETERS -
	 * Obj - Name of the Object
	 * '################################################################################################
	 */

	public String selectCheckbox(WebDriver webDriver, WebElement objName,String checkboxData,ExtentReports extentReports,ExtentTest extentTestReport) {
		String status = "false";
		String selValue ="true";
		String selectorvalue ="";
		WebElement element = objName;
		try {
			String[] paramvalues= checkboxData.split(",");
			
			for(int i=0;i<paramvalues.length;i++)
			{
			selectorvalue = "//*[text()="+ "'"+paramvalues[i] +"'"+"]";
			CucumberTestAutomationLogger.writeToLog("SeleniumKeywordslib", "selectCheckbox() ","web elements size - " );			
			if (element.isDisplayed()) {

					if (selValue.equalsIgnoreCase("true")) {
						if (element.isSelected()) {
							extentTestReport.log(LogStatus.PASS, "click on "+paramvalues[i]+" Checkbox");
							CucumberTestAutomationLogger.writeToLog("SeleniumKeywordslib","selectCheckbox() ", "'" + objName+ "' => Checkbox is already selected");
							status ="true";
						} else {
							element.click();
							extentTestReport.log(LogStatus.PASS, "click on "+paramvalues[i]+" Checkbox");
							CucumberTestAutomationLogger.writeToLog("SeleniumKeywordslib","selectCheckbox() ", "'" + objName+ "' => Checkbox is  checked");
							status ="true";
						}
					} else {
						if (element.isSelected()) {
							element.click();
							extentTestReport.log(LogStatus.PASS, "click on "+paramvalues[i]+" Checkbox");
							CucumberTestAutomationLogger.writeToLog("SeleniumKeywordslib","selectCheckbox() ", "'" + objName+ "' => Checkbox is unchecked");
							status ="true";
						} else {
							extentTestReport.log(LogStatus.PASS, "'+element+'" +"Checkbox is already selected");
							CucumberTestAutomationLogger.writeToLog("SeleniumKeywordslib","selectCheckbox() ", "'" + objName+ "' => Checkbox is already unchecked");
							status ="true";
						}

					}

				} else {
					extentTestReport.log(LogStatus.FAIL, "click on "+paramvalues[i]+" Checkbox");
					CucumberTestAutomationLogger.writeToLog("SeleniumKeywordslib","selectCheckbox() ", "'" + objName+ "CheckBox was not visible");
					status = "CheckBox was not visible";
					return status;
				}

			}
			
			if(paramvalues.length==0)
			{
				extentTestReport.log(LogStatus.FAIL, "click on "+objName+" Checkbox");
				CucumberTestAutomationLogger.writeToLog("SeleniumKeywordslib","selectCheckbox() ", "'" + objName+ " CheckBox was not found");
				status = "CheckBox was not found";
			}
				
				return status;
			
		
		}catch (NoSuchElementException ns) {
            ns.printStackTrace();
            CucumberTestAutomationLogger.writeToLog("SeleniumKeywordslib", "selectCheckbox() ","Object not found exeption thrown");
            CucumberTestAutomationLogger.writeToLog("SeleniumKeywordslib", "selectCheckbox() ", "'" + objName + "'object does not exist");
            status = "false";
            extentTestReport.log(LogStatus.FAIL, "Unable to select " +objName+ "Checkbox");
            return status;
     } catch (Exception ex) {
     	try
     	{
            ex.printStackTrace();
            CucumberTestAutomationLogger.writeToLog("SeleniumKeywordslib", "selectCheckbox() ","Exception occurred");
            extentTestReport.log(LogStatus.FAIL,"select " +objName+ "Checkbox");
            String err[]=ex.getMessage().split("\n");
			status = "Exception " +err[0].replaceAll("'", "") + " Exception Occurred";
			return status;
     	}
     	finally
     	{
     		     extentTestReport.log(LogStatus.FAIL, "Unable to set " +objName+ "Checkbox");
		         extentTestReport.log(LogStatus.FAIL, extentTestReport.addScreenCapture(ExtentReportManager.CaptureScreen(webDriver, propertyFileReader.getValue("SNAPSHOT_FOLDER")))); 
		    	// extentReports.endTest(extentTestReport);
		    	// extentReports.flush();
		         //extentReports.close(); 
		        webDriver.close();
		         //webDriver.quit();
		         
     	}
           
     }
     
	}
	/*
	 * ##########################################################################
	 * ################### ' FUNCTION NAME - selectCheckbox() ' DESCRIPTION -
	 * This function will Select/Deselect the CHECK BOX . ' INPUT PARAMETERS -
	 * Obj - Name of the Object
	 * '################################################################################################
	 */

	public String selectMultilpleCheckboxesbyText(WebDriver webDriver, WebElement objName,String selectorvalue,ExtentReports extentReports,ExtentTest extentTestReport) {
		String status = "false";
		String selValue ="true";
		WebElement element = objName;
		try {
			String[] paramvalues= selectorvalue.split(",");
			
			for(int i=0;i<paramvalues.length;i++)
			{
			selectorvalue = "//*[contains(text(),"+ "'"+paramvalues[i] +"'"+")]//preceding::input[1]";
			CucumberTestAutomationLogger.writeToLog("SeleniumKeywordslib", "selectCheckbox() ","web elements size - " );
			if (element.isDisplayed()) {

					if (selValue.equalsIgnoreCase("true")) {
						if (element.isSelected()) {
							extentTestReport.log(LogStatus.PASS, "click on "+paramvalues[i]+" Checkbox");
							CucumberTestAutomationLogger.writeToLog("SeleniumKeywordslib","selectCheckbox() ", "'" + objName+ "' => Checkbox is already selected");
							status ="true";
						} else {
							element.click();
							extentTestReport.log(LogStatus.PASS, "click on "+paramvalues[i]+" Checkbox");
							CucumberTestAutomationLogger.writeToLog("SeleniumKeywordslib","selectCheckbox() ", "'" + objName+ "' => Checkbox is  checked");
							status ="true";
						}
					} else {
						if (element.isSelected()) {
							element.click();
							extentTestReport.log(LogStatus.PASS, "click on "+paramvalues[i]+" Checkbox");
							CucumberTestAutomationLogger.writeToLog("SeleniumKeywordslib","selectCheckbox() ", "'" + objName+ "' => Checkbox is unchecked");
							status ="true";
						} else {
							extentTestReport.log(LogStatus.PASS, "'+element+'" +"Checkbox is already selected");
							CucumberTestAutomationLogger.writeToLog("SeleniumKeywordslib","selectCheckbox() ", "'" + objName+ "' => Checkbox is already unchecked");
							status ="true";
						}

					}

				} else {
					extentTestReport.log(LogStatus.FAIL, "click on "+paramvalues[i]+" Checkbox");
					CucumberTestAutomationLogger.writeToLog("SeleniumKeywordslib","selectCheckbox() ", "'" + objName+ "CheckBox was not visible");
					status = "CheckBox was not visible";
					return status;
				}

			//}
			
			//}
			}
			
			if(paramvalues.length==0)
			{
				extentTestReport.log(LogStatus.FAIL, "click on "+objName+" Checkbox");
				CucumberTestAutomationLogger.writeToLog("SeleniumKeywordslib","selectCheckbox() ", "'" + objName+ " CheckBox was not found");
				status = "CheckBox was not found";
			}
				
				return status;
			
		
		}catch (NoSuchElementException ns) {
            ns.printStackTrace();
            CucumberTestAutomationLogger.writeToLog("SeleniumKeywordslib", "selectCheckbox() ","Object not found exeption thrown");
            CucumberTestAutomationLogger.writeToLog("SeleniumKeywordslib", "selectCheckbox() ", "'" + objName + "'object does not exist");
            status = "false";
            extentTestReport.log(LogStatus.FAIL, "Unable to select " +objName+ "Checkbox");
            return status;
     } catch (Exception ex) {
     	try
     	{
            ex.printStackTrace();
            CucumberTestAutomationLogger.writeToLog("SeleniumKeywordslib", "selectCheckbox() ","Exception occurred");
            extentTestReport.log(LogStatus.FAIL,"select " +objName+ "Checkbox");
            String err[]=ex.getMessage().split("\n");
			status = "Exception " +err[0].replaceAll("'", "") + " Exception Occurred";
			return status;
     	}
     	finally
     	{
     		     extentTestReport.log(LogStatus.FAIL, "Unable to set " +objName+ "Checkbox");
		         extentTestReport.log(LogStatus.FAIL, extentTestReport.addScreenCapture(ExtentReportManager.CaptureScreen(webDriver, propertyFileReader.getValue("SNAPSHOT_FOLDER")))); 
		    	// extentReports.endTest(extentTestReport);
		    	// extentReports.flush();
		         //extentReports.close(); 
		        webDriver.close();
		         //webDriver.quit();
		         
     	}
           
     }
     
	}
	/*
	 * ##########################################################################
	 * ################### ' FUNCTION NAME - selectCheckbox() ' DESCRIPTION -
	 * This function will Select/Deselect the CHECK BOX . ' INPUT PARAMETERS -
	 * Obj - Name of the Object
	 * '################################################################################################
	 */

	public String selectMultilpleCheckboxes(WebDriver webDriver, WebElement objName,String selectorvalue,ExtentReports extentReports,ExtentTest extentTestReport) {
		String status = "false";
		String selValue ="true";
		WebElement element = objName;
		try {
			String[] paramvalues= selectorvalue.split(",");
			
			for(int i=0;i<paramvalues.length;i++)
			{
			selectorvalue = "//label[contains(text(),"+ "'"+paramvalues[i] +"'"+")]";
			CucumberTestAutomationLogger.writeToLog("SeleniumKeywordslib", "selectCheckbox() ","web elements size - " );
			if (element.isDisplayed()) {

					if (selValue.equalsIgnoreCase("true")) {
						if (element.isSelected()) {
							extentTestReport.log(LogStatus.PASS, "click on "+paramvalues[i]+" Checkbox");
							CucumberTestAutomationLogger.writeToLog("SeleniumKeywordslib","selectCheckbox() ", "'" + objName+ "' => Checkbox is already selected");
							status ="true";
						} else {
							element.click();
							extentTestReport.log(LogStatus.PASS, "click on "+paramvalues[i]+" Checkbox");
							CucumberTestAutomationLogger.writeToLog("SeleniumKeywordslib","selectCheckbox() ", "'" + objName+ "' => Checkbox is  checked");
							status ="true";
						}
					} else {
						if (element.isSelected()) {
							element.click();
							extentTestReport.log(LogStatus.PASS, "click on "+paramvalues[i]+" Checkbox");
							CucumberTestAutomationLogger.writeToLog("SeleniumKeywordslib","selectCheckbox() ", "'" + objName+ "' => Checkbox is unchecked");
							status ="true";
						} else {
							extentTestReport.log(LogStatus.PASS, "'+element+'" +"Checkbox is already selected");
							CucumberTestAutomationLogger.writeToLog("SeleniumKeywordslib","selectCheckbox() ", "'" + objName+ "' => Checkbox is already unchecked");
							status ="true";
						}

					}

				} else {
					extentTestReport.log(LogStatus.FAIL, "click on "+paramvalues[i]+" Checkbox");
					CucumberTestAutomationLogger.writeToLog("SeleniumKeywordslib","selectCheckbox() ", "'" + objName+ "CheckBox was not visible");
					status = "CheckBox was not visible";
					return status;
				}

			//}
			
			//}
			}
			
			if(paramvalues.length==0)
			{
				extentTestReport.log(LogStatus.FAIL, "click on "+objName+" Checkbox");
				CucumberTestAutomationLogger.writeToLog("SeleniumKeywordslib","selectCheckbox() ", "'" + objName+ " CheckBox was not found");
				status = "CheckBox was not found";
			}
				
				return status;
			
		
		}catch (NoSuchElementException ns) {
            ns.printStackTrace();
            CucumberTestAutomationLogger.writeToLog("SeleniumKeywordslib", "selectCheckbox() ","Object not found exeption thrown");
            CucumberTestAutomationLogger.writeToLog("SeleniumKeywordslib", "selectCheckbox() ", "'" + objName + "'object does not exist");
            status = "false";
            extentTestReport.log(LogStatus.FAIL, "Unable to select " +objName+ "Checkbox");
            return status;
     } catch (Exception ex) {
     	try
     	{
            ex.printStackTrace();
            CucumberTestAutomationLogger.writeToLog("SeleniumKeywordslib", "selectCheckbox() ","Exception occurred");
            extentTestReport.log(LogStatus.FAIL,"select " +objName+ "Checkbox");
            String err[]=ex.getMessage().split("\n");
			status = "Exception " +err[0].replaceAll("'", "") + " Exception Occurred";
			return status;
     	}
     	finally
     	{
     		     extentTestReport.log(LogStatus.FAIL, "Unable to set " +objName+ "Checkbox");
		         extentTestReport.log(LogStatus.FAIL, extentTestReport.addScreenCapture(ExtentReportManager.CaptureScreen(webDriver, propertyFileReader.getValue("SNAPSHOT_FOLDER")))); 
		    	// extentReports.endTest(extentTestReport);
		    	// extentReports.flush();
		         //extentReports.close(); 
		        webDriver.close();
		         //webDriver.quit();
		         
     	}
           
     }
     
	}
	/*
	 * ##########################################################################
	 * ################### ' FUNCTION NAME - selectCheckbox() ' DESCRIPTION -
	 * This function will Select/Deselect the CHECK BOX . ' INPUT PARAMETERS -
	 * Obj - Name of the Object
	 * '################################################################################################
	 */

	public String unSelectMultilpleCheckboxes(WebDriver webDriver, WebElement objName,String selectorvalue,ExtentReports extentReports,ExtentTest extentTestReport) {
		String status = "false";
		String selValue ="true";
		WebElement element = objName;
		try {
			String[] paramvalues= selectorvalue.split(",");
			
			for(int i=0;i<paramvalues.length;i++)
			{
			selectorvalue = "//label[contains(text(),"+ "'"+" "+paramvalues[i].trim() +"'"+")]";
			CucumberTestAutomationLogger.writeToLog("SeleniumKeywordslib", "selectCheckbox() ","web elements size - ");
			
				if (element.isDisplayed()) {

					if (selValue.equalsIgnoreCase("true")) {
						if (element.isSelected()) {
							extentTestReport.log(LogStatus.PASS, "click on "+paramvalues[i]+" Checkbox");
							CucumberTestAutomationLogger.writeToLog("SeleniumKeywordslib","selectCheckbox() ", "'" + objName+ "' => Checkbox is already selected");
							status ="true";
						} else {
							element.click();
							extentTestReport.log(LogStatus.PASS, "click on "+paramvalues[i]+" Checkbox");
							CucumberTestAutomationLogger.writeToLog("SeleniumKeywordslib","selectCheckbox() ", "'" + objName+ "' => Checkbox is  checked");
							status ="true";
						}
					} else {
						if (element.isSelected()) {
							element.click();
							extentTestReport.log(LogStatus.PASS, "click on "+paramvalues[i]+" Checkbox");
							CucumberTestAutomationLogger.writeToLog("SeleniumKeywordslib","selectCheckbox() ", "'" + objName+ "' => Checkbox is unchecked");
							status ="true";
						} else {
							extentTestReport.log(LogStatus.PASS, "'+element+'" +"Checkbox is already selected");
							CucumberTestAutomationLogger.writeToLog("SeleniumKeywordslib","selectCheckbox() ", "'" + objName+ "' => Checkbox is already unchecked");
							status ="true";
						}

					}

				} else {
					extentTestReport.log(LogStatus.FAIL, "click on "+paramvalues[i]+" Checkbox");
					CucumberTestAutomationLogger.writeToLog("SeleniumKeywordslib","selectCheckbox() ", "'" + objName+ "CheckBox was not visible");
					status = "CheckBox was not visible";
					return status;
				}

		//	}
			
			//}
			}
			
			if(paramvalues.length==0)
			{
				extentTestReport.log(LogStatus.FAIL, "click on "+objName+" Checkbox");
				CucumberTestAutomationLogger.writeToLog("SeleniumKeywordslib","selectCheckbox() ", "'" + objName+ " CheckBox was not found");
				status = "CheckBox was not found";
			}
				
				return status;
			
		
		}catch (NoSuchElementException ns) {
            ns.printStackTrace();
            CucumberTestAutomationLogger.writeToLog("SeleniumKeywordslib", "selectCheckbox() ","Object not found exeption thrown");
            CucumberTestAutomationLogger.writeToLog("SeleniumKeywordslib", "selectCheckbox() ", "'" + objName + "'object does not exist");
            status = "false";
            extentTestReport.log(LogStatus.FAIL, "Unable to select " +objName+ "Checkbox");
            return status;
     } catch (Exception ex) {
     	try
     	{
            ex.printStackTrace();
            CucumberTestAutomationLogger.writeToLog("SeleniumKeywordslib", "selectCheckbox() ","Exception occurred");
            extentTestReport.log(LogStatus.FAIL,"select " +objName+ "Checkbox");
            String err[]=ex.getMessage().split("\n");
			status = "Exception " +err[0].replaceAll("'", "") + " Exception Occurred";
			return status;
     	}
     	finally
     	{
     		     extentTestReport.log(LogStatus.FAIL, "Unable to set " +objName+ "Checkbox");
		         extentTestReport.log(LogStatus.FAIL, extentTestReport.addScreenCapture(ExtentReportManager.CaptureScreen(webDriver, propertyFileReader.getValue("SNAPSHOT_FOLDER")))); 
		    	// extentReports.endTest(extentTestReport);
		    	// extentReports.flush();
		         //extentReports.close(); 
		        webDriver.close();
		         //webDriver.quit();
		         
     	}
           
     }
     
	}
	
	/*
	 * ##########################################################################
	 * ################### ' FUNCTION NAME - selectCheckbox() ' DESCRIPTION -
	 * This function will Select/Deselect the CHECK BOX . ' INPUT PARAMETERS -
	 * Obj - Name of the Object
	 * '################################################################################################
	 */

	public String unselectCheckboxByObj(WebDriver webDriver, WebElement objName,ExtentReports extentReports,ExtentTest extentTestReport) {
		String status = "false";
		String selValue ="true";
		WebElement element = objName;
		try {
			CucumberTestAutomationLogger.writeToLog("SeleniumKeywordslib", "selectCheckbox() ","web elements size - ");
			if (element.isDisplayed()) {

					if (selValue.equalsIgnoreCase("true")) {
						if (element.isSelected()) {
							element.click();
							extentTestReport.log(LogStatus.PASS, "click on "+objName+" Checkbox");
							CucumberTestAutomationLogger.writeToLog("SeleniumKeywordslib","selectCheckbox() ", "'" + objName+ "' => Checkbox is already selected");
							status ="true";
						} else {
							element.click();
							extentTestReport.log(LogStatus.PASS, "click on "+objName+" Checkbox");
							CucumberTestAutomationLogger.writeToLog("SeleniumKeywordslib","selectCheckbox() ", "'" + objName+ "' => Checkbox is  checked");
							status ="true";
						}
					} else {
						if (element.isSelected()) {
							element.click();
							extentTestReport.log(LogStatus.PASS, "click on "+objName+" Checkbox");
							CucumberTestAutomationLogger.writeToLog("SeleniumKeywordslib","selectCheckbox() ", "'" + objName+ "' => Checkbox is unchecked");
							status ="true";
						} else {
							extentTestReport.log(LogStatus.PASS, "'+element+'" +"Checkbox is already selected");
							CucumberTestAutomationLogger.writeToLog("SeleniumKeywordslib","selectCheckbox() ", "'" + objName+ "' => Checkbox is already unchecked");
							status ="true";
						}

					}

				} else {
					extentTestReport.log(LogStatus.FAIL, "click on "+objName+" Checkbox");
					CucumberTestAutomationLogger.writeToLog("SeleniumKeywordslib","selectCheckbox() ", "'" + objName+ "CheckBox was not visible");
					status = "CheckBox was not visible";
					return status;
				}

			
				return status;
			
		
		}catch (NoSuchElementException ns) {
            ns.printStackTrace();
            CucumberTestAutomationLogger.writeToLog("SeleniumKeywordslib", "selectCheckbox() ","Object not found exeption thrown");
            CucumberTestAutomationLogger.writeToLog("SeleniumKeywordslib", "selectCheckbox() ", "'" + objName + "'object does not exist");
            status = "false";
            extentTestReport.log(LogStatus.FAIL, "Unable to select " +objName+ "Checkbox");
            return status;
     } catch (Exception ex) {
     	try
     	{
            ex.printStackTrace();
            CucumberTestAutomationLogger.writeToLog("SeleniumKeywordslib", "selectCheckbox() ","Exception occurred");
            extentTestReport.log(LogStatus.FAIL,"select " +objName+ "Checkbox");
            String err[]=ex.getMessage().split("\n");
			status = "Exception " +err[0].replaceAll("'", "") + " Exception Occurred";
			return status;
     	}
     	finally
     	{
     		     extentTestReport.log(LogStatus.FAIL, "Unable to set " +objName+ "Checkbox");
		         extentTestReport.log(LogStatus.FAIL, extentTestReport.addScreenCapture(ExtentReportManager.CaptureScreen(webDriver, propertyFileReader.getValue("SNAPSHOT_FOLDER")))); 
		    	// extentReports.endTest(extentTestReport);
		    	// extentReports.flush();
		         //extentReports.close(); 
		        webDriver.close();
		         //webDriver.quit();
		         
     	}
           
     }
     
	}
	/*
	 * ##########################################################################
	 * ################### ' FUNCTION NAME - selectCheckbox() ' DESCRIPTION -
	 * This function will Select/Deselect the CHECK BOX . ' INPUT PARAMETERS -
	 * Obj - Name of the Object
	 * '################################################################################################
	 */

	public String unselectCheckboxesbyObj(WebDriver webDriver, WebElement objName,ExtentReports extentReports,ExtentTest extentTestReport) {
		String status = "false";
		String selValue ="true";
		WebElement element=objName;
		try {
			CucumberTestAutomationLogger.writeToLog("SeleniumKeywordslib", "selectCheckbox() ","web elements size - " );
				if (element.isDisplayed()) {

					if (selValue.equalsIgnoreCase("true")) {
						if (element.isSelected()) {
							element.click();
							extentTestReport.log(LogStatus.PASS, "click on "+objName+" Checkbox");
							CucumberTestAutomationLogger.writeToLog("SeleniumKeywordslib","selectCheckbox() ", "'" + objName+ "' => Checkbox is already selected");
							status ="true";
						} else {
							element.click();
							extentTestReport.log(LogStatus.PASS, "click on "+objName+" Checkbox");
							CucumberTestAutomationLogger.writeToLog("SeleniumKeywordslib","selectCheckbox() ", "'" + objName+ "' => Checkbox is  checked");
							status ="true";
						}
					} else {
						if (element.isSelected()) {
							element.click();
							extentTestReport.log(LogStatus.PASS, "click on "+objName+" Checkbox");
							CucumberTestAutomationLogger.writeToLog("SeleniumKeywordslib","selectCheckbox() ", "'" + objName+ "' => Checkbox is unchecked");
							status ="true";
						} else {
							extentTestReport.log(LogStatus.PASS, "'+element+'" +"Checkbox is already selected");
							CucumberTestAutomationLogger.writeToLog("SeleniumKeywordslib","selectCheckbox() ", "'" + objName+ "' => Checkbox is already unchecked");
							status ="true";
						}

					}

				} else {
					extentTestReport.log(LogStatus.FAIL, "click on "+objName+" Checkbox");
					CucumberTestAutomationLogger.writeToLog("SeleniumKeywordslib","selectCheckbox() ", "'" + objName+ "CheckBox was not visible");
					status = "CheckBox was not visible";
					return status;
				}
	
				return status;
			
		
		}catch (NoSuchElementException ns) {
            ns.printStackTrace();
            CucumberTestAutomationLogger.writeToLog("SeleniumKeywordslib", "selectCheckbox() ","Object not found exeption thrown");
            CucumberTestAutomationLogger.writeToLog("SeleniumKeywordslib", "selectCheckbox() ", "'" + objName + "'object does not exist");
            status = "false";
            extentTestReport.log(LogStatus.FAIL, "Unable to select " +objName+ "Checkbox");
            return status;
     } catch (Exception ex) {
     	try
     	{
            ex.printStackTrace();
            CucumberTestAutomationLogger.writeToLog("SeleniumKeywordslib", "selectCheckbox() ","Exception occurred");
            extentTestReport.log(LogStatus.FAIL,"select " +objName+ "Checkbox");
            String err[]=ex.getMessage().split("\n");
			status = "Exception " +err[0].replaceAll("'", "") + " Exception Occurred";
			return status;
     	}
     	finally
     	{
     		     extentTestReport.log(LogStatus.FAIL, "Unable to set " +objName+ "Checkbox");
		         extentTestReport.log(LogStatus.FAIL, extentTestReport.addScreenCapture(ExtentReportManager.CaptureScreen(webDriver, propertyFileReader.getValue("SNAPSHOT_FOLDER")))); 
		    	// extentReports.endTest(extentTestReport);
		    	// extentReports.flush();
		         //extentReports.close(); 
		        webDriver.close();
		         //webDriver.quit();
		         
     	}
           
     }
     
	}
	/*
	 * ##########################################################################
	 * ################### ' FUNCTION NAME - selectCheckbox() ' DESCRIPTION -
	 * This function will Select/Deselect the CHECK BOX . ' INPUT PARAMETERS -
	 * Obj - Name of the Object
	 * '################################################################################################
	 */

	public String selectScoreRadio(WebDriver webDriver, WebElement objName,String radio,String selectorvalue,ExtentReports extentReports,ExtentTest extentTestReport) {
		String status = "false";
		String selValue ="true";
		WebElement element = objName;
		try {
			
			selectorvalue = "//*[contains(text(),"+ "'"+radio +"'"+")]//following::input[1]";
			CucumberTestAutomationLogger.writeToLog("SeleniumKeywordslib", "selectCheckbox() ","web elements size - ");
			if (element.isDisplayed()) {

					if (selValue.equalsIgnoreCase("true")) {
						if (element.isSelected()) {
							extentTestReport.log(LogStatus.PASS, "click on "+radio+" radio");
							CucumberTestAutomationLogger.writeToLog("SeleniumKeywordslib","selectCheckbox() ", "'" + objName+ "' => Checkbox is already selected");
							status ="true";
						} else {
							element.click();
							extentTestReport.log(LogStatus.PASS, "click on "+radio+" radio");
							CucumberTestAutomationLogger.writeToLog("SeleniumKeywordslib","selectCheckbox() ", "'" + objName+ "' => Checkbox is  checked");
							status ="true";
						}
					} else {
						if (element.isSelected()) {
							element.click();
							extentTestReport.log(LogStatus.PASS, "click on "+radio+" radio");
							CucumberTestAutomationLogger.writeToLog("SeleniumKeywordslib","selectCheckbox() ", "'" + objName+ "' => Checkbox is unchecked");
							status ="true";
						} else {
							extentTestReport.log(LogStatus.PASS, "'+element+'" +"Checkbox is already selected");
							CucumberTestAutomationLogger.writeToLog("SeleniumKeywordslib","selectCheckbox() ", "'" + objName+ "' => Checkbox is already unchecked");
							status ="true";
						}

					}

				} else {
					extentTestReport.log(LogStatus.FAIL, "click on "+radio+" Checkbox");
					CucumberTestAutomationLogger.writeToLog("SeleniumKeywordslib","selectCheckbox() ", "'" + objName+ "CheckBox was not visible");
					status = "CheckBox was not visible";
					return status;
				}

			return status;
			
		
		}catch (NoSuchElementException ns) {
            ns.printStackTrace();
            CucumberTestAutomationLogger.writeToLog("SeleniumKeywordslib", "selectCheckbox() ","Object not found exeption thrown");
            CucumberTestAutomationLogger.writeToLog("SeleniumKeywordslib", "selectCheckbox() ", "'" + objName + "'object does not exist");
            status = "false";
            extentTestReport.log(LogStatus.FAIL, "Unable to select " +objName+ "Checkbox");
            return status;
     } catch (Exception ex) {
     	try
     	{
            ex.printStackTrace();
            CucumberTestAutomationLogger.writeToLog("SeleniumKeywordslib", "selectCheckbox() ","Exception occurred");
            extentTestReport.log(LogStatus.FAIL,"select " +objName+ "Checkbox");
            String err[]=ex.getMessage().split("\n");
			status = "Exception " +err[0].replaceAll("'", "") + " Exception Occurred";
			return status;
     	}
     	finally
     	{
     		     extentTestReport.log(LogStatus.FAIL, "Unable to set " +objName+ "Checkbox");
		         extentTestReport.log(LogStatus.FAIL, extentTestReport.addScreenCapture(ExtentReportManager.CaptureScreen(webDriver, propertyFileReader.getValue("SNAPSHOT_FOLDER")))); 
		    	// extentReports.endTest(extentTestReport);
		    	// extentReports.flush();
		         //extentReports.close(); 
		        webDriver.close();
		         //webDriver.quit();
		         
     	}
           
     }
     
	}
	 /*
	 * ##########################################################################
	 * ##################### ' FUNCTION NAME - selectRadioButton() '
	 * DESCRIPTION - This function will Select the Radio button . ' INPUT
	 * PARAMETERS - Obj - Name of the Object
	 * '################################################################################################
	 */

			public String selectRadioButton(WebDriver webDriver, String objName,String selector,String selectorvalue,ExtentReports extentReports,ExtentTest extentTestReport) {
				String status = "false";
				try {
				    waitForExistence(webDriver, objName, selector,selectorvalue);
					List<WebElement> list = getElements(webDriver, objName, selector,selectorvalue);
					CucumberTestAutomationLogger.writeToLog("SeleniumKeywordslib","selectRadioButton()","web elements size - " + list.size());
					if (list.size() > 1) {
						CucumberTestAutomationLogger.writeToLog("SeleniumKeywordslib","selectRadioButton()","found more than one element");
						CucumberTestAutomationLogger.writeToLog("SeleniumKeywordslib","selectRadioButton()", "clicking 1st element");
						for (WebElement element : list) {
							if ((element.isDisplayed()) && (!element.isSelected())){
								webDriver.manage().timeouts().pageLoadTimeout(60, TimeUnit.SECONDS);
									element.click();
									webDriver.manage().timeouts().pageLoadTimeout(60, TimeUnit.SECONDS);
									status = "true";
									extentTestReport.log(LogStatus.PASS, "click on " +objName +" radio button");
									CucumberTestAutomationLogger.writeToLog("SeleniumKeywordslib","selectRadioButton()", "after click");
									CucumberTestAutomationLogger.writeToLog("SeleniumKeywordslib","selectRadioButton()","'"+ objName + "' => Radio Button was successfully clicked");
									
								} 
							else if ((element.isDisplayed()) && (element.isSelected())){
								webDriver.manage().timeouts().pageLoadTimeout(60, TimeUnit.SECONDS);
									element.click();
									webDriver.manage().timeouts().pageLoadTimeout(60, TimeUnit.SECONDS);
									status = "true";
									extentTestReport.log(LogStatus.PASS, "click on " +objName +" radio button");
									CucumberTestAutomationLogger.writeToLog("SeleniumKeywordslib","selectRadioButton()", "after click");
									CucumberTestAutomationLogger.writeToLog("SeleniumKeywordslib","selectRadioButton()","'"+ objName + "' => Radio Button was successfully clicked");
									//break;
								} 
							else {
									CucumberTestAutomationLogger.writeToLog("SeleniumKeywordslib","selectRadioButton()","'"+ objName + "' Radio Button was not visible_______");
								//	element.sendKeys(Keys.ENTER);
		                            status = "false";
									CucumberTestAutomationLogger.writeToLog("SeleniumKeywordslib","selectRadioButton()", "after click");
									//extentTestReport.log(LogStatus.FAIL, objName +" radio button is not found");
									return status;
								}
								
							} 
						return status;
					}	
					 else if (list.size() == 1) {
						CucumberTestAutomationLogger.writeToLog("SeleniumKeywordslib","selectRadioButton()", "clicking element");
						WebElement element = list.get(0);

						if (element != null) {
							if (element.isDisplayed()) {
								element.click();
								//list.get(0).click();
								//int waitTime = (int) findObjTime;
								webDriver.manage().timeouts().pageLoadTimeout(60, TimeUnit.SECONDS);
								status = "true";
								CucumberTestAutomationLogger.writeToLog("SeleniumKeywordslib","selectRadioButton()","'"+ objName+ "' => Radio Button was successfully selected");
								CucumberTestAutomationLogger.writeToLog("SeleniumKeywordslib","selectRadioButton()", "after click");
								extentTestReport.log(LogStatus.PASS, "click on " +objName +"radio button");
								return status;
							} else {
								CucumberTestAutomationLogger.writeToLog("SeleniumKeywordslib","selectRadioButton()","'"+ objName + "' Radio Button was not visible******");
								element.sendKeys(Keys.ENTER);
								status = "true";
								extentTestReport.log(LogStatus.FAIL, objName +" radio button is not found");
								return status;
							}
						} else {
							CucumberTestAutomationLogger.writeToLog("SeleniumKeywordslib","selectRadioButton()", "'" + objName + "' Radio Button was not found");
							status = "Radio Button was not found";
							extentTestReport.log(LogStatus.FAIL, objName +" radio button is not found");
							return status;
						}
					} else {
						CucumberTestAutomationLogger.writeToLog("SeleniumKeywordslib","selectRadioButton()", "'" + objName+ "' Radio Button was not selected");
						status = "Radio Button was not selected";
						extentTestReport.log(LogStatus.FAIL, objName +" radio button is not found");
						return status;
					}

				}  catch (Exception ex) {
					try
					{
						ex.printStackTrace();
						CucumberTestAutomationLogger.writeToLog("SeleniumKeywordslib","selectRadioButton()", "Exception occurred");
						CucumberTestAutomationLogger.writeToLog("SeleniumKeywordslib","selectRadioButton()", "Exception '" + ex.getMessage()+ "' Occurred");
						String err[]=ex.getMessage().split("\n");
						status =  "Exception " +err[0].replaceAll("'", "") + " Occurred";
						return status;
					}
					finally
					{
						 extentTestReport.log(LogStatus.FAIL, objName +" radio button is not found");
				         extentTestReport.log(LogStatus.FAIL, extentTestReport.addScreenCapture(ExtentReportManager.CaptureScreen(webDriver, propertyFileReader.getValue("SNAPSHOT_FOLDER")))); 
				    	// extentReports.endTest(extentTestReport);
				    	// extentReports.flush();
				         //extentReports.close(); 
				        webDriver.close();
				         //webDriver.quit();
				         
					}
				}
			}
			
				
		/*
		 * ##########################################################################
		 * ################### ' FUNCTION NAME - unSelectCheckbox() ' DESCRIPTION -
		 * This function will Select/Deselect the CHECK BOX . ' INPUT PARAMETERS -
		 * Obj - Name of the Object
		 * '################################################################################################
		 */

		public String unSelectCheckbox(WebDriver webDriver, WebElement objName,String selectorvalue,ExtentReports extentReports,ExtentTest extentTestReport) {
			String status = "false";
			String selValue ="false";
			List<String> dropList = Arrays.asList(selectorvalue.split(","));
			WebElement element = objName;
			try {
				
				if(dropList.size()>1)
				{
				for (String dropDownValue : dropList) {
				selectorvalue = "//*[contains(text(),"+ "'"+dropDownValue +"'"+")]//preceding::input[1]";
				CucumberTestAutomationLogger.writeToLog("SeleniumKeywordslib", "unSelectCheckbox() ","web elements size - ");
					selectorvalue = "//*[contains(text(),"+ "'"+dropList +"'"+")]//preceding::input[1]";
					if (element.isDisplayed()) {

						if (selValue.equalsIgnoreCase("true")) {
							if (element.isSelected()) {
								extentTestReport.log(LogStatus.PASS, "click on "+objName +"checkbox");
								CucumberTestAutomationLogger.writeToLog("SeleniumKeywordslib","unSelectCheckbox() ", "'" + objName+ "' => checkbox is already selected");
								return "true";
							} else {
								element.click();
								extentTestReport.log(LogStatus.PASS, "click on "+objName +"checkbox");
								CucumberTestAutomationLogger.writeToLog("SeleniumKeywordslib","unSelectCheckbox() ", "'" + objName+ "' =>checkbox  is  checked");
								return "true";
							}
						} else {
							if (element.isSelected()) {
								element.click();
								extentTestReport.log(LogStatus.PASS, "click on "+objName +"checkbox");
								CucumberTestAutomationLogger.writeToLog("SeleniumKeywordslib","unSelectCheckbox() ", "'" + objName+ "' =>checkbox is unchecked");
								return "true";
							} else {
								extentTestReport.log(LogStatus.PASS, "'+objName+'" +"checkbox is already selected");
								CucumberTestAutomationLogger.writeToLog("SeleniumKeywordslib","unSelectCheckbox() ", "'" + objName+ "' => checkbox is already unchecked");
								return "true";
							}

						}

					} else {
						extentTestReport.log(LogStatus.FAIL, "click on "+objName +"checkbox");
						CucumberTestAutomationLogger.writeToLog("SeleniumKeywordslib","unSelectCheckbox() ", "'" + objName+ "checkBox was not visible");
						status = "CheckBox was not visible";
						return status;
					}

				} 
				}
				else if(dropList.size()==1)
				{
					if (element.isDisplayed()) {

						if (selValue.equalsIgnoreCase("true")) {
							if (element.isSelected()) {
								extentTestReport.log(LogStatus.PASS, "click on "+objName +"checkbox");
								CucumberTestAutomationLogger.writeToLog("SeleniumKeywordslib","unSelectCheckbox() ", "'" + objName+ "' => checkbox is already selected");
								return "true";
							} else {
								element.click();
								extentTestReport.log(LogStatus.PASS, "click on "+objName +"checkbox");
								CucumberTestAutomationLogger.writeToLog("SeleniumKeywordslib","unSelectCheckbox() ", "'" + objName+ "' =>checkbox  is  checked");
								return "true";
							}
						} else {
							if (element.isSelected()) {
								element.click();
								extentTestReport.log(LogStatus.PASS, "click on "+objName +"checkbox");
								CucumberTestAutomationLogger.writeToLog("SeleniumKeywordslib","unSelectCheckbox() ", "'" + objName+ "' =>checkbox is unchecked");
								return "true";
							} else {
								extentTestReport.log(LogStatus.PASS, "'+objName+'" +"checkbox is already selected");
								CucumberTestAutomationLogger.writeToLog("SeleniumKeywordslib","unSelectCheckbox() ", "'" + objName+ "' => checkbox is already unchecked");
								return "true";
							}

						}

					} else {
						extentTestReport.log(LogStatus.FAIL, "click on "+objName +"checkbox");
						CucumberTestAutomationLogger.writeToLog("SeleniumKeywordslib","unSelectCheckbox() ", "'" + objName+ "checkBox was not visible");
						status = "CheckBox was not visible";
						return status;
					}
				}
				else {
					extentTestReport.log(LogStatus.FAIL, "click on "+objName +"checkbox");
					CucumberTestAutomationLogger.writeToLog("SeleniumKeywordslib","unSelectCheckbox() ", "'" + objName+ " checkBox was not found");
					status = "CheckBox was not found";
					return status;
				}

						
			} catch (Exception ex) {
	     	try
	     	{
	            ex.printStackTrace();
	            CucumberTestAutomationLogger.writeToLog("SeleniumKeywordslib", "unSelectCheckbox() ","Exception occurred");
	            extentTestReport.log(LogStatus.FAIL,"select " +objName+ "checkbox");
	            String err[]=ex.getMessage().split("\n");
				status = "Exception " +err[0].replaceAll("'", "") + " Exception Occurred";
				return status;
	     	}
	     	finally
	     	{
	     		     extentTestReport.log(LogStatus.FAIL, "Unable to select " +objName+ "check box");
			         extentTestReport.log(LogStatus.FAIL, extentTestReport.addScreenCapture(ExtentReportManager.CaptureScreen(webDriver, propertyFileReader.getValue("SNAPSHOT_FOLDER")))); 
			    	// extentReports.endTest(extentTestReport);
			    	// extentReports.flush();
			         //extentReports.close(); 
			        webDriver.close();
			         //webDriver.quit();
			         
	     	}
	           
	     }
	     return status;
		}
	/*
 * ############################################################################################# 
 * ' FUNCTION NAME - mouseOver ' DESCRIPTION - This
 * function will be used to focus on Menu or list ' INPUT PARAMETERS -
 * '################################################################################################
 */

 public String clickByText(WebDriver webDriver, String objName,String selector,String selectorvalue,String text,ExtentReports extentReports,ExtentTest extentTestReport) {
        CucumberTestAutomationLogger.writeToLog("SeleniumKeywordslib", "clickByText() "," object name" + objName);
        String status = "false";
        try {
               
        	   String path = "//*[text()="+ "'"+text +"'"+"]";
              
               List<WebElement> list = getElements(webDriver, objName, selector,path);
             
               if (list.size() == 1) {
                     CucumberTestAutomationLogger.writeToLog("SeleniumKeywordslib", "clickByText() ", "found more than one element.");
                     Actions act = new Actions(webDriver);
                     WebElement element = list.get(0);
                    

                     if (element.isDisplayed()) {

                            act.moveToElement(element).click(element).build().perform();
                            return status;
                     } else {
                            CucumberTestAutomationLogger.writeToLog("SeleniumKeywordslib", "clickByText() ", "'" + objName + "' object was not visible");
                            status = "true";
                            extentTestReport.log(LogStatus.PASS, "click on " +objName);
                            return status;
                     }
               } 
               else if (list.size() >1) {
                     CucumberTestAutomationLogger.writeToLog("SeleniumKeywordslib", "clickByText() ", "found more than one element.");
                     Actions act = new Actions(webDriver);
                     WebElement element = list.get(0);
                    

                     if (element.isDisplayed()) {

                            act.moveToElement(element).click(element).build().perform();
                            return status;
                     } else {
                            CucumberTestAutomationLogger.writeToLog("SeleniumKeywordslib", "clickByText() ", "'" + objName + "' object was not visible");
                            status = "true";
                            extentTestReport.log(LogStatus.PASS, "click on Menu Item" +objName);
                            return status;
                     }
               } 
               else {
                     CucumberTestAutomationLogger.writeToLog("SeleniumKeywordslib","clickByText() ", "'" + objName+ "' object was not found");
                     status = "false";
                     extentTestReport.log(LogStatus.FAIL, "click on Menu Item" +objName);
                     CucumberTestAutomationLogger.writeToLog("SeleniumKeywordslib", "clickByText() ", "object not found");
                     return status;
               }

        } catch (Exception ex) {
        	try{
                ex.printStackTrace();
              // npiCTRMLogger.writeToLog("SeleniumKeywordslib", "mouseOver() ","Exception occurred");
                CucumberTestAutomationLogger.writeToLog("SeleniumKeywordslib", "clickByText() ","Exception '" + ex.getMessage() + "' Occurred");
                String err[]=ex.getMessage().split("\n");
				status = "Exception " +err[0].replaceAll("'", "") + " Exception Occurred";
				return status;
              
        	}
        	finally {
        		 extentTestReport.addScreenCapture(propertyFileReader.getValue("REPORT_PATH")+"objName");
            	 extentTestReport.log(LogStatus.FAIL, extentTestReport.addScreenCapture(ExtentReportManager.CaptureScreen(webDriver, propertyFileReader.getValue("SNAPSHOT_FOLDER")+"objName"))); 
            	// extentReports.endTest(extentTestReport);
            	// extentReports.flush();
            	 //extentReports.close(); 
        	}
        }
 }
 /*
  * ############################################################################################# 
  * ' FUNCTION NAME - mouseOver ' DESCRIPTION - This
  * function will be used to focus on Menu or list ' INPUT PARAMETERS -
  * '################################################################################################
  */

  public String clickBySpan(WebDriver webDriver, String objName,String selector,String selectorvalue,String text,ExtentReports extentReports,ExtentTest extentTestReport) {
         CucumberTestAutomationLogger.writeToLog("SeleniumKeywordslib", "clickByText() "," object name" + objName);
         String status = "false";
         try {
                
         	   String path = "//span[text()="+ "'"+text +"'"+"]//preceding::span[1]";
               
                List<WebElement> list = getElements(webDriver, objName, selector,path);
              
                if (list.size() == 1) {
                      CucumberTestAutomationLogger.writeToLog("SeleniumKeywordslib", "clickByText() ", "found more than one element.");
                      Actions act = new Actions(webDriver);
                      WebElement element = list.get(0);
                     

                      if (element.isDisplayed()) {

                             act.moveToElement(element).click(element).build().perform();
                             return status;
                      } else {
                             CucumberTestAutomationLogger.writeToLog("SeleniumKeywordslib", "clickByText() ", "'" + objName + "' object was not visible");
                             status = "true";
                             extentTestReport.log(LogStatus.PASS, "click on " +objName);
                             return status;
                      }
                } 
                else if (list.size() >1) {
                      CucumberTestAutomationLogger.writeToLog("SeleniumKeywordslib", "clickByText() ", "found more than one element.");
                      Actions act = new Actions(webDriver);
                      WebElement element = list.get(0);
                     

                      if (element.isDisplayed()) {

                             act.moveToElement(element).click(element).build().perform();
                             return status;
                      } else {
                             CucumberTestAutomationLogger.writeToLog("SeleniumKeywordslib", "clickByText() ", "'" + objName + "' object was not visible");
                             status = "true";
                             extentTestReport.log(LogStatus.PASS, "click on Menu Item" +objName);
                             return status;
                      }
                } 
                else {
                      CucumberTestAutomationLogger.writeToLog("SeleniumKeywordslib","clickByText() ", "'" + objName+ "' object was not found");
                      status = "false";
                      extentTestReport.log(LogStatus.FAIL, "click on Menu Item" +objName);
                      CucumberTestAutomationLogger.writeToLog("SeleniumKeywordslib", "clickByText() ", "object not found");
                      return status;
                }

         } catch (Exception ex) {
         	try{
                 ex.printStackTrace();
               // npiCTRMLogger.writeToLog("SeleniumKeywordslib", "mouseOver() ","Exception occurred");
                 CucumberTestAutomationLogger.writeToLog("SeleniumKeywordslib", "clickByText() ","Exception '" + ex.getMessage() + "' Occurred");
                 String err[]=ex.getMessage().split("\n");
 				status = "Exception " +err[0].replaceAll("'", "") + " Exception Occurred";
 				return status;
               
         	}
         	finally {
         		 extentTestReport.addScreenCapture(propertyFileReader.getValue("REPORT_PATH")+"objName");
             	 extentTestReport.log(LogStatus.FAIL, extentTestReport.addScreenCapture(ExtentReportManager.CaptureScreen(webDriver, propertyFileReader.getValue("SNAPSHOT_FOLDER")+"objName"))); 
             	// extentReports.endTest(extentTestReport);
             	// extentReports.flush();
             	 //extentReports.close(); 
         	}
         }
  }
 /*
  * ############################################################################################# 
  * ' FUNCTION NAME - mouseOver ' DESCRIPTION - This
  * function will be used to focus on Menu or list ' INPUT PARAMETERS -
  * '################################################################################################
  */

  public String displayValueInGUI(WebDriver webDriver, String objName,ExtentReports extentReports,ExtentTest extentTestReport) {
         CucumberTestAutomationLogger.writeToLog("SeleniumKeywordslib", "displayValueInGUI() "," object name" + objName);
        String status = "";
         try {
                
         	                 CucumberTestAutomationLogger.writeToLog("SeleniumKeywordslib", "displayValueInGUI() ", "'" + objName + "' object was visible");
                             status = "true";
                             extentTestReport.log(LogStatus.PASS, "" +objName);
                             return status;
               

         } catch (Exception ex) {
         	try{
                 ex.printStackTrace();
               // npiCTRMLogger.writeToLog("SeleniumKeywordslib", "mouseOver() ","Exception occurred");
                 CucumberTestAutomationLogger.writeToLog("SeleniumKeywordslib", "displayValueInGUI() ","Exception '" + ex.getMessage() + "' Occurred");
                 String err[]=ex.getMessage().split("\n");
 				status = "Exception " +err[0].replaceAll("'", "") + " Exception Occurred";
 				return status;
               
         	}
         	finally {
         		 extentTestReport.addScreenCapture(propertyFileReader.getValue("REPORT_PATH")+"objName");
             	 extentTestReport.log(LogStatus.FAIL, extentTestReport.addScreenCapture(ExtentReportManager.CaptureScreenForExcel(webDriver, propertyFileReader.getValue("FILE_DOWNLOAD_LOC")+"objName"))); 
             	// extentReports.endTest(extentTestReport);
             	// extentReports.flush();
             	 //extentReports.close(); 
         	}
         }
  }
  
  /*
   * ############################################################################################# 
   * ' FUNCTION NAME - windowActivate ' DESCRIPTION - This
   * function will be used to switch to Parent Window ' INPUT PARAMETERS -
   * '################################################################################################
   */

  public boolean elementDisplayed(WebDriver webDriver,String objName,String selector,String selectorvalue, ExtentReports extentReports,ExtentTest extentTestReport) {
  	CucumberTestAutomationLogger.writeToLog("SeleniumKeywordslib", "elementDisplayed() ","elementDisplayed - ");
  	boolean status = false;
  	try {

  		if (webDriver == null) {
  			CucumberTestAutomationLogger.writeToLog("SeleniumKeywordslib","elementDisplayed() ", "Browser is null");
  			return false;
  		}
  		List<WebElement> list = getElements(webDriver, objName, selector,selectorvalue);
		
       if (list.size() > 0) {
		CucumberTestAutomationLogger.writeToLog("SeleniumKeywordslib","elementDisplayed()", list.size()+ " number of elements Found");

		  WebElement we = list.get(0);
  		if(we.isDisplayed())
  		{
  			
  			if((we.getText().equalsIgnoreCase("Warning")) || (we.getText().equalsIgnoreCase("Warning")))
  			{
  				CucumberTestAutomationLogger.writeToLog("SeleniumKeywordslib", "elementDisplayed() ",we +"is displayed ");
				status = true;
				extentTestReport.log(LogStatus.PASS, " Warning Message in Application");
				return status;
  			}
  				
  			else
  			{
  			  CucumberTestAutomationLogger.writeToLog("SeleniumKeywordslib", "elementDisplayed() ",objName +"is displayed ");
				status = false;
				extentTestReport.log(LogStatus.PASS, objName +" Element Visible ");
				return status;
  			}
  			
  		}
  		else
  		{
  			 CucumberTestAutomationLogger.writeToLog("SeleniumKeywordslib", "elementDisplayed() ",we +"is not displayed ");
				status = false;
				extentTestReport.log(LogStatus.FAIL,objName + " Element not Visible ");
				return status;
  		}
  		

  	} else if (list.size() == 1)
  	{
  		 WebElement element = list.get(0);
  		if(element.isDisplayed()) 
	        {
  			if((element.getText().equalsIgnoreCase("Warning")) || (element.getText().equalsIgnoreCase("Warning")))
  			{
  				CucumberTestAutomationLogger.writeToLog("SeleniumKeywordslib", "elementDisplayed() ",element +"is displayed ");
				status = true;
				extentTestReport.log(LogStatus.FAIL, " Warning in Application");
				return status;
  			}
  				
  			else
  			{
       		  CucumberTestAutomationLogger.writeToLog("SeleniumKeywordslib", "elementDisplayed() ","element is displayed ");
				status = true;
				extentTestReport.log(LogStatus.PASS, " Element Visible ");
				return status;
			}
	        }

	 else 
	 {
		CucumberTestAutomationLogger.writeToLog("SeleniumKeywordslib","elementDisplayed() ","element is  not displayed");
		status = false;
		return status;
	 }
  	}
  	else
  	{
  		CucumberTestAutomationLogger.writeToLog("SeleniumKeywordslib","elementDisplayed() ","element is  not displayed");
		status = false;
		return status;
  	}
  	}catch (Exception exception) {
  		 try
  		 {
  			    exception.printStackTrace();
  				CucumberTestAutomationLogger.writeToLog("SeleniumKeywordslib","elementDisplayed() ", "Exeption thrown");
  				CucumberTestAutomationLogger.writeToLog("SeleniumKeywordslib","elementDisplayed() ", "object doesnot exists");
  				return false;
  		 }
  		 finally{
  				extentTestReport.log(LogStatus.FAIL, "object doesnot exists");
  		        extentTestReport.log(LogStatus.FAIL, extentTestReport.addScreenCapture(ExtentReportManager.CaptureScreen(webDriver, propertyFileReader.getValue("SNAPSHOT_FOLDER")))); 
  		    	// extentReports.endTest(extentTestReport);
  		    	// extentReports.flush();
  		         //extentReports.close(); 
  		        webDriver.close();
  		         //webDriver.quit();
  		         
  			}

  	}
  }	
  /*
   * ############################################################################################# 
   * ' FUNCTION NAME - windowActivate ' DESCRIPTION - This
   * function will be used to switch to Parent Window ' INPUT PARAMETERS -
   * '################################################################################################
   */

  public boolean elementVisible(WebDriver webDriver,String objName,String selector,String selectorvalue, ExtentReports extentReports,ExtentTest extentTestReport) {
  	CucumberTestAutomationLogger.writeToLog("SeleniumKeywordslib", "elementDisplayed() ","elementDisplayed - ");
  	boolean status = false;
  	try {

  		if (webDriver == null) {
  			CucumberTestAutomationLogger.writeToLog("SeleniumKeywordslib","elementDisplayed() ", "Browser is null");
  			return false;
  		}
  		List<WebElement> list = getElements(webDriver, objName, selector,selectorvalue);
		
       if (list.size() > 0) {
		CucumberTestAutomationLogger.writeToLog("SeleniumKeywordslib","elementDisplayed()", list.size()+ " number of elements Found");

		  WebElement we = list.get(0);
  		if(we.isDisplayed())
  		{
  				CucumberTestAutomationLogger.writeToLog("SeleniumKeywordslib", "elementDisplayed() ",we +"is displayed ");
				status = true;
				extentTestReport.log(LogStatus.PASS, objName +" Visible");
				return status;
  			
  		}
  		else
  		{
  			 CucumberTestAutomationLogger.writeToLog("SeleniumKeywordslib", "elementDisplayed() ",we +"is not displayed ");
				status = false;
				extentTestReport.log(LogStatus.FAIL, " Element not Visible ");
				return status;
  		}
  		

  	} else if (list.size() == 1)
  	{
  		 WebElement element = list.get(0);
  		if(element.isDisplayed()) 
	        {
  				CucumberTestAutomationLogger.writeToLog("SeleniumKeywordslib", "elementDisplayed() ",element +"is displayed ");
				status = true;
				extentTestReport.log(LogStatus.FAIL, " Warning in Application");
				return status;
  			
	        }

	 else 
	 {
		CucumberTestAutomationLogger.writeToLog("SeleniumKeywordslib","elementDisplayed() ","element is  not displayed");
		status = false;
		return status;
	 }
  	}
  	else
  	{
  		CucumberTestAutomationLogger.writeToLog("SeleniumKeywordslib","elementDisplayed() ","element is  not displayed");
		status = false;
		return status;
  	}
  	}catch (Exception exception) {
  		 try
  		 {
  			    exception.printStackTrace();
  				CucumberTestAutomationLogger.writeToLog("SeleniumKeywordslib","elementDisplayed() ", "Exeption thrown");
  				CucumberTestAutomationLogger.writeToLog("SeleniumKeywordslib","elementDisplayed() ", "object doesnot exists");
  				return false;
  		 }
  		 finally{
  				extentTestReport.log(LogStatus.FAIL, "object doesnot exists");
  		        extentTestReport.log(LogStatus.FAIL, extentTestReport.addScreenCapture(ExtentReportManager.CaptureScreen(webDriver, propertyFileReader.getValue("SNAPSHOT_FOLDER")))); 
  		    	// extentReports.endTest(extentTestReport);
  		    	// extentReports.flush();
  		         //extentReports.close(); 
  		        webDriver.close();
  		         //webDriver.quit();
  		         
  			}

  	}
  }	
  /*
   * ############################################################################################# 
   * ' FUNCTION NAME - windowActivate ' DESCRIPTION - This
   * function will be used to switch to Parent Window ' INPUT PARAMETERS -
   * '################################################################################################
   */

  public boolean verifyMultileElementsVisible(WebDriver webDriver,String objName,String selector,String selectorValue,String elements, ExtentReports extentReports,ExtentTest extentTestReport) {
  	CucumberTestAutomationLogger.writeToLog("SeleniumKeywordslib", "elementDisplayed() ","elementDisplayed - ");
  	boolean status = false;
  	String[] parts = elements.split(",");
  	
  	try {

  		if (webDriver == null) {
  			CucumberTestAutomationLogger.writeToLog("SeleniumKeywordslib","elementDisplayed() ", "Browser is null");
  			return false;
  		}
  		List<WebElement> list = getElements(webDriver, objName, selector,selectorValue);
  		
       if (list.size() > 0) {
    	   WebElement we = list.get(0);
    	   for(int i=0;i<parts.length;i++)
 		  {
    		 
    		   WebElement ele  =webDriver.findElement(By.xpath("//*[text()="+"'"+parts[i] +"'"+"]"));
		CucumberTestAutomationLogger.writeToLog("SeleniumKeywordslib","elementDisplayed()", list.size()+ " number of elements Found");

		 
		  
			   
  		if(ele.isDisplayed())
  		{
  				CucumberTestAutomationLogger.writeToLog("SeleniumKeywordslib", "elementDisplayed() ",we +"is displayed ");
				status = true;
				extentTestReport.log(LogStatus.PASS, ele +" Visible");
				
  			
  		}
  		else
  		{
  			 CucumberTestAutomationLogger.writeToLog("SeleniumKeywordslib", "elementDisplayed() ",we +"is not displayed ");
				status = false;
				extentTestReport.log(LogStatus.FAIL, " Element not Visible ");
				//return status;
  		}
		  }
    	   return status;
  	}
  	
  	else if (list.size() == 1)
  	{
  		 //W
  		 for(int i=0;i<parts.length;i++)
		  {
  			 
   		   WebElement ele  =webDriver.findElement(By.xpath( "//*[text()="+ "'"+parts[i] +"'"+"]"));
		CucumberTestAutomationLogger.writeToLog("SeleniumKeywordslib","elementDisplayed()", list.size()+ " number of elements Found");

		 
  		if(ele.isDisplayed()) 
	        {
  				CucumberTestAutomationLogger.writeToLog("SeleniumKeywordslib", "elementDisplayed() ",ele +"is displayed ");
				status = true;
				extentTestReport.log(LogStatus.FAIL, " Warning in Application");
				//return status;
  			
	        }

	 else 
	 {
		CucumberTestAutomationLogger.writeToLog("SeleniumKeywordslib","elementDisplayed() ","element is  not displayed");
		status = false;
		//return status;
	 }
		  }
  		return status;
  	}
  	else
  	{
  		CucumberTestAutomationLogger.writeToLog("SeleniumKeywordslib","elementDisplayed() ","element is  not displayed");
		status = false;
		return status;
  	}
  	}catch (Exception exception) {
  		 try
  		 {
  			    exception.printStackTrace();
  				CucumberTestAutomationLogger.writeToLog("SeleniumKeywordslib","elementDisplayed() ", "Exeption thrown");
  				CucumberTestAutomationLogger.writeToLog("SeleniumKeywordslib","elementDisplayed() ", "object doesnot exists");
  				return false;
  		 }
  		 finally{
  				extentTestReport.log(LogStatus.FAIL, "object doesnot exists");
  		        extentTestReport.log(LogStatus.FAIL, extentTestReport.addScreenCapture(ExtentReportManager.CaptureScreen(webDriver, propertyFileReader.getValue("SNAPSHOT_FOLDER")))); 
  		    	// extentReports.endTest(extentTestReport);
  		    	// extentReports.flush();
  		         //extentReports.close(); 
  		        webDriver.close();
  		         //webDriver.quit();
  		         
  			}

  	}
  }
  	 /*
     * ############################################################################################# 
     * ' FUNCTION NAME - elementPresence ' DESCRIPTION - This
     * function will be used to switch to Parent Window ' INPUT PARAMETERS -
     * '################################################################################################
     */

    public boolean elementPresence(WebDriver webDriver,String objName,String selector,String selectorvalue, ExtentReports extentReports,ExtentTest extentTestReport) {
    	CucumberTestAutomationLogger.writeToLog("SeleniumKeywordslib", "elementPresence() ","elementPresence - ");
    	boolean status = false;
    	try {

    		if (webDriver == null) {
    			CucumberTestAutomationLogger.writeToLog("SeleniumKeywordslib","elementPresence() ", "Browser is null");
    			return false;
    		}
    		List<WebElement> list = getElements(webDriver, objName, selector,selectorvalue);
  		
         if (list.size() > 0) {
  		CucumberTestAutomationLogger.writeToLog("SeleniumKeywordslib","elementPresence()", list.size()+ " number of elements Found");

  		  WebElement we = list.get(0);
    		
    					CucumberTestAutomationLogger.writeToLog("SeleniumKeywordslib", "windowActivate() ",we +"is displayed ");
    					status = true;
    					extentTestReport.log(LogStatus.PASS, objName+"   Exists");
    					return status;
    		

    	} else if (list.size() == 1)
    	{
    		 WebElement element = list.get(0);
    		if(element.isDisplayed()) 
  	        {
  		
         		CucumberTestAutomationLogger.writeToLog("SeleniumKeywordslib", "elementPresence() ","element is displayed ");
  				status = true;
  				extentTestReport.log(LogStatus.PASS, objName+" Element is displayed ");
  				return status;
  			} 

  	 else 
  	 {
  		CucumberTestAutomationLogger.writeToLog("SeleniumKeywordslib","elementPresence() ","element is  not displayed");
  		status = false;
  		return status;
  	 }
    	}
    	else
    	{
    		CucumberTestAutomationLogger.writeToLog("SeleniumKeywordslib","elementPresence() ","element is  not displayed");
  		status = false;
  		return status;
    	}
    	}catch (Exception exception) {
    		 try
    		 {
    			    exception.printStackTrace();
    				CucumberTestAutomationLogger.writeToLog("SeleniumKeywordslib","elementPresence() ", "Exeption thrown");
    				CucumberTestAutomationLogger.writeToLog("SeleniumKeywordslib","elementPresence() ", "object doesnot exists");
    				return false;
    		 }
    		 finally{
    				extentTestReport.log(LogStatus.FAIL, "Unable to switch to window ");
    		        extentTestReport.log(LogStatus.FAIL, extentTestReport.addScreenCapture(ExtentReportManager.CaptureScreen(webDriver, propertyFileReader.getValue("SNAPSHOT_FOLDER")))); 
    		    	// extentReports.endTest(extentTestReport);
    		    	// extentReports.flush();
    		         //extentReports.close(); 
    		        webDriver.close();
    		         //webDriver.quit();
    		         
    			}

    	}
  }
  /*
	 * ##########################################################################
	 * ################### ' FUNCTION NAME - setCheckbox() ' DESCRIPTION -
	 * This function will Select/Deselect the CHECK BOX . ' INPUT PARAMETERS -
	 * Obj - Name of the Object
	 * '################################################################################################
	 */

	public String setCheckbox(WebDriver webDriver, String objName,String selector,String selectorvalue,ExtentReports extentReports,ExtentTest extentTestReport) {
		String status = "false";
		try {
			List<WebElement> list = getElements(webDriver, objName,selector,selectorvalue);
			
			CucumberTestAutomationLogger.writeToLog("SeleniumKeywordslib", "setCheckbox() ","web elements size - " + list.size());
			if (list.size() > 1) {
				CucumberTestAutomationLogger.writeToLog("SeleniumKeywordslib","setCheckbox() ", "found more than one element");
				CucumberTestAutomationLogger.writeToLog("SeleniumKeywordslib","setCheckbox() ", "clicking 1st element");
				WebElement element=list.get(0);
				//for (WebElement element : list) {
					if (element.isDisplayed()) {
						
							if (element.isSelected()) {
								CucumberTestAutomationLogger.writeToLog("SeleniumKeywordslib","setCheckbox() ", "'" + objName+ "' => is already selected");
								extentTestReport.log(LogStatus.PASS, "File Name " +objName);
								return "true";
							} else {
								element.click();
								CucumberTestAutomationLogger.writeToLog("SeleniumKeywordslib","setCheckbox() ", "'" + objName+ "' => is  checked");
								extentTestReport.log(LogStatus.PASS, "File Name " +objName);
								return "true";
					}
					}
					else {
						CucumberTestAutomationLogger.writeToLog("SeleniumKeywordslib","setCheckbox() ", "'" + objName+ "CheckBox was not visible");
						status = "CheckBox was not visible";
						return status;
					}
				
			//} 
			}else if (list.size() == 1) {
				WebElement element = list.get(0);
				if (element.isDisplayed()) {
					if (element.isSelected()) {
							CucumberTestAutomationLogger.writeToLog("SeleniumKeywordslib","setCheckbox() ", "'" + objName+ "' => is already selected");
							extentTestReport.log(LogStatus.PASS, "File Name " +objName);
							return "true";
						} else {
							//element.click();
							Actions act = new Actions(webDriver);
							act.moveToElement(element).click(element).build().perform();
							CucumberTestAutomationLogger.writeToLog("SeleniumKeywordslib","setCheckbox() ", "'" + objName+ "' => is  checked");
							extentTestReport.log(LogStatus.PASS, "File Name " +objName);
							return "true";
						}
					
					}

				} else {
					CucumberTestAutomationLogger.writeToLog("SeleniumKeywordslib","setCheckbox() ", "'" + objName+ "CheckBox was not visible");
					status = "CheckBox was not visible";
					return status;
				}

			} 
		 catch (Exception exception) {
	  		 try
	  		 {
	  			    exception.printStackTrace();
	  				CucumberTestAutomationLogger.writeToLog("SeleniumKeywordslib","select Window() ", "Exeption thrown");
	  				CucumberTestAutomationLogger.writeToLog("SeleniumKeywordslib","select Window() ", "object doesnot exists");
	  				
	  		 }
	  		 finally{
	  				extentTestReport.log(LogStatus.FAIL, "Unable to switch to window ");
	  		        extentTestReport.log(LogStatus.FAIL, extentTestReport.addScreenCapture(ExtentReportManager.CaptureScreen(webDriver, propertyFileReader.getValue("SNAPSHOT_FOLDER")))); 
	  		    	// extentReports.endTest(extentTestReport);
	  		    	// extentReports.flush();
	  		         //extentReports.close(); 
	  		        webDriver.close();
	  		         //webDriver.quit();
	  		         
	  			}
	  	}
		return status;
	}
	 /*
		 * ##########################################################################
		 * ################### ' FUNCTION NAME - setCheckbox() ' DESCRIPTION -
		 * This function will Select/Deselect the CHECK BOX . ' INPUT PARAMETERS -
		 * Obj - Name of the Object
		 * '################################################################################################
		 */

		public String setReassignCheckbox(WebDriver webDriver, String objName,String selector,String selectorvalue,int noofcheckboxes,ExtentReports extentReports,ExtentTest extentTestReport) {
			String status = "false";
			int count=0;
			try {
				List<WebElement> list = getElements(webDriver, objName,selector,selectorvalue);
				
				CucumberTestAutomationLogger.writeToLog("SeleniumKeywordslib", "setCheckbox() ","web elements size - " + list.size());
				if (list.size() > 1) {
					CucumberTestAutomationLogger.writeToLog("SeleniumKeywordslib","setCheckbox() ", "found more than one element");
					CucumberTestAutomationLogger.writeToLog("SeleniumKeywordslib","setCheckbox() ", "clicking 1st element");
					for (WebElement element : list) {
						if (element.isDisplayed()) {
							
								if (element.isSelected()) {
									CucumberTestAutomationLogger.writeToLog("SeleniumKeywordslib","setCheckbox() ", "'" + objName+ "' => is already selected");
									extentTestReport.log(LogStatus.PASS, "File Name " +objName);
									count++;
									if(count == noofcheckboxes)
									{
										break;
									
									}
									status="true";
								}
								else {
									element.click();
									CucumberTestAutomationLogger.writeToLog("SeleniumKeywordslib","setCheckbox() ", "'" + objName+ "' => is  checked");
									extentTestReport.log(LogStatus.PASS, "File Name " +objName);
									count++;
									if(count == noofcheckboxes)
									{
										break;
									
									}
									status="true";
									//return "true";
						}
						}
						else {
							CucumberTestAutomationLogger.writeToLog("SeleniumKeywordslib","setCheckbox() ", "'" + objName+ "CheckBox was not visible");
							status = "CheckBox was not visible";
							
						}
						
				} 
					return status;
				}else if (list.size() == 1) {
					WebElement element = list.get(0);
					if (element.isDisplayed()) {
				if (element.isSelected()) {
								CucumberTestAutomationLogger.writeToLog("SeleniumKeywordslib","setCheckbox() ", "'" + objName+ "' => is already selected");
								extentTestReport.log(LogStatus.PASS, "File Name " +objName);
								return "true";
							} else {
								element.click();
								CucumberTestAutomationLogger.writeToLog("SeleniumKeywordslib","setCheckbox() ", "'" + objName+ "' => is  checked");
								extentTestReport.log(LogStatus.PASS, "File Name " +objName);
								return "true";
							}
						
						}

					} else {
						CucumberTestAutomationLogger.writeToLog("SeleniumKeywordslib","setCheckbox() ", "'" + objName+ "CheckBox was not visible");
						status = "CheckBox was not visible";
						return status;
					}

				} 
			 catch (Exception exception) {
		  		 try
		  		 {
		  			    exception.printStackTrace();
		  				CucumberTestAutomationLogger.writeToLog("SeleniumKeywordslib","select Window() ", "Exeption thrown");
		  				CucumberTestAutomationLogger.writeToLog("SeleniumKeywordslib","select Window() ", "object doesnot exists");
		  				
		  		 }
		  		 finally{
		  				extentTestReport.log(LogStatus.FAIL, "Unable to switch to window ");
		  		        extentTestReport.log(LogStatus.FAIL, extentTestReport.addScreenCapture(ExtentReportManager.CaptureScreen(webDriver, propertyFileReader.getValue("SNAPSHOT_FOLDER")))); 
		  		    	// extentReports.endTest(extentTestReport);
		  		    	// extentReports.flush();
		  		         //extentReports.close(); 
		  		        webDriver.close();
		  		         //webDriver.quit();
		  		         
		  			}
		  	}
			return status;
		}
	public boolean refreshPage(WebDriver webDriver) {
		boolean status = false;
		try {
			Navigation nav = webDriver.navigate();
			nav.refresh();
			status = true;
			return status;
		}
		
		catch (Exception e) {
			
		}
		return status;
	}
	 /* * * ##########################################################################
		 * ################### ' FUNCTION NAME - handleAlertDialog() ' DESCRIPTION
		 * - This function will handle Alert Dialogue box
		 * '################################################################################################*/
	public boolean handleAlertDialog(WebDriver webDriver,ExtentReports extentReports,ExtentTest extentTestReport) {
		try {

			Alert alert = webDriver.switchTo().alert();
			alert.accept();
			webDriver.navigate().refresh();
			extentTestReport.log(LogStatus.PASS, "ALERT Handled");
			CucumberTestAutomationLogger.writeToLog("SeleniumKeywordslib","handleAlertDialog() ", "ALERT Handled");

		}
		catch (Exception exception) {
	  		 try
	  		 {
	  			    exception.printStackTrace();
	  				CucumberTestAutomationLogger.writeToLog("SeleniumKeywordslib","handleAlertDialog() ", "ALERT not Handled");
	  		 }
	  		 finally{
	  				extentTestReport.log(LogStatus.FAIL, "ALERT not Handled");
	  		        extentTestReport.log(LogStatus.FAIL, extentTestReport.addScreenCapture(ExtentReportManager.CaptureScreen(webDriver, propertyFileReader.getValue("SNAPSHOT_FOLDER")))); 
	  		    	// extentReports.endTest(extentTestReport);
	  		    	// extentReports.flush();
	  		         //extentReports.close(); 
	  		        webDriver.close();
	  		         //webDriver.quit();
	  		         
	  			}
		}
		
		return true;
	}

	public boolean handleAlertDialog(WebDriver webDriver, String val,ExtentReports extentReports,ExtentTest extentTestReport) {
		CucumberTestAutomationLogger.writeToLog("SeleniumKeywordslib","handleAlertDialog() ", "Value" + val);
		try {

			if (val.equalsIgnoreCase("Yes")) {
				Alert alert = webDriver.switchTo().alert();
				alert.accept();

			} else {
				Alert alert = webDriver.switchTo().alert();
				alert.dismiss();
			}

			extentTestReport.log(LogStatus.PASS, "ALERT Handled");
			CucumberTestAutomationLogger.writeToLog("SeleniumKeywordslib","handleAlertDialog() ", "ALERT Handled");
	
		} catch (Exception exception) {
	  		 try
	  		 {
	  			    exception.printStackTrace();
	  				CucumberTestAutomationLogger.writeToLog("SeleniumKeywordslib","handleAlertDialog() ", "ALERT not Handled");
	  		 }
	  		 finally{
	  				extentTestReport.log(LogStatus.FAIL, "ALERT not Handled ");
	  		        extentTestReport.log(LogStatus.FAIL, extentTestReport.addScreenCapture(ExtentReportManager.CaptureScreen(webDriver, propertyFileReader.getValue("SNAPSHOT_FOLDER")))); 
	  		    	// extentReports.endTest(extentTestReport);
	  		    	// extentReports.flush();
	  		         //extentReports.close(); 
	  		        webDriver.close();
	  		         //webDriver.quit();
	  		         
	  			}
	
	}
		return true;
	}
	
	/*
	 * ##########################################################################
	 * ################### ' FUNCTION NAME - compareData() ' DESCRIPTION - This
	 * function will used to compare the two values, whether it is a normal text
	 * or properties. ' INPUT PARAMETERS - Expectedvalue- OrginalValue -
	 * Actualvalue - Captured value
	 * '################################################################################################
	 */

	public boolean compareData(WebDriver webDriver,double Actualvalue, String Expectedvalue,String Match,ExtentReports extentReports,ExtentTest extentTestReport) {
		CucumberTestAutomationLogger.writeToLog("SeleniumKeywordslib", "compareData() ","Actualvalue " + Actualvalue + " Expectedvalue "+ Expectedvalue + " Match " + Match);
		
		try
		{
    		if (Match.equalsIgnoreCase("Exact")) {
				if ((Double.toString(Actualvalue)).equals(Expectedvalue)) {
					extentTestReport.log(LogStatus.PASS, "Actual data matches with expected data");
					CucumberTestAutomationLogger.writeToLog("SeleniumKeywordslib", "compareData() ","Actual data matches with expected data");
					return true;
				} else {
					extentTestReport.log(LogStatus.FAIL, "Actual datadoesn't  matches with expected data");
					CucumberTestAutomationLogger.writeToLog("SeleniumKeywordslib", "compareData() ","Actual doesn't match with expected data");
					return false;
				}
			} 
    		else if(Match.equalsIgnoreCase("contains")){
    			 String Expectedval = Expectedvalue.replaceAll("-", "");
 				if ((Double.toString(Actualvalue)).contains(Expectedval)) {
    				extentTestReport.log(LogStatus.PASS, "Actual data contains  expected data");
					CucumberTestAutomationLogger.writeToLog("SeleniumKeywordslib", "compareData() ","Actual data contains  expected data");
					return true;
				} else {
					extentTestReport.log(LogStatus.FAIL, "Actual doesn't contains expected data");
					CucumberTestAutomationLogger.writeToLog("SeleniumKeywordslib", "compareData() ","Actual doesn't contains expected data");
					return false;
				}
    			}
    			
		
		}
		catch (Exception exception) {
	  		 try
	  		 {
	  			    exception.printStackTrace();
	  				CucumberTestAutomationLogger.writeToLog("SeleniumKeywordslib","compareData() ", "Unable to compare data");
	  		 }
	  		 finally{
	  				extentTestReport.log(LogStatus.FAIL, "Unable to compare data");
	  		        extentTestReport.log(LogStatus.FAIL, extentTestReport.addScreenCapture(ExtentReportManager.CaptureScreen(webDriver, propertyFileReader.getValue("SNAPSHOT_FOLDER")))); 
	  		    	// extentReports.endTest(extentTestReport);
	  		    	// extentReports.flush();
	  		         //extentReports.close(); 
	  		        webDriver.close();
	  		         //webDriver.quit();
	  		         
	  			}
	
	}
		return false;
		}
	
	 /*
		 * ############################################################################################# 
		 * ' FUNCTION NAME - scrollPage ' DESCRIPTION - This
		 * function will be used to Scroll to the required object ' INPUT PARAMETERS -
		 * '################################################################################################
		 */
	 public boolean scrollPage(WebDriver webDriver, String objName,String selector,String selectorvalue,String text,ExtentReports extentReports,ExtentTest extentTestReport) {
			boolean status = false;
			try {
			    selectorvalue = "//*[text()="+ "'"+text +"'"+"]";
                Actions act = new Actions(webDriver);
				//JavascriptExecutor js = (JavascriptExecutor) webDriver;
				List<WebElement> list = getElements(webDriver, objName, selector,selectorvalue);
				WebElement we = list.get(0);
            	Point scrollpoint = we.getLocation();
				webDriver.manage().timeouts().implicitlyWait(60, TimeUnit.MINUTES);
				 act.moveToElement(we).build().perform();
               	((JavascriptExecutor) webDriver).executeScript("window.scrollBy(0,"+ scrollpoint + ")");
				extentTestReport.log(LogStatus.PASS, " Scrolled to  " +objName );
				CucumberTestAutomationLogger.writeToLog("Scrolled to " +objName);
				status = true;
				return status;
			} catch (NoSuchElementException ns) {
				try
				{
				ns.printStackTrace();
				CucumberTestAutomationLogger.writeToLog("SeleniumKeywordslib", "scrollPage()","Scrolling Failed");
				return status;
			} 
			finally {
       		 extentTestReport.addScreenCapture(propertyFileReader.getValue("REPORT_PATH")+"objName");
        	 extentTestReport.log(LogStatus.FAIL, extentTestReport.addScreenCapture(ExtentReportManager.CaptureScreen(webDriver, propertyFileReader.getValue("SNAPSHOT_FOLDER")+"objName"))); 
        	// extentReports.endTest(extentTestReport);
        	// extentReports.flush();
        	 //extentReports.close(); 
    	}
		}
	 }
	 
	 public String getCellDataForAnnouncementsTable(WebDriver webDriver, String objName,String selector,String selectorvalue, String rowName, String colName,ExtentReports extentReports,ExtentTest extentTestReport) {
			CucumberTestAutomationLogger.writeToLog("SeleniumKeywordslib", "getCellData()","getCellData  rowNo " + rowName + " colNo " + colName+ "..objName - " + objName);
			String value = null;
			String getVal = null;
			int rowNo = 0;
			int colNo = 0;
			int  row=0;
			int col=0;
			try {
				List<WebElement> list = getElements(webDriver, objName, selector,selectorvalue);
				WebElement we = null;
				if (list != null)
					we = list.get(0);

				if (we != null) {
					List<WebElement> rows = null;
					rows = (we.findElements(By.xpath(".//tr")));
                   CucumberTestAutomationLogger.writeToLog("SeleniumKeywordslib","getRowByContent() ", "rows - " + rows.size()+colNo);
					if (rows != null) {
						int tempRowNo = -1;
						for (WebElement werowCell : rows) {
							String value1 = null;
							tempRowNo++;
				
					CucumberTestAutomationLogger.writeToLog("SeleniumKeywordslib","getCellData()", rowName + "X" + colName);
					value1 = werowCell.getText();
					
					CucumberTestAutomationLogger.writeToLog("SeleniumKeywordslib","getRowByContent() ", "VALUE - "+ value1);

					if (value1 != null) {
						if (value1.contains(rowName)) {
							rowNo = tempRowNo;
							CucumberTestAutomationLogger.writeToLog("SeleniumKeywordslib","getRowByContent() ","VALUES EQUAL");
							CucumberTestAutomationLogger.writeToLog("SeleniumKeywordslib","getRowByContent() ","Row number captured is = \""+ rowNo + "\"");
							row = rowNo;
							List<WebElement> cols = null;
							cols = (we.findElements(By.xpath("//*[@class='k-grid-table']//tr//td[6]")));

							CucumberTestAutomationLogger.writeToLog("SeleniumKeywordslib","getRowByContent() ", "rows - " + cols.size());
							if (cols != null) {
								int tempColNo = -1;
								for (WebElement wecolCell : cols) {
									String value2 = null;
									tempColNo++;
							
							CucumberTestAutomationLogger.writeToLog("SeleniumKeywordslib","getCellData()", rowName + "X" + colName +tempColNo);
							value2 = wecolCell.getText();
							CucumberTestAutomationLogger.writeToLog("SeleniumKeywordslib","getRowByContent() ", "VALUE - "+ value2);

							if (value2 != null) {
								  	extentTestReport.log(LogStatus.PASS," Value for  :  "+ colName + "   in RowName : " + rowName + " : is "+ value);
											CucumberTestAutomationLogger.writeToLog("SeleniumKeywordslib","getCellData()", "'" + objName+ "'=>Get the value from column=:"+ col + "and RowNo=: " + row + " : is "+ value);
											return value2;
										} else {
											extentTestReport.log(LogStatus.FAIL,"getCellData()", getVal + " is null");
											CucumberTestAutomationLogger.writeToLog("SeleniumKeywordslib","getCellData()", getVal + " is null");
										}
									
								//}
							}
								}
						}
					}
						///}
					else
					{
						CucumberTestAutomationLogger.writeToLog("SeleniumKeywordslib","getCellData()", getVal + " is null");
					}
					}
				
					} 

			} 
				else {
					extentTestReport.log(LogStatus.FAIL,"getCellData()", "'" + objName+ "' =>does not exist!");
					CucumberTestAutomationLogger.writeToLog("SeleniumKeywordslib","getCellData()", "'" + objName+ "' =>does not exist!");
					return null;
				}
			}catch (NoSuchElementException ns) {
				try
				{
				ns.printStackTrace();
				CucumberTestAutomationLogger.writeToLog("SeleniumKeywordslib", "getCellData()","Object not found exeption thrown");
				CucumberTestAutomationLogger.writeToLog("SeleniumKeywordslib", "getCellData()","'" + objName + "'object does not exist");
				return value;
				}
			 finally {
	       		 extentTestReport.addScreenCapture(propertyFileReader.getValue("REPORT_PATH")+"objName");
	        	 extentTestReport.log(LogStatus.FAIL, extentTestReport.addScreenCapture(ExtentReportManager.CaptureScreen(webDriver, propertyFileReader.getValue("SNAPSHOT_FOLDER")+"objName"))); 
	        	// extentReports.endTest(extentTestReport);
	        	// extentReports.flush();
	        	 //extentReports.close(); 
	    	}
			}
			return value;
		}
	 public String getCellData(WebDriver webDriver, String objName,String selector,String selectorvalue, String rowName, String colName,ExtentReports extentReports,ExtentTest extentTestReport) {
			CucumberTestAutomationLogger.writeToLog("SeleniumKeywordslib", "getCellData()","getCellData  rowNo " + rowName + " colNo " + colName+ "..objName - " + objName);
			String value = null;
			String getVal = null;
			int rowNo = 0;
			int colNo = 0;
			int  row=0;
			int col=0;
			try {
				List<WebElement> list = getElements(webDriver, objName, selector,selectorvalue);
				WebElement we = null;
				if (list != null)
					we = list.get(0);

				if (we != null) {
					List<WebElement> rows = null;
					rows = (we.findElements(By.xpath(".//tr")));

					CucumberTestAutomationLogger.writeToLog("SeleniumKeywordslib","getRowByContent() ", "rows - " + rows.size());
					if (rows != null) {
						int tempRowNo = -1;
						for (WebElement werowCell : rows) {
							String value1 = null;
							tempRowNo++;
				
					CucumberTestAutomationLogger.writeToLog("SeleniumKeywordslib","getCellData()", rowName + "X" + colName);
					value1 = werowCell.getText();
					
					CucumberTestAutomationLogger.writeToLog("SeleniumKeywordslib","getRowByContent() ", "VALUE - "+ value1);

					if (value1 != null) {
						if (value1.contains(rowName)) {
							rowNo = tempRowNo;
							CucumberTestAutomationLogger.writeToLog("SeleniumKeywordslib","getRowByContent() ","VALUES EQUAL");
							CucumberTestAutomationLogger.writeToLog("SeleniumKeywordslib","getRowByContent() ","Row number captured is = \""+ rowNo + "\"");
							row = rowNo;
							List<WebElement> cols = null;
							cols = (we.findElements(By.xpath(".//th")));

							CucumberTestAutomationLogger.writeToLog("SeleniumKeywordslib","getRowByContent() ", "rows - " + cols.size());
							if (cols != null) {
								int tempColNo = -1;
								for (WebElement wecolCell : cols) {
									String value2 = null;
									tempColNo++;
							
							CucumberTestAutomationLogger.writeToLog("SeleniumKeywordslib","getCellData()", rowName + "X" + colName);
							value2 = wecolCell.getText();
							CucumberTestAutomationLogger.writeToLog("SeleniumKeywordslib","getRowByContent() ", "VALUE - "+ value2);

							if (value2 != null) {
								if (value2.equalsIgnoreCase(colName)) {
									colNo = tempColNo;
									CucumberTestAutomationLogger.writeToLog("SeleniumKeywordslib","getRowByContent() ","VALUES EQUAL");
									CucumberTestAutomationLogger.writeToLog("SeleniumKeywordslib","getRowByContent() ","Col number captured is = \""+ colNo + "\"");
									col = colNo;
									
									
									WebElement weCell = (we.findElements(By.tagName("tr"))).get(row).findElements(By.tagName("td")).get(col);
									CucumberTestAutomationLogger.writeToLog("SeleniumKeywordslib","getCellData()", "weCell - " + weCell);

									if (weCell != null) {
										getVal = weCell.getText();
										if (getVal != null) {
											value = getVal.toString();
											extentTestReport.log(LogStatus.PASS," Value for  :  "+ colName + "   in RowName : " + rowName + " : is "+ value);
											CucumberTestAutomationLogger.writeToLog("SeleniumKeywordslib","getCellData()", "'" + objName+ "'=>Get the value from column=:"+ col + "and RowNo=: " + row + " : is "+ value);
											return value;
										} else {
											extentTestReport.log(LogStatus.FAIL,"getCellData()", getVal + " is null");
											CucumberTestAutomationLogger.writeToLog("SeleniumKeywordslib","getCellData()", getVal + " is null");
										}
									} else {
										extentTestReport.log(LogStatus.FAIL,"getCellData()", getVal + " is null");
										CucumberTestAutomationLogger.writeToLog("SeleniumKeywordslib","getCellData()", "value is null");
									}
								}
							}
								}
						}
					}
						}
					else
					{
						CucumberTestAutomationLogger.writeToLog("SeleniumKeywordslib","getCellData()", "value is null");
					}
					}
				
					} 

			} 
				else {
					extentTestReport.log(LogStatus.FAIL,"getCellData()", "'" + objName+ "' =>does not exist!");
					CucumberTestAutomationLogger.writeToLog("SeleniumKeywordslib","getCellData()", "'" + objName+ "' =>does not exist!");
					return null;
				}
			}catch (NoSuchElementException ns) {
				try
				{
				ns.printStackTrace();
				CucumberTestAutomationLogger.writeToLog("SeleniumKeywordslib", "getCellData()","Object not found exeption thrown");
				CucumberTestAutomationLogger.writeToLog("SeleniumKeywordslib", "getCellData()","'" + objName + "'object does not exist");
				return value;
				}
			 finally {
	       		 extentTestReport.addScreenCapture(propertyFileReader.getValue("REPORT_PATH")+"objName");
	        	 extentTestReport.log(LogStatus.FAIL, extentTestReport.addScreenCapture(ExtentReportManager.CaptureScreen(webDriver, propertyFileReader.getValue("SNAPSHOT_FOLDER")+"objName"))); 
	        	// extentReports.endTest(extentTestReport);
	        	// extentReports.flush();
	        	 //extentReports.close(); 
	    	}
			}
			return value;
		}
/*
 * ##########################################################################
 * ################### ' FUNCTION NAME - compareData() ' DESCRIPTION - This
 * function will used to compare the two values, whether it is a normal text
 * or properties. ' INPUT PARAMETERS - Expectedvalue- OrginalValue -
 * Actualvalue - Captured value
 * '################################################################################################
 */

public boolean compareData(WebDriver webDriver,String Actualvalue, String Expectedvalue,String Match,ExtentReports extentReports,ExtentTest extentTestReport) {
	CucumberTestAutomationLogger.writeToLog("SeleniumKeywordslib", "compareData() ",
			"Actualvalue " + Actualvalue + " Expectedvalue "
					+ Expectedvalue + " Match " + Match);
	if (Match.equalsIgnoreCase("Exact")) {
		if (Expectedvalue.equals(Actualvalue)) {
			CucumberTestAutomationLogger.writeToLog("SeleniumKeywordslib", "compareData() ","Actual data matches with expected data");
			 extentTestReport.log(LogStatus.PASS, "Actual data matches with expected data" +" \" "+Actualvalue  +"\" " + "==" +" \" "+Expectedvalue+"\" ");
			return true;
		} else {
			CucumberTestAutomationLogger.writeToLog("SeleniumKeywordslib", "compareData() ","Actual doesn't match with expected data");
			 extentTestReport.log(LogStatus.FAIL, "Actual data doesn't matches with expected data" +" \" "+Actualvalue  +"\" " + "!=" +" \" "+Expectedvalue+"\" ");
			return false;
		}
	} 
	else if (Match.equalsIgnoreCase("contains")) {
		if (Expectedvalue.contains(Actualvalue)) {
			CucumberTestAutomationLogger.writeToLog("SeleniumKeywordslib", "compareData() ","Actual data matches with expected data");
			extentTestReport.log(LogStatus.PASS, "Actual data matches with expected data" +" \" "+Actualvalue  +"\" " + "==" +" \" "+Expectedvalue+"\" ");
			return true;
		} else {
			CucumberTestAutomationLogger.writeToLog("SeleniumKeywordslib", "compareData() ","Actual doesn't match with expected data");
			 extentTestReport.log(LogStatus.FAIL, "Actual data doesn't matches with expected data" +" \" "+Actualvalue  +"\" " + "!=" +" \" "+Expectedvalue+"\" ");
			return false;
		}
	}else {
		Pattern pattern = Pattern.compile(Expectedvalue);
		Matcher matcher = pattern.matcher(Actualvalue);
		if (matcher.find()) {
			CucumberTestAutomationLogger.writeToLog("SeleniumKeywordslib", "compareData() ","Actual data matches with expected data");
			extentTestReport.log(LogStatus.PASS, "Actual data matches with expected data" +" \" "+Actualvalue  +"\" " + "==" +" \" "+Expectedvalue+"\" ");
			return true;
		} else {
			CucumberTestAutomationLogger.writeToLog("SeleniumKeywordslib", "compareData() ","Actual doesn't match with expected data");
			 extentTestReport.log(LogStatus.FAIL, "Actual data doesn't matches with expected data" +" \" "+Actualvalue  +"\" " + "!=" +" \" "+Expectedvalue+"\" ");
			return false;
		}
	}

}
/*
 * ##########################################################################
 * ################### ' FUNCTION NAME - custom_ClickWorkbenchOptions() ' DESCRIPTION -
 * This function will Select required links in shipment work bench and inventory workbench ' INPUT PARAMETERS -
 * Obj - Name of the Object
 * '################################################################################################
 */

/*public String moveToElementbyText(WebDriver webDriver, String objName,ExtentReports extentReports,ExtentTest extentTestReport) {
	String status = "false";
	try {
		
       selectorvalue = "//*[normalize-space(text())="+ "'"+selectorvalue +"'"+"]";
		List<WebElement> list = getElements(webDriver, objName, selector,selectorvalue);
		Actions act = new Actions(webDriver);
       	CucumberTestAutomationLogger.writeToLog("SeleniumKeywordslib", "moveToElementbyText() ","web elements size - " + list.size());
		if (list.size() > 1) {
			CucumberTestAutomationLogger.writeToLog("SeleniumKeywordslib","moveToElementbyText() ", "found more than one element");
			CucumberTestAutomationLogger.writeToLog("SeleniumKeywordslib","moveToElementbyText() ", "clicking 1st element");
			
			   WebElement element = list.get(0);
			   Point scrollpoint = element.getLocation();
			   Thread.sleep(2000);
			   act.moveToElement(element).build().perform();
			    ((JavascriptExecutor) webDriver).executeScript("window.scrollBy(0,"+ scrollpoint + ")");
			    status = "true";	 
                extentTestReport.log(LogStatus.PASS, "move To Element" +selectorvalue);
              // return status;
              // if (element.isDisplayed()) {

                       
                     //   ((JavascriptExecutor) webDriver).executeScript("arguments[0].scrollTo(true);", webDriver.findElement(By.xpath(selectorvalue))); 
                     
			//}
		}
		
		else if (list.size() == 1) {
			CucumberTestAutomationLogger.writeToLog("SeleniumKeywordslib","moveToElementbyText() ", "found more than one element");
			CucumberTestAutomationLogger.writeToLog("SeleniumKeywordslib","moveToElementbyText() ", "clicking 1st element");
			

			   WebElement element = list.get(0);
			   Point scrollpoint = element.getLocation();
           // if (element.isDisplayed()) {

                    act.moveToElement(element).build().perform();
                    ((JavascriptExecutor) webDriver).executeScript("window.scrollBy(0,"+ scrollpoint + ")");
                        status = "true";	 
                        extentTestReport.log(LogStatus.PASS, "move to element" +selectorvalue);
                       return status;

			//}
		}else {
				extentTestReport.log(LogStatus.FAIL, "move to element" +selectorvalue);
				CucumberTestAutomationLogger.writeToLog("SeleniumKeywordslib","moveToElementbyText ", "'" + objName+ "Object was not visible");
				status = "Link was not visible";
				return status;
			}

	
	}catch (NoSuchElementException ns) {
        ns.printStackTrace();
        CucumberTestAutomationLogger.writeToLog("SeleniumKeywordslib", "moveToElementbyText() ","Object not found exeption thrown");
        CucumberTestAutomationLogger.writeToLog("SeleniumKeywordslib", "moveToElementbyText() ", "'" + objName + "'object does not exist");
        status = "false";
        extentTestReport.log(LogStatus.FAIL, "Unable to move to " +objName+ "link");
        return status;
 } catch (Exception ex) {
 	try
 	{
        ex.printStackTrace();
        CucumberTestAutomationLogger.writeToLog("SeleniumKeywordslib", "moveToElementbyText() ","Exception occurred");
        extentTestReport.log(LogStatus.FAIL,"select " +objName+ "option");
        String err[]=ex.getMessage().split("\n");
		status = "Exception " +err[0].replaceAll("'", "") + " Exception Occurred";
		return status;
 	}
 	finally
 	{
 		     extentTestReport.log(LogStatus.FAIL, "Unable to click on " +objName+ "Link");
	         extentTestReport.log(LogStatus.FAIL, extentTestReport.addScreenCapture(ExtentReportManager.CaptureScreen(webDriver, propertyFileReader.getValue("SNAPSHOT_FOLDER")))); 
	    	// extentReports.endTest(extentTestReport);
	    	// extentReports.flush();
	         //extentReports.close(); 
	        webDriver.close();
	         //webDriver.quit();
	         
 	}
 }
	return status;
}*/
/*
 * ##########################################################################
 * ################### ' FUNCTION NAME - custom_ClickWorkbenchOptions() ' DESCRIPTION -
 * This function will Select required links in shipment work bench and inventory workbench ' INPUT PARAMETERS -
 * Obj - Name of the Object
 * '################################################################################################
 */

/*public String moveToElement(WebDriver webDriver, String objName,ExtentReports extentReports,ExtentTest extentTestReport) {
	String status = "false";
	try {
      // selectorvalue = "//*[contains(text(),"+ "'"+selectorvalue +"'"+")]";
		List<WebElement> list = getElements(webDriver, objName, selector,selectorvalue);
		Actions act = new Actions(webDriver);
       	CucumberTestAutomationLogger.writeToLog("SeleniumKeywordslib", "moveToElementbyText() ","web elements size - " + list.size());
		if (list.size() > 1) {
			CucumberTestAutomationLogger.writeToLog("SeleniumKeywordslib","moveToElementbyText() ", "found more than one element");
			CucumberTestAutomationLogger.writeToLog("SeleniumKeywordslib","moveToElementbyText() ", "clicking 1st element");
			
			   WebElement element = list.get(0);
			   Point scrollpoint = element.getLocation();
			   Thread.sleep(2000);
			   act.moveToElement(element).build().perform();
			    ((JavascriptExecutor) webDriver).executeScript("window.scrollBy(0,"+ scrollpoint + ")");
			    status = "true";	 
                extentTestReport.log(LogStatus.PASS, "move To Element : " +objName);
              // return status;
              // if (element.isDisplayed()) {

                       
                     //   ((JavascriptExecutor) webDriver).executeScript("arguments[0].scrollTo(true);", webDriver.findElement(By.xpath(selectorvalue))); 
                     
			//}
		}
		
		else if (list.size() == 1) {
			CucumberTestAutomationLogger.writeToLog("SeleniumKeywordslib","moveToElementbyText() ", "found more than one element");
			CucumberTestAutomationLogger.writeToLog("SeleniumKeywordslib","moveToElementbyText() ", "clicking 1st element");
			

			   WebElement element = list.get(0);
			   Point scrollpoint = element.getLocation();
           // if (element.isDisplayed()) {

                    act.moveToElement(element).build().perform();
                    ((JavascriptExecutor) webDriver).executeScript("window.scrollBy(0,"+ scrollpoint + ")");
                        status = "true";	 
                        extentTestReport.log(LogStatus.PASS, "move to element : " +objName);
                       return status;

			//}
		}else {
				extentTestReport.log(LogStatus.FAIL, "move to element" +objName);
				CucumberTestAutomationLogger.writeToLog("SeleniumKeywordslib","moveToElementbyText ", "'" + objName+ "Object was not visible");
				status = "Link was not visible";
				return status;
			}

	
	}catch (NoSuchElementException ns) {
        ns.printStackTrace();
        CucumberTestAutomationLogger.writeToLog("SeleniumKeywordslib", "moveToElementbyText() ","Object not found exeption thrown");
        CucumberTestAutomationLogger.writeToLog("SeleniumKeywordslib", "moveToElementbyText() ", "'" + objName + "  "+ "'object does not exist");
        status = "false";
        extentTestReport.log(LogStatus.FAIL, "Unable to move to " +objName+ "link");
        return status;
 } catch (Exception ex) {
 	try
 	{
        ex.printStackTrace();
        CucumberTestAutomationLogger.writeToLog("SeleniumKeywordslib", "moveToElementbyText() ","Exception occurred");
        extentTestReport.log(LogStatus.FAIL,"select " +objName+ "option");
        String err[]=ex.getMessage().split("\n");
		status = "Exception " +err[0].replaceAll("'", "") + " Exception Occurred";
		return status;
 	}
 	finally
 	{
 		     extentTestReport.log(LogStatus.FAIL, "Unable to click on " +objName);
	         extentTestReport.log(LogStatus.FAIL, extentTestReport.addScreenCapture(ExtentReportManager.CaptureScreen(webDriver, propertyFileReader.getValue("SNAPSHOT_FOLDER")))); 
	    	// extentReports.endTest(extentTestReport);
	    	// extentReports.flush();
	         //extentReports.close(); 
	        webDriver.close();
	         //webDriver.quit();
	         
 	}
 }
	return status;
}*/
/*
 * ##########################################################################
 * ################### ' FUNCTION NAME - datePicker() ' DESCRIPTION -
 * This function will select Date from Calender ' INPUT PARAMETERS -
 * Obj - Name of the Object
 * '################################################################################################
 */

/*public String datePicker(WebDriver webDriver, String objName,String targetDate,ExtentReports extentReports,ExtentTest extentTestReport) {
	String status = "false";
	  final Map<String,Integer> MONTH_TO_CALENDAR_INDEX = new HashMap<String,Integer>();
		Actions act = new Actions(webDriver);
	    MONTH_TO_CALENDAR_INDEX.put("Jan",  1);
	    MONTH_TO_CALENDAR_INDEX.put("Feb",2);
	    MONTH_TO_CALENDAR_INDEX.put("Mar",3);
	    MONTH_TO_CALENDAR_INDEX.put("Apr",4);
	    MONTH_TO_CALENDAR_INDEX.put("May",5);
	    MONTH_TO_CALENDAR_INDEX.put("Jun",6);
	    MONTH_TO_CALENDAR_INDEX.put("Jul",7);
	    MONTH_TO_CALENDAR_INDEX.put("Aug",8);
	    MONTH_TO_CALENDAR_INDEX.put("Sep",9);
	    MONTH_TO_CALENDAR_INDEX.put("Oct",10);
	    MONTH_TO_CALENDAR_INDEX.put("Nov",11);
	    MONTH_TO_CALENDAR_INDEX.put("Dec",12);

	    
	    
	   String menuItems=  targetDate;
       String[] parts = menuItems.split("-");
        String targetday= parts[0];
        String targetMonth = parts[1];
        String targetYear =parts[2];
        
       
        
          try{  
	  //  List<WebElement> list = getElements(webDriver, objName, selector,selectorvalue);
			CucumberTestAutomationLogger.writeToLog("SeleniumKeywordslib", "datePicker() ","web elements size - " + list.size());
			if (list.size() > 1) {
				CucumberTestAutomationLogger.writeToLog("SeleniumKeywordslib","datePicker() ", "found more than one element");
				CucumberTestAutomationLogger.writeToLog("SeleniumKeywordslib","datePicker() ", "clicking 1st element");
				
				    WebElement element = list.get(0);
		            element.click();
		            WebElement picker = webDriver.findElement(By.xpath("//*[@src='../assets/images/calendar.svg']"));
	                Integer mnth = MONTH_TO_CALENDAR_INDEX.get(picker.findElement(By.xpath("//*[@class='custom-select d-inline-block']")).getText());
		            int month = Integer.parseInt(targetMonth) - mnth;
		             if (Math.abs(month) > 120) throw new AssertionError("Target date is more than 10 years away");
	                if (month > 0) {
		                while (month-- > 0) picker.findElement(By.xpath("//*[@href='javascript:nextMonth()']")).click();
		            } else if (month < 0 ){
		                while (month++ < 0) picker.findElement(By.xpath("//*[@class='ngb-dp-navigation-chevron']")).click();
		            }
	                
	                Integer yearinCalender = Integer.parseInt((picker.findElement(By.xpath("//*[@class='custom-select d-inline-block']")).getText()));
	                int year = Integer.parseInt(targetYear) - yearinCalender;
	                
	                if (year > 0) {
		                while (year-- > 0) picker.findElement(By.xpath("//*[@href='javascript:nextYear()']")).click();
		            } else if (year < 0 ){
		                while (year++ < 0) picker.findElement(By.xpath("//*[@href='javascript:prevYear()']")).click();
		                
		            }
		            else
	                {
	                	
	                }
		            WebElement tableOfDays = picker.findElement(By.xpath("//ngb-datepicker-month-view[@class='d-block']]"));
		            for (WebElement we : tableOfDays.findElements(By.tagName("span"))) {
		               if (we.getText().contains((targetday))) {
		                    we.click();
		                    extentTestReport.log(LogStatus.PASS, "Selected "+ targetDate+" from Calender");
							CucumberTestAutomationLogger.writeToLog("SeleniumKeywordslib","datePicker()", "'"+ targetDate + "' =>Selected");
					}
				}
				
				status = "true";
				return status;
			}
			else if (list.size() == 1) {
			CucumberTestAutomationLogger.writeToLog("SeleniumKeywordslib","datePicker() ", "found more than one element");
			CucumberTestAutomationLogger.writeToLog("SeleniumKeywordslib","datePicker() ", "clicking 1st element");
			
			//   WebElement element = list.get(0);
	         //   element.click();
	      //      WebElement picker = webDriver.findElement(By.xpath("//*[@src='../assets/images/calendar.svg']"));
			    Select select = new Select(webDriver.findElement(By.xpath("//select[@class='custom-select d-inline-block'][1]")));
			    WebElement option = select.getFirstSelectedOption();
			    String mnthdisplayed = option.getText();
			  
            
	            Integer mnth = MONTH_TO_CALENDAR_INDEX.get(mnthdisplayed);
	            int month = MONTH_TO_CALENDAR_INDEX.get(targetMonth) - mnth;
	             if (Math.abs(month) > 120) throw new AssertionError("Target date is more than 10 years away");
                if (month > 0) {
	                while (month-- > 0) webDriver.findElement(By.xpath("//*[@class='ngb-dp-navigation-chevron right']")).click();
	            } else if (month < 0 ){
	                while (month++ < 0) webDriver.findElement(By.xpath("//*[@class='ngb-dp-navigation-chevron']")).click();
	            }
                Select select1 = new Select(webDriver.findElement(By.xpath("//select[@class='custom-select d-inline-block'][2]")));
			    WebElement option1 = select1.getFirstSelectedOption();
			    String yeardisplayed = option1.getText();
			  
                Integer yearinCalender = Integer.parseInt(yeardisplayed);
                int year = Integer.parseInt(targetYear)- yearinCalender;
                Select selectyear = new Select(webDriver.findElement(By.xpath("//select[@class='custom-select d-inline-block'][2]")));
  			   
                if (year > 0) {
	                //while (year-- > 0) webDriver.findElement(By.xpath("//*[@href='javascript:nextYear()']")).click();
                	while (year-- > 0) 
                	 selectyear.selectByVisibleText(targetYear);

                	
	            } else if (year < 0 ){
	            	 selectyear.selectByVisibleText(targetYear);
	                
	            }
	            else
                {
                	
                }
                WebElement tableOfDays = webDriver.findElement(By.xpath("//ngb-datepicker-month-view[@class='d-block']"));
	            for (WebElement we : tableOfDays.findElements(By.tagName("span"))) {
	            	 targetday= targetday.replaceFirst("^0+(?!$)", "");
	            	if (we.getText().equals((targetday))) {
	            		Thread.sleep(1500);
	            		act.moveToElement(webDriver.findElement(By.xpath("//span[normalize-space(text()) ="+ "'"+targetday +"'"+"]"))).click(webDriver.findElement(By.xpath("//span[normalize-space(text()) ="+ "'"+targetday +"'"+"]"))).build().perform();
	                    status="true";
	                    extentTestReport.log(LogStatus.PASS, "Selected "+ targetDate+" from Calender");
						CucumberTestAutomationLogger.writeToLog("SeleniumKeywordslib","datePicker()", "'"+ targetDate + "' =>Selected");
						return status;
	               }
			}
			
			
		}else {
				extentTestReport.log(LogStatus.FAIL, "move to element" +objName);
				CucumberTestAutomationLogger.writeToLog("SeleniumKeywordslib","datePicker ", "'" + objName+ "Object was not visible");
				status = "Link was not visible";
				return status;
			}

	
	}catch (NoSuchElementException ns) {
        ns.printStackTrace();
        CucumberTestAutomationLogger.writeToLog("SeleniumKeywordslib", "datePicker() ","Object not found exeption thrown");
        CucumberTestAutomationLogger.writeToLog("SeleniumKeywordslib", "datePicker() ", "'" + objName + "'object does not exist");
        status = "false";
        extentTestReport.log(LogStatus.FAIL, "Unable to click on " +objName+ "Calender");
        return status;
 } catch (Exception ex) {
 	try
 	{
        ex.printStackTrace();
        CucumberTestAutomationLogger.writeToLog("SeleniumKeywordslib", "datePicker() ","Exception occurred");
        extentTestReport.log(LogStatus.FAIL,"select " +objName+ "option");
        String err[]=ex.getMessage().split("\n");
		status = "Exception " +err[0].replaceAll("'", "") + " Exception Occurred";
		return status;
 	}
 	finally
 	{
 		     extentTestReport.log(LogStatus.FAIL, "Unable to click on " +objName+ "Calender");
	         extentTestReport.log(LogStatus.FAIL, extentTestReport.addScreenCapture(ExtentReportManager.CaptureScreen(webDriver, propertyFileReader.getValue("SNAPSHOT_FOLDER")))); 
	    	// extentReports.endTest(extentTestReport);
	    	// extentReports.flush();
	         //extentReports.close(); 
	        webDriver.close();
	         //webDriver.quit();
	         
 	}
 }
		return status;
}*/
/*public String datePickerDueDate(WebDriver webDriver, String objName,String targetDate,ExtentReports extentReports,ExtentTest extentTestReport) {
	String status = "false";
	  final Map<String,Integer> MONTH_TO_CALENDAR_INDEX = new HashMap<String,Integer>();
		Actions act = new Actions(webDriver);
	    MONTH_TO_CALENDAR_INDEX.put("Jan",  1);
	    MONTH_TO_CALENDAR_INDEX.put("Feb",2);
	    MONTH_TO_CALENDAR_INDEX.put("Mar",3);
	    MONTH_TO_CALENDAR_INDEX.put("Apr",4);
	    MONTH_TO_CALENDAR_INDEX.put("May",5);
	    MONTH_TO_CALENDAR_INDEX.put("Jun",6);
	    MONTH_TO_CALENDAR_INDEX.put("Jul",7);
	    MONTH_TO_CALENDAR_INDEX.put("Aug",8);
	    MONTH_TO_CALENDAR_INDEX.put("Sep",9);
	    MONTH_TO_CALENDAR_INDEX.put("Oct",10);
	    MONTH_TO_CALENDAR_INDEX.put("Nov",11);
	    MONTH_TO_CALENDAR_INDEX.put("Dec",12);

	    
	    
	   String menuItems=  targetDate;
       String[] parts = menuItems.split("-");
        String targetday= parts[0];
        String targetMonth = parts[1];
        String targetYear =parts[2];
        
       
        
          try{  
	  //  List<WebElement> list = getElements(webDriver, objName, selector,selectorvalue);
			CucumberTestAutomationLogger.writeToLog("SeleniumKeywordslib", "datePicker() ","web elements size - " + list.size());
			if (list.size() > 1) {
				CucumberTestAutomationLogger.writeToLog("SeleniumKeywordslib","datePicker() ", "found more than one element");
				CucumberTestAutomationLogger.writeToLog("SeleniumKeywordslib","datePicker() ", "clicking 1st element");
				
				   Select select = new Select(webDriver.findElement(By.xpath("//select[@class='custom-select d-inline-block'][1]")));
				    WebElement option = select.getFirstSelectedOption();
				    String mnthdisplayed = option.getText();
				   Integer mnth = MONTH_TO_CALENDAR_INDEX.get(mnthdisplayed);
		            int month = MONTH_TO_CALENDAR_INDEX.get(targetMonth) - mnth;
		             if (Math.abs(month) > 120) throw new AssertionError("Target date is more than 10 years away");
	                if (month > 0) {
		                while (month-- > 0) webDriver.findElement(By.xpath("//*[@class='ngb-dp-navigation-chevron right']")).click();
		            } else if (month < 0 ){
		                while (month++ < 0) webDriver.findElement(By.xpath("//*[@class='ngb-dp-navigation-chevron']")).click();
		            }
	                Select select1 = new Select(webDriver.findElement(By.xpath("//select[@class='custom-select d-inline-block'][2]")));
				    WebElement option1 = select1.getFirstSelectedOption();
				    String yeardisplayed = option1.getText();
				    Integer yearinCalender = Integer.parseInt(yeardisplayed);
	                int year = Integer.parseInt(targetYear)- yearinCalender;
	                Select selectyear = new Select(webDriver.findElement(By.xpath("//select[@class='custom-select d-inline-block'][2]")));
	  			   
	                if (year > 0) {
		                //while (year-- > 0) webDriver.findElement(By.xpath("//*[@href='javascript:nextYear()']")).click();
	                	while (year-- > 0) 
	                	 selectyear.selectByVisibleText(targetYear);

	                	
		            } else if (year < 0 ){
		            	 selectyear.selectByVisibleText(targetYear);
		                
		            }
		            else
	                {
	                	
	                }
	                WebElement tableOfDays = webDriver.findElement(By.xpath("//ngb-datepicker-month-view[@class='d-block']"));
		            for (WebElement we : tableOfDays.findElements(By.tagName("div"))) {
		            	 targetday= targetday.replaceFirst("^0+(?!$)", "");
		            	if (we.getText().equals((targetday))) {
		            		Thread.sleep(1500);
		            		act.moveToElement(webDriver.findElement(By.xpath("//div[normalize-space(text()) ="+ "'"+targetday +"'"+"]"))).click(webDriver.findElement(By.xpath("//div[normalize-space(text()) ="+ "'"+targetday +"'"+"]"))).build().perform();
		                    status="true";
		                    extentTestReport.log(LogStatus.PASS, "Selected "+ targetDate+" from Calender");
							CucumberTestAutomationLogger.writeToLog("SeleniumKeywordslib","datePicker()", "'"+ targetDate + "' =>Selected");
							return status;
		               }
				}
			}
			else if (list.size() == 1) {
			CucumberTestAutomationLogger.writeToLog("SeleniumKeywordslib","datePicker() ", "found more than one element");
			CucumberTestAutomationLogger.writeToLog("SeleniumKeywordslib","datePicker() ", "clicking 1st element");
		  Select select = new Select(webDriver.findElement(By.xpath("//select[@class='custom-select d-inline-block'][1]")));
			    WebElement option = select.getFirstSelectedOption();
			    String mnthdisplayed = option.getText();
			  Integer mnth = MONTH_TO_CALENDAR_INDEX.get(mnthdisplayed);
	            int month = MONTH_TO_CALENDAR_INDEX.get(targetMonth) - mnth;
	             if (Math.abs(month) > 120) throw new AssertionError("Target date is more than 10 years away");
                if (month > 0) {
	                while (month-- > 0) webDriver.findElement(By.xpath("//*[@class='ngb-dp-navigation-chevron right']")).click();
	            } else if (month < 0 ){
	                while (month++ < 0) webDriver.findElement(By.xpath("//*[@class='ngb-dp-navigation-chevron']")).click();
	            }
                Select select1 = new Select(webDriver.findElement(By.xpath("//select[@class='custom-select d-inline-block'][2]")));
			    WebElement option1 = select1.getFirstSelectedOption();
			    String yeardisplayed = option1.getText();
			    Integer yearinCalender = Integer.parseInt(yeardisplayed);
                int year = Integer.parseInt(targetYear)- yearinCalender;
                Select selectyear = new Select(webDriver.findElement(By.xpath("//select[@class='custom-select d-inline-block'][2]")));
  			   
                if (year > 0) {
	                //while (year-- > 0) webDriver.findElement(By.xpath("//*[@href='javascript:nextYear()']")).click();
                	while (year-- > 0) 
                	 selectyear.selectByVisibleText(targetYear);

                	
	            } else if (year < 0 ){
	            	 selectyear.selectByVisibleText(targetYear);
	                
	            }
	            else
                {
                	
                }
                WebElement tableOfDays = webDriver.findElement(By.xpath("//ngb-datepicker-month-view[@class='d-block']"));
	            for (WebElement we : tableOfDays.findElements(By.tagName("div"))) {
	            	 targetday= targetday.replaceFirst("^0+(?!$)", "");
	            	if (we.getText().equals((targetday))) {
	            		Thread.sleep(1500);
	            		act.moveToElement(webDriver.findElement(By.xpath("//div[normalize-space(text()) ="+ "'"+targetday +"'"+"]"))).click(webDriver.findElement(By.xpath("//div[normalize-space(text()) ="+ "'"+targetday +"'"+"]"))).build().perform();
	                    status="true";
	                    extentTestReport.log(LogStatus.PASS, "Selected "+ targetDate+" from Calender");
						CucumberTestAutomationLogger.writeToLog("SeleniumKeywordslib","datePicker()", "'"+ targetDate + "' =>Selected");
						return status;
	               }
			}
			
			
		}else {
				extentTestReport.log(LogStatus.FAIL, "move to element" +objName);
				CucumberTestAutomationLogger.writeToLog("SeleniumKeywordslib","datePicker ", "'" + objName+ "Object was not visible");
				status = "Link was not visible";
				return status;
			}
}catch (NoSuchElementException ns) {
        ns.printStackTrace();
        CucumberTestAutomationLogger.writeToLog("SeleniumKeywordslib", "datePicker() ","Object not found exeption thrown");
        CucumberTestAutomationLogger.writeToLog("SeleniumKeywordslib", "datePicker() ", "'" + objName + "'object does not exist");
        status = "false";
        extentTestReport.log(LogStatus.FAIL, "Unable to click on " +objName+ "Calender");
        return status;
 } catch (Exception ex) {
 	try
 	{
        ex.printStackTrace();
        CucumberTestAutomationLogger.writeToLog("SeleniumKeywordslib", "datePicker() ","Exception occurred");
        extentTestReport.log(LogStatus.FAIL,"select " +objName+ "option");
        String err[]=ex.getMessage().split("\n");
		status = "Exception " +err[0].replaceAll("'", "") + " Exception Occurred";
		return status;
 	}
 	finally
 	{
 		     extentTestReport.log(LogStatus.FAIL, "Unable to click on " +objName+ "Calender");
	         extentTestReport.log(LogStatus.FAIL, extentTestReport.addScreenCapture(ExtentReportManager.CaptureScreen(webDriver, propertyFileReader.getValue("SNAPSHOT_FOLDER")))); 
	    	// extentReports.endTest(extentTestReport);
	    	// extentReports.flush();
	         //extentReports.close(); 
	        webDriver.close();
	         //webDriver.quit();
	         
 	}
 }
		return status;
}*/
/*public String datePickerMyActionItems(WebDriver webDriver, String objName,String targetDate,ExtentReports extentReports,ExtentTest extentTestReport) {
	String status = "false";
	  final Map<String,Integer> MONTH_TO_CALENDAR_INDEX = new HashMap<String,Integer>();
		Actions act = new Actions(webDriver);
	    MONTH_TO_CALENDAR_INDEX.put("Jan",  1);
	    MONTH_TO_CALENDAR_INDEX.put("Feb",2);
	    MONTH_TO_CALENDAR_INDEX.put("Mar",3);
	    MONTH_TO_CALENDAR_INDEX.put("Apr",4);
	    MONTH_TO_CALENDAR_INDEX.put("May",5);
	    MONTH_TO_CALENDAR_INDEX.put("Jun",6);
	    MONTH_TO_CALENDAR_INDEX.put("Jul",7);
	    MONTH_TO_CALENDAR_INDEX.put("Aug",8);
	    MONTH_TO_CALENDAR_INDEX.put("Sep",9);
	    MONTH_TO_CALENDAR_INDEX.put("Oct",10);
	    MONTH_TO_CALENDAR_INDEX.put("Nov",11);
	    MONTH_TO_CALENDAR_INDEX.put("Dec",12);

	    
	    
	   String menuItems=  targetDate;
       String[] parts = menuItems.split("-");
        String targetday= parts[0];
        String targetMonth = parts[1];
        String targetYear =parts[2];
        
       
        
          try{  
	  //  List<WebElement> list = getElements(webDriver, objName, selector,selectorvalue);
			CucumberTestAutomationLogger.writeToLog("SeleniumKeywordslib", "datePicker() ","web elements size - " + list.size());
			if (list.size() > 1) {
				CucumberTestAutomationLogger.writeToLog("SeleniumKeywordslib","datePicker() ", "found more than one element");
				CucumberTestAutomationLogger.writeToLog("SeleniumKeywordslib","datePicker() ", "clicking 1st element");
				
				    WebElement element = list.get(0);
		            element.click();
		            WebElement picker = webDriver.findElement(By.xpath("//*[@src='../assets/images/calendar.svg']"));
	                Integer mnth = MONTH_TO_CALENDAR_INDEX.get(picker.findElement(By.xpath("//*[@class='custom-select d-inline-block']")).getText());
		            int month = Integer.parseInt(targetMonth) - mnth;
		             if (Math.abs(month) > 120) throw new AssertionError("Target date is more than 10 years away");
	                if (month > 0) {
		                while (month-- > 0) picker.findElement(By.xpath("//*[@href='javascript:nextMonth()']")).click();
		            } else if (month < 0 ){
		                while (month++ < 0) picker.findElement(By.xpath("//*[@class='ngb-dp-navigation-chevron']")).click();
		            }
	                
	                Integer yearinCalender = Integer.parseInt((picker.findElement(By.xpath("//*[@class='custom-select d-inline-block']")).getText()));
	                int year = Integer.parseInt(targetYear) - yearinCalender;
	                
	                if (year > 0) {
		                while (year-- > 0) picker.findElement(By.xpath("//*[@href='javascript:nextYear()']")).click();
		            } else if (year < 0 ){
		                while (year++ < 0) picker.findElement(By.xpath("//*[@href='javascript:prevYear()']")).click();
		                
		            }
		            else
	                {
	                	
	                }
		            WebElement tableOfDays = picker.findElement(By.xpath("//ngb-datepicker-month-view[@class='d-block']]"));
		            for (WebElement we : tableOfDays.findElements(By.tagName("span"))) {
		               if (we.getText().contains((targetday))) {
		                    we.click();
		                    extentTestReport.log(LogStatus.PASS, "Selected "+ targetDate+" from Calender");
							CucumberTestAutomationLogger.writeToLog("SeleniumKeywordslib","datePicker()", "'"+ targetDate + "' =>Selected");
					}
				}
				
				status = "true";
				return status;
			}
			else if (list.size() == 1) {
			CucumberTestAutomationLogger.writeToLog("SeleniumKeywordslib","datePicker() ", "found more than one element");
			CucumberTestAutomationLogger.writeToLog("SeleniumKeywordslib","datePicker() ", "clicking 1st element");
			
		   Select select = new Select(webDriver.findElement(By.xpath("//select[@class='custom-select d-inline-block'][1]")));
			    WebElement option = select.getFirstSelectedOption();
			    String mnthdisplayed = option.getText();
			  Integer mnth = MONTH_TO_CALENDAR_INDEX.get(mnthdisplayed);
	            int month = MONTH_TO_CALENDAR_INDEX.get(targetMonth) - mnth;
	             if (Math.abs(month) > 120) throw new AssertionError("Target date is more than 10 years away");
                if (month > 0) {
	                while (month-- > 0) webDriver.findElement(By.xpath("//*[@class='ngb-dp-navigation-chevron right']")).click();
	            } else if (month < 0 ){
	                while (month++ < 0) webDriver.findElement(By.xpath("//*[@class='ngb-dp-navigation-chevron']")).click();
	            }
                Select select1 = new Select(webDriver.findElement(By.xpath("//select[@class='custom-select d-inline-block'][2]")));
			    WebElement option1 = select1.getFirstSelectedOption();
			    String yeardisplayed = option1.getText();
			   Integer yearinCalender = Integer.parseInt(yeardisplayed);
                int year = Integer.parseInt(targetYear)- yearinCalender;
                Select selectyear = new Select(webDriver.findElement(By.xpath("//select[@class='custom-select d-inline-block'][2]")));
  			   
                if (year > 0) {
	                //while (year-- > 0) webDriver.findElement(By.xpath("//*[@href='javascript:nextYear()']")).click();
                	while (year-- > 0) 
                	 selectyear.selectByVisibleText(targetYear);

                	
	            } else if (year < 0 ){
	            	 selectyear.selectByVisibleText(targetYear);
	                
	            }
	            else
                {
                	
                }
                WebElement tableOfDays = webDriver.findElement(By.xpath("//ngb-datepicker-month-view[@class='d-block']"));
	            for (WebElement we : tableOfDays.findElements(By.tagName("div"))) {
	            	 targetday= targetday.replaceFirst("^0+(?!$)", "");
	            	if (we.getText().equals((targetday))) {
	            		Thread.sleep(1500);
	            		act.moveToElement(webDriver.findElement(By.xpath("//div[@class=\"ngb-dp-day\"]//following::span[normalize-space(text()) ="+ "'"+targetday +"'"+"]"))).click(webDriver.findElement(By.xpath("//div[@class=\"ngb-dp-day\"]//following::span[normalize-space(text()) ="+ "'"+targetday +"'"+"]"))).build().perform();
	                    status="true";
	                    extentTestReport.log(LogStatus.PASS, "Selected "+ targetDate+" from Calender");
						CucumberTestAutomationLogger.writeToLog("SeleniumKeywordslib","datePicker()", "'"+ targetDate + "' =>Selected");
						return status;
	               }
			}
			
			
		}else {
				extentTestReport.log(LogStatus.FAIL, "move to element" +objName);
				CucumberTestAutomationLogger.writeToLog("SeleniumKeywordslib","datePicker ", "'" + objName+ "Object was not visible");
				status = "Link was not visible";
				return status;
			}
}catch (NoSuchElementException ns) {
        ns.printStackTrace();
        CucumberTestAutomationLogger.writeToLog("SeleniumKeywordslib", "datePicker() ","Object not found exeption thrown");
        CucumberTestAutomationLogger.writeToLog("SeleniumKeywordslib", "datePicker() ", "'" + objName + "'object does not exist");
        status = "false";
        extentTestReport.log(LogStatus.FAIL, "Unable to click on " +objName+ "Calender");
        return status;
 } catch (Exception ex) {
 	try
 	{
        ex.printStackTrace();
        CucumberTestAutomationLogger.writeToLog("SeleniumKeywordslib", "datePicker() ","Exception occurred");
        extentTestReport.log(LogStatus.FAIL,"select " +objName+ "option");
        String err[]=ex.getMessage().split("\n");
		status = "Exception " +err[0].replaceAll("'", "") + " Exception Occurred";
		return status;
 	}
 	finally
 	{
 		     extentTestReport.log(LogStatus.FAIL, "Unable to click on " +objName+ "Calender");
	         extentTestReport.log(LogStatus.FAIL, extentTestReport.addScreenCapture(ExtentReportManager.CaptureScreen(webDriver, propertyFileReader.getValue("SNAPSHOT_FOLDER")))); 
	    	// extentReports.endTest(extentTestReport);
	    	// extentReports.flush();
	         //extentReports.close(); 
	        webDriver.close();
	         //webDriver.quit();
	         
 	}
 }
		return status;
}
public String datePickerPCS(WebDriver webDriver, String objName,String targetDate,ExtentReports extentReports,ExtentTest extentTestReport) {
	String status = "false";
	  final Map<String,Integer> MONTH_TO_CALENDAR_INDEX = new HashMap<String,Integer>();
		Actions act = new Actions(webDriver);
	    MONTH_TO_CALENDAR_INDEX.put("Jan",  1);
	    MONTH_TO_CALENDAR_INDEX.put("Feb",2);
	    MONTH_TO_CALENDAR_INDEX.put("Mar",3);
	    MONTH_TO_CALENDAR_INDEX.put("Apr",4);
	    MONTH_TO_CALENDAR_INDEX.put("May",5);
	    MONTH_TO_CALENDAR_INDEX.put("Jun",6);
	    MONTH_TO_CALENDAR_INDEX.put("Jul",7);
	    MONTH_TO_CALENDAR_INDEX.put("Aug",8);
	    MONTH_TO_CALENDAR_INDEX.put("Sep",9);
	    MONTH_TO_CALENDAR_INDEX.put("Oct",10);
	    MONTH_TO_CALENDAR_INDEX.put("Nov",11);
	    MONTH_TO_CALENDAR_INDEX.put("Dec",12);

	    
	    
	   String menuItems=  targetDate;
       String[] parts = menuItems.split("-");
        String targetday= parts[0];
        String targetMonth = parts[1];
        String targetYear =parts[2];
        
       
        
          try{  
	  //  List<WebElement> list = getElements(webDriver, objName, selector,selectorvalue);
			CucumberTestAutomationLogger.writeToLog("SeleniumKeywordslib", "datePicker() ","web elements size - " + list.size());
			if (list.size() > 1) {
				CucumberTestAutomationLogger.writeToLog("SeleniumKeywordslib","datePicker() ", "found more than one element");
				CucumberTestAutomationLogger.writeToLog("SeleniumKeywordslib","datePicker() ", "clicking 1st element");
				
				    WebElement element = list.get(0);
		            element.click();
		            WebElement picker = webDriver.findElement(By.xpath(selectorvalue));
	                Integer mnth = MONTH_TO_CALENDAR_INDEX.get(picker.findElement(By.xpath("//*[@class='custom-select d-inline-block']")).getText());
		            int month = Integer.parseInt(targetMonth) - mnth;
		             if (Math.abs(month) > 120) throw new AssertionError("Target date is more than 10 years away");
	                if (month > 0) {
		                while (month-- > 0) picker.findElement(By.xpath("//*[@href='javascript:nextMonth()']")).click();
		            } else if (month < 0 ){
		                while (month++ < 0) picker.findElement(By.xpath("//*[@class='ngb-dp-navigation-chevron']")).click();
		            }
	                
	                Integer yearinCalender = Integer.parseInt((picker.findElement(By.xpath("//*[@class='custom-select d-inline-block']")).getText()));
	                int year = Integer.parseInt(targetYear) - yearinCalender;
	                
	                if (year > 0) {
		                while (year-- > 0) picker.findElement(By.xpath("//*[@href='javascript:nextYear()']")).click();
		            } else if (year < 0 ){
		                while (year++ < 0) picker.findElement(By.xpath("//*[@href='javascript:prevYear()']")).click();
		                
		            }
		            else
	                {
	                	
	                }
		            WebElement tableOfDays = picker.findElement(By.xpath("//ngb-datepicker-month-view[@class='d-block']]"));
		            for (WebElement we : tableOfDays.findElements(By.tagName("span"))) {
		               if (we.getText().contains((targetday))) {
		                    we.click();
		                    extentTestReport.log(LogStatus.PASS, "Selected "+ targetDate+" from Calender");
							CucumberTestAutomationLogger.writeToLog("SeleniumKeywordslib","datePicker()", "'"+ targetDate + "' =>Selected");
					}
				}
				
				status = "true";
				return status;
			}
			else if (list.size() == 1) {
			CucumberTestAutomationLogger.writeToLog("SeleniumKeywordslib","datePicker() ", "found more than one element");
			  Select select = new Select(webDriver.findElement(By.xpath("//select[@class='ui-datepicker-month']")));
			    WebElement option = select.getFirstSelectedOption();
			    String mnthdisplayed = option.getText();
			  Integer mnth = MONTH_TO_CALENDAR_INDEX.get(mnthdisplayed);
	           
	            int month = Integer.parseInt(targetMonth) - mnth;
	             if (Math.abs(month) > 120) throw new AssertionError("Target date is more than 10 years away");
                if (month > 0) {
	                while (month-- > 0) webDriver.findElement(By.xpath("//*[@class='ui-icon ui-icon-circle-triangle-e']")).click();
	            } else if (month < 0 ){
	            while (month++ < 0) webDriver.findElement(By.xpath("//*[@class='ui-icon ui-icon-circle-triangle-w']")).click();
	            }
	            else
	            {
	            	
	            }
                Select select1 = new Select(webDriver.findElement(By.xpath("//select[@class='ui-datepicker-year']")));
			    WebElement option1 = select1.getFirstSelectedOption();
			    String yeardisplayed = option1.getText();
			    Integer yearinCalender = Integer.parseInt(yeardisplayed);
                int year = Integer.parseInt(targetYear)- yearinCalender;
                Select selectyear = new Select(webDriver.findElement(By.xpath("//select[@class='ui-datepicker-month']")));
  			   
                if (year > 0) {
	                //while (year-- > 0) webDriver.findElement(By.xpath("//*[@href='javascript:nextYear()']")).click();
                	while (year-- > 0) 
                	 selectyear.selectByVisibleText(targetYear);

                	
	            } else if (year < 0 ){
	            	 selectyear.selectByVisibleText(targetYear);
	                
	            }
	            else
                {
                	
                }
                targetday= targetday.replaceFirst("^0+(?!$)", "");
                act.moveToElement(webDriver.findElement(By.xpath("//table[@class='ui-datepicker-calendar']//thead//following::*[normalize-space(text()) ="+ "'"+targetday +"'"+"]"))).click(webDriver.findElement(By.xpath("//table[@class='ui-datepicker-calendar']//thead//following::*[normalize-space(text()) ="+ "'"+targetday +"'"+"]"))).build().perform();
                status="true";
                extentTestReport.log(LogStatus.PASS, "Selected "+ targetDate+" from Calender");
				CucumberTestAutomationLogger.writeToLog("SeleniumKeywordslib","datePicker()", "'"+ targetDate + "' =>Selected");
				//return status;
                WebElement tableOfDays = webDriver.findElement(By.xpath("//table[@class='ui-datepicker-calendar']"));
	            for (WebElement we : tableOfDays.findElements(By.tagName("//td"))) {
	            	 targetday= targetday.replaceFirst("^0+(?!$)", "");
	            	if (we.getText().equals((targetday))) {
	            		Thread.sleep(1500);
	            		act.moveToElement(webDriver.findElement(By.xpath("//table[@class='ui-datepicker-calendar']//thead//following::*[normalize-space(text()) ="+ "'"+targetday +"'"+"]"))).click(webDriver.findElement(By.xpath("//table[@class='ui-datepicker-calendar']//thead//following::*[normalize-space(text()) ="+ "'"+targetday +"'"+"]"))).build().perform();
	                    status="true";
	                    extentTestReport.log(LogStatus.PASS, "Selected "+ targetDate+" from Calender");
						CucumberTestAutomationLogger.writeToLog("SeleniumKeywordslib","datePicker()", "'"+ targetDate + "' =>Selected");
						return status;
	               }
			}
			
			
		}else {
				extentTestReport.log(LogStatus.FAIL, "move to element" +objName);
				CucumberTestAutomationLogger.writeToLog("SeleniumKeywordslib","datePicker ", "'" + objName+ "Object was not visible");
				status = "Link was not visible";
				return status;
			}
}catch (NoSuchElementException ns) {
        ns.printStackTrace();
        CucumberTestAutomationLogger.writeToLog("SeleniumKeywordslib", "datePicker() ","Object not found exeption thrown");
        CucumberTestAutomationLogger.writeToLog("SeleniumKeywordslib", "datePicker() ", "'" + objName + "'object does not exist");
        status = "false";
        extentTestReport.log(LogStatus.FAIL, "Unable to click on " +objName+ "Calender");
        return status;
 } catch (Exception ex) {
 	try
 	{
        ex.printStackTrace();
        CucumberTestAutomationLogger.writeToLog("SeleniumKeywordslib", "datePicker() ","Exception occurred");
        extentTestReport.log(LogStatus.FAIL,"select " +objName+ "option");
        String err[]=ex.getMessage().split("\n");
		status = "Exception " +err[0].replaceAll("'", "") + " Exception Occurred";
		return status;
 	}
 	finally
 	{
 		     extentTestReport.log(LogStatus.FAIL, "Unable to click on " +objName+ "Calender");
	         extentTestReport.log(LogStatus.FAIL, extentTestReport.addScreenCapture(ExtentReportManager.CaptureScreen(webDriver, propertyFileReader.getValue("SNAPSHOT_FOLDER")))); 
	    	// extentReports.endTest(extentTestReport);
	    	// extentReports.flush();
	         //extentReports.close(); 
	        webDriver.close();
	         //webDriver.quit();
	         
 	}
 }
		return status;
}

 * ##########################################################################
 * ##################### ' FUNCTION NAME - selectRadioButton() '
 * DESCRIPTION - This function will Select the Radio button . ' INPUT
 * PARAMETERS - Obj - Name of the Object
 * '################################################################################################
 

		public String selectRadioButtonbyText(WebDriver webDriver, String objName,String selector,String selectorvalue,String radiobuttonName,ExtentReports extentReports,ExtentTest extentTestReport) {
			String status = "false";
			try {
				String[] paramvalues= radiobuttonName.split(",");
				
				for(int i=0;i<paramvalues.length;i++)
				{
				selectorvalue = "//*[text()="+ "'"+paramvalues[i] +"'"+"]//preceding::input[1]";
				 waitForExistence(webDriver, objName, selector,selectorvalue);
				
				List<WebElement> list = getElements(webDriver, objName, selector,selectorvalue);
				
				CucumberTestAutomationLogger.writeToLog("SeleniumKeywordslib", "selectRadioButton() ","web elements size - " + list.size());
				if (list.size() > 1) {
					CucumberTestAutomationLogger.writeToLog("SeleniumKeywordslib","selectRadioButton()","found more than one element");
					CucumberTestAutomationLogger.writeToLog("SeleniumKeywordslib","selectRadioButton()", "clicking 1st element");
					for (WebElement element : list) {
						if ((element.isDisplayed()) && (!element.isSelected())){
							   webDriver.manage().timeouts().pageLoadTimeout(60, TimeUnit.SECONDS);
								element.click();
							    webDriver.manage().timeouts().pageLoadTimeout(60, TimeUnit.SECONDS);
								status = "true";
								extentTestReport.log(LogStatus.PASS, "click on " +objName +" radio button");
								CucumberTestAutomationLogger.writeToLog("SeleniumKeywordslib","selectRadioButton()", "after click");
								CucumberTestAutomationLogger.writeToLog("SeleniumKeywordslib","selectRadioButton()","'"+ objName + "' => Radio Button was successfully clicked");
							} 
						else if ((element.isDisplayed()) && (element.isSelected())){
							   webDriver.manage().timeouts().pageLoadTimeout(60, TimeUnit.SECONDS);
								element.click();
							    webDriver.manage().timeouts().pageLoadTimeout(60, TimeUnit.SECONDS);
								status = "true";
								extentTestReport.log(LogStatus.PASS, "click on " +objName +" radio button");
								CucumberTestAutomationLogger.writeToLog("SeleniumKeywordslib","selectRadioButton()", "after click");
								CucumberTestAutomationLogger.writeToLog("SeleniumKeywordslib","selectRadioButton()","'"+ objName + "' => Radio Button was successfully clicked");
							
							} 
						else {
								CucumberTestAutomationLogger.writeToLog("SeleniumKeywordslib","selectRadioButton()","'"+ objName + "' Radio Button was not visible_______");
							    status = "false";
								CucumberTestAutomationLogger.writeToLog("SeleniumKeywordslib","selectRadioButton()", "after click");
								return status;
							}
							
						} 
					
				}	
				 else if (list.size() == 1) {
					CucumberTestAutomationLogger.writeToLog("SeleniumKeywordslib","selectRadioButton()", "clicking element");
					WebElement element = list.get(0);

					if (element != null) {
						if (element.isDisplayed()) {
							element.click();
							webDriver.manage().timeouts().pageLoadTimeout(60, TimeUnit.SECONDS);
							status = "true";
							CucumberTestAutomationLogger.writeToLog("SeleniumKeywordslib","selectRadioButton()","'"+ objName+ "' => Radio Button was successfully selected");
							CucumberTestAutomationLogger.writeToLog("SeleniumKeywordslib","selectRadioButton()", "after click");
							extentTestReport.log(LogStatus.PASS, "click on " +objName +"radio button");
							return status;
						} else {
							CucumberTestAutomationLogger.writeToLog("SeleniumKeywordslib","selectRadioButton()","'"+ objName + "' Radio Button was not visible******");
							element.sendKeys(Keys.ENTER);
							status = "true";
							extentTestReport.log(LogStatus.FAIL, objName +" radio button is not found");
							return status;
						}
					} else {
						CucumberTestAutomationLogger.writeToLog("SeleniumKeywordslib","selectRadioButton()", "'" + objName + "' Radio Button was not found");
						status = "Radio Button was not found";
						extentTestReport.log(LogStatus.FAIL, objName +" radio button is not found");
						return status;
					}
				} else {
					CucumberTestAutomationLogger.writeToLog("SeleniumKeywordslib","selectRadioButton()", "'" + objName+ "' Radio Button was not selected");
					status = "Radio Button was not selected";
					extentTestReport.log(LogStatus.FAIL, objName +" radio button is not found");
					return status;
				}
				
			
			}
				if(paramvalues.length==0)
				{
					extentTestReport.log(LogStatus.FAIL, "click on "+objName+" Radio Button");
					CucumberTestAutomationLogger.writeToLog("SeleniumKeywordslib","selectRadioButton() ", "'" + objName+ " RadioButton was not found");
					status = "CheckBox was not found";
				}
					
					return status;
			}catch (NoSuchElementException ns) {
	            ns.printStackTrace();
	            CucumberTestAutomationLogger.writeToLog("SeleniumKeywordslib", "selectRadioButton() ","Object not found exeption thrown");
	            CucumberTestAutomationLogger.writeToLog("SeleniumKeywordslib", "selectRadioButton() ", "'" + objName + "'object does not exist");
	            status = "false";
	            extentTestReport.log(LogStatus.FAIL, "Unable to select " +objName+ "Checkbox");
	            return status;
	     }   catch (Exception ex) {
				try
				{
					ex.printStackTrace();
					CucumberTestAutomationLogger.writeToLog("SeleniumKeywordslib","selectRadioButton()", "Exception occurred");
					CucumberTestAutomationLogger.writeToLog("SeleniumKeywordslib","selectRadioButton()", "Exception '" + ex.getMessage()+ "' Occurred");
					String err[]=ex.getMessage().split("\n");
					status =  "Exception " +err[0].replaceAll("'", "") + " Occurred";
					return status;
				}
				finally
				{
					 extentTestReport.log(LogStatus.FAIL, objName +" radio button is not found");
			         extentTestReport.log(LogStatus.FAIL, extentTestReport.addScreenCapture(ExtentReportManager.CaptureScreen(webDriver, propertyFileReader.getValue("SNAPSHOT_FOLDER")))); 
			    	// extentReports.endTest(extentTestReport);
			    	// extentReports.flush();
			         //extentReports.close(); 
			        webDriver.close();
			         //webDriver.quit();
			         
				}
			}
			
		}
	
		 
			 * ############################################################################################# 
			 * ' FUNCTION NAME - checkboxState ' DESCRIPTION - This
			 * function will be used to check the checkbox status ' INPUT PARAMETERS -
			 * '################################################################################################
			 
			public String checkboxState(WebDriver webDriver, String objName,String selector,String selectorvalue,ExtentReports extentReports,ExtentTest extentTestReport) {
				String status = "false";
				try {
					List<WebElement> list = getElements(webDriver, objName,selector,selectorvalue);

					CucumberTestAutomationLogger.writeToLog("SeleniumKeywordslib", "setCheckbox() ","web elements size - " + list.size());
					if (list.size() > 1) {
						CucumberTestAutomationLogger.writeToLog("SeleniumKeywordslib","setCheckbox() ", "found more than one element");
						CucumberTestAutomationLogger.writeToLog("SeleniumKeywordslib","setCheckbox() ", "clicking 1st element");
						for (WebElement element : list) {
							if (element.isDisplayed()) {
								
									if (element.isSelected()) {
										CucumberTestAutomationLogger.writeToLog("SeleniumKeywordslib","setCheckbox() ", "'" + objName+ "' => selected/Holiday");
										extentTestReport.log(LogStatus.PASS, "File Name " +objName);
										return "true";
									} else {
										CucumberTestAutomationLogger.writeToLog("SeleniumKeywordslib","setCheckbox() ", "'" + objName+ "' => is  not Holiday");
										extentTestReport.log(LogStatus.PASS, "File Name " +objName);
										return "false";
							}
							}
							else {
								CucumberTestAutomationLogger.writeToLog("SeleniumKeywordslib","setCheckbox() ", "'" + objName+ "CheckBox was not visible");
								status = "CheckBox was not visible";
								return status;
							}
						
					} 
					}else if (list.size() == 1) {
						WebElement element = list.get(0);
						if (element.isDisplayed()) {
					if (element.isSelected()) {
									CucumberTestAutomationLogger.writeToLog("SeleniumKeywordslib","setCheckbox() ", "'" + objName+ "' => selected/Holiday");
									extentTestReport.log(LogStatus.PASS, "File Name " +objName);
									return "true";
								} else {
									CucumberTestAutomationLogger.writeToLog("SeleniumKeywordslib","setCheckbox() ", "'" + objName+ "' => is  not Holiday");
									extentTestReport.log(LogStatus.PASS, "File Name " +objName);
									return "false";
								}
							
							}

						} else {
							CucumberTestAutomationLogger.writeToLog("SeleniumKeywordslib","setCheckbox() ", "'" + objName+ "CheckBox was not visible");
							status = "CheckBox was not visible";
							return status;
						}

					} 
				 catch (Exception exception) {
			  		 try
			  		 {
			  			    exception.printStackTrace();
			  				CucumberTestAutomationLogger.writeToLog("SeleniumKeywordslib","select Window() ", "Exeption thrown");
			  				CucumberTestAutomationLogger.writeToLog("SeleniumKeywordslib","select Window() ", "object doesnot exists");
			  				
			  		 }
			  		 finally{
			  				extentTestReport.log(LogStatus.FAIL, "Unable to switch to window ");
			  		        extentTestReport.log(LogStatus.FAIL, extentTestReport.addScreenCapture(ExtentReportManager.CaptureScreen(webDriver, propertyFileReader.getValue("SNAPSHOT_FOLDER")))); 
			  		    	// extentReports.endTest(extentTestReport);
			  		    	// extentReports.flush();
			  		        webDriver.close();
			  		         
			  			}
			  	}
				return status;
			}
			
	
 * ############################################################################################# 
 * ' FUNCTION NAME - getCellDataByRowNoAndColNo ' DESCRIPTION - This
 * function will be used to gettext for given colno and rowno in a table' INPUT PARAMETERS -
 * '################################################################################################
 
public String getCellDataByRowNoAndColNo(WebDriver webDriver, String objName,String selector,String selectorvalue, String rowNo, String colNo,ExtentReports extentReports,ExtentTest extentTestReport) {
	CucumberTestAutomationLogger.writeToLog("SeleniumKeywordslib", "getCellDataByRowNoAndColNo()","getCellDataByRowNoAndColNo  rowNo " + rowNo + " colNo " + colNo+ "..objName - " + objName);
	String value = null;
	String getVal = null;
	
	try {
		List<WebElement> list = getElements(webDriver, objName, selector,selectorvalue);
		WebElement we = null;
		if (list != null)
			we = list.get(0);

		if (we != null) {
		List<WebElement> rows = null;
		rows = (we.findElements(By.xpath(".//tr")));

			CucumberTestAutomationLogger.writeToLog("SeleniumKeywordslib","getCellDataByRowNoAndColNo() ", "rows - " + rows.size());
			if (rows != null) {
				WebElement weCell = (we.findElements(By.tagName("tr"))).get(Integer.parseInt(rowNo)).findElements(By.tagName("td")).get(Integer.parseInt(colNo));
				CucumberTestAutomationLogger.writeToLog("SeleniumKeywordslib","getCellDataByRowNoAndColNo()", "weCell - " + weCell);

							if (weCell != null) {
								getVal = weCell.getText();
								if (getVal != null) {
									value = getVal.toString();
									//extentTestReport.log(LogStatus.PASS," Value for  :  "+ colNo + "   in RowName : " + rowNo + " : is "+ value);
									CucumberTestAutomationLogger.writeToLog("SeleniumKeywordslib","getCellDataByRowNoAndColNo()", "'" + objName+ "'=>Get the value from column=:"+ colNo + "and RowNo=: " + rowNo + " : is "+ value);
									return value;
								} else {
									//extentTestReport.log(LogStatus.FAIL,"getCellDataByRowNoAndColNo()", getVal + " is null");
									CucumberTestAutomationLogger.writeToLog("SeleniumKeywordslib","getCellDataByRowNoAndColNo()", getVal + " is null");
								}
							} else {
								extentTestReport.log(LogStatus.FAIL,"getCellDataByRowNoAndColNo()", getVal + " is null");
								CucumberTestAutomationLogger.writeToLog("SeleniumKeywordslib","getCellDataByRowNoAndColNo()", "value is null");
							}
						}
		  } 
		else {
			extentTestReport.log(LogStatus.FAIL,"getCellDataByRowNoAndColNo()", "'" + objName+ "' =>does not exist!");
			CucumberTestAutomationLogger.writeToLog("SeleniumKeywordslib","getCellDataByRowNoAndColNo()", "'" + objName+ "' =>does not exist!");
			return null;
		}
	}catch (NoSuchElementException ns) {
		try
		{
		ns.printStackTrace();
		CucumberTestAutomationLogger.writeToLog("SeleniumKeywordslib", "getCellDataByRowNoAndColNo()","Object not found exeption thrown");
		CucumberTestAutomationLogger.writeToLog("SeleniumKeywordslib", "getCellDataByRowNoAndColNo()","'" + objName + "'object does not exist");
		return value;
		}
	 finally {
   		 extentTestReport.addScreenCapture(propertyFileReader.getValue("REPORT_PATH")+"objName");
    	 extentTestReport.log(LogStatus.FAIL, extentTestReport.addScreenCapture(ExtentReportManager.CaptureScreen(webDriver, propertyFileReader.getValue("SNAPSHOT_FOLDER")+"objName"))); 
    	// extentReports.endTest(extentTestReport);
    	// extentReports.flush();
    	 //extentReports.close(); 
	}
	}
	return value;
}

public HashMap<String, String> getCellDataByRownameAndColNameforDOubleValues(WebDriver webDriver, String objName,String selector,String selectorvalue, String rowName, String colName,ExtentReports extentReports,ExtentTest extentTestReport) {
	CucumberTestAutomationLogger.writeToLog("SeleniumKeywordslib", "getCellData()","getCellData  rowNo " + rowName + " colNo " + colName+ "..objName - " + objName);
	String value = null;
	String getVal = null;
	int rowNo = 0;
	int colNo = 0;
	int  row=0;
	int col=0;
	HashMap<String, String> positionvectorvalues = new HashMap<String, String>();
	try {
		List<WebElement> list = getElements(webDriver, objName, selector,selectorvalue);
		WebElement we = null;
		if (list != null)
			we = list.get(0);

		if (we != null) {
			List<WebElement> rows = null;
			rows = (we.findElements(By.xpath(".//tr")));

			CucumberTestAutomationLogger.writeToLog("SeleniumKeywordslib","getRowByContent() ", "rows - " + rows.size());
			if (rows != null) {
				int tempRowNo = 0;
				for (WebElement werowCell : rows) {
					String value1 = null;
					tempRowNo++;
		
			CucumberTestAutomationLogger.writeToLog("SeleniumKeywordslib","getCellData()", rowName + "X" + colName);
			value1 = werowCell.getText();
			CucumberTestAutomationLogger.writeToLog("SeleniumKeywordslib","getRowByContent() ", "VALUE - "+ value1);

			if (value1 != null) {
				if (value1.contains(rowName)) {
					rowNo = tempRowNo;
					CucumberTestAutomationLogger.writeToLog("SeleniumKeywordslib","getRowByContent() ","VALUES EQUAL");
					CucumberTestAutomationLogger.writeToLog("SeleniumKeywordslib","getRowByContent() ","Row number captured is = \""+ rowNo + "\"");
					row = rowNo;
					List<WebElement> cols = null;
					cols = (we.findElements(By.xpath(".//th")));

					CucumberTestAutomationLogger.writeToLog("SeleniumKeywordslib","getRowByContent() ", "rows - " + cols.size());
					if (cols != null) {
						int tempColNo =0;
						for (WebElement wecolCell : cols) {
							String value2 = null;
							tempColNo++;
					
					CucumberTestAutomationLogger.writeToLog("SeleniumKeywordslib","getCellData()", rowName + "X" + colName);
					value2 = wecolCell.getText();
					CucumberTestAutomationLogger.writeToLog("SeleniumKeywordslib","getRowByContent() ", "VALUE - "+ value2);

					if (value2 != null) {
						if (value2.equalsIgnoreCase(colName)) {
							colNo = tempColNo;
							CucumberTestAutomationLogger.writeToLog("SeleniumKeywordslib","getRowByContent() ","VALUES EQUAL");
							CucumberTestAutomationLogger.writeToLog("SeleniumKeywordslib","getRowByContent() ","Col number captured is = \""+ colNo + "\"");
							colNo=colNo-3;
							col = colNo;
							
							
							String weCell = webDriver.findElement(By.xpath(selectorvalue+"/tbody/tr["+ row +"]/td["+col +"]")).getText();;
							CucumberTestAutomationLogger.writeToLog("SeleniumKeywordslib","getCellData()", "weCell - " + weCell);

							if (weCell != null) {
								getVal = weCell.toString();
								if (getVal != null) {
									value = getVal.toString();
									if((colName.contains("Priced Days")) ||(colName.contains("Unpriced Days")))
									{
										if(getVal.contains(","))
										{
											
										}
									}
									else
									{
										extentTestReport.log(LogStatus.PASS," Value for  :  "+ colName + "   in RowName : " + rowName + " : is "+ value);
										CucumberTestAutomationLogger.writeToLog("SeleniumKeywordslib","getCellData()", "'" + objName+ "'=>Get the value from column=:"+ col + "and RowNo=: " + row + " : is "+ value);
										positionvectorvalues.put(getVal, getVal);
										
									}
									
								} 
								else {
									extentTestReport.log(LogStatus.FAIL,"getCellData()", getVal + " is null");
									CucumberTestAutomationLogger.writeToLog("SeleniumKeywordslib","getCellData()", getVal + " is null");
								}
							} else {
								extentTestReport.log(LogStatus.FAIL,"getCellData()", getVal + " is null");
								CucumberTestAutomationLogger.writeToLog("SeleniumKeywordslib","getCellData()", "value is null");
							}
						}
					}
						}
				}
			}
				}
			else
			{
				CucumberTestAutomationLogger.writeToLog("SeleniumKeywordslib","getCellData()", getVal + " is null");
			}
			}
		
			} 

	} 
		else {
			extentTestReport.log(LogStatus.FAIL,"getCellData()", "'" + objName+ "' =>does not exist!");
			CucumberTestAutomationLogger.writeToLog("SeleniumKeywordslib","getCellData()", "'" + objName+ "' =>does not exist!");
			return null;
		}
	}catch (NoSuchElementException ns) {
		try
		{
		ns.printStackTrace();
		CucumberTestAutomationLogger.writeToLog("SeleniumKeywordslib", "getCellData()","Object not found exeption thrown");
		CucumberTestAutomationLogger.writeToLog("SeleniumKeywordslib", "getCellData()","'" + objName + "'object does not exist");
		return positionvectorvalues;
		}
	 finally {
   		 extentTestReport.addScreenCapture(propertyFileReader.getValue("REPORT_PATH")+"objName");
    	 extentTestReport.log(LogStatus.FAIL, extentTestReport.addScreenCapture(ExtentReportManager.CaptureScreen(webDriver, propertyFileReader.getValue("SNAPSHOT_FOLDER")+"objName"))); 
    	// extentReports.endTest(extentTestReport);
    	// extentReports.flush();
    	 //extentReports.close(); 
	}
	}
	return positionvectorvalues;
}
public String getCellDataByRownameAndColName(WebDriver webDriver, String objName,String selector,String selectorvalue, String rowName, String colName,ExtentReports extentReports,ExtentTest extentTestReport) {
	CucumberTestAutomationLogger.writeToLog("SeleniumKeywordslib", "getCellData()","getCellData  rowNo " + rowName + " colNo " + colName+ "..objName - " + objName);
	String value = null;
	String getVal = null;
	int rowNo = 0;
	int colNo = 0;
	int  row=0;
	int col=0;
	try {
		List<WebElement> list = getElements(webDriver, objName, selector,selectorvalue);
		WebElement we = null;
		if (list != null)
			we = list.get(0);

		if (we != null) {
			List<WebElement> rows = null;
			rows = (we.findElements(By.xpath(".//tr")));

			CucumberTestAutomationLogger.writeToLog("SeleniumKeywordslib","getRowByContent() ", "rows - " + rows.size());
			if (rows != null) {
				int tempRowNo = 0;
				for (WebElement werowCell : rows) {
					String value1 = null;
					tempRowNo++;
		
			CucumberTestAutomationLogger.writeToLog("SeleniumKeywordslib","getCellData()", rowName + "X" + colName);
			value1 = werowCell.getText();
			CucumberTestAutomationLogger.writeToLog("SeleniumKeywordslib","getRowByContent() ", "VALUE - "+ value1);

			if (value1 != null) {
				if (value1.contains(rowName)) {
					rowNo = tempRowNo;
					CucumberTestAutomationLogger.writeToLog("SeleniumKeywordslib","getRowByContent() ","VALUES EQUAL");
					CucumberTestAutomationLogger.writeToLog("SeleniumKeywordslib","getRowByContent() ","Row number captured is = \""+ rowNo + "\"");
					row = rowNo;
					List<WebElement> cols = null;
					cols = (we.findElements(By.xpath(".//th")));

					CucumberTestAutomationLogger.writeToLog("SeleniumKeywordslib","getRowByContent() ", "rows - " + cols.size());
					if (cols != null) {
						int tempColNo =0;
						for (WebElement wecolCell : cols) {
							String value2 = null;
							tempColNo++;
					
					CucumberTestAutomationLogger.writeToLog("SeleniumKeywordslib","getCellData()", rowName + "X" + colName);
					value2 = wecolCell.getText();
					CucumberTestAutomationLogger.writeToLog("SeleniumKeywordslib","getRowByContent() ", "VALUE - "+ value2);

					if (value2 != null) {
						if (value2.equalsIgnoreCase(colName)) {
							colNo = tempColNo;
							CucumberTestAutomationLogger.writeToLog("SeleniumKeywordslib","getRowByContent() ","VALUES EQUAL");
							CucumberTestAutomationLogger.writeToLog("SeleniumKeywordslib","getRowByContent() ","Col number captured is = \""+ colNo + "\"");
							col = colNo;
							
							
							String weCell = webDriver.findElement(By.xpath(selectorvalue+"/tbody/tr["+ row +"]/td["+col +"]")).getText();;
							CucumberTestAutomationLogger.writeToLog("SeleniumKeywordslib","getCellData()", "weCell - " + weCell);

							if (weCell != null) {
								getVal = weCell.toString();
								if (getVal != null) {
									value = getVal.toString();
									extentTestReport.log(LogStatus.PASS," Value for  :  "+ colName + "   in RowName : " + rowName + " : is "+ value);
									CucumberTestAutomationLogger.writeToLog("SeleniumKeywordslib","getCellData()", "'" + objName+ "'=>Get the value from column=:"+ col + "and RowNo=: " + row + " : is "+ value);
									return value;
								} else {
									extentTestReport.log(LogStatus.FAIL,"getCellData()", getVal + " is null");
									CucumberTestAutomationLogger.writeToLog("SeleniumKeywordslib","getCellData()", getVal + " is null");
								}
							} else {
								extentTestReport.log(LogStatus.FAIL,"getCellData()", getVal + " is null");
								CucumberTestAutomationLogger.writeToLog("SeleniumKeywordslib","getCellData()", "value is null");
							}
						}
					}
						}
				}
			}
				}
			else
			{
				CucumberTestAutomationLogger.writeToLog("SeleniumKeywordslib","getCellData()", "value is null");			}
			}
		
			} 

	} 
		else {
			extentTestReport.log(LogStatus.FAIL,"getCellData()", "'" + objName+ "' =>does not exist!");
			CucumberTestAutomationLogger.writeToLog("SeleniumKeywordslib","getCellData()", "'" + objName+ "' =>does not exist!");
			return null;
		}
	}catch (NoSuchElementException ns) {
		try
		{
		ns.printStackTrace();
		CucumberTestAutomationLogger.writeToLog("SeleniumKeywordslib", "getCellData()","Object not found exeption thrown");
		CucumberTestAutomationLogger.writeToLog("SeleniumKeywordslib", "getCellData()","'" + objName + "'object does not exist");
		return value;
		}
	 finally {
   		 extentTestReport.addScreenCapture(propertyFileReader.getValue("REPORT_PATH")+"objName");
    	 extentTestReport.log(LogStatus.FAIL, extentTestReport.addScreenCapture(ExtentReportManager.CaptureScreen(webDriver, propertyFileReader.getValue("SNAPSHOT_FOLDER")+"objName"))); 
    	// extentReports.endTest(extentTestReport);
    	// extentReports.flush();
    	 //extentReports.close(); 
	}
	}
	return value;

}
@SuppressWarnings("unused")
public HashMap<String, String> validateColumnDataInTable(WebDriver webDriver, String objName,String selector,String selectorvalue, String colName,String data,ExtentReports extentReports,ExtentTest extentTestReport) {
	CucumberTestAutomationLogger.writeToLog("SeleniumKeywordslib", "getCellData()","getCellData  rowNo " + " colNo " + colName+ "..objName - " + objName);
	String value = null;
	String getVal = null;
	int rowNo = 0;
	int colNo = 0;
	int  row=0;
	int col=0;
	HashMap<String, String> map = new HashMap<String,String>();
	try {
		
		List<WebElement> list = getElements(webDriver, objName, selector,selectorvalue);
		WebElement we = null;
		if (list != null)
			we = list.get(0);

		if (we != null) {
			List<WebElement> rows = null;
			rows = (we.findElements(By.xpath(".//tr")));
       	CucumberTestAutomationLogger.writeToLog("SeleniumKeywordslib","getRowByContent() ", "rows - " + rows.size());
			if (rows != null) {
				int tempRowNo = 0;
				for (WebElement werowCell : rows) {
				String value1 = null;
					tempRowNo++;
		
			CucumberTestAutomationLogger.writeToLog("SeleniumKeywordslib","getCellData()",  "X" + colName);
			value1 = werowCell.getText();
			CucumberTestAutomationLogger.writeToLog("SeleniumKeywordslib","getRowByContent() ", "VALUE - "+ value1);
			String[] rowname = value1.split(" ");
            value1 = rowname[0];
           if (value1 != null) {
				if (!value1.contains(("Program ID"))) {
					rowNo = tempRowNo;
					CucumberTestAutomationLogger.writeToLog("SeleniumKeywordslib","getRowByContent() ","VALUES EQUAL");
					CucumberTestAutomationLogger.writeToLog("SeleniumKeywordslib","getRowByContent() ","Row number captured is = \""+ rowNo + "\"");
					row = rowNo;
					List<WebElement> cols = null;
					cols = (webDriver.findElements(By.xpath("//*[text()='All']//following::table//th")));

					CucumberTestAutomationLogger.writeToLog("SeleniumKeywordslib","getRowByContent() ", "rows - " + cols.size());
					if (cols != null) {
						int tempColNo =0;
						for (WebElement wecolCell : cols) {
							String value2 = null;
							tempColNo++;
					
					CucumberTestAutomationLogger.writeToLog("SeleniumKeywordslib","getCellData()",  "X" + colName);
					value2 = wecolCell.getText();
					CucumberTestAutomationLogger.writeToLog("SeleniumKeywordslib","getRowByContent() ", "VALUE - "+ value2);

					if (value2 != null) {
						if (value2.contains(colName)) {
							colNo = tempColNo;
							CucumberTestAutomationLogger.writeToLog("SeleniumKeywordslib","getRowByContent() ","VALUES EQUAL");
							CucumberTestAutomationLogger.writeToLog("SeleniumKeywordslib","getRowByContent() ","Col number captured is = \""+ colNo + "\"");
							col = colNo;
							
							
							String weCell = webDriver.findElement(By.xpath("//*[text()='All']//following::table[2]"+"/tbody/tr["+ row +"]/td["+col +"]")).getText();;
							CucumberTestAutomationLogger.writeToLog("SeleniumKeywordslib","getCellData()", "weCell - " + weCell);

							if (weCell != null) {
								getVal = weCell.toString();
								if (getVal != null) {
									value = getVal.toString();
									if(value.contains(data))
									{
										value= getVal.toString();
										extentTestReport.log(LogStatus.PASS," Value for  :  "+ colName + "   in RowName : " + " : is "+ value);
										CucumberTestAutomationLogger.writeToLog("SeleniumKeywordslib","getCellData()", "'" + objName+ "'=>Get the value from column=:"+ col + "and RowNo=: " + row + " : is "+ value);
										map.put(value1, value);
										
										//break;
									}
									
								} else {
									extentTestReport.log(LogStatus.FAIL,"getCellData()", getVal + " is null");
									CucumberTestAutomationLogger.writeToLog("SeleniumKeywordslib","getCellData()", getVal + " is null");
									map.put(value1, "Data Doesnot match with Search criteria");
									return map;
								}
							} else {
								extentTestReport.log(LogStatus.FAIL,"getCellData()", getVal + " is null");
								CucumberTestAutomationLogger.writeToLog("SeleniumKeywordslib","getCellData()", "value is null");
								map.put("null", "null");
								return map;
							}
						}
					}
						}
				}
			}
				}
			else
			{
				CucumberTestAutomationLogger.writeToLog("SeleniumKeywordslib","getCellData()", getVal + " is null");
			}
			
			}
				return map;
			} 
			//return map;
	} 
		else {
			extentTestReport.log(LogStatus.FAIL,"getCellData()", "'" + objName+ "' =>does not exist!");
			CucumberTestAutomationLogger.writeToLog("SeleniumKeywordslib","getCellData()", "'" + objName+ "' =>does not exist!");
			return null;
		}
		return map;	
	}catch (NoSuchElementException ns) {
		try
		{
		ns.printStackTrace();
		CucumberTestAutomationLogger.writeToLog("SeleniumKeywordslib", "getCellData()","Object not found exeption thrown");
		CucumberTestAutomationLogger.writeToLog("SeleniumKeywordslib", "getCellData()","'" + objName + "'object does not exist");
		return map;
		}
	 finally {
   		 extentTestReport.addScreenCapture(propertyFileReader.getValue("REPORT_PATH")+"objName");
    	 extentTestReport.log(LogStatus.FAIL, extentTestReport.addScreenCapture(ExtentReportManager.CaptureScreen(webDriver, propertyFileReader.getValue("SNAPSHOT_FOLDER")+"objName"))); 
    	// extentReports.endTest(extentTestReport);
    	// extentReports.flush();
    	 //extentReports.close(); 
    	
	}
		
	}
	

}*/
/*
 * ##########################################################################
 * ################### ' FUNCTION NAME - setKeyDates() ' DESCRIPTION -
 * This function will select Date from Calender ' INPUT PARAMETERS -
 * Obj - Name of the Object
 * '################################################################################################
 */
/*public String setKeyDates(WebDriver webDriver, String objName,String rowName,String keyDates,ExtentReports extentReports,ExtentTest extentTestReport) {
	String status = "false";
	   Actions act = new Actions(webDriver);
        String[] setKeyDates= keyDates.split(",");
         
         
       try{  
	    List<WebElement> list1 = getElements(webDriver, objName, selector,selectorvalue);
			CucumberTestAutomationLogger.writeToLog("SeleniumKeywordslib", "setKeyDates() ","web elements size - " + list1.size());
			if (list1.size() == 1) {
				CucumberTestAutomationLogger.writeToLog("SeleniumKeywordslib","setKeyDates() ", "found more than one element");
				 int rowNo = 0;
						int colNo = 0;
						int  row=0;
						int col=0;
							List<WebElement> list = getElements(webDriver, objName, selector,"//table[@class='table table-responsive table-bordered keydateTable']");
				WebElement we = null;
				if (list != null)
					we = list.get(0);
				if (we != null) {
					List<WebElement> rows = null;
					rows = (we.findElements(By.xpath(".//td")));
				
				CucumberTestAutomationLogger.writeToLog("SeleniumKeywordslib","setKeyDates() ", "rows - " + rows.size()+" and row num" +row);
				if (rows != null) {
					int tempRowNo = -1;
					for (WebElement werowCell : rows) {
						String value1 = null;
						tempRowNo++;
				value1 = werowCell.getText();
				CucumberTestAutomationLogger.writeToLog("SeleniumKeywordslib","setKeyDates() ", "VALUE - "+ value1);
				
				if (value1 != null) {
					if (value1.contains(rowName)) {
						rowNo = tempRowNo;
						row = rowNo;
						List<WebElement> cols = null;	
						 String value2 = null;
						for(int i=0;i<setKeyDates.length;i++)
					{
							
						List<WebElement> collist = getElements(webDriver, objName, selector,"//table[@class='table table-responsive table-bordered keydateTable']//tr[1]");
						we = collist.get(0);
					 cols = (we.findElements(By.xpath(".//th")));
					  CucumberTestAutomationLogger.writeToLog("SeleniumKeywordslib","setKeyDates() ", "rows - " + cols.size());
					  String keyDate = setKeyDates[i];
					  String[] gwdates =keyDate.split(":");
				         String phase = gwdates[0];
				        String date = gwdates[1];
					 if (cols != null) 
				       {
				    	    int tempColNo = 0;
			             for(WebElement wecolCell :cols)
				             {
							    tempColNo++;
				            	 value2 = wecolCell.getText();
							     if(value2 == null)
								   {
							    	 CucumberTestAutomationLogger.writeToLog("SeleniumKeywordslib","setKeyDates() ", "value2 null " + value2);
							    	
								   }
								 else if (value2 != null) 
					    	       {
					    		   colNo = tempColNo;
								    col = colNo;
								    if (value2.equalsIgnoreCase(phase)) 
										{
												 // act.moveToElement(webDriver.findElement(By.xpath("//*[text()="+ "'"+rowName +"'"+"]//following::td[" + col + "]"))).click(webDriver.findElement(By.xpath("//*[text()="+ "'"+rowName +"'"+"]//following::td[" + col + "]"))).sendKeys(date).build().perform();
								    	act.moveToElement(webDriver.findElement(By.xpath("//*[text()="+ "'"+rowName +"'"+"]//following::td[" + col + "]"))).click(webDriver.findElement(By.xpath("//td[" + col + "]//following::div[@class='input-group-append']"))).build().perform();			
								    	// datePicker(webDriver, objName, selector,"//td[" + col + "]//following::div[@class='input-group-append']",date, extentReports, extentTestReport);
								    	Thread.sleep(3000);
								    	datePicker(webDriver, objName, selector,"//ngb-datepicker-month-view[@class='d-block']",date, extentReports, extentTestReport);
								    	 
								    	 webDriver.manage().timeouts().implicitlyWait(70, TimeUnit.SECONDS);
												   // act.moveToElement(webDriver.findElement(By.xpath("//tr[@class='page-title']"))).click(webDriver.findElement(By.xpath("//tr[@class='page-title']"))).build().perform();
												   status ="true";
												    break;
												    
								       }
										

						  }
										
							 }
				          } 
				      
												            									}
						return status;
						
				       }
				            		
				      }
								            								    	
					}   
				 }
						            						}
		            							}
else {
				extentTestReport.log(LogStatus.FAIL, "move to element" +objName);
				CucumberTestAutomationLogger.writeToLog("SeleniumKeywordslib","setKeyDates ", "'" + objName+ "Object was not visible");
				status = "Link was not visible";
				return status;
			}

         }catch (NoSuchElementException ns) {
        ns.printStackTrace();
        CucumberTestAutomationLogger.writeToLog("SeleniumKeywordslib", "setKeyDates() ","Object not found exeption thrown");
        CucumberTestAutomationLogger.writeToLog("SeleniumKeywordslib", "setKeyDates() ", "'" + objName + "'object does not exist");
        status = "false";
        extentTestReport.log(LogStatus.FAIL, "Unable to click on " +objName+ "Calender");
        return status;
 } catch (Exception ex) {
 	try
 	{

 		ex.printStackTrace();
        CucumberTestAutomationLogger.writeToLog("SeleniumKeywordslib", "setKeyDates() ","Exception occurred");
        extentTestReport.log(LogStatus.FAIL,"select " +objName+ "option");
        String err[]=ex.getMessage().split("\n");
		status = "Exception " +err[0].replaceAll("'", "") + " Exception Occurred";
		return status;
 	}
 	finally
 	{
 		     extentTestReport.log(LogStatus.FAIL, "Unable to click on " +objName+ "Calender");
	         extentTestReport.log(LogStatus.FAIL, extentTestReport.addScreenCapture(ExtentReportManager.CaptureScreen(webDriver, propertyFileReader.getValue("SNAPSHOT_FOLDER")))); 
	    	// extentReports.endTest(extentTestReport);
	    	// extentReports.flush();
	         //extentReports.close(); 
	        webDriver.close();
	         //webDriver.quit();
	         
 	}
 }
		return status;
}	


 * ##########################################################################
 * ################### ' FUNCTION NAME - setKeyDates() ' DESCRIPTION -
 * This function will select Date from Calender ' INPUT PARAMETERS -
 * Obj - Name of the Object
 * '################################################################################################
 
public String checkKeydatesDisabledcells(WebDriver webDriver, String objName,String rowName,String phase,ExtentReports extentReports,ExtentTest extentTestReport) {
	String status = "false";
	  try{  
	    List<WebElement> list1 = getElements(webDriver, objName, selector,selectorvalue);
			CucumberTestAutomationLogger.writeToLog("SeleniumKeywordslib", "setKeyDates() ","web elements size - " + list1.size());
			if (list1.size() == 1) {
				CucumberTestAutomationLogger.writeToLog("SeleniumKeywordslib","setKeyDates() ", "found more than one element");
				 int rowNo = 0;
						int colNo = 0;
						int  row=0;
						int col=0;
							List<WebElement> list = getElements(webDriver, objName, selector,"//table[@class='table table-responsive table-bordered keydateTable']");
				WebElement we = null;
				if (list != null)
					we = list.get(0);
				if (we != null) {
					List<WebElement> rows = null;
					rows = (we.findElements(By.xpath(".//td")));
				
				CucumberTestAutomationLogger.writeToLog("SeleniumKeywordslib","setKeyDates() ", "rows - " + rows.size()+" and row num" +row);
				if (rows != null) {
					int tempRowNo = -1;
					for (WebElement werowCell : rows) {
						String value1 = null;
						tempRowNo++;
				value1 = werowCell.getText();
				CucumberTestAutomationLogger.writeToLog("SeleniumKeywordslib","setKeyDates() ", "VALUE - "+ value1);
				
				if (value1 != null) {
					if (value1.contains(rowName)) {
						rowNo = tempRowNo;
						row = rowNo;
						List<WebElement> cols = null;	
						 String value2 = null;
						List<WebElement> collist = getElements(webDriver, objName, selector,"//table[@class='table table-responsive table-bordered keydateTable']//tr[1]");
						we = collist.get(0);
					 cols = (we.findElements(By.xpath(".//th")));
					  CucumberTestAutomationLogger.writeToLog("SeleniumKeywordslib","setKeyDates() ", "rows - " + cols.size());
					
					 
					 if (cols != null) 
				       {
				    	    int tempColNo = -1;
			             for(WebElement wecolCell :cols)
				             {
							    tempColNo++;
				            	 value2 = wecolCell.getText();
							     if(value2 == null)
								   {
							    	 CucumberTestAutomationLogger.writeToLog("SeleniumKeywordslib","setKeyDates() ", "value2 null " + value2);
							    	
								   }
								 else if (value2 != null) 
					    	       {
					    		   colNo = tempColNo;
								    col = colNo;
								    if (value2.equalsIgnoreCase(phase)) 
										{
								    	String disablecellistSelectorValue = "//*[text()="+ "'"+rowName +"'"+"]//following::td[" + col + "]"+"//input[not(@img)]";
								    	List<WebElement> disablecellist = getElements(webDriver, objName, selector,disablecellistSelectorValue);
								    	//List<WebElement> disablecellist = webDriver.findElements(By.xpath(disablecellistSelectorValue));
								    	if(disablecellist.size()==0)
								    	{
								    		  status ="true";
											    break;
								    	}
								    	else
								    	{
								    		 status ="false";
											 break;
								    	}
								       }
										

						  }
										
							 }
				          } 
				      
												            									//}
						return status;
						
				       }
				            		
				      }
								            								    	
					}   
				 }
						            						}
		            							}
else {
				extentTestReport.log(LogStatus.FAIL, "move to element" +objName);
				CucumberTestAutomationLogger.writeToLog("SeleniumKeywordslib","setKeyDates ", "'" + objName+ "Object was not visible");
				status = "Link was not visible";
				return status;
			}

         }catch (NoSuchElementException ns) {
        ns.printStackTrace();
        CucumberTestAutomationLogger.writeToLog("SeleniumKeywordslib", "setKeyDates() ","Object not found exeption thrown");
        CucumberTestAutomationLogger.writeToLog("SeleniumKeywordslib", "setKeyDates() ", "'" + objName + "'object does not exist");
        status = "false";
        extentTestReport.log(LogStatus.FAIL, "Unable to click on " +objName+ "Calender");
        return status;
 } catch (Exception ex) {
 	try
 	{

 		ex.printStackTrace();
        CucumberTestAutomationLogger.writeToLog("SeleniumKeywordslib", "setKeyDates() ","Exception occurred");
        extentTestReport.log(LogStatus.FAIL,"select " +objName+ "option");
        String err[]=ex.getMessage().split("\n");
		status = "Exception " +err[0].replaceAll("'", "") + " Exception Occurred";
		return status;
 	}
 	finally
 	{
 		     extentTestReport.log(LogStatus.FAIL, "Unable to click on " +objName+ "Calender");
	         extentTestReport.log(LogStatus.FAIL, extentTestReport.addScreenCapture(ExtentReportManager.CaptureScreen(webDriver, propertyFileReader.getValue("SNAPSHOT_FOLDER")))); 
	    	// extentReports.endTest(extentTestReport);
	    	// extentReports.flush();
	         //extentReports.close(); 
	        webDriver.close();
	         //webDriver.quit();
	         
 	}
 }
		return status;
}	

 * ##########################################################################
 * ################### ' FUNCTION NAME - setProgramRoleForTeamMember() ' DESCRIPTION -
 * This function will select Date from Calender ' INPUT PARAMETERS -
 * Obj - Name of the Object
 * '################################################################################################
 
public String setProgramRoleForTeamMember(WebDriver webDriver, String objName,String rowName,String programRoles,ExtentReports extentReports,ExtentTest extentTestReport) {
	String status = "false";
	   Actions act = new Actions(webDriver);
      //  String[] prgramRoles= programRoles.split(";");
         
         
       try{  
	    List<WebElement> list1 = getElements(webDriver, objName, selector,selectorvalue);
			CucumberTestAutomationLogger.writeToLog("SeleniumKeywordslib", "setProgramRoleForTeamMember() ","web elements size - " + list1.size());
			if (list1.size() == 1) {
				CucumberTestAutomationLogger.writeToLog("SeleniumKeywordslib","setProgramRoleForTeamMember() ", "found more than one element");
				 int rowNo = 0;
						int colNo = 0;
						int  row=0;
						int col=0;
							List<WebElement> list = getElements(webDriver, objName, selector,"//*[text()='Search Results']//following::table");
				WebElement we = null;
				if (list != null)
					we = list.get(0);
				if (we != null) {
					List<WebElement> rows = null;
					rows = (we.findElements(By.xpath(".//td")));
				
				CucumberTestAutomationLogger.writeToLog("SeleniumKeywordslib","setProgramRoleForTeamMember() ", "rows - " + rows.size()+" and row num" +row);
				if (rows != null) {
					int tempRowNo = -1;
					for (WebElement werowCell : rows) {
						String value1 = null;
						tempRowNo++;
				value1 = werowCell.getText();
				CucumberTestAutomationLogger.writeToLog("SeleniumKeywordslib","setProgramRoleForTeamMember() ", "VALUE - "+ value1);
				
				if (value1 != null) {
					if (value1.contains(rowName)) {
						rowNo = tempRowNo;
						row = rowNo;
						List<WebElement> cols = null;	
						 String value2 = null;
						for(int i=0;i<programRoles.length();i++)
					{
							
						List<WebElement> collist = getElements(webDriver, objName, selector,"//*[text()='Search Results']//following::table//tr[1]");
						we = collist.get(0);
					 cols = (we.findElements(By.xpath(".//th")));
					  CucumberTestAutomationLogger.writeToLog("SeleniumKeywordslib","setProgramRoleForTeamMember() ", "rows - " + cols.size());
					 // String prgrmRoles = prgramRoles[i];
					  String[] roles =programRoles.split(",");
				         String input = roles[0];
				        String selection = roles[1];
					 if (cols != null) 
				       {
				    	    int tempColNo = -1;
			             for(WebElement wecolCell :cols)
				             {
							    tempColNo++;
				            	 value2 = wecolCell.getText();
							     if(value2 == null)
								   {
							    	 CucumberTestAutomationLogger.writeToLog("SeleniumKeywordslib","setProgramRoleForTeamMember() ", "value2 null " + value2);
							    	 
								   }
								 else if (value2 != null) 
					    	       {
					    		   colNo = tempColNo;
								    col = colNo;
								    if (value2.equalsIgnoreCase("Program Role")) 
										{
												  act.moveToElement(webDriver.findElement(By.xpath("//*[text()="+ "'"+rowName +"'"+"]//following::td[" + col + "]"))).click(webDriver.findElement(By.xpath("//*[text()="+ "'"+rowName +"'"+"]//following::td[" + col + "]"))).build().perform();
													webDriver.manage().timeouts().implicitlyWait(70, TimeUnit.SECONDS);
													selectFromList(webDriver, objName, selector, "//*[text()="+ "'"+rowName +"'"+"]//following::select[@class='user-role-dropdown']", input,selection, extentReports, extentTestReport);
												   // act.moveToElement(webDriver.findElement(By.xpath("//tr[@class='page-title']"))).click(webDriver.findElement(By.xpath("//tr[@class='page-title']"))).build().perform();
													act.moveToElement(webDriver.findElement(By.xpath("//*[text()="+ "'"+rowName +"'"+"]//following::td[" + col + "]"))).click(webDriver.findElement(By.xpath("//*[text()="+ "'"+rowName +"'"+"]//following::td[" + col + "]"))).build().perform();
													status ="true";
												    break;
												    
								       }
										

						  }
										
							 }
				          } 
				      
												            									}
						return status;
						
				       }
				            		
				      }
								            								    	
					}   
				 }
	      }
		   }
			else if(list1.size()>1)
			{

				CucumberTestAutomationLogger.writeToLog("SeleniumKeywordslib","setProgramRoleForTeamMember() ", "found more than one element");
				 int rowNo = 0;
						int colNo = 0;
						int  row=0;
						int col=0;
				List<WebElement> list = getElements(webDriver, objName, selector,"//div[2]/div[2]/div/div/kendo-grid/kendo-grid-list/div/div[1]/table/tbody");
				WebElement we = null;
				if (list != null)
					we = list.get(0);
				if (we != null) {
					List<WebElement> rows = null;
					rows = (we.findElements(By.xpath(".//tr")));
				
				CucumberTestAutomationLogger.writeToLog("SeleniumKeywordslib","setProgramRoleForTeamMember() ", "rows - " + rows.size()+" and row num" +row);
				if (rows != null) {
					int tempRowNo = -1;
					for (WebElement werowCell : rows) {
						String value1 = null;
						tempRowNo++;
				value1 = werowCell.getText();
				CucumberTestAutomationLogger.writeToLog("SeleniumKeywordslib","setProgramRoleForTeamMember() ", "VALUE - "+ value1);
				
				if (value1 != null) {
					if (value1.contains(rowName)) {
						rowNo = tempRowNo;
						row = rowNo;
						List<WebElement> cols = null;	
						 String value2 = null;
						
						for(int i=0;i<programRoles.length();i++)
					{
							
						List<WebElement> collist = getElements(webDriver, objName, selector,"//*[text()='Search Results']//following::table");
						we = collist.get(0);
					 cols = (we.findElements(By.xpath(".//th")));
					  CucumberTestAutomationLogger.writeToLog("SeleniumKeywordslib","setProgramRoleForTeamMember() ", "rows - " + cols.size());
					 // String prgrmRoles = prgramRoles[i];
					  String[] roles =programRoles.split(",");
				         String input = roles[0];
				        
				        String selection = roles[1];
				       
					 if (cols != null) 
				       {
				    	    int tempColNo = -1;
			             for(WebElement wecolCell :cols)
				             {
							    tempColNo++;
				            	 value2 = wecolCell.getText();
							     if(value2 == null)
								   {
							    	 CucumberTestAutomationLogger.writeToLog("SeleniumKeywordslib","setProgramRoleForTeamMember() ", "value2 null " + value2 +selection +input);
							    									   }
								 else if (value2 != null) 
					    	       {
					    		   colNo = tempColNo;
								    col = colNo;
								    if (value2.equalsIgnoreCase("Program Role")) 
										{
												  act.moveToElement(webDriver.findElement(By.xpath("//*[text()="+ "'"+rowName +"'"+"]//following::td[" + col + "]"))).build().perform();
													webDriver.manage().timeouts().implicitlyWait(70, TimeUnit.SECONDS);
													//selectFromList(webDriver, objName, selector, "//*[text()="+ "'"+rowName +"'"+"]//following::select[@class='user-role-dropdown']", input,selection, extentReports, extentTestReport);
												   // act.moveToElement(webDriver.findElement(By.xpath("//tr[@class='page-title']"))).click(webDriver.findElement(By.xpath("//tr[@class='page-title']"))).build().perform();
													//act.moveToElement(webDriver.findElement(By.xpath("//*[text()="+ "'"+rowName +"'"+"]"))).click().build().perform();
													status ="true";
												    break;
												    
								       }
										

						  }
										
							 }
				          } 
				      
												            									}
						return status;
						
				       }
				            		
				      }
								            								    	
					}   
				 }
     }
		            							
			}
else {
				extentTestReport.log(LogStatus.FAIL, "move to element" +objName);
				CucumberTestAutomationLogger.writeToLog("SeleniumKeywordslib","setProgramRoleForTeamMember ", "'" + objName+ "Object was not visible");
				status = "Link was not visible";
				return status;
			}

         }catch (NoSuchElementException ns) {
        ns.printStackTrace();
        CucumberTestAutomationLogger.writeToLog("SeleniumKeywordslib", "setProgramRoleForTeamMember() ","Object not found exeption thrown");
        CucumberTestAutomationLogger.writeToLog("SeleniumKeywordslib", "setProgramRoleForTeamMember() ", "'" + objName + "'object does not exist");
        status = "false";
        extentTestReport.log(LogStatus.FAIL, "Unable to click on " +objName+ "Calender");
        return status;
 } catch (Exception ex) {
 	try
 	{

 		ex.printStackTrace();
        CucumberTestAutomationLogger.writeToLog("SeleniumKeywordslib", "setProgramRoleForTeamMember() ","Exception occurred");
        extentTestReport.log(LogStatus.FAIL,"select " +objName+ "option");
        String err[]=ex.getMessage().split("\n");
		status = "Exception " +err[0].replaceAll("'", "") + " Exception Occurred";
		return status;
 	}
 	finally
 	{
 		     extentTestReport.log(LogStatus.FAIL, "Unable to click on " +objName+ "Calender");
	         extentTestReport.log(LogStatus.FAIL, extentTestReport.addScreenCapture(ExtentReportManager.CaptureScreen(webDriver, propertyFileReader.getValue("SNAPSHOT_FOLDER")))); 
	    	// extentReports.endTest(extentTestReport);
	    	// extentReports.flush();
	         //extentReports.close(); 
	        webDriver.close();
	         //webDriver.quit();
	         
 	}
 }
		return status;
}	
 * ############################################################################################# 
 * ' FUNCTION NAME - mouseOver ' DESCRIPTION - This
 * function will be used to focus on Menu or list ' INPUT PARAMETERS -
 * '################################################################################################
 

 public String selectCategoryRadio(WebDriver webDriver, String objName,String selector,String selectorvalue,String text,ExtentReports extentReports,ExtentTest extentTestReport) {
        CucumberTestAutomationLogger.writeToLog("SeleniumKeywordslib", "clickByText() "," object name" + objName);
        String status = "false";
        String[] category = text.split(",");
        String categoryType=category[0];
        String value=category[1];
        try {
        	
               
        	   String path = "//*[contains(text(),"+ "'"+categoryType +"'"+")]//following::*[text()="+ "'"+value +"'"+"]";
              
               List<WebElement> list = getElements(webDriver, objName, selector,path);
             
               if (list.size() == 1) {
                     CucumberTestAutomationLogger.writeToLog("SeleniumKeywordslib", "clickByText() ", "found more than one element.");
                     Actions act = new Actions(webDriver);
                     WebElement element = list.get(0);
                    

                     if (element.isDisplayed()) {

                            act.moveToElement(element).click(element).build().perform();
                            status = "true";
                            extentTestReport.log(LogStatus.PASS, "click on " +objName);
                            return status;
                     } else {
                            CucumberTestAutomationLogger.writeToLog("SeleniumKeywordslib", "clickByText() ", "'" + objName + "' object was not visible");
                            status = "true";
                            extentTestReport.log(LogStatus.PASS, "click on " +objName);
                            return status;
                     }
               } 
               else if (list.size() >1) {
                     CucumberTestAutomationLogger.writeToLog("SeleniumKeywordslib", "clickByText() ", "found more than one element.");
                     Actions act = new Actions(webDriver);
                     WebElement element = list.get(0);
                    

                     if (element.isDisplayed()) {

                            act.moveToElement(element).click(element).build().perform();
                            status = "true";
                            extentTestReport.log(LogStatus.PASS, "click on Menu Item" +objName);
                            return status;
                     } else {
                            CucumberTestAutomationLogger.writeToLog("SeleniumKeywordslib", "clickByText() ", "'" + objName + "' object was not visible");
                            status = "true";
                            extentTestReport.log(LogStatus.PASS, "click on Menu Item" +objName);
                            return status;
                     }
               } 
               else {
                     CucumberTestAutomationLogger.writeToLog("SeleniumKeywordslib","clickByText() ", "'" + objName+ "' object was not found");
                     status = "false";
                     extentTestReport.log(LogStatus.FAIL, "click on Menu Item" +objName);
                     CucumberTestAutomationLogger.writeToLog("SeleniumKeywordslib", "clickByText() ", "object not found");
                     return status;
               }

        } catch (Exception ex) {
        	try{
                ex.printStackTrace();
              // npiCTRMLogger.writeToLog("SeleniumKeywordslib", "mouseOver() ","Exception occurred");
                CucumberTestAutomationLogger.writeToLog("SeleniumKeywordslib", "clickByText() ","Exception '" + ex.getMessage() + "' Occurred");
                String err[]=ex.getMessage().split("\n");
				status = "Exception " +err[0].replaceAll("'", "") + " Exception Occurred";
				return status;
              
        	}
        	finally {
        		 extentTestReport.addScreenCapture(propertyFileReader.getValue("REPORT_PATH")+"objName");
            	 extentTestReport.log(LogStatus.FAIL, extentTestReport.addScreenCapture(ExtentReportManager.CaptureScreen(webDriver, propertyFileReader.getValue("SNAPSHOT_FOLDER")+"objName"))); 
            	// extentReports.endTest(extentTestReport);
            	// extentReports.flush();
            	 //extentReports.close(); 
        	}
        }
 }
 
	 * ############################################################################################# 
	 * ' FUNCTION NAME - checkElementDisableState ' DESCRIPTION - This
	 * function will be used to check the Element is in Disable state' INPUT PARAMETERS -
	 * '################################################################################################
	 
	public String checkElementDisableState(WebDriver webDriver, String objName,String selector,String selectorvalue,String disabledElements,ExtentReports extentReports,ExtentTest extentTestReport) {
		String status = "false";
		try {
			  	     CucumberTestAutomationLogger.writeToLog("SeleniumKeywordslib","checkElementDisableState() ", "clicking 1st element");
					  selectorvalue = "//*[text()="+ "'"+disabledElements +"'"+"]";
					  List<WebElement> list = getElements(webDriver, objName,selector,selectorvalue);
					  if (list.size() > 1) {
						CucumberTestAutomationLogger.writeToLog("SeleniumKeywordslib","checkElementDisableState() ", "found more than one element");
						CucumberTestAutomationLogger.writeToLog("SeleniumKeywordslib", "checkElementDisableState() ","web elements size - " + list.size());
					 // for (WebElement element : list) {
						WebElement element  =list.get(0);
				//	if (element.isDisplayed()) {
						
						if (element.getAttribute("class") != null&& element.getAttribute("class").contains("disabled")) {
								CucumberTestAutomationLogger.writeToLog("SeleniumKeywordslib","checkElementDisableState() ", "'" + objName+ "' => is disabled");
								extentTestReport.log(LogStatus.PASS, "File Name " +disabledElements+"   is  disabled");
								status=  "true";
								return status;
							} else {
								CucumberTestAutomationLogger.writeToLog("SeleniumKeywordslib","checkElementDisableState() ", "'" + objName+ "' => is  enabled");
								extentTestReport.log(LogStatus.FAIL, "File Name "+disabledElements +"   is  enabled");
								status=  "false";
								return status;
					}

			} 
					  else if (list.size() == 1) {
							WebElement element = list.get(0);
						if ((element.getAttribute("class") != null&& element.getAttribute("class").contains("disabled")) ||(element.getTagName().equals("button")) ||(element.getTagName().equals("label"))) {
											
									if((element.getTagName().equals("button")) ||element.getTagName().equals("label"))
									{
										if(element.isEnabled())
										{
											CucumberTestAutomationLogger.writeToLog("SeleniumKeywordslib","checkElementDisableState() ", "'" +disabledElements+ "' => is disabled");
											
											extentTestReport.log(LogStatus.PASS,disabledElements + "   is  disabled");
											status= "false";
											return status;
										}
										else
										{
											if(element.getTagName().contains("disabled"))
													{
												CucumberTestAutomationLogger.writeToLog("SeleniumKeywordslib","checkElementDisableState() ", "'" +disabledElements+ "' => is disabled");
												
												extentTestReport.log(LogStatus.PASS,disabledElements + "   is  disabled");
												status= "false";
												return status;
													}
											else
											{
											CucumberTestAutomationLogger.writeToLog("SeleniumKeywordslib","checkElementDisableState() ", "'" +disabledElements+ "' => is disabled");
											
											extentTestReport.log(LogStatus.PASS,disabledElements + "   is  disabled");
											status= "true";
											return status;
											}
										}
									}
									
										} else {
											CucumberTestAutomationLogger.writeToLog("SeleniumKeywordslib","checkElementDisableState() ", "'" + objName+ "' => is  enabled");
											extentTestReport.log(LogStatus.FAIL, "File Name " +disabledElements +"   is  enabled");
											status= "false";
											return status;
								}
//								}
//								else {
//									CATPLMNPILogger.writeToLog("SeleniumKeywordslib","checkElementDisableState() ", "'" + objName+ " was not visible");
//									status = "Element was not visible";
//									return status;
//								}
							

							} else {
								CucumberTestAutomationLogger.writeToLog("SeleniumKeywordslib","checkElementDisableState() ", "'" + objName+ "CheckBox was not visible");
								status = "CheckBox was not visible";
								return status;
							}
							
			} 
		 catch (Exception exception) {
	  		 try
	  		 {
	  			    exception.printStackTrace();
	  				CucumberTestAutomationLogger.writeToLog("SeleniumKeywordslib","checkElementDisableState ", "Exeption thrown");
	  				CucumberTestAutomationLogger.writeToLog("SeleniumKeywordslib","checkElementDisableState ", "object doesnot exists");
	  				
	  		 }
	  		 finally{
	  				extentTestReport.log(LogStatus.FAIL, "Unable to check object Status ");
	  		        extentTestReport.log(LogStatus.FAIL, extentTestReport.addScreenCapture(ExtentReportManager.CaptureScreen(webDriver, propertyFileReader.getValue("SNAPSHOT_FOLDER")))); 
	  		    	// extentReports.endTest(extentTestReport);
	  		    	// extentReports.flush();
	  		        webDriver.close();
	  		         
	  			}
	  	}
		return status;
	}
	
	 * ############################################################################################# 
	 * ' FUNCTION NAME - checkElementDisableState ' DESCRIPTION - This
	 * function will be used to check the Element is in Disable state' INPUT PARAMETERS -
	 * '################################################################################################
	 
	public String checkboxstatus(WebDriver webDriver, String objName,String selector,String selectorvalue,ExtentReports extentReports,ExtentTest extentTestReport) {
		String status = "false";
		try {
			  	     CucumberTestAutomationLogger.writeToLog("SeleniumKeywordslib","checkElementDisableState() ", "clicking 1st element");
					  List<WebElement> list = getElements(webDriver, objName,selector,selectorvalue);
					  if (list.size() > 1) {
						CucumberTestAutomationLogger.writeToLog("SeleniumKeywordslib","checkElementDisableState() ", "found more than one element");
						CucumberTestAutomationLogger.writeToLog("SeleniumKeywordslib", "checkElementDisableState() ","web elements size - " + list.size());
					 // for (WebElement element : list) {
						WebElement element  =list.get(0);
						if ((element.getAttribute("class") != null&& element.getAttribute("class").contains("disabled")) ||(element.getTagName().equals("input")) ||(element.getTagName().equals("label")))
						{
							if(element.getTagName().contains("checked"))
											{
										CucumberTestAutomationLogger.writeToLog("SeleniumKeywordslib","checkElementDisableState() ", "'" +element+ "' => is disabled");
										
										extentTestReport.log(LogStatus.PASS,element + "   is  checked");
										status= "true";
										//return status;
											}
									else
									{
									CucumberTestAutomationLogger.writeToLog("SeleniumKeywordslib","checkElementDisableState() ", "'" +element+ "' => is not checked");
									
									extentTestReport.log(LogStatus.FAIL,element + "   is  disabled");
									status= "false";
									//return status;
									}
								//}
							}
							
				
			} 
					  else if (list.size() == 1) {
							WebElement element = list.get(0);
						if ((element.getAttribute("class") != null&& element.getAttribute("class").contains("disabled")) ||(element.getTagName().equals("input")) ||(element.getTagName().equals("label"))) {
											
									if((element.getTagName().equals("button")) ||element.getTagName().equals("label"))
									{
										if(element.isEnabled())
										{
											CucumberTestAutomationLogger.writeToLog("SeleniumKeywordslib","checkElementDisableState() ", "'" +element+ "' => is disabled");
											
											extentTestReport.log(LogStatus.PASS,element + "   is  disabled");
											status= "false";
											return status;
										}
										else
										{
											if(element.getTagName().contains("checked"))
													{
												CucumberTestAutomationLogger.writeToLog("SeleniumKeywordslib","checkElementDisableState() ", "'" +element+ "' => is disabled");
												
												extentTestReport.log(LogStatus.PASS,element + "   is  checked");
												status= "true";
												//return status;
													}
											else
											{
											CucumberTestAutomationLogger.writeToLog("SeleniumKeywordslib","checkElementDisableState() ", "'" +element+ "' => is not checked");
											
											extentTestReport.log(LogStatus.PASS,element + "   is  disabled");
											status= "false";
											//return status;
											}
										}
									}
									
										} else {
											CucumberTestAutomationLogger.writeToLog("SeleniumKeywordslib","checkElementDisableState() ", "'" + objName+ "' => is  enabled");
											extentTestReport.log(LogStatus.FAIL, "File Name " +element +"   is  enabled");
											status= "false";
											return status;
								}
//								}
//								else {
//									CATPLMNPILogger.writeToLog("SeleniumKeywordslib","checkElementDisableState() ", "'" + objName+ " was not visible");
//									status = "Element was not visible";
//									return status;
//								}
							

							} else {
								CucumberTestAutomationLogger.writeToLog("SeleniumKeywordslib","checkElementDisableState() ", "'" + objName+ "CheckBox was not visible");
								status = "CheckBox was not visible";
								return status;
							}
							
			} 
		 catch (Exception exception) {
	  		 try
	  		 {
	  			    exception.printStackTrace();
	  				CucumberTestAutomationLogger.writeToLog("SeleniumKeywordslib","checkElementDisableState ", "Exeption thrown");
	  				CucumberTestAutomationLogger.writeToLog("SeleniumKeywordslib","checkElementDisableState ", "object doesnot exists");
	  				
	  		 }
	  		 finally{
	  				extentTestReport.log(LogStatus.FAIL, "Unable to check object Status ");
	  		        extentTestReport.log(LogStatus.FAIL, extentTestReport.addScreenCapture(ExtentReportManager.CaptureScreen(webDriver, propertyFileReader.getValue("SNAPSHOT_FOLDER")))); 
	  		    	// extentReports.endTest(extentTestReport);
	  		    	// extentReports.flush();
	  		        webDriver.close();
	  		         
	  			}
	  	}
		return status;
	}
	
	 * ############################################################################################# 
	 * ' FUNCTION NAME - checkElementDisableState ' DESCRIPTION - This
	 * function will be used to check the Element is in Disable state' INPUT PARAMETERS -
	 * '################################################################################################
	 
	public String verifycheckboxSelectedornot(WebDriver webDriver, String objName,String selector,String selectorvalue,String functionName,String accesstype,ExtentReports extentReports,ExtentTest extentTestReport) {
		String status = "false";
		try {
			  	     CucumberTestAutomationLogger.writeToLog("SeleniumKeywordslib","checkElementDisableState() ", "clicking 1st element");
			  	     if(accesstype.contains("Edit"))
			  	     {
			  	    	selectorvalue = "//span[contains(text(),"+ "'"+functionName +"'"+")]//following::td[1]//input[1]"; 
			  	     }
					  
					  else
					  {
						  selectorvalue = "//span[contains(text(),"+ "'"+functionName +"'"+")]//following::td[2]//input[1]";
					  }
					  List<WebElement> list = getElements(webDriver, objName,selector,selectorvalue);
					  if (list.size() > 1) {
						CucumberTestAutomationLogger.writeToLog("SeleniumKeywordslib","checkElementDisableState() ", "found more than one element");
						CucumberTestAutomationLogger.writeToLog("SeleniumKeywordslib", "checkElementDisableState() ","web elements size - " + list.size());
					 // for (WebElement element : list) {
						WebElement element  =list.get(0);
				//	if (element.isDisplayed()) {
						
						if (element.getTagName().contains("checked")) {
								CucumberTestAutomationLogger.writeToLog("SeleniumKeywordslib","checkElementDisableState() ", "'" + objName+ "' => is disabled");
								//extentTestReport.log(LogStatus.PASS, "Checkbox   is  checked");
								status=  "checked";
								return status;
							} else {
								CucumberTestAutomationLogger.writeToLog("SeleniumKeywordslib","checkElementDisableState() ", "'" + objName+ "' => is  enabled");
								//extentTestReport.log(LogStatus.FAIL, "Checkbox   unchecked");
								status=  "unchecked";
								return status;
					}

			} 
					  else if (list.size() == 1) {
							WebElement element = list.get(0);
								
									if(element.getTagName().contains("checked"))
									{
											CucumberTestAutomationLogger.writeToLog("SeleniumKeywordslib","checkElementDisableState() ", "'" +objName+ "' => is checked");
											
										//	extentTestReport.log(LogStatus.PASS, " checkbox  is  checked");
											status= "checked";
											return status;
									}
											else
											{
											CucumberTestAutomationLogger.writeToLog("SeleniumKeywordslib","checkElementDisableState() ", "'" +objName+ "' => is not checked");
											
											//extentTestReport.log(LogStatus.FAIL, " checkbox  is  unchecked");
											status= "unchecked";
											return status;
	
							} 
							
			} 
					  else
					  {
						  CucumberTestAutomationLogger.writeToLog("SeleniumKeywordslib","checkElementDisableState() ", "'" +objName+ "' => is not visible");
							
							extentTestReport.log(LogStatus.FAIL, " checkbox  is  not visible");
							status= "false";
							return status;  
					  }
		}
		 catch (Exception exception) {
	  		 try
	  		 {
	  			    exception.printStackTrace();
	  				CucumberTestAutomationLogger.writeToLog("SeleniumKeywordslib","checkElementDisableState ", "Exeption thrown");
	  				CucumberTestAutomationLogger.writeToLog("SeleniumKeywordslib","checkElementDisableState ", "object doesnot exists");
	  				
	  		 }
	  		 finally{
	  				extentTestReport.log(LogStatus.FAIL, "Unable to check object Status ");
	  		        extentTestReport.log(LogStatus.FAIL, extentTestReport.addScreenCapture(ExtentReportManager.CaptureScreen(webDriver, propertyFileReader.getValue("SNAPSHOT_FOLDER")))); 
	  		    	// extentReports.endTest(extentTestReport);
	  		    	// extentReports.flush();
	  		        webDriver.close();
	  		         
	  			}
	  	}
		return status;
	}
	
	 * ############################################################################################# 
	 * ' FUNCTION NAME - checkElementDisableState ' DESCRIPTION - This
	 * function will be used to check the Element is in Disable state' INPUT PARAMETERS -
	 * '################################################################################################
	 
	public String checkElementDisableStatebyObj(WebDriver webDriver, String objName,String selector,String selectorvalue,ExtentReports extentReports,ExtentTest extentTestReport) {
		String status = "false";
		try {
			  	     CucumberTestAutomationLogger.writeToLog("SeleniumKeywordslib","checkElementDisableState() ", "clicking 1st element");
					 List<WebElement> list = getElements(webDriver, objName,selector,selectorvalue);
					  if (list.size() > 1) {
						CucumberTestAutomationLogger.writeToLog("SeleniumKeywordslib","checkElementDisableState() ", "found more than one element");
						CucumberTestAutomationLogger.writeToLog("SeleniumKeywordslib", "checkElementDisableState() ","web elements size - " + list.size());
					 // for (WebElement element : list) {
						WebElement element  =list.get(0);
				//	if (element.isDisplayed()) {
						
						if ((element.getAttribute("class") != null&& element.getAttribute("class").contains("disabled")) ||element.getTagName().contains("disabled")||(element.getAttribute("disabled").contains("disabled")) ) {
								CucumberTestAutomationLogger.writeToLog("SeleniumKeywordslib","checkElementDisableState() ", "'" + objName+ "' => is disabled");
								extentTestReport.log(LogStatus.PASS,objName+"   is  disabled");
								status=  "true";
								return status;
							} else {
								CucumberTestAutomationLogger.writeToLog("SeleniumKeywordslib","checkElementDisableState() ", "'" + objName+ "' => is  enabled");
								extentTestReport.log(LogStatus.FAIL, objName +"   is  enabled");
								status=  "false";
								return status;
					}

			} 
					  else if (list.size() == 1) {
							WebElement element = list.get(0);
							if ((element.getAttribute("class") != null&& element.getAttribute("class").contains("disabled")) ||(element.getTagName().equals("button")) ||(element.getTagName().equals("label")) || (element.getTagName().equals("input"))||(element.getAttribute("disabled").contains("disabled"))) {
											
									if((element.getTagName().equals("button")) ||element.getTagName().equals("label"))
									{
										if(element.isEnabled())
										{
											CucumberTestAutomationLogger.writeToLog("SeleniumKeywordslib","checkElementDisableState() ", "'" +objName+ "' => is disabled");
											
											extentTestReport.log(LogStatus.FAIL,objName + "   is  disabled");
											status= "false";
											return status;
										}
										else
										{
											if((element.getTagName().contains("disabled"))||(element.getAttribute("disabled").contains("disabled")))
													{
												CucumberTestAutomationLogger.writeToLog("SeleniumKeywordslib","checkElementDisableState() ", "'" +objName+ "' => is disabled");
												
												extentTestReport.log(LogStatus.PASS,objName + "   is  disabled");
												status= "true";
												return status;
													}
											else
											{
											CucumberTestAutomationLogger.writeToLog("SeleniumKeywordslib","checkElementDisableState() ", "'" +objName+ "' => is disabled");
											
											extentTestReport.log(LogStatus.PASS,objName + "   is  disabled");
											status= "true";
											return status;
											}
										}
									}
									
										} else {
											CucumberTestAutomationLogger.writeToLog("SeleniumKeywordslib","checkElementDisableState() ", "'" + objName+ "' => is  enabled");
											extentTestReport.log(LogStatus.FAIL, objName +"   is  enabled");
											status= "false";
											return status;
								}
//								}
//								else {
//									CATPLMNPILogger.writeToLog("SeleniumKeywordslib","checkElementDisableState() ", "'" + objName+ " was not visible");
//									status = "Element was not visible";
//									return status;
//								}
							

							} else {
								CucumberTestAutomationLogger.writeToLog("SeleniumKeywordslib","checkElementDisableState() ", "'" + objName+ "CheckBox was not visible");
								status = "CheckBox was not visible";
								return status;
							}
							
			} 
		 catch (Exception exception) {
	  		 try
	  		 {
	  			    exception.printStackTrace();
	  				CucumberTestAutomationLogger.writeToLog("SeleniumKeywordslib","checkElementDisableState ", "Exeption thrown");
	  				CucumberTestAutomationLogger.writeToLog("SeleniumKeywordslib","checkElementDisableState ", "object doesnot exists");
	  				
	  		 }
	  		 finally{
	  				extentTestReport.log(LogStatus.FAIL, "Unable to check object Status ");
	  		        extentTestReport.log(LogStatus.FAIL, extentTestReport.addScreenCapture(ExtentReportManager.CaptureScreen(webDriver, propertyFileReader.getValue("SNAPSHOT_FOLDER")))); 
	  		    	// extentReports.endTest(extentTestReport);
	  		    	// extentReports.flush();
	  		        webDriver.close();
	  		         
	  			}
	  	}
		return status;
	}
	
	 * ############################################################################################# 
	 * ' FUNCTION NAME - checkElementDisableState ' DESCRIPTION - This
	 * function will be used to check the Element is in Disable state' INPUT PARAMETERS -
	 * '################################################################################################
	 
	public String checkElementEnableState(WebDriver webDriver, String objName,String selector,String disabledElements,ExtentReports extentReports,ExtentTest extentTestReport) {
		String status = "false";
		try {
			  	     CucumberTestAutomationLogger.writeToLog("SeleniumKeywordslib","checkElementDisableState() ", "clicking 1st element");
					String[] enabledele = disabledElements.split(",");
			  	     for(int i=0;i<enabledele.length;i++)
					{
			  	     String selectorvalue = "//*[text()="+ "'"+enabledele[i] +"'"+"]";
					  List<WebElement> list = getElements(webDriver, objName,selector,selectorvalue);
					  if (list.size() > 1) {
						CucumberTestAutomationLogger.writeToLog("SeleniumKeywordslib","checkElementDisableState() ", "found more than one element");
						CucumberTestAutomationLogger.writeToLog("SeleniumKeywordslib", "checkElementDisableState() ","web elements size - " + list.size());
					WebElement element=list.get(0);
						if (element.getAttribute("class") != null&& element.getAttribute("class").contains("disabled")) {
								CucumberTestAutomationLogger.writeToLog("SeleniumKeywordslib","checkElementDisableState() ", "'" + objName+ "' => is disabled");
								extentTestReport.log(LogStatus.FAIL, "File Name " +enabledele[i]+"   is  disabled");
								status=  "true";
								//return status;
							} else {
								CucumberTestAutomationLogger.writeToLog("SeleniumKeywordslib","checkElementDisableState() ", "'" + objName+ "' => is  enabled");
								extentTestReport.log(LogStatus.PASS, "File Name "+enabledele[i] +"   is  enabled");
								status=  "false";
							//	return status;
					}
//					}
//					else {
//						CATPLMNPILogger.writeToLog("SeleniumKeywordslib","checkElementDisableState() ", "'" + objName+ " was not visible");
//						status = "Element was not visible";
//						//return status;
//					}
				//}
			} 
					  else if (list.size() == 1) {
							WebElement element = list.get(0);
							//if (element.isDisplayed()) {
								if (element.getAttribute("class") != null&& element.getAttribute("class").contains("disabled")) {
											CucumberTestAutomationLogger.writeToLog("SeleniumKeywordslib","checkElementDisableState() ", "'" +disabledElements+ "' => is disabled");
											extentTestReport.log(LogStatus.FAIL,enabledele[i] + "   is  disabled");
											status= "true";
											//return status;
										} else {
											CucumberTestAutomationLogger.writeToLog("SeleniumKeywordslib","checkElementDisableState() ", "'" + objName+ "' => is  enabled");
											extentTestReport.log(LogStatus.PASS, "File Name " +enabledele[i] +"   is  enabled");
											status= "false";
										//	return status;
								}
								//}
//								else {
//									CATPLMNPILogger.writeToLog("SeleniumKeywordslib","checkElementDisableState() ", "'" + objName+ " was not visible");
//									status = "Element was not visible";
//									//return status;
//								}
//							

							} else {
								CucumberTestAutomationLogger.writeToLog("SeleniumKeywordslib","checkElementDisableState() ", "'" + objName+ "CheckBox was not visible");
								status = "CheckBox was not visible";
								
							}
					}	
			  	   return status;
			} 
		 catch (Exception exception) {
	  		 try
	  		 {
	  			    exception.printStackTrace();
	  				CucumberTestAutomationLogger.writeToLog("SeleniumKeywordslib","checkElementDisableState ", "Exeption thrown");
	  				CucumberTestAutomationLogger.writeToLog("SeleniumKeywordslib","checkElementDisableState ", "object doesnot exists");
	  				
	  		 }
	  		 finally{
	  				extentTestReport.log(LogStatus.FAIL, "Unable to check object Status ");
	  		        extentTestReport.log(LogStatus.FAIL, extentTestReport.addScreenCapture(ExtentReportManager.CaptureScreen(webDriver, propertyFileReader.getValue("SNAPSHOT_FOLDER")))); 
	  		    	// extentReports.endTest(extentTestReport);
	  		    	// extentReports.flush();
	  		        webDriver.close();
	  		         
	  			}
	  	}
		return status;
	}
	 
		 * ############################################################################################# 
		 * ' FUNCTION NAME - checkboxDisableState ' DESCRIPTION - This
		 * function will be used to check the Element is in Disable state' INPUT PARAMETERS -
		 * '################################################################################################
		 
		public String checkboxDisableState(WebDriver webDriver, String objName,String selector,String selectorvalue,String assertValue,ExtentReports extentReports,ExtentTest extentTestReport) {
			String status = "false";
			try {
				  	     CucumberTestAutomationLogger.writeToLog("SeleniumKeywordslib","checkboxDisableState() ", "clicking 1st element");
						 List<WebElement> list = getElements(webDriver, objName,selector,selectorvalue);
						  if (list.size() > 1) {
							CucumberTestAutomationLogger.writeToLog("SeleniumKeywordslib","checkboxDisableState() ", "found more than one element");
							CucumberTestAutomationLogger.writeToLog("SeleniumKeywordslib", "checkboxDisableState() ","web elements size - " + list.size());
							WebElement element =list.get(0);
							//for (WebElement element : list) {
						//if (element.isDisplayed()) {
							if(assertValue.contains("disabled")){
							if (element.getTagName().contains(assertValue)) {
									CucumberTestAutomationLogger.writeToLog(element +"   is  " +assertValue);
									extentTestReport.log(LogStatus.PASS,element +"   is  " +assertValue);
									status=  "true";
									return status;
								} else {
									CucumberTestAutomationLogger.writeToLog("SeleniumKeywordslib","checkboxDisableState() ", "'" + objName+ "' => is  enabled");
									extentTestReport.log(LogStatus.FAIL, element +"   is   not" +assertValue);
									status=  "false";
									return status;
						}
						}
							else
							{
								if (!element.getTagName().contains(assertValue)) {
									CucumberTestAutomationLogger.writeToLog(element +"   is  " +assertValue);
									extentTestReport.log(LogStatus.PASS,objName +"   is  " +assertValue);
									status=  "true";
									return status;
								} else {
									CucumberTestAutomationLogger.writeToLog("SeleniumKeywordslib","checkboxDisableState() ", "'" + objName+ "' => is  enabled");
									extentTestReport.log(LogStatus.FAIL, objName +"   is   not" +assertValue);
									status=  "false";
									return status;
						}
							}
						//}
//						else {
//							CATPLMNPILogger.writeToLog("SeleniumKeywordslib","checkboxDisableState() ", "'" + objName+ " was not visible");
//							status = "Element was not visible";
//							return status;
//						}
					}
				//} 
						  else if (list.size() == 1) {
								WebElement element = list.get(0);
								if (element.isDisplayed()) {
									if(assertValue.contains("disabled")){
									if ((element.getTagName().contains(assertValue)) || (element.getAttribute("class").contains(assertValue))) {
												CucumberTestAutomationLogger.writeToLog("SeleniumKeywordslib","checkboxDisableState() ", "'" +element+ "' => is disabled");
												extentTestReport.log(LogStatus.PASS,objName +"is "   +assertValue);
												status= "true";
												return status;
											} else {
												CucumberTestAutomationLogger.writeToLog("SeleniumKeywordslib","checkboxDisableState() ", "'" + objName+ "' => is  enabled");
												extentTestReport.log(LogStatus.FAIL, objName+"is  not"   +assertValue);
												status= "false";
												return status;
									}
								}
									else
									{
										if (!element.getTagName().contains(assertValue)) {
											CucumberTestAutomationLogger.writeToLog("SeleniumKeywordslib","checkboxDisableState() ", "'" +element+ "' => is disabled");
											extentTestReport.log(LogStatus.PASS,objName +"is "   +assertValue);
											status= "true";
											return status;
										} else {
											CucumberTestAutomationLogger.writeToLog("SeleniumKeywordslib","checkboxDisableState() ", "'" + objName+ "' => is  enabled");
											extentTestReport.log(LogStatus.FAIL, objName+"is  not"   +assertValue);
											status= "false";
											return status;
								}
									}
									}
									else {
										CucumberTestAutomationLogger.writeToLog("SeleniumKeywordslib","checkboxDisableState() ", "'" + objName+ " was not visible");
										status = "Element was not visible";
										return status;
									}
								

								} else {
									CucumberTestAutomationLogger.writeToLog("SeleniumKeywordslib","checkboxDisableState() ", "'" + objName+ "CheckBox was not visible");
									status = "CheckBox was not visible";
									return status;
								}
								
				} 
			 catch (Exception exception) {
		  		 try
		  		 {
		  			    exception.printStackTrace();
		  				CucumberTestAutomationLogger.writeToLog("SeleniumKeywordslib","checkboxDisableState ", "Exeption thrown");
		  				CucumberTestAutomationLogger.writeToLog("SeleniumKeywordslib","checkboxDisableState ", "object doesnot exists");
		  				
		  		 }
		  		 finally{
		  				extentTestReport.log(LogStatus.FAIL, "Unable to check object Status ");
		  		        extentTestReport.log(LogStatus.FAIL, extentTestReport.addScreenCapture(ExtentReportManager.CaptureScreen(webDriver, propertyFileReader.getValue("SNAPSHOT_FOLDER")))); 
		  		    	// extentReports.endTest(extentTestReport);
		  		    	// extentReports.flush();
		  		        webDriver.close();
		  		         
		  			}
		  	}
			return status;
		}
	public String validateDropDownList(WebDriver webDriver,String objName, String selector,String selectorValue, String dropDownList,String assertValue,ExtentReports extentReports,ExtentTest extentTestReport) {
		CucumberTestAutomationLogger.writeToLog("SeleniumKeywordslib","validateDropDownList()", "Drop Down");
		String status = "false";
		try {
			List<WebElement> list = getElements(webDriver, objName, selector,selectorValue);
			List<String> lw1 = new ArrayList<String>();
			WebElement selectElement = null;
			if (list != null) {
				selectElement = list.get(0);
				Select s = new Select(selectElement);
				for (WebElement webElement : s.getOptions()) {
					lw1.add(webElement.getText().trim());
				}
				CucumberTestAutomationLogger.writeToLog("SeleniumKeywordslib","validateDropDownList()", "List" + lw1);
			List<String> dropList = Arrays.asList(dropDownList.split(","));
				CucumberTestAutomationLogger.writeToLog("SeleniumKeywordslib","validateDropDownList()", "dropList" + dropList);
				if (assertValue.equalsIgnoreCase("true")) {
					for (String dropDownValue : dropList) {
						if (lw1.contains(dropDownValue)) {
							CucumberTestAutomationLogger.writeToLog("SeleniumKeywordslib","validateDropDownList()","dropDownValue" + dropDownValue);
							CucumberTestAutomationLogger.writeToLog("SeleniumKeywordslib","validateDropDownList()","List Matches");
							extentTestReport.log(LogStatus.PASS,lw1 +"  Contains the dopdown Value");
							status = "true";
							// return status;
						} else {
							CucumberTestAutomationLogger.writeToLog("SeleniumKeywordslib","validateDropDownList()","List doesnt Matches");
							extentTestReport.log(LogStatus.FAIL,lw1 +" not Contains the dopdown Value");
							status = "List does not matches";
							return status;
						}
					}
				} else {
					for (String dropDownValue : dropList) {
						if (lw1.contains(dropDownValue)) {
							CucumberTestAutomationLogger.writeToLog("SeleniumKeywordslib","validateDropDownList()","dropDownValue" +dropDownValue);
							CucumberTestAutomationLogger.writeToLog("SeleniumKeywordslib","validateDropDownList()","List Matches");
							extentTestReport.log(LogStatus.FAIL,lw1 +"  Contains the dopdown Value");
							status = "List matches";
							// return status;
						} else {
							CucumberTestAutomationLogger.writeToLog("SeleniumKeywordslib","validateDropDownList()","List doesnt Matches");
							extentTestReport.log(LogStatus.PASS,lw1 +"  not Contains the dopdown Value");
							status = "true";
							return status;
						}
					}
				}
			}
		} catch (Exception exception) {
	  		 try
	  		 {
	  			    exception.printStackTrace();
	  				CucumberTestAutomationLogger.writeToLog("SeleniumKeywordslib","checkElementDisableState ", "Exeption thrown");
	  				CucumberTestAutomationLogger.writeToLog("SeleniumKeywordslib","checkElementDisableState ", "object doesnot exists");
	  				
	  		 }
	  		 finally{
	  				extentTestReport.log(LogStatus.FAIL, "Unable to check object Status ");
	  		        extentTestReport.log(LogStatus.FAIL, extentTestReport.addScreenCapture(ExtentReportManager.CaptureScreen(webDriver, propertyFileReader.getValue("SNAPSHOT_FOLDER")))); 
	  		    	// extentReports.endTest(extentTestReport);
	  		    	// extentReports.flush();
	  		        webDriver.close();
	  		         
	  			}
	  	}
		return status;
	}
	public String validateTableHeaders(WebDriver webDriver,String objName, String selector,String selectorValue, String dropDownList,String assertValue,ExtentReports extentReports,ExtentTest extentTestReport) {
		CucumberTestAutomationLogger.writeToLog("SeleniumKeywordslib","validateDropDownList()", "Drop Down");
		String status = "false";
		try {
			List<WebElement> list = getElements(webDriver, objName, selector,selectorValue);
			List<String> lw1 = new ArrayList<String>();
			//WebElement selectElement = null;
			WebElement textnew =null;
			if (list != null) {
				//WebElement selectElement = list.get(0);
				List<WebElement> listnew = getElements(webDriver, objName, selector,selectorValue);
				for(int i=1;i<listnew.size();i++)
				{	
					
				String text = listnew.get(i).getText().trim();
				if((i==38) &&(text.equals("")) || (i==14) &&(text.equals("")) || (i==21)&&(text.equals("Latest Prop65 status")) || (i==6) &&(text.equals("VERSION TYPE")))
				{
					break;
				}

					if(text.equalsIgnoreCase("CLASSIFIED IN"))
					{
						 textnew = listnew.get(i);
					}
					else
					{
				
							textnew = listnew.get(i+1);
					}
					
					((JavascriptExecutor)webDriver).executeScript("arguments[0].scrollIntoView(true);", textnew);
				      lw1.add(text);
				}
				CucumberTestAutomationLogger.writeToLog("SeleniumKeywordslib","validateDropDownList()", "List" + lw1);
			 List<String> dropList = Arrays.asList(dropDownList.split(","));
				CucumberTestAutomationLogger.writeToLog("SeleniumKeywordslib","validateDropDownList()", "dropList" + dropList);
				if (assertValue.equalsIgnoreCase("true")) {
					for (String dropDownValue : dropList) {
						if (lw1.contains(dropDownValue.trim())) {
							CucumberTestAutomationLogger.writeToLog("SeleniumKeywordslib","validateDropDownList()","dropDownValue" + dropDownValue);
							CucumberTestAutomationLogger.writeToLog("SeleniumKeywordslib","validateDropDownList()","List Matches");
							extentTestReport.log(LogStatus.PASS,lw1 +"  Contains the dopdown Value" +dropDownValue);
							status = "true";
							// return status;
						} else {
							CucumberTestAutomationLogger.writeToLog("SeleniumKeywordslib","validateDropDownList()","List doesnt Matches");
							extentTestReport.log(LogStatus.FAIL,lw1 +" not Contains the dopdown Value" +dropDownValue);
							status = "List does not matches";
							return status;
						}
					}
				} else {
					for (String dropDownValue : dropList) {
						if (lw1.contains(dropDownValue)) {
							CucumberTestAutomationLogger.writeToLog("SeleniumKeywordslib","validateDropDownList()","dropDownValue" +dropDownValue);
							CucumberTestAutomationLogger.writeToLog("SeleniumKeywordslib","validateDropDownList()","List Matches");
							extentTestReport.log(LogStatus.FAIL,lw1 +"  Contains the dopdown Value" +dropDownValue);
							status = "List matches";
							// return status;
						} else {
							CucumberTestAutomationLogger.writeToLog("SeleniumKeywordslib","validateDropDownList()","List doesnt Matches");
							extentTestReport.log(LogStatus.PASS,lw1 +"  not Contains the dopdown Value" +dropDownValue);
							status = "true";
							return status;
						}
					}
				}
			}
		} catch (Exception exception) {
	  		 try
	  		 {
	  			    exception.printStackTrace();
	  				CucumberTestAutomationLogger.writeToLog("SeleniumKeywordslib","checkElementDisableState ", "Exeption thrown");
	  				CucumberTestAutomationLogger.writeToLog("SeleniumKeywordslib","checkElementDisableState ", "object doesnot exists");
	  				
	  		 }
	  		 finally{
	  				extentTestReport.log(LogStatus.FAIL, "Unable to check object Status ");
	  		        extentTestReport.log(LogStatus.FAIL, extentTestReport.addScreenCapture(ExtentReportManager.CaptureScreen(webDriver, propertyFileReader.getValue("SNAPSHOT_FOLDER")))); 
	  		    	// extentReports.endTest(extentTestReport);
	  		    	// extentReports.flush();
	  		        webDriver.close();
	  		         
	  			}
	  	}
		return status;
	}
	public String validateTableHeader(WebDriver webDriver,String objName, String selector,String selectorValue, String dropDownList,String assertValue,ExtentReports extentReports,ExtentTest extentTestReport) {
		CucumberTestAutomationLogger.writeToLog("SeleniumKeywordslib","validateDropDownList()", "Drop Down");
		String status = "false";
		try {
			List<WebElement> list = getElements(webDriver, objName, selector,selectorValue);
			List<String> lw1 = new ArrayList<String>();
			//WebElement selectElement = null;
			WebElement textnew =null;
			if (list != null) {
				//selectElement = list.get(0);
				List<WebElement> listnew = getElements(webDriver, objName, selector,selectorValue);
           for(int i=1;i<=listnew.size();i++)
				{	
					
				String text = listnew.get(i).getText().trim();
				if((i==38) &&(text.equals("")) || (i==14) &&(text.equals("")) || (i==21)&&(text.equals("Latest Prop65 status")) || (i==6) &&(text.equals("VERSION TYPE")) || (i==7) &&(text.equalsIgnoreCase("REVISION")) || (i==5) &&(text.equalsIgnoreCase("REVISION")))
				{
					break;
				}

					if(text.equalsIgnoreCase("CLASSIFIED IN"))
					{
						 textnew = listnew.get(i);
					}
					else
					{
								if(i==listnew.size())
								{
									break;
								}
								else
									
								{
									textnew = listnew.get(i+1);
								}
							
					}
					
					((JavascriptExecutor)webDriver).executeScript("arguments[0].scrollIntoView(true);", textnew);
				      lw1.add(text);
				}
				CucumberTestAutomationLogger.writeToLog("SeleniumKeywordslib","validateDropDownList()", "List" + lw1);
			 List<String> dropList = Arrays.asList(dropDownList.split(","));
				CucumberTestAutomationLogger.writeToLog("SeleniumKeywordslib","validateDropDownList()", "dropList" + dropList);
				if (assertValue.equalsIgnoreCase("true")) {
					for (String dropDownValue : dropList) {
						if (lw1.contains(dropDownValue.trim())) {
							CucumberTestAutomationLogger.writeToLog("SeleniumKeywordslib","validateDropDownList()","dropDownValue" + dropDownValue);
							CucumberTestAutomationLogger.writeToLog("SeleniumKeywordslib","validateDropDownList()","List Matches");
							extentTestReport.log(LogStatus.PASS,lw1 +"  Contains the dopdown Value" +dropDownValue);
							status = "true";
							// return status;
						} else {
							CucumberTestAutomationLogger.writeToLog("SeleniumKeywordslib","validateDropDownList()","List doesnt Matches");
							extentTestReport.log(LogStatus.FAIL,lw1 +" not Contains the dopdown Value" +dropDownValue);
							status = "List does not matches";
							return status;
						}
					}
				} else {
					for (String dropDownValue : dropList) {
						if (lw1.contains(dropDownValue)) {
							CucumberTestAutomationLogger.writeToLog("SeleniumKeywordslib","validateDropDownList()","dropDownValue" +dropDownValue);
							CucumberTestAutomationLogger.writeToLog("SeleniumKeywordslib","validateDropDownList()","List Matches");
							extentTestReport.log(LogStatus.FAIL,lw1 +"  Contains the dopdown Value" +dropDownValue);
							status = "List matches";
							// return status;
						} else {
							CucumberTestAutomationLogger.writeToLog("SeleniumKeywordslib","validateDropDownList()","List doesnt Matches");
							extentTestReport.log(LogStatus.PASS,lw1 +"  not Contains the dopdown Value" +dropDownValue);
							status = "true";
							return status;
						}
					}
				}
			}
		} catch (Exception exception) {
	  		 try
	  		 {
	  			    exception.printStackTrace();
	  				CucumberTestAutomationLogger.writeToLog("SeleniumKeywordslib","checkElementDisableState ", "Exeption thrown");
	  				CucumberTestAutomationLogger.writeToLog("SeleniumKeywordslib","checkElementDisableState ", "object doesnot exists");
	  				
	  		 }
	  		 finally{
	  				extentTestReport.log(LogStatus.FAIL, "Unable to check object Status ");
	  		        extentTestReport.log(LogStatus.FAIL, extentTestReport.addScreenCapture(ExtentReportManager.CaptureScreen(webDriver, propertyFileReader.getValue("SNAPSHOT_FOLDER")))); 
	  		    	// extentReports.endTest(extentTestReport);
	  		    	// extentReports.flush();
	  		        webDriver.close();
	  		         
	  			}
	  	}
		return status;
	}
	public String clickEnter(WebDriver webDriver, String objName,String selector,String selectorValue,ExtentReports extentReports,ExtentTest extentTestReport) {
		String status = "false";
		try {
			CucumberTestAutomationLogger.writeToLog("seleniumKeywordslib","clickEnter() ", " objName - " + objName+ " selector - " + selector);
			CucumberTestAutomationLogger.writeToLog("seleniumKeywordslib","clickEnter() ", "before finding element");
			List<WebElement> list = getElements(webDriver, objName, selector,selectorValue);
		
			WebElement element = null;
			if (list != null)
				element = list.get(0);
			CucumberTestAutomationLogger.writeToLog("seleniumKeywordslib",
					"clickEnter() ", "ListSize" + list.size());
			if (element != null) {
				element.sendKeys(Keys.ENTER);


				(new Robot()).keyPress(java.awt.event.KeyEvent.VK_ENTER);

				(new Robot()).keyRelease(java.awt.event.KeyEvent.VK_ENTER);

				CucumberTestAutomationLogger.writeToLog("seleniumKeywordslib","clickEnter() ", "after click on element");

				status = "true";
				extentTestReport.log(LogStatus.PASS, "click on ENTER" );
				return status;
			}
		} catch (Exception e) {
			e.printStackTrace();
			CucumberTestAutomationLogger.writeToLog("seleniumKeywordslib","clickEnter() ","Exception exeption thrown in clickEnter"+ e.getMessage());
			String err[]=e.getMessage().split("\n");
			status=  "Exception " +err[0].replaceAll("'", "") + " Occurred";
			return status;

		}
		return status;

	}
	
	 * ##########################################################################
	 * ################### ' FUNCTION NAME - setScoreInAssessmentTab() ' DESCRIPTION -
	 * This function will select scores in Assessment ' INPUT PARAMETERS -
	 * Obj - Name of the Object
	 * '################################################################################################
	 
	public String setScoreInAssessmentTab(WebDriver webDriver, String objName,String scores,String checkboxSelectorValue,ExtentReports extentReports,ExtentTest extentTestReport) {
		String status = "false";
		   Actions act = new Actions(webDriver);
	       try{  
		    List<WebElement> list1 = getElements(webDriver, objName, selector,"//*[@class='assessment-table']");
				CucumberTestAutomationLogger.writeToLog("SeleniumKeywordslib", "setScoreInAssessmentTab() ","web elements size - " + list1.size());
				if (list1.size() == 1) {
					CucumberTestAutomationLogger.writeToLog("SeleniumKeywordslib","setScoreInAssessmentTab() ", "found more than one element");
					 int  row=0;
					List<WebElement> list = getElements(webDriver, objName, selector,selectorvalue);
					WebElement we = null;
					if (list != null)
						we = list.get(0);
					if (we != null) {
						List<WebElement> rows = null;
						rows = (we.findElements(By.xpath("//*[text()='Deliverable/Sub-Deliverable/Activity']//following::tr[3]//following::td[@class='assessment-table__rows__td assessment-table__rows__td--gateway-marker']")));
					
					CucumberTestAutomationLogger.writeToLog("SeleniumKeywordslib","setScoreInAssessmentTab() ", "rows - " + rows.size()+" and row num" +row);
					if (rows != null) {
					//	int tempRowNo = -1;
						for (WebElement werowCell : rows) {
							String value1 = null;
						//	tempRowNo++;
					value1 = werowCell.getText();
					CucumberTestAutomationLogger.writeToLog("SeleniumKeywordslib","setScoreInAssessmentTab() ", "VALUE - "+ value1);
					
					if (value1 != null) {
						
					        act.moveToElement(werowCell).click(werowCell).build().perform();
					        setCheckbox(webDriver,objName, selector, checkboxSelectorValue, extentReports, extentTestReport);
					         clickByJavaScript(webDriver, objName, selector, checkboxSelectorValue+"//following::a[1]", extentReports, extentTestReport);
					        status="true";
					        extentTestReport.log(LogStatus.PASS, "move to element" +objName);
	                         return status;
	                         
					   // }
						
	                        
									            								    	
						}   
					 }
							            						}
			            							}
	else {
					extentTestReport.log(LogStatus.FAIL, "move to element" +objName);
					CucumberTestAutomationLogger.writeToLog("SeleniumKeywordslib","setScoreInAssessmentTab ", "'" + objName+ "Object was not visible");
					status = "Link was not visible";
					return status;
				}

	         }
	       }
				catch (NoSuchElementException ns) {
	        ns.printStackTrace();
	        CucumberTestAutomationLogger.writeToLog("SeleniumKeywordslib", "setScoreInAssessmentTab() ","Object not found exeption thrown");
	        CucumberTestAutomationLogger.writeToLog("SeleniumKeywordslib", "setScoreInAssessmentTab() ", "'" + objName + "'object does not exist");
	        status = "false";
	        extentTestReport.log(LogStatus.FAIL, "Unable to click on " +objName+ "Scores");
	        return status;
	 } catch (Exception ex) {
	 	try
	 	{

	 		ex.printStackTrace();
	        CucumberTestAutomationLogger.writeToLog("SeleniumKeywordslib", "setKeyDates() ","Exception occurred");
	        extentTestReport.log(LogStatus.FAIL,"select " +objName+ "option");
	        String err[]=ex.getMessage().split("\n");
			status = "Exception " +err[0].replaceAll("'", "") + " Exception Occurred";
			return status;
	 	}
	 	finally
	 	{
	 		     extentTestReport.log(LogStatus.FAIL, "Unable to click on " +objName+ "Calender");
		         extentTestReport.log(LogStatus.FAIL, extentTestReport.addScreenCapture(ExtentReportManager.CaptureScreen(webDriver, propertyFileReader.getValue("SNAPSHOT_FOLDER")))); 
		    	// extentReports.endTest(extentTestReport);
		    	// extentReports.flush();
		         //extentReports.close(); 
		        webDriver.close();
		         //webDriver.quit();
		         
	 	}
	 }
			return status;
	}	
	public String checkNoOfRecords(WebDriver webDriver,String objName, String selector,String selectorvalue,ExtentReports extentReports,ExtentTest extentTestReport) {
		CucumberTestAutomationLogger.writeToLog("SeleniumKeywordslib","checkNoOfRecords() ", " objName - "+ objName);
		String rowsize="";
		try {
			List<WebElement> list = getElements(webDriver, objName, selector,selectorvalue);
			WebElement we = null;
			if (list != null)
				we = list.get(0);
			if (we != null)
			{
				int mRows = 0;
				List<WebElement> rows = we.findElements(By.xpath(selectorvalue));
				mRows = rows.size();
				CucumberTestAutomationLogger.writeToLog("SeleniumKeywordslib","checkNoOfRecords() ","Total rows - " +( mRows-1));
				if(rows != null)
				{
					    rowsize = Integer.toString(rows.size());
					    extentTestReport.log(LogStatus.PASS, "No of rows in table" +rowsize);
						CucumberTestAutomationLogger.writeToLog("SeleniumKeywordslib","checkNoOfRecords() ", "No of rows in table matches with expected value");
						return rowsize;
				}
	
			}
				
			 else 
			 {
				 extentTestReport.log(LogStatus.FAIL, "'"+ objName + "' =>does not exist!");
				CucumberTestAutomationLogger.writeToLog("SeleniumKeywordslib","checkNoOfRecords() ", "'"+ objName + "' =>does not exist!");
				rowsize="0";
				return rowsize;
			 }
		
		} catch (Exception exception) {
	  		 try
	  		 {
	  			    exception.printStackTrace();
	  				CucumberTestAutomationLogger.writeToLog("SeleniumKeywordslib","checkNoOfRecords() ", "Unable to get num of rows in table");
	  		 }
	  		 finally{
	  				extentTestReport.log(LogStatus.FAIL, "Unable to get num of rows in table");
	  		        extentTestReport.log(LogStatus.FAIL, extentTestReport.addScreenCapture(ExtentReportManager.CaptureScreen(webDriver, propertyFileReader.getValue("SNAPSHOT_FOLDER")))); 
	  		    	extentReports.endTest(extentTestReport);
	  		    	extentReports.flush();
	  		        webDriver.close();
	  		   	}
	
	}
		return rowsize;
	}
	public String getSelectedItemFromList(WebDriver webDriver,String objName, String selector,String selectorvalue,ExtentReports extentReports,ExtentTest extentTestReport) {
		CucumberTestAutomationLogger.writeToLog("SeleniumKeywordslib","checkNoOfRecords() ", " objName - "+ objName);
		String item = "";
		try {
			List<WebElement> list = getElements(webDriver, objName, selector,selectorvalue);
			WebElement selectElement = null;
			if (list != null)
				selectElement = list.get(0);
			if (selectElement != null) {
			if ((selectElement.getTagName().equalsIgnoreCase("select"))) {
					Select select = new Select(selectElement);
					item = select.getFirstSelectedOption().getText();
				
				} else if ((selectElement.getTagName()
						.equalsIgnoreCase("input"))
						|| (selectElement.getTagName().equalsIgnoreCase("span"))) {

					item = selectElement.getText();
				
				}

			}
		
		} catch (Exception exception) {
	  		 try
	  		 {
	  			    exception.printStackTrace();
	  				CucumberTestAutomationLogger.writeToLog("SeleniumKeywordslib","checkNoOfRecords() ", "Unable to get num of rows in table");
	  		 }
	  		 finally{
	  				extentTestReport.log(LogStatus.FAIL, "Unable to get num of rows in table");
	  		        extentTestReport.log(LogStatus.FAIL, extentTestReport.addScreenCapture(ExtentReportManager.CaptureScreen(webDriver, propertyFileReader.getValue("SNAPSHOT_FOLDER")))); 
	  		    	extentReports.endTest(extentTestReport);
	  		    	extentReports.flush();
	  		        webDriver.close();
	  		   	}
	
	}
		return item;
	}
	
	 * ##########################################################################
	 * ################### ' FUNCTION NAME - setMetricsforGateways() ' DESCRIPTION -
	 * This function will select Date from Calender ' INPUT PARAMETERS -
	 * Obj - Name of the Object
	 * '################################################################################################
	 
	public String setMetricsforGateways(WebDriver webDriver, String objName,String rowName,String keyDates,ExtentReports extentReports,ExtentTest extentTestReport) {
		String status = "false";
		   Actions act = new Actions(webDriver);
	        String[] setMetricsforGateways= keyDates.split(",");
	         
	         
	       try{  
		    List<WebElement> list1 = getElements(webDriver, objName, selector,selectorvalue);
				CucumberTestAutomationLogger.writeToLog("SeleniumKeywordslib", "setMetricsforGateways() ","web elements size - " + list1.size());
				if (list1.size()==1) {
					CucumberTestAutomationLogger.writeToLog("SeleniumKeywordslib","setMetricsforGateways() ", "found more than one element");
					 int rowNo = 0;
							int colNo = 0;
							int  row=0;
							int col=0;
								List<WebElement> list = getElements(webDriver, objName, selector,selectorvalue);
					WebElement we = null;
					if (list != null)
						we = list.get(0);
					if (we != null) {
						List<WebElement> rows = null;
						rows = (we.findElements(By.xpath(".//tr")));
					
					CucumberTestAutomationLogger.writeToLog("SeleniumKeywordslib","setMetricsforGateways() ", "rows - " + rows.size()+" and row num" +row);
					if (rows != null) {
						int tempRowNo = -1;
						for (WebElement werowCell : rows) {
							String value1 = null;
							tempRowNo++;
					value1 = werowCell.getText();
					CucumberTestAutomationLogger.writeToLog("SeleniumKeywordslib","setMetricsforGateways() ", "VALUE - "+ value1);
					
					if (value1 != null) {
						if ((!value1.contains("L")) && (!value1.contains("Risk Factor"))) {
						if (value1.contains(rowName)) {
							rowNo = tempRowNo;
							row = rowNo;
							List<WebElement> cols = null;	
							 String value2 = null;
							for(int i=0;i<setMetricsforGateways.length;i++)
						{
								
							List<WebElement> collist = getElements(webDriver, objName, selector,selectorvalue);
							we = collist.get(0);
						 cols = (we.findElements(By.xpath(".//th")));
						  CucumberTestAutomationLogger.writeToLog("SeleniumKeywordslib","setMetricsforGateways() ", "rows - " + cols.size());
						  String metrics = setMetricsforGateways[i];
						  String[] gwdates =metrics.split(":");
					         String colName = gwdates[0];
					        String gwmetrics = gwdates[1];
						 if (cols != null) 
					       {
					    	    int tempColNo = -1;
				             for(WebElement wecolCell :cols)
					             {
								    tempColNo++;
					            	 value2 = wecolCell.getText();
								     if(value2 == null)
									   {
								    	 CucumberTestAutomationLogger.writeToLog("SeleniumKeywordslib","setMetricsforGateways() ", "value2 null " + value2);
								    	
									   }
									 else if (value2 != null) 
						    	       {
						    		   colNo = tempColNo;
									    col = colNo;
									    if (value2.equalsIgnoreCase(colName)) 
											{
									    	if(gwmetrics.contains("N/A"))
									    	{
									    		
									    		//WebElement txtField = webDriver.findElement(By.xpath("//*[text()="+ "'"+rowName +"'"+"]//following::input[" + col + "]"));
									    		//act.moveToElement(webDriver.findElement(By.xpath("//*[text()="+ "'"+rowName +"'"+"]//following::input[" + col + "]"))).click(webDriver.findElement(By.xpath("//*[text()="+ "'"+rowName +"'"+"]//following::input[" + col + "]"))).build().perform();
												setCheckbox(webDriver, objName, selector, "//*[text()='Not Applicable:']//following::input[" + col + "]", extentReports, extentTestReport);
									    		webDriver.manage().timeouts().implicitlyWait(70, TimeUnit.SECONDS);
									    		//enterText(webDriver, objName, selector,"//textarea[@name='comment']","Comments", extentReports, extentTestReport);
							           		      
									    		status ="true";
												extentTestReport.log(LogStatus.PASS, "Enter " +gwmetrics+ "in "+rowName +" of" +objName);
												break;
									    		
									    	}
									    	else
									    	{
									    	WebElement txtField = webDriver.findElement(By.xpath("//*[text()="+ "'"+rowName +"'"+"]//following::td[" + col + "]"));
								    		//txtField.click();
								    		//txtField.clear();
											
											String initText = txtField.getText();
											
											if(initText.isEmpty() && (initText.equals("")))
											{
												// act.moveToElement(txtField).build().perform();
												act.moveToElement(txtField).click().sendKeys(gwmetrics).build().perform();;
													webDriver.manage().timeouts().implicitlyWait(70, TimeUnit.SECONDS);
												   // act.moveToElement(webDriver.findElement(By.xpath("//tr[@class='page-title']"))).click(webDriver.findElement(By.xpath("//tr[@class='page-title']"))).build().perform();
												   status ="true";
												   extentTestReport.log(LogStatus.PASS, "Enter " +gwmetrics+ "in "+ rowName +"of" +objName);
												    break;
											}
											else
											{
												
											}
									    	}
									    		    
									       }
											

							  }
											
								 }
					          } 
					      
					      }
							
							
					       }
					            		
					      }
						if(value1.contains("Risk Factor"))
						{
							break;
						}
						}
					
						}  
						
						
					 }
			     }
					return status;
			   }
				
	else {
					extentTestReport.log(LogStatus.FAIL, "move to element" +objName);
					CucumberTestAutomationLogger.writeToLog("SeleniumKeywordslib","setMetricsforGateways ", "'" + objName+ "Object was not visible");
					status = "Link was not visible";
					return status;
				}

	         }catch (NoSuchElementException ns) {
	        ns.printStackTrace();
	        CucumberTestAutomationLogger.writeToLog("SeleniumKeywordslib", "setMetricsforGateways() ","Object not found exeption thrown");
	        CucumberTestAutomationLogger.writeToLog("SeleniumKeywordslib", "setMetricsforGateways() ", "'" + objName + "'object does not exist");
	        status = "false";
	        extentTestReport.log(LogStatus.FAIL, "Unable to click on " +objName+ "Calender");
	        return status;
	 } catch (Exception ex) {
	 	try
	 	{

	 		ex.printStackTrace();
	        CucumberTestAutomationLogger.writeToLog("SeleniumKeywordslib", "setMetricsforGateways() ","Exception occurred");
	        extentTestReport.log(LogStatus.FAIL,"select " +objName+ "option");
	        String err[]=ex.getMessage().split("\n");
			status = "Exception " +err[0].replaceAll("'", "") + " Exception Occurred";
			return status;
	 	}
	 	finally
	 	{
	 		     extentTestReport.log(LogStatus.FAIL, "Unable to click on " +objName+ "Calender");
		         extentTestReport.log(LogStatus.FAIL, extentTestReport.addScreenCapture(ExtentReportManager.CaptureScreen(webDriver, propertyFileReader.getValue("SNAPSHOT_FOLDER")))); 
		    	// extentReports.endTest(extentTestReport);
		    	// extentReports.flush();
		         //extentReports.close(); 
		        webDriver.close();
		         //webDriver.quit();
		         
	 	}
	 }
		//	return status;
	}
	

   * ##########################################################################
   * ################### ' FUNCTION NAME - getText' DESCRIPTION - This
   * function will be used to get the text with out property name' INPUT PARAMETERS -
   * spyObj - object
   * '################################################################################################
   
	  public String checkLengthofString(WebDriver webDriver,String objName,String selectorvalue,ExtentReports extentReports,ExtentTest extentTestReport) {
			String status = "";
			try {
				int len = selectorvalue.length();
				
			if(len <=100) 
			{
				CucumberTestAutomationLogger.writeToLog("SeleniumKeywordslib","checkLengthofString()","'"+  "' =>Length of  "+ objName +":"+len);
				extentTestReport.log(LogStatus.PASS, "Length of " +objName + " upTo 100Characters ");
				status="true";
				return status;
		    }
			 else
			{
				 CucumberTestAutomationLogger.writeToLog("SeleniumKeywordslib","checkLengthofString()","'"+  "' =>Length of  "+ objName +":"+len);
				extentTestReport.log(LogStatus.FAIL, "Length of " +objName +" has more than 100 Characters ");
				status="false";
				return status;
			}
								
					
			
			} catch (Exception ex) {
				try
				{
				ex.printStackTrace();
				CucumberTestAutomationLogger.writeToLog("SeleniumKeywordslib", "getText()","Exception occurred");
			//	npiCTRMLogger.writeToLog("SeleniumKeywordslib", "getText()","Exception '" + ex.getMessage() + "' Occurred");
				String err[]=ex.getMessage().split("\n");
				 status = "Exception " +err[0].replaceAll("'", "") + " Exception Occurred";
				return status;	
				}
				finally
				{
					 extentTestReport.log(LogStatus.FAIL, "Unable to retrive the text from web element ");
			         extentTestReport.log(LogStatus.FAIL, extentTestReport.addScreenCapture(ExtentReportManager.CaptureScreen(webDriver, propertyFileReader.getValue("SNAPSHOT_FOLDER")))); 
			    	// extentReports.endTest(extentTestReport);
			    	// extentReports.flush();
			         //extentReports.close(); 
			        webDriver.close();
			         //webDriver.quit();
			         
				}
			}
		//	return status;
		}
	  
	   * ##########################################################################
	   * ################### ' FUNCTION NAME - getText' DESCRIPTION - This
	   * function will be used to get the text with out property name' INPUT PARAMETERS -
	   * spyObj - object
	   * '################################################################################################
	   
		  public String checkLengthofString(WebDriver webDriver,String objName,String selectorvalue,String length,ExtentReports extentReports,ExtentTest extentTestReport) {
				String status = "";
				try {
					int len = selectorvalue.length();
				
				if(len <=Integer.parseInt(length)) 
				{
					CucumberTestAutomationLogger.writeToLog("SeleniumKeywordslib","checkLengthofString()","'"+  "' =>Length of  "+ objName +":"+len);
				//	extentTestReport.log(LogStatus.PASS, "Length of " +objName + " upTo 100Characters ");
					status="Length < "+length +"chars";
					return status;
			    }
				 else
				{
					 CucumberTestAutomationLogger.writeToLog("SeleniumKeywordslib","checkLengthofString()","'"+  "' =>Length of  "+ objName +":"+len);
					//extentTestReport.log(LogStatus.FAIL, "Length of " +objName +" has more than 100 Characters ");
					 status="Length > "+length +"chars";
					// status="false";
					return status;
				}
									
						
				
				} catch (Exception ex) {
					try
					{
					ex.printStackTrace();
					CucumberTestAutomationLogger.writeToLog("SeleniumKeywordslib", "getText()","Exception occurred");
				//	npiCTRMLogger.writeToLog("SeleniumKeywordslib", "getText()","Exception '" + ex.getMessage() + "' Occurred");
					String err[]=ex.getMessage().split("\n");
					 status = "Exception " +err[0].replaceAll("'", "") + " Exception Occurred";
					return status;	
					}
					finally
					{
						 extentTestReport.log(LogStatus.FAIL, "Unable to retrive the text from web element ");
				         extentTestReport.log(LogStatus.FAIL, extentTestReport.addScreenCapture(ExtentReportManager.CaptureScreen(webDriver, propertyFileReader.getValue("SNAPSHOT_FOLDER")))); 
				    	// extentReports.endTest(extentTestReport);
				    	// extentReports.flush();
				         //extentReports.close(); 
				        webDriver.close();
				         //webDriver.quit();
				         
					}
				}
			//	return status;
			}
	  
	   * ##########################################################################
	   * ################### ' FUNCTION NAME - getText' DESCRIPTION - This
	   * function will be used to get the text with out property name' INPUT PARAMETERS -
	   * spyObj - object
	   * '################################################################################################
	   
		  public String checkLengthofString(WebDriver webDriver,String objName,String selectorvalue,int length,ExtentReports extentReports,ExtentTest extentTestReport) {
				String status = "";
				try {
					int len = selectorvalue.length();
				
				if(len <=length) 
				{
					CucumberTestAutomationLogger.writeToLog("SeleniumKeywordslib","checkLengthofString()","'"+  "' =>Length of  "+ objName +":"+len);
					extentTestReport.log(LogStatus.PASS, "Length of " +objName + " upTo 100Characters ");
					status="true";
					return status;
			    }
				 else
				{
					 CucumberTestAutomationLogger.writeToLog("SeleniumKeywordslib","checkLengthofString()","'"+  "' =>Length of  "+ objName +":"+len);
					extentTestReport.log(LogStatus.FAIL, "Length of " +objName +" has more than 100 Characters ");
					status="false";
					return status;
				}
									
						
				
				} catch (Exception ex) {
					try
					{
					ex.printStackTrace();
					CucumberTestAutomationLogger.writeToLog("SeleniumKeywordslib", "getText()","Exception occurred");
				//	npiCTRMLogger.writeToLog("SeleniumKeywordslib", "getText()","Exception '" + ex.getMessage() + "' Occurred");
					String err[]=ex.getMessage().split("\n");
					 status = "Exception " +err[0].replaceAll("'", "") + " Exception Occurred";
					return status;	
					}
					finally
					{
						 extentTestReport.log(LogStatus.FAIL, "Unable to retrive the text from web element ");
				         extentTestReport.log(LogStatus.FAIL, extentTestReport.addScreenCapture(ExtentReportManager.CaptureScreen(webDriver, propertyFileReader.getValue("SNAPSHOT_FOLDER")))); 
				    	// extentReports.endTest(extentTestReport);
				    	// extentReports.flush();
				         //extentReports.close(); 
				        webDriver.close();
				         //webDriver.quit();
				         
					}
				}
			//	return status;
			}
	  
	   * ##########################################################################
	   * ################### ' FUNCTION NAME - getText' DESCRIPTION - This
	   * function will be used to get the text with out property name' INPUT PARAMETERS -
	   * spyObj - object
	   * '################################################################################################
	   
		  public String compareRetainTillDateis30DaysAfterCurrentDate(WebDriver webDriver,String objName,String visibleDate,ExtentReports extentReports,ExtentTest extentTestReport) {
				String status = "";
				try {
					 Date after30daysDate =new SimpleDateFormat("dd-MMM-yy").parse(visibleDate);
					SimpleDateFormat formatter = new SimpleDateFormat("dd-MMM-yy");
                   Calendar cal =  Calendar.getInstance();
					 cal.setTime(after30daysDate);
					 cal.add(Calendar.DAY_OF_MONTH, -30);
					 Date today30 = cal.getTime();
					String format = formatter.format(today30);
					Date today = new Date();
					 String todaysDate = formatter.format(today);
					 
				if(todaysDate.equals(format)) 
				{
					CucumberTestAutomationLogger.writeToLog("SeleniumKeywordslib","compareRetainTillDateis30DaysAfterCurrentDate()","'"+  "' =>Length of  "+ objName +":");
					extentTestReport.log(LogStatus.PASS,  objName + "displaying after 30 days Date from Current Date ");
					status="true";
					return status;
			    }
				 else
				{
					 CucumberTestAutomationLogger.writeToLog("SeleniumKeywordslib","compareRetainTillDateis30DaysAfterCurrentDate()","'"+  "' =>Length of  "+ objName +":");
					extentTestReport.log(LogStatus.FAIL, objName +" not displaying after 30 days Date from Current Date");
					status="false";
					return status;
				}
									
						
				
				} catch (Exception ex) {
					try
					{
					ex.printStackTrace();
					CucumberTestAutomationLogger.writeToLog("SeleniumKeywordslib", "getText()","Exception occurred");
				//	npiCTRMLogger.writeToLog("SeleniumKeywordslib", "getText()","Exception '" + ex.getMessage() + "' Occurred");
					String err[]=ex.getMessage().split("\n");
					 status = "Exception " +err[0].replaceAll("'", "") + " Exception Occurred";
					return status;	
					}
					finally
					{
						 extentTestReport.log(LogStatus.FAIL, "Unable to retrive the text from web element ");
				         extentTestReport.log(LogStatus.FAIL, extentTestReport.addScreenCapture(ExtentReportManager.CaptureScreen(webDriver, propertyFileReader.getValue("SNAPSHOT_FOLDER")))); 
				    	// extentReports.endTest(extentTestReport);
				    	// extentReports.flush();
				         //extentReports.close(); 
				        webDriver.close();
				         //webDriver.quit();
				         
					}
				}
			//	return status;
			}
		  
		  public HashMap<String, String> selectSalesModelsFromSerialNumbersTab(WebDriver webDriver,String objName, String selector,String selectorValue,String salesModelType,ExtentReports extentReports,ExtentTest extentTestReport) {
				CucumberTestAutomationLogger.writeToLog("SeleniumKeywordslib","selectSalesModelsFromSerialNumbersTab()", "Drop Down");
				 HashMap<String, String> map = new HashMap<String, String>();
				try {
					List<WebElement> list = getElements(webDriver, objName, selector,selectorValue);
					WebElement selectElement = null;
					if (list != null) {
						selectElement =list.get(0);
						Select s = new Select(selectElement);
						for (WebElement webElement : s.getOptions()) {
							if(webElement.getText().contains(salesModelType))
							{
								String optionIndex = webElement.getAttribute("index");
								 map.put(webElement.getText(), optionIndex);
								CucumberTestAutomationLogger.writeToLog("SeleniumKeywordslib","selectSalesModelsFromSerialNumbersTab()", "List" + map);
								extentTestReport.log(LogStatus.PASS,map +"  Contains the dopdown Value");
							   
							}
							
							//else
							
						}
						 return map;
					}
						
				} catch (Exception exception) {
			  		 try
			  		 {
			  			    exception.printStackTrace();
			  				CucumberTestAutomationLogger.writeToLog("SeleniumKeywordslib","checkElementDisableState ", "Exeption thrown");
			  				CucumberTestAutomationLogger.writeToLog("SeleniumKeywordslib","checkElementDisableState ", "object doesnot exists");
			  				
			  		 }
			  		 finally{
			  				extentTestReport.log(LogStatus.FAIL, "Unable to check object Status ");
			  		        extentTestReport.log(LogStatus.FAIL, extentTestReport.addScreenCapture(ExtentReportManager.CaptureScreen(webDriver, propertyFileReader.getValue("SNAPSHOT_FOLDER")))); 
			  		    	// extentReports.endTest(extentTestReport);
			  		    	// extentReports.flush();
			  		        webDriver.close();
			  		         
			  			}
			  	}
				return map;
			}
		  
   * * ##########################################################################
	 * ################### ' FUNCTION NAME - selectMultipleOptionsFromList() ' DESCRIPTION
	 * - This function will Select item from the from list box. ' INPUT
	 * PARAMETERS - Obj - Name of the Object ' item - Option of that needs to be
	 * selected
	 * '################################################################################################
		public String selectMultipleOptionsFromList(WebDriver webDriver, String objName,String selector, String selectorvalue,String paramvalues, String selection,ExtentReports extentReports,ExtentTest extentTestReport) {
				CucumberTestAutomationLogger.writeToLog("SeleniumKeywordslib", "select FromList() "," objName - " + objName + ".. input -.. selection - " + selection);
				String status = "false";

				try {
					String[] input= paramvalues.split(",");
					
					for(int i=0;i<input.length;i++)
					{
					   if (selection != null) {
		     			waitForExistence(webDriver, objName,selector,selectorvalue);
					    List<WebElement> list = getElements(webDriver, objName, selector, selectorvalue);
						WebElement selectElement = null;
						if (list.size()>0)
							selectElement = list.get(0);

						if (selectElement != null) {
							if (selectElement.isDisplayed()) {
								
								if ((selectElement.getTagName().equalsIgnoreCase("input"))|| (selectElement.getTagName().equalsIgnoreCase("span"))) {
									CucumberTestAutomationLogger.writeToLog("SeleniumKeywordslib","select FromList()--- TagName",selectElement.getTagName());
									selectElement.clear();
									selectElement.sendKeys(input[i]);
									selectElement.sendKeys(Keys.RETURN);
									CucumberTestAutomationLogger.writeToLog("SeleniumKeywordslib","select FromList() ",input[i]+ "=> Item was selected successfully from the"+ "'" + objName + "'"+ "ListBox");
									status = "true";
									 extentTestReport.log(LogStatus.PASS, "select "+input[i] +" option from " +objName+ " dropdown" );
									return status;

								} else if ((selectElement.getTagName().equalsIgnoreCase("select"))) {
									Select select = new Select(selectElement);
									if (selection.equalsIgnoreCase("value")) {
										CucumberTestAutomationLogger.writeToLog("SeleniumKeywordslib","select FromList() ","by option value");

										select.selectByValue(input[i]);
										webDriver.manage().timeouts().pageLoadTimeout(1, TimeUnit.MINUTES);
										CucumberTestAutomationLogger.writeToLog("SeleniumKeywordslib","select FromList() ",input[i]+ "=> Item was selected successfully from the"+ "'" + objName + "'"+ "ListBox");
										status = "true";
										 extentTestReport.log(LogStatus.PASS, "select "+input[i] +" option from " +objName+ " dropdown");
										return status;

									} else if (selection.equalsIgnoreCase("text")) {
										CucumberTestAutomationLogger.writeToLog("SeleniumKeywordslib","select FromList() ", "by label");
										select.selectByVisibleText(input[i].trim());
										CucumberTestAutomationLogger.writeToLog("SeleniumKeywordslib","select FromList() ",input[i]+ "=> Item was selected successfully from the"+ "'" + objName + "'"+ "ListBox");
										status = "true";
										 extentTestReport.log(LogStatus.PASS, "select "+input[i] +" option from " +objName+ " dropdown");
										
									} else if (selection.equalsIgnoreCase("index")) {
										CucumberTestAutomationLogger.writeToLog("SeleniumKeywordslib","select FromList() ","by option index");
										int index= Integer.parseInt(input[i]);
										select.selectByIndex(index);
									} 

								} else {
									CucumberTestAutomationLogger.writeToLog("SeleniumKeywordslib","select FromList()--- TagName is not input neither select",selectElement.getTagName());
									status = "false";
									extentTestReport.log(LogStatus.FAIL, "select "+input[i] +" option from " +objName+ " dropdown");
									return status;
								}
							} else if (!selectElement.isDisplayed()) {
		                       	if ((selectElement.getTagName().equalsIgnoreCase("input"))|| (selectElement.getTagName().equalsIgnoreCase("span"))) {
									selectElement.clear();
									selectElement.sendKeys(input[i]);
									selectElement.sendKeys(Keys.RETURN);
									status = "true";
									extentTestReport.log(LogStatus.PASS, "select "+input[i] +"from list dropdown" +objName);
									CucumberTestAutomationLogger.writeToLog("SeleniumKeywordslib","select FromList() ",input[i]+ "=> Item was selected successfully from the"+ "'" + objName + "'"+ "ListBox");
								} else {
									Select select = new Select(selectElement);
									if (selection.equalsIgnoreCase("value")) {
										CucumberTestAutomationLogger.writeToLog("SeleniumKeywordslib","select FromList() ","by option value");
		                                select.selectByValue(input[i]);
										status = "true";
										 extentTestReport.log(LogStatus.PASS, "select "+input[i] +"from list dropdown" +objName);
										CucumberTestAutomationLogger.writeToLog("SeleniumKeywordslib","select FromList() ",input+ "=> Item was selected successfully from the"+ "'" + objName + "'"+ "ListBox");
									} else if (selection.equalsIgnoreCase("text")) {
										CucumberTestAutomationLogger.writeToLog("SeleniumKeywordslib","select FromList() ", "by label");
										select.selectByVisibleText(input[i].trim());
										status = "true";
										 extentTestReport.log(LogStatus.PASS, "select "+input[i] +" option from " +objName +" dropdown" );
										CucumberTestAutomationLogger.writeToLog("SeleniumKeywordslib","select FromList() ",input+ "=> Item was selected successfully from the"+ "'" + objName + "'"+ "ListBox");
									} else if (selection
											.equalsIgnoreCase("index")) {
										CucumberTestAutomationLogger.writeToLog("SeleniumKeywordslib","select FromList() ","by option index");
										select.selectByIndex(Integer.parseInt(input[i]));
										status = "true";
										 extentTestReport.log(LogStatus.PASS, "select "+input[i] +" option from " +objName +" dropdown");
										CucumberTestAutomationLogger.writeToLog("SeleniumKeywordslib","select FromList() ",input[i]+ "=> Item was selected successfully from the"+ "'" + objName + "'"+ "ListBox");
									} 
								}

							}

							else {
								CucumberTestAutomationLogger.writeToLog("SeleniumKeywordslib","select FromList() ", "'" + objName+ "' list was not visible");
								status = "false";
								extentTestReport.log(LogStatus.FAIL, "select "+input[i] +" option from " +objName+ " dropdown");
								return status;
							}
						} else {
							CucumberTestAutomationLogger.writeToLog("SeleniumKeywordslib","select FromList() ","Invalid keyword usage. '" + objName+ "' is not a list");
							status = "false";
							extentTestReport.log(LogStatus.FAIL, "select "+input[i] +" option from " +objName+ " dropdown");
							return status;
						}

					}
				}
				}
				catch (Exception ex) {
		            try{
		                ex.printStackTrace();
		                String err[]=ex.getMessage().split("\n");
		                CucumberTestAutomationLogger.writeToLog("Input", "input()", " Object not found exeption thrown");
		                status = "Exception " +err[0].replaceAll("'", "") + " Occurred";
		                return status;
		            }

					finally
					{
						 extentTestReport.log(LogStatus.FAIL, "Unable to select  option from " +objName +" dropdown");
				         extentTestReport.log(LogStatus.FAIL, extentTestReport.addScreenCapture(ExtentReportManager.CaptureScreen(webDriver, propertyFileReader.getValue("SNAPSHOT_FOLDER")))); 
				    	// extentReports.endTest(extentTestReport);
				    	// extentReports.flush();
				         //extentReports.close(); 
				        webDriver.close();
				         //webDriver.quit();
				         
					}
				}
				return status;

			}
		
   * * ##########################################################################
	 * ################### ' FUNCTION NAME - validateTableData() ' DESCRIPTION
	 * - This function will Validate whether the tablecontains the search data or not. ' INPUT
	 * PARAMETERS - Obj - Name of the Object ' item - Option of that needs to be
	 * selected
	 * '################################################################################################	
		public String validateTableData(WebDriver webDriver, String objName,String selector,String selectorvalue,String data,ExtentReports extentReports,ExtentTest extentTestReport) {
			CucumberTestAutomationLogger.writeToLog("SeleniumKeywordslib", "validateTableData()","validateTableData  " + data+ "..objName - " + objName);
			String value = null;
			String getVal = null;
		    String status ="";
			int  row=0;
			try {
				
				List<WebElement> list = getElements(webDriver, objName, selector,selectorvalue);
				WebElement we = null;
				if (list != null)
					we = list.get(0);

				if (we != null) {
					List<WebElement> rows = null;
					rows = (we.findElements(By.xpath("//*[@class='k-grid-table']//tr")));
		       CucumberTestAutomationLogger.writeToLog("SeleniumKeywordslib","validateTableData() ", "rows - " + rows.size());
					if (rows != null) {
						int tempRowNo = 0;
						for (WebElement werowCell : rows) {
							String value1 = null;
							tempRowNo++;
				
					CucumberTestAutomationLogger.writeToLog("SeleniumKeywordslib","validateTableData()",  "X" + tempRowNo);
					value1 = werowCell.getText();
					CucumberTestAutomationLogger.writeToLog("SeleniumKeywordslib","validateTableData() ", "VALUE - "+ value1);
					if ((value1 != null) && (value1.contains(data))) 
					{
					     extentTestReport.log(LogStatus.PASS," Value  in RowName : " + " : is "+ value);
						 CucumberTestAutomationLogger.writeToLog("SeleniumKeywordslib","getCellData()", "'" + objName+ "'=>Get the value from column=:"+ data + "and RowNo=: " + row + " : is "+ value);
						 status= "true";
						 return status;
					}
					else
					{
						//extentTestReport.log(LogStatus.FAIL,"getCellData()", getVal + " is null");
						CucumberTestAutomationLogger.writeToLog("SeleniumKeywordslib","getCellData()", getVal + " is null");
						status= "false";
					}
					if(value1.contains("Program and Resource Management"))
					{
						break;
					}
				} 
			    return status;
			}
				}
						
			
				}catch (NoSuchElementException ns) {
				try
				{
				ns.printStackTrace();
				CucumberTestAutomationLogger.writeToLog("SeleniumKeywordslib", "getCellData()","Object not found exeption thrown");
				CucumberTestAutomationLogger.writeToLog("SeleniumKeywordslib", "getCellData()","'" + objName + "'object does not exist");
				return status;
				}
			 finally {
		   		 extentTestReport.addScreenCapture(propertyFileReader.getValue("REPORT_PATH")+"objName");
		    	 extentTestReport.log(LogStatus.FAIL, extentTestReport.addScreenCapture(ExtentReportManager.CaptureScreen(webDriver, propertyFileReader.getValue("SNAPSHOT_FOLDER")+"objName"))); 
		    	// extentReports.endTest(extentTestReport);
		    	// extentReports.flush();
		    	 //extentReports.close(); 
		    	
			}
				
			}
			
			return status;
		}*/
		
	/*	public String selectFrame(WebDriver webDriver, String objName,String selector,String selectorValue) {
			String status = "false";

			try {
               try {
					if (selectorValue.equalsIgnoreCase("name")) {
					 WebElement we = webDriver.findElement(By.name(objName));
						webDriver.switchTo().frame(we);
						status = "true";
						return status;
					} else if (selectorValue.equalsIgnoreCase("xpath")) {
						 WebElement we = webDriver.findElement(By.xpath(objName));
						try {
							if (we != null) {
								webDriver.switchTo().frame(we);
								return "true";
							}
						}

						catch (Exception e) {
							return "frame not found";
						}
					} else {
						List<WebElement> list = getElements(webDriver, objName,selector,selectorValue);
						if (list.size() > 0) {

							WebElement frameElem = list.get(0);
							webDriver.switchTo().frame(frameElem);
							status = "true";
							return status;

						} else {
							status = "Frame doesnt exists";
							return status;
						}
					}

				} catch (Exception e) {

					e.printStackTrace();
					webDriver.switchTo().defaultContent();
					String err[]=e.getMessage().split("\n");
					status =  "Exception " +err[0].replaceAll("'", "") + " Occurred";
					return status;
				}

			} catch (NoSuchElementException ns) {
				ns.printStackTrace();
				CucumberTestAutomationLogger.writeToLog("SeleniumKeywordslib", "selectFrame() ","Object not found exeption thrown");
				CucumberTestAutomationLogger.writeToLog("SeleniumKeywordslib", "selectFrame() ","'" + objName + "'object does not exist");
				status = "" + objName + "object does not exist";
				return status;
			} catch (Exception ex) {
				ex.printStackTrace();
				CucumberTestAutomationLogger.writeToLog("SeleniumKeywordslib", "selectFrame() ","Exception occurred");

				CucumberTestAutomationLogger.writeToLog("SeleniumKeywordslib", "selectFrame() ","Exception " + ex.getMessage() + " Occurred");
				String err[]=ex.getMessage().split("\n");
				status =  "Exception " +err[0].replaceAll("'", "") + " Occurred";
				return status;
			}
			return status;
		}	
		
		
		public String switchToDefaultContent(WebDriver webDriver) {
			String status="false";
			try {
				webDriver.switchTo().defaultContent();
				CucumberTestAutomationLogger.writeToLog("SeleniumKeywordslib","switchToDefaultContent() ","Switched to default content");
				return "true";
			}

			catch (Exception e) {
				e.printStackTrace();
				CucumberTestAutomationLogger.writeToLog("SeleniumKeywordslib","switchToDefaultContent() ","Exception exeption thrown in Html_OpenURL"+ e.getMessage());
				String err[]=e.getMessage().split("\n");
				status=  "Exception " +err[0].replaceAll("'", "") + " Occurred";
				return status;

			}

		}
		
		
		 * ##########################################################################
		 * #################### ' FUNCTION NAME - Html_waitForPageToLoad() '
		 * DESCRIPTION - This function will wait for page to Load
		 * 
		 * '###############################################################################################
		 

		public String waitForPageLoaded(WebDriver webDriver) {
			String status = "false";

			try {

				ExpectedCondition<Boolean> expectation = new ExpectedCondition<Boolean>() {
					public Boolean apply(WebDriver driver) {
						return ((JavascriptExecutor) driver).executeScript(
								"return document.readyState").equals("complete");
					}
				};

				WebDriverWait wait = new WebDriverWait(webDriver, 60);

				wait.until(expectation);

				status = "true";
				return status;

			} catch (NoSuchElementException ns) {
				ns.printStackTrace();
				CucumberTestAutomationLogger.writeToLog("SeleniumKeywordslib","waitForPageLoaded()","Object not found exeption thrown");
				status = "Object not found exception was thrown";
				return status;
			} catch (Exception ex) {
				ex.printStackTrace();
				CucumberTestAutomationLogger.writeToLog("SeleniumKeywordslib","waitForPageLoaded()", "Exception occurred");
				CucumberTestAutomationLogger.writeToLog("SeleniumKeywordslib","waitForPageLoaded()", "Exception '" + ex.getMessage()+ "' Occurred");
				String err[]=ex.getMessage().split("\n");
				status=  "Exception " +err[0].replaceAll("'", "") + " Occurred";
				return status;
			}

		}
		
		   * ############################################################################################# 
		   * ' FUNCTION NAME - windowActivate ' DESCRIPTION - This
		   * function will be used to switch to Parent Window ' INPUT PARAMETERS -
		   * '################################################################################################
		   

		  public boolean elementInVisible(WebDriver webDriver,String objName,String selector,String selectorvalue, ExtentReports extentReports,ExtentTest extentTestReport) {
		  	CucumberTestAutomationLogger.writeToLog("SeleniumKeywordslib", "elementDisplayed() ","elementDisplayed - ");
		  	boolean status = false;
		  	try {

		  		if (webDriver == null) {
		  			CucumberTestAutomationLogger.writeToLog("SeleniumKeywordslib","elementDisplayed() ", "Browser is null");
		  			return false;
		  		}
		  		List<WebElement> list = getElements(webDriver, objName, selector,selectorvalue);
				
		       if (list.size() > 0) {
				CucumberTestAutomationLogger.writeToLog("SeleniumKeywordslib","elementDisplayed()", list.size()+ " number of elements Found");

				  WebElement we = list.get(0);
		  		if(we.isDisplayed())
		  		{
		  				CucumberTestAutomationLogger.writeToLog("SeleniumKeywordslib", "elementDisplayed() ",we +"is displayed ");
						status = true;
						extentTestReport.log(LogStatus.FAIL,objName+ "  is displayed ");
						return status;
		  			
		  		}
		  		else
		  		{
		  			 CucumberTestAutomationLogger.writeToLog("SeleniumKeywordslib", "elementDisplayed() ",we +"is not displayed ");
						status = false;
						extentTestReport.log(LogStatus.PASS, " Element is not displayed ");
						return status;
		  		}
		  		

		  	} else if (list.size() == 1)
		  	{
		  		 WebElement element = list.get(0);
		  		if(element.isDisplayed()) 
			        {
		  				CucumberTestAutomationLogger.writeToLog("SeleniumKeywordslib", "elementDisplayed() ",element +"is displayed ");
						status = true;
						extentTestReport.log(LogStatus.FAIL, objName +" is displayed");
						return status;
		  			
			        }

			 else 
			 {
				CucumberTestAutomationLogger.writeToLog("SeleniumKeywordslib","elementDisplayed() ","element is  not displayed");
				status = false;
				extentTestReport.log(LogStatus.PASS, objName +" is not displayed");
				return status;
			 }
		  	}
		  	else
		  	{
		  		CucumberTestAutomationLogger.writeToLog("SeleniumKeywordslib","elementDisplayed() ","element is  not displayed");
				status = false;
				return status;
		  	}
		  	}catch (Exception exception) {
		  		 try
		  		 {
		  			    exception.printStackTrace();
		  				CucumberTestAutomationLogger.writeToLog("SeleniumKeywordslib","elementDisplayed() ", "Exeption thrown");
		  				CucumberTestAutomationLogger.writeToLog("SeleniumKeywordslib","elementDisplayed() ", "object doesnot exists");
		  				return false;
		  		 }
		  		 finally{
		  				extentTestReport.log(LogStatus.FAIL, "object doesnot exists");
		  		        extentTestReport.log(LogStatus.FAIL, extentTestReport.addScreenCapture(ExtentReportManager.CaptureScreen(webDriver, propertyFileReader.getValue("SNAPSHOT_FOLDER")))); 
		  		    	// extentReports.endTest(extentTestReport);
		  		    	// extentReports.flush();
		  		         //extentReports.close(); 
		  		        webDriver.close();
		  		         //webDriver.quit();
		  		         
		  			}

		  	}
		  }	
		  public LinkedList<String> getTableHeaders(WebDriver webDriver, String objName,String selector,String selectoValue,ExtentReports extentReports,ExtentTest extentTestReport) throws Exception {

			  LinkedList<String> list = new LinkedList<String>();
			//	 String status="";
				try
				{
					WebElement we = null;
					 	 List<WebElement> cols = null;	
						 List<WebElement> collist = getElements(webDriver, objName, selector,selectoValue);
						we = collist.get(0);
					   cols = (we.findElements(By.xpath(selectoValue)));
					  CucumberTestAutomationLogger.writeToLog("SeleniumKeywordslib","getTableHeaders() ", "rows - " + cols.size());
					  
					 
	                  for(WebElement wecolcells: cols)
						 {
	                	  list.add(wecolcells.getText());
						 }
	          
	          		return list;
					
				}

				 catch(Exception e)
				 {
				     e.printStackTrace();
				 }
						return list;
						}
		  public String verifyListOfDeliverables(WebDriver webDriver, String objName,String selector,String selectoValue,String listofDeliverablesinTeamTab,ExtentReports extentReports,ExtentTest extentTestReport) throws Exception {

				 HashMap<String, String> map = new HashMap<String,String>();
				 String status="";
				try
				{
					WebElement we = null;
					 	 List<WebElement> cols = null;	
						 List<WebElement> collist = getElements(webDriver, objName, selector,selectoValue);
						we = collist.get(0);
					   cols = (we.findElements(By.xpath(selectoValue)));
					   CucumberTestAutomationLogger.writeToLog("SeleniumKeywordslib","verifyListOfDeliverables() ", "rows - " + cols.size());
					  
					 
	                  for(WebElement wecolcells: cols)
						 {
	                	 map.put(wecolcells.getText(), wecolcells.getText());
						 }
	                 
	                  String[] delivearbls=listofDeliverablesinTeamTab.split(",");
	          		for(int i=0;i<delivearbls.length;i++)
	          		{
	          			  String eachdel = delivearbls[i];
	          			  
	          			  if(map.containsValue((eachdel)))
	          			  {
	          				CucumberTestAutomationLogger.writeToLog("SeleniumKeywordslib","verifyListOfDeliverables() ","element is  not displayed");
	        				status = "true";
	        				extentTestReport.log(LogStatus.PASS, objName +" contains" +" "+eachdel);
	        				
	          			  }
	          			  else
	          			  {
	          				CucumberTestAutomationLogger.writeToLog("SeleniumKeywordslib","verifyListOfDeliverables() ","element is  not displayed");
	        				status = "false";
	        				extentTestReport.log(LogStatus.FAIL, objName +" not contains" +" "+eachdel);
	        				
	          			  }
	          		}
	          		return status;
					
				}

				 catch(Exception e)
				 {
				     e.printStackTrace();
				 }
						return status;
						}
		  public String verifyoptOutDeliverablesinTeamTab(WebDriver webDriver, String objName,String selector,String selectoValue,String listofDeliverablesinTeamTab,ExtentReports extentReports,ExtentTest extentTestReport) throws Exception {

				 HashMap<String, String> map = new HashMap<String,String>();
				 String status="";
				try
				{
					WebElement we = null;
					 	 List<WebElement> cols = null;	
						 List<WebElement> collist = getElements(webDriver, objName, selector,selectoValue);
						we = collist.get(0);
					   cols = (we.findElements(By.xpath(selectoValue)));
					  CucumberTestAutomationLogger.writeToLog("SeleniumKeywordslib","verifyListOfDeliverables() ", "rows - " + cols.size());
					  
					 
	                  for(WebElement wecolcells: cols)
						 {
	                	  map.put(wecolcells.getText(), wecolcells.getText());
						 }
	                 
	                  String[] delivearbls=listofDeliverablesinTeamTab.split(",");
	          		for(int i=0;i<delivearbls.length;i++)
	          		{
	          			  String eachdel = delivearbls[i];
	          			  
	          			  if(!map.containsValue((eachdel)))
	          			  {
	          				CucumberTestAutomationLogger.writeToLog("SeleniumKeywordslib","verifyListOfDeliverables() ","element is  not displayed");
	        				status = "true";
	        				extentTestReport.log(LogStatus.PASS, objName +" not contains" +" "+eachdel);
	        				
	          			  }
	          			  else
	          			  {
	          				CucumberTestAutomationLogger.writeToLog("SeleniumKeywordslib","verifyListOfDeliverables() ","element is  not displayed");
	        				status = "false";
	        				extentTestReport.log(LogStatus.FAIL, objName +" contains" +" "+eachdel);
	        				
	          			  }
	          		}
	          		return status;
					
				}

				 catch(Exception e)
				 {
				     e.printStackTrace();
				 }
						return status;
						}
		  
		  
		  public String dragAndDrop(WebDriver webDriver, String objName,String selector,String sourceselectoValue,String desselectoValue,ExtentReports extentReports,ExtentTest extentTestReport) throws Exception {

				 String status="";
				try
				{
					
					  CucumberTestAutomationLogger.writeToLog("SeleniumKeywordslib","verifyListOfDeliverables() ", "");
					  WebElement element = webDriver.findElement(By.xpath("//*[@id='currentSales-0']")); 
                      WebElement element1 = webDriver.findElement(By.xpath("/html/body/ngb-modal-window/div/div/app-split-program/app-popup/div[2]/form/div[2]/div/div/div[3]/div[2]/ul"));
      			  Point coordinates1 = element.getLocation();
					  Point coordinates2 = element1.getLocation();
					   
					  Robot robot = new Robot();
					   
					  robot.mouseMove(coordinates1.getX(), coordinates1.getY());
					  robot.mousePress(InputEvent.BUTTON1_MASK);
					  robot.mouseMove(coordinates2.getX(), coordinates2.getY());
					  robot.mouseRelease(InputEvent.BUTTON1_DOWN_MASK);
					  Thread.sleep(2000);
          				CucumberTestAutomationLogger.writeToLog("SeleniumKeywordslib","verifyListOfDeliverables() ","element is  not displayed");
	        				status = "true";
	        				extentTestReport.log(LogStatus.PASS, objName +" drag and drop is successfull");
	        				

	          		}
	          	
				 catch(Exception e)
				 {
				     e.printStackTrace();
				 }
						return status;
						}
		  
		  
		  public String multipleElementVisible(WebDriver webDriver,String objName,String selector,String elements, ExtentReports extentReports,ExtentTest extentTestReport) {
			  	CucumberTestAutomationLogger.writeToLog("SeleniumKeywordslib", "elementDisplayed() ","elementDisplayed - ");
			  	String status = "";
			  	try {

			  		if (webDriver == null) {
			  			CucumberTestAutomationLogger.writeToLog("SeleniumKeywordslib","elementDisplayed() ", "Browser is null");
			  			return "false";
			  		}
			  		String[] elemns = elements.split(",");
			  		for(int i=0;i<elemns.length;i++)
			  		{
			  			//elemns = elements.split(",");
			  		String selectorvalue ="//*[normalize-space(text())="+ "'"+elemns[i].trim()+"'"+"]";
			  		List<WebElement> list = getElements(webDriver, objName, selector,selectorvalue);
					
			       if (list.size() > 0) {
					CucumberTestAutomationLogger.writeToLog("SeleniumKeywordslib","elementDisplayed()", list.size()+ " number of elements Found");

					  WebElement we = list.get(0);
			  		if(we.isDisplayed())
			  		{
			  				CucumberTestAutomationLogger.writeToLog("SeleniumKeywordslib", "elementDisplayed() ",we +"is displayed ");
							status ="true";
							extentTestReport.log(LogStatus.PASS,elemns[i]+ "   is Visible");
							
			  			
			  		}
			  		else
			  		{
			  			 CucumberTestAutomationLogger.writeToLog("SeleniumKeywordslib", "elementDisplayed() ",we +"is not displayed ");
							status = "false";
							extentTestReport.log(LogStatus.FAIL, elemns[i]+"   not Visible ");
							
			  		}
			  		

			  	}
			 
			 	}
			  		return status;
			 
			  	}catch (Exception exception) {
			  		 try
			  		 {
			  			    exception.printStackTrace();
			  				CucumberTestAutomationLogger.writeToLog("SeleniumKeywordslib","elementDisplayed() ", "Exeption thrown");
			  				CucumberTestAutomationLogger.writeToLog("SeleniumKeywordslib","elementDisplayed() ", "object doesnot exists");
			  				return "false";
			  		 }
			  		 finally{
			  				extentTestReport.log(LogStatus.FAIL, "object doesnot exists");
			  		        extentTestReport.log(LogStatus.FAIL, extentTestReport.addScreenCapture(ExtentReportManager.CaptureScreen(webDriver, propertyFileReader.getValue("SNAPSHOT_FOLDER")))); 
			  		    	// extentReports.endTest(extentTestReport);
			  		    	// extentReports.flush();
			  		         //extentReports.close(); 
			  		        webDriver.close();
			  		         //webDriver.quit();
			  		         
			  			}

			  	}
			  }	
		  public String multipleElementInVisible(WebDriver webDriver,String objName,String selector,String elements, ExtentReports extentReports,ExtentTest extentTestReport) {
			  	CucumberTestAutomationLogger.writeToLog("SeleniumKeywordslib", "elementDisplayed() ","elementDisplayed - ");
			  	String status = "";
			  	try {

			  		if (webDriver == null) {
			  			CucumberTestAutomationLogger.writeToLog("SeleniumKeywordslib","elementDisplayed() ", "Browser is null");
			  			return "false";
			  		}
			  		String[] elemns = elements.split(",");
			  		for(int i=0;i<elemns.length;i++)
			  		{
			  			//elemns = elements.split(",");
			  		String selectorvalue ="//*[text()="+ "'"+elemns[i]+"'"+"]";
			  		List<WebElement> list = getElements(webDriver, objName, selector,selectorvalue);
					
			       if (list.size() > 0) {
					CucumberTestAutomationLogger.writeToLog("SeleniumKeywordslib","elementDisplayed()", list.size()+ " number of elements Found");

					  WebElement we = list.get(0);
			  		if(!we.isDisplayed())
			  		{
			  				CucumberTestAutomationLogger.writeToLog("SeleniumKeywordslib", "elementDisplayed() ",we +"is displayed ");
							status ="true";
							extentTestReport.log(LogStatus.PASS,elemns[i]+ "   is not Visible");
							
			  			
			  		}
			  		else
			  		{
			  			 CucumberTestAutomationLogger.writeToLog("SeleniumKeywordslib", "elementDisplayed() ",we +"is not displayed ");
							status = "false";
							extentTestReport.log(LogStatus.FAIL, elemns[i]+"   ot Visible ");
							
			  		}
			  		

			  	}
			 
			 	}
			  		return status;
			 
			  	}catch (Exception exception) {
			  		 try
			  		 {
			  			    exception.printStackTrace();
			  				CucumberTestAutomationLogger.writeToLog("SeleniumKeywordslib","elementDisplayed() ", "Exeption thrown");
			  				CucumberTestAutomationLogger.writeToLog("SeleniumKeywordslib","elementDisplayed() ", "object doesnot exists");
			  				return "false";
			  		 }
			  		 finally{
			  				extentTestReport.log(LogStatus.FAIL, "object doesnot exists");
			  		        extentTestReport.log(LogStatus.FAIL, extentTestReport.addScreenCapture(ExtentReportManager.CaptureScreen(webDriver, propertyFileReader.getValue("SNAPSHOT_FOLDER")))); 
			  		    	// extentReports.endTest(extentTestReport);
			  		    	// extentReports.flush();
			  		         //extentReports.close(); 
			  		        webDriver.close();
			  		         //webDriver.quit();
			  		         
			  			}

			  	}
			  }	
		  */
		  /*
			 * ########################################################################################################
			 * ##################### ' FUNCTION NAME - getHolidaysFromCalendar() '
			 * DESCRIPTION - This function will retreive the List of public Holidays for Instrument
			 * . ' INPUT * PARAMETERS - Obj - Name of the Object,StartDate, EndDate and ListOfPublic Holidays
			 * '################################################################################################
			 */
			public String SinglePhaseprogramfunctionality(WebDriver webDriver, String objName,String selector,String selectoValue,String data,ExtentReports extentReports,ExtentTest extentTestReport) throws Exception {

				 HashMap<String, String> map = new HashMap<String,String>();
				 String status ="";
				 
				try
				{
					WebElement we = null;
					 	 List<WebElement> cols = null;	
						 List<WebElement> collist = getElements(webDriver, objName, selector,selectoValue);
						we = collist.get(0);
					   cols = (we.findElements(By.xpath(".//td[4]")));
					  CucumberTestAutomationLogger.writeToLog("SeleniumKeywordslib","getRowByContent() ", "rows - " + cols.size());
					  
					 
	                  for(WebElement wecolcells: cols)
						 {
	                	  map.put(wecolcells.getText(), wecolcells.getText());
						 }
	                 if(map!=null && map.containsValue("data"))
	                 {
	                	 CucumberTestAutomationLogger.writeToLog("SeleniumKeywordslib", "elementDisplayed() ",we +"is not displayed ");
							status = "false";
							extentTestReport.log(LogStatus.FAIL, data+"   is Visible in table");
	                 }
	                 else
	                 {
	                	 CucumberTestAutomationLogger.writeToLog("SeleniumKeywordslib", "elementDisplayed() ",we +"is not displayed ");
							status = "true";
							extentTestReport.log(LogStatus.PASS, data+"   is not Visible in table"); 
	                 }
	                  return status;
					
				}
				
					
		
		 catch(Exception e)
		 {
		     e.printStackTrace();
		 }
				return status;
				}
			
			public boolean uploadFile(WebDriver webDriver,String browserType,String URL,String objName) {
				webDriver.manage().timeouts().implicitlyWait(30, TimeUnit.SECONDS);
				Long w = (Long) ((JavascriptExecutor) webDriver)
						.executeScript("return window.screen.availWidth;");
				Long h = (Long) ((JavascriptExecutor) webDriver)
						.executeScript("return window.screen.availHeight;");

				webDriver.manage().window()
						.setSize(new Dimension(w.intValue(), h.intValue()));
				//webDriver.get(URL);
				//waitForPageLoaded(webDriver);
				
				String autoItExe = fileUploadExe(browserType,objName);
				boolean actualFlag = upload(autoItExe);
//				URL=webDriver.getCurrentUrl();
//				webDriver.get(URL);
				return actualFlag;
			}
			
			
			private String fileUploadExe(String browserType,String objName) {
				String exeName = "";
				
				
				CucumberTestAutomationLogger.writeToLog("getAuthenticateExe - browserType "+browserType+"..");
				
				PropertyFileReader cfg = new PropertyFileReader();
				String autoItPath = cfg.getValue("AUTOIT_FOLDER");
				objName="";
				if(objName !=null && !objName.isEmpty())
					autoItPath=autoItPath+objName+"\\";
						
				if (browserType.equalsIgnoreCase("Firefox")) {
					exeName = autoItPath + "AuthenticationFF.exe";
				} 
				else if (browserType.contains("iexplore") || browserType.equalsIgnoreCase("IE")) {
					exeName = autoItPath + "FileUploadIE.exe";
				} 
				else if (browserType.equalsIgnoreCase("chrome")) {
					exeName = autoItPath + "FileUploadChrome.exe";
				}
				else if (browserType.equalsIgnoreCase("opera")) {
					exeName = autoItPath + "AuthenticationOpera.exe";
				}
				else if (browserType.equalsIgnoreCase("Safari")) {
					exeName = autoItPath + "AuthenticationSafari.exe";
				}
				CucumberTestAutomationLogger.writeToLog("exeName - "+exeName);
				return exeName;
			}
			
			public Boolean upload(String autoItExeName) {
				CucumberTestAutomationLogger.writeToLog("KeyWordActions - autoItExeName - "+autoItExeName);
				Boolean success=false;
				try {
					Process p1= Runtime.getRuntime().exec(autoItExeName);	
				
					success = true;
					System.out.println("p1"+p1);
				}catch(Exception e){
					//throw new Exception(null, e.getMessage());
				}
				return success;
			}	
			
			



public void dragAndDropByJavaScriptExecutor(WebDriver webDriver,String fromele, String toele,ExtentReports extentReports,ExtentTest extentTestReport) {
	JavascriptExecutor js = (JavascriptExecutor) webDriver;
	WebElement from = webDriver.findElement(By.xpath(fromele));
	WebElement to = webDriver.findElement(By.xpath(toele));
	
    js.executeScript("function createEvent(typeOfEvent) {\n" + "var event =document.createEvent(\"CustomEvent\");\n"
            + "event.initCustomEvent(typeOfEvent,true, true, null);\n" + "event.dataTransfer = {\n" + "data: {},\n"
            + "setData: function (key, value) {\n" + "this.data[key] = value;\n" + "},\n"
            + "getData: function (key) {\n" + "return this.data[key];\n" + "}\n" + "};\n" + "return event;\n"
            + "}\n" + "\n" + "function dispatchEvent(element, event,transferData) {\n"
            + "if (transferData !== undefined) {\n" + "event.dataTransfer = transferData;\n" + "}\n"
            + "if (element.dispatchEvent) {\n" + "element.dispatchEvent(event);\n"
            + "} else if (element.fireEvent) {\n" + "element.fireEvent(\"on\" + event.type, event);\n" + "}\n"
            + "}\n" + "\n" + "function simulateHTML5DragAndDrop(element, destination) {\n"
            + "var dragStartEvent =createEvent('dragstart');\n" + "dispatchEvent(element, dragStartEvent);\n"
            + "var dropEvent = createEvent('drop');\n"
            + "dispatchEvent(destination, dropEvent,dragStartEvent.dataTransfer);\n"
            + "var dragEndEvent = createEvent('dragend');\n"
            + "dispatchEvent(element, dragEndEvent,dropEvent.dataTransfer);\n" + "}\n" + "\n"
            + "var source = arguments[0];\n" + "var destination = arguments[1];\n"
            + "simulateHTML5DragAndDrop(source,destination);", from, to);

}
/*
 * ##########################################################################
 * ################### ' FUNCTION NAME - selectCheckbox() ' DESCRIPTION -
 * This function will Select/Deselect the CHECK BOX . ' INPUT PARAMETERS -
 * Obj - Name of the Object
 * '################################################################################################
 */

/*public String selectMultilpleCheckboxesbyObj(WebDriver webDriver, String objName,ExtentReports extentReports,ExtentTest extentTestReport) {
	String status = "false";
	String selValue ="true";
	try {
		String[] paramvalues= selectorvalue.split(",");
		
		for(int i=0;i<paramvalues.length;i++)
		{
		selectorvalue = "//*[text()="+ "'"+paramvalues[i] +"'"+"]//preceding::label[1]";
		waitForExistence(webDriver, objName, selector,selectorvalue);
		List<WebElement> list = getElements(webDriver, objName, selector,selectorvalue);
		CucumberTestAutomationLogger.writeToLog("SeleniumKeywordslib", "selectCheckbox() ","web elements size - " + list.size());
		if (list.size() > 1) {
			CucumberTestAutomationLogger.writeToLog("SeleniumKeywordslib","selectCheckbox() ", "found more than one element");
			CucumberTestAutomationLogger.writeToLog("SeleniumKeywordslib","selectCheckbox() ", "clicking 1st element");
			//for (WebElement element : list) {
			WebElement element = list.get(0);
				if (!element.getAttribute("type").equals("hidden") && (element.getAttribute("type").equals("checkbox")))
				{
				if (element.isDisplayed() ) {
					if (selValue.equalsIgnoreCase("true")) {
						if (element.isSelected()) {
							extentTestReport.log(LogStatus.PASS, "click on "+paramvalues[i] +" Checkbox");
							CucumberTestAutomationLogger.writeToLog("SeleniumKeywordslib","selectCheckbox() ", "'" + paramvalues[i]+ "' => checkbox is already selected");
							status ="true";
							//return "true";
						} else {
							element.click();
							extentTestReport.log(LogStatus.PASS, "click on "+paramvalues[i] +" Checkbox");
							CucumberTestAutomationLogger.writeToLog("SeleniumKeywordslib","selectCheckbox() ", "'" + objName+ "' =>  checkbox is  checked");
							status ="true";
							//return "true";
						}
					} else {
						if (element.isSelected()) {
							extentTestReport.log(LogStatus.PASS, "click on "+paramvalues[i]+" Checkbox");
							element.click();
							CucumberTestAutomationLogger.writeToLog("SeleniumKeywordslib","selectCheckbox() ", "'" + objName+ "' => checkbox is unchecked");
							status ="true";
							//return "true";
						} else {
							extentTestReport.log(LogStatus.PASS, "click on "+paramvalues[i]+" Checkbox");
							CucumberTestAutomationLogger.writeToLog("SeleniumKeywordslib","selectCheckbox() ", "'" + objName+ "' =>  checkbox is already unchecked");
							status ="true";
							//return "true";
						}

					}
				} else {
					extentTestReport.log(LogStatus.FAIL, "click on "+paramvalues[i]+" Checkbox");
					CucumberTestAutomationLogger.writeToLog("SeleniumKeywordslib","selectCheckbox() ", "'" + objName+ "CheckBox was not visible");
					status = "CheckBox was not visible";
					//
				}
			}
				else
				{
					CucumberTestAutomationLogger.writeToLog("SeleniumKeywordslib","selectCheckbox() ", "'" + objName+ "is not checkbox");
					status = "CheckBox was not visible";
				}
			//}
		} 
		else if (list.size() == 1) {
			//WebElement element = dropList.get(0);
			//List<WebElement> list = getElements(webDriver, objName, selector,selectorvalue);
			WebElement element = list.get(0);
			if (element.isDisplayed()) {

				if (selValue.equalsIgnoreCase("true")) {
					if (element.isSelected()) {
						extentTestReport.log(LogStatus.PASS, "click on "+paramvalues[i]+" Checkbox");
						CucumberTestAutomationLogger.writeToLog("SeleniumKeywordslib","selectCheckbox() ", "'" + objName+ "' => Checkbox is already selected");
						status ="true";
					} else {
						element.click();
						extentTestReport.log(LogStatus.PASS, "click on "+paramvalues[i]+" Checkbox");
						CucumberTestAutomationLogger.writeToLog("SeleniumKeywordslib","selectCheckbox() ", "'" + objName+ "' => Checkbox is  checked");
						status ="true";
					}
				} else {
					if (element.isSelected()) {
						element.click();
						extentTestReport.log(LogStatus.PASS, "click on "+paramvalues[i]+" Checkbox");
						CucumberTestAutomationLogger.writeToLog("SeleniumKeywordslib","selectCheckbox() ", "'" + objName+ "' => Checkbox is unchecked");
						status ="true";
					} else {
						extentTestReport.log(LogStatus.PASS, "'+element+'" +"Checkbox is already selected");
						CucumberTestAutomationLogger.writeToLog("SeleniumKeywordslib","selectCheckbox() ", "'" + objName+ "' => Checkbox is already unchecked");
						status ="true";
					}

				}

			} else {
				extentTestReport.log(LogStatus.FAIL, "click on "+paramvalues[i]+" Checkbox");
				CucumberTestAutomationLogger.writeToLog("SeleniumKeywordslib","selectCheckbox() ", "'" + objName+ "CheckBox was not visible");
				status = "CheckBox was not visible";
				return status;
			}

		}
		
		//}
		}
		
		if(paramvalues.length==0)
		{
			extentTestReport.log(LogStatus.FAIL, "click on "+objName+" Checkbox");
			CucumberTestAutomationLogger.writeToLog("SeleniumKeywordslib","selectCheckbox() ", "'" + objName+ " CheckBox was not found");
			status = "CheckBox was not found";
		}
			
			return status;
		
	
	}catch (NoSuchElementException ns) {
        ns.printStackTrace();
        CucumberTestAutomationLogger.writeToLog("SeleniumKeywordslib", "selectCheckbox() ","Object not found exeption thrown");
        CucumberTestAutomationLogger.writeToLog("SeleniumKeywordslib", "selectCheckbox() ", "'" + objName + "'object does not exist");
        status = "false";
        extentTestReport.log(LogStatus.FAIL, "Unable to select " +objName+ "Checkbox");
        return status;
 } catch (Exception ex) {
 	try
 	{
        ex.printStackTrace();
        CucumberTestAutomationLogger.writeToLog("SeleniumKeywordslib", "selectCheckbox() ","Exception occurred");
        extentTestReport.log(LogStatus.FAIL,"select " +objName+ "Checkbox");
        String err[]=ex.getMessage().split("\n");
		status = "Exception " +err[0].replaceAll("'", "") + " Exception Occurred";
		return status;
 	}
 	finally
 	{
 		     extentTestReport.log(LogStatus.FAIL, "Unable to set " +objName+ "Checkbox");
	         extentTestReport.log(LogStatus.FAIL, extentTestReport.addScreenCapture(ExtentReportManager.CaptureScreen(webDriver, propertyFileReader.getValue("SNAPSHOT_FOLDER")))); 
	    	// extentReports.endTest(extentTestReport);
	    	// extentReports.flush();
	         //extentReports.close(); 
	        webDriver.close();
	         //webDriver.quit();
	         
 	}
       
 }
 
}
 * ############################################################################################# 
 *  * ' FUNCTION NAME - enterText ' DESCRIPTION - This
 * function will be used to Enter Text in a textbox ' INPUT PARAMETERS -
 * '################################################################################################
 	
public String clear(WebDriver webDriver,String objName,String selector,String selectorvalue,ExtentReports extentReports,ExtentTest extentTestReport){
		
		CucumberTestAutomationLogger.writeToLog("Input","input()"," method called" );
		String status="false";
	    try {
	    	
			 List<WebElement> listElements= getElements(webDriver, objName,selector,selectorvalue);
			WebElement txtField = listElements.get(0);
			 
		     if (txtField.isDisplayed()) {
		    	 txtField.click();
					txtField.clear();
					
						CucumberTestAutomationLogger.writeToLog("Input",	"input() ",	"after deleting the text");
					}
					
			
			
			
		} catch (Exception e) {
			try{
				e.printStackTrace();
				String err[]=e.getMessage().split("\n");
				CucumberTestAutomationLogger.writeToLog(objName+ " unable to enter value in textbox "+e.getMessage().split("\n"));
				status =  "Exception " +err[0].replaceAll("'", "") + " Occurred";
			}
			finally{
				extentTestReport.log(LogStatus.FAIL, "Unable to enter "+objName +" due to Invalid Xpath");
		        extentTestReport.log(LogStatus.FAIL, extentTestReport.addScreenCapture(ExtentReportManager.CaptureScreen(webDriver, propertyFileReader.getValue("SNAPSHOT_FOLDER")+objName+": " ))); 
		    	// extentReports.endTest(extentTestReport);
		    	// extentReports.flush();
		         //extentReports.close(); 
		        webDriver.close();
		         //webDriver.quit();
		         
			}
	
	}
	    return status;
}

public int getNoofAttributesSizeDR(WebDriver webDriver, String objName,ExtentReports extentReports,ExtentTest extentTestReport) {
	
	List<WebElement> noofele = new ArrayList<WebElement>();
	   String status="";
	  try{  
	    List<WebElement> list1 = getElements(webDriver, objName, selector,selectorvalue);
	   	CucumberTestAutomationLogger.writeToLog("SeleniumKeywordslib", "setProgramRoleForTeamMember() ","web elements size - " + list1.size());
			if (list1.size() > 1) {
				CucumberTestAutomationLogger.writeToLog("SeleniumKeywordslib","setProgramRoleForTeamMember() ", "found more than one element"+status);
				List<WebElement> list = getElements(webDriver, objName, selector,selectorvalue);
					for (WebElement werowCell : list) {
						//String value1 = null;
						
				//value1 = werowCell.getText();
				
				if((!werowCell.getText().equalsIgnoreCase("")) && (werowCell.getText().equalsIgnoreCase("List with Summary")))
				{
					break;
				}
				else
				{
					noofele.add(werowCell);
				}
				
				
				//CATPLMNPILogger.writeToLog("SeleniumKeywordslib","setProgramRoleForTeamMember() ", "VALUE - "+ value1);
				
				 
				 }
					return noofele.size();
	      }
			

         }catch (NoSuchElementException ns) {
        ns.printStackTrace();
        CucumberTestAutomationLogger.writeToLog("SeleniumKeywordslib", "setProgramRoleForTeamMember() ","Object not found exeption thrown");
        CucumberTestAutomationLogger.writeToLog("SeleniumKeywordslib", "setProgramRoleForTeamMember() ", "'" + objName + "'object does not exist");
        status = "false";
        extentTestReport.log(LogStatus.FAIL, "Unable to click on " +objName+ "Calender");
        return noofele.size();
 } catch (Exception ex) {
 	try
 	{

 		ex.printStackTrace();
        CucumberTestAutomationLogger.writeToLog("SeleniumKeywordslib", "setProgramRoleForTeamMember() ","Exception occurred");
        extentTestReport.log(LogStatus.FAIL,"select " +objName+ "option");
        String err[]=ex.getMessage().split("\n");
		status = "Exception " +err[0].replaceAll("'", "") + " Exception Occurred";
		return noofele.size();
 	}
 	finally
 	{
 		     extentTestReport.log(LogStatus.FAIL, "Unable to click on " +objName+ "Calender");
	         extentTestReport.log(LogStatus.FAIL, extentTestReport.addScreenCapture(ExtentReportManager.CaptureScreen(webDriver, propertyFileReader.getValue("SNAPSHOT_FOLDER")))); 
	    	// extentReports.endTest(extentTestReport);
	    	// extentReports.flush();
	         //extentReports.close(); 
	        webDriver.close();
	         //webDriver.quit();
	         
 	}
 }
		return noofele.size();
}*/
/*public boolean verifyAttributesSortedorNot(WebDriver webDriver, String objName,ExtentReports extentReports,ExtentTest extentTestReport) {
	
	List<WebElement> noofele = new ArrayList<WebElement>();
	 boolean isSorted=true;
	   String status="";
	  try{  
	    List<WebElement> list1 = getElements(webDriver, objName, selector,selectorvalue);
	   	CucumberTestAutomationLogger.writeToLog("SeleniumKeywordslib", "setProgramRoleForTeamMember() ","web elements size - " + list1.size()+status);
			if (list1.size() > 1) {
				CucumberTestAutomationLogger.writeToLog("SeleniumKeywordslib","setProgramRoleForTeamMember() ", "found more than one element");
				List<WebElement> list = getElements(webDriver, objName, selector,selectorvalue);
					for (WebElement werowCell : list) {
						//String value1 = null;
						
			//	String value1 = werowCell.getText();
				if((!werowCell.getText().equalsIgnoreCase("")) && (werowCell.getText().contains("CBL_Production ")))
				{
					//noofele.add(werowCell);
					break;
				}
				else
				{
					noofele.add(werowCell);
				}
				 
				 }
					for(int i=1;i < noofele.size();i++){
						 if(noofele.get(i-1).getText().compareTo(noofele.get(i).getText()) > 0){
			                isSorted= false;
			                break;
			            }
			            
			        }
					  return isSorted;
	      }
			

         }catch (NoSuchElementException ns) {
        ns.printStackTrace();
        CucumberTestAutomationLogger.writeToLog("SeleniumKeywordslib", "setProgramRoleForTeamMember() ","Object not found exeption thrown");
        CucumberTestAutomationLogger.writeToLog("SeleniumKeywordslib", "setProgramRoleForTeamMember() ", "'" + objName + "'object does not exist");
        status = "false";
        extentTestReport.log(LogStatus.FAIL, "Unable to click on " +objName+ "Calender");
        return isSorted;
 } catch (Exception ex) {
 	try
 	{

 		ex.printStackTrace();
        CucumberTestAutomationLogger.writeToLog("SeleniumKeywordslib", "setProgramRoleForTeamMember() ","Exception occurred");
        extentTestReport.log(LogStatus.FAIL,"select " +objName+ "option");
        String err[]=ex.getMessage().split("\n");
		status = "Exception " +err[0].replaceAll("'", "") + " Exception Occurred";
		  return isSorted;
 	}
 	finally
 	{
 		     extentTestReport.log(LogStatus.FAIL, "Unable to click on " +objName+ "Calender");
	         extentTestReport.log(LogStatus.FAIL, extentTestReport.addScreenCapture(ExtentReportManager.CaptureScreen(webDriver, propertyFileReader.getValue("SNAPSHOT_FOLDER")))); 
	    	// extentReports.endTest(extentTestReport);
	    	// extentReports.flush();
	         //extentReports.close(); 
	        webDriver.close();
	         //webDriver.quit();
	         
 	}
 }
       return isSorted;
}*/
/* * ############################################################################################# 
 *  * ' FUNCTION NAME - enterText ' DESCRIPTION - This
 * function will be used to Enter Text in a textbox ' INPUT PARAMETERS -
 * '################################################################################################
 */	
public String moveCursorToLeft(WebDriver webDriver,String objName,String selector,String selectorvalue,ExtentReports extentReports,ExtentTest extentTestReport){
		
		CucumberTestAutomationLogger.writeToLog("Input","input()"," method called" );
		String status="false";
	    try {
	    	
	    	  
			 List<WebElement> listElements= getElements(webDriver, objName,selector,selectorvalue);
			WebElement slider = listElements.get(0);
			 
			int width=slider.getSize().getWidth();
		    Actions move = new Actions(webDriver);
		    org.openqa.selenium.interactions.Action action  = move.dragAndDropBy(slider, ((width*25)/100), 0).build();
		    action.perform();
		   

			
		} catch (Exception e) {
			try{
				e.printStackTrace();
				String err[]=e.getMessage().split("\n");
				CucumberTestAutomationLogger.writeToLog(objName+ " unable to enter value in textbox "+e.getMessage().split("\n"));
				status =  "Exception " +err[0].replaceAll("'", "") + " Occurred";
			}
			finally{
				extentTestReport.log(LogStatus.FAIL, "Unable to enter "+objName +" due to Invalid Xpath");
		        extentTestReport.log(LogStatus.FAIL, extentTestReport.addScreenCapture(ExtentReportManager.CaptureScreen(webDriver, propertyFileReader.getValue("SNAPSHOT_FOLDER")+objName+": " ))); 
		    	// extentReports.endTest(extentTestReport);
		    	// extentReports.flush();
		         //extentReports.close(); 
		        webDriver.close();
		         //webDriver.quit();
		         
			}
	
	}
	    return status;
}
/*
 * ############################################################################################# 
 * ' FUNCTION NAME - windowActivate ' DESCRIPTION - This
 * function will be used to switch to Parent Window ' INPUT PARAMETERS -
 * '################################################################################################
 */

public boolean rightClickonElement(WebDriver webDriver,String objName,String selector,String selectorvalue, ExtentReports extentReports,ExtentTest extentTestReport) {
	CucumberTestAutomationLogger.writeToLog("SeleniumKeywordslib", "elementDisplayed() ","elementDisplayed - ");
	boolean status = false;
	Actions act = new Actions(webDriver);
	try {

		if (webDriver == null) {
			CucumberTestAutomationLogger.writeToLog("SeleniumKeywordslib","elementDisplayed() ", "Browser is null");
			return false;
		}
		List<WebElement> list = getElements(webDriver, objName, selector,selectorvalue);
		
     if (list.size() > 0) {
		CucumberTestAutomationLogger.writeToLog("SeleniumKeywordslib","elementDisplayed()", list.size()+ " number of elements Found");

		  WebElement we = list.get(0);
		if(we.isDisplayed())
		{ 
			    act.moveToElement(we).contextClick().build().perform();
				CucumberTestAutomationLogger.writeToLog("SeleniumKeywordslib", "elementDisplayed() ",we +"is displayed ");
				status = true;
				extentTestReport.log(LogStatus.PASS, objName +" Visible");
				return status;
			
		}
		else
		{
			 CucumberTestAutomationLogger.writeToLog("SeleniumKeywordslib", "elementDisplayed() ",we +"is not displayed ");
				status = false;
				extentTestReport.log(LogStatus.FAIL, " Element not Visible ");
				return status;
		}
		

	} else if (list.size() == 1)
	{
		 WebElement element = list.get(0);
		if(element.isDisplayed()) 
	        {
			  act.moveToElement(element).contextClick().build().perform();
				CucumberTestAutomationLogger.writeToLog("SeleniumKeywordslib", "elementDisplayed() ",element +"is displayed ");
				status = true;
				extentTestReport.log(LogStatus.FAIL, " Warning in Application");
				return status;
			
	        }

	 else 
	 {
		CucumberTestAutomationLogger.writeToLog("SeleniumKeywordslib","elementDisplayed() ","element is  not displayed");
		status = false;
		return status;
	 }
	}
	else
	{
		CucumberTestAutomationLogger.writeToLog("SeleniumKeywordslib","elementDisplayed() ","element is  not displayed");
		status = false;
		return status;
	}
	}catch (Exception exception) {
		 try
		 {
			    exception.printStackTrace();
				CucumberTestAutomationLogger.writeToLog("SeleniumKeywordslib","elementDisplayed() ", "Exeption thrown");
				CucumberTestAutomationLogger.writeToLog("SeleniumKeywordslib","elementDisplayed() ", "object doesnot exists");
				return false;
		 }
		 finally{
				extentTestReport.log(LogStatus.FAIL, "object doesnot exists");
		        extentTestReport.log(LogStatus.FAIL, extentTestReport.addScreenCapture(ExtentReportManager.CaptureScreen(webDriver, propertyFileReader.getValue("SNAPSHOT_FOLDER")))); 
		    	// extentReports.endTest(extentTestReport);
		    	// extentReports.flush();
		         //extentReports.close(); 
		        webDriver.close();
		         //webDriver.quit();
		         
			}

	}
}	
public String DoubleClick(WebDriver webDriver, String objName,String selector,String selectorvalue,ExtentReports extentReports,ExtentTest extentTestReport) {
	String status = "false";
	try {
		List<WebElement> list = getElements(webDriver, objName, selector,selectorvalue);
		CucumberTestAutomationLogger.writeToLog("SeleniumKeywordslib", "DoubleClick()",
				"web elements size - " + list.size());
		Actions act=new Actions(webDriver);
		if (list.size() > 1) {
			CucumberTestAutomationLogger.writeToLog("SeleniumKeywordslib",
					"DoubleClick()", "found more than one element");
			CucumberTestAutomationLogger.writeToLog("SeleniumKeywordslib",
					"DoubleClick()", "clicking 1st element");
			
			for (WebElement btnField : list) {
				if (btnField.isDisplayed()) {
					act.doubleClick(btnField).build().perform();
					CucumberTestAutomationLogger.writeToLog("SeleniumKeywordslib","DoubleClick()", "after click");
					status = "true";
					CucumberTestAutomationLogger.writeToLog("SeleniumKeywordslib","ButtonClick()","'"+ objName+ "' => BUTTON was successfully clicked");
					return status;
				}

				else {
					CucumberTestAutomationLogger.writeToLog("SeleniumKeywordslib","DoubleClick()", "'" + objName+ "Button was not visible");
					act.doubleClick(btnField).build().perform();
					status = "true";
					return status;
				}
			}

		} 
		
			else if (list.size() == 1) {
		
			WebElement btnField = list.get(0);
				if (btnField.isDisplayed() == false) {
				try {
					
					act.doubleClick(btnField).build().perform();
				
					status = "true";
					return status;
				} catch (Exception e) {
					// TODO Auto-generated catch block
					e.printStackTrace();
					String err[]=e.getMessage().split("\n");
					status=  "Exception " +err[0].replaceAll("'", "") + " Occurred";
				}
			} else {
				try {
					act.doubleClick(btnField).build().perform();
				
					status = "true";
					return status;
				} catch (Exception e) {
					e.printStackTrace();
				}
			}

			

		}
			else{
				CucumberTestAutomationLogger.writeToLog("SeleniumKeywordslib", "DoubleClick()","'" + objName + "' =>does not exist!");
				//objectNameList.add(objName);
				status=objName + "does not exist";
				return status;
			}

	}
	 catch (Exception ex) {
	 try
	 {
		    ex.printStackTrace();
			CucumberTestAutomationLogger.writeToLog("SeleniumKeywordslib","select Window() ", "Exeption thrown");
			CucumberTestAutomationLogger.writeToLog("SeleniumKeywordslib","select Window() ", "'" + "'object doesnot exists");
			String err[]=ex.getMessage().split("\n");
			 status = "Exception " +err[0].replaceAll("'", "") + " Exception Occurred";
			return status;
	 }
	 finally{
			extentTestReport.log(LogStatus.FAIL, "Unable to switch to window ");
	        extentTestReport.log(LogStatus.FAIL, extentTestReport.addScreenCapture(ExtentReportManager.CaptureScreen(webDriver, propertyFileReader.getValue("SNAPSHOT_FOLDER")))); 
	    	// extentReports.endTest(extentTestReport);
	    	// extentReports.flush();
	         //extentReports.close(); 
	        webDriver.close();
	         //webDriver.quit();
	         
		}

}
	return status;
}

}
