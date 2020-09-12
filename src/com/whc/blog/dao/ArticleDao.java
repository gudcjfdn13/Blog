package com.whc.blog.dao;

import java.sql.Connection;
import java.util.ArrayList;
import java.util.List;
import java.util.Map;

import com.whc.blog.dto.Article;
import com.whc.blog.util.DBUtil;
import com.whc.blog.util.SecSql;

public class ArticleDao {
	private Connection dbConn;

	public ArticleDao(Connection dbConn) {
		this.dbConn = dbConn;
	}

	public List<Article> getForPrintListArticles(int page, int itemsInAPage, int cateItemId ) {
		
		int limitFrom = (page - 1) * itemsInAPage;
		
		SecSql sql = SecSql.from("SELECT *");
		sql.append("FROM article");
		sql.append("WHERE displayStatus = 1");
		if(cateItemId != 0) {
			sql.append("AND cateItemId = ?", cateItemId);
		}
		sql.append("ORDER BY id DESC");
		sql.append("LIMIT ?, ?", limitFrom, itemsInAPage);

		List<Map<String, Object>> rows = DBUtil.selectRows(dbConn, sql);
		List<Article> articles = new ArrayList<Article>();
		
		for(Map<String, Object> row : rows) {
			articles.add(new Article(row));
		}
		
		
		
		
		return articles;
	}

	public int getForPrintListArticlesCount(int cateItemId) {
		SecSql sql = SecSql.from("SELECT COUNT(*) AS cnt");
		sql.append("FROM article");
		sql.append("WHERE displayStatus = 1");
		if(cateItemId != 0) {
			sql.append("AND cateItemId = ?", cateItemId);
		}
		int count = DBUtil.selectRowIntValue(dbConn, sql);
		
		
		return count;
	}

}
