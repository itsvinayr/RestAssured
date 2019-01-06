package com.rest.learning.httpMethods;

import java.util.HashMap;

import org.testng.annotations.BeforeMethod;
import org.testng.annotations.Test;

import static io.restassured.RestAssured.*;
import static org.hamcrest.Matchers.*;

public class PutMethod {
	
	HashMap<String, String> map = new HashMap<String, String>();
	
	@BeforeMethod
	public void putData() {
		map.put("userId", "2");
		map.put("id", "19");
		map.put("title", "this is projectdebug.com");
		map.put("body", "i am testing REST api with REST-Assured and sending a PUT request.");
	}
	
	@Test
	public void testPutMethod() {
		
		given()
			.baseUri("http://jsonplaceholder.typicode.com")
			.basePath("/posts/")
			.contentType("application/json")
			.body(map)
		.when()
			.put("/100")
		.then()
			.statusCode(200)
			.and()
			.body("title", equalTo("this is projectdebug.com"));
		
	}

}
