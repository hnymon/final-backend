package com.web.service;

import java.util.List;

import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;

import com.web.domain.CommentEntity;
import com.web.dto.CommentDTO;

public interface CommentService {
	
	public String CommentArea(CommentEntity commentEntity);
	// 댓글 뽑아내기
	public List<CommentEntity> commnetList();
	
	List<CommentDTO> getComments(String isbn);
	
	public void CommentDelete(Long commentId);
	
	// 수정 
	public void CommentUpdate(Long commentId,CommentEntity editedCommentContent);
}
 