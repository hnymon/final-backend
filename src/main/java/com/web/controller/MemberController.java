package com.web.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;

import com.web.dto.JoinDTO;
import com.web.service.MemberService;

@RestController
public class MemberController {
	@Autowired
	private MemberService memberService;
	// 안녕
	// 아이디 중복확인
	@PostMapping("/checkId")
	public String checkId(@RequestBody JoinDTO joinDTO) {
		String res = memberService.checkId(joinDTO.getUsername());
		return res;
	}
	// 회원가입
	@PostMapping("/join")
	public String join(@RequestBody JoinDTO joinDTO) {
		String res = memberService.join(joinDTO);
		return res;
	}
	
}
