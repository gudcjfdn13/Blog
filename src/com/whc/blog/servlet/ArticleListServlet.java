package com.whc.blog.servlet;

import java.io.IOException;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;
import java.util.Map;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import com.whc.blog.config.Config;
import com.whc.blog.dto.Article;
import com.whc.blog.util.DBUtil;
import com.whc.blog.util.SecSql;



@WebServlet("/s/article/list2")
public class ArticleListServlet extends HttpServlet {
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		response.setContentType("text/html; charset=UTF-8");
		
		Connection con = null;
		
		String title = request.getParameter("title");
		String body = request.getParameter("body");
		
		SecSql sql = SecSql.from("SELECT *");
		sql.append("FROM article");
		sql.append("ORDER BY id DESC");
		
		List<Article> articles = new ArrayList<>();
		
		try {
			Class.forName(Config.getDBDriverName());
			con = DriverManager.getConnection(Config.getUrl(), Config.getUserId(), Config.getUserPw());
			
			List<Map<String, Object>> articleRows = DBUtil.selectRows(con, sql);
			
			for(Map<String, Object> articleRow: articleRows) {
				articles.add(new Article(articleRow));
			}
			
			request.setAttribute("articleRows", articles);
			
			request.getRequestDispatcher("/jsp/article/list.jsp").forward(request, response);
			
		} catch (ClassNotFoundException e) {
			// TODO Auto-generated catch block
			System.err.printf("[드라이버 클래스 로딩 예외] : %s\n" + e.getMessage());
		} catch (SQLException e) {
			System.err.printf("[SQL 예외] : %s\n" + e.getMessage());
		}finally {
			try {
				if (con != null) con.close();
			} catch (Exception e2) {
				System.err.printf("[SQL 예외, Connection 닫기] : %s\n" + e2.getMessage());
			}
		}
	}

	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		request.setCharacterEncoding("UTF-8");
		doGet(request, response);
	}
}
