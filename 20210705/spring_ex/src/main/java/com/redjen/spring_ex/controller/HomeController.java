package com.redjen.spring_ex.controller;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;

@Controller
public class HomeController {
    
    @GetMapping("/registerForm")
    public String ajaxHome() {
        return "registerForm";
    }
}
