package com.dal.drplus.controller;

import com.dal.drplus.model.LabSchedule;
import com.dal.drplus.repository.implementation.LabScheduleRepositoryImpl;
import com.dal.drplus.service.LabSlotService;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;

import java.util.List;
@Controller
public class LabSlotController {
    private LabSlotService labSlotService;

    public LabSlotController(LabScheduleRepositoryImpl labScheduleRepository) {
        this.labSlotService = new LabSlotService(labScheduleRepository);
    }

    @GetMapping("/lab/{labId}")
    public String filterSlotOnLabId(Model model, @PathVariable("labId") String labId){
        System.out.println("lab: "+labId);
        List<LabSchedule> labScheduleList = labSlotService.filterSlotOnLabId(labId);
        model.addAttribute("labSlots",labScheduleList);
        return "lab/lab_availability";
    }
}
