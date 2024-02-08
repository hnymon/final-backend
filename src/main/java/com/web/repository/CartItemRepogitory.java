package com.web.repository;

import org.springframework.data.repository.CrudRepository;

import com.web.domain.CartItem;

public interface CartItemRepogitory extends CrudRepository<CartItem, Long> {

	CartItem findByIsbn13(String isbn13);

	
}
