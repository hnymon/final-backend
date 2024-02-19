package com.web.service;

import java.util.Map;

import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;

import com.web.domain.Member;
import com.web.dto.JoinDTO;

public interface MemberService {
	// 아이디 중복확인
	public String checkId(String username);
	// 이메일 중복확인
	public String checkEmail(JoinDTO joinDTO);
	// 이메일 중복확인
	public String checkMemberEmail(JoinDTO joinDTO);
	// 인증코드 확인
	public String checkCode(JoinDTO joinDTO);
	// 회원가입 
	public String join(JoinDTO joinDTO);
	// 회원정보 수정
	public String editMemberInfo(JoinDTO joinDTO);
	// 필요할 때 토큰 넘겨서 username(아이디)로 멤버 정보 불러오기
	public Member getMemberInfo(String username);
	// 아이디 찾기
	public Map<String, Object> findId(JoinDTO joinDTO);
	// 비밀번호 찾기
	public Map<String, Object> findPwd(JoinDTO joinDTO);
	// 비밀번호 재설정
	public String editPwd(JoinDTO joinDTO);
	// 회원 탈퇴
	public String deleteMember(JoinDTO joinDTO);
	
	public Page<Member> getMemberList(Pageable pageable);	
	public Page<Member> searchMemberNum(Pageable pageable, String term);

	public void deleteMemberInAdmin(Long memberNum);
	

}
