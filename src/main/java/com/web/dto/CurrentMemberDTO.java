package com.web.dto;

import java.util.ArrayList;
import java.util.List;

import javax.persistence.CascadeType;
import javax.persistence.Column;
import javax.persistence.EnumType;
import javax.persistence.Enumerated;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.OneToMany;
import javax.persistence.OneToOne;

import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;

import com.web.domain.Cart;
import com.web.domain.CommentEntity;
import com.web.domain.MemberDeliveryAddress;
import com.web.domain.OneToOneInquiryEntity;
import com.web.domain.Role;
import com.web.domain.SocialType;

import lombok.Data;

@Data
public class CurrentMemberDTO {
	private Long memberNum;
	private String memberName; //회원 이름
	private String username; 	//회원 id
	private String password;	//회원 pw
	private String email; 		//회원 이메일
	private String phoneNum; 	//회원 전화번호
	private String membership; 	//회원 등급
	private String birthday; 	// 생년월일, 성별 확인용
    @Enumerated(EnumType.STRING)
    private Role role;
    @Enumerated(EnumType.STRING)
    private SocialType socialType; // KAKAO, NAVER, GOOGLE  
    private String socialId; // 로그인한 소셜 타입의 식별자 값 (일반 로그인인 경우 null)
    private String refreshToken; // 리프레시 토큰 
    private Cart cart;
	private List<MemberDeliveryAddress> addr = new ArrayList<>();
	private List<CommentEntity> comment = new ArrayList<>();
	private List<OneToOneInquiryEntity> Inquery = new ArrayList<>();
}
