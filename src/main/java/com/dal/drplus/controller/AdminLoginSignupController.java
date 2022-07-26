package com.dal.drplus.controller;

import com.dal.drplus.model.IEntity.IAdmin;
import com.dal.drplus.model.entity.Admin;
import com.dal.drplus.model.factory.ModelFactory;
import com.dal.drplus.repository.implementation.AdminRepositoryImpl;
import com.dal.drplus.service.AdminLoginSignupService;
import com.dal.drplus.service.AdminService;
import com.dal.drplus.service.PasswordEncryptionService;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.servlet.view.RedirectView;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpSession;

@Controller
@RequestMapping(value = "/auth_admin")
public class AdminLoginSignupController {

    private AdminLoginSignupService loginSignupService;
    private AdminService adminService;
    private PasswordEncryptionService passwordEncryptionService;

    public AdminLoginSignupController(AdminRepositoryImpl adminRepository) {
        this.loginSignupService = new AdminLoginSignupService(adminRepository);
        this.adminService =new AdminService(adminRepository);
        this.passwordEncryptionService = new PasswordEncryptionService();
    }

    @GetMapping("/admin_signup")
    public String userSignup(Model model) {
        model.addAttribute("admin", ModelFactory.instance().createAdmin());
        return "admin/signup";
    }

    @PostMapping("/admin_signup")
    public RedirectView registerAdmin(@ModelAttribute Admin admin, @RequestParam(value = "confirmAdminPassword") String confirmPassword){
        System.out.println("admin "+admin.getAdminId());
        System.out.println("admin pas"+admin.getAdminPassword());
        System.out.println("admin ak"+admin.getAdminAccessKey());
        System.out.println("confirmPassword"+confirmPassword);
        admin.setAdminPassword(passwordEncryptionService.hashPassword(admin.getAdminPassword()));
        confirmPassword = passwordEncryptionService.hashPassword(confirmPassword);

        System.out.println("HASH admin pas"+admin.getAdminPassword());
        System.out.println("HASH confirmPassword"+confirmPassword);

        if((admin.validateAdminAccesskey(admin.getAdminAccessKey())) && (!adminService.isAdminIdExists(admin.getAdminId()))) {
            boolean result = loginSignupService.registerAdmin(admin, confirmPassword);
            return new RedirectView("/auth_admin/admin_login");
        } else {
            return new RedirectView("/auth_admin/admin_signup");
        }
    }

    @GetMapping("/admin_login")
    public String login(){
        return "admin/login";
    }

    @PostMapping("/admin_login")
    public RedirectView loginAdmin(HttpSession session, @RequestParam(value="adminId") String adminId, @RequestParam(value="adminPassword") String password){
        System.out.println(adminId+"adminId");
        System.out.println(password+" password");
        password = passwordEncryptionService.hashPassword(password);
        System.out.println(password+" hashed password");
        boolean isCredentialsValid;
        isCredentialsValid = loginSignupService.isAdminCredentialValid(adminId,password);
        System.out.println(isCredentialsValid+"iscredentialValid");
        if(isCredentialsValid){
            IAdmin admin = adminService.getAdminbyId(adminId);
            session.setAttribute("CurrentAdmin",admin);
            session.setAttribute("Type","A");
            return new RedirectView("/auth_admin/admin_home");
        }else{
            return new RedirectView("/auth_admin/admin_login");
        }
    }

    @GetMapping("/admin_home")
    public String adminHome(HttpSession session){
        IAdmin currentAdmin= (Admin) session.getAttribute("CurrentAdmin");
        System.out.println("current admin Id"+currentAdmin.getAdminId());
        System.out.println("current admin password"+currentAdmin.getAdminPassword());
        return "admin/admin_home";
    }

    @RequestMapping(value = "/logout")
    public RedirectView logoutAdmin(HttpSession session, HttpServletRequest request){
        System.out.println("inside logout");
        session.invalidate();
        System.out.println("session invalidated");
        return new RedirectView("/");
    }
}
