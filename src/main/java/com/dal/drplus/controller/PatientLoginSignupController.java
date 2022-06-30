package com.dal.drplus.controller;

import com.dal.drplus.model.Patient;
import com.dal.drplus.repository.implementation.PatientRepositoryImpl;
import com.dal.drplus.service.PatientLoginSignupService;
import com.dal.drplus.service.PatientService;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.servlet.view.RedirectView;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpSession;

@Controller
@RequestMapping(value = "/auth")
public class PatientLoginSignupController {

    private PatientLoginSignupService loginSignupService;
    private PatientService patientService;

    public PatientLoginSignupController(PatientRepositoryImpl patientRepository) {
        this.loginSignupService = new PatientLoginSignupService(patientRepository);
        this.patientService =new PatientService(patientRepository);
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
    public RedirectView LoginPatient(HttpSession session, @RequestParam(value="patientId") String patientId, @RequestParam(value="patientPassword") String password){
        System.out.println(patientId+"patientId");
        System.out.println(password+"password");
        boolean isCredentialsValid;
        isCredentialsValid = loginSignupService.isPatientCredentialValid(patientId,password);
        System.out.println(isCredentialsValid+"iscredentialValid");
        if(isCredentialsValid){
            Patient patient = patientService.getPatientById(patientId);
            session.setAttribute("CurrentPatient",patient);
//            return "patient/patient_home";
            return new RedirectView("/auth/patient_home");
        }else{
//            return "patient/login";
            return new RedirectView("/auth/patient_login");
        }
    }

    @GetMapping("/patient_home")
    public String PatientHome(HttpSession session){
        Patient currentPatient = (Patient) session.getAttribute("CurrentPatient");
        System.out.println("current patient Id"+currentPatient.getPatientName());
        System.out.println("current patient password"+currentPatient.getPatientPassword());
        return "patient/patient_home";
    }

    @RequestMapping(value = "/logout")
    public RedirectView LogoutPatient(HttpSession session,HttpServletRequest request){
        System.out.println("inside logout");
//        request.getSession().invalidate();
        session.invalidate();
        System.out.println("session invalidated");
        return new RedirectView("/");
    }
}