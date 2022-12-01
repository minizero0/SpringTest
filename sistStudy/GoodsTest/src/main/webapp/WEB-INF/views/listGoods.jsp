<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title>Insert title here</title>
</head>
<body>
	<h2>상품목록</h2>
	<hr>
	<a href="insertGoods">상품등록</a>
	<hr>
	<c:forEach var="g" items="${list }">
		<a href = "detailGoods?no=${g.no }">${g.no }</a><br>
	</c:forEach>
	<hr>
	<c:forEach var="i" begin = "1" end = "${totalPage }">
		<a href = "listGoods?pageNUM=${i }">${i }</a>&nbsp;&nbsp;
	</c:forEach>
</body>
</html>