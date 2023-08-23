package com.joongang.service;

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
@ContextConfiguration(classes = {RootConfig.class, ServletConfig.class})
@WebAppConfiguration
@Log4j
public class BoardServiceTest {

	@Autowired
	private BoardService service;
	
	
	@Test
	@Ignore
	public void test() {
		log.info(service);
		assertNotNull(service);
	}

	@Test
	@Ignore
	public void testRegister() {
		BoardVO board = new BoardVO();
		board.setTitle("새로 작성한느 글");
		board.setContent("새로작성하는 내용");
		board.setWriter("newbie");
		service.register(board);
		log.info("생성된 게시물의 번호 : " + board.getBno());
		
	}
	
	@Test
	@Ignore
	public void testGetList() {
		service.getList().forEach(board->log.info(board));
	}
	
	@Test
	@Ignore
	public void testGet() {
		log.info(service.get(1L));
	}
	
	@Test
	public void testUpdate() {
		BoardVO board = service.get(1L);
		assertNotNull(board);
		board.setTitle("제목 수정합니다.");
		log.info("MOIDIFY RESULT : "+ service.modify(board));
	}
	
	
	@Test
	public void testDelete() {
		//게시물 번호의 존재 여부를 확인한고 테스트 할 것!!!
		log.info("REMOVE RESULT : "+ service.remove(2L));
	}
	
	
}