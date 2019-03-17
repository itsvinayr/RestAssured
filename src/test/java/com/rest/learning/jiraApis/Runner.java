package com.rest.learning.jiraApis;

import static io.restassured.RestAssured.given;

import java.io.FileInputStream;
import java.io.IOException;
import java.util.Properties;

import org.testng.annotations.BeforeTest;
import org.testng.annotations.Test;

import com.rest.learning.utilities.PayLoadData;
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
		
		RestAssured.baseURI=prop.getProperty("JIRA_HOST");
		
		// Create Jira API
		Response response = given().
		header("Content-Type", "application/json").
		header("Cookie", "JSESSIONID="+Utilities.getJiraSessionKey()).
		body(PayLoadData.getJiraCreateIssueData("Sample5")).
		when().
		post(ResourceData.placeCreateIssueData()).
		then().
		assertThat().statusCode(201).
		extract().response();
		
		JsonPath json = Utilities.rawToJson(response);
		String id = json.get("id").toString();
		System.out.println("Id is "+id);	
		
		// add a comment
		Response addCommentResponse = given().
		header("Content-Type", "application/json").
		header("Cookie", "JSESSIONID="+Utilities.getJiraSessionKey()).
		body(PayLoadData.getJiraAddCommentData()).
		when().
		post(ResourceData.placeJiraAddCommentData(id)).
		then().
		assertThat().statusCode(201).
		extract().response();
		json = Utilities.rawToJson(addCommentResponse);
		String commentID = json.get("id");
		System.out.println("Comment ID is "+commentID);
		
		// update a comment
		Response updateCommentResponse = given().
		header("Content-Type", "application/json").
		header("Cookie", "JSESSIONID="+Utilities.getJiraSessionKey()).
		body(PayLoadData.getJiraUpdateCommentData()).
		when().
		put(ResourceData.placeJiraUpdateCommentData(id, commentID)).
		then().
		assertThat().statusCode(200).
		extract().response();
		json = Utilities.rawToJson(addCommentResponse);
		
		// delete issue
		Response deleteBugResponse = given().
		header("Content-Type", "application/json").
		header("Cookie", "JSESSIONID="+Utilities.getJiraSessionKey()).
		when().
		delete(ResourceData.placeJiraDeleteBugData(id)).
		then().
		assertThat().statusCode(204).
		extract().response();
		json = Utilities.rawToJson(addCommentResponse);
		
	}

}
