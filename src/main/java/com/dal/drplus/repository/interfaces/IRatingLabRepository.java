package com.dal.drplus.repository.interfaces;

import com.dal.drplus.model.RatingDoctor;
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
    List<String> findLabReviewsByLabId(String labId);
    List<Integer> findLabRatingListByLabId(String labId);
    StorageResult deleteLabRatingById(int ratingId,String labId);
    List<RatingLab> findAll();
    int deleteAll();
}
