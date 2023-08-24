package com.joongang.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;

import com.joongang.domain.BoardVO;
import com.joongang.service.BoardService;

import lombok.RequiredArgsConstructor;
import lombok.extern.log4j.Log4j;

@Controller
@Log4j
@RequestMapping("/board")
public class BoardController {

	@Autowired
	private  BoardService boardService;
	
	@GetMapping("/list")
	public void list(Model model) {
		model.addAttribute("list",boardService.getList());
	}

	@GetMapping({"/get","/modify"})
	public void get(Long bno, Model model) {
		log.info("/get..............");
		model.addAttribute("board",boardService.get(bno));
	}
	
	@GetMapping("/register")
	public void register() {}
	
	
	@PostMapping("/register")
	public String register(BoardVO vo, RedirectAttributes rttr) {
		boardService.register(vo);
		rttr.addFlashAttribute("result" , vo.getBno()); // list.jsp  ${result}
		rttr.addFlashAttribute("operation","register");
		return "redirect:/board/list";
	}
	

	@PostMapping("/modify")
	public String modify(BoardVO vo, RedirectAttributes rttr) {
		if(boardService.modify(vo)) {
			rttr.addFlashAttribute("result",vo.getBno());
			rttr.addFlashAttribute("operation","modify");
		}
		return "redirect:/board/list";
		
	}
	
	@PostMapping("/remove")
	public String remove(Long bno, RedirectAttributes rttr) {
		if(boardService.remove(bno)) {
			rttr.addFlashAttribute("result",bno);
			rttr.addFlashAttribute("operation","remove");
		}
		return "redirect:/board/list";
	}
}