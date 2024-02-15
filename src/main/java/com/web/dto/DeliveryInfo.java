package com.web.dto;

import com.web.domain.CartItem;
import com.web.domain.MemberDeliveryAddress;

import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@NoArgsConstructor
public class DeliveryInfo {
	private String name; // 수령인
	private String phone; // 수령인 연락처
	private String zipcode; // 우편번호
	private String address; // 주소
	private String addrDetail; // 상세주소
	
	public DeliveryInfo(MemberDeliveryAddress deliveryAddr) {
		this.name = deliveryAddr.getRecipientName();
		this.phone = deliveryAddr.getRecipientTel();
		this.zipcode = deliveryAddr.getZipcode();
		this.address = deliveryAddr.getAddress();
		this.addrDetail = deliveryAddr.getAddrDetail();
	}
}
