package com.whc.blog.servlet;

import java.io.IOException;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;
import java.util.List;
import java.util.Map;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import com.whc.blog.config.Config;
import com.whc.blog.util.DBUtil;
import com.whc.blog.util.SecSql;



@WebServlet("/s/article/detail")
public class ArticleDetailServlet extends HttpServlet {
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		response.setContentType("text/html; charset=UTF-8");
		
		Connection con = null;
		
		String title = request.getParameter("title");
		String body = request.getParameter("body");
		int id = Integer.parseInt(request.getParameter("id"));
		
		
		SecSql sql = SecSql.from("SELECT *");
		sql.append("FROM article");
		sql.append("WHERE id = ?", id);
		try {
			Class.forName(Config.getDBDriverName());
			con = DriverManager.getConnection(Config.getUrl(), Config.getUserId(), Config.getUserPw());
			
			Map<String, Object> articleRow = DBUtil.selectRow(con, sql);
			
			request.setAttribute("articleRow", articleRow);
			
			request.getRequestDispatcher("/jsp/article/detail.jsp").forward(request, response);
			
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
