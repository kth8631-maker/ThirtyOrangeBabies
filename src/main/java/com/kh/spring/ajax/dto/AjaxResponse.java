package com.kh.spring.ajax.dto;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@Builder
@NoArgsConstructor
@AllArgsConstructor
public class AjaxResponse {
	private String code;
	private String message;
	private Object data;
	
	
}
