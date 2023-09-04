package com.joongang.controller;


import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.access.AccessDeniedException;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.authority.SimpleGrantedAuthority;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;

import com.joongang.domain.BoardAttachVO;
import com.joongang.domain.BoardVO;
import com.joongang.domain.Criteria;
import com.joongang.domain.Pagination;
import com.joongang.service.BoardService;

import lombok.extern.log4j.Log4j;

@Controller
@RequestMapping("/board")
@Log4j
public class BoardController {

	@Autowired
	private  BoardService boardService;
	
	@GetMapping("/list")
	public void list(Model model, Criteria criteria) {
		model.addAttribute("list",boardService.getList(criteria));
		model.addAttribute("p", new Pagination(criteria, boardService.totalCount(criteria)));
	}

	@GetMapping("/getAttachList")
	@ResponseBody
	public ResponseEntity<List<BoardAttachVO>> getAttachList(Long bno){
		return new ResponseEntity<List<BoardAttachVO>>(boardService.getAttachList(bno),HttpStatus.OK);
	}
	
	@GetMapping("/getAttachFileInfo")
	@ResponseBody
	public ResponseEntity<BoardAttachVO> getAttachFileInfo(String uuid){
		return new ResponseEntity<>(boardService.getAttach(uuid), HttpStatus.OK); 
	}
	

	@GetMapping("/get")
	public void get(Long bno, Model model, Criteria criteria) {
		model.addAttribute("board",boardService.get(bno));
	}

	@PreAuthorize("isAuthenticated()")
	@GetMapping("/modify")
	public String modify(Long bno, Model model, Criteria criteria, Authentication auth) {
		BoardVO vo = boardService.get(bno);
		String username = auth.getName(); // 인증된 사용자 계정
		if (!vo.getWriter().equals(username) &&  // 글작성자가 아닌 경우
			!auth.getAuthorities().contains(new SimpleGrantedAuthority("ROLE_ADMIN"))) { // 관리자가 아닌 경우
				throw new AccessDeniedException("Access denied"); // 접근 금지 
	    }
		model.addAttribute("board", vo);
		return "board/modify";
	}
	
	@PreAuthorize("isAuthenticated()")
	@GetMapping("/register")
	public void register() {}

	@PreAuthorize("isAuthenticated()")
	@PostMapping("/register")
	public String register(BoardVO vo, RedirectAttributes rttr) {
		boardService.register(vo);
		rttr.addFlashAttribute("result" , vo.getBno()); // list.jsp  ${result}
		rttr.addFlashAttribute("operation","register");
		return "redirect:/board/list";
	}
	

	@PreAuthorize("isAuthenticated() and principal.username== #vo.writer or hasRole('ROLE_ADMIN')")
	@PostMapping("/modify")
	public String modify(BoardVO vo, RedirectAttributes rttr, Criteria criteria) {
		if(boardService.modify(vo)) {
			rttr.addFlashAttribute("result", vo.getBno());
			rttr.addFlashAttribute("operation", "modify");
		}
		return "redirect:/board/list"+criteria.getListLink();
	}
	
	@PreAuthorize("isAuthenticated() and principal.username == #writer or hasRole('ROLE_ADMIN')")
	@PostMapping("/remove")
	public String remove(Long bno, RedirectAttributes rttr, Criteria criteria,String writer) {
		if(boardService.remove(bno)) {
			rttr.addFlashAttribute("result",bno);
			rttr.addFlashAttribute("operation","remove");
		}
		return "redirect:/board/list"+criteria.getListLink();
	}
	
}