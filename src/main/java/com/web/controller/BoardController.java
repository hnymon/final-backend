package com.web.controller;

import java.util.List;

import javax.websocket.server.PathParam;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.web.domain.BoardEntity;
import com.web.service.BoardService;

@RestController
@RequestMapping("/board")
public class BoardController {
	@Autowired
	private BoardService boardService;
	
	@PostMapping("/BoardCreate")
	public BoardEntity BoardCreate(@RequestBody BoardEntity boardEntity) {
		System.out.println(boardEntity);
		return boardService.BoardCreate(boardEntity);
	}
	//게시판 전체 리스트 출력
	@GetMapping("/BoardList")
	public List<BoardEntity> BoardList() {
		List<BoardEntity> list = boardService.BoardList();
		return list;
	}
	@GetMapping("/BoardDetail/{boardSeq}")
	public BoardEntity BoardDetail(@PathVariable Long boardSeq){
		System.out.println(boardService.BoardDetail(boardSeq));
		return boardService.BoardDetail(boardSeq);
	}
	@DeleteMapping("BoardDelete/{boardSeq}")
	public void BoardDelete(@PathVariable Long boardSeq) {
		 boardService.BoardDelete(boardSeq);
	}
}  
