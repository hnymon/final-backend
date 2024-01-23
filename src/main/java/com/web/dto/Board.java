package com.web.dto;

import java.util.Date;

import lombok.Data;

@Data
public class Board {
	private Long boardSeq;
	private String boardTitle;
	private String boardContent;
	private Date boardDate;
	private Long boardViews;
	private String admin;
}
