package com.kh.spring.board.model.dto;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import lombok.ToString;

@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
@ToString
public class ReplyDTO {
	
	private Long replyNo;
	private Long refBno;
	private String replyContent;
	private String replyWriter;
	private String createDate;
	private String status;
	
}
