<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<c:set var="root" value="${pageContext.request.contextPath}"/>
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title>공지사항</title>
  <meta name="viewport" content="width=device-width, initial-scale=1">
  <link rel="stylesheet" href="https://maxcdn.bootstrapcdn.com/bootstrap/4.4.1/css/bootstrap.min.css">
  <script src="https://ajax.googleapis.com/ajax/libs/jquery/3.5.1/jquery.min.js"></script>
  <script src="https://cdnjs.cloudflare.com/ajax/libs/popper.js/1.16.0/umd/popper.min.js"></script>
  <script src="https://maxcdn.bootstrapcdn.com/bootstrap/4.4.1/js/bootstrap.min.js"></script>
  
<script type="text/javascript">
	function pageMove(pg) { 
		document.getElementById("pg").value=pg;
		document.getElementById("pageform").action = "${root}/notice.mdo";
		document.getElementById("pageform").submit();
	}
</script>
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

.pagination{
	max-width:1300px;
	width:100%;
	margin: 0;
	padding: 0;
	display: flex;
	flex-direction: row;
	list-style-type: none;
	align-item: center;
}

.page-item{
	padding: 15px;
	cursor: pointer;
}

.page-item a{
	text-align: center;
	text-decoration: none;
	color: Blue;
}

</style>
</head>
<body>
<div class="container">
	<form name="pageform" id="pageform" method="GET" action="">
		<input type="hidden" name="act" id="act" value="list">
		<input type="hidden" name="pg" id="pg" value="">
	</form>
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
		<c:if test="${manager == true}">
			<a href="${root }/write.mdo">새 글 작성</a>
			<br>
		</c:if>
		<TABLE class="table table-striped" BORDER=1 CELLSPACING=1 CELLPADDING = 1>
		<thead>
			<tr class="table-info">
			<td colspan="4" align="center"><h2>공지 사항</h2></td>
			</tr>
	   		<tr>
			<th width=100 >번호</th>
			<th width=600 >제목</th>
			<th width=200 >작성자</th>
			<th width=300 >작성 일자</th>
			</tr>
		</thead>
		<tbody>
		<c:forEach var="notice" items="${list }">
		  <tr>
			  <td align=center>&nbsp;<font size=2>${notice.id }</td>
		      <td align=center>&nbsp;<font size=2><a href="${root }/detail.mdo?id=${notice.id}">${notice.title }</a></td>       
		      <td align=center>&nbsp;<font size=2>${notice.managerName }</td>
		      <td align=center>&nbsp;<font size=2>${notice.regtime }</td>
	      </tr>
		</c:forEach>
		</tbody>
		</TABLE>
		${navigation.navigator }
		</div>
	</body>
</html>