package com.joongang.service;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.joongang.domain.Criteria;
import com.joongang.domain.ReplyPageDTO;
import com.joongang.domain.ReplyVO;
import com.joongang.repository.BoardRepository;
import com.joongang.repository.ReplyRepository;

@Service
public class ReplyServiceImpl implements ReplyService {

	@Autowired
	private ReplyRepository replyRepository;
	
	@Autowired
	private BoardRepository boardRepository;

	@Transactional
	@Override
	public int register(ReplyVO vo) {
		boardRepository.updateReplyCnt(vo.getBno(), 1);
		return replyRepository.insert(vo);
	}

	@Override
	public ReplyVO get(Long rno) {
		return replyRepository.read(rno);
	}

	@Override
	public int modify(ReplyVO vo) {
		return replyRepository.update(vo);
	}

	@Transactional
	@Override
	public int remove(Long rno) {
		ReplyVO vo = replyRepository.read(rno);
		boardRepository.updateReplyCnt(vo.getBno(), -1);
		return replyRepository.delete(rno);
	}

	@Override
	public ReplyPageDTO getList(Criteria criteria, Long bno) {
		return new ReplyPageDTO(
				replyRepository.getReplyCount(bno),
				replyRepository.getList(bno, criteria));
	}

}
