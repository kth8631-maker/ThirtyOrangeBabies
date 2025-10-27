package com.kh.spring.api.controller;

import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;

@Controller
public class ForwardController {

	@GetMapping("beef")
	public String toApi(){
		return "api/blog";
	}
	
	@GetMapping("map")
	public String toMap(){
		return "api/map";
	}
	
	@GetMapping("busan")
	public String toBusan(){
		return "api/busan";
	}
	
	@GetMapping("busan/forward/{num}")
	public String toDtail(Model model, @PathVariable(value="num") int num){
		model.addAttribute("num",num);
		return "api/detail";
	}
}
