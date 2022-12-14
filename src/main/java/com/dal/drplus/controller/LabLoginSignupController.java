package com.dal.drplus.controller;
import com.dal.drplus.model.IEntity.ILab;
import com.dal.drplus.model.entity.Appointment;
import com.dal.drplus.model.entity.Lab;
import com.dal.drplus.model.factory.ModelFactory;
import com.dal.drplus.repository.implementation.AppointmentRepositoryImpl;
import com.dal.drplus.repository.implementation.LabRepositoryImpl;
import com.dal.drplus.model.service.AppointmentListService;
import com.dal.drplus.model.service.LabLoginSignupService;
import com.dal.drplus.model.service.LabService;
import com.dal.drplus.model.service.PasswordEncryptionService;
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
    public RedirectView RegisterLab(HttpSession session, @ModelAttribute Lab lab) {
        lab.setLabPassword(passwordEncryptionService.hashPassword(lab.getLabPassword()));
        if (lab.validateLabPersonNameFormat(lab.getLabPersonName()) && lab.validateLabEmailIdFormat(lab.getLabEmailId()) && lab.validateLabContactInfoFormat(lab.getLabContactInfo()) && lab.validateLabPincodeFormat(lab.getLabPincode())) {
            boolean result = loginSignupService.registerLab(lab);
            String type = String.valueOf(session.getAttribute("Type"));
            if (type.equals("A")) {
                return new RedirectView("/admin/lab_list_admin");
            }
            return new RedirectView("/auth_lab/lab_login");
        }
        return new RedirectView("/auth_lab/lab_signup");
    }

    @GetMapping("/lab_login")
    public String login(){
        return "lab/labLogin";
    }

    @PostMapping("/lab_login")
    public RedirectView LoginLab(HttpSession session,@RequestParam(value="labId") String labId, @RequestParam(value="labPassword") String labPassword, Model model){
        labPassword = passwordEncryptionService.hashPassword(labPassword);
        boolean isCredentialsValid;
        isCredentialsValid = loginSignupService.isLabCredentialValid(labId,labPassword);
        if(isCredentialsValid){
            ILab lab = labService.getLabById(labId);
            session.setAttribute("CurrentLab",lab);
            session.setAttribute("Type","L");
            return new RedirectView("/auth_lab/lab_home");
        }else{
            return new RedirectView("/auth_lab/lab_login");
        }
    }
    @GetMapping("/lab_home")
    public String LabHome(HttpSession session, Model model){
        ILab currentLab= (Lab) session.getAttribute("CurrentLab");
        List<Appointment> appointmentList = appointmentListService.listAppointmentbyLab(currentLab.getLabId());
        model.addAttribute("appointments",appointmentList);
        return "lab/lab_home";
    }

    @RequestMapping(value = "/logout")
    public RedirectView LogoutLab(HttpSession session,HttpServletRequest request){
        session.invalidate();
        return new RedirectView("/");
    }
}
