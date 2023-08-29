package com.joongang.service;

import static org.junit.Assert.*;

import org.junit.Ignore;
import org.junit.Test;
import org.springframework.beans.factory.annotation.Autowired;

import com.joongang.AppTest;
import com.joongang.domain.AuthVO;
import com.joongang.domain.MemberVO;
import com.joongang.repository.AuthRepository;


public class MemberServiceImplTest extends AppTest {
	
	@Autowired
	private MemberService memberService;
	
	@Autowired
	private AuthRepository authRepository;
	
	

	@Test
	//@Ignore
	public void test() {
		MemberVO vo = new MemberVO();
		vo.setMemberId("admin");
		vo.setMemberPwd("1234");
		vo.setMemberName("관리자");
		vo.setEmail("admin@test.com");
		memberService.join(vo);
		
		AuthVO authVO = new AuthVO("admin" ,"ROLE_ADMIN");
		authRepository.insert(authVO);
		
	}
	
	@Test
	@Ignore
	public void test2() {
		MemberVO vo = new MemberVO();
		vo.setMemberId("scott");
		vo.setMemberPwd("1234");
		vo.setMemberName("스캇");
		vo.setEmail("scott@test.com");
		memberService.join(vo);
	}
	
	
	
	
	

}
