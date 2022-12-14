package com.dal.drplus.controller;

import com.dal.drplus.model.IEntity.IDoctor;
import com.dal.drplus.model.entity.Appointment;
import com.dal.drplus.model.entity.Doctor;
import com.dal.drplus.model.factory.ModelFactory;
import com.dal.drplus.repository.implementation.AppointmentRepositoryImpl;
import com.dal.drplus.repository.implementation.DoctorRepositoryImpl;
import com.dal.drplus.model.service.AppointmentListService;
import com.dal.drplus.model.service.DoctorLoginSignupService;
import com.dal.drplus.model.service.DoctorService;
import com.dal.drplus.model.service.PasswordEncryptionService;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.servlet.view.RedirectView;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpSession;
import java.util.List;

@Controller
@RequestMapping(value = "/auth_doctor")
public class DoctorLoginSignupController {
    private DoctorLoginSignupService loginSignupService;
    private AppointmentListService appointmentListService;
    private DoctorService doctorService;
    private PasswordEncryptionService passwordEncryptionService;

    public DoctorLoginSignupController(DoctorRepositoryImpl doctorRepository, AppointmentRepositoryImpl appointmentRepository) {
        this.loginSignupService = new DoctorLoginSignupService(doctorRepository);
        this.doctorService = new DoctorService(doctorRepository);
        this.appointmentListService = new AppointmentListService(appointmentRepository);
        this.passwordEncryptionService = new PasswordEncryptionService();
    }

    @GetMapping("/doctor_signup")
    public String SignUp(Model model){
        model.addAttribute("doctor", (Doctor)ModelFactory.instance().createDoctor());
        return "doctor/signup";
    }

    @PostMapping("/doctor_signup")
    public RedirectView RegisterDoctor(HttpSession session, @ModelAttribute Doctor doctor, @RequestParam(value = "confirmDoctorPassword") String confirmPassword){
        if(loginSignupService.isDoctorIdExists(doctor.getDoctorId())){
            return new RedirectView("/auth_doctor/doctor_signup");
        }else{
            if(doctor.validateDoctorAge()
                    && doctor.validateDoctorCredentials()
                    && doctor.validateDoctorEmail()
                    && doctor.validateDoctorName()
                    && doctor.validatePhoneNumber()
                    && doctor.validateDoctorPincode() && doctor.validateDoctorFee())
            {
                doctor.setDoctorPassword(passwordEncryptionService.hashPassword(doctor.getDoctorPassword()));
                confirmPassword = passwordEncryptionService.hashPassword(confirmPassword);

                boolean result = loginSignupService.registerDoctor(doctor,confirmPassword);
                String type = String.valueOf(session.getAttribute("Type"));
                if(type.equals("A")){
                    return new RedirectView("/admin/doctor_list_admin");
                }
                return new RedirectView("/auth_doctor/doctor_login");
            }else{
                return new RedirectView("/auth_doctor/doctor_signup");
            }
        }

    }

    @GetMapping("/doctor_login")
    public String login(){
        return "doctor/login";
    }

    @PostMapping("/doctor_login")
    public String LoginDoctor(HttpSession session, @RequestParam(value="doctorId") String doctorId,@RequestParam(value="doctorPassword") String password, Model model){
        password = passwordEncryptionService.hashPassword(password);
        boolean isCredentialsValid;
        isCredentialsValid = loginSignupService.isDoctorCredentialValid(doctorId,password);
        if(isCredentialsValid){
            IDoctor doctor = doctorService.getDoctorById(doctorId);
            session.setAttribute("CurrentDoctor",doctor);
            session.setAttribute("Type","D");
            List<Appointment> appointmentList = appointmentListService.listAppointmentbyDoctor(doctorId);
            model.addAttribute("appointments",appointmentList);
            return "doctor/doctor_home";
        }else{
            return "doctor/login";
        }
    }

    @GetMapping("/doctor_home")
    public String DoctorHome(HttpSession session, Model model){
        IDoctor currentDoctor= (Doctor) session.getAttribute("CurrentDoctor");
        List<Appointment> appointmentList = appointmentListService.listAppointmentbyDoctor(currentDoctor.getDoctorId());
        model.addAttribute("appointments",appointmentList);
        return "doctor/doctor_home";
    }

    @RequestMapping(value = "/logout")
    public RedirectView LogoutDoctor(HttpServletRequest request){
        request.getSession().invalidate();
        return new RedirectView("/");
    }
}

