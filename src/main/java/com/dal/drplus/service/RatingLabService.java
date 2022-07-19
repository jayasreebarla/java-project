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

}
