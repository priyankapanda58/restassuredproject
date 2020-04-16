package com.restassured.utilities;

import org.testng.ITestContext;
import org.testng.ITestResult;
import org.testng.TestListenerAdapter;

import com.aventstack.extentreports.ExtentReports;
import com.aventstack.extentreports.ExtentTest;
import com.aventstack.extentreports.Status;
import com.aventstack.extentreports.reporter.ExtentHtmlReporter;
import com.aventstack.extentreports.reporter.configuration.Theme;

public class Listeners extends TestListenerAdapter{
	
        @SuppressWarnings("deprecation")
		public ExtentHtmlReporter htmlReporter;
        public ExtentReports extent;
        public ExtentTest test;
        
        public void onStart(ITestContext testContext)
        {
        	htmlReporter = new ExtentHtmlReporter(System.getProperty("user.dir")+"/reports/myReport.html");
        	htmlReporter.config().setDocumentTitle("Automation Report");
        	htmlReporter.config().setReportName("Rest assured report");
        	htmlReporter.config().setTheme(Theme.DARK);
        	
        	extent = new ExtentReports();
        	extent.attachReporter(htmlReporter);
        	extent.setSystemInfo("Hostname", "localhost");
        	extent.setSystemInfo("Environment", "QA");
        	extent.setSystemInfo("User", "priyanka");
        	
        }
        
        public void onTestSuccess(ITestResult result)
        {
        	test = extent.createTest(result.getName());
        	test.log(Status.PASS, "TestCase Passed is"+result.getName());
        }
        
        public void onTestFailure(ITestResult result)
        {
        	test = extent.createTest(result.getName());
        	test.log(Status.FAIL, "TestCase Failed is-->"+result.getTestClass().getName()+result.getName());
        	test.log(Status.FAIL, "TestCase Failed is-->"+result.getThrowable());
        }
        
        public void onTestSkipped(ITestResult result)
        {
        	test = extent.createTest(result.getName());
        	test.log(Status.SKIP, "TestCase Skipped is"+result.getName());
        	
        }
        
        public void onFinish(ITestContext testContext)
        {
        	extent.flush();
        }
}
