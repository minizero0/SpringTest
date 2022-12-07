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
	#msgType, #mailType{
		display: none;
	}
</style>
<script type="text/javascript" src = "https://code.jquery.com/jquery-3.6.1.min.js"></script>
<script type="text/javascript">
	$(function(){
		$("#mail").click(function(){
			$("#mailType").css("display","inline");
			$("#msgType").css("display","none");
		})
		
		$("#msg").click(function(){
			$("#msgType").css("display","inline");
			$("#mailType").css("display","none");
		})
		
		
		var server_code;
		
		$("#btnSendMsg").click(function(){
			var data = {phone:$("#sendNum").val()}
			$.ajax({
				url:"CheckMsg",
				data:data,
				success:function(data){
					server_code = data;		
				}
			})
		})
		$("#btnCheckMsg").click(function(){
			if(server_code == $("#checkNum").val()){
				alert("인증번호가 확인되었습니다.")
				$("#phone").val($("#sendNum").val())
				$(".signUp").css("display","inline");
				
			}else{
				alert("인증번호가 잘못되었습니다.")
			}
		})
		
		$("#btnSendMail").click(function(){
			var data = {email:$("#email").val()}
			$.ajax({
				url:"sendCode",
				data:data,
				success:function(data){
					server_code = data;
					console.log(server_code);
				}
			})
		})
		
		$("#btnCheckMail").click(function(){
			if(server_code == $("#checkCode").val()){
				alert("인증되었습니다.")
				$("#email2").val($("#email").val())
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
	<button id = "mail">메일</button>
	<button id = "msg">문자</button>	
	<hr>
	
	<div id = "msgType">
		전화번호 : <input type = "tel" id = "sendNum"><button id = "btnSendMsg">인증번호 전송</button><br>
		인증번호 : <input type = "text" id = "checkNum"><button id = "btnCheckMsg">인증번호 확인</button><br>
	</div>
	
	<div id = "mailType">
		이메일 : <input type = "email" id = "email"><button id ="btnSendMail">코드 전송</button><br>
		인증코드 : <input type = "text" id = "checkCode"><button id = "btnCheckMail">인증확인</button><br>
	</div>
	<form action="signUp" method = "post" class = "signUp">
		아이디 : <input type = "text" name = "id"><br>
		비밀번호 : <input type = "password" name = "pwd"><br>
		이름 : <input type = "text" name = "name"><br>
		이메일 : <input type = "email" name = "email" id = "email2"><br>
		전화번호 : <input type = "tel" name = "phone" id = "phone"><br>
		<input type = "submit" value = "회원가입">
		<input type = "reset" value = "취소">
	</form>
</body>
</html>