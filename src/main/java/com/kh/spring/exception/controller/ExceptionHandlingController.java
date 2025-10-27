package com.kh.spring.exception.controller;

import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.servlet.ModelAndView;

import com.kh.spring.exception.AuthenticationException;
import com.kh.spring.exception.InvalidArgumentsException;
import com.kh.spring.exception.TooLargeValueException;
import com.kh.spring.exception.UserIdNotFoundException;

import lombok.extern.slf4j.Slf4j;

@Slf4j
@ControllerAdvice
public class ExceptionHandlingController {
	
	private ModelAndView createErrorResponse(RuntimeException e) {
		ModelAndView mv = new ModelAndView();
		mv.addObject("msg", e.getMessage()).setViewName("include/error_page");
		log.info("발생예외 : {}", e);
		return mv;
	}
	
	@ExceptionHandler(AuthenticationException.class)
	protected ModelAndView authenticationError(AuthenticationException e) {
		return createErrorResponse(e);
	}
	@ExceptionHandler(UserIdNotFoundException.class)
	protected ModelAndView idNotFoundError(UserIdNotFoundException e) {
		return createErrorResponse(e);
	}
	@ExceptionHandler(TooLargeValueException.class)
	protected ModelAndView largeValueError(TooLargeValueException e) {
		return createErrorResponse(e);
	}
	
	@ExceptionHandler(InvalidArgumentsException.class)
	protected ModelAndView invaildArgumentError(InvalidArgumentsException e) {
		return createErrorResponse(e);
	}
	
	/*
	@ExceptionHandler(UserIdNotFoundException.class)
	protected ModelAndView idNotFoundError(UserIdNotFoundException e) {
		ModelAndView mv = new ModelAndView();
		mv.addObject("msg", e.getMessage()).setViewName("include/error_page");
		log.info("발생예외 : {}", e);
		return mv;
	}
	
	@ExceptionHandler(TooLargeValueException.class)
	protected ModelAndView largeValueError(TooLargeValueException e) {
		//log.info("{}", e.getMessage());
		ModelAndView mv = new ModelAndView();
		mv.addObject("msg", e.getMessage()).setViewName("include/error_page");
		return mv;
	}
	
	@ExceptionHandler(InvalidArgumentsException.class)
	protected ModelAndView invaildArgumentError(InvalidArgumentsException e) {
		//log.info("{}", e.getMessage());
		ModelAndView mv = new ModelAndView();
		mv.addObject("msg", e.getMessage()).setViewName("include/error_page");
		return mv;
	}
	*/
	

}
