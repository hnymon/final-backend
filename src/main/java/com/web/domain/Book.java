package com.web.domain;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.OneToOne;
import javax.persistence.SequenceGenerator;
import javax.persistence.Table;

import lombok.Getter;

@Getter
@Entity
@SequenceGenerator(name="BOOK_SEQ_GENERATOR", sequenceName="BOOK_SEQ", allocationSize = 1)
@Table(name = "BOOK")
public class Book extends BaseEntity {
	
	@Id
	@Column(name = "BOOK_ID")
	@GeneratedValue(strategy = GenerationType.SEQUENCE, generator = "BOOK_SEQ_GENERATOR" )
	private Long Id;
	
	private String title; //책이름
	private String author; //작가명
	private int price; //가격
	private int discount; //할인율
	private Long stock; //재고
	
	@OneToOne
	@JoinColumn(name = "BOOK_IMG_ID")
	private BookImg bookImg;

}
