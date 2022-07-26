package com.dal.drplus.controller;

import com.dal.drplus.model.IEntity.IPatient;
import com.dal.drplus.model.entity.Patient;
import com.dal.drplus.model.factory.ModelFactory;
import com.dal.drplus.repository.implementation.PatientRepositoryImpl;
import com.dal.drplus.model.service.PasswordEncryptionService;
import com.dal.drplus.model.service.PatientLoginSignupService;
import com.dal.drplus.model.service.PatientService;
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
    private PasswordEncryptionService passwordEncryptionService;

    public PatientLoginSignupController(PatientRepositoryImpl patientRepository) {
        this.loginSignupService = new PatientLoginSignupService(patientRepository);
        this.patientService =new PatientService(patientRepository);
        this.passwordEncryptionService = new PasswordEncryptionService();
    }

    @GetMapping("/patient_signup")
    public String SignUp(Model model){
        model.addAttribute((Patient) ModelFactory.instance().createPatient());
        return "patient/signup";
    }

    @PostMapping("/patient_signup")
    public RedirectView RegisterPatient(HttpSession session, @ModelAttribute Patient patient, @RequestParam(value = "confirmPatientPassword") String confirmPassword){
        patient.setPatientPassword(passwordEncryptionService.hashPassword(patient.getPatientPassword()));
        confirmPassword = passwordEncryptionService.hashPassword(confirmPassword);
        if(patient.validatePatientAgeFormat(patient.getPatientAge())
                && patient.validatePatientEmailFormat(patient.getPatientEmail())
                && patient.validatePatientPincodeFormat(patient.getPatientPincode())
                && patient.validatePatientNameFormat(patient.getPatientName())
                && patient.validatePatientPhoneNumberFormat(patient.getPatientPhoneNo())
                && (!loginSignupService.isPatientIdExists(patient.getPatientId())))
        {
            boolean result = loginSignupService.registerPatient(patient,confirmPassword);
            String type = String.valueOf(session.getAttribute("Type"));
            if(type.equals("A")){
                return new RedirectView("/admin/patient_list_admin");
            }
            return new RedirectView("/auth/patient_login");
        }
        return new RedirectView("/auth/patient_signup");
    }

    @GetMapping("/patient_login")
    public String login(){
        return "patient/login";
    }

    @PostMapping("/patient_login")
    public RedirectView LoginPatient(HttpSession session, @RequestParam(value="patientId") String patientId, @RequestParam(value="patientPassword") String password){
        password = passwordEncryptionService.hashPassword(password);
        boolean isCredentialsValid;
        isCredentialsValid = loginSignupService.isPatientCredentialValid(patientId,password);
        if(isCredentialsValid){
            IPatient patient = patientService.getPatientById(patientId);
            session.setAttribute("CurrentPatient",patient);
            session.setAttribute("Type","P");
            return new RedirectView("/auth/patient_home");
        }else{
            return new RedirectView("/auth/patient_login");
        }
    }

    @GetMapping("/patient_home")
    public String PatientHome(HttpSession session){
        Patient currentPatient = (Patient) session.getAttribute("CurrentPatient");
        return "patient/patient_home";
    }

    @RequestMapping(value = "/logout")
    public RedirectView LogoutPatient(HttpSession session,HttpServletRequest request){
        session.invalidate();
        return new RedirectView("/");
    }
}
