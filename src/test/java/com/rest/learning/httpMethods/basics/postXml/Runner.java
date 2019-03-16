package com.rest.learning.httpMethods.basics.postXml;

import static io.restassured.RestAssured.given;
import static org.hamcrest.Matchers.equalTo;

import java.io.FileInputStream;
import java.io.IOException;
import java.util.Properties;

import org.testng.annotations.BeforeTest;
import org.testng.annotations.Test;

import com.rest.learning.utilities.ResourceData;
import com.rest.learning.utilities.Utilities;
import com.rest.learning.utilities.XMLUtility;

import io.restassured.RestAssured;
import io.restassured.http.ContentType;
import io.restassured.path.json.JsonPath;
import io.restassured.path.xml.XmlPath;
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
	public void testPostAndDelete() throws IOException {
		
		// Set base URI
		RestAssured.baseURI = prop.getProperty("HOST");
		String body = XMLUtility.generateStringFromResource(System.getProperty("user.dir")+"\\src\\test\\resources\\xmlFiles\\post.xml");
		
		Response response = given().
				queryParam("key", prop.getProperty("KEY")).
				body(body).
		when().
				post(ResourceData.placePostXmlData()).
		then().
				assertThat().
							statusCode(200).and().
							contentType(ContentType.XML).and().
							body("response.status", equalTo("OK")).
		extract().response();		
		 
		XmlPath xml = Utilities.rawToXml(response);
		String placeId = xml.get("response.place_id");
		System.out.println("Place id is "+placeId);		
		
		String deleteBody = "<?xml version=\"1.0\" encoding=\"UTF-8\"?>\r\n" + 
				"<root>\r\n" + 
				"   <place_id>"+placeId+"</place_id>\r\n" + 
				"</root>";
		System.out.println(deleteBody);
		Response deleteResponse = given().
				queryParam("key", prop.getProperty("KEY")).
				body(deleteBody).
		when().
				post(ResourceData.placeDeleteXmlData()).
		then().
				assertThat().
							statusCode(200).
							contentType(ContentType.XML).
		extract().response();
		
		XmlPath deleteXml = Utilities.rawToXml(deleteResponse);		  
		 
	}

}
