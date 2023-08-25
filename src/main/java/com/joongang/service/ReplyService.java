package com.joongang.service;

import java.util.List;

import com.joongang.domain.Criteria;
import com.joongang.domain.ReplyPageDTO;
import com.joongang.domain.ReplyVO;

public interface ReplyService {
	
	int register(ReplyVO vo);
	
	ReplyVO get(Long rno);
	
	int modify(ReplyVO vo);
	
	int remove(Long rno);
	
	ReplyPageDTO getList(Criteria criteria, Long bno);
	

}
