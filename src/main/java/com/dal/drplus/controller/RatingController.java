package com.dal.drplus.controller;

import com.dal.drplus.model.Patient;
import com.dal.drplus.model.RatingDoctor;
import com.dal.drplus.model.RatingLab;
import com.dal.drplus.repository.implementation.RatingDoctorRepositoryImpl;
import com.dal.drplus.repository.implementation.RatingLabRepositoryImpl;
import com.dal.drplus.service.RatingDoctorService;
import com.dal.drplus.service.RatingLabService;
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

    private RatingLabService ratingLabService;

    public RatingController(RatingDoctorRepositoryImpl ratingDoctorRepository,RatingLabRepositoryImpl ratingLabRepository) {
        this.ratingDoctorService = new RatingDoctorService(ratingDoctorRepository);
        this.ratingLabService = new RatingLabService(ratingLabRepository);
    }

    @GetMapping("/add_rating/{doctor_id}")
    public String RatingForDoctor(HttpSession session, Model model, @PathVariable("doctor_id") String doctor_id ){
        System.out.println("inside rating for doctor");
        Patient currentPatient = (Patient) session.getAttribute("CurrentPatient");
        patientId=currentPatient.getPatientId();
        doctorId=doctor_id;
        System.out.println("inside rating for doctor patientId"+patientId);
        System.out.println("inside rating for doctor doctorId"+doctorId);

        if(ratingDoctorService.checkPreviousDoctorRatingNotExistsForPatientID(doctorId, patientId) == true) {
            model.addAttribute("doctorRating",new RatingDoctor());
            return "Rating/rating_doctor";
        }
        else{
            return "Rating/rating_already_exists";
        }
    }

    @GetMapping("/add_lab_rating/{lab_id}")
    public String RatingForLab(HttpSession session, Model model, @PathVariable("lab_id") String lab_id ){
        System.out.println("inside rating for doctor");
        Patient currentPatient = (Patient) session.getAttribute("CurrentPatient");
        patientId=currentPatient.getPatientId();
        labId=lab_id;
        System.out.println("inside rating for Lab patientId"+patientId);

        if(ratingLabService.checkPreviousLabRatingNotExistsForPatientID(labId, patientId) == true) {
            model.addAttribute("labRating", new RatingLab());
            return "Rating/rating_lab";
        }
        else{
            return "Rating/rating_already_exists";
        }
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
        System.out.println("rating save result" + result);
        return "Rating/rating_successful";
    }

    @PostMapping("/add_lab_rating/")
    public String AddRatingForLab(@RequestParam("review") String review,@RequestParam("labRating") String labRating){
        System.out.println("inside add rating for lab");
        System.out.println("inside add rating for lab => patientId"+patientId);
        System.out.println("inside add rating for lab => LabId"+labId);
        RatingLab rating = new RatingLab();
        rating.setRatingId(0);
        rating.setPatientId(patientId);
        rating.setLabId(labId);
        rating.setReview(review);
        rating.setLabRating(Integer.parseInt(labRating));
        boolean result = ratingLabService.addRating(rating);
        System.out.println("rating save result"+result);
        return "Rating/rating_successful";
    }

}
