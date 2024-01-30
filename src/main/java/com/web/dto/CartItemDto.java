package com.web.dto;


import com.web.domain.BookImg;

import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@NoArgsConstructor
public class CartItemDto {
	private String title;
	private String author;
	private int price;
	private int discount;
	private BookImg bookImg;
	private Long count;
}
