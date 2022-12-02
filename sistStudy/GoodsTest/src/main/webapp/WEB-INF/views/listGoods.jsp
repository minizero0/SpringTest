<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title>Insert title here</title>
<style type="text/css">

	#op{
		display:none;
	}
</style>
<script type="text/javascript" src = "https://code.jquery.com/jquery-3.6.1.min.js"></script>
<script type="text/javascript">
	$(function(){
		var column;
		$("th").click(function(){
			column = $(this).html();
			location.href = "listGoods?pageNUM="+${pageNUM}+"&column="+column;
		})
		
	 	$("#cate").change(function(){
			var c_name = $(this).val();
			if(c_name == "name"){
				$("#op").css("display","none")
			}else{
				$("#op").css("display","inline")
			}
		})
		$("#f").submit(function(){
			var cate = $("#cate").val();
			var op = $("#op").val();
			var keyword = $("#keyword").val();
			
			sessionStorage.setItem("cate",cate);
			sessionStorage.setItem("op",op);
			sessionStorage.setItem("keyword",keyword);
		})
		
		var sc = sessionStorage.getItem("cate");
		var op = sessionStorage.getItem("op");
		var keyword = sessionStorage.getItem("keyword");
		
		$("#cate > option[value="+sc+"]").attr("selected","selected");
		$("#op > option[value='"+op+"']").attr("selected","selected");
		if(keyword != null && keyword != "null"){
			$("#keyword").val(keyword);
		}
		
		if(sc=="name" || sc ==null){
			$("#op").css("display","none");
		}else{
			$("#op").css("display","inline");
		}
		
	})
</script>
</head>
<body>
	<h2>상품목록</h2>
	<hr>
	<a href="insertGoods">상품등록</a>

	<form action="listGoods" method = "get" id = "f">
		<select name = "cate" id = "cate">
			<option value = "no">no</option>
			<option value = "name" selected="selected">name</option>
			<option value = "price">price</option>
			<option value = "qty">qty</option>
			<option value = "fname">fname</option>
		</select >
		<select id = "op" name = "op">
			<option value = "=">=</option>
			<option value = ">=">>=</option>
			<option value = "<="><=</option>
			<option value = ">">></option>
			<option value = "<"><</option>
			<option value = "!=">!=</option>
		</select>
		상품이름 : <input type = "search" name = "keyword" id = "keyword">
		<input type = "submit" value = "검색">
	</form>
	
	<hr>
	<table border = "1">
		<thead>
			<tr>
				<th>no</th>
				<th>name</th>
				<th>price</th>
				<th>qty</th>
				<th>fname</th>
			</tr>
		</thead>
		<tbody>
	<c:forEach var="g" items="${list }">
		<tr>
			<td><a href = "detailGoods?no=${g.no}">${g.no }</a></td>
			<td>${g.name }</td>
			<td>${g.price }</td>
			<td>${g.qty }</td>
			<td>${g.fname }</td>
		</tr>
	</c:forEach>
		</tbody>
	</table>
	
	<hr>
	<c:forEach var="i" begin = "1" end = "${totalPage }">
		<a href = "listGoods?pageNUM=${i }">${i }</a>&nbsp;&nbsp;
	</c:forEach>
</body>
</html>