package com.dal.drplus.controller;

import com.dal.drplus.model.entity.Lab;
import com.dal.drplus.repository.implementation.LabRepositoryImpl;
import com.dal.drplus.service.LabService;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestParam;

import java.util.List;

@Controller
public class LabController {
    private LabService labService;

    public LabController(LabRepositoryImpl labRepository) {
        this.labService = new LabService(labRepository);
    }

    @GetMapping("/patient_lab_home")
    public String getLabHome(){
        return "/patient/patient_lab_home";
    }


    @GetMapping("/show_labs")
    public String filterLabOnPincode(Model model, @RequestParam("pincode") String pincode){
        System.out.println("Inside Lab Controller");
        List<Lab> labList = labService.filterLabOnPincode(pincode);
        model.addAttribute("labs",labList);
        return "lab/lab_list";
    }


}
