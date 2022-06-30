package com.dal.drplus.controller;

import com.dal.drplus.model.Patient;
import com.dal.drplus.repository.implementation.PatientRepositoryImpl;
import com.dal.drplus.service.PatientLoginSignupService;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.servlet.view.RedirectView;

import javax.servlet.http.HttpServletRequest;

@Controller
@RequestMapping(value = "/auth")
public class PatientLoginSignupController {

    private PatientLoginSignupService loginSignupService;

    public PatientLoginSignupController(PatientRepositoryImpl patientRepository) {
        this.loginSignupService = new PatientLoginSignupService(patientRepository);
    }

    @GetMapping("/patient_signup")
    public String SignUp(Model model){
        model.addAttribute("patient",new Patient());
        return "patient/signup";
    }

    @PostMapping("/patient_signup")
    public String RegisterPatient(@ModelAttribute Patient patient, @RequestParam(value = "confirmPatientPassword") String confirmPassword){
        System.out.println(patient.toString());
        System.out.println("confirmPassword"+confirmPassword);
        boolean result = loginSignupService.registerPatient(patient,confirmPassword);
        return "patient/login";
    }

    @GetMapping("/patient_login")
    public String login(){
        return "patient/login";
    }

    @PostMapping("/patient_login")
    public String LoginPatient(@RequestParam(value="patientId") String patientId,@RequestParam(value="patientPassword") String password){
        System.out.println(patientId+"patientId");
        System.out.println(password+"password");
        boolean isCredentialsValid;
        isCredentialsValid = loginSignupService.isPatientCredentialValid(patientId,password);
        System.out.println(isCredentialsValid+"iscredentialValid");
        if(isCredentialsValid){
            return "patient/patient_home";
        }else{
            return "patient/login";
        }
    }

    @GetMapping("/patient_home")
    public String PatientHome(){
        return "patient/patient_home";
    }

    @RequestMapping(value = "/logout")
    public RedirectView LogoutPatient(HttpServletRequest request){
        System.out.println("inside logout");
        request.getSession().invalidate();
        System.out.println("session invalidated");
        return new RedirectView("/");
    }
}
