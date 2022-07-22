package com.dal.drplus.service;

import com.dal.drplus.model.Promotions;
import com.dal.drplus.repository.implementation.PromotionsRepositoryImpl;
import com.dal.drplus.repository.interfaces.IPromotionsRepository;
import org.junit.jupiter.api.BeforeAll;
import org.junit.jupiter.api.Test;
import org.mockito.Mockito;

import static org.junit.jupiter.api.Assertions.*;
public class PromotionsServiceTest {

    static Promotions promotion1;
    static Promotions promotion2;
    static String promoCode1 = "1";
    static String promoCode2 = "2";
    private static PromotionsService promotionsService;
    private static IPromotionsRepository promotionsRepository;

    @BeforeAll
    public static void init(){
        promotionsRepository = Mockito.mock(PromotionsRepositoryImpl.class);
        promotionsService = new PromotionsService(promotionsRepository);

        promotion1 = new Promotions();
        promotion1.setPromotionId(promoCode1);
        promotion1.setPromotionName("2% off");
        promotion1.setPromotionStartDate("2022-07-20");
        promotion1.setPromotionEndDate("2022-07-30");
        promotion1.setAmountOff(2);

        promotion2 = new Promotions();
        promotion2.setPromotionId(promoCode2);
        promotion2.setPromotionName("3% off");
        promotion2.setPromotionStartDate("2022-11-23");
        promotion2.setPromotionEndDate("2022-11-27");
        promotion2.setAmountOff(3);

        Mockito.when(promotionsRepository.findById(promoCode1)).thenReturn(promotion1);
        Mockito.when(promotionsRepository.findById(promoCode2)).thenReturn(promotion2);
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
}
