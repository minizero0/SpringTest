<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title>Insert title here</title>
<script type="text/javascript" src = "https://code.jquery.com/jquery-3.6.1.min.js"></script>
<script type="text/javascript">
	$(function(){
		$("th").click(function(){
			var column = $(this).html();
			location.href = "listGoods?pageNUM="+${pageNUM}+"&column="+column;
		})
	})
</script>
</head>
<body>
	<h2>상품목록</h2>
	<hr>
	<a href="insertGoods">상품등록</a>
	<hr>
	<table border = "1">
		<thead>
			<tr>
				<th>no</th>
				<th>name</th>
				<th>price</th>
				<th>qty</th>
				<th>fname</th>
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
		<a href = "listGoods?pageNUM=${i }&column=<%=session.getAttribute("column")%>">${i }</a>&nbsp;&nbsp;
	</c:forEach>
</body>
</html>