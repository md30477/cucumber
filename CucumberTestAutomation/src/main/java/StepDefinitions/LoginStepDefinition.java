package StepDefinitions;

import org.junit.Assert;
import org.openqa.selenium.By;

import com.aventstack.extentreports.GherkinKeyword;
import com.qa.Base.BaseUtil;
import com.qa.pages.ContactsPage;
import com.qa.pages.LoginPage;

import cucumber.api.java.en.And;
import cucumber.api.java.en.Given;
import cucumber.api.java.en.Then;
import cucumber.api.java.en.When;


public class LoginStepDefinition  extends BaseUtil{
LoginPage loginpage ;
ContactsPage contactsPage;


@SuppressWarnings("unused")
private static boolean isReportRunning;


	@Given("^user is already on login page$")
	public void user_is_already_on_login_page() throws ClassNotFoundException
	{
		scenarioDef.createNode(new GherkinKeyword("Given"), "user is already on login page");

		System.out.println("user_is_already_on_login_page");
		
	}
	@When("^launching the url$")
	public void launching_the_url() throws Throwable
	{
		scenarioDef.createNode(new GherkinKeyword("When"), "launching the url");

		System.out.println("launching the url");
		 driver.get("https://ui.freecrm.com/");
	}
//	@Then("^enters usernameand password$")
//	public void enters_usernameand_password()
//	{
//		driver.findElement(By.name("email")).sendKeys("30477md");
//		driver.findElement(By.name("password")).sendKeys("pranuthi2020$");
//	}
	@Then("^enters \"([^\"]*)\" and \"([^\"]*)\"$")
	public void enters_usernameand_password(String username,String password) throws Throwable
	{
		scenarioDef.createNode(new GherkinKeyword("Then"), "enters_usernameand_password");
		System.out.println("++++++++++++++++"+username+"and" +password);
		 loginpage = new LoginPage();
		
		//driver.findElement(By.name("email")).sendKeys(username);
		//driver.findElement(By.name("password")).sendKeys(password);
		loginpage.login(username, password);
	}
	
	@And("^click on login button$")
	public void click_on_login_button() throws InterruptedException, Throwable
	{
		scenarioDef.createNode(new GherkinKeyword("And"), "click on login button");
		
		//driver.findElement(By.xpath("//*[text()='Login']")).click();
		Thread.sleep(3000);
	}
	@And("^verify home page titile$")
	public void verify_home_page_titile()
	{
		String title  = driver.getTitle();
		System.out.println("title"+title);
		Assert.assertEquals("Cogmento CRM", title);
	}
	
	@Then("^click on new contact$")
	public void click_on_new_contact() throws InterruptedException, Throwable
	{
		scenarioDef.createNode(new GherkinKeyword("Then"), "click on new contact");
		
		/*driver.findElement(By.xpath("//span[text()='Contacts']")).click();
		driver.findElement(By.xpath("//*[text()='New']")).click();
		Thread.sleep(1000);*/
		
		   driver.findElement(By.xpath("//span[text()='Contacts']")).click();
	}
	
	@Then("^enters \"([^\"]*)\" and \"([^\"]*)\" and \"([^\"]*)\"$")
	
	public void enters_frstname_lastname_position(String firstname,String lastname,String position) throws InterruptedException, Throwable
	{
		scenarioDef.createNode(new GherkinKeyword("Then"), "enters_frstname_lastname_position");
		
		   contactsPage= new ContactsPage();
		contactsPage.createNewContacts(firstname, lastname,position);
		/*driver.findElement(By.name("first_name")).sendKeys(firstname);
		driver.findElement(By.name("last_name")).sendKeys(lastname);
		driver.findElement(By.name("description")).sendKeys(position);
		driver.findElement(By.xpath("//*[text()='Save']")).click();*/
		Thread.sleep(1000);
	}
	@Then("^close the browser$")
	public void close_the_browser() throws Throwable
	{
		//extentReports.endTest(extentTestReport);
		//    extentReports.flush(); 
			//extentReports.close();
		scenarioDef.createNode(new GherkinKeyword("Then"), "close the browser");
		
		    driver.quit();
	}
	
}
