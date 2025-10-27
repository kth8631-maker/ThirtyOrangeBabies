package com.kh.spring.board.controller;

import java.util.Map;

import javax.servlet.http.HttpSession;

import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.multipart.MultipartFile;

import com.kh.spring.board.model.dto.BoardDTO;
import com.kh.spring.board.model.service.BoardService;

import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;

@Slf4j
@Controller
@RequestMapping("boards")
@RequiredArgsConstructor
public class BoardController {
	
	/*
	 * mapping
	 * 
	 * SELECT == GET / INSERT == POST
	 * 
	 * 전체조회 		  == boards					== GET
	 * 상세조회(단일조회) == boards/{boardNo}
	 * 작성 			  == boards					== POST
	 */
	
	private final BoardService boardService;
	
	@DeleteMapping
	public void a() {};
	
	@PutMapping // @FetchMapping
	public void b() {};
	
	@GetMapping
	public String findAll(@RequestParam(name="page", defaultValue="1") Long page
						 ,Model model) {
		// log.info("앞에서 넘어온 페이지 값 : {}", page);
		// 페이징처리
		// 게시글 몇개야
		// 한페이지에 몇개 보여주지?
		// 버튼은 몇개보여주지?
		Map<String, Object> map = boardService.findAll(page);
		model.addAttribute("map", map);
		
		return "board/list";
	}
	
	@GetMapping("/form")
	public String toForm() {
		
		
		return "board/form";
	}
	
	@PostMapping
	public String save(BoardDTO board, MultipartFile upfile
					  ,HttpSession session){
		log.info("게시글 정보? {}, 파일 정보 : {} ", board, upfile);
		// 첨부파일의 존재유무
		// MultipartFile객체의 fileName필드값으로 확인해야함
		
		// INSERT INTO BOARD
		// VALUES (#{boardTitle}, #{boardContent}, #{originName}, #{changeName})
		
		// 1. 권한있는 요청인가
		// 2. 파일 존재유무 체크 => 이름바꾸기 작업(파일 확장자 체크) => 업로드 
		// 3. 값들이 유효성 있는 값인가
		// 4. 바뀐이름을 changeName필드에 담아서 Mapper로 보내기
		// 
		boardService.save(board, upfile, session);
		
		return "redirect:boards";
	}
	
	@GetMapping("/{id}")
	public String toDetail(@PathVariable(name="id") Long boardNo,
													Model model) {
		
		log.info("게시글 번호 : {}", boardNo);
		// 조회수 증가
		// 조회수 증가에 성공했다면 SELECT로 조회
		// 만약에 없는 게시글 번호라면 예외발생
		BoardDTO board = boardService.findByBoardNo(boardNo);
		model.addAttribute("board", board);
		return "board/detail";
	}
	
}
