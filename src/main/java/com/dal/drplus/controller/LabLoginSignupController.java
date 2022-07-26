package com.dal.drplus.controller;
import com.dal.drplus.model.IEntity.ILab;
import com.dal.drplus.model.entity.Appointment;
import com.dal.drplus.model.entity.Lab;
import com.dal.drplus.model.factory.ModelFactory;
import com.dal.drplus.repository.implementation.AppointmentRepositoryImpl;
import com.dal.drplus.repository.implementation.LabRepositoryImpl;
import com.dal.drplus.service.AppointmentListService;
import com.dal.drplus.service.LabLoginSignupService;
import com.dal.drplus.service.LabService;
import com.dal.drplus.service.PasswordEncryptionService;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.servlet.view.RedirectView;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpSession;
import java.util.List;


@Controller
@RequestMapping (value = "/auth_lab")
public class LabLoginSignupController {
    private LabLoginSignupService loginSignupService;
    private LabService labService;
    private PasswordEncryptionService passwordEncryptionService;

    private AppointmentListService appointmentListService;
    public LabLoginSignupController(LabRepositoryImpl labRepository, AppointmentRepositoryImpl appointmentRepository)
    {
        this.loginSignupService = new LabLoginSignupService(labRepository);
        this.labService =new LabService(labRepository);
        this.appointmentListService = new AppointmentListService(appointmentRepository);
        this.passwordEncryptionService = new PasswordEncryptionService();
    }

    @GetMapping("/lab_signup")
    public String SignUp(Model model){
        model.addAttribute("lab",(Lab) ModelFactory.instance().createLab());
        return "lab/labSignup";
    }

    @PostMapping("/lab_signup")
    public RedirectView RegisterLab(HttpSession session, @ModelAttribute Lab lab){
        System.out.println("LAB SIGNUP"+lab.getLabId()+lab.getLabPassword()+lab.getLabAddress()+lab.getLabEmailId()+lab.getLabPincode()+lab.getLabContactInfo()+lab.getLabPersonName());
        lab.setLabPassword(passwordEncryptionService.hashPassword(lab.getLabPassword()));

        boolean result = loginSignupService.registerLab(lab);
        String type = String.valueOf(session.getAttribute("Type"));
        if(type.equals("A")){
            return new RedirectView("/admin/lab_list_admin");
        }
        return new RedirectView("/auth_lab/lab_login");
    }

    @GetMapping("/lab_login")
    public String login(){
        return "lab/labLogin";
    }

    @PostMapping("/lab_login")
    public RedirectView LoginLab(HttpSession session,@RequestParam(value="labId") String labId, @RequestParam(value="labPassword") String labPassword, Model model){
        System.out.println(labId+"labId");
        System.out.println(labPassword+"labPassword");
        labPassword = passwordEncryptionService.hashPassword(labPassword);
        boolean isCredentialsValid;
        isCredentialsValid = loginSignupService.isLabCredentialValid(labId,labPassword);
        System.out.println(isCredentialsValid+"iscredentialValid");
//        if(isCredentialsValid){
//           // return "lab/lab_home";
//            return new RedirectView("/auth_lab/lab_home");
//        }else{
//            //return "lab/login";
//            return new RedirectView("/auth_lab/lab_login");
//        }

        if(isCredentialsValid){
            System.out.println("inside if");
            ILab lab = labService.getLabById(labId);
            session.setAttribute("CurrentLab",lab);
            session.setAttribute("Type","L");
            //List<Appointment> appointmentList = appointmentListService.listAppointmentbyDoctor(doctorId);
            System.out.println("labId"+labId);
            System.out.println("abcd");
            return new RedirectView("/auth_lab/lab_home");
        }else{
            return new RedirectView("/auth_lab/lab_login");
        }

    }
    @GetMapping("/lab_home")
    public String LabHome(HttpSession session, Model model){
        ILab currentLab= (Lab) session.getAttribute("CurrentLab");
        System.out.println("current Lab Id inside lab login signup controller"+currentLab.getLabId());
        List<Appointment> appointmentList = appointmentListService.listAppointmentbyLab(currentLab.getLabId());
        model.addAttribute("appointments",appointmentList);
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
