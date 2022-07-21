package com.dal.drplus.model;

public class RatingLab{
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



    public String getReview() {
        return review;
    }

    public void setReview(String review) {
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

    public String getLabId() {
        return labId;
    }

    public void setLabId(String labId) {
        this.labId = labId;
    }

    public int getLabRating() {
        return labRating;
    }

    public void setLabRating(int labRating) {
        this.labRating = labRating;
    }
}
