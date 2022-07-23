package com.dal.drplus.controller;
import com.dal.drplus.model.Appointment;
import com.dal.drplus.repository.implementation.NotificationsRepositoryImpl;
import com.dal.drplus.service.interfaces.NotificationsService;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;

import java.util.List;
@Controller
public class NotificationsController {
    private NotificationsService notificationsService;

    public NotificationsController(NotificationsRepositoryImpl notificationsRepository) {
        this.notificationsService = new NotificationsService(notificationsRepository);
    }
    @GetMapping("/notification_patient")
    public String notifyPatient(Model model){
        NotificationsService notificationsService = new NotificationsService();
        List<Appointment> appointmentList = notificationsService.NotifyPatient("P12");
        model.addAttribute("appointments",appointmentList);
        return "admin/appointments_list_admin";
    }

}
