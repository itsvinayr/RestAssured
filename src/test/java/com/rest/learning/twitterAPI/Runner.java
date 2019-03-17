package com.rest.learning.twitterAPI;

import static io.restassured.RestAssured.given;

import java.io.FileInputStream;
import java.io.IOException;
import java.util.Properties;

import org.testng.annotations.BeforeTest;
import org.testng.annotations.Test;

import com.rest.learning.utilities.ResourceData;
import com.rest.learning.utilities.Utilities;

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
	public void test() {
		
		RestAssured.baseURI=prop.getProperty("TWITTER_HOST");
		
		// Get status
		Response getResponse = given().auth().oauth(prop.getProperty("CONSUMER_KEY"), 
								prop.getProperty("CONSUMER_SECRET"), 
								prop.getProperty("ACCESS_TOKEN"), 
								prop.getProperty("SECRET_TOKEN")).
				queryParam("count", 5).
		when().
			get(ResourceData.placeTwitterGetData()).
		then().
			assertThat().statusCode(200).
		extract().response();
		
		JsonPath json = Utilities.rawToJson(getResponse);
		int size = json.get("size()");
		System.out.println("size is "+size);
		
		// Post tweet
		Response postResponse = given().auth().oauth(prop.getProperty("CONSUMER_KEY"), 
				prop.getProperty("CONSUMER_SECRET"), 
				prop.getProperty("ACCESS_TOKEN"), 
				prop.getProperty("SECRET_TOKEN")).
				queryParam("status", "posting through automation 4").
		when().
			post(ResourceData.placeTwitterPostData()).
		then().
			assertThat().statusCode(200).
		extract().response();

		json = Utilities.rawToJson(postResponse);
		String tweetID = json.get("id").toString();
		System.out.println("Tweet ID "+tweetID);
		
		// delete tweet
		Response deleteResponse = given().auth().oauth(prop.getProperty("CONSUMER_KEY"), 
				prop.getProperty("CONSUMER_SECRET"), 
				prop.getProperty("ACCESS_TOKEN"), 
				prop.getProperty("SECRET_TOKEN")).
		when().
			post(ResourceData.placeTwitterDeleteData(tweetID)).
		then().
			assertThat().statusCode(200).
		extract().response();

		json = Utilities.rawToJson(deleteResponse);
		System.out.println(json.get("id"));
		System.out.println(json.get("text"));
		
	}

}
