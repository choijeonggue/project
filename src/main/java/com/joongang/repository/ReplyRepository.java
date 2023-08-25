package com.joongang.repository;

import java.util.List;

import org.apache.ibatis.annotations.Param;

import com.joongang.domain.Criteria;
import com.joongang.domain.ReplyVO;

public interface ReplyRepository {
	
	int insert(ReplyVO vo);
	
	ReplyVO read(Long bno);
	
	int delete(Long rno);
	
	int update(ReplyVO vo);
	
	List<ReplyVO> getList(
			@Param("bno") Long bno, 
			@Param("criteria") Criteria criteria);
	
	int getReplyCount(Long bno);

}
