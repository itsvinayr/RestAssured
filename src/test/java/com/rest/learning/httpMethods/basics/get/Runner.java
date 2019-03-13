package com.rest.learning.httpMethods.basics.get;

import static io.restassured.RestAssured.given;

import io.restassured.RestAssured;
import io.restassured.http.ContentType;
import static org.hamcrest.Matchers.equalTo;

import org.testng.annotations.Test;

public class Runner {

	@Test
	public void test() {
		
		// Set base URI
		RestAssured.baseURI = "https://maps.googleapis.com";
		
		given().
				param("location", "-33.8670522,151.1957362").
				param("radius", "1500").
				param("key", "AIzaSyAk6F-pW4x6vLOGTzbiu9w6qOGk8VZkwuY").
				when().
				get("/maps/api/place/nearbysearch/json").
				then().
				assertThat().statusCode(200).and().
							contentType(ContentType.JSON).and().
							body("results[0].name", equalTo("Sydney")).and().
							body("results[0].place_id", equalTo("ChIJP3Sa8ziYEmsRUKgyFmh9AQM")).and().
							header("Server","pablo");
		
		System.out.println("Completed");
		
	}

}
