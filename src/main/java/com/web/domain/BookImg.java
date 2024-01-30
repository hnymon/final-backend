package com.web.domain;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.SequenceGenerator;
import javax.persistence.Table;

import lombok.Getter;

@Getter
@Entity
@SequenceGenerator(name="BOOK_IMG_SEQ_GENERATOR", sequenceName="BOOK_IMG_SEQ", allocationSize = 1)
@Table(name ="BOOK_IMG")
public class BookImg {

	@Id
	@Column(name = "BOOK_IMG_ID")
	@GeneratedValue(strategy = GenerationType.SEQUENCE, generator = "BOOK_IMG_SEQ_GENERATOR" )
	private Long Id;
	
	private String imgName;
	private String path;
}
