package com.dal.drplus.model.entity;

import com.dal.drplus.model.IBuilder.IRatingDoctorBuilder;
import com.dal.drplus.model.IEntity.IRatingDoctor;

public class RatingDoctor extends IRatingDoctor {
    private int ratingId;
    private String patientId;
    private String doctorId;
    private int doctorRating;

    private String review;

    public RatingDoctor() {
    }

    public RatingDoctor(IRatingDoctorBuilder builder) {
    }

    public RatingDoctor(int ratingId, String patientId, String doctorId, int doctorRating, String review) {
        this.ratingId = ratingId;
        this.patientId = patientId;
        this.doctorId = doctorId;
        this.doctorRating = doctorRating;
        this.review = review;
    }

    public int getRatingId() {
        return ratingId;
    }

    public void setRatingId(int ratingId) {
        this.ratingId = ratingId;
    }

    public String getPatientId() {
        return patientId;
    }

    public void setPatientId(String patientId) {
        this.patientId = patientId;
    }

    public String getDoctorId() {
        return doctorId;
    }

    public void setDoctorId(String doctorId) {
        this.doctorId = doctorId;
    }

    public int getDoctorRating() {
        return doctorRating;
    }

    public void setDoctorRating(int doctorRating) {
        this.doctorRating = doctorRating;
    }

    public String getReview() {
        return review;
    }

    public void setReview(String review) {
        this.review = review;
    }
}
