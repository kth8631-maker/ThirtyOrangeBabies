package com.kh.spring.board.model.dto;

import java.util.List;

import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import lombok.ToString;

@Getter
@Setter
@NoArgsConstructor
@ToString
public class BoardDTO {
	private Long boardNo;
	private String boardTitle;
	private String boardContent;
	private String boardWriter;
	private String originName;
	private String changeName;
	private int count;
	private String createDate;
	private String status;
	private List<ReplyDTO> replies;
}
