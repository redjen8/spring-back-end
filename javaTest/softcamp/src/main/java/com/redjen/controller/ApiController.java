package com.redjen.controller;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;

@Controller
public class ApiController {

    @GetMapping("/login")
    public String getId(@RequestParam String id, @RequestParam(name = "pw") String pwd) {
        System.out.println("id = " + id);
        System.out.println("password = " + pwd);
        return "login";
    }
}
