<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title>비밀번호 변경</title>
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
		<h1>비밀번호 찾기</h1>
	</div>
	<div style="padding-left: 20px; padding-right: 20px;">
	<form action="chagepassProcess.udo" method="post" class="was-validated">
	  <div class="form-group">
	    <label for="pass1" style="font-weight: bold;">새 비밀번호 입력: </label>
	    <input type="text" class="form-control" id="pass1" placeholder="Enter new password" name="pass1" required>
	    <div class="valid-feedback">Valid.</div>
	    <div class="invalid-feedback">필수 입력값입니다.</div>
	  </div>
	  <div class="form-group">
	    <label for="pass2" style="font-weight: bold;">비밀번호 확인: </label>
	    <input type="text" class="form-control" id="pass2" placeholder="Enter new password" name="pass2" required>
	    <div class="valid-feedback">Valid.</div>
	    <div class="invalid-feedback">필수 입력값입니다.</div>
	  </div>
	  <div align="center"><button type="submit" class="btn btn-primary">전송</button></div>
	  <div align="center"><a href="logOut.udo">메인으로 돌아가기</a></div>
	</form>
	</div>
</div>
</body>
</html>