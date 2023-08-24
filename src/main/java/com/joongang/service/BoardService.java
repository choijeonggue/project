package com.joongang.service;

import java.util.List;

import com.joongang.domain.BoardVO;

public interface BoardService {
	
	List<BoardVO> getList(); // 목록
	
	void register(BoardVO board); //등록
	
	BoardVO get(Long bno); //조회
	
	boolean modify(BoardVO board); // 수정 
	
	boolean remove(Long bno); // 삭제
	

}
