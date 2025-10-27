package com.kh.spring.interceptor;

import javax.naming.AuthenticationException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import org.springframework.web.servlet.handler.HandlerInterceptorAdapter;

public class LoginInterceptor extends HandlerInterceptorAdapter{

	
	/*
	 * Interceptor
	 * 
	 * Requestandler가 호출되기 전 또는 수행 후 실행할 내용을 만들어 줄 수 있음
	 * 
	 * preHandler(전처리)	  : 핸들러 수행 전 낚아챔
	 * posrHandler(후처리) : 핸들러 수행 후 낚아챔
	 * 
	 * 
	 * 
	 */
	
	
	@Override
	public boolean preHandle(HttpServletRequest request,
			                 HttpServletResponse response,
			                 Object handler) throws Exception {
		HttpSession session = request.getSession();
		if(session.getAttribute("loginMember") != null) {
			return true;
		} else {
			response.sendRedirect(request.getContextPath());
			return false;
		}
		
		//throw new AuthenticationException("로그인해라");
		
	}
	
	
	
}
