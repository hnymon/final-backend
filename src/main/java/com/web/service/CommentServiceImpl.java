package com.web.service;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;

import com.web.domain.CommentEntity;
import com.web.dto.CommentDTO;
import com.web.repository.CommentRepository;

@Service
public class CommentServiceImpl implements CommentService {
	@Autowired
	private CommentRepository commentRepository;

	// 댓글 등록
	@Override
	public String CommentArea(CommentEntity commentEntity) {
		// TODO Auto-generated method stub
		try {
			Optional<CommentEntity> optional = commentRepository.findByIsbnAndMemberMemberNum(commentEntity.getIsbn(),
					commentEntity.getMember().getMemberNum());
		  
			if (optional.isPresent()) {
				return "Failure";
			}
//			boolean a = commentEntity.getMember().getMemberName() == null;
			if(commentEntity.getMember().getMemberName() == null) {
				commentEntity.setMemberName(commentEntity.getMember().getSocialType()+" 회원"+ commentEntity.getMember().getMemberNum() );
			}
			commentRepository.save(commentEntity);
		} catch (Exception e) {
			e.printStackTrace();
		}
		return "Success";
	}

	// 전체 뽑기
	@Override
	public List<CommentEntity> commnetList() {
		// TODO Auto-generated method stub
		return commentRepository.findAll();
	}
	// 페이징처리 Entity형식
//	@Override
//	public Page<CommentEntity> getComments(Pageable pageable, String isbn) {
////		System.out.println(commentRepository.findAllByIsbn(pageable,isbn)); // fintBy뒤에는 대문자
//		return commentRepository.findByIsbn(pageable, isbn);
//	}
	@Override
	public List<CommentDTO> getComments(String isbn) {
		List<CommentEntity> list = commentRepository.findByIsbn(isbn);
		List<CommentDTO> returnList = new ArrayList<>();
		for(CommentEntity ce : list) {
			CommentDTO dto = new CommentDTO();
			dto.setCommentContent(ce.getCommentContent());
			dto.setCommentDate(ce.getCommentDate());
			dto.setCommentId(ce.getCommentId());
			dto.setIsbn(ce.getIsbn());
			dto.setMemberNum(ce.getMember().getMemberNum());
			dto.setMemberName(ce.getMemberName());
			dto.setStarRating(ce.getStarRating());
			returnList.add(dto);
		}
		return returnList;
	}

	@Override
	public void CommentDelete(Long commentId) {
		// TODO Auto-generated method stub
		commentRepository.deleteById(commentId);
	}
	// 수정
	@Override
	public void CommentUpdate(Long commentId,CommentEntity editedCommentContent) {
		// TODO Auto-generated method stub
		Optional<CommentEntity> optional = commentRepository.findById(commentId);
		optional.ifPresent(comment -> {
            comment.setCommentContent(editedCommentContent.getCommentContent());
            comment.setStarRating(editedCommentContent.getStarRating());
            commentRepository.save(comment);
        });
	}
	

}
