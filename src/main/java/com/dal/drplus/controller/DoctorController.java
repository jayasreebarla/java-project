package com.dal.drplus.controller;

import com.dal.drplus.model.entity.Doctor;
import com.dal.drplus.repository.implementation.DoctorRepositoryImpl;
import com.dal.drplus.repository.implementation.DoctorScheduleRepositoryImpl;
import com.dal.drplus.repository.implementation.RatingDoctorRepositoryImpl;
import com.dal.drplus.service.DoctorService;
import com.dal.drplus.service.DoctorSlotService;
import com.dal.drplus.service.RatingDoctorService;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestParam;
import java.util.*;

@Controller
public class DoctorController {
    private DoctorService doctorService;
    private RatingDoctorService ratingDoctorService;
    private DoctorSlotService doctorSlotService;
    private List<Doctor> doctorList;

    public DoctorController(DoctorRepositoryImpl doctorRepository, RatingDoctorRepositoryImpl ratingDoctorRepository, DoctorScheduleRepositoryImpl doctorScheduleRepository) {
        this.doctorService = new DoctorService(doctorRepository);
        this.ratingDoctorService = new RatingDoctorService(ratingDoctorRepository);
        this.doctorSlotService = new DoctorSlotService(doctorScheduleRepository);
    }

    @GetMapping("/show_doctors")
    public String filterDoctorOnPincodeAndSpecialization(Model model, @RequestParam("pincode") String pincode, @RequestParam("specialization") String specialization){
        doctorList = doctorService.filterDoctorOnPincodeAndSpecialization(pincode,specialization);
        Iterator<Doctor> doctorIterator = doctorList.iterator();
        while(doctorIterator.hasNext()){
            Doctor doctor = doctorIterator.next();
            int rating = ratingDoctorService.getRating(doctor.getDoctorId());
            doctor.setDoctorRating(rating);
        }
        List<Doctor> doctorListSorted = doctorService.sortDoctorList(doctorList);
        model.addAttribute("doctors",doctorListSorted);
        return "doctor/doctor_list";
    }

    @GetMapping("/reviews_doctor/{doctorId}")
    public String filterReviewsOnDoctorId(Model model, @PathVariable("doctorId") String doctorId){
        List<String> reviews = ratingDoctorService.getReviews(doctorId);
        model.addAttribute("doctorId",doctorId);
        model.addAttribute("reviews",reviews);
        return "Rating/list_of_reviews_doctor";
    }

}
