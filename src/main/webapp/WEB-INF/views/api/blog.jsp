<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title>블로그 검색하기</title>
</head>
<body>
	<jsp:include page="../include/header.jsp" />
	<div class="innerOuter">
		<div class="form-control">
			<input type="text" id="query" style="width:100%; height:100%"/>
		</div>
		<br>
		<button class="btn btn-lg btn-danger" onclick="search()">쇼핑 검색하기</button>
		<hr style="margin:25px;">
		<div id="search-result">
		</div>
	
	</div>
	<script>
		function search(){
			const keyword = document.getElementById('query').value;
			
			$.ajax({
				url : `api/blog?query=\${keyword}`,
				success : result =>{
					//console.log(result)
					const items = result.items;
					//console.log(items)
					/*
					const el = items.map(e => `
							            		<div style="margin-bottom:56px;">
													<h5>\${e.title}</h5>
													<br />
													<p>\${e.description}</p>
													<br>
													<a href="\${e.link}" target="_blank">이동</a>
					 							</div>`).join('');
					document.querySelector('#search-result').innerHTML=el
					*/
					
					const el = items.map( e => `
							     				<div style="width:200px; height: 450px; padding: 14px; display:inline-block">
							     				<h5>\${e.title}</h5>
							     				<br/>
							     				<img width="200" height="130" src="\${e.image}" />
							     				<br/>
							     				<a href="\${e.link}" target="_blank">보러가기</a>
												</div>`).join('');
					document.querySelector('#search-result').innerHTML=el;
					
				}
			});
		}
	</script>
	
	
	<jsp:include page="../include/footer.jsp" />

</body>
</html>