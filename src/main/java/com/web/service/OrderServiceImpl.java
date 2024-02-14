package com.web.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.web.domain.Member;
import com.web.domain.Order;
import com.web.domain.OrderDetail;
import com.web.dto.OrderDto;
import com.web.repository.OrderDetailRepository;
import com.web.repository.OrderRepository;

@Service
public class OrderServiceImpl implements OrderService{
	
	@Autowired
	OrderRepository oRepo;
	
	@Autowired
	OrderDetailRepository deRepo;
	
	@Autowired
	TokenService tService;

	@Override
	@Transactional
	public void addOrder(OrderDto orderDto, String token) {
		System.out.println(orderDto);
		try {
			if(tService.existMember(token)) {
				Member member = tService.getMemberByMemberNum(token);
				System.out.println(member);
				Order order = Order.builder()
						.member(member)
						.totalPrice(orderDto.getTotalPrice())
						.build();
				oRepo.save(order);
				
				System.out.println(orderDto.getIsbn().size());
				System.out.println(orderDto.getIsbn().get(0));
				System.out.println("책수량 "+ orderDto.getBookCount().get(0));
				
				OrderDetail orderDetail;
				for(int i = 0 ; i<orderDto.getIsbn().size(); i++) {
					orderDetail = OrderDetail.builder()
							.isbn(orderDto.getIsbn().get(i))
							.count(orderDto.getBookCount().get(i))
							.order(order)
							.build();
					deRepo.save(orderDetail);
				}
			}else {
				System.out.println("로그인 해주세요");
			}

			
		} catch (Exception e) {
			System.out.println(e);
		}
		
		
		
	}
	
}
