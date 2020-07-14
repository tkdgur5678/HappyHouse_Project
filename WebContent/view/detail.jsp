<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<c:set var="root" value="${pageContext.request.contextPath}"/>
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title>상세 정보</title>
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

.nav-item{
	padding: 15px;
	cursor: pointer;
}

.nav-item a{
	text-align: center;
	text-decoration: none;
	color: white;
}

</style>
</head>
<script>
$(document).ready(function(){
	$("img").click(function(){
		alert("이미지");
	});

});//ready
</script>
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

	<div id="wrap">
		<div id="header" style= "padding-left:25%">
			<h2>아파트 거래 정보</h2>
			<br><br>
		</div>
	
  	<button type="button" class="btn btn-success" data-toggle="modal" data-target="#myModal">
    	Open img
  	</button>

 	 <!-- The Modal -->
  	<div class="modal" id="myModal">
    	<div class="modal-dialog">
      	<div class="modal-content">
      
        <!-- Modal Header -->
        <div class="modal-header">
          <h4 class="modal-title">${houseDeal.aptName }</h4>
          <button type="button" class="close" data-dismiss="modal">&times;</button>
        </div>
        
        <!-- Modal body -->
        <div class="modal-body">
          <img src="img/${imgPath }" style="width: 100%"/>
        </div>
        
        <!-- Modal footer -->
        <div class="modal-footer">
          <button type="button" class="btn btn-danger" data-dismiss="modal">Close</button>
        </div>
        
      </div>
    </div>
  </div>

	
	
	<div id="content" style="float: right; width:70%">
		<table class="table table-dark" style="font-weight: bold;">
			<tr>
				<td width="200" height="80">주택명</td>
				<td width="300" height="80">${houseDeal.aptName }</td>
			</tr>
			<tr>
				<td width="200" height="80">거래금액</td>
				<td width="300" height="80">${houseDeal.dealAmount }</td>
			</tr>
			<tr>
				<td width="200" height="80">월세금액</td>
				<td width="300" height="80">없음</td>
			</tr>
			<tr>
				<td width="200" height="80">건축연도</td>
				<td width="300" height="80">${houseDeal.buildYear }</td>
			</tr>
			<tr>
				<td width="200" height="80">전용면적</td>
				<td width="300" height="80">${houseDeal.area }</td>
			</tr>
			<tr>
				<td width="200" height="80">거래일</td>
				<td width="300" height="80">${houseDeal.dealDay }</td>
			</tr>
			<tr>
				<td width="200" height="80">법정동</td>
				<td width="300" height="80">${houseDeal.dong }</td>
			</tr>
			<tr>
				<td width="200" height="80">지번</td>
				<td width="300" height="80">${houseDeal.jibun }</td>
			</tr>
		</table>
	</div>
</div>
</div>
</body>

</html>