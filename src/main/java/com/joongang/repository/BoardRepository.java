package com.joongang.repository;

import java.util.List;

import org.apache.ibatis.annotations.Param;

import com.joongang.domain.BoardVO;
import com.joongang.domain.Criteria;

public interface BoardRepository {

	List<BoardVO> getList(Criteria criteria);
	
	void insert(BoardVO vo);
	
	// 삽입된 행의 개수
	Integer insertSelectKey(BoardVO vo);
	
	BoardVO read(Long bno);
	
	// 삭제된 행의 개수
	int delete(Long bno);
	
	// 수정된 행의 개수
	int update(BoardVO vo);
	
	//전체 게시물 수
	int getTotalCount(Criteria criteria);
	
	//amount = 댓글 추가 :1 댓글 감소 : -1
	void updateReplyCnt(@Param("bno")Long bno, @Param("amount")int amount);
	
}