<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title>Insert title here</title>
</head>
<body>

	<h1>AJAX</h1>
	<jsp:include page="../include/header.jsp"></jsp:include>
	<div class="innerOuter">
	<pre>
		웹페이지 전체를 새로고침하지않고
		서버와 비동기 통신을 하여 화면을 갱신할 수 있는 기술
		* 핵심!! => 비동기통신 => 부분 갱신 => 사용자 경험 향상 => 트래픽 줄어든다.
		---------------------------------------------------------------
		
		전송 데이터 형식 => 과거에는 XML 사용 현재 JSON
		AJAX 구현 API == XMLHttpRequest => modern Fetch API
		                               => ajax() => jQuery
		                               => axios() => React
		-------------------------------------------------------------------
		전체적인 흐름
		1. 클라이언트가 요청 보냄(JS로 보냄)
		2. 서버는 요청 처리 후 데이터 응답(문자열 => JSON)
		3. 요소개체를 갱신
		- jQuery로 ajax요청 시 주요 속성
		
		- url : 요청할 URL(필수)
		- data : 요청 시 전달값({ 키 : 밸류})
		- type : 요청 전송방식(get/post/put/delete)
		         GET방식 : 조회 요청(SELECT)
		         POST방식 : 데이터 생성 요청(INSERT)
		         PUT방식 : 데이터 갱신 요청(UPDATE)
		         DELETE방식 : 데이터 삭제요청(DELETE) 
		         success : AJAX통신 성공시 실행할 함수를 정의
	</pre>
	<h3>1. 버튼 클릭해서 GET방식으로 요청보내서 데이터 받아서 화면에 출력!</h3>
	<div class="form-group">
		<div class="form-control">
			입력 : <input type="text" id="ajax-input">
		</div>
		<div class="form-control">
			<button class="btn btn-sm btn-success"
			id="ajax-btn" onclick="test1();">AJAX로 요청보내기</button>
		</div>
	</div>
	응답 : <label id="result">현재응답 없음</label>
	
	<!-- 계획:
		인풋요소에 아무거나 쓰고 요청보내기 버튼을 누르면 
		AJAX요청을 보내서
		요청을 받아서 처리해주는 RequestHandler가 값을 받아서 응답을 해주고
		받은응답데이터를 라벨 요소 Content영역에 출력할것
	 -->
	 
	 <script>
	 function test1(){
		 $.ajax({
			 url : "test",
			 type : "get",
			 data : { "input" : $("#ajax-input").val() },
			 success : function(response){
				 console.log(response);
				 
				 document.getElementById('result').innerHTML = response;
			 }
		 });
		 
		 
	 }
	 
	 
	 
	 </script>
	</div>
	
	
	<hr>
	
	<h3>2. 댓글작성하기</h3>
	<!-- 
		계획 : 글번호를 입력받고, 댓글 내용을 입력받은 뒤
		      버튼을 클릭하면 AJAX요청을 보내 Reply테이블에 한 행 INSERT
	 -->
	 <div class="form-group">
	 	<div class="form-control">
	 		글 번호 : <input type="text" id="num" />
	 	</div>
	 	<div class="form-control">
	 		댓글 내용 : <input type="text" id="reply-content"/>
	 	</div>
	 	<div class="form-control">
	 		<button onclick="insert();" class="btn btn-sm btn-info">댓글작성하기</button>
	 	</div>
	 </div>
	 <script >
	 	function insert(){
	 		const boardNo = document.getElementById("num").value;
	 		const replyContent = document.getElementById("reply-content").value;
	 		
	 		$.ajax({
	 			url: 'replies',
	 			type : 'post',
	 			data :{
	 				refBno : boardNo,
	 				replyConetent : replyContent
	 			},
	 			success : function(response){
	 				console.log(response);
	 				if(response ==='success'){
	 					
	 				} else {
	 					alert("댓글 작성 실패!");
	 				}
	 				document.getElementById("num").value ='';
	 				document.getElementById("reply-content").value ='';
	 			}
	 		});
	 		
	 	}
	 </script>
	 
	 <hr>
	 
	 <h4> 자 재밌는거. AJAX요청으로 게시글 상세조회 해보기</h4>
	 <div>
	 	<h3>게시글 자세히 보기</h3>
	 	제목 : <p id="title"></p>
	 	작성자 : <p id="writer"></p>
	 	내용 : <p id="content"></p>
	 	작성일 : <p id="date"></p>
	 	<hr>
	 	<img id="board-img" />
	 	<hr>
	 	<div id="reply-area">
	 	</div>
	 </div>
	 <br>
	 게시글 번호를 입력하세요 : <input type="text" id="detail" />
	 <button onclick="detail();">게시글보여주세용~</button>
	 
	 <script>
	 	function detail(){
	 		const num = document.getElementById('detail').value;
	 		$.ajax({
	 			url : `board/\${num}`,
	 			type : 'get',
	 			success : result => {
	 				console.log(result);
	 				// 응답 데이터를 화면에 출력
	 				document.querySelector("#title").innerText = result.boardTitle;
	 				document.querySelector("#writer").innerText = result.boardWriter;
	 				document.querySelector("#content").innerText = result.boardContent;
	 				document.querySelector("#date").innerText = result.createDate;
	 				
	 				const imgEl = document.querySelector("#board-img");
	 				imgEl.src = result.changeName != undefined ? result.changeName : '';
	 				
	 				const replies = result.replies;
	 				
	 				const el = replies.map(e=>{
	 					return(
	 							`<div>
	 							    <label style"330px">댓글작성자 : \${e.replyWriter}</label>
	 							<label style"400px">댓글 내용 : \${e.replyContent}</label>
	 							<label style"150px">작성일 : \${e.createDate}</label>
	 							</div>`)
	 				}).join('');
	 				
	 				document.querySelector("#reply-area").innerHTML = el;
	 			}
	 		});
	 	}
	 </script>
	 
	 <hr>
	 <button class="btn btn-lg btn-primary" onclick="btn1();">버튼1</button>
	 <button class="btn btn-lg btn-primary" onclick="btn2();">버튼2</button>
	 <script>
	 	function btn1(){
	 		$.ajax({
	 			url : 'revol/c', 
	 			type : 'post',
	 			success : res=>{
	 				//alert(res.boardTitle);
	 				console.log(res);
	 			}
	 		})
	 	}
	 	function btn2(){
	 		$.ajax({
	 			url : 'revol/d', 
	 			type : 'get',
	 			success : res=>{
	 				//alert(res.boardTitle);
	 				console.log(res);
	 			}
	 		})
	 	}
	 </script>
	 
	
	<jsp:include page="../include/footer.jsp"></jsp:include>
</body>
</html>