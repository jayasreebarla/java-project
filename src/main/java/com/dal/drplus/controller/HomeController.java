package com.dal.drplus.controller;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.servlet.view.RedirectView;

import javax.servlet.http.HttpSession;

@Controller
public class HomeController {

    @GetMapping("/home")
    public RedirectView getHomePage(HttpSession session){
        String type = String.valueOf(session.getAttribute("Type"));
        return new RedirectView("/home/"+type);
    }

    @GetMapping("/home/D")
    public String getDoctorHomePage(){
        return "doctor/doctor_home";
    }

    @GetMapping("/home/A")
    public String getAdminHomePage(){
        return "admin/admin_home";
    }

    @GetMapping("/home/L")
    public String getLabHomePage(){
        return "lab/lab_home";
    }

    @GetMapping("/home/P")
    public String getPatientHomePage(){
        return "patient/patient_home";
    }
}
