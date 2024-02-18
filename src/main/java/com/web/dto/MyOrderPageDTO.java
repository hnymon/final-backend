package com.web.dto;

import java.util.List;

import lombok.Data;

@Data
public class MyOrderPageDTO {
	
	private List<MyOrderDTO> myOrder;
	private Long count;
	private int size;
	private int page;

}
