package com.web.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.stereotype.Service;

import com.web.domain.Member;
import com.web.domain.Role;
import com.web.dto.JoinDTO;
import com.web.repository.MemberRepository;
@Service
public class MemberServiceImpl implements MemberService{
	@Autowired
	private MemberRepository memberRepository;
	
	private final BCryptPasswordEncoder bCryptPasswordEncoder;
	public MemberServiceImpl(BCryptPasswordEncoder bCryptPasswordEncoder) {
        this.bCryptPasswordEncoder = bCryptPasswordEncoder;
    }
	
	// 아이디 중복확인
	@Override
	public String checkId(String username) {
		// TODO Auto-generated method stub
		if(username.equals("")) {
			return "Empty";
		}
		// 해당 아이디로 검색했을 때 있으면 true 반환
		Boolean result = memberRepository.existsByUsername(username);
		if(result) {
			return "Exist";
		}
		return "Can Use";
	}
	
	// 회원가입
	@Override
	public String join(JoinDTO joinDTO) {
		// TODO Auto-generated method stub
		Member member = Member.builder()
				.memberName(joinDTO.getMemberName())
				.username(joinDTO.getUsername())
				.email(joinDTO.getEmail()+joinDTO.getDomain())
				.password(bCryptPasswordEncoder.encode(joinDTO.getPassword()))
				.phoneNum(joinDTO.getPhoneNum())
				.membership(joinDTO.getSocialNum())
				.role(Role.USER)
				.build();
		
//		Member member = new Member();
//		member.setMemberName(joinDTO.getMemberName());
//		member.setUsername(joinDTO.getUsername());
//		member.setPassword(bCryptPasswordEncoder.encode(joinDTO.getPassword()));
//		member.setPhoneNum(joinDTO.getPhoneNum());
//		member.setMembership(joinDTO.getSocialNum());
//		member.setRole("ROLE_ADMIN");
		memberRepository.save(member);
		return "ok";
	}
	// 필요할 때 토큰 넘겨서 username(아이디)로 멤버 정보 불러오기
	@Override
	public Member getMemberInfo(String username) {
		// TODO Auto-generated method stub
		Member member = memberRepository.findByUsername(username);
		if(member != null) {
			// 아이디값과 일치하는 멤버객체를 반환
			return member; 
		}
		return null;
	}
	
}
