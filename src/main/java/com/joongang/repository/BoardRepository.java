package com.joongang.repository;

import java.util.List;


import com.joongang.domain.BoardVO;

public interface BoardRepository {

	List<BoardVO> getList();
	
	void insert(BoardVO vo);
	
	Integer insertSelectKey(BoardVO vo);
	
	BoardVO read(Long bno);
	
	int delete(Long bno);
	
	int update(BoardVO vo);
}
