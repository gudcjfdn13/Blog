package com.whc.blog.controller;

import java.sql.Connection;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import com.whc.blog.service.ArticleService;

public class MemberController extends Controller {
	
	public MemberController(Connection dbConn, String actionMethodName, HttpServletRequest request, HttpServletResponse response) {
		super(dbConn, actionMethodName, request, response);
	}
	

	@Override
	public String doAction() {
		return "";
	}

}
