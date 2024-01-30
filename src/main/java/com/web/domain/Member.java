package com.web.domain;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.SequenceGenerator;
import javax.persistence.Table;

import lombok.Getter;
import lombok.Setter;
import lombok.ToString;

@Getter
@Setter
@ToString
@Entity
@SequenceGenerator(name="MEMBER_SEQ_GENERATOR", sequenceName="MEMBER_SEQ", allocationSize = 1)
@Table(name="MEMBER")
public class Member extends BaseEntity{

	@Id
	@GeneratedValue(strategy = GenerationType.SEQUENCE)
	private Long memberNum;

	private String memberName; //회원 이름
	
	@Column(unique=true)
	private String username; 	//회원 id
	
	private String password;	//회원 pw
	private String email; 		//회원 이메일
	private String phoneNum; 	//회원 전화번호
	private String socialNum;	// 주민번호
	private String membership; 	//회원 등급
	private String role;
//	private String locked; 		//정지 여부
//	private String disabled; 	//탈퇴 여부
}
