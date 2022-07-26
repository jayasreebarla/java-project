package com.dal.drplus.controller;

import com.dal.drplus.model.IBuilder.IAppointmentBuilder;
import com.dal.drplus.model.IEntity.IAppointment;
import com.dal.drplus.model.IEntity.IDoctor;
import com.dal.drplus.model.IEntity.IPatient;
import com.dal.drplus.model.IEntity.ILab;
import com.dal.drplus.model.entity.Appointment;
import com.dal.drplus.model.entity.Patient;
import com.dal.drplus.model.factory.ModelFactory;
import com.dal.drplus.model.service.*;
import com.dal.drplus.repository.implementation.*;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;
import org.springframework.web.servlet.view.RedirectView;
import javax.servlet.http.HttpSession;
import java.util.List;

@Controller
public class AppointmentController {
    private AppointmentService appointmentService;
    private AppointmentListService appointmentListService;
    private DoctorSlotService doctorSlotService;
    private DoctorService doctorService;
    private PatientService patientService;
    private LabService labService;
    private LabSlotService labSlotService;
    private BillService billService;
    private WalletService walletService;

    public AppointmentController(AppointmentRepositoryImpl appointmentRepository,
                                 DoctorRepositoryImpl doctorRepository,
                                 PatientRepositoryImpl patientRepository,
                                 BillRepositoryImpl billRepository,
                                 WalletRepositoryImpl walletRepository,
                                 LabRepositoryImpl labRepository,
                                 DoctorScheduleRepositoryImpl doctorScheduleRepository,
                                 LabScheduleRepositoryImpl labScheduleRepository) {
        this.appointmentService = new AppointmentService(appointmentRepository);
        this.appointmentListService = new AppointmentListService(appointmentRepository);
        this.doctorService = new DoctorService(doctorRepository);
        this.patientService = new PatientService(patientRepository);
        this.billService = new BillService(billRepository);
        this.walletService = new WalletService(walletRepository);
        this.labService = new LabService(labRepository);
        this.doctorSlotService = new DoctorSlotService(doctorScheduleRepository);
        this.labSlotService = new LabSlotService(labScheduleRepository);
    }

    @GetMapping("/appointment_list")
    public String getAppointmentList(Model model, HttpSession session){
        IPatient currentPatient = (Patient) session.getAttribute("CurrentPatient");
        List<Appointment> appointmentList = appointmentListService.listAppointmentbyPatient(currentPatient.getPatientId());
        model.addAttribute("appointments",appointmentList);
        return "appointment/appointment_list";
    }

    @GetMapping("/doctorSlot/{slotId}/{doctorId}")
    public RedirectView bookAppointment(Model model, HttpSession session, @PathVariable("slotId") String slotId, @PathVariable("doctorId") String doctorId,RedirectAttributes attributes){
        IPatient currentPatient = (Patient) session.getAttribute("CurrentPatient");
        IDoctor doctor = doctorService.getDoctorById(doctorId);
        double billAmount = doctor.getDoctorFee();
        int billId = billService.generateBill(billAmount,"DOCTOR");
        IAppointmentBuilder appointmentBuilder = ModelFactory.instance().createAppointmentBuilder();
        IAppointment appointment = appointmentBuilder
                .addSlotId(Integer.parseInt(slotId))
                .addAppointmentType("DOCTOR")
                .addAppointmentFee(billAmount)
                .addPatientId(currentPatient.getPatientId())
                .addDoctorId(doctorId)
                .addBillId(billId)
                .build();

        boolean result = appointmentService.bookAppointment(appointment);
        if(result == true){
            doctorSlotService.updateSlotStatus(true, Integer.parseInt(slotId));
            attributes.addFlashAttribute("bill",billService.getBill(billId));
            return new RedirectView("/payment");
        } else {
            return new RedirectView("/error_appointment");
        }
    }

    @GetMapping("/labSlot/{slotId}/{labId}")
    public RedirectView bookAppointmentForLab(Model model, HttpSession session, @PathVariable("slotId") String slotId, @PathVariable("labId") String labId,RedirectAttributes attributes){
        Patient currentPatient = (Patient) session.getAttribute("CurrentPatient");
        ILab lab = labService.getLabById(labId);
        int billId = billService.generateBill(lab.getLabFee(),"LAB");
        double billAmount = lab.getLabFee();

        IAppointmentBuilder appointmentBuilder = ModelFactory.instance().createAppointmentBuilder();
        appointmentBuilder
                .addSlotId(Integer.parseInt(slotId))
                .addAppointmentType("LAB")
                .addAppointmentFee(billAmount)
                .addPatientId(currentPatient.getPatientId())
                .addLabId(labId)
                .addBillId(billId)
                .build();
        IAppointment appointment = ModelFactory.instance().createAppointmentUsingBuilder(appointmentBuilder);

        boolean result = appointmentService.bookAppointment(appointment);
        if(result == true){
            labSlotService.updateSlotStatus(true, Integer.parseInt(slotId));
            attributes.addFlashAttribute("bill",billService.getBill(billId));
            return new RedirectView("/payment");
        } else {
            return new RedirectView("/error_appointment");
        }
    }

    @GetMapping("/rescheduleDoctorSlot/{slotId}/{appointmentId}")
    public RedirectView rescheduleDoctorSlot(Model model, @PathVariable("slotId") int slotId, @PathVariable("appointmentId") int appointmentId){
        IAppointment appointment = appointmentService.findAppointmentbyId(appointmentId);
        int previousSlotId = appointment.getSlotId();
        boolean res = doctorSlotService.updateSlotStatus(false,previousSlotId);
        boolean res1 = appointmentService.rescheduleAppointment(slotId,appointmentId);
        boolean res2 = doctorSlotService.updateSlotStatus(true,slotId);
        return new RedirectView("/appointment_booked_page");
    }

    @GetMapping("/rescheduleLabSlot/{slotId}/{appointmentId}")
    public RedirectView rescheduleLabSlot(Model model, @PathVariable("slotId") int slotId, @PathVariable("appointmentId") int appointmentId){
        IAppointment appointment = appointmentService.findAppointmentbyId(appointmentId);
        int previousSlotId = appointment.getSlotId();
        boolean res = labSlotService.updateSlotStatus(false,previousSlotId);
        boolean res1 = appointmentService.rescheduleAppointment(slotId,appointmentId);
        boolean res2 = labSlotService.updateSlotStatus(true,slotId);
        return new RedirectView("/appointment_booked_page");
    }

    @GetMapping("/error_appointment")
    public String errorPage(){
        return "appointment/error";
    }

    @GetMapping("/cancel_appointment/{id}/{slotId}")
    public RedirectView cancelAppointment(HttpSession session, @PathVariable("id") int id, @PathVariable("slotId") int slotId){
        IPatient currentPatient = (Patient) session.getAttribute("CurrentPatient");
        IAppointment appointment = appointmentService.findAppointmentbyId(id);
        double amountToBeCredited = appointment.getAppointmentFee();
        boolean result = appointmentService.cancelAppointment(id);
        if(result == true){
            doctorSlotService.updateSlotStatus(false, slotId);
            walletService.addMoneyToWallet(amountToBeCredited, currentPatient.getPatientEmail());
            return new RedirectView("/appointment_list");
        }else{
            return new RedirectView("/error_appointment");
        }
    }

    @GetMapping("/cancel_appointment_doc/{id}/{slotId}")
    public RedirectView cancelAppointmentbyDoc(@PathVariable("id") int id, @PathVariable("slotId") int slotId){
        IAppointment appointment = appointmentService.findAppointmentbyId(id);
        String patientId = appointment.getPatientId();
        double amountToBeCredited = appointment.getAppointmentFee();
        IPatient patient = patientService.getPatientById(patientId);
        boolean result = appointmentService.cancelAppointment(id);
        if(result == true){
            doctorSlotService.updateSlotStatus(false, slotId);
            walletService.addMoneyToWallet(amountToBeCredited,patient.getPatientEmail());
            return new RedirectView("/auth_doctor/doctor_home");
        }
        else{
            return new RedirectView("/error_appointment");
        }
    }

    @GetMapping("/cancel_appointment_lab/{id}/{slotId}")
    public RedirectView cancelAppointmentbyLab(@PathVariable("id") int id, @PathVariable("slotId") int slotId){
        IAppointment appointment = appointmentService.findAppointmentbyId(id);
        String patientId = appointment.getPatientId();
        double amountToBeCredited = appointment.getAppointmentFee();
        IPatient patient = patientService.getPatientById(patientId);
        boolean result = appointmentService.cancelAppointment(id);
        if(result == true){
            labSlotService.updateSlotStatus(false, slotId);
            walletService.addMoneyToWallet(amountToBeCredited,patient.getPatientEmail());
            return new RedirectView("/auth_lab/lab_home");
        }else{
            return new RedirectView("/error_appointment");
        }
    }

    @GetMapping("/appointment_booked_page")
    public String appointmentBooked(){
        return "appointment/appointment_booked";
    }
}
