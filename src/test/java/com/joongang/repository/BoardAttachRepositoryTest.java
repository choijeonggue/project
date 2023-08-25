package com.joongang.repository;

import static org.junit.Assert.*;

import java.util.UUID;

import org.junit.Ignore;
import org.junit.Test;
import org.springframework.beans.factory.annotation.Autowired;

import com.joongang.AppTest;
import com.joongang.domain.BoardAttachVO;

import lombok.extern.java.Log;
import lombok.extern.log4j.Log4j;

@Log4j
public class BoardAttachRepositoryTest extends AppTest{

	@Autowired
	BoardAttachRepository boardAttachRepository;
	
	@Test
	@Ignore
	public void test() {
		BoardAttachVO vo = new BoardAttachVO();
		vo.setBno(1L);
		vo.setFileName("test02.txt");
		vo.setFileType(false);
		vo.setUploadPath("c:/upload");
		String uuid = UUID.randomUUID().toString();
		vo.setUuid(uuid);
		boardAttachRepository.insert(vo);
	}
	
	@Test
	@Ignore
	public void testSelectByBno() {
		boardAttachRepository.selectByBno(1L)
			.forEach(file->log.info(file));
	}
	
	@Test
	@Ignore
	public void testDelete() {
		String uuid = "333d8255-795e-4576-bcb5-ac88f0aafaef";
		boardAttachRepository.delete(uuid);
	}
	
	
	

}
