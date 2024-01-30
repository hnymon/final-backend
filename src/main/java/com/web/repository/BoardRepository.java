package com.web.repository;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;

import com.web.domain.BoardEntity;

public interface BoardRepository extends JpaRepository<BoardEntity, Long>{

	BoardEntity save(Long boardSeq);
}
  