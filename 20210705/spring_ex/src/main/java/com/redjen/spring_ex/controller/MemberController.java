package com.redjen.spring_ex.controller;

import com.redjen.spring_ex.domain.Member;

import org.springframework.data.mongodb.core.MongoOperations;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import org.springframework.http.ResponseEntity;
import org.springframework.validation.BindingResult;
import org.springframework.validation.FieldError;
import org.springframework.validation.annotation.Validated;

import lombok.extern.slf4j.Slf4j;

import org.springframework.data.mongodb.core.MongoOperations;
import org.springframework.data.mongodb.core.MongoTemplate;
import org.springframework.data.mongodb.core.query.Query;
import com.mongodb.client.MongoClients;

import org.springframework.validation.FieldError;
import org.springframework.validation.ObjectError;

import java.util.List;

@Slf4j
@RestController
@RequestMapping("/users")
public class MemberController {

    @PostMapping(value="", produces="text/plain; charset=UTF-8")
    public ResponseEntity<String> register(@Validated @RequestBody Member member, BindingResult result) {
        log.info("register");

        log.info("result.hasErrors()= " + result.hasErrors());
        if(result.hasErrors()) {
            List<ObjectError> allErrors = result.getAllErrors();
            List<ObjectError> globalErrors = result.getGlobalErrors();
            List<FieldError> fieldErrors = result.getFieldErrors();
            
            log.info("allErrors.size()= " + allErrors.size());
            log.info("globalErrors.size()= " + globalErrors.size());
            log.info("fieldErrors.size()= " + fieldErrors.size());

            for (int i = 0; i < allErrors.size(); i++) {
                ObjectError objectError = allErrors.get(i);
                log.info("allError= " + objectError);
            }

            for (int i = 0; i< globalErrors.size(); i++) {
                ObjectError objectError = globalErrors.get(i);
                log.info("globalError= " + objectError);
            }

            for (int i = 0; i < fieldErrors.size(); i++) {
                FieldError fieldError = fieldErrors.get(i);
                log.info("fieldError= " + fieldError);
                log.info("fieldError.getDefaultMessage()= " + fieldError.getDefaultMessage());
            }

            ResponseEntity<String> entity = new ResponseEntity<String>(result.toString(), HttpStatus.BAD_REQUEST);
            return entity;
        }
        
        log.info("member.getUserId()= " + member.getUserId());
        log.info("member.getPassword()= " + member.getPassword());
        log.info("member.getUserName()= " + member.getUserName());
        log.info("member.getEmail()= " + member.getEmail());
        log.info("member.getGender()= " + member.getGender());
        log.info("member.getDateOfBirth()= " + member.getDateOfBirth());

        MongoOperations mongoOps = new MongoTemplate(MongoClients.create(), "test");
        mongoOps.insert(member);

        ResponseEntity<String> entity = new ResponseEntity<String>("Success", HttpStatus.OK);
        return entity;
    }    
}
