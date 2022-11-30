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
<h2>고객목록</h2>
<hr>
<a href = "insertCustomer">고객등록</a>
	<c:forEach var = "b" items="${list }">
	<li><a href="detailCustomer?custid=${b.custid }">${b.custid }</a></li>
		
	</c:forEach>
</body>
</html>