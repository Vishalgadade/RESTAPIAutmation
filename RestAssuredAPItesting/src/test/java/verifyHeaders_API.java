import org.testng.Assert;
import org.testng.annotations.Test;

import io.restassured.RestAssured;
import io.restassured.http.Header;
import io.restassured.http.Headers;
import io.restassured.http.Method;
import io.restassured.response.Response;
import io.restassured.specification.RequestSpecification;

public class verifyHeaders_API {

	
	@Test
	void getWeatherDetails()
	{
		//specify base url
		
		RestAssured.baseURI="http://restapi.de	moqa.com/utilities/weather/city";
		
		//Request Object
		RequestSpecification httpRequest=RestAssured.given();
		
		
		////Response Object
		Response response=httpRequest.request(Method.GET,"/Pune");
		
		//Print Response
		String responseBody=response.getBody().asString();
		System.out.println("Response is" +responseBody);
				
		Headers allHeaders = response.headers();
		
		for(Header header:allHeaders)
		{
			System.out.println(header.getName()+"  "+header.getValue());
			
		}
		
	}
}
