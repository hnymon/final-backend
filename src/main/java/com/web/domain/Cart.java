package com.web.domain;

import java.util.ArrayList;
import java.util.List;

import javax.persistence.CascadeType;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.OneToMany;
import javax.persistence.SequenceGenerator;
import javax.persistence.Table;

import lombok.Getter;

@Getter
@Entity
@SequenceGenerator(name="CART_SEQ_GENERATOR", sequenceName="CART_SEQ", allocationSize = 1)
@Table(name="CART")
public class Cart extends BaseEntity{
	
	@Id
	@Column(name = "CART_ID")
	@GeneratedValue(strategy = GenerationType.SEQUENCE, generator = "CART_SEQ_GENERATOR" )
	private Long Id;
	
	@OneToMany(mappedBy = "cart", cascade = CascadeType.ALL, orphanRemoval = true)
	private List<CartItem> cartItems = new ArrayList<>();
	
	private Long deliveryFee;
	
	
}
