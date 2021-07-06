package com.redjen.spring_ex;

import org.springframework.data.jpa.repository.JpaRepository;

import com.redjen.spring_ex.domain.Member;

public interface MemberRepository extends JpaRepository<Member, Integer>{
    
}
