package com.dal.drplus.controller;

import com.dal.drplus.model.*;
import com.dal.drplus.repository.implementation.*;
import com.dal.drplus.repository.interfaces.IAppointmentRepository;
import com.dal.drplus.service.*;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;
import org.springframework.web.servlet.view.RedirectView;

import javax.print.Doc;
import javax.servlet.http.HttpSession;
import java.util.ArrayList;
import java.util.List;

@Controller
public class AppointmentController {

    private AppointmentService appointmentService;
    private AppointmentListService appointmentListService;
    private DoctorSlotService doctorSlotService;
    private DoctorService doctorService;
    private LabService labService;
    private LabSlotService labSlotService;
    private BillService billService;
    public AppointmentController(AppointmentRepositoryImpl appointmentRepository,
                                 DoctorRepositoryImpl doctorRepository,
                                 BillRepositoryImpl billRepository,
                                 WalletRepositoryImpl walletRepository,
                                 LabRepositoryImpl labRepository,
                                 DoctorScheduleRepositoryImpl doctorScheduleRepository,
                                 LabScheduleRepositoryImpl labScheduleRepository) {
        this.appointmentService = new AppointmentService(appointmentRepository);
        this.appointmentListService = new AppointmentListService(appointmentRepository);
        this.doctorService = new DoctorService(doctorRepository);
        this.billService = new BillService(billRepository);
        this.labService = new LabService(labRepository);
        this.doctorSlotService = new DoctorSlotService(doctorScheduleRepository);
        this.labSlotService = new LabSlotService(labScheduleRepository);
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
    public RedirectView bookAppointment(Model model, HttpSession session, @PathVariable("slotId") String slotId, @PathVariable("doctorId") String doctorId, RedirectAttributes attributes){

        Appointment appointment = new Appointment();
        Patient currentPatient = (Patient) session.getAttribute("CurrentPatient");
        Doctor doctor = doctorService.getDoctorById(doctorId);
        int billId = billService.generateBill(doctor.getDoctorFee(),"DOCTOR");
        //double billAmount = doctor.getDoctorFee();
        appointment.setSlotId(Integer.parseInt(slotId));
        appointment.setAppointmentType("DOCTOR");
        appointment.setAppointmentDescription("");
        appointment.setAppointmentFee(doctor.getDoctorFee());
        appointment.setPatientId(currentPatient.getPatientId());
        appointment.setDoctorId(doctorId);
        appointment.setBillId(billId);
        appointment.setLabId("");

        boolean result = appointmentService.bookAppointment(appointment);
        if(result == true){
            doctorSlotService.updateSlotStatus(true, Integer.parseInt(slotId));
        }
        //return "appointment/appointment_booked";
//        attributes.addFlashAttribute("billAmount",billAmount);

        attributes.addFlashAttribute("bill",billService.getBill(billId));
        //System.out.println("bill amount in appointment controller"+billService.getBillAmount(billId));
        return new RedirectView("/payment");
    }

    @GetMapping("/labSlot/{slotId}/{labId}")
    public RedirectView bookAppointmentForLab(Model model, HttpSession session, @PathVariable("slotId") String slotId, @PathVariable("labId") String labId, RedirectAttributes attributes){

        Appointment appointment = new Appointment();
        Patient currentPatient = (Patient) session.getAttribute("CurrentPatient");
        Lab lab = labService.getLabById(labId);
        int billId = billService.generateBill(lab.getLabFee(),"LAB");
        double billAmount = lab.getLabFee();
        appointment.setSlotId(Integer.parseInt(slotId));
        appointment.setAppointmentType("LAB");
        appointment.setAppointmentDescription("");
        appointment.setAppointmentFee(lab.getLabFee());
        appointment.setPatientId(currentPatient.getPatientId());
        appointment.setDoctorId("");
        appointment.setBillId(billId);
        appointment.setLabId(labId);

        boolean result = appointmentService.bookAppointment(appointment);
        if(result == true){
            labSlotService.updateSlotStatus(true, Integer.parseInt(slotId));
        }
//        attributes.addFlashAttribute("billAmount",billAmount);
//        System.out.println("bill amount in appointment controller"+billAmount);
        attributes.addFlashAttribute("bill",billService.getBill(billId));
        return new RedirectView("/payment");
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

    @GetMapping("/cancel_appointment_lab/{id}")
    public String cancelAppointmentbyLab(@PathVariable int id){
        boolean result = appointmentService.cancelAppointment(id);
        if(result == true){
            return "lab/lab_home";
        }else{
            return "appointment/error";
        }
    }

    @GetMapping("/appointment_booked_page")
    public String appointmentBooked(){
        return "appointment/appointment_booked";
    }
}
