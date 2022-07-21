package com.dal.drplus.service;

import com.dal.drplus.model.RatingLab;
import com.dal.drplus.repository.interfaces.IRatingLabRepository;

import java.util.List;

public class RatingLabService {
    private IRatingLabRepository ratingLabRepository;

    public RatingLabService(IRatingLabRepository ratingLabRepository) {
        this.ratingLabRepository = ratingLabRepository;
    }

    public boolean addRating(RatingLab ratingLab){
        IRatingLabRepository.StorageResult result = ratingLabRepository.saveLabRating(ratingLab);
        System.out.println("Inside rating Service"+result);
        if(result.equals(IRatingLabRepository.StorageResult.SUCCESS)){
            return true;
        }else{
            return false;
        }
    }

    public boolean checkPreviousLabRatingNotExistsForPatientID(String labId, String patientId){
        System.out.println(ratingLabRepository.findLabRatingByLabIdAndPatientID(labId, patientId));
        if(ratingLabRepository.findLabRatingByLabIdAndPatientID(labId, patientId)){
            return false;
        }
        return true;
    }

    public List<String> getReviews(String labId){
        return ratingLabRepository.findLabReviewsByLabId(labId);
    }

    public int getRating(String labId){
        List<Integer> ratings = ratingLabRepository.findLabRatingListByLabId(labId);
        int total_sum=0;
        for (Integer rating:ratings) {
            total_sum+=rating;
        }

        int average_rating = total_sum/ratings.size();
        return average_rating;
    }

    public boolean isLabRatingexistsforLab(String labId){
        List<RatingLab> ratingLabList = ratingLabRepository.findLabRatingByLabId(labId);
        if(ratingLabList.isEmpty()){
            return false;
        } else {
            return true;
        }
    }

    public boolean deleteLabRatingbyLabId(String labId){
        if(isLabRatingexistsforLab(labId)){
            IRatingLabRepository.StorageResult result = ratingLabRepository.deleteLabRatingByLabId(labId);
            if(IRatingLabRepository.StorageResult.SUCCESS.equals(result)){
                return true;
            } else {
                return false;
            }
        }
        return true;
    }

    public boolean isLabRatingexistsforPatient(String patientId){
        List<RatingLab> ratingLabList = ratingLabRepository.findLabRatingByPatientId(patientId);
        if(ratingLabList.isEmpty()){
            return false;
        } else {
            return true;
        }
    }

    public boolean deleteLabRatingbyPatientId(String patientId){
        if(isLabRatingexistsforPatient(patientId)){
            IRatingLabRepository.StorageResult result = ratingLabRepository.deleteLabRatingByLabId(patientId);
            if(IRatingLabRepository.StorageResult.SUCCESS.equals(result)){
                return true;
            } else {
                return false;
            }
        }
        return true;
    }

}
