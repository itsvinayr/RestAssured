package com.rest.learning.httpMethods.basics.postAndDelete;

import static io.restassured.RestAssured.given;
import static org.hamcrest.Matchers.equalTo;

import org.testng.annotations.Test;

import io.restassured.RestAssured;
import io.restassured.http.ContentType;
import io.restassured.path.json.JsonPath;
import io.restassured.response.Response;

public class Runner {
	
	@Test
	public void testPostAndDelete() {
		
		// Set base URI
		RestAssured.baseURI = "http://216.10.245.166";
		String body = "{\r\n" + 
				"\"location\":{\r\n" + 
				"	\"lat\" : -38.383494,\r\n" + 
				"	\"lng\" : 33.427362\r\n" + 
				"},\r\n" + 
				"\"accuracy\":50,\r\n" + 
				"\"name\":\"Frontline house\",\r\n" + 
				"\"phone_number\":\"(+91) 983 893 3937\",\r\n" + 
				"\"address\" : \"29, side layout, cohen 09\",\r\n" + 
				"\"types\": [\"shoe park\",\"shop\"],\r\n" + 
				"\"website\" : \"http://google.com\",\r\n" + 
				"\"language\" : \"French-IN\"\r\n" + 
				"}\r\n" + 
				"";
		
		Response response = given().
				queryParam("key", "qaclick123").
				body(body).
				when().
				post("/maps/api/place/add/json").
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
				queryParam("key", "qaclick123").
				body(deleteBody).
		when().
				post("/maps/api/place/delete/json").
		then().
				assertThat().
							statusCode(200).and().
							contentType(ContentType.JSON).
		extract().response().asString();
		
		System.out.println(deleteResponse);
	}

}
