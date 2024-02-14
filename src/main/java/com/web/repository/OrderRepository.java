package com.web.repository;

import org.springframework.data.repository.CrudRepository;

import com.web.domain.Order;


public interface OrderRepository extends CrudRepository<Order, Long> {

}
