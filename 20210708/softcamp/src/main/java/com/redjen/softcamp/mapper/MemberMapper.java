package com.redjen.softcamp.mapper;

import java.util.List;

import com.redjen.softcamp.domain.Member;
import com.redjen.softcamp.domain.MemberAuth;

import org.apache.ibatis.annotations.Mapper;

@Mapper
public interface MemberMapper {

    public void create(Member member) throws Exception;

    public Member read(int userNo) throws Exception;

    public void update(Member member) throws Exception;

    public void delete(int userNo) throws Exception;

    public List<Member> list() throws Exception;

    public void createAuth(MemberAuth memberAuth) throws Exception;

    public void deleteAuth(int userNo) throws Exception;
}