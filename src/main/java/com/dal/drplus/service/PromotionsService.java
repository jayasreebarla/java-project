package com.dal.drplus.service;
import com.dal.drplus.model.Promotions;

import com.dal.drplus.repository.interfaces.IPromotionsRepository;

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

   public Promotions findById(int promotion_id) {
      Promotions promotions = promotionsRepository.findById(promotion_id);
      return promotions;
   }

}
