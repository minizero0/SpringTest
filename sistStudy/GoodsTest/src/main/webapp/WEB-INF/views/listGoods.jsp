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
	<table border = "1">
		<thead>
			<tr>
				<th>No</th>
				<th>Name</th>
				<th>Price</th>
				<th>Qty</th>
				<th>Fname</th>
			</tr>
		</thead>
		<tbody>
	<c:forEach var="g" items="${list }">
		<tr>
			<td><a href = "detailGoods?no=${g.no}">${g.no }</a></td>
			<td>${g.name }</td>
			<td>${g.price }</td>
			<td>${g.qty }</td>
			<td>${g.fname }</td>
		</tr>
	</c:forEach>
		</tbody>
	</table>
	
	<hr>
	<c:forEach var="i" begin = "1" end = "${totalPage }">
		<a href = "listGoods?pageNUM=${i }">${i }</a>&nbsp;&nbsp;
	</c:forEach>
</body>
</html>