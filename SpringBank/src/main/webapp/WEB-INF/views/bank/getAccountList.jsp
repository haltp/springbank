<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title>getAccountList.jsp</title>
<script src="https://ajax.googleapis.com/ajax/libs/jquery/3.5.1/jquery.min.js"></script>
</head>
<body>
<h3>계좌목록</h3>
<table border="1">
<c:forEach items="${list.res_list}" var="bank">
    <tr>
    <td>${bank.bank_name}</td>
    <td>${bank.account_alias}</td>
    <td>${bank.account_num_masked}</td>
    <td><a href="#" onclick="getBalance2('${bank.fintech_use_num}')">${bank.fintech_use_num}</a></td>
    </tr>
</c:forEach>
</table>
<div id="result">
</div>
<script>
function getBalance(fin){
	 $.ajax({
	   url : 'getBalance',
	   data : {fintech_use_num : fin},             
	   success: function(response) {
		  $("#result").html(response);
	   }	   
   });
}
function getBalance2(fin){
	 $.ajax({
 	   url : 'ajaxGetBalance',
 	   method : 'get',
 	   data : {fintech_use_num : fin},
 	   dataType: 'json',               
 	   success: function(response) {
 		  $("#result").html(response);
 		  $("#result").empty();
 		  $("#result").append("bankname:" + response.bank_name +"<br>")
 		  .append("product_name:"+response.product_name +"<br>")
 		  .append("balance_amt:"+response.balance_amt+"<br>")
 		  .append("available_amt:"+response.available_amt);
 	   }	   
    });
}
</script>
</body>
</html>