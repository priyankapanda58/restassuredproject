import static org.testng.Assert.assertEquals;

import java.util.concurrent.TimeUnit;

import org.testng.annotations.Test;

import io.restassured.RestAssured;
import io.restassured.http.Header;
import io.restassured.http.Headers;
import io.restassured.http.Method;
import io.restassured.path.json.JsonPath;
import io.restassured.response.Response;
import io.restassured.specification.RequestSpecification;
import junit.framework.Assert;

public class TC001_GET_Request1 {

	@Test(enabled=true)
	void getWeatherDetails()
	{
		RestAssured.baseURI = "http://restapi.demoqa.com/utilities/weather/city";
		RequestSpecification httpRequest = RestAssured.given();
		Response response = httpRequest.request(Method.GET,"/Hyderabad");
		String responseBody = response.getBody().asString();
		System.out.println("responseBody is:"+responseBody);
		
		//Status code validation
		int statusCode = response.getStatusCode();
		Assert.assertEquals(200, statusCode);
		
		//Get all Headers
		Headers Header = response.getHeaders();
		for(Header h:Header)
		{
			System.out.println(h.getName()+": "+h.getValue());
		}
		
		//Get Content-type
		String contenttype = response.contentType();
		System.out.println("contenttype is:"+contenttype);
		
		//Get statusLine
		String statusLine = response.statusLine();
		System.out.println("statusLine is:"+statusLine);
		
		//Get Response Time
		long time = response.getTimeIn(TimeUnit.MILLISECONDS);
		System.out.println("Response time is:"+time+" milliseconds");
		
		//Get City value
		JsonPath jsonpath = response.jsonPath();
		System.out.println(jsonpath.get("Temperature"));
		
		System.out.println(response.jsonPath().get("City"));
		System.out.println(responseBody.contains("Hyderabad"));
		
}
	
}
