package com.web.service;

import com.web.domain.Member;
import com.web.dto.JoinDTO;

public interface MemberService {
	// 아이디 중복확인
	public String checkId(String username);
	// 회원가입 
	public String join(JoinDTO joinDTO);
	// 필요할 때 토큰 넘겨서 username(아이디)로 멤버 정보 불러오기
	public Member getMemberInfo(String username);
	
}
