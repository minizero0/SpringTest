<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title>Insert title here</title>
<style type="text/css">	
	.high_light{
		background: yellow;
	}
	
	.active{
		background: pink;
	}
	
	.goods_div{
		float: left;
		height: 300px;
		width: 300px;
		border: solid 3px pink;
		overflow: auto;
	}
	
	
	#goodsDetail{				
	}
	
</style>
<script type="text/javascript" src="https://code.jquery.com/jquery-3.6.1.min.js"></script>
<script type="text/javascript">
	$(function(){
		
		$(document).on("mouseover",".goods",function(){
			$(this).addClass("high_light");
		});
		$(document).on("mouseleave",".goods",function(){
			$(this).removeClass("high_light");
		});
		
		
		$(document).on("click",".goods", function(){	
			
			$("#goodsForm")[0].reset();
			
			$(".goods").removeClass("active");
			$(this).addClass("active");
			
			var no = $(this).attr("no");
			var data = {no:no};
			$.ajax({
				url:"detailGoods",
				data:data,
				success:function(g){
					$("#goodsDetail").empty();
					var no = $("<p></p>").html("상품번호: "+ g.no);	
					var name = $("<p></p>").html("상품이름: "+ g.name);	
					var price = $("<p></p>").html("상품가격: "+ g.price);	
					var qty = $("<p></p>").html("상품수량: "+ g.qty);
					var img = $("<img/>").attr({
						src:"images/"+g.fname,
						width:200,
						height:200
					});
					
					$("#goodsDetail").append(no,name,price,qty,img);
					
					$("#no").val(g.no);
					$("#name").val(g.name);
					$("#qty").val(g.qty);
					$("#price").val(g.price);
					$("#fname").val(g.fname);
					
				}
			})
		});
		
		function listGoods(){
			$.ajax({
				url:"listGoods",
				success:function(arr){
					$("#goodsList").empty();
					$.each(arr, function(){
						var li = $("<li></li>").html( this.name  ).addClass("goods").attr("no",this.no);
						$("#goodsList").append(li);
					});
					$("#goodsDetail").empty();
				}
			});
		}		
		
		function listDept(){
			$.ajax({
				url:"listDept",
				success:function( arr ){
					$("#list").empty();
					$.each(arr, function(){
						 var tr = $("<tr></tr>").addClass("item");
						 var td1 = $("<td></td>").html(this.dno);
						 var td2 = $("<td></td>").html(this.dname);
						 var td3 = $("<td></td>").html(this.dloc);
						 $(tr).append(td1,td2,td3);
						 $("#list").append(tr);
					});			
				}
			})
		}
		
		listDept();
		listGoods();
		
		$(document).on("mouseover",".item",function(){
			$(this).addClass("high_light");
		});
		$(document).on("mouseleave",".item",function(){
			$(this).removeClass("high_light");
		});
		
		$(document).on("click",".item",function(){
			var td_list = $(this).find("td");
			$("#dno").val(  $(td_list[0]).html()  );
			$("#dname").val(  $(td_list[1]).html()  );
			$("#dloc").val(  $(td_list[2]).html()  );
			
			$(".item").removeClass("active");
			$(this).addClass("active");
		});
		
		
		
		
		$("#btnInsert").click(function(){
			var data = $("#f").serializeArray();
			$.ajax({
				url:"insertDept",
				data:data,
				success:function(re){
					listDept();
				}
			})
		});
		
		$("#btnUpdate").click(function(){
			var data = $("#f").serializeArray();
			$.ajax({
				url:"updateDept",
				data:data,
				success:function(re){
					listDept();
				}
			})
		});
		
		$("#btnDelete").click(function(){
			
			if(confirm('정말로 삭제하시겠어요?')){
				var data = {dno:$("#dno").val()}
				$.ajax({
					url:"deleteDept",
					data:data,
					success:function(re){
						listDept();
					}
				})
			}
			
		});
		
		
		$("#btnGoodsInsert").click(function(){
			var form = new FormData(document.getElementById("goodsForm"));		
			$.ajax({
				url:"insertGoods",
				type:"post",
				processData:false,
				contentType:false,
				data:form,
				dataType:"json",
				success:function(){
					listGoods();
				}
			});
		});	
		
		
		$("#btnGoodsUpdate").click(function(){
			var form = new FormData(document.getElementById("goodsForm"));
			$.ajax({
				url:"updateGoods",
				type:"post",
				processData:false,
				contentType:false,
				data:form,
				dataType:"json",
				success:function(){
					listGoods();
				}
			});
		});
		
		
		
		$("#btnGoodsDelete").click(function(){
			
			if(confirm('정말로 삭제할까요?')){
				var data = {
					no:$("#no").val(),
					fname:$("#fname").val()
				}
				$.ajax({
					url:"deleteGoods",
					data:data,
					success:function(){
						listGoods();						
					}
				});
			}
			
		});
		
	});
</script>
</head>
<body>
	<h3>부서목록</h3>
	
	<table border="1">
		<thead>
			<tr>
				<td>부서번호</td>
				<td>부서명</td>
				<td>부서위치</td>
			</tr>
		</thead>
		<tbody id="list"></tbody>
	</table>
	
	<hr>
	<form id="f">
		부서번호 : <input type="text" name="dno" id="dno"><br>
		부서이름 : <input type="text" name="dname" id="dname"><br>
		부서위치 : <input type="text" name="dloc" id="dloc"><br>
		<input type="button" value="등록" id="btnInsert">
		<input type="button" value="수정" id="btnUpdate">
		<input type="button" value="삭제" id="btnDelete">
		
		<input type="reset" value="다시입력">
	</form>	
	
	<hr>
</body>
</html>