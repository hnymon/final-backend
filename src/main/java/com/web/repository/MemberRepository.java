package com.web.repository;

import org.springframework.data.repository.CrudRepository;

import com.web.domain.MemberEntity;

public interface MemberRepository extends CrudRepository<MemberEntity, Long>{

	MemberEntity findByUsername(String username);

	Boolean existsByUsername(String username);
}
