package com.web.service;

import java.util.List;

import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;

import com.web.domain.CommentEntity;

public interface CommentService {
	
	public CommentEntity CommentArea(CommentEntity commentEntity);
	// 댓글 뽑아내기
	public List<CommentEntity> commnetList();
	
	Page<CommentEntity> getComments(Pageable pageable, String isbn);
	
	public void CommentDelete(Long commentId);
}
