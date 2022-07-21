package com.dal.drplus.service;

import com.dal.drplus.model.RatingDoctor;
import com.dal.drplus.repository.interfaces.IRatingDoctorRepository;

import java.util.List;

public class RatingDoctorService {

    private IRatingDoctorRepository ratingDoctorRepository;


    public RatingDoctorService(IRatingDoctorRepository ratingDoctorRepository) {
        this.ratingDoctorRepository = ratingDoctorRepository;
    }

    public boolean addRating(RatingDoctor ratingDoctor){
        IRatingDoctorRepository.StorageResult result = ratingDoctorRepository.saveDoctorRating(ratingDoctor);
        System.out.println("Inside rating Service"+result);
        if(result.equals(IRatingDoctorRepository.StorageResult.SUCCESS)){
            return true;
        }else{
            return false;
        }
    }

    public boolean checkPreviousDoctorRatingNotExistsForPatientID(String doctorId, String patientId){
        if(ratingDoctorRepository.findDoctorRatingByDoctorIdAndPatientID(doctorId, patientId)){
            return false;
        }
        return true;
    }

    public List<String> getReviews(String doctorId){
        return ratingDoctorRepository.findDoctorReviewsByDoctorId(doctorId);
    }

    public int getRating(String doctorId){
        List<Integer> ratings = ratingDoctorRepository.findDoctorRatingListByDoctorId(doctorId);
        int total_sum=0;
        for (Integer rating:ratings) {
            total_sum+=rating;
        }

        int average_rating = total_sum/ratings.size();
        return average_rating;
    }

    public boolean isDoctorRatingexistsforDoctor(String doctorId){
        List<RatingDoctor> ratingDoctorList = ratingDoctorRepository.findDoctorRatingByDoctorId(doctorId);
        if(ratingDoctorList.isEmpty()){
            return false;
        } else {
            return true;
        }
    }

    public boolean deleteDoctorRatingbydoctorid(String doctorId){
        if(isDoctorRatingexistsforDoctor(doctorId)){
            IRatingDoctorRepository.StorageResult result = ratingDoctorRepository.deleteDoctorRatingByDoctorId(doctorId);
            if(IRatingDoctorRepository.StorageResult.SUCCESS.equals(result)){
                return true;
            }else{
                return false;
            }
        }
        return true;
    }

    public boolean isDoctorRatingexistsforPatient(String patientId){
        List<RatingDoctor> ratingDoctorList = ratingDoctorRepository.findDoctorRatingByPatientId(patientId);
        if(ratingDoctorList.isEmpty()){
            return false;
        } else {
            return true;
        }
    }

    public boolean deleteDoctorRatingbyPatientId(String patientId){
       if(isDoctorRatingexistsforPatient(patientId)) {
           IRatingDoctorRepository.StorageResult result = ratingDoctorRepository.deleteDoctorRatingByPatientId(patientId);
           if (IRatingDoctorRepository.StorageResult.SUCCESS.equals(result)) {
               return true;
           } else {
               return false;
           }
       }
       return true;
    }

}
