package com.rest.learning.utilities;

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

}
