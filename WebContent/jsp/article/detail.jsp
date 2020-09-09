<%@page import="java.util.Map"%>
<%@page import="java.util.List"%>
<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%@ include file="/jsp/part/head.jspf"%>
<%
	Map<String, Object> articleRow = (Map<String, Object>) request.getAttribute("articleRow");
%>
<table border=1>
	<tr>
		<th>번호</th>
		<td><%=articleRow.get("id") %></td>
		<th>제목</th>
		<td><%=articleRow.get("title") %></td>
		<th>작성일</th>
		<td><%=articleRow.get("regDate") %></td>
	</tr>
	<tr>
		<th>내용</th>
		<td colspan = "5"><%=articleRow.get("body") %></td>
	</tr>
</table>
<a href="write">글쓰기</a>

<%@ include file="/jsp/part/foot.jspf"%>