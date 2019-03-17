package com.rest.learning.utilities;

import static io.restassured.RestAssured.given;

import io.restassured.RestAssured;
import io.restassured.path.json.JsonPath;
import io.restassured.path.xml.XmlPath;
import io.restassured.response.Response;

public class Utilities {

	public static XmlPath rawToXml(Response r) {
		String responseString = r.asString();// converting raw response to string
		System.out.println(responseString);
		XmlPath xml = new XmlPath(responseString);
		return xml;
	}

	public static JsonPath rawToJson(Response r) {
		String responseString = r.asString();// converting raw response to string
		System.out.println(responseString);
		JsonPath json = new JsonPath(responseString);
		return json;
	}

	public static String getJiraSessionKey() {
		RestAssured.baseURI = "http://localhost:8080";

		// Creating a session
		Response res = given().header("Content-Type", "application/json").body(PayLoadData.getJiraLoginData()).when()
				.post(ResourceData.placeJiraLoginData()).then().assertThat().statusCode(200).extract().response();

		JsonPath json = Utilities.rawToJson(res);
		String sessionId = json.get("session.value").toString();
		return sessionId;
	}

}
