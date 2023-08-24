package com.joongang.repository;

import static org.junit.Assert.*;

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
	private BoardRepository mapper;
	
	@Test
	@Ignore
	public void test() {
		mapper.getList().forEach(board -> log.info(board));
	}
	
	@Test
	public void testInsertSelectKey() {
		BoardVO board = new BoardVO();
		board.setTitle("새로 작성하는 글 select key");
		board.setContent("새로 작성하는 내용 select key");
		board.setWriter("newbie");
		mapper.insertSelectKey(board);
		log.info(board);
		
	}
	@Test
	@Ignore
	public void testRead() {
		BoardVO board = mapper.read(5L);
		log.info(board);
	}
	
	@Test
	@Ignore
	public void testDelete() {
		log.info("DELETE COUNT : "+ mapper.delete(3L));
	}

	@Test
	public void testUpdate() {
		BoardVO board = new BoardVO();
		
		board.setBno(5L);
		board.setTitle("수정된 제목");
		board.setContent("수정된 내용");
		board.setWriter("user00");
		
		int count = mapper.update(board);
		log.info("UPDATE COUNT : "+ count);
	}
}