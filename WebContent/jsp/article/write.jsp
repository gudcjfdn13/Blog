<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
    
    <link rel="stylesheet" href="${pageContext.request.contextPath}/resource/css/article/write.css">
<%@ include file = "/jsp/part/head.jspf"%>
	<div>
		<form action="doWrite" method="POST">
			<table class="write">
				<tr>
					<th>제목</th>
					<td><input type="text" name = "title" placeholder="제목"  autocomplete="off" /></td>
				</tr>
				<tr>
					<th>내용</th>
					<td><textarea name="body"></textarea></td>
				</tr>
			</table>
			<button type="submit">작성</button>
		</form>
	</div>
	
<%@ include file = "/jsp/part/foot.jspf"%>