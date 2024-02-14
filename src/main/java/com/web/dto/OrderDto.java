package com.web.dto;

import java.util.List;

import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@NoArgsConstructor
public class OrderDto {
	private List<String> isbn;
	private List<Long> bookCount;
//	private List<Long> bookPrice;
	private Long totalPrice;
	
	private DeliveryInfo deliveryInfo;
//	private Long deliveryFee;
}
