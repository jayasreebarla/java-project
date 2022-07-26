package com.dal.drplus.controller;

import com.dal.drplus.model.entity.Lab;
import com.dal.drplus.model.entity.RatingLab;
import com.dal.drplus.repository.implementation.LabRepositoryImpl;
import com.dal.drplus.repository.implementation.RatingLabRepositoryImpl;
import com.dal.drplus.service.LabService;
import com.dal.drplus.service.RatingLabService;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestParam;

import java.util.Iterator;
import java.util.List;

@Controller
public class LabController {
    private LabService labService;
    private RatingLabService ratingLabService;

    private Lab lab;

    public LabController(LabRepositoryImpl labRepository, RatingLabRepositoryImpl ratingLabRepository) {
        this.ratingLabService = new RatingLabService(ratingLabRepository);
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

        Iterator<Lab> labIterator =  labList.iterator();
        while(labIterator.hasNext()){
            Lab lab = labIterator.next();
            int rating = ratingLabService.getRating(lab.getLabId());
            lab.setLabRating(rating);
        }
        List<Lab> labListSorted = labService.sortLabList(labList);
        model.addAttribute("labs",labList);
        return "lab/lab_list";
    }

    @GetMapping("/reviews_lab/{labId}")
    public String filterReviewsOnDoctorId(Model model, @PathVariable("labId") String labID){
        System.out.println("lab"+labID);
        List<String> reviews = ratingLabService.getReviews(labID);
        model.addAttribute("labId",labID);
        model.addAttribute("reviews",reviews);
        return "Rating/list_of_reviews_doctor";
    }


}
