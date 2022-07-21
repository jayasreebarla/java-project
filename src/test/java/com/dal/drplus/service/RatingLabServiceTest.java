package com.dal.drplus.service;

import com.dal.drplus.model.RatingLab;
import com.dal.drplus.repository.implementation.RatingLabRepositoryImpl;
import com.dal.drplus.repository.interfaces.IRatingLabRepository;
import org.junit.jupiter.api.BeforeAll;
import org.junit.jupiter.api.Test;
import org.mockito.Mock;
import org.mockito.Mockito;

import java.util.ArrayList;
import java.util.List;

import static org.junit.jupiter.api.Assertions.*;
import static org.junit.jupiter.api.Assertions.assertIterableEquals;

public class RatingLabServiceTest {

    private static RatingLabService ratingLabService;
    private static IRatingLabRepository ratingLabRepository;
    static RatingLab ratingLab1 = new RatingLab(1, "l1", "l1", 5, "abcd");
    static RatingLab ratingLab2 = new RatingLab(2, "P12", "A1", 5, "abcdef");


    @BeforeAll
    public static void init(){
        ratingLabRepository = Mockito.mock(RatingLabRepositoryImpl.class);

        Mockito.when(ratingLabRepository.saveLabRating(ratingLab1)).thenReturn(IRatingLabRepository.StorageResult.SUCCESS);
        Mockito.when(ratingLabRepository.saveLabRating(ratingLab2)).thenReturn(IRatingLabRepository.StorageResult.FAILURE);

        List<Integer> ratingList = new ArrayList<>();
        ratingList.add(5);
        ratingList.add(1);
        Mockito.when(ratingLabRepository.findLabRatingListByLabId("l1")).thenReturn(ratingList);

        ratingLabService = new RatingLabService(ratingLabRepository);
    }

    @Test
    void addRatingTrue(){
        boolean result = ratingLabService.addRating(ratingLab1);
        assertTrue(result);
    }

    @Test
    void addRatingFalse(){
        boolean result = ratingLabService.addRating(ratingLab2);
        assertFalse(result);
    }

    @Test
    void getReviewsWhenListExists(){
        String review1 = "abcd";
        String review2 = "efgh";
        String review3 = "hijk";
        List<String> reviewList = new ArrayList<>();
        reviewList.add(review1);
        reviewList.add(review2);
        reviewList.add(review3);
        Mockito.when(ratingLabRepository.findLabReviewsByLabId("l1")).thenReturn(reviewList);
        List<String> reviews_result = ratingLabService.getReviews("l1");
        assertIterableEquals(reviewList,reviews_result);
    }

    @Test
    void getReviewsWhenListNull(){
        Mockito.when(ratingLabRepository.findLabReviewsByLabId("l1")).thenReturn(null);
        List<String> reviews_result = ratingLabService.getReviews("l1");
        assertNull(reviews_result);
    }

    @Test
    void getReviewsWhenListEmpty(){
        List<String> reviewList = new ArrayList<>();
        Mockito.when(ratingLabRepository.findLabReviewsByLabId("l1")).thenReturn(reviewList);
        List<String> reviews_result = ratingLabService.getReviews("l1");
        assertEquals(0,reviews_result.size());
    }

    @Test
    void getRating(){
        int result = ratingLabService.getRating("l1");
        assertEquals(3,result);
    }

}
