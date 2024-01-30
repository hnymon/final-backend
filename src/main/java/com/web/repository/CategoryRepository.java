package com.web.repository;

import org.springframework.data.repository.CrudRepository;

import com.web.domain.Category;

public interface CategoryRepository extends CrudRepository<Category, Long>{

}
