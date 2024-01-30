package com.web.repository;

import org.springframework.data.repository.CrudRepository;

import com.web.domain.Book;

public interface BookRepository extends CrudRepository<Book, Long>{

}
