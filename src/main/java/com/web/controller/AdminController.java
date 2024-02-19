package com.web.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.web.PageableDefault;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import com.web.domain.Member;
import com.web.service.MemberService;

@RestController
@RequestMapping("/admin")
public class AdminController {
	
	@Autowired
	MemberService mService;

	@GetMapping("/memberManagement")
	public Page<Member> adminP(@PageableDefault(size = 5) Pageable pageable, @RequestParam(required = false) String term) {
		
		System.out.println("서치키워드"+term);
		
		if (term != null && !term.isEmpty()) {
			return mService.searchMemberNum(pageable, term);
		}else {
			return mService.getMemberList(pageable);
		}
	}
	
	@Transactional
	@DeleteMapping("/member/delete/{memberNum}")
	public void deleteMemberInAdmin (@PathVariable Long memberNum) {
		mService.deleteMemberInAdmin(memberNum);
	}
	
}
