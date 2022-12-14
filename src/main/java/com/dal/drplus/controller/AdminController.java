package com.dal.drplus.controller;

import com.dal.drplus.model.entity.*;
import com.dal.drplus.model.service.*;
import com.dal.drplus.repository.implementation.*;
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
    private PromotionsService promotionsService;
    private NotificationsService notificationsService;

    public AdminController(AppointmentRepositoryImpl appointmentRepository,
                           DoctorRepositoryImpl doctorRepository,
                           PatientRepositoryImpl patientRepository,
                           LabRepositoryImpl labRepository,
                           DoctorScheduleRepositoryImpl doctorScheduleRepository,
                           LabScheduleRepositoryImpl labScheduleRepository,
                           RatingDoctorRepositoryImpl ratingDoctorRepository,
                           RatingLabRepositoryImpl ratingLabRepository,
                           PromotionsRepositoryImpl promotionsRepository,NotificationsRepositoryImpl notificationsRepository) {
        this.appointmentService = new AppointmentService(appointmentRepository);
        this.appointmentListService = new AppointmentListService(appointmentRepository);
        this.doctorService = new DoctorService(doctorRepository);
        this.labService = new LabService(labRepository);
        this.patientService = new PatientService(patientRepository);
        this.doctorSlotService = new DoctorSlotService(doctorScheduleRepository);
        this.labSlotService = new LabSlotService(labScheduleRepository);
        this.ratingDoctorService = new RatingDoctorService(ratingDoctorRepository);
        this.ratingLabService = new RatingLabService(ratingLabRepository);
        this.promotionsService = new PromotionsService(promotionsRepository);
        this.notificationsService = new NotificationsService(notificationsRepository);
    }

    @GetMapping("/appointment_list_admin")
    public String getAppointmentList(Model model){
        List<Appointment> appointmentList = appointmentListService.listAppointmentAll();
        model.addAttribute("appointments",appointmentList);
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

    @GetMapping("/promotions_list_admin")
    public String getPromotionsList(Model model){
        List<Promotions> promotionsList = promotionsService.listAllPromotions();
        model.addAttribute("Promotions",promotionsList);
        return "admin/promotions_list";
    }

    @GetMapping("/delete_promotion_admin/{id}")
    public RedirectView cancelPromotionsbyAdmin(@PathVariable String id){
        boolean result = promotionsService.deletePromotionsbyId(id);
        if(result == true){
            return new RedirectView("/admin/promotions_list_admin");
        } else {
            return new RedirectView("/admin/error_list");
        }
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
        if(doctorSchedule.validateSlotDateFormat(doctorSchedule.getSlotDate())) {
            boolean result = doctorSlotService.addDoctorSlot(doctorSchedule);
            return new RedirectView("/admin/doctor_schedule_list_admin");
        }
        return new RedirectView("/admin/doctor_slot_add");
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
        if(labSchedule.validateSlotDateFormat(labSchedule.getSlotDate())) {
            boolean result = labSlotService.addLabSlot(labSchedule);
            return new RedirectView("/admin/lab_schedule_list_admin");
        }
        return new RedirectView("/admin/lab_slot_add");
    }

    @GetMapping("/cancel_appointment_admin/{id}")
    public RedirectView cancelAppointmentbyAdmin(@PathVariable int id){
        boolean result = appointmentService.cancelAppointment(id);
        if(result == true){
            return new RedirectView("/admin/appointment_list_admin");
        }else{
            return new RedirectView("/admin/error_list");
        }
    }

    @GetMapping("/delete_doctor_admin/{id}")
    public RedirectView deleteDoctorbyAdmin(@PathVariable String id){
        boolean result = doctorService.deleteDoctorbyId(id);
        if(result == true){
            boolean result1 = appointmentService.deleteAppointmentbyDoctorId(id);
            if(result1 == true) {
                boolean result2 = doctorSlotService.deleteSlotbyDoctorId(id);
                if(result2 == true) {
                    boolean result3 = ratingDoctorService.deleteDoctorRatingbydoctorid(id);
                    if(result3 == true) {
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
            boolean result1 = appointmentService.deleteAppointmentbyPatientId(id);
            if(result1 == true) {
                boolean result2 = ratingDoctorService.deleteDoctorRatingbyPatientId(id);
                if(result2 == true){
                    boolean result3 = ratingLabService.deleteLabRatingbyPatientId(id);
                    if(result3 == true) {
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
