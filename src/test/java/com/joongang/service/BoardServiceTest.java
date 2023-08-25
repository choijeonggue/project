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
import com.joongang.domain.Criteria;

import lombok.extern.log4j.Log4j;

@RunWith(SpringJUnit4ClassRunner.class)
@ContextConfiguration(classes = {RootConfig.class, ServletConfig.class})
@WebAppConfiguration
@Log4j
public class BoardServiceTest {

	@Autowired
	private BoardService boardService;
	
	
	@Test
	public void test() {
		boardService.getList(new Criteria()).forEach(b->log.info(b));
	
	}

	
	
	
}