<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title>Insert title here</title>
</head>
<body>
	<h2>게시물 상세</h2>
	<hr>
	<a href = "insertBoard">등록</a>
	<a href = "listBoard">목록</a>
	<a href = "updateBoard?no=${b.no }">수정</a>
	<br>	
	글번호 : ${b.no }<br>
	글제목 : ${b.title}<br>
	작성자 : ${b.writer }<br>
	글내용 : <br>
	<textarea rows="10" cols="80">${b.content }</textarea>
	<br>
	조회수 : ${b.hit }<br>
	등록일 : ${b.regdate }<br>
	파일명 : ${b.fname }<br>
	<hr>

</body>
</html>