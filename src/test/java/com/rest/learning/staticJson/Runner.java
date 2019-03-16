package com.rest.learning.staticJson;

import static io.restassured.RestAssured.given;

import java.io.FileInputStream;
import java.io.IOException;
import java.util.Properties;

import org.testng.annotations.BeforeTest;
import org.testng.annotations.DataProvider;
import org.testng.annotations.Test;

import com.rest.learning.utilities.PayLoadData;
import com.rest.learning.utilities.Utilities;
import com.rest.learning.utilities.XmlOrJsonConversion;

import io.restassured.RestAssured;
import io.restassured.path.json.JsonPath;
import io.restassured.response.Response;

public class Runner {
	
	Properties prop;
	
	@BeforeTest
	public void loadProperties() throws IOException {
		prop = new Properties();
		FileInputStream fis = new FileInputStream(System.getProperty("user.dir")+"\\src\\test\\resources\\files\\env.properties");
		prop.load(fis);
	}
	
	@Test
	public void testAddAndDeleteBookAPI() throws IOException {
		
		RestAssured.baseURI = prop.getProperty("HOST");
		
		Response response = given().
				header("Content-Type", "application/json").
				body(XmlOrJsonConversion.generateStringFromResource(System.getProperty("user.dir")+"\\src\\test\\resources\\jsonFiles\\post.json")).
		when().
				post("/Library/Addbook.php").
		then().
				assertThat().statusCode(200).
		extract().response();
		
		JsonPath json = Utilities.rawToJson(response);
		System.out.println(json.get("ID"));
		
		Response deleteResponse = given().
				body(PayLoadData.deleteBookData(json.get("ID").toString())).
		when().
				post("/Library/DeleteBook.php").
		then().
				assertThat().statusCode(200).
		extract().response();
		
		json = Utilities.rawToJson(deleteResponse);		
		
	}

}
