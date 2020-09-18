package com.whc.blog.controller;

import java.sql.Connection;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

public class HomeController extends Controller {
	
	public HomeController(Connection dbConn, String actionMethodName, HttpServletRequest request, HttpServletResponse response) {
		super(dbConn, actionMethodName, request, response);
	}

	@Override
	public String doAction() {
		switch (actionMethodName) {
		case "main":
			return doActionMain(request, response);

		case "aboutMe":
			return doActionAboutMe(request, response);
		}
		return "";
	}

	private String doActionAboutMe(HttpServletRequest request, HttpServletResponse response) {
		return "home/aboutMe.jsp";
	}

	private String doActionMain(HttpServletRequest request, HttpServletResponse response) {
		return "home/main.jsp";
	}

}
