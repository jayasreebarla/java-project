package com.dal.drplus.model;

public class RatingLab{
    private int ratingId;
    private int patientId;
    private int labId;
    private int labRating;

    public int getRatingId() {
        return ratingId;
    }

    public void setRatingId(int ratingId) {
        this.ratingId = ratingId;
    }

    public int getPatientId() {
        return patientId;
    }

    public void setPatientId(int patientId) {
        this.patientId = patientId;
    }

    public int getLabId() {
        return labId;
    }

    public void setLabId(int labId) {
        this.labId = labId;
    }

    public int getLabRating() {
        return labRating;
    }

    public void setLabRating(int labRating) {
        this.labRating = labRating;
    }
}
