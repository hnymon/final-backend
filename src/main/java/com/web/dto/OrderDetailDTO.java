package com.web.dto;

import com.web.domain.OrderDetail;

import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@NoArgsConstructor
public class OrderDetailDTO {
	
	private String isbn;
	private Long count;
	private boolean detailApproval;
	
	public OrderDetailDTO(OrderDetail orderDetail) {
		this.isbn = orderDetail.getIsbn();
		this.count = orderDetail.getCount();
		this.detailApproval = orderDetail.getDetailApproval();
	}

}
