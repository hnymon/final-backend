package com.web.repository;

import org.springframework.data.repository.CrudRepository;

import com.web.domain.Cart;
import com.web.domain.CartItem;
import com.web.domain.Member;

public interface CartItemRepogitory extends CrudRepository<CartItem, Long> {

	CartItem findByIsbn13(String isbn13);

	CartItem findByIsbn13AndCart(String isbn13, Cart cart);

	void deleteByIsbn13(String isbn);

	
}
