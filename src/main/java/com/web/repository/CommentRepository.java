package com.web.repository;

import java.util.List;
import java.util.Optional;

import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;

import com.web.domain.CommentEntity;

public interface CommentRepository extends JpaRepository<CommentEntity, Long> {

	Page<CommentEntity> findByIsbn(Pageable pageable, String isbn); // String username 자리에 책고유번호 isbn

	Optional<CommentEntity> findByIsbnAndMemberMemberNum(String isbn, Long memberNum);

	List<CommentEntity> findByIsbn(String isbn);

	@Query("SELECT SUM(e.starRating) FROM CommentEntity e where e.isbn = :isbn")
	int sumOfColumn(String isbn);
}
