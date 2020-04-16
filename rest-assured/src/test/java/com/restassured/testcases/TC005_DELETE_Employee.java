package com.restassured.testcases;

import javax.swing.Spring;

import org.json.simple.JSONObject;
import org.testng.annotations.AfterClass;
import org.testng.annotations.BeforeClass;
import org.testng.annotations.Test;

import com.restassured.base.TestBase;
import com.restassured.utilities.RestUtils;

import io.restassured.RestAssured;
import io.restassured.http.Method;
import io.restassured.path.json.JsonPath;
import io.restassured.response.Response;
import io.restassured.specification.RequestSpecification;
import junit.framework.Assert;

public class TC005_DELETE_Employee extends TestBase{
	RequestSpecification httpRequest;
	Response response;
	
	String empName = RestUtils.empName();
	String empSal = RestUtils.empSal();
	String empAge = RestUtils.empAge();
	
	@BeforeClass()
	public void addNewEmployee() throws InterruptedException
	{
		logger.info("*******Started TC005-Delete single Employee*******");
		RestAssured.baseURI = "http://dummy.restapiexample.com/api/v1";
		httpRequest = RestAssured.given();
		response = httpRequest.request(Method.GET,"/employees");
		JsonPath jsonpath = response.jsonPath();
		String empId = jsonpath.get("data[20].id");
		logger.info("Employee to be deleted:"+empId);
		response = httpRequest.request(Method.DELETE,"/delete/"+empId);
	    Thread.sleep(3000);
	}
	@Test
	void CheckResponseBody()
	{
		logger.info("*******Checking Response Body*******");
		String responseBody = response.getBody().asString();
		
		logger.info("Response body is:"+responseBody);
		Assert.assertEquals(true,responseBody.contains("successfully! deleted Records") );
		
		
		
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
		logger.info("***********Finished TC005-Delete single employee details execution**********");
	}
}


