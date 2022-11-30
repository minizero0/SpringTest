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
	<c var = "b" items="${list }">
	<a href="detailCustomer?custid=${b.custid }">${b.custid }</a>
		${b.name }
		${b.address }
		${b.phone }<br>
	</c>
</body>
</html>