package com.dal.drplus.repository.interfaces;

import com.dal.drplus.model.RatingLab;

import java.util.List;

public interface IRatingLabRepository {
    enum StorageResult{
        SUCCESS,
        FAILURE
    }
    StorageResult saveLabRating(RatingLab ratingLab);
    StorageResult updateLabRating(RatingLab ratingLab);
    StorageResult updateLabReview(RatingLab ratingLab);
    RatingLab findLabRatingById(int ratingId,String labId);
    List<RatingLab> findLabRatingByLabId(String labId);
    List<RatingLab> findLabRatingByPatientId(String patientId);
    List<String> findLabReviewsByLabId(String labId);
    List<Integer> findLabRatingListByLabId(String labId);
    StorageResult deleteLabRatingByLabId(String labId);
    StorageResult deleteLabRatingByPatientId(String patientId);
    List<RatingLab> findAll();
    int deleteAll();
}
