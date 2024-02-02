package com.web;

import java.nio.charset.StandardCharsets;

import javax.crypto.SecretKey;
import javax.crypto.spec.SecretKeySpec;
import javax.persistence.EntityManager;

import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.boot.test.context.SpringBootTest;

import com.web.domain.Member;
import com.web.repository.MemberRepository;

import io.jsonwebtoken.Jwts;

@SpringBootTest
class FinalProjectApplicationTests {
	
	@Autowired
	MemberRepository mRepo;
	
	@Autowired
	EntityManager em;

	@Value("${spring.jwt.secret}")
	private String secret;
	
	@Test
	void contextLoads() {
		em.clear();
//		Member member = new Member();
//		member.setUsername("admin");
//		member.setPassword("1234");
//		member.setRole("ROLE_ADMIN");
//		mRepo.save(member);
		
//		SecretKey secretKey = new SecretKeySpec(secret.getBytes(StandardCharsets.UTF_8), Jwts.SIG.HS256.key().build().getAlgorithm());
//
//		System.out.println(secretKey);
		
	}
	
	

}
