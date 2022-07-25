package com.dal.drplus.model.Builder;

import com.dal.drplus.model.entity.Doctor;
import com.dal.drplus.model.entity.RatingLab;
import com.dal.drplus.model.factory.ModelFactory;

public class RatingLabBuilder {
    private int ratingId;
    private String patientId;
    private String labId;
    private int labRating;
    private String review;

    public RatingLabBuilder addReview(String review) {
        this.review = review;
        return this;
    }

    public RatingLabBuilder addRatingId(int ratingId) {
        this.ratingId = ratingId;
        return this;
    }

    public RatingLabBuilder addPatientId(String patientId) {
        this.patientId = patientId;
        return this;
    }

    public RatingLabBuilder addLabId(String labId) {
        this.labId = labId;
        return this;
    }

    public RatingLabBuilder addLabRating(int labRating) {
        this.labRating = labRating;
        return this;
    }

    public RatingLab build() {
        RatingLab ratingLab = (RatingLab) ModelFactory.instance().createRatingLabBuilder(this);
        return ratingLab;
    }

    public String getReview() {
        return review;
    }

    public int getRatingId() {
        return ratingId;
    }

    public String getPatientId() {
        return patientId;
    }

    public String getLabId() {
        return labId;
    }

    public int getLabRating() {
        return labRating;
    }
}
