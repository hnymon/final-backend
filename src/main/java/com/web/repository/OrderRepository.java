package com.web.repository;

import java.util.List;

import org.springframework.data.domain.Sort;
import org.springframework.data.jpa.repository.JpaRepository;

import com.web.domain.Order;

public interface OrderRepository extends JpaRepository<Order, Long> {
	List<Order> findAll();
	List<Order> findAll(Sort sort);
	
}
