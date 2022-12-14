package com.dal.drplus.controller;

import com.dal.drplus.model.IBuilder.IRatingDoctorBuilder;
import com.dal.drplus.model.IEntity.IRatingDoctor;
import com.dal.drplus.model.IBuilder.IRatingLabBuilder;
import com.dal.drplus.model.IEntity.IRatingLab;
import com.dal.drplus.model.entity.Patient;
import com.dal.drplus.model.entity.RatingDoctor;
import com.dal.drplus.model.entity.RatingLab;
import com.dal.drplus.model.factory.ModelFactory;
import com.dal.drplus.repository.implementation.RatingDoctorRepositoryImpl;
import com.dal.drplus.repository.implementation.RatingLabRepositoryImpl;
import com.dal.drplus.model.service.RatingDoctorService;
import com.dal.drplus.model.service.RatingLabService;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.servlet.view.RedirectView;
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
        Patient currentPatient = (Patient) session.getAttribute("CurrentPatient");
        patientId=currentPatient.getPatientId();
        doctorId=doctor_id;
        if(ratingDoctorService.checkPreviousDoctorRatingNotExistsForPatientID(doctorId, patientId) == true) {
            model.addAttribute("doctorRating", (RatingDoctor)ModelFactory.instance().createRatingDoctor());
            return "Rating/rating_doctor";
        }
        else{
            return "Rating/rating_already_exists";
        }
    }

    @GetMapping("/add_lab_rating/{lab_id}")
    public String RatingForLab(HttpSession session, Model model, @PathVariable("lab_id") String lab_id ){
        Patient currentPatient = (Patient) session.getAttribute("CurrentPatient");
        patientId=currentPatient.getPatientId();
        labId=lab_id;
        if(ratingLabService.checkPreviousLabRatingNotExistsForPatientID(labId, patientId) == true) {
            model.addAttribute("labRating", (RatingLab) ModelFactory.instance().createRatingLab());
            return "Rating/rating_lab";
        }
        else{
            return "Rating/rating_already_exists";
        }
    }

    @PostMapping("/add_rating/")
    public RedirectView AddRatingForDoctor(@RequestParam("review") String review, @RequestParam("doctorRating") String doctorRating){
        IRatingDoctorBuilder builder = ModelFactory.instance().createRatingDoctorBuilder();
        IRatingDoctor rating = builder
                                .addPatientId(patientId)
                                .addDoctorId(doctorId)
                                .addReview(review)
                                .addDoctorRating(Integer.parseInt(doctorRating)).build();
        if(rating.validateDoctorRating()){
            boolean result = ratingDoctorService.addRating(rating);
            return new RedirectView("/rating_success");
        }else{
            return new RedirectView("/add_rating/"+doctorId);
        }

    }

    @PostMapping("/add_lab_rating/")
    public RedirectView AddRatingForLab(@RequestParam("review") String review, @RequestParam("labRating") String labRating){
        IRatingLabBuilder builder = ModelFactory.instance().createRatingLabBuilder();
        IRatingLab rating =  builder
                        .addPatientId(patientId)
                        .addLabId(labId)
                        .addReview(review)
                        .addLabRating(Integer.parseInt(labRating)).build();
        if(rating.validateLabRatingFormat(rating.getLabRating())){
            boolean result = ratingLabService.addRating(rating);
            return new RedirectView("/rating_success");
        }
        return new RedirectView("/add_rating/"+labId);
    }

    @GetMapping("/rating_success")
    public String returnRatingSuccessPage(){
        return "Rating/rating_successful";
    }
}
