package com.redjen.softcamp.controller;

import java.util.List;

import com.redjen.softcamp.domain.Member;
import com.redjen.softcamp.service.MemberService;

import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;

@RestController
@Slf4j
@RequiredArgsConstructor
@RequestMapping("/users")
public class MemberController {

    private final MemberService service;

    @GetMapping("/{userNo}")
    public ResponseEntity<Member> read(@PathVariable("userNo") int userNo) throws Exception {
        log.info("read");

        Member member = service.read(userNo);

        return new ResponseEntity<>(member, HttpStatus.OK);
    }

    @GetMapping
    public ResponseEntity<List<Member>> list() throws Exception {
        log.info("list");

        return new ResponseEntity<>(service.list(), HttpStatus.OK);
    }

    @PostMapping
    public ResponseEntity<Member> register(@Validated @RequestBody Member member) throws Exception {
        log.info("register");

        service.register(member);

        log.info("register member.getUserNo()= " + member.getUserNo());
        return new ResponseEntity<>(member, HttpStatus.OK);
    }

    @DeleteMapping("/{userNo}")
    public ResponseEntity<Void> remove(@PathVariable("userNo") int userNo) throws Exception {
        log.info("remove");
        
        service.remove(userNo);

        return new ResponseEntity<Void>(HttpStatus.NO_CONTENT);
    }


    @PutMapping("/{userNo}")
    public ResponseEntity<Void> modify(@PathVariable("userNo") int userNo, @Validated @RequestBody Member member) throws Exception {
        log.info("modify");

        member.setUserNo(userNo);
        service.modify(member);

        return new ResponseEntity<Void>(HttpStatus.NO_CONTENT);
    }
}