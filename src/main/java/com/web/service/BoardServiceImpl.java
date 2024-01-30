package com.web.service;

import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.web.domain.BoardEntity;
import com.web.repository.BoardRepository;
@Service
public class BoardServiceImpl implements BoardService{
	
	@Autowired
	private BoardRepository boardRepository;
	@Override
	public BoardEntity BoardCreate(BoardEntity boardEntity) {
		// TODO Auto-generated method stub
		return boardRepository.save(boardEntity);
	}
	//게시판 전체 리스트 출력
	@Override
	public List<BoardEntity> BoardList() {
		// TODO Auto-generated method stub
		return boardRepository.findAll();
	}
	@Override
	public BoardEntity BoardDetail(Long boardSeq) {
		// TODO Auto-generated method stub
		Optional<BoardEntity> optional = boardRepository.findById(boardSeq);
		// findById 로 찾으면 타입이 optional 이된다  isPresent() -> boolean 타입으로 거짓이면 null
		if(optional.isPresent()) {
			BoardEntity boardEntity = optional.get();
			return boardEntity;
		}
		return null;
	} 
	@Override
	public void BoardDelete(Long boardSeq) {
		// TODO Auto-generated method stub
	boardRepository.deleteById(boardSeq);
	}
	@Override
	public BoardEntity Edit(BoardEntity boardEntity) {
		// TODO Auto-generated method stub
		return boardRepository.save(boardEntity);
	}
	
}
