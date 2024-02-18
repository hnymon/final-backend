package com.web.dto;

import java.util.List;

import com.web.domain.OrderDetail;

import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@NoArgsConstructor
public class OrderDto {
	private List<String> isbn;
	private List<String> title;
	private List<String> thumbnail;
	private List<Long> price;
	private List<Long> bookCount;
	private Long totalPrice;
	
	private DeliveryInfo deliveryInfo;
	
}
