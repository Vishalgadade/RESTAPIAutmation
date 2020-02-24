package DataDrivenTest;

import java.io.IOException;

import org.json.simple.JSONObject;
import org.testng.Assert;
import org.testng.annotations.DataProvider;
import org.testng.annotations.Test;

import io.restassured.RestAssured;
import io.restassured.http.Method;
import io.restassured.response.Response;
import io.restassured.specification.RequestSpecification;

public class DataDriven_Demo {
	
	@Test(dataProvider="Empdata")
	public void AddnewEmp(String ename,String eSal, String eage)
	{
		
		//specify base url
		
				RestAssured.baseURI="http://dummy.restapiexample.com/api/v1";
				
				//Request Object
				RequestSpecification httpRequest=RestAssured.given();
				
				
			////Request payload for POST request
							JSONObject requestParams = new JSONObject();
							requestParams.put("name", ename);
							requestParams.put("salary", eSal);
							requestParams.put("age", eage);
						
							
							httpRequest.header("Content-Type","application/json");
							
							// attach above data to request
							httpRequest.body(requestParams.toJSONString());
							
							//Response Object
							Response response=httpRequest.request(Method.POST,"/create");
							
							//Print Response
							String responseBody=response.getBody().asString();
							System.out.println("Response is" +responseBody);
							
							Assert.assertEquals(responseBody.contains(ename),true);
							Assert.assertEquals(responseBody.contains(eSal),true);
							Assert.assertEquals(responseBody.contains(eage),true);
									
							//status code validation
							int stCode=response.getStatusCode();
							System.out.println("status code is:"+stCode);
							Assert.assertEquals(stCode,200);
						
				
	}
	
	@DataProvider(name="Empdata")
	String [][] getEmpData() throws IOException
	{
		
		String Path=System.getProperty("user.dir")+"/src/test/java/DataDrivenTest/Empdata.xlsx";
		int rownum=ExcelUtilities.getRowCount(Path, "Sheet1");
		int colcount=ExcelUtilities.getCellCount(Path, "Sheet1", 1);
		
		String Empdata [][]= new String[rownum][colcount];
		for(int i=1;i<=rownum;i++) {
			for(int j=0;j<colcount;j++)
			{
				Empdata [i-1][j]=ExcelUtilities.getCellData(Path, "Sheet1", i, j);
			}
			
		}
		//String empData[][]= {{"abc","123","19"},{"def","345","21"},{"xyz","456","34"}};	
		return (Empdata);
	}

}
