package com.rest.learning.httpMethods.basics.optimizedCode;

import static io.restassured.RestAssured.given;
import static org.hamcrest.Matchers.equalTo;

import java.io.FileInputStream;
import java.io.IOException;
import java.util.Properties;

import org.testng.annotations.BeforeTest;
import org.testng.annotations.Test;

import com.rest.learning.utilities.PayLoadData;
import com.rest.learning.utilities.ResourceData;

import io.restassured.RestAssured;
import io.restassured.http.ContentType;
import io.restassured.path.json.JsonPath;
import io.restassured.response.Response;

public class Runner {
	
	public Properties prop;
	
	@BeforeTest
	public void setProperties() throws IOException {
		prop = new Properties();
		FileInputStream fis = new FileInputStream(System.getProperty("user.dir")+"\\src\\test\\resources\\files\\env.properties");
		prop.load(fis);
	}
	
	@Test
	public void testPostAndDelete() {
		
		// Set base URI
		RestAssured.baseURI = prop.getProperty("HOST");
		String body = PayLoadData.getPostData();
		
		Response response = given().
				queryParam("key", prop.getProperty("KEY")).
				body(body).
				when().
				post(ResourceData.placePostData()).
				then().
				assertThat().
							statusCode(200).and().
							contentType(ContentType.JSON).and().
							body("status", equalTo("OK")).
				extract().response();// raw response
		
		String responseString = response.asString();// converting raw response to string
		System.out.println(responseString);
		JsonPath js = new JsonPath(responseString);// converting string to json response
		String placeId = js.get("place_id");
		System.out.println("Place id is "+placeId);
		
		// Performing delete operation for above place
		String deleteBody = "{\r\n" + 
				"    \"place_id\":"+"\""+placeId+"\""+ 
				"\n}";
		System.out.println(deleteBody);
		String deleteResponse = 
		given().
				queryParam("key", prop.getProperty("KEY")).
				body(deleteBody).
		when().
				post(ResourceData.placeDeleteData()).
		then().
				assertThat().
							statusCode(200).and().
							contentType(ContentType.JSON).
		extract().response().asString();
		
		System.out.println(deleteResponse);
	}

}
