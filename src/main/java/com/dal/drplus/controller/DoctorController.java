package com.dal.drplus.controller;

import com.dal.drplus.model.Doctor;
import com.dal.drplus.repository.implementation.DoctorRepositoryImpl;
import com.dal.drplus.service.DoctorService;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestParam;

import javax.print.Doc;
import java.util.ArrayList;
import java.util.List;

@Controller
public class DoctorController {

    private DoctorService doctorService;

    public DoctorController(DoctorRepositoryImpl doctorRepository) {
        this.doctorService = new DoctorService(doctorRepository);
    }


    @GetMapping("/show_doctors")
    public String filterDoctorOnPincodeAndSpecialization(Model model, @RequestParam("pincode") String pincode, @RequestParam("specialization") String specialization){
        List<Doctor> doctorList = doctorService.filterDoctorOnPincodeAndSpecialization(pincode,specialization);
        model.addAttribute("doctors",doctorList);
        return "doctor/doctor_list";
    }
}
