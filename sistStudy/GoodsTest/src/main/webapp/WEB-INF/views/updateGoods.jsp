<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title>Insert title here</title>
</head>
<body>
	<form action="updateGoods" method="post" enctype="multipart/form-data">
		<input type = "hidden" name = "no" value = "${g.no }">
		상품명 : <input type = "text" name = "name" value = "${g.name }"><br>
		가격 : <input type = "number" name = "price" value = "${g.price }"><br>
		개수 : <input type = "number" name = "qty" value = "${g.qty }"><br>
		<input type = "hidden" name = "fname" value = "${g.fname }">
		<img alt="" src="images/${g.fname }" width = "50" height = "50"><br>
		사진 : <input type = "file" name = "uploadFile" ><br>
		<input type = "submit" value = "수정">
		<input type = "reset" value = "취소">
	</form>
</body>
</html>