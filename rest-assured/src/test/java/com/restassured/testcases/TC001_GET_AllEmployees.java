package com.restassured.testcases;
import org.testng.annotations.AfterClass;
import org.testng.annotations.BeforeClass;
import org.testng.annotations.Test;

import com.restassured.base.TestBase;

import io.restassured.RestAssured;
import io.restassured.http.Method;
import junit.framework.Assert;

public class TC001_GET_AllEmployees extends TestBase {

	@BeforeClass
	public void getEmployees() throws InterruptedException
	{
		logger.info("*******Started TC01-Get All Employees*******");
		RestAssured.baseURI = "http://dummy.restapiexample.com/api/v1";
		httpRequest = RestAssured.given();
	    response = httpRequest.request(Method.GET,"/employees");
	    Thread.sleep(5000);
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
		if(resTime>2000)
			logger.warn("response time is greater than 2000 milli seconds.");
		Assert.assertTrue(resTime<2000);
	}
	
	@Test
	void CheckStatusLine()
	{
		logger.info("*******Checking status Line*******");
		String statusLine = response.getStatusLine();
		logger.info("Status Line is:"+statusLine);
		//Assert.assertEquals(200, statusCode);
	}
	
	
	@Test
	void CheckContentType()
	{
		logger.info("*******Checking content type Header*******");
		String conentType = response.header("content-type");
		logger.info("ContentType is:"+conentType);
		Assert.assertEquals("application/json;charset=utf-8", conentType);
	}
	
	@Test
	void CheckServer()
	{
		logger.info("*******Checking server type Header*******");
		String server = response.header("server");
		logger.info("server is:"+server);
		Assert.assertEquals("nginx/1.16.0", server);
	}
	
	@Test
	void CheckContentEncoding()
	{
		logger.info("*******Checking content encoding Header*******");
		String contentEncoding = response.header("content-encoding");
		logger.info("content-encoding is:"+contentEncoding);
		Assert.assertEquals("gzip", contentEncoding);
	}

	@Test
	void CheckContentLength()
	{
		logger.info("*******Checking Content Length header*******");
		String contentLength = response.header("content-length");
		logger.info("Content length is:"+contentLength);
		if(Integer.parseInt(contentLength)<100)
			logger.warn("Content length is less than 100.");
		Assert.assertTrue(Integer.parseInt(contentLength)>100);
	}	
	
	@Test
	void getCookies()
	{
		logger.info("*******Checking Cookies*******");
		String cookie = response.getCookie("PHPSESSID");
		logger.info("Cookie is:"+cookie);
	}
	
	@AfterClass
	void tearDown()
	{
		logger.info("***********Finished TC001-Get Employees execution**********");
	}
}
