import org.testng.annotations.Test;

import io.restassured.RestAssured;
import io.restassured.authentication.PreemptiveBasicAuthScheme;
import io.restassured.http.Method;
import io.restassured.response.Response;
import io.restassured.specification.RequestSpecification;

public class TC003_GET_AuthRequest {
	@Test
	void checkAuthentication()
	{
		RestAssured.baseURI = "https://restapi.demoqa.com/authentication/CheckForAuthentication";
		
		
		//Add basic auth to request
		PreemptiveBasicAuthScheme authsc = new PreemptiveBasicAuthScheme();
		authsc.setUserName("ToolsQA");
		authsc.setPassword("TestPassword");
		RestAssured.authentication = authsc;
		
		RequestSpecification httpRequest = RestAssured.given();
		Response response = httpRequest.request(Method.GET,"/");
		String responseBody = response.getBody().asString();
		System.out.println("responseBody is:"+responseBody);
		System.out.println("Response is"+response.asString());
}
}
