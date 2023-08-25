package com.joongang.service;

import java.util.List;

import org.springframework.stereotype.Service;

import com.joongang.domain.BoardVO;
import com.joongang.domain.Criteria;
import com.joongang.repository.BoardRepository;

import lombok.RequiredArgsConstructor;
import lombok.extern.log4j.Log4j;

@Service
@RequiredArgsConstructor
public class BoardServiceImpl implements BoardService {

	
	private final BoardRepository boardRepositroy;

	@Override
	public List<BoardVO> getList(Criteria criteria) {
		return boardRepositroy.getList(criteria);
	}
	
	@Override
	public void register(BoardVO board) {
		boardRepositroy.insertSelectKey(board);
	}

	@Override
	public BoardVO get(Long bno) {
		return boardRepositroy.read(bno);
	}

	@Override
	public boolean modify(BoardVO board) {
		return boardRepositroy.update(board)==1;
	}

	@Override
	public boolean remove(Long bno) {
		return boardRepositroy.delete(bno)==1;
	}

	@Override
	public int totalCount(Criteria criteria) {
		return boardRepositroy.getTotalCount(criteria);
	}


}
