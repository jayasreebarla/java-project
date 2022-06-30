package com.dal.drplus.controller;

import com.dal.drplus.model.Doctor;
import com.dal.drplus.repository.implementation.DoctorRepositoryImpl;
import com.dal.drplus.service.DoctorLoginSignupService;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.servlet.view.RedirectView;

import javax.servlet.http.HttpServletRequest;

@Controller
@RequestMapping(value = "/auth_doctor")
public class DoctorLoginSignupController {

    private DoctorLoginSignupService loginSignupService;

    public DoctorLoginSignupController(DoctorRepositoryImpl doctorRepository) {
        this.loginSignupService = new DoctorLoginSignupService(doctorRepository);
    }

    @GetMapping("/doctor_signup")
    public String SignUp(Model model){
        model.addAttribute("doctor",new Doctor());
        return "doctor/signup";
    }

    @PostMapping("/doctor_signup")
    public String RegisterPatient(@ModelAttribute Doctor doctor, @RequestParam(value = "confirmDoctorPassword") String confirmPassword){
        System.out.println(doctor.toString());
        System.out.println("confirmPassword"+confirmPassword);
        boolean result = loginSignupService.registerDoctor(doctor,confirmPassword);
        return "doctor/login";
    }

    @GetMapping("/doctor_login")
    public String login(){
        return "doctor/login";
    }

    @PostMapping("/doctor_login")
    public String LoginDoctor(@RequestParam(value="doctorId") String doctorId,@RequestParam(value="doctorPassword") String password){
        System.out.println(doctorId+"doctorId");
        System.out.println(password+"password");
        boolean isCredentialsValid;
        isCredentialsValid = loginSignupService.isDoctorCredentialValid(doctorId,password);
        System.out.println(isCredentialsValid+"iscredentialValid");
        if(isCredentialsValid){
            return "doctor/doctor_home";
        }else{
            return "doctor/login";
        }
    }

    @GetMapping("/doctor_home")
    public String PatientHome(){
        return "doctor/doctor_home";
    }

    @RequestMapping(value = "/logout")
    public RedirectView LogoutDoctor(HttpServletRequest request){
        System.out.println("inside logout");
        request.getSession().invalidate();
        System.out.println("session invalidated");
        return new RedirectView("/");
    }
}

