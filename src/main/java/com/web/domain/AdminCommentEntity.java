package com.web.domain;

import java.util.Date;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.OneToOne;
import javax.persistence.Table;
import javax.persistence.TableGenerator;

import com.fasterxml.jackson.annotation.JsonIgnore;

import lombok.Data;

@Data
@Entity
@Table(name = "ADMIN_COMMENT")
@TableGenerator(name = "ADMIN_COMMENT_SEQ",	// 테이블 이름  
table = "ALL_SEQUENCE", 		// 시퀀스 생성
pkColumnValue = "COMMENTSEQ",	// 시퀀스 이름
initialValue = 0,				// 시퀀스 시작 값
allocationSize = 1)
public class AdminCommentEntity {
	@Id
	@GeneratedValue(strategy = GenerationType.TABLE,generator = "ADMIN_COMMENT_SEQ")
	@Column(name = "ADMIN_ID") // 고유 답변 번호
	private Long adminId;
	@Column(name = "ADMIN_COMMENT") // admin 답변 등록
	private String adminComment;
	@Column(name = "ADMIN_COMMENT_DATE" ,insertable = false, updatable = false , columnDefinition = "date default sysdate")
	private Date adminCommentDate;	// admin 답변 날짜
	@Column(name = "INQUIRY_Num") // 1대1문의 번호
	private Long inquiryId;
	
	@OneToOne
	@JoinColumn(name = "INQUIRY")
	@JsonIgnore
	private OneToOneInquiryEntity Inquery; // 외래키
}
