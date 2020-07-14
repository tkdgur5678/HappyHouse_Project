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
<c:if test="${empty uid}">
	<a href="signupForm.udo" class="btn btn-dark"><i class="fas fa-lock"></i> 회원가입</a><br>
</c:if>
<c:if test="${!empty uid}">
	<a href="userinfoData.udo" class="btn btn-dark"><i class="fas fa-lock"></i> 회원정보</a><br>
</c:if>
</body>
</html>