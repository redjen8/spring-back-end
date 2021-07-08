package com.redjen.softcamp.controller;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;

@Controller
public class HomeController {
    @GetMapping("/boardHome")
    public String boardHome() {
        return "boardHome";
    }
}