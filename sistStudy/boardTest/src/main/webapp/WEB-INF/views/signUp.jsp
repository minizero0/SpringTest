<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title>Insert title here</title>
<style type="text/css">
	.signUp {
		display: none;
	}
	#div_code{
		display: none;
	}
</style>
<script type="text/javascript" src = "https://code.jquery.com/jquery-3.6.1.min.js"></script>
<script type="text/javascript">
	$(function(){
		var auth_type = "";
		
		$("#msg").click(function(){
			$("#auth_type_text").html("전화번호")
			$("#to").attr("type","phone")
			auth_type = "tel";
		})
		
		$("#mail").click(function(){
			$("#auth_type_text").html("이메일")
			$("#to").attr("type","email")
			auth_type = "email";
		})
		
		
		var server_code;
		
		$("#btnSend").click(function(){
			
			var data = {
				auth_type:auth_type,
				to:$("#to").val()
				}
			console.log(data);
			$.ajax({
				url:"sendAuthCode",
				data:data,
				success:function(data){
					server_code = data;		
					$("#div_code").css("display", "block")
				}
			})
		})
		$("#btnCheck").click(function(){
			if(server_code == $("#checkNum").val()){
				alert("인증번호가 확인되었습니다.")
				$(".signUp").css("display","inline");
				if(auth_type == "email"){
					$("#email").val($("#to").val())
				}else{
					$("#phone").val($("#to").val())	
				}
			}else{
				alert("인증번호가 잘못되었습니다.")
			}
		})
		
	})
</script>
</head>
<body>
	<h2>회원가입</h2>
	<hr>
	<input type = "radio" id = "msg" name = "auth">문자 인증
	<input type = "radio" id = "mail" name = "auth">이메일 인증
	<hr>
	
	
		<span id = "auth_type_text">전화번호</span> : <input type = "text" id = "to"><button id = "btnSend">인증번호 전송</button><br>
	<div id = "div_code">	
		인증번호 : <input type = "text" id = "checkNum"><button id = "btnCheck">인증번호 확인</button><br>
	</div>
	
	<br>
	<br>
	<br>
	<br>
	<form action="signUp" method = "post" class = "signUp">
		아이디 : <input type = "text" name = "id"><br>
		비밀번호 : <input type = "password" name = "pwd"><br>
		이름 : <input type = "text" name = "name"><br>
		이메일 : <input type = "email" name = "email" id = "email"><br>
		전화번호 : <input type = "tel" name = "phone" id = "phone"><br>
		<input type = "submit" value = "회원가입">
		<input type = "reset" value = "취소">
	</form>
</body>
</html>