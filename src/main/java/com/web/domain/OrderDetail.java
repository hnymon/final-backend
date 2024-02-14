package com.web.domain;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.OneToOne;
import javax.persistence.SequenceGenerator;
import javax.persistence.Table;

import lombok.Builder;
import lombok.Getter;

@Getter
@Builder
@Entity
@SequenceGenerator(name="ORDER_DETAIL_SEQ_GENERATOR", sequenceName="ORDER_DETAIL_SEQ", allocationSize = 1)
@Table(name="ORDER_DETAIL")
public class OrderDetail {
	
	@Id
	@Column(name="order_datail_id")
	@GeneratedValue(strategy = GenerationType.SEQUENCE, generator = "ORDER_DETAIL_SEQ_GENERATOR" )
	private Long id;
	
	@ManyToOne(fetch = FetchType.LAZY)
	@JoinColumn(name="orders_id")
	private Order order;
	
	private String isbn;
	private Long count;
	
//	@OneToOne
//	@JoinColumn(name="CART_ITEM_ID")
//	private CartItem cartItem;
	
	

}
