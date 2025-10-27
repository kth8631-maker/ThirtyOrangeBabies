package com.kh.spring.ajax.controller;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;

@Controller
public class AjaxForwardController {
	
	@GetMapping("page")
	public String toAjax() {
		return "ajax/ajax";
	}
}
