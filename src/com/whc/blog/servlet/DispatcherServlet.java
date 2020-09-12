package com.whc.blog.servlet;

import java.io.IOException;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import com.whc.blog.config.Config;
import com.whc.blog.controller.ArticleController;
import com.whc.blog.controller.Controller;
import com.whc.blog.controller.MemberController;

@WebServlet("/s/*")
public class DispatcherServlet extends HttpServlet {
	protected void doGet(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {
		response.setContentType("text/html; charset=utf-8");

		try {
			Class.forName(Config.getDBDriverName());
		} catch (ClassNotFoundException e) {
			e.printStackTrace();
			System.err.printf("[ClassNotFoundException 예외]\n%s ", e.getMessage());
			response.getWriter().append("DB 드라이버 클래스 로딩 에러");
			return;
		} // Class.forName()
		Controller controller = null;
		Connection dbConn = null;

		try {
			dbConn = DriverManager.getConnection(Config.getUrl(), Config.getUserId(), Config.getUserPw());

			String contextPath = request.getContextPath();
			String requsetURI = request.getRequestURI();
			String actionStr = requsetURI.replace(contextPath + "/s/", "");

			String[] actrionStrBits = actionStr.split("/");
			String controllerName = actrionStrBits[0];
			String actionMethodName = actrionStrBits[1];

			switch (controllerName) {
			case "article":
				controller = new ArticleController(dbConn);
				break;

			case "member":
				controller = new MemberController();
				break;
			}// switch()
			if (controller != null) {
				String viewPath = controller.doAction(actionMethodName, request, response);
				if (viewPath.equals("")) {
					response.getWriter().append("ERROR, CODE 1");
				}
				viewPath = "/jsp/" + viewPath + ".jsp";
				request.getRequestDispatcher(viewPath).forward(request, response);
			} else {
				response.getWriter().append("존재하지 않는 페이지입니다.");
			} // if()

		} catch (SQLException e) {
			e.printStackTrace();
			System.err.printf("[SQLException 예외]\n%s", e.getMessage());
			response.getWriter().append("DB 연결 에러");
			return;
		} catch (Exception e2) {
			e2.printStackTrace();
			System.err.printf("[Exception 예외]\n%s", e2.getMessage());
			response.getWriter().append("기타 에러");
			return;
		} finally {
			if (dbConn != null) {
				try {
					dbConn.close();
				} catch (SQLException e) {
					e.printStackTrace();
					System.err.printf("[SQLException 예외]\n%s", e.getMessage());
					response.getWriter().append("DB 연결 종료 실패");
				}
			} // if()
		}

	}

	protected void doPost(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {
		doGet(request, response);
	}

}
