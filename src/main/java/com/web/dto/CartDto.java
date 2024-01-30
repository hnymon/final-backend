package com.web.dto;

import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@NoArgsConstructor
public class CartDto {
	private CartItemDto cartItem;
	private Long totalCount;
	private Long deliveryFee;
}
