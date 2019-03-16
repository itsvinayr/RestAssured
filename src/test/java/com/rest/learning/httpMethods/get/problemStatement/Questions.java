package com.rest.learning.httpMethods.get.problemStatement;

import static io.restassured.RestAssured.given;

import io.restassured.RestAssured;
import io.restassured.http.ContentType;
import io.restassured.path.json.JsonPath;
import io.restassured.response.Response;

import static org.hamcrest.Matchers.equalTo;

import org.testng.annotations.Test;

import com.rest.learning.utilities.Utilities;

public class Questions {
	
	/*
	 * print circuit id of all circuits
	 */
	@Test
	public void printAllCircuitIds() {
		Response r = given().
		when().
			get("http://ergast.com/api/f1/2017/circuits.json").
		then().
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
