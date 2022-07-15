package com.dal.drplus.controller;

import com.dal.drplus.model.Appointment;
import com.dal.drplus.model.Doctor;
import com.dal.drplus.model.DoctorSchedule;
import com.dal.drplus.model.Patient;
import com.dal.drplus.repository.implementation.AppointmentRepositoryImpl;
import com.dal.drplus.repository.interfaces.IAppointmentRepository;
import com.dal.drplus.service.AppointmentListService;
import com.dal.drplus.service.AppointmentService;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.servlet.view.RedirectView;

import javax.print.Doc;
import javax.servlet.http.HttpSession;
import java.util.ArrayList;
import java.util.List;

@Controller
public class AppointmentController {

    private AppointmentService appointmentService;
    private AppointmentListService appointmentListService;

    public AppointmentController(AppointmentRepositoryImpl appointmentRepository) {
        this.appointmentService = new AppointmentService(appointmentRepository);
        this.appointmentListService = new AppointmentListService(appointmentRepository);
    }

    @GetMapping("/appointment_list")
    public String getAppointmentList(Model model, HttpSession session){
        Patient currentPatient = (Patient) session.getAttribute("CurrentPatient");
        System.out.println("current Patient Id inside appointment controller"+currentPatient.getPatientId());
        List<Appointment> appointmentList = appointmentListService.listAppointmentbyPatient(currentPatient.getPatientId());
        model.addAttribute("appointments",appointmentList);
        return "appointment/appointment_list";
    }

    @GetMapping("/doctorSlot/{slotId}/{doctorId}")
    public String bookAppointment(Model model, HttpSession session, @PathVariable("slotId") String slotId, @PathVariable("doctorId") String doctorId){

        Appointment appointment = new Appointment();
        Patient currentPatient = (Patient) session.getAttribute("CurrentPatient");

        appointment.setSlotId(Integer.parseInt(slotId));
        appointment.setAppointmentType("DOCTOR");
        appointment.setAppointmentDescription("");
        appointment.setAppointmentFee(0);
        appointment.setPatientId(currentPatient.getPatientId());
        appointment.setDoctorId(doctorId);
        appointment.setBillId(0);
        appointment.setLabId("");

        appointmentService.bookAppointment(appointment);
        return "appointment/appointment_booked";
    }

    @GetMapping("/labSlot/{slotId}/{labId}")
    public String bookAppointmentForLab(Model model, HttpSession session, @PathVariable("slotId") String slotId, @PathVariable("labId") String labId){

        Appointment appointment = new Appointment();
        Patient currentPatient = (Patient) session.getAttribute("CurrentPatient");

        appointment.setSlotId(Integer.parseInt(slotId));
        appointment.setAppointmentType("LAB");
        appointment.setAppointmentDescription("");
        appointment.setAppointmentFee(0);
        appointment.setPatientId(currentPatient.getPatientId());
        appointment.setDoctorId(labId);
        appointment.setBillId(0);
        appointment.setLabId("");

        appointmentService.bookAppointment(appointment);
        return "appointment/appointment_booked";
    }

    @GetMapping("/error_appointment")
    public String errorPage(){
        return "appointment/error";
    }

    @GetMapping("/cancel_appointment/{id}")
    public RedirectView cancelAppointment(@PathVariable int id){

        boolean result = appointmentService.cancelAppointment(id);
        if(result == true){
            return new RedirectView("/appointment_list");
        }else{
            return new RedirectView("/error_appointment");
        }
    }

    @GetMapping("/cancel_appointment_doc/{id}")
    public String cancelAppointmentbyDoc(@PathVariable int id){
        boolean result = appointmentService.cancelAppointment(id);
        if(result == true){
            return "doctor/doctor_home";
        }else{
            return "appointment/error";
        }
    }
}
