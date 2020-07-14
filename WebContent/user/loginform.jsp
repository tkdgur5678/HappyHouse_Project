<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title>Login</title>
<meta charset="utf-8">
  <meta name="viewport" content="width=device-width, initial-scale=1">
  <link rel="stylesheet" href="https://maxcdn.bootstrapcdn.com/bootstrap/4.4.1/css/bootstrap.min.css">
  <script src="https://ajax.googleapis.com/ajax/libs/jquery/3.4.1/jquery.min.js"></script>
  <script src="https://cdnjs.cloudflare.com/ajax/libs/popper.js/1.16.0/umd/popper.min.js"></script>
  <script src="https://maxcdn.bootstrapcdn.com/bootstrap/4.4.1/js/bootstrap.min.js"></script>
</head>
<body>
<div class="container" style="width:600px; border-radius: .5rem; padding: 20px; border: thick double #32a1ce;">
	<div class="center-block">
		<h1>로그인</h1>
	</div>
	<div>
	<!-- <form cl
	ass="form-inline" action="loginProcess.udo" method="post">  -->
	<form action="loginProcess.udo" method="post">
	  <!--  <label for="uid" class="mr-sm-2">ID :</label> -->
	  <div><input type="text" class="form-control mb-2 mr-sm-2" placeholder="Enter id" id="uid" name="uid"></div>
	  <!--  <label for="pass" class="mr-sm-2">PASS :</label> -->
	  <div><input type="password" class="form-control mb-2 mr-sm-2" placeholder="Enter password" id="pass" name="pass"></div>
	  <div align="right">
	  <div class="form-check mb-2 mr-sm-2">
	    <label class="form-check-label">
	      <input class="form-check-input" type="checkbox"> 아이디 저장<br>
	    </label>
	  </div>
	  <button type="submit" class="btn btn-primary">로그인</button>
	  </div>
	</form>
	</div>
	<div align="center">
		<a href="findPass.udo">비밀번호 찾기</a>
		<a href="main.udo">메인으로 돌아가기</a>
	</div>
</div>
</body>
</html>