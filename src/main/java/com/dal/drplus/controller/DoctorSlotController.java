package com.dal.drplus.controller;

import com.dal.drplus.model.IEntity.IAppointment;
import com.dal.drplus.model.entity.DoctorSchedule;
import com.dal.drplus.repository.implementation.AppointmentRepositoryImpl;
import com.dal.drplus.repository.implementation.DoctorScheduleRepositoryImpl;
import com.dal.drplus.service.AppointmentService;
import com.dal.drplus.service.DoctorSlotService;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import java.util.List;

@Controller
public class DoctorSlotController {
    private DoctorSlotService doctorSlotService;
    private AppointmentService appointmentService;
    public DoctorSlotController(DoctorScheduleRepositoryImpl doctorScheduleRepository,
                                AppointmentRepositoryImpl appointmentRepository) {
        this.doctorSlotService = new DoctorSlotService(doctorScheduleRepository);
        this.appointmentService = new AppointmentService(appointmentRepository);
    }

    @GetMapping("/doctor/{doctorId}")
    public String filterSlotOnDoctorId(Model model, @PathVariable("doctorId") String doctorId){
        List<DoctorSchedule> doctorScheduleList = doctorSlotService.filterUnbookedSlotOnDoctorId(doctorId);
        model.addAttribute("doctorSlots",doctorScheduleList);
        return "doctor/doctor_availability";
    }

    @GetMapping("/doctor_reschedule/{appointmentId}")
    public String listRescheduleSlotOnDoctorId(Model model, @PathVariable("appointmentId") int appointmentId){
        IAppointment appointment = appointmentService.findAppointmentbyId(appointmentId);
        List<DoctorSchedule> doctorScheduleList = doctorSlotService.filterUnbookedSlotOnDoctorId(appointment.getDoctorId());
        model.addAttribute("doctorSlots",doctorScheduleList);
        model.addAttribute("appointment",appointment);
        return "doctor/doctor_reschedule_availability";
    }
}

