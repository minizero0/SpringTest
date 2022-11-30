<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title>Insert title here</title>
</head>
<body>
	<h2>고객상세</h2>
	<hr>
		번호 : ${b.custid }<br>
		이름 : ${b.name }<br>
		주소 : ${b.address }<br>
		번호 : ${b.phone }<br>
	<hr>
	<a href = "updateCustomer?custid=${c.custid }">수정</a>
</body>
</html>