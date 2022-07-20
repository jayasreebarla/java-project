package com.dal.drplus.repository.interfaces;


import com.dal.drplus.model.Promotions;

import java.util.List;

public interface IPromotionsRepository {
    enum StorageResult{
        SUCCESS,
        FAILURE
    }
   StorageResult savePromotions(Promotions promotions);

    StorageResult updatePromotions(Promotions promotions);

    Promotions findById(int promotionId);

    StorageResult deleteById(int promotionId);

    List<Promotions> findAll();

    int deleteAll();
}
