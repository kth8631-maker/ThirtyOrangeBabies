<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title>광안리는 잘 갔네</title>
<style>
	.food:hover{
	cursor:pointer;
	box-shadow: 1px 1px 1px 1px;
	}

</style>
</head>
<body>
	<jsp:include page="../include/header.jsp"/>
		
		
		<div class="innerOuter">
		<div id=result>
		</div>
			
			
			
			
		
		<hr />
		<div style="width : 300px; height: 120px; margin:auto;">
			<button class="btn btn-sm" style="background:darkblue; width: 100%;height :100%; border:none; boarder-radius:15px; color:white; font-weight:800;">더보기 ▽</button>
			
		
		</div>
		</div>
		<script>
			pageNo=1;
			
			$(function() {
			    getBusans();
			  });
			function detail(num){
				location.href=`busan/forward/\${num}`;
			}

			  function getBusans() {
			    $.ajax({
			      url: `/spring/api/busan`,
			      data: { pageNo: pageNo },
			      success: response => {
			        pageNo++;
			        const foods = response.getFoodKr.item;
			        console.log(foods);

			        const result = foods.map(e => `
			          <div onclick="detail(\${e.UC_SEQ})"
			        		  style="width:250px; height:auto; display:inline-block; padding:15px">
			            <img src="\${e.MAIN_IMG_THUMB}" width="100%" height="30%" /><br />
			            <h5>\${e.MAIN_TITLE}</h5>
			            <p>\${e.GUGUN_NM}</p>
			            <p>\${e.CNTCT_TEL}</p>
			          </div>
			        `).join('');

			        document.getElementById('result').innerHTML += result;
			      }
			    });
			  }
			
			
			
			
		</script>
	
	
	
	<jsp:include page="../include/footer.jsp"/>

</body>
</html>