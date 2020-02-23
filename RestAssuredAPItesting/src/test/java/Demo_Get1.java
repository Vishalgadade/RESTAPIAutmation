import org.testng.Assert;
import org.testng.annotations.Test;

import io.restassured.RestAssured;
import io.restassured.http.Method;
import io.restassured.response.Response;
import io.restassured.specification.RequestSpecification;

public class Demo_Get1 {
	
	
	@Test
	void getWeatherDetails()
	{
		//specify base url
		
		RestAssured.baseURI="http://restapi.demoqa.com/utilities/weather/city";
		
		//Request Object
		RequestSpecification httpRequest=RestAssured.given();
		
		
		////Response Object
		Response response=httpRequest.request(Method.GET,"/Pune");
		
		//Print Response
		String responseBody=response.getBody().asString();
		System.out.println("Response is" +responseBody);
				
		//status code validation
		int stCode=response.getStatusCode();
		System.out.println("status code is:"+stCode);
		Assert.assertEquals(stCode,200);
		
		//status line validation
		String stLine=response.getStatusLine();
		System.out.println("status line is:"+stLine);
		Assert.assertEquals(stLine,"HTTP/1.1 200 OK");
		
	}

}
