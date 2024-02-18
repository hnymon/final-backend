package com.web.dto;

import java.util.Date;

import com.web.domain.CartItem;

import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@NoArgsConstructor
public class BookInfoDTO {
	
	private Long salePrice;
	private String thumbnail;
	private String isbn;
	private String title;
	
	public BookInfoDTO(CartItem cartItem) {
		this.salePrice = cartItem.getSalePrice();
		this.thumbnail = cartItem.getThumbnail();
		this.isbn = cartItem.getIsbn();
		this.title = cartItem.getTitle();
	}
}
