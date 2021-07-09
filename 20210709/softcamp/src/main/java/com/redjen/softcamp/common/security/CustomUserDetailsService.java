package com.redjen.softcamp.common.security;

import com.redjen.softcamp.common.security.domain.CustomUser;
import com.redjen.softcamp.domain.Member;
import com.redjen.softcamp.mapper.MemberMapper;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;

import lombok.extern.slf4j.Slf4j;

@Slf4j
public class CustomUserDetailsService implements UserDetailsService {

    @Autowired
    private MemberMapper memberMapper;

    @Override
    public UserDetails loadUserByUsername(String userName) throws UsernameNotFoundException {
        log.info("Load User By UserName : " + userName);

        Member member = memberMapper.read(userName);

        log.info("queried by member mapper : " + member);

        return member == null ? null : new CustomUser(member);
    }
}
