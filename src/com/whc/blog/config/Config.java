package com.whc.blog.config;

public class Config {
	public static String getUserId() {
		String id = "whc";
		return id;
	}
	
	public static String getUserPw() {
		String pw = "whc123";
		return pw;
	}
	
	public static String getDBDriverName() {
		String driverName = "com.mysql.cj.jdbc.Driver";
		return driverName;
	}
	
	public static String getUrl() {
		String url = "jdbc:mysql://localhost:3306/whc?serverTimezone=Asia/Seoul&useOldAliasMetadataBehavior=true&zeroDateTimeBehavior=convertToNull";
		return url;
	}
}
