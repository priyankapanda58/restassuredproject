package com.restassured.testcases;

import org.testng.annotations.AfterClass;
import org.testng.annotations.BeforeClass;
import org.testng.annotations.Test;

import com.restassured.base.TestBase;

import io.restassured.RestAssured;
import io.restassured.http.Method;
import io.restassured.response.Response;
import io.restassured.specification.RequestSpecification;
import junit.framework.Assert;

public class TC002_GET_SingleEmployee extends TestBase {
	
	RequestSpecification httpRequest;
	Response response;
	
	@BeforeClass
	public void getSingleEmployee() throws InterruptedException
	{
		logger.info("*******Started TC02-Get Single Employee details repository*******");
		
		RestAssured.baseURI = "http://dummy.restapiexample.com/api/v1";
		httpRequest = RestAssured.given();
	    response = httpRequest.request(Method.GET,"/employee/"+empID);
	    Thread.sleep(3000);
	    
	}
	
	@Test
	void CheckResponseBody()
	{
		logger.info("*******Checking Response Body*******");
		String responseBody = response.getBody().asString();
		//System.out.println("responseBody is:"+responseBody);
		logger.info("Response body is:"+responseBody);
		Assert.assertTrue(responseBody!=null);
		Assert.assertEquals(responseBody.contains(empID),true);
		
		
	}
	@Test
	void CheckStatusCode()
	{
		logger.info("*******Checking status code*******");
		int statusCode = response.getStatusCode();
		logger.info("Status Code is:"+statusCode);
		Assert.assertEquals(200, statusCode);
	}
	
	@Test
	void CheckResponseTime()
	{
		logger.info("*******Checking Response Time*******");
		long resTime = response.getTime();
		logger.info("Response Time is:"+resTime);
		if(resTime>2000)
			logger.warn("response time is greater than 2000 milli seconds.");
		Assert.assertTrue(resTime<2000);
	}
	
	@AfterClass
	void tearDown()
	{
		logger.info("***********Finished TC002-Get Single Employee details execution**********");
	}
}
