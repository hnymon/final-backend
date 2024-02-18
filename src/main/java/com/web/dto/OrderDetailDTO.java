package com.web.dto;

import com.web.domain.OrderDetail;

import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@NoArgsConstructor
public class OrderDetailDTO {
	private String isbn;
	private String title;
	private String thumbnail;
	private Long price;
	private Long count;
	private boolean detailApproval;
	
	public OrderDetailDTO(OrderDetail orderDetail) {
		this.isbn = orderDetail.getIsbn();
		this.title = orderDetail.getTitle();
		this.thumbnail = orderDetail.getThumbnail();
		this.price = orderDetail.getPrice();
		this.count = orderDetail.getCount();
		this.detailApproval = orderDetail.getDetailApproval();
	}

}
