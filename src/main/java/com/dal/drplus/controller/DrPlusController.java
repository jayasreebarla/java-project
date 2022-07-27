package com.dal.drplus.controller;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;

@Controller
public class DrPlusController {
    @GetMapping("/")
    public String WelcomePage(){
        return "home";
    }
}
