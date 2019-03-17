package com.rest.learning.utilities;

public class ResourceData {
	
	public static String placePostData() {
		return "/maps/api/place/add/json";
	}
	
	public static String placePostXmlData() {
		return "/maps/api/place/add/xml";
	}
	
	public static String placeDeleteData() {
		return "/maps/api/place/delete/json";
	}
	
	public static String placeDeleteXmlData() {
		return "/maps/api/place/delete/xml";
	}
	
	public static String placeJiraLoginData() {
		return "/rest/auth/1/session";
	}
	
	public static String placeCreateIssueData() {
		return "/rest/api/2/issue";
	}
	
	public static String placeJiraAddCommentData(String jiraID) {
		return "/rest/api/2/issue/"+jiraID+"/comment";
	}
	
	public static String placeJiraUpdateCommentData(String jiraID, String commentID) {
		return "/rest/api/2/issue/"+jiraID+"/comment/"+commentID;
	}
	
	public static String placeJiraDeleteBugData(String bugId) {
		return "/rest/api/2/issue/"+bugId;
	}
	
	public static String placeTwitterGetData() {
		return "/home_timeline.json";
	}
	
	public static String placeTwitterPostData() {
		return "/update.json";
	}
	
	public static String placeTwitterDeleteData(String tweetID) {
		return "/destroy/"+tweetID+".json";
	}

}
