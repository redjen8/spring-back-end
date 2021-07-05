package com.redjen.spring_ex.controller;

import com.redjen.spring_ex.domain.Member;

import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.http.ResponseEntity;

import java.time.LocalDate;
import lombok.extern.slf4j.Slf4j;

@Slf4j
@RestController
public class MemberController {

    @PostMapping("/register")
    public ResponseEntity<String> register(@RequestBody Member member) {
        log.info("register");
        log.info("userId = " + member.getUserId());
        log.info("password = " + member.getPassword());
        log.info("member.getDateOfBirth = " + member.getDateOfBirth());

        return new ResponseEntity<String>("Success", HttpStatus.OK);
    }

    @GetMapping("/read")
    public ResponseEntity<Member> read() {
        log.info("register");

        Member member = new Member();
        member.setUserId("hongkd");
        member.setPassword("1234");
        LocalDate dateOfBirth = LocalDate.of(1998,8,15);
        member.setDateOfBirth(dateOfBirth);

        return new ResponseEntity<Member>(member, HttpStatus.OK);
    }
}
