package com.redjen.softcamp.repository;

import com.redjen.softcamp.domain.Board;

import org.springframework.data.jpa.repository.JpaRepository;

public interface BoardRepository extends JpaRepository<Board, Long>{
    
}
