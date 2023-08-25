package com.joongang.repository;

import java.util.Iterator;

import org.junit.Ignore;
import org.junit.Test;
import org.springframework.beans.factory.annotation.Autowired;

import com.joongang.AppTest;
import com.joongang.domain.BoardVO;

public class DataInsert extends AppTest{
	
	@Autowired
	BoardRepository repository;
	
	@Test
	@Ignore
	public void test() {
		
		for (int i = 0; i <=408; i++) {
			BoardVO vo = BoardVO.builder()
					.title("제목 : 페이징 처리" + i)
					.content("내용 : 페이징 처리"+i)
					.writer("작성자"+(i%5))
					.build();
			repository.insert(vo);
		}
	}
	@Test
	public void test2() {
		
		for(int i=1;i<=212;i++) {
			BoardVO vo = BoardVO.builder()
					.title("가전제품 " + i)
					.content("튼튼함" + i)
					.writer("작성자" + (i%5))
					.build();
			repository.insert(vo);			
		}
		
		for(int i=1;i<=212;i++) {
			BoardVO vo = BoardVO.builder()
					.title("의류 " + i)
					.content("신발" + i)
					.writer("글쓴이" + (i%5))
					.build();
			repository.insert(vo);			
		}
		
		for(int i=1;i<=212;i++) {
			BoardVO vo = BoardVO.builder()
					.title("등록상품" + i)
					.content("불량" + i)
					.writer("관리자" + (i%5))
					.build();
			repository.insert(vo);			
		}
		
		for(int i=1;i<=212;i++) {
			BoardVO vo = BoardVO.builder()
					.title("중고상품 " + i)
					.content("등록" + i)
					.writer("등록한사람" + (i%5))
					.build();
			repository.insert(vo);			
		}
	}
	
	
	
}
