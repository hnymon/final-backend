package com.web.domain;

import java.util.ArrayList;
import java.util.List;

import javax.persistence.CascadeType;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.OneToMany;
import javax.persistence.SequenceGenerator;
import javax.persistence.Table;

import org.hibernate.annotations.ColumnDefault;

import lombok.Builder;
import lombok.Getter;

@Getter
@Builder
@Entity
@SequenceGenerator(name="ORDER_SEQ_GENERATOR", sequenceName="ORDER_SEQ", allocationSize = 1)
@Table(name="orders")
public class Order {

	@Id
	@Column(name="orders_id")
	@GeneratedValue(strategy = GenerationType.SEQUENCE, generator = "ORDER_SEQ_GENERATOR" )
	private Long id;
	
	@ManyToOne(fetch = FetchType.LAZY)
	@JoinColumn(name="MEMBER_NUM")
	private Member member;
	
	@OneToMany(mappedBy = "order", cascade = CascadeType.ALL, orphanRemoval = true)
	private List<OrderDetail> orderdetail = new ArrayList<>();
	
	private Long totalPrice;
	
	@Column(columnDefinition = "int default 3000")
	private int deliveryFee;
	
	
	
	
}
