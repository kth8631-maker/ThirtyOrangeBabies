package com.kh.spring.member.model.dao;

import org.mybatis.spring.SqlSessionTemplate;
import org.springframework.stereotype.Repository;

import com.kh.spring.member.model.dto.MemberDTO;

@Repository
public class MemberRepository {
	
	public MemberDTO login(SqlSessionTemplate sqlSession, MemberDTO member) {
		return sqlSession.selectOne("memberMapper.login", member);
	}
	
	public int signup(SqlSessionTemplate sqlSession, MemberDTO member) {
		return sqlSession.insert("memberMapper.signup", member);
	}
	
	

}
