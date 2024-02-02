package com.web.domain;

import javax.persistence.Entity;
import javax.persistence.Id;

import lombok.Data;

@Data
@Entity
public class BookCrawling {
	@Id
	private String uniqueCol;
	private String isbn10;
	private String isbn13;
	private String type;
	private String bookName;
	private String imgUrl;
}

