package com.web.dto;

import java.time.LocalDateTime;
import java.util.Date;
import java.util.List;
import java.util.stream.Collectors;

import com.web.domain.Order;

import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@NoArgsConstructor
public class MyOrderDTO {
	
	private Long id;
	private Long totalPrice;
	private int deliveryFee;
	private String approval;
	private List<OrderDetailDTO> orderDetailList;
	private LocalDateTime orderDate;
	
	public MyOrderDTO(Order order) {
		this.id= order.getId();
		this.totalPrice = order.getTotalPrice();
		this.deliveryFee = order.getDeliveryFee();
		this.approval = order.getApproval();
		this.orderDate = order.getCreateDate();
		this.orderDetailList = order.getOrderdetail().stream()
				.map(e -> new OrderDetailDTO(e))
				.collect(Collectors.toList());
	}
	

}
