package com.dal.drplus.repository.interfaces;

import com.dal.drplus.model.RatingDoctor;

import java.util.List;

public interface IRatingDoctorRepository {
    int saveDoctorRating(RatingDoctor ratingDoctor);
    int updateDoctorRating(RatingDoctor ratingDoctor);
    RatingDoctor findDoctorRatingById(String ratingId,String doctorId);
    List<RatingDoctor> findDoctorRatingByDoctorId(String doctorId);
    int deleteDoctorRatingById(String ratingId,String doctorId);
    List<RatingDoctor> findAll();
    int deleteAll();
}
