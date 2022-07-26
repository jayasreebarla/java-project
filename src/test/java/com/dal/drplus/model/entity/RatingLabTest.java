package com.dal.drplus.model.entity;

import com.dal.drplus.model.factory.ModelFactory;
import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.assertFalse;
import static org.junit.jupiter.api.Assertions.assertTrue;

public class RatingLabTest {
    private static RatingLab ratingLab = (RatingLab) ModelFactory.instance().createRatingLab();

    private static int rating1 = 4;
    private static int rating2 = 10;

    @Test
    void validateLabRatingTrue(){
        boolean result = ratingLab.validateLabRatingFormat(rating1);
        assertTrue(result);
    }

    @Test
    void validateLabRatingFalse(){
        boolean result = ratingLab.validateLabRatingFormat(rating2);
        assertFalse(result);
    }

}
