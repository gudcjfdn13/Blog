package com.whc.blog.controller;

import java.sql.Connection;
import java.util.List;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import com.whc.blog.dto.CateItem;
import com.whc.blog.service.ArticleService;

public abstract class Controller {
	protected Connection dbConn;
	protected String actionMethodName;
	protected HttpServletRequest request;
	protected HttpServletResponse response;
	
	protected ArticleService articleService;
	
	public Controller(Connection dbConn, String actionMethodName, HttpServletRequest request, HttpServletResponse response) {
		this.dbConn = dbConn;
		this.actionMethodName = actionMethodName;
		this.request = request;
		this.response = response;
		articleService = new ArticleService(dbConn, request, response);
	}
	
	public void beforeAction() {
//		이 메서드는 모든 컨트롤러의 모든 액션이 실행되기 전에 실행된다.
		List<CateItem> cateItems = articleService.getForPrintCateItems( );
		
		request.setAttribute("cateItems", cateItems);
	}
	
	public void afterAction() {
		
	}
	
	
	public abstract String doAction();


	public String executeAction() {
		beforeAction();
		String rs = doAction();
		afterAction();
		return rs;
	}
}
