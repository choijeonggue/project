package com.joongang.repository;

import com.joongang.domain.MemberVO;

public interface MemberRepository {
	
	MemberVO read(String memberId);
	
	void insert(MemberVO vo);

}
