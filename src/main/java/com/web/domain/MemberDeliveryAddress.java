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

import lombok.Builder;
import lombok.Data;

@Data
@Builder
@SequenceGenerator(name="MEMBER_SEQ_GENERATOR", sequenceName="ADDRESS_SEQ", allocationSize = 1)
@Table(name="MEMBER_DELIBERY_ADR")
@Entity
public class MemberDeliveryAddress {
	@Id
	@Column(name = "ADDR_NUM")
	@GeneratedValue(strategy = GenerationType.SEQUENCE)
	private Long addrNum;			// 배송지 번호
	private String recipientName;	// 수령인
	private String recipientTel;	// 수령인 연락처
	private String zipcode;			// 우편번호
	private String address;			// 주소
	private String addrDetail;		// 상세주소
	private String deliberyRequest;	// 배송 요청사항
	private boolean isDefault;		// 기본배송지 여부
	
	@ManyToOne(fetch = FetchType.LAZY)
	@JoinColumn(name="MEMBER_NUM")
	private Member member;
}

