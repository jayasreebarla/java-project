package com.dal.drplus.repository.interfaces;

import com.dal.drplus.model.IEntity.IRatingLab;
import com.dal.drplus.model.entity.RatingLab;

import java.util.List;

public interface IRatingLabRepository {
    enum StorageResult{
        SUCCESS,
        FAILURE
    }
    StorageResult saveLabRating(IRatingLab ratingLab);
    StorageResult updateLabRating(RatingLab ratingLab);
    StorageResult updateLabReview(RatingLab ratingLab);
    IRatingLab findLabRatingById(int ratingId, String labId);
    boolean findLabRatingByLabIdAndPatientID(String labId, String patientId);
    List<RatingLab> findLabRatingByLabId(String labId);
    List<RatingLab> findLabRatingByPatientId(String patientId);
    List<String> findLabReviewsByLabId(String labId);
    List<Integer> findLabRatingListByLabId(String labId);
    StorageResult deleteLabRatingByLabId(String labId);
    StorageResult deleteLabRatingByPatientId(String patientId);
    List<RatingLab> findAll();
    int deleteAll();
}
