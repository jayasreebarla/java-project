package com.dal.drplus.controller;

import com.dal.drplus.model.Appointment;
import com.dal.drplus.model.Doctor;
import com.dal.drplus.model.Patient;
import com.dal.drplus.repository.implementation.AppointmentRepositoryImpl;
import com.dal.drplus.repository.implementation.DoctorRepositoryImpl;
import com.dal.drplus.service.AppointmentListService;
import com.dal.drplus.service.DoctorLoginSignupService;
import com.dal.drplus.service.DoctorService;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.servlet.view.RedirectView;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpSession;
import java.util.List;

@Controller
@RequestMapping(value = "/auth_doctor")
public class  DoctorLoginSignupController {

    private DoctorLoginSignupService loginSignupService;
    private AppointmentListService appointmentListService;
    private DoctorService doctorService;

    public DoctorLoginSignupController(DoctorRepositoryImpl doctorRepository, AppointmentRepositoryImpl appointmentRepository) {
        this.loginSignupService = new DoctorLoginSignupService(doctorRepository);
        this.doctorService = new DoctorService(doctorRepository);
        this.appointmentListService = new AppointmentListService(appointmentRepository);
    }

    @GetMapping("/doctor_signup")
    public String SignUp(Model model){
        model.addAttribute("doctor",new Doctor());
        return "doctor/signup";
    }

    @PostMapping("/doctor_signup")
    public String RegisterDoctor(@ModelAttribute Doctor doctor, @RequestParam(value = "confirmDoctorPassword") String confirmPassword){
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
    public String LoginDoctor(HttpSession session, @RequestParam(value="doctorId") String doctorId,@RequestParam(value="doctorPassword") String password, Model model){
        System.out.println(doctorId+"doctorId");
        System.out.println(password+"password");
        boolean isCredentialsValid;
        isCredentialsValid = loginSignupService.isDoctorCredentialValid(doctorId,password);
        System.out.println(isCredentialsValid+" iscredentialValid");
        if(isCredentialsValid){
            System.out.println("inside if");
            Doctor doctor = doctorService.getDoctorById(doctorId);
            session.setAttribute("CurrentDoctor",doctor);
            session.setAttribute("Type","D");
            List<Appointment> appointmentList = appointmentListService.listAppointmentbyDoctor(doctorId);
            model.addAttribute("appointments",appointmentList);
            System.out.println("abcd");
            return "doctor/doctor_home";
        }else{
            return "doctor/login";
        }
    }

    @GetMapping("/doctor_home")
    public String DoctorHome(HttpSession session, Model model){
        Doctor currentDoctor= (Doctor) session.getAttribute("CurrentDoctor");
        System.out.println("current Doctor Id inside doctor login signup controller"+currentDoctor.getDoctorId());
        List<Appointment> appointmentList = appointmentListService.listAppointmentbyDoctor(currentDoctor.getDoctorId());
        model.addAttribute("appointments",appointmentList);
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

