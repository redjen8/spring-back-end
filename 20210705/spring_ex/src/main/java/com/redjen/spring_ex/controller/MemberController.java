package com.redjen.spring_ex.controller;

import com.redjen.spring_ex.domain.Member;

import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.http.ResponseEntity;
import org.springframework.validation.*;
import org.springframework.validation.annotation.Validated;

import lombok.extern.slf4j.Slf4j;

@Slf4j
@RestController
@RequestMapping("/users")
public class MemberController {

    @PostMapping(value="", produces="text/plain; charset=UTF-8")
    public ResponseEntity<String> register(@Validated @RequestBody Member member, BindingResult result) {
        log.info("register");

        if(result.hasErrors()) {
            ResponseEntity<String> entity = new ResponseEntity<String>(result.toString(), HttpStatus.BAD_REQUEST);
            return entity;
        }

        log.info("member.getUserId()= " + member.getUserId());
        log.info("member.getPassword()= " + member.getPassword());
        log.info("member.getUserName()= " + member.getUserName());
        log.info("member.getEmail()= " + member.getEmail());
        log.info("member.getGender()= " + member.getGender());

        ResponseEntity<String> entity = new ResponseEntity<String>("Success", HttpStatus.OK);
        return entity;
    }    
}
