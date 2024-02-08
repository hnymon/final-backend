package com.web.service;

import java.util.List;

import org.springframework.http.ResponseEntity;

import com.web.dto.CartItemDto;
import com.web.dto.OrderDto;

public interface CartService {
	
	public List<CartItemDto> cartList(String token);
	public void addCart(CartItemDto cartdto, String token);
	public ResponseEntity<String> deleteCartitem(String isbn);
	
}
