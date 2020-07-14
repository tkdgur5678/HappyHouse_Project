<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title>수정 페이지</title>
<meta charset="utf-8">
  <meta name="viewport" content="width=device-width, initial-scale=1">
  <link rel="stylesheet" href="https://maxcdn.bootstrapcdn.com/bootstrap/4.4.1/css/bootstrap.min.css">
  <script src="https://ajax.googleapis.com/ajax/libs/jquery/3.4.1/jquery.min.js"></script>
  <script src="https://cdnjs.cloudflare.com/ajax/libs/popper.js/1.16.0/umd/popper.min.js"></script>
  <script src="https://maxcdn.bootstrapcdn.com/bootstrap/4.4.1/js/bootstrap.min.js"></script>
</head>
<body>
<div class="container" style="width:600px; border-radius: .5rem; padding: 20px; border: thick double #32a1ce;">
	<div class="center-block" style="padding: 10px">
		<h3>${ui.name}님의 프로필 정보</h3>
	</div>
	<div style="padding-left: 20px; padding-right: 20px;">
	<form action="userinfochangeProcess.udo" method="post" class="was-validated">
	  <div class="form-group">
	    <label for="name" style="font-weight: bold;">이 름</label>
	    <input type="text" class="form-control" id="name" value="${ui.name}" placeholder="Enter username" name="name" required>
	  </div>
	  <div class="form-group">
	    <label for="pass" style="font-weight: bold;">비밀번호</label>
	    <input type="text" class="form-control" id="pass" value="${ui.password}" placeholder="Enter password" name="pass" required>
	  </div>
	  <div class="form-group">
	    <label for="address" style="font-weight: bold;">주 소</label>
	    <input type="text" class="form-control" id="address" value="${ui.address}" placeholder="Enter address" name="address" required>
	  </div>
	  <div class="form-group">
	    <label for="phone" style="font-weight: bold;">전화번호</label>
	    <input type="text" class="form-control" id="phone" value="${ui.phone}" placeholder="Enter phonenumber" name="phone" required>
	  </div>
	  <div align="center"><button type="submit" class="btn btn-primary">등록</button></div>
	  <div align="center"><a href="main.udo">메인으로 돌아가기</a></div>
	</form>
	</div>
	
</div>
</body>
</html>