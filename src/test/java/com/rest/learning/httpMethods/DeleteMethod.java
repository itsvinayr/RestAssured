package com.rest.learning.httpMethods;

import org.testng.annotations.Test;

import static io.restassured.RestAssured.*;
import static org.hamcrest.Matchers.*;

public class DeleteMethod {
	
	@Test
	public void testDelete() {
		given()
			.baseUri("http://jsonplaceholder.typicode.com")
			.basePath("/posts/")
		.when()
			.delete("/1")
		.then()
			.header("Expires", equalTo("-1"));
	}
	
}
