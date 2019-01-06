package com.rest.learning.httpMethods;

import org.testng.annotations.Test;

import io.restassured.http.ContentType;
import io.restassured.response.Response;
import junit.framework.Assert;

import static io.restassured.RestAssured.*;
import static org.hamcrest.Matchers.*;
import java.util.HashMap;

class ResponseFields{
	int statusCode;
}

public class PostMethod {
	
	HashMap<String, String> map = new HashMap<String, String>();
	
	@Test
	public void submitForm() {
		given().
			baseUri("https://www.example.com").
			urlEncodingEnabled(true).
			param("username", "user@site.com").
			param("password", "Pas54321").
			header("Accept", ContentType.JSON.getAcceptHeader()).
			post("/login").
		then().
			statusCode(404);
	}
	
	@Test
	public void deserializeResponse() {
		Response response = given().
			baseUri("https://www.example.com").
			urlEncodingEnabled(true).
			param("username", "user@site.com").
			param("password", "Pas54321").
			header("Accept", ContentType.JSON.getAcceptHeader()).
			post("/login");
		
		ResponseFields body = response.getBody().as(ResponseFields.class);
		Assert.assertEquals(0, body.statusCode);
		
		
		// similary we can convert json to string
		//List<String> allBooks = response.jsonPath().getList("books.title");
	}
	
	@Test
	public void testPost() {		
		given()
			.contentType(ContentType.JSON)
			.baseUri("http://jsonplaceholder.typicode.com")
			.basePath("/posts/")
			.body(map)
		.when()
			.post()
		.then()
			.statusCode(201)
			.and()
			.body("id", equalTo(101));
	}
}
