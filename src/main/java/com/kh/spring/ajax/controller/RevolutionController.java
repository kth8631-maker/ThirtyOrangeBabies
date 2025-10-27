package com.kh.spring.ajax.controller;

import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.kh.spring.ajax.dto.AjaxResponse;
import com.kh.spring.board.model.dto.BoardDTO;
import com.kh.spring.board.model.dto.ReplyDTO;

@RestController //@RestController가 있으면 @ResponseBody 생략 가능 @RestContoller == @Controller + @ResponseBody
@RequestMapping(value="revol", produces="application/json; charset=UTF-8")
public class RevolutionController {
	
	/*
	 * REST(REpresentational State Transfer)
	 * 
	 * HTTP프로토콜을 화래용한 아키텍처 스타일 중 하나 ==> 제일잘나감
	 * 
	 * 자원(Resource)중심의 URL구조 + 상태없음(Stateless) 통신
	 * 
	 * GET		/boards				=> 게시글 목록 조회
	 * GET		/boards/19			=> 게시글들 중 19번 게시글 조회
	 * GET		/boards/photo/19	=> 게시글들 중 사진 게시글들 중 19번 게시글 조회
	 * POST		/boards				=> 새 게시글 생성
	 * PUT		/boards/19			=> 게시글 전체 수정
	 * PATCH	/boards/19			=> 게시글 부분 수정
	 * DELETE	/boards/10			=> 19번 게시글 삭제
	 * 
	 * HTTP 상태코드 활용
	 * 
	 * 200 OK						=> 요청이 성공적으로 잘 이루어졌음(GET, DELETE)
	 * 201 Create					=> 요청에 의해 데이터가 잘 만들어짐(POST, PUT, PATCH)
	 * 400 Bad Request				=> 잘못된 요청
	 * 401 Unauthorized				=> 인증 실패
	 * 404 Not Found				=> 없음
	 * 500 Internal Error			=> 서버 터짐
	 *  
	 */
	
	@GetMapping("/a")
	public BoardDTO a() {
		BoardDTO a = new BoardDTO();
		a.setBoardTitle("a임");
		return a;
		
	}
	
	@GetMapping("/b")
	public BoardDTO b() {
		BoardDTO b = new BoardDTO();
		b.setBoardTitle("b임");
		return b;
		
	}
	@PostMapping("/c")
	public AjaxResponse c() {
		String str = "어허헣헣";
		AjaxResponse ar = new AjaxResponse();
		ar.setCode("201");
		ar.setData(str);
		ar.setMessage("데이터생성에 성공!");
		return ar;
	}
	
	@GetMapping("/d")
	public AjaxResponse d() {
		ReplyDTO reply= new ReplyDTO();
		AjaxResponse ar = new AjaxResponse();
		ar.setCode("200");
		ar.setData(reply);
		ar.setMessage("조회에 성공");
		return ar;
		
	}
	
}
