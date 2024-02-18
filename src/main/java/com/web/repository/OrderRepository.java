package com.web.repository;

import java.util.List;

import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.domain.Sort;
import org.springframework.data.repository.CrudRepository;

import com.web.domain.Order;

public interface OrderRepository extends CrudRepository<Order, Long> {
	List<Order> findAll();
	List<Order> findAll(Sort sort);
	List<Order> findByMemberMemberNum(Long memberNum);
	
	Page<Order> findByMemberMemberNum(Pageable pageable, Long memberNum);
	
}
