<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri = "http://java.sun.com/jsp/jstl/core" %>
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title>Insert title here</title>
</head>
<body>
번호 : ${board.seq}<br/>
작성자 : ${board.writer}<br/>
제목 : ${board.title}<br/>
내용 : ${board.content}<br/>
첨부파일 단건: <a href="fileDown?seq=${board.seq}">${board.filename}</a><br/>
첨부파일 다건: <c:forTokens items="${board.filename}" delims="," var="file">
<a href="fileNameDown?filename=${file}">${file}</a><br/>
</c:forTokens>
</body>
</html>