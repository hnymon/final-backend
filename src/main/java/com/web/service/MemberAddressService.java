package com.web.service;

import java.util.List;

import com.web.domain.Member;
import com.web.domain.MemberDeliveryAddress;

public interface MemberAddressService {
	public String saveAdr(MemberDeliveryAddress memberDeliveryAddress, Member currentMember);
	public String editAdr(MemberDeliveryAddress memberDeliveryAddress, Member currentMember);
	public String removeAdr(MemberDeliveryAddress memberDeliveryAddress);
	public String setDefaultAddress(MemberDeliveryAddress memberDeliveryAddress);
	public List<MemberDeliveryAddress> findByMemberMemberNum(Long memberNum);
	
}
