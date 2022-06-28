package com.dal.drplus.repository.interfaces;


import com.dal.drplus.model.Promotions;

import java.util.List;

public interface IPromotionsRepository {
    int savePromotions(Promotions promotions);

    int updatePromotions(Promotions promotions);

    Promotions findById(Long bill_id);

    int deleteById(Long bill_id);

    List<Promotions> findAll();

    int deleteAll();
}
