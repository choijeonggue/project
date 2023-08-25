package com.joongang.service;

import java.io.File;
import java.util.List;
import java.util.stream.Collectors;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.joongang.domain.BoardAttachVO;
import com.joongang.domain.BoardVO;
import com.joongang.domain.Criteria;
import com.joongang.repository.BoardAttachRepository;
import com.joongang.repository.BoardRepository;

import lombok.extern.log4j.Log4j;

@Service
@Log4j
public class BoardServiceImpl implements BoardService {

	
	@Autowired
	private BoardRepository boardRepositroy;
	
	@Autowired
	private  BoardAttachRepository boardAttachRepository;
	

	@Override
	public List<BoardVO> getList(Criteria criteria) {
		return boardRepositroy.getList(criteria);
	}
	
	@Transactional
	@Override
	public void register(BoardVO board) {
		boardRepositroy.insertSelectKey(board);
		//첨부파일이 있을 때
		if(board.getAttachList()!=null && !board.getAttachList().isEmpty()) {
			board.getAttachList().forEach(attachFile->{
				attachFile.setBno(board.getBno());
				boardAttachRepository.insert(attachFile);
			});
		}
	}

	@Override
	public BoardVO get(Long bno) {
		return boardRepositroy.read(bno);
	}

	@Override
	public boolean modify(BoardVO board) {
		
		List<BoardAttachVO> attachList = board.getAttachList();
		
		if(attachList!=null) {
			List<BoardAttachVO> delList = attachList.stream().filter(attach->attach.getBno()!=null).collect(Collectors.toList());
			deleteFiles(delList);
			delList.forEach(vo->{
				boardAttachRepository.delete(vo.getUuid()); // 데이터 베이스 기록 삭제
			});
			
			//새로운 파일 추가
			
			attachList.stream().filter(attach->attach.getBno()==null).forEach(vo->{
				vo.setBno(board.getBno());
				boardAttachRepository.insert(vo); // 데이터 베이스 기록
			});
		}
		
		return boardRepositroy.update(board)==1;
	}
	
	private void deleteFiles(List<BoardAttachVO> delList) {
		delList.forEach(vo->{
			File file = new File("C:/storage/"+vo.getUploadPath(),vo.getUuid() + "_" + vo.getFileName());
			file.delete();
			if(vo.isFileType()) {
				file = new File("C:/storage/"+vo.getUploadPath(),"s_"+vo.getUuid() + "_" + vo.getFileName());
				file.delete();
			}
		});
	}

	@Transactional
	@Override
	public boolean remove(Long bno) {
		List<BoardAttachVO> attachList = getAttachList(bno);
		if(attachList!=null) {
			deleteFiles(attachList);
			boardAttachRepository.deleteAll(bno);
		}
		return boardRepositroy.delete(bno)==1;
	}

	@Override
	public int totalCount(Criteria criteria) {
		return boardRepositroy.getTotalCount(criteria);
	}
	
	@Override
	public List<BoardAttachVO> getAttachList(Long bno) {
		return boardAttachRepository.selectByBno(bno);
	}

	@Override
	public BoardAttachVO getAttach(String uuid) {
		return boardAttachRepository.selectByUuid(uuid);
	}

}
