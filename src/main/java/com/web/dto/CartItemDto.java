package com.web.dto;

import com.web.domain.CartItem;

import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@NoArgsConstructor
public class CartItemDto {
	private String isbn13;
	private Long count;
	
	public CartItemDto(CartItem cartItem) {
		isbn13 = cartItem.getIsbn13();
		count = cartItem.getCount();
	}
}
