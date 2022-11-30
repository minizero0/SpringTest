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
			location.href="deleteMember?no="+no;
	}
</script>
</head>
<body>
		번호 : ${m.no }<br>
		이름 : ${m.name }<br>
		나이 : ${m.age }<br>
		주소 : ${m.addr }<br>
		<hr>
		<a href = "updateMember?no=${m.no }">수정</a>
		<a href = "#" onclick = "check(${m.no})">삭제</a>
</body>
</html>