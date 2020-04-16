import org.json.simple.JSONObject;
import org.testng.annotations.Test;

import io.restassured.RestAssured;
import io.restassured.http.Method;
import io.restassured.response.Response;
import io.restassured.specification.RequestSpecification;

public class TC002_POST_Request2 {
	@Test
	void registerCustomer()
	{
		RestAssured.baseURI = "http://restapi.demoqa.com/customer/register";
		RequestSpecification httpRequest = RestAssured.given();
		
		JSONObject jsonobj = new JSONObject();
		jsonobj.put("FirstName", "Priyanka");
		jsonobj.put("LastName", "Panda");
		jsonobj.put("UserName", "pp1234");
		jsonobj.put("Password", "pp");
		jsonobj.put("Email", "pp@test.com");
		
		String requestBody = jsonobj.toJSONString();
		
		httpRequest.header("Content-Type","application/json");
		httpRequest.body(requestBody);
		Response response = httpRequest.request(Method.POST,"");
		
		String responseBody=response.getBody().asString();
		System.out.println("Response Body is:" +responseBody);
		
		System.out.println(response.jsonPath().get("FaultId"));
		
		System.out.println(response.header("Content-Encoding"));
}
}
