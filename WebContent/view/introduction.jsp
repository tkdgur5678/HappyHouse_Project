<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<c:set var="root" value="${pageContext.request.contextPath}"/>
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title>사이트 소개</title>
  <meta name="viewport" content="width=device-width, initial-scale=1">
  <link rel="stylesheet" href="https://maxcdn.bootstrapcdn.com/bootstrap/4.4.1/css/bootstrap.min.css">
  <script src="https://ajax.googleapis.com/ajax/libs/jquery/3.5.1/jquery.min.js"></script>
  <script src="https://cdnjs.cloudflare.com/ajax/libs/popper.js/1.16.0/umd/popper.min.js"></script>
  <script src="https://maxcdn.bootstrapcdn.com/bootstrap/4.4.1/js/bootstrap.min.js"></script>

<style type="text/css">

.nav-all{
	max-width:1300px;
	width:100%;
	margin: 0;
	padding: 0;
}

.nav-container{
	display: flex;
	flex-direction: row;
	background-color: black;
	list-style-type: none;
}

.nav-item{
	padding: 15px;
	cursor: pointer;
}

@media (min-width: 768px) {
  .container {
    width: 750px;
  }
}

@media (min-width: 992px) {
  .container {
    width: 940px;
  }
}

.nav-item a{
	text-align: center;
	text-decoration: none;
	color: white;
}

body{
	max-width:1300px;
	width:100%;
	background-image : url('${root}/img/houseimg2.jpg');
	background-repeat: no-repeat;
	background-size: cover;
}



ul, p, h1{
	color: buttontext;
}
</style>
</head>
<body>
<div class="container">
	<nav class="nav-all">
		<ul class="nav-container">
			<li class="nav-item"><a href="${root }/notice.mdo?pg=1">공지 사항</a></li>
			<li class="nav-item"><a href="${root }/list.ado?act=first">거래 정보 검색</a></li>
			<li class="nav-item"><a href="${root }/introduction.mdo">사이트 소개</a></li>
		</ul>
		<div align="right">
			<jsp:include page="/user/logincheck.jsp"/>
			<jsp:include page="/user/userinfocheck.jsp"/>
		</div>
	</nav>
	<br>
	<h1> 사이트 소개</h1>
	<ul>
		<li><h2>우리 사이트는 아파트와 다세대, 주택의 매매 및 전월세 정보를 제공합니다.</h2></li>
		<li><h2>사람과 집을 연결하고, 더 나은 삶의 방식을 만듭니다.</li>
		<li><h2>문의사항은 다음 번호로 연락해주세요. 010-1357-9753
	</ul>
</div>
</body>
</html>