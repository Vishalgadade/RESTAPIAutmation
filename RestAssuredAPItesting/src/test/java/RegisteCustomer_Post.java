import org.json.simple.JSONObject;
import org.testng.Assert;
import org.testng.annotations.Test;

import io.restassured.RestAssured;
import io.restassured.http.Method;
import io.restassured.response.Response;
import io.restassured.specification.RequestSpecification;

public class RegisteCustomer_Post {
	
	@Test
	void registerCustomer()
	{
		//specify base url
		
				RestAssured.baseURI="http://restapi.demoqa.com/customer";
				
				//Request Object
				RequestSpecification httpRequest=RestAssured.given();
				
				
				////Request payload for POST request
				JSONObject requestParams = new JSONObject();
				requestParams.put("FirstName", "lash");
				requestParams.put("LastName", "gad");
				requestParams.put("UserName", "Viskan");
				requestParams.put("Password", "xyz");
				requestParams.put("Email", "xyz@gmail.com");
				
				httpRequest.header("Content-Type","application/json");
				
				// attach above data to request
				httpRequest.body(requestParams.toJSONString());
				
				//Response Object
				Response response=httpRequest.request(Method.POST,"/register");
				
				//Print Response
				String responseBody=response.getBody().asString();
				System.out.println("Response is" +responseBody);
						
				//status code validation
				int stCode=response.getStatusCode();
				System.out.println("status code is:"+stCode);
				Assert.assertEquals(stCode,201);
				
				//status line validation
				 String successCode=response.jsonPath().get("SuccessCode");
				 Assert.assertEquals(successCode,"OPERATION_SUCCESS");
				
	}

}
