package com.dal.drplus.model.IBuilder;

import com.dal.drplus.model.entity.RatingDoctor;

public interface IRatingDoctorBuilder {
    IRatingDoctorBuilder addRatingId(int ratingId);
    IRatingDoctorBuilder addPatientId(String patientId);
    IRatingDoctorBuilder addDoctorId(String doctorId);
    IRatingDoctorBuilder addDoctorRating(int doctorRating);
    IRatingDoctorBuilder addReview(String review);
    RatingDoctor build();
    int getRatingId();
    String getPatientId();
    String getDoctorId();
    int getDoctorRating();
    String getReview();
}
