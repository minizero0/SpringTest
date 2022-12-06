<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title>Insert title here</title>
<style type="text/css">
	.signUp{
		display: none;
	}
</style>
<script type="text/javascript" src = "https://code.jquery.com/jquery-3.6.1.min.js"></script>
<script type="text/javascript">
	$(function(){
		var emailCode;
		$("#btnSend").click(function(){
			var data = {email:$("#email").val()}
			$.ajax({
				url:"sendCode",
				data:data,
				success:function(code){
					emailCode = code;
					console.log(code);
				}
			})
		})
		
		$("#btnCheck").click(function(){
			if(emailCode == $("#checkCode").val()){
				$(".signUp").css("display","inline");
			}else{
				alert("잘못된 인증번호 입니다.")
			}
		})
	})
</script>
</head>
<body>
	<h2>회원가입</h2>
	<hr>
	이메일 : <input type = "email" id = "email"><button id ="btnSend">코드 전송</button><br>
	인증코드 : <input type = "text" id = "checkCode"><button id = "btnCheck">인증확인</button><br>
	<form action="signUp" method = "post" class = "signUp">
		아이디 : <input type = "text" name = "id"><br>
		비밀번호 : <input type = "password" name = "pwd"><br>
		이름 : <input type = "text" name = "name"><br>
		이메일 : <input type = "email" name = "email"><br>
		<input type = "submit" value = "회원가입">
		<input type = "reset" value = "취소">
	</form>
</body>
</html>