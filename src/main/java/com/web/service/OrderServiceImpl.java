package com.web.service;

import java.util.ArrayList;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Sort;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.web.domain.CartItem;
import com.web.domain.Member;
import com.web.domain.MemberDeliveryAddress;
import com.web.domain.Order;
import com.web.domain.OrderDetail;
import com.web.dto.DeliveryInfo;
import com.web.dto.OrderAdminDTO;
import com.web.dto.OrderDto;
import com.web.repository.CartItemRepogitory;
import com.web.repository.MemberAddressRepository;
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
	
	@Autowired
	MemberAddressRepository addrRepo;
	
	@Autowired
	CartItemRepogitory itemRepo;
	

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
				
				for(int i = 0 ; i<orderDto.getIsbn().size(); i++) {
					String isbn = orderDto.getIsbn().get(i);
					CartItem cartItem = itemRepo.findByIsbn13(isbn);
					if (cartItem != null) {
				        itemRepo.delete(cartItem);
				    } else {
				        System.out.println("해당하는 CartItem이 없습니다.");
				    }
				}
				
				
			}else {
				System.out.println("로그인 해주세요");
			}

			
		} catch (Exception e) {
			System.out.println(e);
		}
		
	}
	
	@Override
	public DeliveryInfo loadDefaultDelivery(String token) {
		Long memberNum = tService.getMemberNum(token);
		System.out.println("memberNum"+memberNum);
		MemberDeliveryAddress defaultAddress = addrRepo.findByIsDefaultAndMemberMemberNum(true, memberNum);
		System.out.println("기본배송지"+defaultAddress);
		if(defaultAddress != null) {
			return new DeliveryInfo(defaultAddress);
		}
		return null;
		
	}
	@Override
	public List<OrderAdminDTO> getOrderList() {
		// TODO Auto-generated method stub
		List<Order> orderList = new ArrayList<>();
		orderList = oRepo.findAll(Sort.by(Sort.Direction.DESC, "id"));
		List<OrderAdminDTO> returnList = new ArrayList<>();
		for(Order odr : orderList) {
			System.out.println(odr);
			OrderAdminDTO dto = new OrderAdminDTO();
			dto.setId(odr.getId());
			dto.setMemberNum(odr.getMember().getMemberNum());
			dto.setTotalPrice(odr.getTotalPrice());
			dto.setDeliveryFee(odr.getDeliveryFee());
			odr.updateApproval();
			dto.setApproval(odr.getApproval());
			dto.setOrderdetail(odr.getOrderdetail());
			dto.setCreateDate(odr.getCreateDate());
			returnList.add(dto);
		}
		return returnList;
	}
	
	@Override
	public List<OrderAdminDTO> getOrderDetailList(Long id) {
		// TODO Auto-generated method stub
		List<OrderDetail> orderList = new ArrayList<>();
		orderList = deRepo.findAllByOrderId(id);
		List<OrderAdminDTO> returnList = new ArrayList<>();
		for(OrderDetail odt : orderList) {
			OrderAdminDTO dto = new OrderAdminDTO();
			dto.setOrderNum(odt.getOrder().getId());
			dto.setCount(odt.getCount());
			dto.setIsbn(odt.getIsbn());
			dto.setOdtNum(odt.getId());
			dto.setDetailApproval(odt.getDetailApproval());
			returnList.add(dto);
		}
		return returnList;
	}

	@Override
	public String approval(OrderAdminDTO dto) {
		// TODO Auto-generated method stub
		try {
			OrderDetail od = deRepo.findById(dto.getOdtNum()).get();
			od.setDetailApproval(!od.getDetailApproval());
			deRepo.save(od);
			System.out.println("gd");
			System.out.println(od);
			return "Success";
		} catch (Exception e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
			return "Failure";
		}
	}
}
