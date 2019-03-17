package com.rest.learning.utilities;

public class PayLoadData {
	
	public static String getPostData() {
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
		return body;
	}
	
	public static String getAddBookData(String isbn, String aisle) {
		String body = "{\r\n" + 
				"\"name\":\"Learn Appium Automation with Java\",\r\n" + 
				"\"isbn\":\""+isbn+"\",\r\n" + 
				"\"aisle\":\""+aisle+"\",\r\n" + 
				"\"author\":\"John foe\"\r\n" + 
				"}";
		return body;
	}
	
	public static String deleteBookData(String id) {
		String body = "{\r\n" + 
				" \r\n" + 
				"\"ID\" : \""+id+"\"\r\n" + 
				" \r\n" + 
				"}\r\n" + 
				"";
		return body;
	}
	
	public static String getJiraLoginData() {
		String body = "{\r\n" + 
				"	\"username\": \"vinay.raghu10\",\r\n" + 
				"	\"password\": \"vBqbdYD76Y2Jifb\"\r\n" + 
				"}";
		return body;
	}
	
	public static String getJiraCreateIssueData(String summary) {
		String body = "{\r\n" + 
				"	\"fields\":{\r\n" + 
				"		\"project\":{\r\n" + 
				"			\"key\":\"RES\"\r\n" + 
				"			},\r\n" + 
				"		\"summary\":\""+summary+"\",\r\n" + 
				"		\"description\":\"My first bug\",\r\n" + 
				"		\"issuetype\":{\r\n" + 
				"			\"name\":\"Bug\"\r\n" + 
				"			}\r\n" + 
				"	}\r\n" + 
				"}";
		return body;
	}
	
	public static String getJiraAddCommentData() {
		String body = "{\r\n" + 
				"    \"body\": \"My first comment\",\r\n" + 
				"    \"visibility\": {\r\n" + 
				"        \"type\": \"role\",\r\n" + 
				"        \"value\": \"Administrators\"\r\n" + 
				"    }\r\n" + 
				"}";
		return body;
	}
	
	public static String getJiraUpdateCommentData() {
		String body = "{\r\n" + 
				"    \"body\": \"Updating comment\",\r\n" + 
				"    \"visibility\": {\r\n" + 
				"        \"type\": \"role\",\r\n" + 
				"        \"value\": \"Administrators\"\r\n" + 
				"    }\r\n" + 
				"}";
		return body;
	}

}
