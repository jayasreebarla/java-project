package com.dal.drplus.model.entity;

import com.dal.drplus.model.IBuilder.IRatingLabBuilder;
import com.dal.drplus.model.IEntity.IRatingLab;

public class RatingLab extends IRatingLab {
    private int ratingId;
    private String patientId;
    private String labId;
    private int labRating;
    private String review;
    public RatingLab() {
    }

    public RatingLab(int ratingId, String patientId, String labId, int labRating, String review) {
        this.ratingId = ratingId;
        this.patientId = patientId;
        this.labId = labId;
        this.labRating = labRating;
        this.review = review;
    }

    public RatingLab(IRatingLabBuilder builder){
        this.ratingId = builder.getRatingId();
        this.patientId = builder.getPatientId();
        this.labId = builder.getLabId();
        this.labRating = builder.getLabRating();
        this.review = builder.getReview();
    }
    @Override
    public boolean validateLabRatingFormat(int labRating) {
        return labRating >= 1 && labRating <= 5;
    }
    public String getReview() {
        return this.review;
    }

    public void setReview(String review) {
        this.review = review;
    }

    public int getRatingId() {
        return this.ratingId;
    }

    public void setRatingId(int ratingId) {
        this.ratingId = ratingId;
    }

    public String getPatientId() {
        return this.patientId;
    }

    public void setPatientId(String patientId) {
        this.patientId = patientId;
    }

    public String getLabId() {
        return this.labId;
    }

    public void setLabId(String labId) {
        this.labId = labId;
    }

    public int getLabRating() {
        return this.labRating;
    }

    public void setLabRating(int labRating) {
        this.labRating = labRating;
    }
}
