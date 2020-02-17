package StepDefinitions;

import java.util.List;
import java.util.Map;

import org.junit.Assert;
import org.openqa.selenium.By;

import com.qa.Base.TestBase;

import cucumber.api.DataTable;
import cucumber.api.java.en.And;
import cucumber.api.java.en.Given;
import cucumber.api.java.en.Then;
import cucumber.api.java.en.When;

public class DealStepDefinition extends TestBase{

	@Given("^user is on login page$")
	public void setup_login_page()
	{
		intialization();
		
		
		
	}
	@When("^launch the url$")
	public void launch_the_url()
	{
		 driver.get("https://ui.freecrm.com/");
	}
//	@Then("^enters usernameand password$")
//	public void enters_usernameand_password()
//	{
//		driver.findElement(By.name("email")).sendKeys("30477md");
//		driver.findElement(By.name("password")).sendKeys("pranuthi2020$");
//	}
	@Then("^enters username and password$")
	public void enter_usernameand_password(DataTable credentials)
	{
		List<List<String>> logincredentials= credentials.raw();
		driver.findElement(By.name("email")).sendKeys(logincredentials.get(0).get(0));
		driver.findElement(By.name("password")).sendKeys(logincredentials.get(0).get(1));
	}
	
	@And("^click on login$")
	public void click_on_login() throws InterruptedException
	{
		driver.findElement(By.xpath("//*[text()='Login']")).click();
		Thread.sleep(3000);
	}
	@And("^verify title in homepage$")
	public void verify_home_titile()
	{
		String title  = driver.getTitle();
		System.out.println("title"+title);
		Assert.assertEquals("Cogmento CRM", title);
	}
	
	@Then("^click on create Deals$")
	public void click_on_create_Deals() throws InterruptedException
	{
		driver.findElement(By.xpath("//span[text()='Deals']")).click();
		driver.findElement(By.xpath("//*[text()='New']")).click();
		Thread.sleep(1000);
	}
	
	@Then("^enters mandatory details$")
	
	public void enters_mandatory_details(DataTable data) throws InterruptedException
	{
		List<List<String>> dealsValues= data.raw();
		for(int i=1;i<dealsValues.size();i++)
		{
		driver.findElement(By.name("title")).sendKeys(dealsValues.get(i).get(0));
		driver.findElement(By.name("probability")).sendKeys(dealsValues.get(i).get(1));
		driver.findElement(By.name("commission")).sendKeys(dealsValues.get(i).get(2));
		driver.findElement(By.xpath("//*[text()='Save']")).click();
		Thread.sleep(1000);
		click_on_create_Deals();
		}
	/*	for(Map<String, String> map : data.asMaps(String.class, String.class))
		{
			driver.findElement(By.name("title")).sendKeys(map.get("title"));
			driver.findElement(By.name("probability")).sendKeys(map.get("probability"));
			driver.findElement(By.name("commission")).sendKeys(map.get("commission"));
			driver.findElement(By.xpath("//*[text()='Save']")).click();
			Thread.sleep(1000);
			click_on_create_Deals();
		}*/
	}
	@Then("^close browser$")
	public void tearDown()
	{
		driver.quit();
	}
}

