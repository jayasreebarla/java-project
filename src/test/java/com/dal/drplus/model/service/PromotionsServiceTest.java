package com.dal.drplus.model.service;

import com.dal.drplus.model.entity.Promotions;
import com.dal.drplus.model.service.PromotionsService;
import com.dal.drplus.repository.implementation.PromotionsRepositoryImpl;
import com.dal.drplus.repository.interfaces.IPromotionsRepository;
import org.junit.jupiter.api.BeforeAll;
import org.junit.jupiter.api.Test;
import org.mockito.Mockito;
import java.util.ArrayList;
import java.util.List;
import static org.junit.jupiter.api.Assertions.*;

public class PromotionsServiceTest {

    static Promotions promotion1;
    static Promotions promotion2;
    static String promoCode1 = "1";
    static String promoCode2 = "2";
    private static PromotionsService promotionsService;
    private static IPromotionsRepository promotionsRepository;
    static String promotionId3 = "3";

    @BeforeAll
    public static void init(){
        promotionsRepository = Mockito.mock(PromotionsRepositoryImpl.class);
        promotionsService = new PromotionsService(promotionsRepository);

        promotion1 = new Promotions();
        promotion1.setPromotionId("1");
        promotion1.setPromotionName("2% off");
        promotion1.setPromotionStartDate("2022-04-11");
        promotion1.setPromotionEndDate("2023-07-30");
        promotion1.setAmountOff(2);

        promotion2 = new Promotions();
        promotion2.setPromotionId("2");
        promotion2.setPromotionName("3% off");
        promotion2.setPromotionStartDate("2022-11-23");
        promotion2.setPromotionEndDate("2022-11-27");
        promotion2.setAmountOff(3);

        String promotionId1 = promotion1.getPromotionId();
        String promotionId2 = promotion2.getPromotionId();

        Mockito.when(promotionsRepository.savePromotions(promotion1)).thenReturn(IPromotionsRepository.StorageResult.SUCCESS);
        Mockito.when(promotionsRepository.savePromotions(promotion2)).thenReturn(IPromotionsRepository.StorageResult.FAILURE);
        Mockito.when(promotionsRepository.deleteById(promotionId1)).thenReturn(IPromotionsRepository.StorageResult.SUCCESS);
        Mockito.when(promotionsRepository.deleteById(promotionId2)).thenReturn(IPromotionsRepository.StorageResult.FAILURE);
        Mockito.when(promotionsRepository.findById(promotionId1)).thenReturn(promotion1);
        Mockito.when(promotionsRepository.findById(promotionId2)).thenReturn(promotion2);
        Mockito.when(promotionsRepository.findById(promotionId3)).thenReturn(null);
    }

    @Test
    void validatePromotionAndGetDiscountAmountWhenDiscountIsNot0(){
        int result = promotionsService.validatePromotionAndGetDiscountAmount(promoCode1);
        assertEquals(2,result);
    }

    @Test
    void validatePromotionAndGetDiscountAmountWhenDiscountIs0(){
        int result = promotionsService.validatePromotionAndGetDiscountAmount(promoCode2);
        assertEquals(0,result);
    }

    @Test
    public void findAll(){
        List<Promotions> promotions = new ArrayList<>();
        promotions.add(promotion1);
        promotions.add(promotion2);
        Mockito.when(promotionsRepository.findAll()).thenReturn(promotions);
        List<Promotions> promotion_result = promotionsService.listAllPromotions();
        List<Promotions> expectedPromotions = new ArrayList<>();
        expectedPromotions.add(promotion1);
        expectedPromotions.add(promotion2);
        assertIterableEquals(expectedPromotions,promotion_result);
    }

    @Test
    public void findAllIsNull(){
        Mockito.when(promotionsRepository.findAll()).thenReturn(null);
        List<Promotions> promotion_result = promotionsService.listAllPromotions();
        assertIterableEquals(null,promotion_result);
    }

    @Test
    public void listAllLabsWhenListIsEmpty(){
        List<Promotions> promotion_result = new ArrayList<>();
        Mockito.when(promotionsRepository.findAll()).thenReturn(promotion_result);
        assertEquals(0,promotion_result.size());
    }

    @Test
    public void findByIdPass(){
        Promotions promotion_result =  promotionsService.findById(promotion1.getPromotionId());
        assertEquals(promotion1.getPromotionId(),promotion_result.getPromotionId());
    }

    @Test
    public void findByIdFailNull(){
        Promotions promotion_result =  promotionsService.findById(promotionId3);
        //assertEquals(promotion2.getPromotionId(),promotion_result.getPromotionId());
        assertNull(promotion_result);
    }

    @Test
    public void deleteByIdPass(){
        boolean result = promotionsService.deletePromotionsbyId(promotion1.getPromotionId());
        assertTrue(result);
    }

    @Test
    public void deleteByIdFail() {
        boolean result = promotionsService.deletePromotionsbyId(promotion2.getPromotionId());
        assertFalse(result);
    }
}
