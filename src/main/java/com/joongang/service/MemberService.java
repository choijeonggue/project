package com.joongang.service;

import java.util.Map;

import com.joongang.domain.MemberVO;

public interface MemberService {
	
	void join(MemberVO vo);
	
	void modify(MemberVO vo);
	
	MemberVO read(String memberId);
	
	void changePassword(Map<String, String> memberMap);

}
