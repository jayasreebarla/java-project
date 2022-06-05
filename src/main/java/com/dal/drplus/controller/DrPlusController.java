package com.dal.drplus.controller;

import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
public class DrPlusController {
    @RequestMapping("/")
    public String WelcomePage(){
        return "welcome to Dr+";
    }
}
