package datadriventesting;
import java.io.IOException;

import org.json.simple.JSONObject;
import org.testng.annotations.DataProvider;
import org.testng.annotations.Test;

import io.restassured.RestAssured;
import io.restassured.http.Method;
import io.restassured.path.json.JsonPath;
import io.restassured.response.Response;
import io.restassured.specification.RequestSpecification;

public class TC004_POST_DataProvider {

	@Test(dataProvider="Test1")
	public void createEmployee(String ename, String sal, String age)
	{
		RestAssured.baseURI = "http://dummy.restapiexample.com/api/v1/create";
		RequestSpecification httpRequest = RestAssured.given();
		
		JSONObject jsonobj = new JSONObject();
		jsonobj.put("name", ename);
		jsonobj.put("salary", sal);
		jsonobj.put("age", age);
		String requestBody = jsonobj.toJSONString();
		
		httpRequest.header("Content-Type","application/json");
		httpRequest.body(requestBody);
		Response response = httpRequest.request(Method.POST,"");
		
		String responseBody=response.getBody().asString();
		System.out.println("Response Body is:" +responseBody);
		JsonPath jsonpath = response.jsonPath();
		System.out.println(jsonpath.get("data.id"));
		
	}
	/*
	//Data driven testing -with hard coded data
	@DataProvider(name="Test1")
	public String[][] getEmployeeData()
	{
		String empData[][] = {
				              {"emp1","1000","21"},
				              {"emp2","1002","22"},
				              {"emp3","1003","23"},
				              {"emp4","1004","24"}
				              };
		return empData;
	}*/
	
	//Data driven testing -with excel
	@DataProvider(name="Test1")
	public String[][] getEmployeeData() throws IOException
	{
		String userdir = System.getProperty("user.dir");
		String path = userdir+"/src/test/java/datadriventesting/empData.xlsx";
		int rowcount = XLUtils.getRowCount(path, "Sheet1");
		int colcount = XLUtils.getCellCount(path, "Sheet1", rowcount);
		System.out.println("No. of rows:"+rowcount+" No. of columns"+colcount);
		String empData[][] = new String[rowcount][colcount];
		for(int i=1;i<=rowcount;i++)
		{
			for(int j=0;j<colcount;j++)
			{
				empData[i-1][j] = XLUtils.getCellData(path, "Sheet1", i, j);
				
			}
		}
		for(int i=0;i<rowcount;i++)
		{
			for(int j=0;j<colcount;j++)
			{
				System.out.print(empData[i][j]+" ");
			}
			System.out.println();
		}
		
		
		return empData;
	}
}
