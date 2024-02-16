package com.web.dto;

import java.util.Date;

import com.web.domain.Member;

import lombok.Data;

@Data
public class CommentDTO {
	private Long commentId;
	private String commentContent;
	private Date commentDate;
	private String isbn;
	private int starRating;
	private String memberName; // 회원 이름인데 필요없으면 제외시키겠음
	private Member member;
	
}
