package com.dal.drplus.controller;

import com.dal.drplus.model.IEntity.IAppointment;
import com.dal.drplus.model.entity.LabSchedule;
import com.dal.drplus.repository.implementation.AppointmentRepositoryImpl;
import com.dal.drplus.repository.implementation.LabScheduleRepositoryImpl;
import com.dal.drplus.service.AppointmentService;
import com.dal.drplus.service.LabSlotService;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;

import java.util.List;
@Controller
public class LabSlotController {
    private LabSlotService labSlotService;
    private AppointmentService appointmentService;

    public LabSlotController(LabScheduleRepositoryImpl labScheduleRepository,
                            AppointmentRepositoryImpl appointmentRepository) {
        this.labSlotService = new LabSlotService(labScheduleRepository);
        this.appointmentService = new AppointmentService(appointmentRepository);
    }

    @GetMapping("/lab/{labId}")
    public String filterSlotOnLabId(Model model, @PathVariable("labId") String labId){
        System.out.println("lab: "+labId);
        List<LabSchedule> labScheduleList = labSlotService.filterUnbookedSlotOnLabId(labId);
        model.addAttribute("labSlots",labScheduleList);
        return "lab/lab_availability";
    }

    @GetMapping("/lab_reschedule/{appointmentId}")
    public String rescheduleSlotOnLabId(Model model, @PathVariable("appointmentId") int appointmentId){
        System.out.println("appointmentId: "+appointmentId);
        IAppointment appointment = appointmentService.findAppointmentbyId(appointmentId);
        List<LabSchedule> labScheduleList = labSlotService.filterUnbookedSlotOnLabId(appointment.getLabId());
        model.addAttribute("labSlots",labScheduleList);
        model.addAttribute("appointment",appointment);
        return "lab/lab_reschedule_availability";
    }
}
