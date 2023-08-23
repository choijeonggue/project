package com.joongang.repository;

import java.util.List;


import com.joongang.domain.BoardVO;

public interface BoardMapper {

	List<BoardVO> getList();
	
	void insert(BoardVO board);
	
	Integer insertSelectKey(BoardVO board);
	
	BoardVO read(Long bno);
	
	int delete(Long bno);
	
	int update(BoardVO board);
}
