package com.dal.drplus.controller;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;

@Controller
public class FAQController {
    @GetMapping("/FAQs")
    public String homePageForFAQ(){
        return "FAQ/FAQs";
    }
}
