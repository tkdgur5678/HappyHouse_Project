<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title>Insert title here</title>
  <meta name="viewport" content="width=device-width, initial-scale=1">
  <link rel="stylesheet" href="https://maxcdn.bootstrapcdn.com/bootstrap/4.4.1/css/bootstrap.min.css">
  <script src="https://ajax.googleapis.com/ajax/libs/jquery/3.4.1/jquery.min.js"></script>
  <script src="https://cdnjs.cloudflare.com/ajax/libs/popper.js/1.16.0/umd/popper.min.js"></script>
  <script src="https://maxcdn.bootstrapcdn.com/bootstrap/4.4.1/js/bootstrap.min.js"></script>
  <script src="https://kit.fontawesome.com/7f19b41621.js" crossorigin="anonymous"></script>
</head>
<body>
<!-- 로그인이 안됨 -->
<c:if test="${empty uid}">
	<c:if test="${loginfail == true}">
		아이디 또는 패스워드가 틀립니다.
	</c:if>
	<a href="loginForm.udo" class="btn btn-dark"><i class="fas fa-user"></i> 로그인</a>
</c:if>
<c:if test="${!empty uid}">
${uid} 님 환영합니다. <a href="logOut.udo" class="btn btn-dark"><i class="fas fa-user"></i> 로그아웃</a>
</c:if>
</body>
</html>