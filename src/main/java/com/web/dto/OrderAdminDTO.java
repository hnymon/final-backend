package com.web.dto;

import java.time.LocalDate;
import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;

import com.web.domain.OrderDetail;

import lombok.Data;

@Data
public class OrderAdminDTO {
	
	 private Long id;
	 private Long memberNum;
	 private Long totalPrice;
	 private int deliveryFee;
	 private List<OrderDetail> orderdetail = new ArrayList<>();
	 private LocalDateTime createDate;
	 private String approval;
	 
	 private Long orderNum;
	 private Long count;
	 private String isbn;
	 private Long odtNum;
	 private boolean detailApproval;
	 
}
