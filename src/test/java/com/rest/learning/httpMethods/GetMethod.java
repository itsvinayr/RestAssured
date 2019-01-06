package com.rest.learning.httpMethods;

import org.testng.annotations.DataProvider;
import org.testng.annotations.Test;

import io.restassured.builder.ResponseSpecBuilder;
import io.restassured.http.ContentType;
import io.restassured.specification.ResponseSpecification;

import static io.restassured.RestAssured.*;
import static org.hamcrest.Matchers.*;

/**
 * Get is used to request a resource from REST API
 *
 */

public class GetMethod{
	
	@Test
	public void testGetMethod() {
		
		given().
		when().
			get("http://ergast.com/api/f1/2017/circuits.json").
		then().
			assertThat().
			body("MRData.CircuitTable.Circuits.circuitId",hasSize(20)).
		and().
			contentType(ContentType.JSON).
		and().
			header("Content-Length",equalTo("4551"));
	}
	
	@Test
	public void testParameterizedGetMethod() {
		
		String season = "2017";
		int numberOfRaces = 20;
		
		given().
			pathParam("raceSeason", season).
		when().
			get("http://ergast.com/api/f1/{raceSeason}/circuits.json").
		then().
			assertThat().
			body("MRData.CircuitTable.Circuits.circuitId",hasSize(numberOfRaces));		
	}
	
	@Test(dataProvider="getSeasonsList")
	public void testParameterizationUsingDataProvider(String season, int numberOfRaces) {
		
		given().
			pathParam("raceSeason", season).
		when().
			get("http://ergast.com/api/f1/{raceSeason}/circuits.json").
		then().
			assertThat().
			body("MRData.CircuitTable.Circuits.circuitId",hasSize(numberOfRaces));
	}
	
	@Test
	public void accessingSecuredAPI() {
		given().
			auth().
			preemptive().
			basic("username", "password").
		when().
			get("http://path.to/basic/secured/api").
		then().
			assertThat().
			statusCode(200);
	}
	
	@Test
	public void accessingOauthsecuredAPI() {
		given().
	        auth().
	        oauth2("YOUR_AUTHENTICATION_TOKEN_GOES_HERE").
	    when().
	        get("http://path.to/oath2/secured/api").
	    then().
	        assertThat().
	        statusCode(200);
	}
	
	@Test
	public void passParametersBetweenTests() {
		// First, retrieve the circuit ID for the first circuit of the 2017 season
	    String circuitId = given().
	    when().
	        get("http://ergast.com/api/f1/2017/circuits.json").
	    then().
	        extract().
	        path("MRData.CircuitTable.Circuits.circuitId[0]");
	        
	    // Then, retrieve the information known for that circuit and verify it is located in Australia
	    given().
	        pathParam("circuitId",circuitId).
	    when().
	        get("http://ergast.com/api/f1/circuits/{circuitId}.json").
	    then().
	        assertThat().
	        body("MRData.CircuitTable.Circuits.Location[0].country",equalTo("Australia"));
	}
	
	ResponseSpecification checkStatusCodeAndContentType = 
		    new ResponseSpecBuilder().
		        expectStatusCode(200).
		        expectContentType(ContentType.JSON).
		        build();
	
	@Test
	public void usingResponseSpecs() {
		given().
	    when().
	        get("http://ergast.com/api/f1/2017/circuits.json").
	    then().
	        assertThat().
	        spec(checkStatusCodeAndContentType).
	    and().
	        body("MRData.CircuitTable.Circuits.circuitId",hasSize(20));
	}
	
	@Test
	public void getPostmanEcho() {
		given()
		.when()
		.get("https://postman-echo.com/GET")
		.then()
			.body("headers.host", equalTo("postman-echo.com"));		
	}
	
	@DataProvider(name="getSeasonsList")
	public Object[][] getData(){
		return new Object[][] {
			{"2017", 20},
			{"2016", 21},
			{"1966", 9}
		};
	}	
}
