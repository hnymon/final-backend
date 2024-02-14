package com.web.service;

import org.springframework.stereotype.Service;

import com.web.dto.OrderDto;

public interface OrderService {

	public void addOrder(OrderDto orderDto, String token);
}
