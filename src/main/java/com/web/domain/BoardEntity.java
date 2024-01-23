package com.web.domain;

import java.util.Date;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.Table;

import lombok.Data;
// 공지사항 게시판
@Entity
@Table(name = "BOARD_TAB")
@Data
public class BoardEntity {
	//게시판 교유 번호
	@Id
	@GeneratedValue(strategy = GenerationType.TABLE,generator = "BOARD_SEQ")
	private Long boardSeq;
	//게시판 제목
	@Column(name = "BOARD_TITLE",nullable = false)
	private String boardTitle;
	//게시판 내용
	@Column(name = "BOARD_COUNTENT",nullable = false)
	private String boardContent;
	//게시판 날짜
	@Column(name = "BOARD_DATE",insertable = false, updatable = false, columnDefinition = "date default sysdate")
	private Date boardDate;
	// 조회수
	@Column(name = "BOARD_VIEWS")
	private Long boardViews;
	//작성자 admin
	@Column(name = "ADMIN")
	private String admin;	
}
