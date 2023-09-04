package com.joongang.repository;

import org.apache.ibatis.annotations.Param;

import com.joongang.domain.MemberVO;

public interface MemberRepository {
	
	MemberVO read(String memberId);
	
	void insert(MemberVO vo);
	
	void update(MemberVO vo);
	
	MemberVO selectById(String memberId);
	
	String selectByEmail(String email);
	
	void updatePassword(
			@Param("memberid") String memberId,
			@Param("memberPwd")String memberPwd);

}
