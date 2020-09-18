package com.whc.blog.dao;

import java.sql.Connection;
import java.util.ArrayList;
import java.util.List;
import java.util.Map;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import com.whc.blog.dto.Article;
import com.whc.blog.dto.CateItem;
import com.whc.blog.util.DBUtil;
import com.whc.blog.util.SecSql;

public class ArticleDao extends Dao {
	private Connection dbConn;

	public ArticleDao(Connection dbConn, HttpServletRequest request, HttpServletResponse response) {
		super(request, response);
		this.dbConn = dbConn;
	}

	public List<Article> getForPrintListArticles(int page, int itemsInAPage, int cateItemId, String searchKeywordType,
			String searchKeyword) {

		int limitFrom = (page - 1) * itemsInAPage;

		SecSql sql = SecSql.from("SELECT *");
		sql.append("FROM article");
		sql.append("WHERE displayStatus = 1");
		if (cateItemId != 0) {
			sql.append("AND cateItemId = ?", cateItemId);
		}
		if (searchKeywordType.equals("title") && searchKeyword.length() > 0) {
			sql.append(String.format("AND title LIKE '%%%s%%'", searchKeyword));
		}
		sql.append("ORDER BY id DESC");
		sql.append("LIMIT ?, ?", limitFrom, itemsInAPage);

		List<Map<String, Object>> rows = DBUtil.selectRows(dbConn, sql);
		List<Article> articles = new ArrayList<Article>();

		for (Map<String, Object> row : rows) {
			articles.add(new Article(row));
		}

		return articles;
	}

	public int getForPrintListArticlesCount(int cateItemId, String searchKeywordType, String searchKeyword) {

		SecSql sql = SecSql.from("SELECT COUNT(*) AS cnt");
		sql.append("FROM article");
		sql.append("WHERE displayStatus = 1");

		if (cateItemId != 0) {
			sql.append("AND cateItemId = ?", cateItemId);
		}

		if (searchKeywordType.equals("title") && searchKeyword.length() > 0) {
			sql.append(String.format("AND title LIKE '%%%s%%'", searchKeyword));
		}
		int count = DBUtil.selectRowIntValue(dbConn, sql);
		return count;
	}

	public Article getForPrintArticle(int id) {
		SecSql sql = SecSql.from("SELECT *, '우형철' AS extra__writer");
		sql.append("FROM article");
		sql.append("WHERE 1");
		sql.append("AND id = ?", id);
		sql.append("AND displayStatus = 1");

		return new Article(DBUtil.selectRow(dbConn, sql));

	}

	public List<CateItem> getForPrintCateItems() {
		SecSql sql = SecSql.from("SELECT *");
		sql.append("FROM cateItem");
		sql.append("WHERE 1");
		sql.append("ORDER BY id ASC");

		List<Map<String, Object>> rows = DBUtil.selectRows(dbConn, sql);
		List<CateItem> cateItems = new ArrayList<>();

		for (Map<String, Object> row : rows) {
			cateItems.add(new CateItem(row));
		}

		return cateItems;
	}

	public CateItem getCateItem(int cateItemId) {
		SecSql sql = SecSql.from("SELECT *");
		sql.append("FROM cateitem");
		sql.append("WHERE 1");
		sql.append("AND id = ?", cateItemId);

		return new CateItem(DBUtil.selectRow(dbConn, sql));
	}

}
