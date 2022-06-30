package com.dal.drplus.controller;

import com.dal.drplus.model.Admin;
import com.dal.drplus.repository.implementation.AdminRepositoryImpl;
import com.dal.drplus.service.AdminLoginSignupService;
import com.dal.drplus.service.AdminService;
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

    public AdminLoginSignupController(AdminRepositoryImpl adminRepository) {
        this.loginSignupService = new AdminLoginSignupService(adminRepository);
        this.adminService =new AdminService(adminRepository);
    }

    @GetMapping("/admin_signup")
    public String userSignup(Model model) {
        model.addAttribute("admin", new Admin());
        return "admin/signup";
    }

    @PostMapping("/admin_signup")
    public String registerAdmin(@ModelAttribute Admin admin, @RequestParam(value = "confirmAdminPassword") String confirmPassword){
        System.out.println("admin "+admin.getAdminId());
        System.out.println("admin pas"+admin.getAdminPassword());
        System.out.println("admin ak"+admin.getAdminAccessKey());
        System.out.println("confirmPassword"+confirmPassword);
        boolean result = loginSignupService.registerAdmin(admin,confirmPassword);
        return "admin/login";
    }

    @GetMapping("/admin_login")
    public String login(){
        return "admin/login";
    }

    @PostMapping("/admin_login")
    public RedirectView loginAdmin(HttpSession session, @RequestParam(value="adminId") String adminId, @RequestParam(value="adminPassword") String password){
        System.out.println(adminId+"adminId");
        System.out.println(password+"password");
        boolean isCredentialsValid;
        isCredentialsValid = loginSignupService.isAdminCredentialValid(adminId,password);
        System.out.println(isCredentialsValid+"iscredentialValid");
        if(isCredentialsValid){
            Admin admin = adminService.getAdminbyId(adminId);
            session.setAttribute("CurrentAdmin",admin);
            return new RedirectView("/auth_admin/admin_home");
        }else{
            return new RedirectView("/auth_admin/admin_login");
        }
    }

    @GetMapping("/admin_home")
    public String adminHome(HttpSession session){
        Admin currentAdmin= (Admin) session.getAttribute("CurrentAdmin");
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
