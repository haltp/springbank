<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>
<%@ page contentType="text/html; charset=UTF-8" %>
<html>
<head>
	<title>Home</title>
</head>
<body>
<h1>
	Hello world!  
</h1>

<P>  The time on the server is ${serverTime}. </P>

<a href="getAuthorizeAccount">사용자인증/계좌등록</a></br>
<a href="getOrgAuthorize">기관인증</a></br>
<a href="getAccountList">전체계좌조회</a></br>
<a href="getBiz">크롤링</a></br>
<a href="pdf/empList">emplist pdf</a></br>
<a href="pdf/empList2?dept=50">emplist2 pdf</a></br>
</body>
</html>
