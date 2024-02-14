package com.web.repository;

import org.springframework.data.repository.CrudRepository;

import com.web.domain.OrderDetail;

public interface OrderDetailRepository extends CrudRepository<OrderDetail, Long> {

}
