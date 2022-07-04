package com.dal.drplus.repository.interfaces;

import com.dal.drplus.model.RatingDoctor;

import java.util.List;

public interface IRatingDoctorRepository {
    enum StorageResult{
        SUCCESS,
        FAILURE
    }

    StorageResult saveDoctorRating(RatingDoctor ratingDoctor);
    StorageResult updateDoctorRating(RatingDoctor ratingDoctor);
    StorageResult updateDoctorReview(RatingDoctor ratingDoctor);
    RatingDoctor findDoctorRatingById(int ratingId,String doctorId);
    List<RatingDoctor> findDoctorRatingByDoctorId(String doctorId);
    List<String> findDoctorReviewsByDoctorId(String doctorId);
    List<Integer> findDoctorRatingListByDoctorId(String doctorId);
    StorageResult deleteDoctorRatingById(int ratingId,String doctorId);
    List<RatingDoctor> findAll();
    int deleteAll();
}
