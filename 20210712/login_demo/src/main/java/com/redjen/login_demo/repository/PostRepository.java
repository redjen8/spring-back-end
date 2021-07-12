package com.redjen.login_demo.repository;

import org.springframework.stereotype.Repository;
import org.springframework.data.jpa.repository.JpaRepository;

import com.redjen.login_demo.model.Post;

@Repository
public interface PostRepository extends JpaRepository<Post, Long> {
}