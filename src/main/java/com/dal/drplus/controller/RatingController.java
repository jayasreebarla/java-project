package com.dal.drplus.controller;

import com.dal.drplus.model.Patient;
import com.dal.drplus.model.RatingDoctor;
import com.dal.drplus.model.RatingLab;
import com.dal.drplus.repository.implementation.RatingDoctorRepositoryImpl;
import com.dal.drplus.service.RatingDoctorService;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;

import javax.servlet.http.HttpSession;

@Controller
public class RatingController {
    private String patientId;
    private String doctorId;
    private String labId;
    private RatingDoctorService ratingDoctorService;

    public RatingController(RatingDoctorRepositoryImpl ratingDoctorRepository) {
        this.ratingDoctorService = new RatingDoctorService(ratingDoctorRepository);
    }

    @GetMapping("/add_rating/{doctor_id}")
    public String RatingForDoctor(HttpSession session, Model model, @PathVariable("doctor_id") String doctor_id ){
        System.out.println("inside rating for doctor");
        Patient currentPatient = (Patient) session.getAttribute("CurrentPatient");
        patientId=currentPatient.getPatientId();
        doctorId=doctor_id;
        System.out.println("inside rating for doctor patientId"+patientId);
        System.out.println("inside rating for doctor doctorId"+doctorId);
        model.addAttribute("doctorRating",new RatingDoctor());
        return "Rating/rating_doctor";
    }

    @GetMapping("/add_lab_rating/{lab_id}")
    public String RatingForLab(HttpSession session, Model model, @PathVariable("lab_id") String lab_id ){
        System.out.println("inside rating for doctor");
        Patient currentPatient = (Patient) session.getAttribute("CurrentPatient");
        patientId=currentPatient.getPatientId();
        labId=lab_id;
        System.out.println("inside rating for Lab patientId"+patientId);
        System.out.println("inside rating for Lab doctorId"+doctorId);
        model.addAttribute("labRating",new RatingLab());
        return "Rating/rating_lab";
    }

    @PostMapping("/add_rating/")
    public String AddRatingForDoctor(@RequestParam("review") String review,@RequestParam("doctorRating") String doctorRating){
        System.out.println("inside add rating for doc");
        System.out.println("inside add rating for doc => patientId"+patientId);
        System.out.println("inside add rating for doc => doctorId"+doctorId);
        RatingDoctor rating = new RatingDoctor();
        rating.setRatingId(0);
        rating.setPatientId(patientId);
        rating.setDoctorId(doctorId);
        rating.setReview(review);
        rating.setDoctorRating(Integer.parseInt(doctorRating));
        boolean result = ratingDoctorService.addRating(rating);
        System.out.println("rating save result"+result);
        return "Rating/rating_successful";
    }

    @PostMapping("/add_lab_rating/")
    public String AddRatingForLab(@RequestParam("review") String review,@RequestParam("doctorRating") String doctorRating){
        System.out.println("inside add rating for doc");
        System.out.println("inside add rating for doc => patientId"+patientId);
        System.out.println("inside add rating for doc => doctorId"+doctorId);
        RatingDoctor rating = new RatingDoctor();
        rating.setRatingId(0);
        rating.setPatientId(patientId);
        rating.setDoctorId(doctorId);
        rating.setReview(review);
        rating.setDoctorRating(Integer.parseInt(doctorRating));
        boolean result = ratingDoctorService.addRating(rating);
        System.out.println("rating save result"+result);
        return "Rating/rating_successful";
    }

}
