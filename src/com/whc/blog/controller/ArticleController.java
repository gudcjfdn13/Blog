package com.whc.blog.controller;

import java.sql.Connection;
import java.util.List;

import javax.security.auth.message.callback.PrivateKeyCallback.Request;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import com.whc.blog.dto.Article;
import com.whc.blog.dto.CateItem;
import com.whc.blog.util.Util;

public class ArticleController extends Controller {

	public ArticleController(Connection dbConn, String actionMethodName, HttpServletRequest request,
			HttpServletResponse response) {
		super(dbConn, actionMethodName, request, response);

	}

	@Override
	public void beforeAction() {
		super.beforeAction();
//		이 메서드는 게시물 컨트롤러의 모든 액션이 실행되기 전에 실행된다.
//		필요없다면 지워도 된다.

	}

	@Override
	public String doAction() {
		switch (actionMethodName) {
		case "list":
			return doActionList(request, response);

		case "detail":
			return doActionDetail(request, response);

		case "doWrite":
			return doActionDoWrite(request, response);
		}
		return "";

	}

	private String doActionDoWrite(HttpServletRequest request, HttpServletResponse response) {

		return null;
	}

	private String doActionDetail(HttpServletRequest request, HttpServletResponse response) {
		if (Util.empty(request, "id")) {

			return "html:id를 입력해주세요.";
		}
		if (Util.isNum(request, "id") == false) {
			return "html:id를 정수로 입력해주세요.";
		}

		int id = Util.getInt(request, "id");

		Article article = articleService.getForPrintArticle(id);

		request.setAttribute("article", article);

		return "article/detail.jsp";
	}

	private String doActionList(HttpServletRequest request, HttpServletResponse response) {
		int page = 1;
		if (!Util.empty(request, "page") && Util.isNum(request, "page")) {
			page = Util.getInt(request, "page");
		}

		int cateItemId = 0;
		if (!Util.empty(request, "cateItemId") && Util.isNum(request, "cateItemId")) {
			cateItemId = Util.getInt(request, "cateItemId");
		}
		
		String cateItemName = "전체";
		if(cateItemId != 0) {
			CateItem cateItem = articleService.getCateItem(cateItemId);
			cateItemName = cateItem.getName();
		}
		request.setAttribute("cateItemName", cateItemName);
		

		String searchKeywordType = "";
		if (!Util.empty(request, "searchKeywordType")) {
			searchKeywordType = Util.getString(request, "searchKeywordType");
		}

		String searchKeyword = "";
		if (!Util.empty(request, "searchKeyword")) {
			searchKeyword = Util.getString(request, "searchKeyword");
		}

		
		int itemInAPage = 10;
		int totalCount = articleService.getForPrintListArticlesCount(cateItemId, searchKeywordType, searchKeyword);
		int totalPage = (int) Math.ceil(totalCount / (double) itemInAPage);

		request.setAttribute("totalCount", totalCount);
		request.setAttribute("totalPage", totalPage);
		request.setAttribute("page", page);

		List<Article> articles = articleService.getForPrintListArticles(page, itemInAPage, cateItemId,
				searchKeywordType, searchKeyword);
		request.setAttribute("articles", articles);
		return "article/list.jsp";
	}
}