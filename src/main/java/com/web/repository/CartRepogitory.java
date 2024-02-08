package com.web.repository;

import java.util.List;

import org.springframework.data.repository.CrudRepository;

import com.web.domain.Cart;
import com.web.domain.CartItem;
import com.web.domain.Member;

public interface CartRepogitory extends CrudRepository<Cart, Long> {

	Cart findByMember(Member member);

}
