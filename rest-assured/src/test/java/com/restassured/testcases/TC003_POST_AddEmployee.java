package com.restassured.testcases;

import org.json.simple.JSONObject;
import org.testng.annotations.AfterClass;
import org.testng.annotations.BeforeClass;
import org.testng.annotations.Test;

import com.restassured.base.TestBase;
import com.restassured.utilities.RestUtils;

import io.restassured.RestAssured;
import io.restassured.http.Method;
import io.restassured.response.Response;
import io.restassured.specification.RequestSpecification;
import junit.framework.Assert;

public class TC003_POST_AddEmployee extends TestBase {
	RequestSpecification httpRequest;
	Response response;
	
	String empName = RestUtils.empName();
	String empSal = RestUtils.empSal();
	String empAge = RestUtils.empAge();
	
	@BeforeClass()
	public void addNewEmployee() throws InterruptedException
	{
		logger.info("*******Started TC03-Add new Employee*******");
		RestAssured.baseURI = "http://dummy.restapiexample.com/api/v1";
		httpRequest = RestAssured.given();
		
		JSONObject jsonobj = new JSONObject();
		jsonobj.put("name", empName);
		jsonobj.put("salary", empSal);
		jsonobj.put("age", empAge);
		
		
		String requestBody = jsonobj.toJSONString();
		
		httpRequest.header("Content-Type","application/json");
		httpRequest.body(requestBody);
		response = httpRequest.request(Method.POST,"/create");
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
		if(resTime>3000)
			logger.warn("response time is greater than 3000 milli seconds.");
		Assert.assertTrue(resTime<3000);
	}

	@AfterClass
	void tearDown()
	{
		logger.info("***********Finished TC003-Post add employee execution**********");
	}
}
