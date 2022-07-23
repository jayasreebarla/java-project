package com.dal.drplus.service;
import com.dal.drplus.model.Promotions;

import com.dal.drplus.repository.interfaces.IPromotionsRepository;

import java.time.LocalDate;
import java.util.List;


public class PromotionsService {
   IPromotionsRepository promotionsRepository;

   public PromotionsService(IPromotionsRepository promotionsRepository) {
      this.promotionsRepository = promotionsRepository;
   }

   public boolean addPromotions(Promotions promotions) {
      IPromotionsRepository.StorageResult result = promotionsRepository.savePromotions(promotions);
      if(IPromotionsRepository.StorageResult.SUCCESS.equals(result)){
         return true;
      } else {
         return false;
      }
   }
   public List<Promotions> listAllPromotions(){
      return promotionsRepository.findAll();
   }

   public boolean deletePromotionsbyId(String promotionId){
      System.out.println("promotions service");
      IPromotionsRepository.StorageResult result = promotionsRepository.deleteById(promotionId);
      System.out.println("promotions service res "+result);
      if(result.equals(IPromotionsRepository.StorageResult.SUCCESS)){
         return true;
      } else {
         return false;
      }
   }

   public Promotions findById(String promotionId) {
      Promotions promotions = promotionsRepository.findById(promotionId);
      return promotions;
   }

   public int validatePromotionAndGetDiscountAmount(String promoCode){
      int discount =0;
      Promotions promotion = findById(promoCode);
      LocalDate startDate = LocalDate.parse(promotion.getPromotionStartDate());
      LocalDate endDate = LocalDate.parse(promotion.getPromotionEndDate());
      LocalDate today = LocalDate.now();
      if(endDate.isAfter(today) && startDate.isBefore(today)){
         discount = promotion.getAmountOff();
      }
      return discount;
   }

}
