package com.web.service;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.web.domain.Cart;
import com.web.domain.CartItem;
import com.web.domain.Member;
import com.web.dto.CartItemDto;
import com.web.dto.OrderDto;
import com.web.jwt.JWTUtil;
import com.web.repository.CartItemRepogitory;
import com.web.repository.CartRepogitory;
import com.web.repository.MemberRepository;

@Service
public class CartServiceImpl implements CartService {
	
	@Autowired
	CartRepogitory cRepo;
	
	@Autowired
	CartItemRepogitory itemRepo;
	
	@Autowired
	MemberRepository mRepo;
	
	@Autowired
	TokenService tService;
	
	@Override
	public List<CartItemDto> cartList(String token) {
		if(tService.existMember(token)) {
			Member member = tService.getMemberByMemberNum(token);
			Cart cart = cRepo.findByMember(member);
			if(cart == null) {
				return null;
			}
			List<CartItemDto> cartItems = cart.getCartItems().stream()
					.map(e -> new CartItemDto(e))
					.collect(Collectors.toList());
			
			return cartItems;
			
		}
		return null;
	}
	
	
	@Override
	public void addCart(CartItemDto cartdto, String token) {
		
		if (tService.existMember(token)) {
			Member member = tService.getMemberByMemberNum(token);
            
            Cart cart = cRepo.findByMember(member);
            
            if (cart == null) {
                // 검색 결과가 없는 경우에만 새로운 Cart 생성
                cart = Cart.builder()
                        .member(member)
                        .build();
                cRepo.save(cart);
            }
            
         // 중복 체크
            if (!isDuplicateCartItem(cartdto.getIsbn(), cart)) {
                // 중복되지 않는 경우에만 CartItem 추가
            	CartItem cartItem = CartItem.builder()
                        .isbn(cartdto.getIsbn())
                        .title(cartdto.getTitle())
                        .salePrice(cartdto.getSalePrice())
                        .thumbnail(cartdto.getThumbnail())
                        .count(cartdto.getCount())
                        .cart(cart)
                        .build();
                itemRepo.save(cartItem);
                
            } else {
                throw new RuntimeException("이 상품은 이미 장바구니에 존재합니다.");
            }
            
        }else {
        	throw new RuntimeException("로그인 해주세요");
        }
			
	}
	
    // 중복 체크 메서드
    private boolean isDuplicateCartItem(String isbn, Cart cart) {
    	CartItem cartItem = itemRepo.findByIsbnAndCart(isbn, cart);
        if(cartItem == null) {
        	return false;
        }
        return true;
    }


	@Override
	public ResponseEntity<String> deleteCartitem(String isbn) {

		try {
			itemRepo.deleteByIsbn(isbn);
			return ResponseEntity.ok().build();
			
		} catch (Exception e) {
			return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).body("삭제 실패");
		}
	}


	@Override
	@Transactional
	public int countItem(String token) {
		if(tService.existMember(token)) {
			Member member = tService.getMemberByMemberNum(token);
			int result = member.getCart().getCartItems().size();
			System.out.println("장바구니 개수" + result);
			return result;
		}
		return 0;
	}

}
