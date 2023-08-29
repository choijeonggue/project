package com.joongang.security;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Component;

import com.joongang.domain.MemberVO;
import com.joongang.repository.MemberRepository;

@Component
public class CustomUserDetailService implements UserDetailsService{

	@Autowired
	private MemberRepository memberRepository;
	
	@Override
	public UserDetails loadUserByUsername(String username) throws UsernameNotFoundException {
		MemberVO vo = memberRepository.read(username);
		if(vo==null) {
			throw new UsernameNotFoundException("User not found with user name : " + username);
		}
		return new CustomUser(vo);
	}

}
