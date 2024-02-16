package com.web.dto;

import javax.persistence.EnumType;
import javax.persistence.Enumerated;

import com.web.domain.Role;
import com.web.domain.SocialType;

import lombok.Data;
import lombok.Getter;
import lombok.Setter;

@Data
public class JoinDTO {
	private Long memberNum;
	private String username; 	// 아이디
	private String password;	// 비밀번호
	private String memberName;	// 이름
	private String email;		// 이메일
	private String domain;		// 도메인
	private String phoneNum;	// 전화번호
	private String birthday;	// 주민번호 앞자리 (생년월일 8자리)
	private String randomInitial; // 이메일 인증 코드
	
	@Enumerated(EnumType.STRING)
	private SocialType socialType;
	@Enumerated(EnumType.STRING)
    private Role role;
}
