<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title>식당 상세보기</title>
</head>
<body>
	<jsp:include page="../include/header.jsp" />
	<div class="innerOuter">
		<h1 id="title">여기다가 식당명</h1>
		<h1 id="description">여기다가 상세설명</h1>
		<br />
		<br />
		<p id=avail></p>
		<br />
		<br />
		<p id="address"></p>
		<br />
		<br /> <img id="img" src="" width="100%" height="240px" /> <br />
		<br />
		<div id="map" style="width: 100%; height: 400px"></div>

	</div>
	<div id="map"></div>

	<button class="btn btn-sm btn-secondary" >뒤로가기</button>
	<script>
		$(function(){
			$.ajax({
				url : `/spring/api/busan/detail/${num}`,
				success : response => {
					//console.log(response);
					
					
					const food = response.getFoodKr.item[0];
					document.getElementById("title").innerText=food.MAIN_TITLE;
					document.getElementById("description").innerText=food.ITEMCNTNTS;
					document.getElementById("avail").innerText=food.USAGE_DAY_WEEK_AND_TIME;
					document.getElementById("img").src=food.MAIN_IMG_NORMAL;
					document.getElementById("address").innerText=food.ADDR1;
				}
			})
		})
	</script>

	<jsp:include page="../include/footer.jsp" />

</body>
</html>