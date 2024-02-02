package com.web.repository;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;

import com.web.domain.BookCrawling;

public interface BookCrawlingRepository extends JpaRepository<BookCrawling, String>{
	List<BookCrawling> findAllByType(String type);
	void deleteAllInBatch(Iterable<BookCrawling> entities);
}
