package com.web.repository;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;

import com.web.domain.SeoulPublicLibrary;

public interface LibraryRepository extends JpaRepository<SeoulPublicLibrary, Long>{

//	List<SeoulPublicLibrary> findById();
//	List<SeoulPublicLibrary> finByLbrryseqno(String lbrryseqno);
}
