package com.dal.drplus.controller;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.servlet.view.RedirectView;

@Controller
public class DrPlusController {
    @RequestMapping("/")
    public String WelcomePage(){
        return "home";
    }
}
