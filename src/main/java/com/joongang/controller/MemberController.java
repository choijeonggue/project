package com.joongang.controller;

import javax.servlet.http.HttpServletRequest;

import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.security.core.Authentication;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;

import lombok.extern.log4j.Log4j;

@Controller
@Log4j
public class MemberController {
	
	@GetMapping("/guest/guestPage")
	public String guestPage() {
		return "guest/guestPage";
	}

	@PreAuthorize("hasAnyRole('ROLE_ADMIN','ROLE_MEMBER')")
	@GetMapping("/member/myPage")
	public String myPage() {
		return "member/myPage";
	}
	
	@PreAuthorize("hasRole('ROLE_ADMIN')")
	@GetMapping("/admin/adminPage")
	public String adminPage() {
		return "admin/adminPage";
	}
	
	@GetMapping("/accessDenied")
	public String accessDenided() {
		return "accessError";
	}
	
	@RequestMapping("/login")
	public String loginPage(HttpServletRequest request,
			Authentication authentication, RedirectAttributes rttr, String error, String logout, Model model) {
		String uri = request.getHeader("Referer"); // 로그인 전 사용자가 보던 페이지
		if(uri!=null && !uri.contains("/login")) {
			request.getSession().setAttribute("prevPage", uri);
		}
		
		if(authentication!=null) {
			rttr.addFlashAttribute("duplicateLogin", "로그인 상태 입니다.");
			if(uri==null) uri="/";
			return "redirect : " + uri;
		}
		
		if(logout!=null) model.addAttribute("logout","로그아웃");
		return "member/login";
	}

}
