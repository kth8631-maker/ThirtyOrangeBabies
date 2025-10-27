package com.kh.spring.member.controller;

import javax.servlet.http.HttpSession;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.servlet.ModelAndView;

import com.kh.spring.member.model.dto.MemberDTO;
import com.kh.spring.member.model.service.MemberService;

import lombok.extern.slf4j.Slf4j;

@Slf4j
@Controller
// @RequestMapping("/member")
public class MemberController {
	/*
	public MemberController() {
		log.info("하이 난 빈임");
	}
	*/
	/*
	@RequestMapping("login")
	public void login(Member member) {
		// 1. 값 뽑기
		// 2. 데이터 가공
		System.out.println(member);
	}
	*/
	/*
	@RequestMapping("login")
	public String login(HttpServletRequest request) {
		String userId = request.getParameter("userId");
		String userPwd = request.getParameter("userPwd");
		
		System.out.printf("id : %s , pw : %s", userId, userPwd);
		
		return "main";
	}
	*/
	/*
	@RequestMapping("login")
	public String login(@RequestParam(value="userId", defaultValue="fffff") String id,
						@RequestParam(value="userPwd") String pwd) {
		System.out.printf("이렇게 하면 될까요?? id : %s, pwd : %s", id, pwd);
		
		return "main";
	}
	*/
	/*
	@RequestMapping("login")
	public String login(/*@RequestParam(value="userId") String userId,
						/*@RequestParam(value="userPwd") String userPwd) {
		System.out.println("으흐흫ㅎㅎㅎ id : " + userId + ", pwd : " + userPwd);
		
		return "main";
	}
	*/
	/*
	 * HandlerAdapter의 판단 방법 :
	 * 
	 * 1. 매개변수 자리에 기본타입(int, boolean, String, Date...)이 있거나
	 *    @RequestParam애노테이션이 존재하는 경우 == RequestParam으로 인식
	 * 
	 * 2. 매개변수 자리에 사용자 정의 클래스(MemberDTO, Board, Reply..)이 있거나
	 *    @ModelAttribute애노테이션이 존재하는 경우 == 커맨드 객체 방식으로 인식 
	 * 
	 * 커맨드 객체 방식
	 * 
	 * 스프링에서 해당 객체를 기본생성자를 이용해서 생성한 후 내부적으로 setter메서드를 찾아서
	 * 요청 시 전달값을 해당 필드에 대입해줌
	 * 
	 * 1. 매개변수 자료형에 반드시 기본생성자가 존재할 것
	 * 2. 전달되는 키 값과 객체의 필드명이 동일할 것
	 * 3. setter메서드가 반드시 존재할 것
	 */
	//@Autowired == 필드 인젝션
	private final MemberService memberService; // = new MemberService();
	
	/*
	@Autowired == 세터 인젝션
	public void setMemberService(MemberService memberService) {
		this.memberService = memberService;
	} 둘다안씀
	*/
	
	@Autowired /* ☆ 권장 방법 ★ */
	public MemberController(MemberService memberService) {
		this.memberService = memberService;
	}
	/*
	@RequestMapping("login")
	public String login(/*@ModelAttribute MemberDTO member,
						//HttpServletRequest request
						HttpSession session,
						Model model) {
		//System.out.println("로그인 시 입력한 정보 : " + member);
		log.info("Member객체 필드값 확인 ~ {}", member);
		MemberDTO loginMember = memberService.login(member);
		/*
		if(loginMember != null) {
			log.info("로그인 성공");
		} else {
			log.info("실패");
		}
		
		if(loginMember != null) { // 로그인에 성공
			// sessionScope에 로그인된 사용자의 정보를 담아줌
			// HttpSessino session = request.getSession();s
			session.setAttribute("loginMember", loginMember);
			// 포워딩 방식 보다는 -> sendRedirect
			// localhost/spring			/
			
			return "redirect:/";
		} else { // 실패했을 때
			// error_page -> 포워딩
			// requestScope에 msg라는 키값으로 로그인 실패입니다 ~~ 담아서 포워딩
			// Spring에서는 Model타입을 이용해서 RequestScope에 값을 담음
			model.addAttribute("msg", "로그인 실패 까비~");
			// Forwarding
			// /WEB-INF/views/
			// .jsp
			
			// /WEB-INF/views/include/error_page.jsp
			
			return "include/error_page";
		}
		//return "main";
	}
	*/
	
	// 두 번째 방법 : 반환타입 ModelAndView타입으로 반환
	@PostMapping("/login")
	public ModelAndView login(MemberDTO member,
							  HttpSession session,
							  ModelAndView mv) {
		
		MemberDTO loginMember = memberService.login(member);
		// ModelAndView mv = new ModelAndView();
		if(loginMember != null) {
			session.setAttribute("loginMember", loginMember);
			mv.setViewName("redirect:/");
		} else {
			mv.addObject("msg", "로그인실패!")
			  .setViewName("include/error_page");
		}
		return mv;
	}
	
	// CRUD
	// INSERT		--> POST  ------> /member
	// SELECT		--> GET
	// UPDATE
	// DELETE
	@GetMapping("logout")
	public String logout(HttpSession session) {
		session.removeAttribute("loginMember");
		return "redirect:/";
	}
	
	
	// 회원가입
	@GetMapping("join")
	public String joinForm() {
		// 포워딩할 JSP파일의 논리적인 경로
		// /WEB-INF/views/	member/signup	.jsp
		return "member/signup";
	}
	
	@PostMapping("signup")
	public String signup(MemberDTO member
						//,HttpServletRequest request
						) {
		// 아이디, 비밀번호, 이름, 이메일
		/*
		try {
			request.setCharacterEncoding("UTF-8");
		} catch (UnsupportedEncodingException e) {
			e.printStackTrace();
		}
		*/
		log.info("{}", member);
		memberService.signUp(member);
		return "main";
	}
	
	@GetMapping("mypage")
	public String myPage() {
		return "member/my_page";
	}
	
	@PostMapping("edit")
	public String edit(MemberDTO member, HttpSession session) {
		/*
		 * 1_1) 404 발생 : mapping값 확인하기
		 * org.springframework.web.servlet.PageNotFound
		 * 
		 * 1_2) 405 발생 : mapping값 잘씀 GET/POST 를 잘못적었을 때
		 * 
		 * 1_3) 필드에 값이 잘 들어왔나?? - Key값 확인
		 */
		log.info("값 찍어보기 : {}", member);
		/*
		 * 2. SQL문
		 * UPDATE  => MEMBER => PK?
		 * ID PWD NAME EMAIL ENROLLDATE
		 * 
		 * 2_1) 매개변수 MemberDTO타입의 memberId필드값 조건
		 * UPDATE MEMBER SET USER_NAME = 입력한 값, EMAIL = 입력한 값
		 *  WHERE USER_ID = 넘어온 아이디
		 */
		/*
		 * Best Practice
		 * 
		 * 컨트롤러에서 세션관리를 담당
		 * 서비스에는 순수 비즈니스 로직만 구현
		 * 서비스에서 HttpSession이 필요하다면 인자로 전달
		 * 
		 */
		memberService.update(member, session);
		return "redirect:mypage";
	}
	
	@PostMapping("delete")
	public String delete(@RequestParam(value="userPwd") String userPwd,
						 HttpSession session) {
	
		memberService.delete(userPwd, session);
		session.removeAttribute("loginMember");
		return "redirect:/";
	}
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	

}
