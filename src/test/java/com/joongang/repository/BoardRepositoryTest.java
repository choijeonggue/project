package com.joongang.repository;

import static org.junit.Assert.*;

import java.util.List;

import org.junit.Ignore;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;
import org.springframework.test.context.web.WebAppConfiguration;

import com.joongang.config.RootConfig;
import com.joongang.config.ServletConfig;
import com.joongang.domain.BoardVO;

import lombok.extern.log4j.Log4j;

@RunWith(SpringJUnit4ClassRunner.class)
@ContextConfiguration(classes= {RootConfig.class, ServletConfig.class})
@WebAppConfiguration
@Log4j
public class BoardRepositoryTest {

	@Autowired
	private BoardRepository boardRepository;
	
	
//	@Test
//	public void testGetList() {
//		List<BoardVO> list = boardRepository.getList(3, 10);
//		log.info(list.size());
//		list.forEach(b->log.info(b));
//	}
	
	@Test
	@Ignore
	public void testInsertSelectKey() {
		BoardVO board = new BoardVO();
		board.setTitle("새로 작성하는 글 select key");
		board.setContent("새로 작성하는 내용 select key");
		board.setWriter("newbie");
		boardRepository.insertSelectKey(board);
		log.info(board);
		
	}
	@Test
	@Ignore
	public void testRead() {
		BoardVO board = boardRepository.read(5L);
		log.info(board);
	}
	
	@Test
	@Ignore
	public void testDelete() {
		log.info("DELETE COUNT : "+ boardRepository.delete(3L));
	}

	@Test
	public void testUpdate() {
		BoardVO board = new BoardVO();
		
		board.setBno(5L);
		board.setTitle("수정된 제목");
		board.setContent("수정된 내용");
		board.setWriter("user00");
		
		int count = boardRepository.update(board);
		log.info("UPDATE COUNT : "+ count);
	}
}