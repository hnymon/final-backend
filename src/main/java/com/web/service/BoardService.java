package com.web.service;

import java.util.List;

import com.web.domain.BoardEntity;

public interface BoardService {
	public BoardEntity BoardCreate(BoardEntity boardEntity);
	//게시판 전체 리스트 출력
	public List<BoardEntity> BoardList();
	//게시판 상세글 보기 =
	public BoardEntity BoardDetail(Long boardSeq);
	// 게시판 글삭제 
	public void BoardDelete(Long boardSeq);
	
	public BoardEntity Edit(BoardEntity boardEntity);
	
}
 