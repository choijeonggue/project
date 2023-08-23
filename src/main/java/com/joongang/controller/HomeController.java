package com.joongang.controller;

import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;

@Controller // 이 클래스로 생성된 객체가 컨트롤러임을 나타낸다.
public class HomeController {
	
	// URL과 HTTP 메서드를 지정함
	@RequestMapping(value = "/", method = RequestMethod.GET)
	public String home(Model model) {
 		// Model 뷰(View)에 데이터를 전달하기 위해 사용하는 객체 
		model.addAttribute("greeting", "프로젝트!!!");
		return "home";
	}
}