package com.dal.drplus.model.IEntity;

public abstract class IRatingLab {
    protected int ratingId;
    protected String patientId;
    protected String labId;
    protected int labRating;
    protected String review;

    public IRatingLab() {
    }

    public IRatingLab(int ratingId, String patientId, String labId, int labRating, String review) {
        this.ratingId = ratingId;
        this.patientId = patientId;
        this.labId = labId;
        this.labRating = labRating;
        this.review = review;
    }



    abstract public String getReview();

    abstract public void setReview(String review);

    abstract public int getRatingId();

    abstract public void setRatingId(int ratingId);

    abstract public String getPatientId();

    abstract public void setPatientId(String patientId);

    abstract public String getLabId();

    abstract public void setLabId(String labId);

    abstract public int getLabRating();

    abstract public void setLabRating(int labRating);
}
