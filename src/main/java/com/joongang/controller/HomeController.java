package com.joongang.controller;

import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;

@Controller // 이 클래스로 생성된 객체가 컨트롤러임을 나타낸다.
public class HomeController {
	
	public HomeController() {
		System.out.println("HomeController 빈 생성");
	}
	
	@GetMapping("/")
	public String home() {
		return "home";
	}
}