package com.web.repository;

import org.springframework.data.repository.CrudRepository;

import com.web.domain.Cart;
import com.web.domain.CartItem;
import com.web.domain.Member;

public interface CartItemRepogitory extends CrudRepository<CartItem, Long> {

	CartItem findByIsbn(String isbn);

	CartItem findByIsbnAndCart(String isbn, Cart cart);

	void deleteByIsbn(String isbn);

	
}
