package com.web.repository;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;

import com.web.domain.MemberDeliveryAddress;

public interface MemberAddressRepository extends JpaRepository<MemberDeliveryAddress, Long>{
	List<MemberDeliveryAddress> findAllByMemberMemberNum(Long memberNum);
}
