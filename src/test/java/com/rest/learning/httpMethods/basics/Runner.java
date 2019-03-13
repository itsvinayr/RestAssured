package com.rest.learning.httpMethods.basics;

import static io.restassured.RestAssured.given;
import io.restassured.RestAssured;

public class Runner {

	public static void main(String[] args) {
		
		// Set base URI
		RestAssured.baseURI = "https://maps.googleapis.com";
		
		given().
				param("location", "-33.8670522,151.1957362").
				param("radius", "1500").
				param("key", "AIzaSyAk6F-pW4x6vLOGTzbiu9w6qOGk8VZkwuY").
				when().
				get("/maps/api/place/nearbysearch/json").
				then().
				assertThat().statusCode(200);
		
		System.out.println("Completed");
		
	}

}
