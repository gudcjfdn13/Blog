<%@page import="com.whc.blog.dto.Article"%>
<%@page import="java.util.Map"%>
<%@page import="java.util.List"%>
<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<%@ include file="/jsp/part/head.jspf"%>
<style>

.table-box>table {
	width: 100%;
	border-collapse: collapse;
	margin-top: 30px;
}

.table-box>table th, .table-box>table td {
	border: 1px solid black;
	padding: 10px;
}

.article-list-box-1 td {
	text-align: center;
}

.con {
	width: 1000px;
	margin: 0 auto;
}

.table-box>table {
	width: 100%;
	border-collapse: collapse;
}

.table-box>table th, .table-box>table td {
	border:1px solid black;
}
</style>
<%
	List<Article> articleRows = (List<Article>) request.getAttribute("articleRows");
%>
<div class="article-list-box-1 con table-box">
	<table>
	<colgroup>
		<col width="50"/>
		<col width="200"/>
		<col width="200"/>
	</colgroup>
		<thead>
			<tr>
				<th>번호</th>
				<th>제목</th>
				<th>작성일</th>
			</tr>
		</thead>
		<%
			for (Article articleRow : articleRows) {
		%>
		<tr>
			<td><%=articleRow.getId()%></td>
			<td><a href="detail?id=<%=articleRow.getId()%>"><%=articleRow.getTitle()%></a></td>
			<td><%=articleRow.getRegDate()%></td>
		</tr>
		<%
			}
		%>
	</table>
</div>
<a href="write">글쓰기</a>

<%@ include file="/jsp/part/foot.jspf"%>