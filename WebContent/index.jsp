<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<c:set var="root" value="${pageContext.request.contextPath}"/>
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title>Happy House</title>
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
	<form name="pageform" id="pageform" method="GET" action="">
		<input type="hidden" name="pg" id="pg" value="">
	</form>
	<div class="container">
	<nav class="nav-all">
		<ul class="nav-container">
			<li class="nav-item"><a href="${root }/notice.mdo?pg=1">공지 사항</a></li>
			<li class="nav-item"><a href="${root }/list.ado">거래 정보 검색</a></li>
			<li class="nav-item"><a href="${root }/introduction.mdo">사이트 소개</a></li>
		</ul>
		<div align="right">
			<jsp:include page="/user/logincheck.jsp"/>
			<jsp:include page="/user/userinfocheck.jsp"/>
		</div>
	</nav>
	</div>
	<br>
	<c:if test="${msg != null }">
		<script type="text/javascript">
 			 alert("${msg}");
		</script>
	</c:if>

	<div class="container">
		<div class="form-group form-check-inline">
			<label class="form-check-label">
				<input	class="form-check-input" type="checkbox" id='deal' name="aptdeal" value="aptdeal" checked> 아파트 매매 
			</label>
			<label class="form-check-label">
				<input	class="form-check-input" type="checkbox" id='deal' name="aptrent" value="aptrent"> 아파트 전월세 
			</label>     
			<label class="form-check-label">
				<input	class="form-check-input" type="checkbox" id='deal' name="housedeal" value="housedeal"> 다세대,주택 매매 
			</label>
			<label class="form-check-label">
				<input	class="form-check-input" type="checkbox" id='deal' name="houserent" value="houserent"> 다세대,주택 전월세 
			</label>
		</div>
		
		<select class="form-control" style="width: 200px;" name="key" id="key">
			<option value="all">-------전체-------</option>
			<option value="dong">동 이름</option>
			<option value="name">아파트 이름</option>
		</select> 
		
		<input class="form-control" style="width: 600px" type="text" id="word" name="word" /> 
		</div>	
	<br>
	
	<script>
	function searchkmp(){	 
	    // name이 같은 체크박스의 값들을 배열에 담는다.
	    var checkboxValues = new Array();
	    $("input[id='deal']:checked").each(function() {
	    	console.log($(this).val());
	        checkboxValues.push($(this).val());
	    });
	    
	    if($("#word").val() == ''){
	    	console.log("stop");
	    }
	    var allData = {checkArray:checkboxValues,word:$("#word").val(),key:$("#key").val()};
	     
	    $.ajax({
	        url:"${pageContext.request.contextPath}/list.ado",
	        type:'GET',
	        dataType:'json',
	        data: allData,	        
	        success:function(data){
	            $("tbody").empty();
	            $.each(data, function(index, dto){
					let str = "<tr>"
					+"<td align=center>"+ dto.no + "</td>"
					+"<td align=center>"+ dto.dong + "</td>"
					+"<td align=center><a href=detail.ado?no="+dto.no+">"+ dto.aptname + "</td>"
					+"<td align=center>"+ dto.amount + "</td>"
					+"<td align=center>"+ dto.type + "</td></tr>";
					$("tbody").append(str);
				});//each <a href=detail.ado?no="+dto.no+">
	        },
	        error:function(jqXHR, textStatus, errorThrown){
	            alert("에러 \n" + textStatus + " : " + errorThrown);
	        }
	    });
	}
	
		$(document).ready(function(){
			var timerid;
			$("#word").on("input", function(e) {
			  var value = $(this).val();
			  if ($(this).data("lastval") != value && value != '') {

			    $(this).data("lastval", value);
			    clearTimeout(timerid);

			    timerid = setTimeout(function() {
			    searchkmp();
			      console.log(value);
			    }, 500);			    
			  };
			});//input

		});//ready
		
		
	</script>
	
	<div class="container">
  		<table class="table table-hover" BORDER=1 CELLSPACING=1 CELLPADDING = 1>
    		<thead>
    		<tr class="table-warning">
				<td colspan="5" align="center"><h2>거래 내역</h2></td>
			</tr>
      		<tr class="table-info">
        		<th width=100 align=center>번호</th>
				<th width=300 align=center>동</th>
				<th width=300 align=center>아파트이름</th>
				<th width=300 align=center>거래금액</th>
				<th width=300 align=center>거래종류</th>
      		</tr>
    		</thead>
    	<tbody>

    	</tbody>
  	</table>
	</div>	
</body>
</html>