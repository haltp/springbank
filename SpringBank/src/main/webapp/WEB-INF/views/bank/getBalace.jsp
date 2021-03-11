<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title>Insert title here</title>
</head>
<body>
<h3>잔액조회</h3>
은행: ${balance.bank_name}
계좌명: ${balance.product_name }
잔액: ${balance["balance_amt"]}
</body>
</html>