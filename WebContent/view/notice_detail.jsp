<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<c:set var="root" value="${pageContext.request.contextPath}"/>
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title>Insert title here</title>
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
	
	<TABLE class="table table-borderless" BORDER=2 CELLSPACING=2 CELLPADDING = 2>
		<thead>
			<tr class="table-info">
			<td colspan="4" align="center"><h2>${notice.title}</h2></td>
			</tr>
		</thead>
 		<TR>
			<TH WIDTH=200>번호</TH><TD width = 860 align=left>${notice.id}</TD>
		</TR> 
		<TR>
			<TH WIDTH=200>작성자</TH><TD width=860 align=left>${notice.managerName }</TD>
		</TR>
		<TR>
			<TH WIDTH=200>작성 날짜</TH><TD width=860 align=left>${notice.regtime }</TD>
		</TR>
		<TR>
			<TH WIDTH=200>CONTENT</TH>
			<TD COLSPAN=3><textarea readonly cols=120 rows=20>${notice.content }</textarea></TD>
		</TR> 		
	</TABLE><br>
	<c:if test="${manager == true}">
	<a href="delete.mdo?id=${notice.id}">삭제하기</a>&nbsp;&nbsp;&nbsp;
	<a href="mvmodify.mdo?id=${notice.id}">수정하기</a>&nbsp;&nbsp;&nbsp;
	</c:if>
	<br><a href="notice.mdo?pg=1">전체화면</a>&nbsp;&nbsp;&nbsp;	
	

</body>
</html>