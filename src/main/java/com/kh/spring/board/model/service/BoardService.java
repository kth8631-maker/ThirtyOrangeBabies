package com.kh.spring.board.model.service;

import java.util.Map;

import javax.servlet.http.HttpSession;

import org.springframework.web.multipart.MultipartFile;

import com.kh.spring.board.model.dto.BoardDTO;
import com.kh.spring.board.model.dto.ReplyDTO;

public interface BoardService {
	
	// 게시글 목록 조회 + 페이징처리
	// 총 게시글 수 조회
	// int selectListCount();
	// 목록 조회
	/*
	 * selectBoardList();
	 * findAll();
	 * selectAll();
	 */
	Map<String, Object> findAll(Long boardNo);
	
	// 게시글 작성
	/*
	 * insertBoard();
	 * save();
	 */
	int save(BoardDTO board, MultipartFile upfile, HttpSession session);
	
	// 게시글 상세보기(조회수 증가)
	/*
	 * selectBoard();
	 * findByXXXX();
	 */
	BoardDTO findByBoardNo(Long boardNo);
	
	// 게시글 삭제하기
	/*
	 * deleteByXXXX();
	 */
	int deleteBoardNo(Long boardNo);
	
	// 게시글 수정하기
	/*
	 * updateBoard();
	 * updateByXXXX();
	 */
	int update(BoardDTO board);
	
	//---------------------------------------------
	// 댓글 서비스
	
    int insertReply(ReplyDTO reply, HttpSession session);
	
	
	
}
