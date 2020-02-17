package com.qa.utils;

import com.qa.Base.TestBase;

import cucumber.api.java.After;
import cucumber.api.java.Before;

public class Hooks extends TestBase{
	
	@Before
	public void setUp()
	{
		
		System.out.println("in before method");
		intialization();
		 driver.get("https://ui.freecrm.com/");
	}
	
	
	@After
	public void tearDown()
	{
		driver.quit();
		System.out.println("in after method");
	}
}
