package com.joongang.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.joongang.domain.AuthVO;
import com.joongang.domain.MemberVO;
import com.joongang.repository.AuthRepository;
import com.joongang.repository.MemberRepository;

@Service
public class MemberServiceImpl implements MemberService {

	@Autowired
	private MemberRepository memberRepository;
	
	@Autowired
	private AuthRepository authRepository;
	
	@Autowired
	private PasswordEncoder passwordEncoder;
	
	@Transactional
	@Override
	public void join(MemberVO vo) {
		vo.setMemberPwd(passwordEncoder.encode(vo.getMemberPwd()));
		AuthVO authVO = new AuthVO(vo.getMemberId(),"ROLE_MEMBER");
		memberRepository.insert(vo);
		authRepository.insert(authVO);

	}

}
