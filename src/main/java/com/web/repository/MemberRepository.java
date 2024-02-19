package com.web.repository;

import java.util.Optional;

import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.repository.CrudRepository;

import com.web.domain.Member;
import com.web.domain.SocialType;

public interface MemberRepository extends JpaRepository<Member, Long>{

	Member findByUsername(String username);

	Boolean existsByUsername(String username);
	
	Boolean existsByEmail(String email);

	Optional<Member> findBySocialTypeAndSocialId(SocialType socialType, String id);

	Member findByEmail(String email);
	
	Optional<Member> findByRefreshToken(String refreshToken);
	// 아이디 찾기 - 전화번호로 찾기
	Member findByMemberNameAndBirthdayAndPhoneNum(String memberName, String birthday, String phoneNum);
	// 아이디 찾기 - 이메일로 찾기
	Member findByMemberNameAndBirthdayAndEmail(String memberName, String birthday, String email);
	// 비밀번호 찾기 - 전화번호로 찾기
	Optional<Member> findByUsernameAndMemberNameAndBirthdayAndPhoneNum(String username, String memberName, String birthday, String phoneNum);
	// 비밀번호 찾기 - 이메일로 찾기
	Optional<Member> findByUsernameAndMemberNameAndBirthdayAndEmail(String username, String memberName, String birthday, String email);

	Page<Member> findByMemberNum(Pageable pageable, Long memberNum);

	void deleteByMemberNum(Long memberNum);
}
