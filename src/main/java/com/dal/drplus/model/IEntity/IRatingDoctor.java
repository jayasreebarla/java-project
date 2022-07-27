package com.dal.drplus.model.IEntity;

public abstract class IRatingDoctor {
    protected int ratingId;
    protected String patientId;
    protected String doctorId;
    protected int doctorRating;
    protected String review;

    public IRatingDoctor() {
    }

    public IRatingDoctor(int ratingId, String patientId, String doctorId, int doctorRating, String review) {
        this.ratingId = ratingId;
        this.patientId = patientId;
        this.doctorId = doctorId;
        this.doctorRating = doctorRating;
        this.review = review;
    }

    abstract public boolean validateDoctorRating();

    abstract public int getRatingId();

    abstract public void setRatingId(int ratingId);

    abstract public String getPatientId();

    abstract public void setPatientId(String patientId);

    abstract public String getDoctorId();

    abstract public void setDoctorId(String doctorId);

    abstract public int getDoctorRating();

    abstract public void setDoctorRating(int doctorRating);

    abstract public String getReview();

    abstract public void setReview(String review);
}
