package com.kh.spring.member.model.service;

import javax.servlet.http.HttpSession;

import org.springframework.stereotype.Service;

import com.kh.spring.exception.AuthenticationException;
import com.kh.spring.exception.UserIdNotFoundException;
import com.kh.spring.member.model.dao.MemberMapper;
import com.kh.spring.member.model.dto.MemberDTO;

import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;

@Slf4j
@Service
@RequiredArgsConstructor
public class MemberServiceImpl implements MemberService {
	/*
	 * SRP(Single Responsibility Principle)
	 * 단 일 책 임 원 칙 위반
	 * 
	 * 하나의 클래스는(메소드) 하나의 책임만을 가져야함 == 얘가 수정되는 이유는 딱 하나여야함
	 * 
	 * 책임 분리하면 끝
	 */
	
	//@Autowired
	// private final SqlSessionTemplate sqlSession;
	//@Autowired
	// private final MemberRepository memberRepository;
	//@Autowired
	private final PasswordEncoder passwordEncoder;
	private final MemberValidator validator;
	private final MemberMapper mapper;
	
	/*
	@Autowired
	public MemberServiceImpl(SqlSessionTemplate sqlSession,
							 MemberRepository memberRepository,
							 BCryptPasswordEncoder passwordEncoder) {
		this.sqlSession = sqlSession;
		this.memberRepository = memberRepository;
		this.passwordEncoder = passwordEncoder;
	}
	*/

	@Override
	public MemberDTO login(MemberDTO member) {
		// log.info("나 불렀어?");
		/*
		 * SqlSession session = Template.getSqlSession();
		 * MemberDTO loginMember = new MemberDAO().login(sqlSession, member);
		 * sqlSession.close();
		 * return loginMember;
		 */
		// ver 0.1
		// return memberRepository.login(sqlSession, member);
		
		// 사용자는 평문을 입력하지만 실제 DB컬럼에는 암호문이 들어있기 때문에
		// 비밀번호를 비교하는 SELECT문은 사용할 수 없음
		
		MemberDTO loginMember = mapper.login(member);//memberRepository.login(sqlSession, member);
		/*
		if(loginMember == null) {
			throw new UserIdNotFoundException("아이디 또는 비밀번호가 틀림");
		}
		// 1절
		//log.info("사용자가 입력한 비밀번호 평문 : {}",member.getUserPwd());
		//log.info("DB에 저장된 암호화된 암호문 : {}", loginMember.getUserPwd());
		
		// 아이디만 가지고 조회를 하기 때문에
		// 비밀번호를 검증 후
		// 비밀번호가 유효하다면 ㅇㅋㅇㅋ~
		// 비밀번호가 유효하지 않다면 이상한데??
		if(passwordEncoder.matches(member.getUserPwd(), loginMember.getUserPwd())) {
			return loginMember;
		}
		return null;
		*/
		return validateLoginMember(loginMember, member.getUserPwd());
	}
	
	private MemberDTO validateLoginMember(MemberDTO loginMember, String userPwd) {
		if(loginMember == null) {
			throw new UserIdNotFoundException("아이디 또는 비밀번호가 틀림");
		}
		if(passwordEncoder.matches(userPwd, loginMember.getUserPwd())) {
			return loginMember;
		}
		return null;
	}

	@Override
	public void signUp(MemberDTO member) {
		// 꼼꼼하게 검증
		// 유효값 검증
		/*
		if(member == null) {
			throw new NullPointerException("잘못된 접근입니다.");
		}
		
		if(member.getUserId().length() > 20) {
			throw new TooLargeValueException("아이디 값이 너무 길어용");
		}
		
		if(member.getUserId() == null ||
		   member.getUserId().trim().isEmpty() ||
		   member.getUserPwd() == null ||
		   member.getUserPwd().trim().isEmpty()) {
			throw new InvalidArgumentsException("유효하지 않는 값입니다.");
		}
		*/
		
		// 아이디 중복체크 생략
		// DAO로 가서 INSERT하기전에 비밀번호 암호화하기
		// log.info("사용자가 입력한 비밀번호 평문 : {}", member.getUserPwd());
		// 암호화하기 == 인코더가지고 .encode()호출
		// log.info("암호화한 후 : {}", passwordEncoder.encode(member.getUserPwd()));
		validator.validatedMember(member);
		String encPwd = passwordEncoder.encode(member.getUserPwd());
		member.setUserPwd(encPwd);
		// memberRepository.signup(sqlSession, member);
		mapper.signup(member);
	}

	@Override
	public void update(MemberDTO member, HttpSession session) {
		MemberDTO sessionMember = ((MemberDTO)session.getAttribute("loginMember"));
		// 본격적인 개발자의 영역
		// 앞단에서 넘어온 ID값과 현재 로그인된 사용자의 ID값이 일치하는가?
		
		// 실제 DB에 ID값이 존재하는 회원인가?
		
		// USERNAME 컬럼에 넣을 값이 USERNAME 컬럼 크기보다 크지 않은가?
		
		// EMAIL 컬럼에 넣을 값이 EMAIL컬럼 크기보다 크지않은가?
		
		// (EMAIL컬럼에 넣을 값이 실제 EMAIL형식과 일치하는가?)
		validator.validatedUpdateMember(member, sessionMember);
		// DB가서 UPDATE
		int result = mapper.update(member);
		
		if(result != 1) {
			throw new AuthenticationException("문제가 발생했습니다. 관리자에게 문의하세요.");
		}
		// 성공한 회원의 정보로 SessionScope에 존재하는 loginMember키값의 Member객체 필드값
		// 갱신해주기
		sessionMember.setUserName(member.getUserName());
		sessionMember.setEmail(member.getEmail());
	}

	@Override
	public void delete(String userPwd, HttpSession session) {
		// 제 1원칙 : 기능이 동작해야함
		
		MemberDTO sessionMember = ((MemberDTO)session.getAttribute("loginMember"));
		
		if(sessionMember == null) {
			throw new AuthenticationException("로그인부터해라~");
		}
		//log.info("오잉뚀잉??");
		if(!passwordEncoder.matches(userPwd, sessionMember.getUserPwd())) {
			//log.info("왜 예외 안일어남??");
			throw new AuthenticationException("비밀번호가 일치하지 않습니다.");
		}
		
		// DELETE FROM MEMBER WHERE USER_ID = 현재 로그인된 사용자의 아이디
		int result = mapper.delete(sessionMember.getUserId());
		
		if(result != 1) {
			throw new AuthenticationException("관리자에게 문의하세요.");
		}
		// 리팩토링
		/*
		 * 쉬운데 어려움(복잡함)
		 * 
		 * 세팅 수업시간에 했던거 다 가져다쓰세요
		 * 
		 * 스프링을 이용한 기능 구현 ==> 숙제(주말까지) ==> 꼭!!!하기!!!!꼭꼭해보기!!!!꼭꼭꼭!!
		 * 
		 * 앞에서 구현했던
		 * DynamicWebProject 회원(KH_MEMBER)파트 ==> Spring버전으로 다시 만들기
		 * 
		 * 화면 다있음 테이블 다있음 SQL문 다있음 ==> Service단 및 예외처리를 신경써보자
		 * 
		 */
		
		
		
	}

}
