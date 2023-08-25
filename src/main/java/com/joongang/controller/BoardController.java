package com.joongang.controller;

import java.util.List;
import java.util.stream.Collectors;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
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
@Log4j
@RequestMapping("/board")
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
	

	@GetMapping({"/get","/modify"})
	public void get(Long bno, Model model, Criteria criteria) {
		log.info(bno);
		log.info(criteria);
		model.addAttribute("board",boardService.get(bno));
	}
	
	@GetMapping("/register")
	public void register() {}
	
	
	@PostMapping("/register")
	public String register(BoardVO vo, RedirectAttributes rttr) {
		log.info(vo.getAttachList());
//		boardService.register(vo);
//		rttr.addFlashAttribute("result" , vo.getBno()); // list.jsp  ${result}
//		rttr.addFlashAttribute("operation","register");
		return "redirect:/board/list";
	}
	

	@PostMapping("/modify")
	public String modify(BoardVO vo, RedirectAttributes rttr, Criteria criteria) {
		List<BoardAttachVO> attachList = vo.getAttachList();
		
		List<BoardAttachVO> insertList = attachList.stream()
				.filter(attach->attach.getBno()==null).collect(Collectors.toList());
		log.info("새로 추가"+insertList);
		
		List<BoardAttachVO> delList = attachList.stream()
				.filter(attach->attach.getBno()==null).collect(Collectors.toList());
		log.info("삭제 목록"+insertList);
		
		if(boardService.modify(vo)) {
			rttr.addFlashAttribute("result",vo.getBno());
			rttr.addFlashAttribute("operation","modify");
		}
		return "redirect:/board/list"+criteria.getListLink();
		
	}
	
	@PostMapping("/remove")
	public String remove(Long bno, RedirectAttributes rttr, Criteria criteria) {
		if(boardService.remove(bno)) {
			rttr.addFlashAttribute("result",bno);
			rttr.addFlashAttribute("operation","remove");
		}
		return "redirect:/board/list"+criteria.getListLink();
	}
	
}