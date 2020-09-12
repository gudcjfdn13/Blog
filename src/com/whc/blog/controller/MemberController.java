package com.whc.blog.controller;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

public class MemberController extends Controller {

	@Override
	public String doAction(String actionMethodName, HttpServletRequest request, HttpServletResponse response) {
		System.out.printf("member 컨트롤러인 나는 %s 요청을 받았다.", actionMethodName);
		return "";
	}

}
