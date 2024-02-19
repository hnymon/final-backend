package com.web.service;

import java.util.List;

import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;

import com.web.dto.DeliveryInfo;
import com.web.dto.MyOrderDTO;
import com.web.dto.MyOrderPageDTO;
import com.web.dto.OrderAdminDTO;
import com.web.dto.OrderDto;

public interface OrderService {

	public void addOrder(OrderDto orderDto, String token);
	public DeliveryInfo loadDefaultDelivery(String token);
	public List<DeliveryInfo> loadDeliveryList(String token);
	public List<OrderAdminDTO> getOrderList();
	public List<OrderAdminDTO> getOrderDetailList(Long id);
	public String approval(OrderAdminDTO dto);
	public String approvalModal(Long detailId, Long orderId);
	public MyOrderPageDTO loadMyOrder(Pageable pageable, String token);
}
