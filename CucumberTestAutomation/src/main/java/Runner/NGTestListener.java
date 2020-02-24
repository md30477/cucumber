package Runner;

import com.aventstack.extentreports.gherkin.model.Feature;

import StepDefinitions.ExtentReportUtil;

import org.testng.ITestContext;
import org.testng.ITestListener;
import org.testng.ITestResult;

import java.io.IOException;

import static com.qa.Base.BaseUtil.features;



public class NGTestListener implements ITestListener {

    ExtentReportUtil extentReportUtil = new ExtentReportUtil();

   
    public void onTestStart(ITestResult iTestResult) {
        System.out.println("On test start");
    }

   
    public void onTestSuccess(ITestResult iTestResult) {

        System.out.println("On test Sucess");
    }

   
    public void onTestFailure(ITestResult iTestResult) {
        System.out.println("On test failure");

        try{
            extentReportUtil.ExtentReportScreenshot();
        }catch (IOException e){
            e.printStackTrace();
        }
    }

   
    public void onTestSkipped(ITestResult iTestResult) {
        System.out.println("On test skipped");
    }


    public void onTestFailedButWithinSuccessPercentage(ITestResult iTestResult) {
        System.out.println("On test percentage");
    }

 
    public void onStart(ITestContext iTestContext) {
        System.out.println("On start");

        extentReportUtil.Instance();

        //ToDo: Feature - Hard coding the feature name
        features = extentReportUtil.extent.createTest(Feature.class, "Login Feature");

    }


    public void onFinish(ITestContext iTestContext) {
        System.out.println("On finish");
        extentReportUtil.FlushReport();
    }
}
