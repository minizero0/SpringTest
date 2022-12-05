<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title>Insert title here</title>
<style type="text/css">
	.high_ligh{
		background: pink;
	}
</style>
<script type="text/javascript" src = "https://code.jquery.com/jquery-3.6.1.min.js"></script>
<script type="text/javascript">
	$(function(){
		$(".row").hover(function(){
			$(this).addClass("high_light");
		}, function(){
			$(this).removeClass("high_light");
		});
	})
</script>
</head>
<body>
	<h2>게시물 목록</h2>
	<hr>
	<a href = "insertBoard">게시물 등록</a>
	<table border = "1">
	<tr>
		<td>글번호</td>
		<td>글제목</td>
		<td>작성자</td>
		<td>등록일</td>
	</tr>
	<c:forEach var = "b" items = "${list }">
	<tr class = "row" no= "${b.no }">
		<td>${b.no}</td>
		<td>${b.title}</td>
		<td>${b.writer}</td>
		<td>${b.regdate}</td>
	</tr>
	</c:forEach>
	</table>
</body>
</html>