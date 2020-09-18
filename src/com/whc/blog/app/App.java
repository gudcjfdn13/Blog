package com.whc.blog.app;

import java.io.IOException;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import com.whc.blog.config.Config;
import com.whc.blog.controller.ArticleController;
import com.whc.blog.controller.Controller;
import com.whc.blog.controller.HomeController;
import com.whc.blog.controller.MemberController;
import com.whc.blog.util.Util;

public class App {
	private HttpServletRequest request;
	private HttpServletResponse response;

	public App(HttpServletRequest request, HttpServletResponse response) {
		this.request = request;
		this.response = response;
	}

	private void loadDBDriver(HttpServletResponse response) throws IOException {
		try {
			Class.forName(Config.getDBDriverName());
		} catch (ClassNotFoundException e) {
			e.printStackTrace();
			System.err.printf("[ClassNotFoundException 예외]\n%s ", e.getMessage());
			response.getWriter().append("DB 드라이버 클래스 로딩 에러");

			return;
		}
	} // loadDBDriver()

	public void start() throws ServletException, IOException {

		// DB 드라이버 로딩
		loadDBDriver(response);

		Connection dbConn = null;

		try {
			dbConn = DriverManager.getConnection(Config.getUrl(), Config.getUserId(), Config.getUserPw());

			route(dbConn, request, response);

		} catch (SQLException e) {
			Util.printEx("SQL 예외", response, e);
		} catch (Exception e2) {
			Util.printEx("기타 예외", response, e2);
		} finally {
			if (dbConn != null) {
				try {
					dbConn.close();
				} catch (SQLException e) {
					Util.printEx("SQL 예외(커넥션 닫기)", response, e);
				}
			} // if()
		}

	} // start()

	private void route(Connection dbConn, HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {
		response.setContentType("text/html; charset=utf-8");

		String contextPath = request.getContextPath();
		String requsetURI = request.getRequestURI();
		String actionStr = requsetURI.replace(contextPath + "/s/", "");

		String[] actrionStrBits = actionStr.split("/");
		String controllerName = actrionStrBits[0];
		String actionMethodName = actrionStrBits[1];

		Controller controller = null;

		switch (controllerName) {
		case "article":
			controller = new ArticleController(dbConn, actionMethodName, request, response);
			break;

		case "member":
			controller = new MemberController(dbConn, actionMethodName, request, response);
			break;

		case "home":
			controller = new HomeController(dbConn, actionMethodName, request, response);
			break;
		}// switch()

		if (controller != null) {
			String actionResult = controller.executeAction();

			if (actionResult.equals("")) {
				response.getWriter().append("액션 결과가 없습니다.");

			} else if (actionResult.endsWith(".jsp")) {
				String viewPath = "/jsp/" + actionResult;
				request.getRequestDispatcher(viewPath).forward(request, response);

			} else if (actionResult.startsWith("html:")) {
				response.getWriter().append(actionResult.substring(6));
			} else {
				response.getWriter().append("처리할 수 없는 액션결과 입니다.");
			}

		} else {
			response.getWriter().append("존재하지 않는 페이지입니다.");
		} // if()

	}

}
