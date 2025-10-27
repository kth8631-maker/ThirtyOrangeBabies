package com.kh.spring.member.model.dao;

import org.apache.ibatis.annotations.Delete;
import org.apache.ibatis.annotations.Insert;
import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Select;
import org.apache.ibatis.annotations.Update;

import com.kh.spring.member.model.dto.MemberDTO;

@Mapper
public interface MemberMapper {
	
	@Select("SELECT USER_ID userId, USER_PWD userPwd, USER_NAME userName, EMAIL, ENROLL_DATE enrollDate FROM MEMBER WHERE USER_ID = #{userId}")
	MemberDTO login(MemberDTO member);
	
	@Insert("INSERT INTO MEMBER VALUES(#{userId}, #{userPwd}, #{userName}, #{email}, SYSDATE)")
	int signup(MemberDTO member);
	
	@Update("UPDATE MEMBER SET USER_NAME = #{userName}, EMAIL = #{email} WHERE USER_ID = #{userId}")
	int update(MemberDTO member);
	
	@Delete("DELETE FROM MEMBER WHERE USER_ID = #{userId}")
	int delete(String userId);

}
