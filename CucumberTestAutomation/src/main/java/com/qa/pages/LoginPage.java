package com.qa.pages;

import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.PageFactory;
import com.qa.Base.TestBase;
import com.qa.utils.SeleniumKeywordslib;
import com.relevantcodes.extentreports.ExtentReports;
import com.relevantcodes.extentreports.ExtentTest;

import StepDefinitions.ExtentReportManager;

public class LoginPage extends TestBase{
  SeleniumKeywordslib lib = new SeleniumKeywordslib();
	@FindBy(name="email")
	WebElement email;
	
	@FindBy(name="password")
	WebElement password;
	
	
	
	@FindBy(xpath="//*[text()='Login']")
	WebElement submitbtn;
	static ExtentReports extentReports  =ExtentReportManager.Instance();;
	static ExtentTest extentTestReport=  extentReports.startTest("", "");;
	
	public LoginPage()
	{
		System.out.println("Test");
		PageFactory.initElements(driver, this);
	}
	
	public String verifyTitle()
	{
		return  driver.getTitle();
	}
	public HomePage login(String username,String pwd)
	{
	/*	email.sendKeys(username);
		password.sendKeys(pwd);
		submitbtn.click();*/
		lib.enterText(driver, email, username,extentReports,extentTestReport);
		lib.enterText(driver, password, pwd,extentReports,extentTestReport);
		
		lib.click(driver, submitbtn,extentReports,extentTestReport);
		return new HomePage();
		
	}

}
