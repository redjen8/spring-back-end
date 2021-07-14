package com.redjen.softcamp.repository;

import com.redjen.softcamp.model.SocialProvider;
import com.redjen.softcamp.model.UserEntity;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface UserRepository extends JpaRepository<UserEntity, Integer> {

    UserEntity findByUsername(String username);

    UserEntity findByUsernameAndSns(String id, SocialProvider sns);
}
