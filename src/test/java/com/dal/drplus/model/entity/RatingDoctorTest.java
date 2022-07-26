package com.dal.drplus.model.entity;

import com.dal.drplus.model.factory.ModelFactory;
import org.junit.jupiter.api.BeforeAll;
import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.assertFalse;
import static org.junit.jupiter.api.Assertions.assertTrue;

public class RatingDoctorTest {

    private static RatingDoctor ratingDoctor1 = (RatingDoctor) ModelFactory.instance().createRatingDoctor();
    private static RatingDoctor ratingDoctor2 = (RatingDoctor) ModelFactory.instance().createRatingDoctor();

    @BeforeAll
    public static void init(){
        ratingDoctor1.setRatingId(1);
        ratingDoctor1.setDoctorId("d1");
        ratingDoctor1.setPatientId("p1");
        ratingDoctor1.setDoctorRating(4);
        ratingDoctor1.setReview("nice");

        ratingDoctor2.setRatingId(2);
        ratingDoctor2.setDoctorId("d2");
        ratingDoctor2.setPatientId("p2");
        ratingDoctor2.setDoctorRating(0);
        ratingDoctor2.setReview("nice");
    }

    @Test
    public void validateDoctorRatingTrue(){
        boolean result = ratingDoctor1.validateDoctorRating();
        assertTrue(result);
    }

    @Test
    public void validateDoctorRatingFalse(){
        boolean result = ratingDoctor2.validateDoctorRating();
        assertFalse(result);
    }
}
