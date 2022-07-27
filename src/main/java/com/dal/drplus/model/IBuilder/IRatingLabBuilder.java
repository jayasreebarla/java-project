package com.dal.drplus.model.IBuilder;

import com.dal.drplus.model.Builder.RatingLabBuilder;
import com.dal.drplus.model.entity.RatingLab;
import com.dal.drplus.model.factory.ModelFactory;

public interface IRatingLabBuilder {

    IRatingLabBuilder addReview(String review);

    IRatingLabBuilder addRatingId(int ratingId);

    IRatingLabBuilder addPatientId(String patientId);

    IRatingLabBuilder addLabId(String labId);

    IRatingLabBuilder addLabRating(int labRating);

    public RatingLab build();

    public String getReview();

    public int getRatingId();

    public String getPatientId();

    public String getLabId();

    public int getLabRating();

}
