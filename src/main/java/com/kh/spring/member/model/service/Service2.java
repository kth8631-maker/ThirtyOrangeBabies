package com.kh.spring.member.model.service;

import org.springframework.stereotype.Service;

import com.kh.spring.member.model.dto.MemberDTO;

import lombok.extern.slf4j.Slf4j;

@Slf4j
//@Service
public class Service2 implements MemberService {

	@Override
	public MemberDTO login(MemberDTO member) {
		log.info("후후후 바뀐거 알겠어요??");
		return null;
	}

	@Override
	public void signUp(MemberDTO member) {
	}

	@Override
	public void update(MemberDTO member) {
	}

	@Override
	public void delete(MemberDTO member) {
	}

}
