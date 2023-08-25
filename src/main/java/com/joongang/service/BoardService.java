package com.joongang.service;

import java.util.List;

import com.joongang.domain.BoardAttachVO;
import com.joongang.domain.BoardVO;
import com.joongang.domain.Criteria;

public interface BoardService {
	
	List<BoardVO> getList(Criteria criteria); // 목록
	
	void register(BoardVO board); //등록
	
	BoardVO get(Long bno); //조회
	
	boolean modify(BoardVO board); // 수정 
	
	boolean remove(Long bno); // 삭제
	
	//게시물수
	int totalCount(Criteria criteria);
	
	List<BoardAttachVO> getAttachList(Long bno);
	
	BoardAttachVO getAttach(String uuid);
	
}
