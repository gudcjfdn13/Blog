package com.whc.blog.service;

import java.sql.Connection;
import java.util.List;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import com.whc.blog.dao.ArticleDao;
import com.whc.blog.dto.Article;
import com.whc.blog.dto.CateItem;

public class ArticleService extends Service {

	private ArticleDao articleDao;
	
	public ArticleService(Connection dbConn, HttpServletRequest request, HttpServletResponse response) {
		super(request, response);
		articleDao = new ArticleDao(dbConn, request, response);
	}
	public List<Article> getForPrintListArticles(int page,int itemInAPage, int cateItemId, String searchKeywordType, String searchKeyword) {
		return articleDao.getForPrintListArticles(page, itemInAPage, cateItemId, searchKeywordType, searchKeyword);
	}
	public int getForPrintListArticlesCount(int cateItemId, String searchKeywordType, String searchKeyword) {
		return articleDao.getForPrintListArticlesCount(cateItemId, searchKeywordType, searchKeyword);
	}
	public Article getForPrintArticle(int id) {
		return articleDao.getForPrintArticle(id);
	}
	public List<CateItem> getForPrintCateItems() {
		// TODO Auto-generated method stub
		return articleDao.getForPrintCateItems();
	}
	public CateItem getCateItem(int cateItemId) {
		return articleDao.getCateItem(cateItemId);
	}

}
