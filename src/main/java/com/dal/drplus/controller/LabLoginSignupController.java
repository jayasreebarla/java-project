package com.dal.drplus.controller;


import com.dal.drplus.model.Lab;
import com.dal.drplus.repository.implementation.LabRepositoryImpl;
import com.dal.drplus.service.LabLoginSignupService;
import com.dal.drplus.service.LabService;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.servlet.view.RedirectView;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpSession;

@Controller
@RequestMapping (value = "/auth_lab")
public class LabLoginSignupController {
    private LabLoginSignupService loginSignupService;
    private LabService labService;
    public LabLoginSignupController(LabRepositoryImpl labRepository)
    {

        this.loginSignupService = new LabLoginSignupService(labRepository);
        this.labService =new LabService(labRepository);
    }

    @GetMapping("/lab_signup")
    public String SignUp(Model model){
        model.addAttribute("lab",new Lab());
        return "lab/labSignup";
    }

    @PostMapping("/lab_signup")
    public String RegisterLab(@ModelAttribute Lab lab){
        System.out.println("LAB SIGNUP"+lab.toString());
        boolean result = loginSignupService.registerLab(lab);
        return "lab/labLogin";
    }

    @GetMapping("/lab_login")
    public String login(){
        return "lab/labLogin";
    }

    @PostMapping("/lab_login")
    public RedirectView LoginLab(@RequestParam(value="labId") String labId, @RequestParam(value="labPassword") String labPassword){
        System.out.println(labId+"labId");
        System.out.println(labPassword+"labPassword");
        boolean isCredentialsValid;
        isCredentialsValid = loginSignupService.isLabCredentialValid(labId,labPassword);
        System.out.println(isCredentialsValid+"iscredentialValid");
        if(isCredentialsValid){
           // return "lab/lab_home";
            return new RedirectView("/auth_lab/lab_home");
        }else{
            //return "lab/login";
            return new RedirectView("/auth_lab/lab_login");
        }
    }
    @GetMapping("/lab_home")
    public String LabHome(){
        return "lab/lab_home";
    }

    @RequestMapping(value = "/logout")
    public RedirectView LogoutLab(HttpSession session,HttpServletRequest request){
        System.out.println("inside logout");
        session.invalidate();
        System.out.println("session invalidated");
        return new RedirectView("/");
    }
}
