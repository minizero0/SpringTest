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
	<h2>고객상세</h2>
	<c:forEach var="b" items = "${detailCustomer }">
		${b.custid }
		${b.name }
		${b.address }
		${b.phone }<br>
	</c:forEach>
</body>
</html>