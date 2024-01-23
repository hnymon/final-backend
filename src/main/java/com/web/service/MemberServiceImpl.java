package com.web.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.stereotype.Service;

import com.web.domain.MemberEntity;
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
		MemberEntity member = new MemberEntity();
		member.setMemberName(joinDTO.getMemberName());
		member.setUsername(joinDTO.getUsername());
		member.setPassword(bCryptPasswordEncoder.encode(joinDTO.getPassword()));
		member.setPhoneNum(joinDTO.getPhoneNum());
		member.setMembership(joinDTO.getSocialNum());
		member.setRole("ROLE_ADMIN");
		memberRepository.save(member);
		return "ok";
	}
}
