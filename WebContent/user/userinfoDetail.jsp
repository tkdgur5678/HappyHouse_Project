<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>

<!DOCTYPE html> 
<html>
<head>
<meta charset="UTF-8">
<title>상세 정보</title>
  <meta name="viewport" content="width=device-width, initial-scale=1">
  <link rel="stylesheet" href="https://maxcdn.bootstrapcdn.com/bootstrap/4.4.1/css/bootstrap.min.css">
  <script src="https://ajax.googleapis.com/ajax/libs/jquery/3.4.1/jquery.min.js"></script>
  <script src="https://cdnjs.cloudflare.com/ajax/libs/popper.js/1.16.0/umd/popper.min.js"></script>
  <script src="https://maxcdn.bootstrapcdn.com/bootstrap/4.4.1/js/bootstrap.min.js"></script>
  <script src="https://kit.fontawesome.com/7f19b41621.js" crossorigin="anonymous"></script>
</head>
<body>
<div class="container" style="width:600px; border-radius: .5rem; padding: 20px; border: thick double #32a1ce;">
	<div class="center-block" style="padding: 10px">
		<h3>${ui.name}님의 프로필 정보</h3>
	</div>
	<div style="padding-left: 20px; padding-right: 20px">
	<table class="table table-user-information">
      <tbody>
        <tr>
          <td>아이디:</td>
          <td>${ui.id}</td>
        </tr>                 
        <tr>
          <td>이  름:</td>
          <td>${ui.name}</td>
        </tr>
        </tr>
          <tr>
          <td>비밀번호:</td>
          <td>${ui.password}</td>
        </tr>
        <tr>
          <td>주소 :</td>
          <td>${ui.address}</td>
        </tr>
        <tr>
          <td>전화번호 :</td>
          <td>${ui.phone}</td>
        </tr>
      </tbody>
    </table>
	<div align="right">
        <span class="pull-right">
        	<a href="userinfoChange.udo" class="btn btn-sm btn-warning"><i class="far fa-edit"></i></a>
        	<a href="userdelete.udo" class="btn btn-sm btn-danger"><i class="fas fa-user-times"></i></a>
        </span>
    </div>
</div>
<div align="center"><a href="main.udo">메인으로 돌아가기</a></div>
</body>
</html>