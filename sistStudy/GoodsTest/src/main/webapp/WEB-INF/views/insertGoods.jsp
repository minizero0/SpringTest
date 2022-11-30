<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title>Insert title here</title>
</head>
<body>
	<form action="insertGoods" method="post">
		번호 : <input type = "number" name = "no"><br>
		이름 : <input type = "text" name = "name"><br>
		가격 : <input type = "text" name = "price"><br>
		개수 : <input type = "text" name = "qty"><br>
		사진 : <input type = "text" name = "fname"><br>
		<input type="submit" value = "등록">
		<input type="reset" value = "취소">
	</form>
</body>
</html>