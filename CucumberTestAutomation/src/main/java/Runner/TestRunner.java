package Runner;

import org.junit.runner.RunWith;
import org.testng.annotations.DataProvider;

import cucumber.api.CucumberOptions;
import cucumber.api.junit.Cucumber;
import io.cucumber.testng.AbstractTestNGCucumberTests;
 
@RunWith(Cucumber.class)
@CucumberOptions(
features = "src/main/java/Features/login.feature",
glue={"StepDefinitions"},
format = {"pretty","html:test-output","json:json_output/cucumber.json","junit:junit_xml/cucumber.xml"},
plugin = { "com.cucumber.listener.ExtentCucumberFormatter:target/cucumber-reports/report.html"},
monochrome = true,
strict = false,
dryRun = false
//tags= {"~@smoketest", "~@RegressionTest","~@E2ETest","@cucumberHook"}

)
public class TestRunner {
	

}

//runner with testng
//@CucumberOptions(features = {"src/main/java/Features/login.feature"} , plugin = {"json:target/cucumber.json","html:target/site/cucumber-pretty"},
//glue = "StepDefinitions")
//public class TestRunner extends AbstractTestNGCucumberTests {
//
//
//@Override
//@DataProvider
////@DataProvider (parallel = true) -- For parallel execution support (which is not going to work for our code)
//public Object[][] scenarios() {
//return super.scenarios();
//}
//
//}