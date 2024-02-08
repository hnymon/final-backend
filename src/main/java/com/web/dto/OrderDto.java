package com.web.dto;

import java.util.List;

import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@NoArgsConstructor
public class OrderDto {
	private List<CartItemDto> cartItems;
	private Long totalCount;
	private Long deliveryFee;
}
