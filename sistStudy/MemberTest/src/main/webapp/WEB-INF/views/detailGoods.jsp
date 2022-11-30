<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title>Insert title here</title>
<script type="text/javascript">
	function check(no){
		if(confirm("정말로 삭제할거야?"))
			location.href="deleteGoods?no="+no;
	}
</script>
</head>
<body>
		번호 : ${list.no }<br>
		이름 : ${list.name }<br>
		가격 : ${list.price }<br>
		개수 : ${list.qty }<br>
		사진 : ${list.fname }<br>
		<hr>
		<a href = "updateGoods?no=${list.no }">수정</a>
		<a href = "#" onclick = "check(${list.no})">삭제</a>
</body>
</html>