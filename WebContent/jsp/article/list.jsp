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
	border: 1px solid black;
}

.page-box>ul>li>a {
	padding: 0 10px;
	text-decoration: underline;
	color: #787878;
}

.page-box>ul>li:hover>a {
	color: black;
}

.page-box>ul>li.current>a {
	color: red;
}
</style>

<%
	List<Article> articleRows = (List<Article>) request.getAttribute("articles");
	int totalPage = (int) request.getAttribute("totalPage");
	int cPage = (int) request.getAttribute("page");
	String cateItemName = (String) request.getAttribute("cateItemName");
%>

<div class="con">
	<h1>
		<%=cateItemName%>
	</h1>
</div>

<div class="con">총 게시물 : ${totalCount}</div>

<div class="article-list-box-1 con table-box">
	<table>
		<colgroup>
			<col width="50" />
			<col width="200" />
			<col width="150" />
			<col width="50" />
		</colgroup>
		<thead>
			<tr>
				<th>번호</th>
				<th>제목</th>
				<th>작성일</th>
				<th>카테고리</th>
			</tr>
		</thead>
		<%
			for (Article articleRow : articleRows) {
		%>
		<tr>
			<td><%=articleRow.getId()%></td>
			<td><a href="detail?id=<%=articleRow.getId()%>"><%=articleRow.getTitle()%></a></td>
			<td><%=articleRow.getRegDate()%></td>
			<td>
				<%
					if (articleRow.getCateItemId() == 1) {
				%> IT/일상 <%
					} else if (articleRow.getCateItemId() == 2) {
				%> IT/JAVA <%
					} else if (articleRow.getCateItemId() == 3) {
				%> JSP <%
					} else if (articleRow.getCateItemId() == 4) {
				%> Android <%
					} else if (articleRow.getCateItemId() == 5) {
				%> JavaScript <%
					} else if (articleRow.getCateItemId() == 6) {
				%> 일상/취미 <%
					}
				%>

			</td>
		</tr>
		<%
			}
		%>
	</table>
</div>

<div class="con search-box flex flex-jc-c">

	<form action="${pageContext.request.contextPath}/s/article/list">
		<input type="hidden" name="page" value="1" /> <input type="hidden"
			name="cateItemId" value="${param.cateItemId }" /> <input
			type="hidden" name="searchKeywordType" value="title" /> <input
			type="text" name="searchKeyword" value="${param.searchKeyword}" />
		<button type="submit">검색</button>
	</form>
</div>


<div class="con page-box">
	<ul class="flex flex-jc-c">
		<%
			for (int i = 1; i <= totalPage; i++) {
		%>
		<li class="<%=i == cPage ? "current" : ""%>"><a
			href="?cateItemId=${param.cateItemId}&searchKeywordType=${param.searchKeywordType }
			&searchKeyword=${param.searchKeyword }&page=<%=i %>"
			class="block "><%=i%> </a></li>
		<%
			}
		%>
	</ul>
</div>


<%@ include file="/jsp/part/foot.jspf"%>