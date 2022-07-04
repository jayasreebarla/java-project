package com.dal.drplus.controller;

import com.dal.drplus.model.DoctorSchedule;
import com.dal.drplus.repository.implementation.DoctorScheduleRepositoryImpl;
import com.dal.drplus.service.DoctorSlotService;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestParam;

import javax.print.Doc;
import java.util.ArrayList;
import java.util.List;

@Controller
public class DoctorSlotController {

    private DoctorSlotService doctorSlotService;

    public DoctorSlotController(DoctorScheduleRepositoryImpl doctorScheduleRepository) {
        this.doctorSlotService = new DoctorSlotService(doctorScheduleRepository);
    }

    @GetMapping("/doctor/{doctorId}")
    public String filterSlotOnDoctorId(Model model, @PathVariable("doctorId") String doctorId){
        System.out.println("doctor"+doctorId);
        List<DoctorSchedule> doctorScheduleList = doctorSlotService.filterSlotOnDoctorId(doctorId);
        model.addAttribute("doctorSlots",doctorScheduleList);
        return "doctor/doctor_availability";
    }
}

