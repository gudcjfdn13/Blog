package com.whc.blog.service;

import java.sql.Connection;
import java.util.List;

import com.whc.blog.dao.ArticleDao;
import com.whc.blog.dto.Article;

public class ArticleService {

	private ArticleDao articleDao;
	
	public ArticleService(Connection dbConn) {
		articleDao = new ArticleDao(dbConn);
	}
	public List<Article> getForPrintListArticles(int page,int itemInAPage, int cateItemId) {
		return articleDao.getForPrintListArticles(page, itemInAPage, cateItemId);
	}
	public int getForPrintListArticlesCount(int cateItemId) {
		return articleDao.getForPrintListArticlesCount(cateItemId);
	}

}
