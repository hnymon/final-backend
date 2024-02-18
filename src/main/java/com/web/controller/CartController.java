package com.web.controller;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpHeaders;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestHeader;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.web.dto.CartItemDto;
import com.web.dto.OrderDto;
import com.web.service.CartService;

@RestController
@RequestMapping("/cart")
public class CartController {
	
	@Autowired
	CartService cartService;
	
	//장바구니 목록 보기
	@GetMapping
	public List<CartItemDto> cartList(@RequestHeader(name = HttpHeaders.AUTHORIZATION, required = false) String token) {
		return cartService.cartList(token);
	}
	
	//장바구니에 추가
	@PostMapping("/add")
	public void addBook(@RequestBody CartItemDto cartDto, @RequestHeader(name = HttpHeaders.AUTHORIZATION, required = false) String token){
		System.out.println(cartDto +" "+  cartDto.getCount() + " "+ token);
		cartService.addCart(cartDto, token);
	}
	
	//장바구니 삭제
	@Transactional
	@DeleteMapping("/delete/{isbn}")
	public void deleteBook(@PathVariable String isbn) {
		cartService.deleteCartitem(isbn);
	}
	
	//주문 페이지 이동
//	@GetMapping("/order")
//	public void orderPage() {
////		cartService.goToOrder();
//	}
	
	@GetMapping("/count")
	public int CartItemCount(@RequestHeader(name = HttpHeaders.AUTHORIZATION, required = false) String token) {
		return cartService.countItem(token);
	}

}
