import org.testng.Assert;
import org.testng.annotations.Test;

import io.restassured.RestAssured;
import io.restassured.authentication.PreemptiveBasicAuthScheme;
import io.restassured.http.Method;
import io.restassured.response.Response;
import io.restassured.specification.RequestSpecification;

public class Basic_Auth_Verification {
	
	@Test
	public void Autorization() {
		
     RestAssured.baseURI="http://restapi.demoqa.com/authentication/CheckForAuthentication";
		
     //Basic Authentication
     PreemptiveBasicAuthScheme authScheme= new PreemptiveBasicAuthScheme();
     authScheme.setUserName("ToolsQA");
     authScheme.setPassword("TestPassword");
     RestAssured.authentication=authScheme;
     
		//Request Object
		RequestSpecification httpRequest=RestAssured.given();
		
		
		////Response Object
		Response response=httpRequest.request(Method.GET,"/");
		
		//Print Response
		String responseBody=response.getBody().asString();
		System.out.println("Response is" +responseBody);
						
				//status code validation
				int stCode=response.getStatusCode();
				System.out.println("status code is:"+stCode);
				Assert.assertEquals(stCode,200);
				
		
	}

}
