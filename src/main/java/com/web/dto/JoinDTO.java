package com.web.dto;

import lombok.Getter;
import lombok.Setter;

@Setter
@Getter
public class JoinDTO {
	private String username; 	// 아이디
	private String password;	// 비밀번호
	private String memberName;	// 이름
	private String email;		// 이메일
	private String domain;		// 도메인
	private String phoneNum;	// 전화번호
	private String socialNum;	// 주민번호
}
