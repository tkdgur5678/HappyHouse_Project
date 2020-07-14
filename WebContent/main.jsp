<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<c:set var="root" value="${pageContext.request.contextPath}"/>
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title>Happy House</title>


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
	padding: 30px;
	cursor: pointer;
}

.nav-item a{
	text-align: center;
	text-decoration: none;
	color: white;
}

.main{
	max-width:1300px;
	width:100%;
	margin: 0;
	padding: 0;
}

.sub_parent{
	display: flex;
	max-width:1300px;
	width:100%;
	height: 500px;
}

.sub_child1{
	flex: 3;
}
.sub_child2{
	flex: 2;
}

.footer{
	max-width:1300px;
	width:100%;
	margin: 0;
	padding: 0;
	background-color: black;
}

.notice {
	width: 100%;
	margin: 0 auto;
	box-sizing: border-box;
}

.notice h1 {
	font-size: 25px;
	text-align: center;
	margin: 10px 180px;
	padding: 10px;
	color: #fff;
	background: #007AAE;
	border-radius: 30px;
}

.notice ul{
	width: 100%;
}

.notice ul li:first-child {
	border-top: 2px solid #6a6a6a;
	border-bottom: 2px solid #6a6a6a;
	padding-left: 150px;
}

.notice ul li:last-child {
	border-bottom: 1px solid #000;'
}

.notice ul li:nth-child(2n) {
	background: #94D8F6;
	border-radius: 20px;
}

.notice ul li span {
	display: inline-block;
	float: right;
	width: 150px;
	text-align: center;
}

</style>
</head>
<body>
	<form name="pageform" id="pageform" method="GET" action="">
		<input type="hidden" name="act" id="act" value="first">
		<input type="hidden" name="pg" id="pg" value="">
	</form>
	<nav class="nav-all">
		<ul class="nav-container">
			<li><span class="navbar-brand" style="font-size: 3em; color: Tomato;"><i class="fas fa-house-user"></i></span></li>
			<li class="nav-item"><a href="${root }/notice.mdo?pg=1">공지 사항</a></li>
			<li class="nav-item"><a href="${root }/list.ado?act=first">거래 정보 검색</a></li>
			<li class="nav-item"><a href="${root }/introduction.mdo">사이트 소개</a></li>
		</ul>
		<div align="right">
			<jsp:include page="/user/logincheck.jsp"/>
			<jsp:include page="/user/userinfocheck.jsp"/>
			<hr>
		</div>
	</nav>
	<br>
	<div class="main">
		<!-- map start -->
		<div id="map"" style="width: 80%; height: 500px; margin-left: 10%;"></div>
		<script src="https://unpkg.com/@google/markerclustererplus@4.0.1/dist/markerclustererplus.min.js"></script>
		<script async defer src="https://maps.googleapis.com/maps/api/js?key=AIzaSyC3Jh6Rt72qHXe5GomCfP_4LAuHjs_sr0U&callback=initMap"></script>
		<script>
			var multi = {lat: 37.5665734, lng: 126.978179};
			var map;
			function initMap() {
				map = new google.maps.Map(document.getElementById('map'), {
					center: multi, zoom: 12
				});
				var marker = new google.maps.Marker({position: multi, map: map});
			}
			function addMarker(tmpLat, tmpLng, aptName) {
				var marker = new google.maps.Marker({
					position: new google.maps.LatLng(parseFloat(tmpLat),parseFloat(tmpLng)),
					label: aptName,
					title: aptName
				});
				marker.addListener('click', function() {
					map.setZoom(17);
					map.setCenter(marker.getPosition());
					callHouseDealInfo();
				});
				marker.setMap(map);
			}
		</script>
		<!-- map end -->
	<br><hr>
	</div>
	<div class="sub_parent">
			<div class="sub_child1 notice" style="margin: 50px; ">
			<h2>공지사항</h2>
			<ul>
				<li>내용<span>날짜</span></li>
				<li>내용><span>날짜</span></li>
				<li>내용><span>날짜</span></li>
				<li>내용><span>날짜</span></li>
				<li>내용><span>날짜</span></li>
				<li>내용><span>날짜</span></li>
				<li>내용><span>날짜</span></li>
				<li>내용><span>날짜</span></li>
				<li>내용><span>날짜</span></li>
				<li>내용><span>날짜</span></li>
				<li>내용><span>날짜</span></li>
			</ul>
		    </div>
		    <div class="sub_child2" style="border: 2px solid gray; margin: 50px;">
		    <h2>게시판</h2>
		    </div>
	</div>
	<hr>
	<footer class="footer">
        <div id="serviceNameArea">
            <a href="/">
                <h2>HAPPY HOUSE</h2>
            </a>
        </div>
        <ul>
            <li>회사소개</li>
            <li>개인정보보호정책</li>
            <li>위치</li>
            <li>SSAFY</a></li>
        </ul>
        <address>주소: 서울특별시 강남구 역삼동 테헤란로 212</address>
    </footer>
</body>
</html>