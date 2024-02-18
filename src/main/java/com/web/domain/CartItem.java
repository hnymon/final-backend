package com.web.domain;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.SequenceGenerator;
import javax.persistence.Table;

import com.fasterxml.jackson.annotation.JsonIgnore;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;

@Getter
@Entity
@Builder
@NoArgsConstructor
@AllArgsConstructor
@SequenceGenerator(name="CART_ITEM_SEQ_GENERATOR", sequenceName="CART_ITEM_SEQ", allocationSize = 1)
@Table(name = "cart_item")
public class CartItem extends BaseEntity{
	
	@Id
	@Column(name = "CART_ITEM_ID")
	@GeneratedValue(strategy = GenerationType.SEQUENCE, generator = "CART_ITEM_SEQ_GENERATOR" )
	private Long Id;

	private String isbn;
	private String title;
	private Long salePrice;
	private String thumbnail;
	
	@ManyToOne(fetch = FetchType.LAZY) @JsonIgnore
	@JoinColumn(name="CART_ID")
	private Cart cart;
	
//	@ManyToOne(fetch = FetchType.LAZY)
//	@JoinColumn(name="BOOK_ID")
//	private Book Book;
	
	
	private Long count;//수량
	
	
	
}
