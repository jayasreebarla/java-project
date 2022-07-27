package com.dal.drplus.model.Builder;

import com.dal.drplus.model.IBuilder.IRatingDoctorBuilder;
import com.dal.drplus.model.entity.RatingDoctor;
import com.dal.drplus.model.factory.ModelFactory;

public class RatingDoctorBuilder implements IRatingDoctorBuilder {
    private int ratingId;
    private String patientId;
    private String doctorId;
    private int doctorRating;
    private String review;

    @Override
    public IRatingDoctorBuilder addRatingId(int ratingId) {
        this.ratingId=ratingId;
        return this;
    }

    @Override
    public IRatingDoctorBuilder addPatientId(String patientId) {
        this.patientId=patientId;
        return this;
    }

    @Override
    public IRatingDoctorBuilder addDoctorId(String doctorId) {
        this.doctorId=doctorId;
        return this;
    }

    @Override
    public IRatingDoctorBuilder addDoctorRating(int doctorRating) {
        this.doctorRating=doctorRating;
        return this;
    }

    @Override
    public IRatingDoctorBuilder addReview(String review) {
        this.review=review;
        return this;
    }

    @Override
    public RatingDoctor build() {
        RatingDoctor ratingDoctor = ModelFactory.instance().createRatingDoctorUsingBuilder(this);
        return ratingDoctor;
    }

    @Override
    public int getRatingId() {
        return ratingId;
    }

    @Override
    public String getPatientId() {
        return patientId;
    }

    @Override
    public String getDoctorId() {
        return doctorId;
    }

    @Override
    public int getDoctorRating() {
        return doctorRating;
    }

    @Override
    public String getReview() {
        return review;
    }
}
