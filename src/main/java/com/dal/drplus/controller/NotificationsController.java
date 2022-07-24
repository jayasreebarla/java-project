package com.dal.drplus.controller;
import com.dal.drplus.model.Appointment;
import com.dal.drplus.model.Lab;
import com.dal.drplus.repository.implementation.NotificationsRepositoryImpl;
import com.dal.drplus.service.interfaces.NotificationsService;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;

import javax.servlet.http.HttpSession;
import java.util.List;
@Controller
public class NotificationsController {
    private NotificationsService notificationsService;

    public NotificationsController(NotificationsRepositoryImpl notificationsRepository) {
        this.notificationsService = new NotificationsService(notificationsRepository);
    }
    @GetMapping("/notification_patient")
    public String notifyPatient(Model model){
        List<Appointment> appointmentList = notificationsService.notifyPatient("P12");
        model.addAttribute("notifications",appointmentList);
//        return "admin/appointments_list_admin";
        return "notifications/patient_view_notifications";
    }

    @GetMapping("/notification_doctor")
    public String notifyDoctor(Model model){
        List<Appointment> appointmentList = notificationsService.notifyDoctor("P12");
        model.addAttribute("notifications",appointmentList);
//        return "admin/appointments_list_admin";
        return "notifications/doctor_view_notifications";
    }
//  x
    @GetMapping("/notification_lab")
    public String notifyLab(HttpSession httpSession, Model model){
        Lab currentLab = (Lab) httpSession.getAttribute("CurrentLab");
        List<Appointment> appointmentList = notificationsService.notifyLab(currentLab.getLabId());
        model.addAttribute("notifications",appointmentList);
//        return "admin/appointments_list_admin";
        return "notifications/lab_view_notifications";
    }

}
