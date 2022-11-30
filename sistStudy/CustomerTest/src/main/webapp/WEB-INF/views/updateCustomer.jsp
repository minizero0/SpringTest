<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title>Insert title here</title>
</head>
<body>
<h2>고객 수정</h2>
<hr>
	<form action="updateCustomer" method = "post">
		<input type = "hidden" name = "custid" value = "${b.custid }">
		이름 : <input type = "text" name = "name" value = "${b.name }"><br>
		주소 : <input type = "text" name = "address" value = "${b.address }"><br>
		전화 : <input type = "text" name = "phone" value = "${b.phone }"><br>
		<input type= "submit" value = "수정">
		<input type= "reset" value = "취소">
	</form>	
	
<hr>
</body>
</html>