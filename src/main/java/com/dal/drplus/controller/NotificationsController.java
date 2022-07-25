package com.dal.drplus.controller;
import com.dal.drplus.model.entity.Appointment;
import com.dal.drplus.model.entity.Doctor;
import com.dal.drplus.model.entity.Lab;
import com.dal.drplus.model.entity.Patient;
import com.dal.drplus.repository.implementation.NotificationsRepositoryImpl;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import com.dal.drplus.service.NotificationsService;

import javax.servlet.http.HttpSession;
import java.util.List;
@Controller
public class NotificationsController {
    private NotificationsService notificationsService;

    public NotificationsController(NotificationsRepositoryImpl notificationsRepository) {
        this.notificationsService = new NotificationsService(notificationsRepository);
    }

    @GetMapping("/notification_patient")
    public String notifyPatient(Model model,HttpSession httpSession) {
        Patient currentPatient = (Patient) httpSession.getAttribute("CurrentPatient");
        List<Appointment> appointmentList = notificationsService.notifyPatient(currentPatient.getPatientId());
        model.addAttribute("notifications", appointmentList);
//        return "admin/appointments_list_admin";
        return "notifications/patient_view_notifications";
    }

    @GetMapping("/notification_doctor")
    public String notifyDoctor(Model model,HttpSession httpSession) {
        Doctor currentDoctor = (Doctor) httpSession.getAttribute("CurrentDoctor");
        List<Appointment> appointmentList = notificationsService.notifyDoctor(currentDoctor.getDoctorId());
        model.addAttribute("notifications", appointmentList);
//        return "admin/appointments_list_admin";
        return "notifications/doctor_view_notifications";
    }

    //  x
    @GetMapping("/notification_lab")
    public String notifyLab(HttpSession httpSession, Model model) {
        Lab currentLab = (Lab) httpSession.getAttribute("CurrentLab");
        List<Appointment> appointmentList = notificationsService.notifyLab(currentLab.getLabId());
        model.addAttribute("notifications", appointmentList);
//        return "admin/appointments_list_admin";
        return "notifications/lab_view_notifications";
    }
}
