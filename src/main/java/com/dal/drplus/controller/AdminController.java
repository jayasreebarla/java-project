package com.dal.drplus.controller;

import com.dal.drplus.model.*;
import com.dal.drplus.repository.implementation.*;
import com.dal.drplus.service.*;
import com.dal.drplus.service.interfaces.NotificationsService;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.servlet.view.RedirectView;

import java.util.List;

@Controller
@RequestMapping(value = "/admin")
public class AdminController {
    private AppointmentService appointmentService;
    private AppointmentListService appointmentListService;
    private DoctorService doctorService;
    private LabService labService;
    private PatientService patientService;
    private DoctorSlotService doctorSlotService;
    private LabSlotService labSlotService;
    private RatingDoctorService ratingDoctorService;
    private RatingLabService ratingLabService;

    public AdminController(AppointmentRepositoryImpl appointmentRepository,
                           DoctorRepositoryImpl doctorRepository,
                           PatientRepositoryImpl patientRepository,
                           LabRepositoryImpl labRepository,
                           DoctorScheduleRepositoryImpl doctorScheduleRepository,
                           LabScheduleRepositoryImpl labScheduleRepository,
                           RatingDoctorRepositoryImpl ratingDoctorRepository,
                           RatingLabRepositoryImpl ratingLabRepository ) {
        this.appointmentService = new AppointmentService(appointmentRepository);
        this.appointmentListService = new AppointmentListService(appointmentRepository);
        this.doctorService = new DoctorService(doctorRepository);
        this.labService = new LabService(labRepository);
        this.patientService = new PatientService(patientRepository);
        this.doctorSlotService = new DoctorSlotService(doctorScheduleRepository);
        this.labSlotService = new LabSlotService(labScheduleRepository);
        this.ratingDoctorService = new RatingDoctorService(ratingDoctorRepository);
        this.ratingLabService = new RatingLabService(ratingLabRepository);
    }

    @GetMapping("/appointment_list_admin")
    public String getAppointmentList(Model model){
        NotificationsService notificationsService = new NotificationsService();
        //List<Appointment> appointmentList = appointmentListService.listAppointmentAll();
        List<Appointment> appointmentList = notificationsService.NotifyPatient("P12");
        model.addAttribute("appointments",appointmentList);

        /////////////////////////
        return "admin/appointments_list_admin";
    }

    @GetMapping("/doctor_list_admin")
    public String getDoctorList(Model model){
        List<Doctor> doctorList = doctorService.listAllDoctor();
        model.addAttribute("doctors",doctorList);
        return "admin/doctor_list_admin";
    }

    @GetMapping("/patient_list_admin")
    public String getPatientList(Model model){
        List<Patient> patientList = patientService.listAllPatients();
        model.addAttribute("patients",patientList);
        return "admin/patient_list_admin";
    }

    @GetMapping("/lab_list_admin")
    public String getLabList(Model model){
        List<Lab> labList = labService.listAllLabs();
        model.addAttribute("labs",labList);
        return "admin/lab_list_admin";
    }

    @GetMapping("/doctor_schedule_list_admin")
    public String getDocSchduleList(Model model){
        List<DoctorSchedule> doctorScheduleList = doctorSlotService.listAllDoctorSlots();
        model.addAttribute("doctorSlots",doctorScheduleList);
        return "admin/doctor_schedule_list_admin";
    }

    @GetMapping("/lab_schedule_list_admin")
    public String getLabScheduleList(Model model){
        List<LabSchedule> labSchedulesList = labSlotService.listAllLabSlots();
        model.addAttribute("labSlots",labSchedulesList);
        return "admin/lab_schedule_list_admin";
    }

    @GetMapping("/error_list")
    public String errorPage(){
        return "admin/error";
    }

    @GetMapping("/doctor_slot_add")
    public String doctorSlotAdd(Model model){
        model.addAttribute("doctorSchedule", new DoctorSchedule());
        return "admin/doctor_schedule_add";
    }

    @PostMapping("/doctor_slot_add")
    public RedirectView addDoctorSlot(@ModelAttribute DoctorSchedule doctorSchedule,
                                @RequestParam(value = "fromTime") String fromTime,
                                @RequestParam(value = "toTime") String toTime){
        doctorSchedule.setSlotTiming(fromTime+"-"+toTime);
        boolean result = doctorSlotService.addDoctorSlot(doctorSchedule);
        return new RedirectView("/admin/doctor_schedule_list_admin");
//        return "admin/doctor_schedule_add";
    }

    @GetMapping("/lab_slot_add")
    public String labSlotAdd(Model model){
        model.addAttribute("labSchedule", new LabSchedule());
        return "admin/lab_schedule_add";
    }

    @PostMapping("/lab_slot_add")
    public RedirectView addLabSlot(@ModelAttribute LabSchedule labSchedule,
                                      @RequestParam(value = "fromTime") String fromTime,
                                      @RequestParam(value = "toTime") String toTime){
        labSchedule.setSlotTiming(fromTime+"-"+toTime);
        boolean result = labSlotService.addLabSlot(labSchedule);
        return new RedirectView("/admin/lab_schedule_list_admin");
//        return "admin/lab_schedule_add";
    }

    @GetMapping("/cancel_appointment_admin/{id}")
    public RedirectView cancelAppointmentbyAdmin(@PathVariable int id){
        System.out.println("cancel appointment "+id);
        boolean result = appointmentService.cancelAppointment(id);
        if(result == true){
            return new RedirectView("/admin/appointment_list_admin");
        }else{
            return new RedirectView("/admin/error_list");
        }
    }

    @GetMapping("/delete_doctor_admin/{id}")
    public RedirectView deleteDoctorbyAdmin(@PathVariable String id){
        System.out.println("delete doctor "+id);
        boolean result = doctorService.deleteDoctorbyId(id);
        System.out.println("delete doctor res"+result);
        if(result == true){
            System.out.println("1");
            boolean result1 = appointmentService.deleteAppointmentbyDoctorId(id);
            if(result1 == true) {
                System.out.println("2");
                boolean result2 = doctorSlotService.deleteSlotbyDoctorId(id);
                if(result2 == true) {
                    System.out.println("3");
                    boolean result3 = ratingDoctorService.deleteDoctorRatingbydoctorid(id);
                    if(result3 == true) {
                        System.out.println("4");
                        return new RedirectView("/admin/doctor_list_admin");
                    }
                }
            }
        }
        return new RedirectView("/admin/error_list");
    }

    @GetMapping("/delete_patient_admin/{id}")
    public RedirectView deletePatientbyAdmin(@PathVariable String id){
        boolean result = patientService.deletePatientById(id);
        if(result == true){
            System.out.println("1");
            boolean result1 = appointmentService.deleteAppointmentbyPatientId(id);
            System.out.println("111 "+result1);
            if(result1 == true) {
                System.out.println("2");
                boolean result2 = ratingDoctorService.deleteDoctorRatingbyPatientId(id);
                if(result2 == true){
                    System.out.println("3");
                    boolean result3 = ratingLabService.deleteLabRatingbyPatientId(id);
                    if(result3 == true) {
                        System.out.println("4");
                        return new RedirectView("/admin/patient_list_admin");
                    }
                }
            }
        }
        return new RedirectView("/admin/error_list");
    }

    @GetMapping("/delete_lab_admin/{id}")
    public RedirectView deleteLabbyadmin(@PathVariable String id){
        boolean result = labService.deleteLabById(id);
        if(result == true){
            boolean result1 = appointmentService.deleteAppointmentbyLabId(id);
            if(result1 == true) {
                boolean result2 = labSlotService.deleteSlotByLabId(id);
                if(result2 == true) {
                    boolean result3 = ratingLabService.deleteLabRatingbyLabId(id);
                    if(result3 == true) {
                        return new RedirectView("/admin/lab_list_admin");
                    }
                }
            }
        }
        return new RedirectView("/admin/error_list");
    }

    @GetMapping("/delete_doctor_schedule_admin/{id}")
    public RedirectView deleteDoctorSchedulebyadmin(@PathVariable int id){
        boolean result = doctorSlotService.deleteSlotbyId(id);
        if(result == true){
            return new RedirectView("/admin/doctor_schedule_list_admin");
        }else{
            return new RedirectView("/admin/error_list");
        }
    }

    @GetMapping("/delete_lab_schedule_admin/{id}")
    public RedirectView deleteLabSchedulebyadmin(@PathVariable int id){
        boolean result = labSlotService.deleteSlotById(id);
        if(result == true){
            return new RedirectView("/admin/lab_schedule_list_admin");
        }else{
            return new RedirectView("/admin/error_list");
        }
    }

}
