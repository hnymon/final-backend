package com.web.domain;

import java.util.ArrayList;
import java.util.List;

import javax.persistence.CascadeType;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.EnumType;
import javax.persistence.Enumerated;
import javax.persistence.FetchType;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.ManyToOne;
import javax.persistence.OneToMany;
import javax.persistence.OneToOne;
import javax.persistence.SequenceGenerator;
import javax.persistence.Table;

import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.security.crypto.password.PasswordEncoder;

import com.fasterxml.jackson.annotation.JsonIgnore;

import lombok.AccessLevel;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import lombok.ToString;

@Setter
@Getter
@ToString(exclude = {"comment", "addr", "Inquery"})
@NoArgsConstructor(access = AccessLevel.PROTECTED)
@AllArgsConstructor
@Builder
@Entity
@SequenceGenerator(name="MEMBER_SEQ_GENERATOR", sequenceName="MEMBER_SEQ", allocationSize = 1)
@Table(name="MEMBER")
public class Member extends BaseEntity{

	@Id
	@Column(name = "MEMBER_NUM") // 추가
	@GeneratedValue(strategy = GenerationType.SEQUENCE)
	private Long memberNum;

	private String memberName; //회원 이름
	
	@Column(unique=true)
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
    
    @OneToOne(mappedBy = "member", cascade=CascadeType.ALL)
    private Cart cart;
    
//	private String locked; 		//정지 여부
//	private String disabled; 	//탈퇴 여부
    
    // 유저 권한 설정 메소드
	public void authorizeUser() {
	    this.role = Role.USER;
	}

	
	// 비밀번호 암호화 메소드
	public void passwordEncode(BCryptPasswordEncoder bCryptPasswordEncoder) {
	    this.password = bCryptPasswordEncoder.encode(this.password);
	}
	
	public void updateRefreshToken(String updateRefreshToken) {
	    this.refreshToken = updateRefreshToken;
	}
	
	@OneToMany(mappedBy = "member", cascade=CascadeType.ALL)
	private List<MemberDeliveryAddress> addr = new ArrayList<>();
	
	@OneToMany(mappedBy = "member", cascade=CascadeType.ALL)
	private List<CommentEntity> comment = new ArrayList<>();
	
	@OneToMany(mappedBy = "member", cascade=CascadeType.ALL)
	private List<OneToOneInquiryEntity> Inquery = new ArrayList<>();
	
	@OneToMany(mappedBy = "member", cascade=CascadeType.ALL)
	private List<Order> order = new ArrayList<>();
	
}
