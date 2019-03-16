package com.rest.learning.loggers;

import static io.restassured.RestAssured.given;

import io.restassured.path.json.JsonPath;
import io.restassured.response.Response;

import org.testng.annotations.Test;
import static org.hamcrest.Matchers.equalTo;

import com.rest.learning.utilities.Utilities;

public class Runner {
	
	@Test
	public void printAllCircuitIds() {
		Response r = given().log().all().
		when().
			get("http://ergast.com/api/f1/2017/circuits.json").
		then().
		assertThat().body("MRData.series", equalTo("f2")).log().ifValidationFails().
		extract().response();
		JsonPath json = Utilities.rawToJson(r);
		int count = json.getInt("MRData.CircuitTable.Circuits.size()");
		System.out.println("number of circuits are "+count);
		for(int i=0; i<count; i++) {
			System.out.println("circuit id -- "+i+" "+
		json.get("MRData.CircuitTable.Circuits["+i+"].circuitId"));	
		}
		
	}

}
