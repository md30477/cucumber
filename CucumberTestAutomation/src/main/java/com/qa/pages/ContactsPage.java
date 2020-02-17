package com.qa.pages;

import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.PageFactory;

import com.qa.Base.TestBase;


public class ContactsPage extends TestBase{
	
//	PropertyFileReader prop;
	
	
	@FindBy(xpath="//*[text()='Contacts']")
	WebElement contacts;
	
	@FindBy(xpath="//*[text()='New']")
	WebElement newBtn;

	@FindBy(name="first_name")
	WebElement frstName;
	
	@FindBy(name="last_name")
	WebElement lastname;
	
	@FindBy(name="description")
	WebElement description;

	@FindBy(xpath="//*[text()='Save']")
	WebElement saveBtn;
	
	public ContactsPage()
	{
		System.out.println("Driver in contacts"+driver) ;
		PageFactory.initElements(driver, this);
		
	}

	
	public void clickonContacts()
	{
		
	//	driver.switchTo().frame("downloadFrame");
	//	contacts.click();
		try {
			Thread.sleep(3000);
		} catch (InterruptedException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	}
	
	public void createNewContacts(String frstname,String lastnme,String position)
	{
		//contacts.click();
		newBtn.click();
		try {
			Thread.sleep(3000);
		} catch (InterruptedException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		
		frstName.sendKeys(frstname);
		lastname.sendKeys(lastnme);
		saveBtn.click();
	}
}
