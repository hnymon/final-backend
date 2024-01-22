package com.web;

import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;

import com.web.domain.MemberEntity;
import com.web.repository.MemberRepository;

@SpringBootTest
class FinalProjectApplicationTests {
	
	@Autowired
	MemberRepository mRepo;

	@Test
	void contextLoads() {
		MemberEntity member = new MemberEntity();
		member.setUsername("admin");
		member.setPassword("1234");
		member.setRole("ROLE_ADMIN");
		mRepo.save(member);
	}

}
