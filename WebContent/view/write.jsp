<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<c:set var="root" value="${pageContext.request.contextPath}"/>
<c:if test="${manager == null}">
	<c:redirect url="/index.jsp"/>
</c:if>
<c:if test="${manager == true}">
<!DOCTYPE html>
<html lang="ko">
<head>
  <title>SSAFY-글작성</title>
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
  
  <meta charset="utf-8">
  <meta name="viewport" content="width=device-width, initial-scale=1">
  <link rel="stylesheet" href="https://maxcdn.bootstrapcdn.com/bootstrap/4.2.1/css/bootstrap.min.css">
  <script src="https://ajax.googleapis.com/ajax/libs/jquery/3.3.1/jquery.min.js"></script>
  <script src="https://cdnjs.cloudflare.com/ajax/libs/popper.js/1.14.6/umd/popper.min.js"></script>
  <script src="https://maxcdn.bootstrapcdn.com/bootstrap/4.2.1/js/bootstrap.min.js"></script>
  <script type="text/javascript">
  function writeNotice() {
	if(document.getElementById("title").value == "") {
		alert("제목 입력!!!!");
		return;
	} else if(document.getElementById("content").value == "") {
		alert("내용 입력!!!!");
		return;
	} else {
	  	document.getElementById("writeform").action = "${root}/writeProcess.mdo";
	  	document.getElementById("writeform").submit();
	}
  }
  </script>
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
<div class="container" align="center">
	<div class="col-lg-6" align="center">
		<h2>공지사항 작성</h2>
		<form id="writeform" method="post" action="">
		<input type="hidden" name="managerName" id="managerName" value="${uid }">
			<div class="form-group" align="left">
				<label for="subject">제목:</label>
				<input type="text" class="form-control" id="title" name="title">
			</div>
			<div class="form-group" align="left">
				<label for="content">내용:</label>
				<textarea class="form-control" rows="15" id="content" name="content"></textarea>
			</div>
			<button type="button" class="btn btn-primary" onclick="javascript:writeNotice();">글작성</button>
			<button type="reset" class="btn btn-warning">초기화</button>
		</form>
	</div>
</div>
</div>
</body>
</html>
</c:if>