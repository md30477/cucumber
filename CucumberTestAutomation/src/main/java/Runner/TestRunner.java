package Runner;

import org.junit.runner.RunWith;
import cucumber.api.CucumberOptions;
import cucumber.api.junit.Cucumber;
 
@RunWith(Cucumber.class)
@CucumberOptions(
 features = "src/main/java/Features/login.feature",
 glue={"StepDefinitions"},
 format = {"pretty","html:test-output","json:json_output/cucumber.json","junit:junit_xml/cucumber.xml"},
 monochrome = true,
 strict = true,
 dryRun = false
//tags= {"~@smoketest", "~@RegressionTest","~@E2ETest","@cucumberHook"}
 
 )
 public class TestRunner {
	

}

/*
1.ignore Scenarios :
tags= {"~@smoketest", "~@RegressionTest","~@E2ETest"} -- ignore the scenarios under ~tags

2.tags= {"@smoketest", "@RegressionTest"} -- execute scenarios which has combinations of these 2 tags

3.tags= {"@smoketest, @RegressionTest"} -- execute scenarios wunder both the tags one by one

*/