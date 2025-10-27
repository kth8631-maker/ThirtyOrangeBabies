package com.kh.spring.member.model.service;

import org.springframework.stereotype.Component;

import com.kh.spring.exception.AuthenticationException;
import com.kh.spring.exception.InvalidArgumentsException;
import com.kh.spring.exception.TooLargeValueException;
import com.kh.spring.member.model.dao.MemberMapper;
import com.kh.spring.member.model.dto.MemberDTO;

import lombok.RequiredArgsConstructor;

@Component
@RequiredArgsConstructor
public class MemberValidator {
	
	private final MemberMapper memberMapper;
	
	/*
	@Autowired
	public MemberValidator(MemberMapper memberMapper) {
		this.memberMapper = memberMapper;
	}
	*/
	
	private void checkNull(MemberDTO member) {
		if(member == null) {
			throw new NullPointerException("잘못된 접근입니다.");
		}
	}
	private void checkLength(MemberDTO member) {
		if(member.getUserId().length() > 20) {
			throw new TooLargeValueException("아이디 값이 너무 길어용");
		}
	}
	private void checkBlank(MemberDTO member) {
		if(member.getUserId() == null ||
		   member.getUserId().trim().isEmpty() ||
		   member.getUserPwd() == null ||
		   member.getUserPwd().trim().isEmpty()) {
			throw new InvalidArgumentsException("유효하지 않는 값입니다.");
		}
	}
	
	public void validatedMember(MemberDTO member) {
		checkNull(member);
		checkLength(member);
		checkBlank(member);
	}
	
	public void validatedUpdateMember(MemberDTO member, MemberDTO sessionMember) {
		checkNull(member);
		checkNull(sessionMember);
		
		if(!member.getUserId().equals(sessionMember.getUserId())) {
			throw new AuthenticationException("권한없는 접근입니다.");	
		}
		checkNull(memberMapper.login(member));
		
	}
	
	

}
