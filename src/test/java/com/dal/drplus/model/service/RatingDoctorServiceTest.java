package com.dal.drplus.model.service;

import com.dal.drplus.model.entity.RatingDoctor;
import com.dal.drplus.model.service.RatingDoctorService;
import com.dal.drplus.repository.implementation.RatingDoctorRepositoryImpl;
import com.dal.drplus.repository.interfaces.IRatingDoctorRepository;
import org.junit.jupiter.api.BeforeAll;
import org.junit.jupiter.api.Test;
import org.mockito.Mockito;
import java.util.ArrayList;
import java.util.List;
import static org.junit.jupiter.api.Assertions.*;
import static org.junit.jupiter.api.Assertions.assertIterableEquals;

public class RatingDoctorServiceTest {

    private static RatingDoctorService ratingDoctorService;
    private static IRatingDoctorRepository ratingDoctorRepository;
    static RatingDoctor ratingDoctor1 = new RatingDoctor('1', "d1", "D1", 5, "abcd");
    static RatingDoctor ratingDoctor2 = new RatingDoctor('2', "P12", "A1", 5, "abcdef");

    @BeforeAll
    public static void init(){
        ratingDoctorRepository = Mockito.mock(RatingDoctorRepositoryImpl.class);

        Mockito.when(ratingDoctorRepository.saveDoctorRating(ratingDoctor1)).thenReturn(IRatingDoctorRepository.StorageResult.SUCCESS);
        Mockito.when(ratingDoctorRepository.saveDoctorRating(ratingDoctor2)).thenReturn(IRatingDoctorRepository.StorageResult.FAILURE);

        Mockito.when(ratingDoctorRepository.deleteDoctorRatingByDoctorId(ratingDoctor1.getDoctorId()))
                .thenReturn(IRatingDoctorRepository.StorageResult.SUCCESS);
        Mockito.when(ratingDoctorRepository.deleteDoctorRatingByDoctorId(ratingDoctor2.getDoctorId()))
                .thenReturn(IRatingDoctorRepository.StorageResult.FAILURE);

        Mockito.when(ratingDoctorRepository.deleteDoctorRatingByPatientId(ratingDoctor1.getPatientId()))
                .thenReturn(IRatingDoctorRepository.StorageResult.SUCCESS);
        Mockito.when(ratingDoctorRepository.deleteDoctorRatingByPatientId(ratingDoctor2.getPatientId()))
                .thenReturn(IRatingDoctorRepository.StorageResult.FAILURE);

        List<Integer> ratingList = new ArrayList<>();
        ratingList.add(5);
        ratingList.add(1);
        Mockito.when(ratingDoctorRepository.findDoctorRatingListByDoctorId("D1")).thenReturn(ratingList);

        ratingDoctorService = new RatingDoctorService(ratingDoctorRepository);
    }

    @Test
    void addRatingTrue(){
        boolean result = ratingDoctorService.addRating(ratingDoctor1);
        assertTrue(result);
    }

    @Test
    void addRatingFalse(){
        boolean result = ratingDoctorService.addRating(ratingDoctor2);
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
        Mockito.when(ratingDoctorRepository.findDoctorReviewsByDoctorId("d1")).thenReturn(reviewList);
        List<String> reviews_result = ratingDoctorService.getReviews("d1");
        assertIterableEquals(reviewList,reviews_result);
    }

    @Test
    void getReviewsWhenListNull(){
        Mockito.when(ratingDoctorRepository.findDoctorReviewsByDoctorId("d1")).thenReturn(null);
        List<String> reviews_result = ratingDoctorService.getReviews("d1");
        assertNull(reviews_result);
    }

    @Test
    void getReviewsWhenListEmpty(){
        List<String> reviewList = new ArrayList<>();
        Mockito.when(ratingDoctorRepository.findDoctorReviewsByDoctorId("d1")).thenReturn(reviewList);
        List<String> reviews_result = ratingDoctorService.getReviews("d1");
        assertEquals(0,reviews_result.size());
    }

    @Test
    void getRating(){
        int result = ratingDoctorService.getRating("D1");
        assertEquals(3,result);
    }

    @Test
    void isDoctorRatingexistsforDoctorTrue(){
        List<RatingDoctor> ratingDoctorList = new ArrayList<>();
        ratingDoctorList.add(ratingDoctor1);
        Mockito.when(ratingDoctorRepository.findDoctorRatingByDoctorId(ratingDoctor1.getDoctorId()))
                .thenReturn(ratingDoctorList);
        boolean result = ratingDoctorService.isDoctorRatingexistsforDoctor(ratingDoctor1.getDoctorId());
        assertTrue(result);
    }

    @Test
    void isDoctorRatingexistsforDoctorFalse(){
        List<RatingDoctor> ratingDoctorList = new ArrayList<>();
        Mockito.when(ratingDoctorRepository.findDoctorRatingByDoctorId(ratingDoctor2.getDoctorId()))
                .thenReturn(ratingDoctorList);
        boolean result = ratingDoctorService.isDoctorRatingexistsforDoctor(ratingDoctor2.getDoctorId());
        assertFalse(result);
    }

    @Test
    void deleteDoctorRatingbydoctoridTrue(){
        List<RatingDoctor> ratingDoctorList = new ArrayList<>();
        ratingDoctorList.add(ratingDoctor1);
        Mockito.when(ratingDoctorRepository.findDoctorRatingByDoctorId(ratingDoctor1.getDoctorId()))
                .thenReturn(ratingDoctorList);

        boolean result = ratingDoctorService.deleteDoctorRatingbydoctorid(ratingDoctor1.getDoctorId());
        assertTrue(result);
    }

    @Test
    void deleteDoctorRatingbydoctoridFalse(){
        List<RatingDoctor> ratingDoctorList = new ArrayList<>();
        ratingDoctorList.add(ratingDoctor2);
        Mockito.when(ratingDoctorRepository.findDoctorRatingByDoctorId(ratingDoctor2.getDoctorId()))
                .thenReturn(ratingDoctorList);

        boolean result = ratingDoctorService.deleteDoctorRatingbydoctorid(ratingDoctor2.getDoctorId());
        assertFalse(result);
    }

    @Test
    void isDoctorRatingexistsforPatientTrue(){
        List<RatingDoctor> ratingDoctorList = new ArrayList<>();
        ratingDoctorList.add(ratingDoctor1);
        Mockito.when(ratingDoctorRepository.findDoctorRatingByPatientId(ratingDoctor1.getPatientId()))
                .thenReturn(ratingDoctorList);
        boolean result = ratingDoctorService.isDoctorRatingexistsforPatient(ratingDoctor1.getPatientId());
        assertTrue(result);
    }

    @Test
    void isDoctorRatingexistsforPatientFalse(){
        List<RatingDoctor> ratingDoctorList = new ArrayList<>();
        Mockito.when(ratingDoctorRepository.findDoctorRatingByPatientId(ratingDoctor2.getPatientId()))
                .thenReturn(ratingDoctorList);
        boolean result = ratingDoctorService.isDoctorRatingexistsforPatient(ratingDoctor2.getPatientId());
        assertFalse(result);
    }

    @Test
    void deleteDoctorRatingbyPatientIdTrue(){
        List<RatingDoctor> ratingDoctorList = new ArrayList<>();
        ratingDoctorList.add(ratingDoctor1);
        Mockito.when(ratingDoctorRepository.findDoctorRatingByPatientId(ratingDoctor1.getPatientId()))
                .thenReturn(ratingDoctorList);

        boolean result = ratingDoctorService.deleteDoctorRatingbyPatientId(ratingDoctor1.getPatientId());
        assertTrue(result);
    }

    @Test
    void deleteDoctorRatingbyPatientIdFalse(){
        List<RatingDoctor> ratingDoctorList = new ArrayList<>();
        ratingDoctorList.add(ratingDoctor2);
        Mockito.when(ratingDoctorRepository.findDoctorRatingByPatientId(ratingDoctor2.getPatientId()))
                .thenReturn(ratingDoctorList);

        boolean result = ratingDoctorService.deleteDoctorRatingbyPatientId(ratingDoctor2.getPatientId());
        assertFalse(result);
    }
}
