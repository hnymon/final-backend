package com.web.repository;


import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;

import com.web.domain.CommentEntity;

public interface CommentRepository extends JpaRepository<CommentEntity, Long>{
	
	 Page<CommentEntity> findByIsbn(Pageable pageable,String isbn); // String username 자리에 책고유번호 isbn 
}
 