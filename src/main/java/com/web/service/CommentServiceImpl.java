package com.web.service;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;

import com.web.domain.CommentEntity;
import com.web.repository.CommentRepository;

@Service
public class CommentServiceImpl implements CommentService {
	@Autowired
	private CommentRepository commentRepository;

	// 댓글 등록
	@Override
	public CommentEntity CommentArea(CommentEntity commentEntity) {
		// TODO Auto-generated method stub
		return commentRepository.save(commentEntity);
	}

	// 전체 뽑기
	@Override
	public List<CommentEntity> commnetList() {
		// TODO Auto-generated method stub
		return commentRepository.findAll();
	}
	@Override
	public Page<CommentEntity> getComments(Pageable pageable, String isbn) {
		System.out.println(commentRepository.findByIsbn(pageable,isbn)); // fintBy뒤에는 대문자
		return commentRepository.findByIsbn(pageable,isbn);
	}

	@Override
	public void CommentDelete(Long commentId) {
		// TODO Auto-generated method stub
		commentRepository.deleteById(commentId);
	}
	
}
