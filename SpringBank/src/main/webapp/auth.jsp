<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title>Insert title here</title>
</head>
<body>
<a href="auth">사용자인증</a>
<a href="callback?code=">토큰발급</a>
<a href="userinfo?access_token=eyJhbGciOiJIUzI1NiIsInR5cCI6IkpXVCJ9.eyJhdWQiOiIxMTAwNzcwNTIxIiwic2NvcGUiOlsiaW5xdWlyeSIsImxvZ2luIiwidHJhbnNmZXIiXSwiaXNzIjoiaHR0cHM6Ly93d3cub3BlbmJhbmtpbmcub3Iua3IiLCJleHAiOjE2MjMxMzc5MDEsImp0aSI6IjFjMDBhYTFjLWQzMjgtNDZkZC1iYmQyLTdkMDFjYTkzNzA2OSJ9.RmkYC1HpuDlTAK-49cZ2edk9ERaBjqmTcaCauGGw8yQ">사용자정보</a>
<div>
	access_token:${access_token}
</div>
</body>
</html>