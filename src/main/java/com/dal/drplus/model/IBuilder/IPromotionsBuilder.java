package com.dal.drplus.model.IBuilder;

import com.dal.drplus.model.entity.Promotions;

public interface IPromotionsBuilder {
    IPromotionsBuilder addPromotionsId(String promotionsId);
    IPromotionsBuilder addPromotionsName(String promotionsName);
    IPromotionsBuilder addPromotionsStartDate(String promotionsStartDate);
    IPromotionsBuilder addPromotionsEndDate(String promotionsEndDate);
    IPromotionsBuilder addAmountOff(int amountOff);

    public Promotions build();

    public String getPromotionsId();
    public String getPromotionsName();
    public String getPromotionsStartDate();
    public String getPromotionsEndDate();
    public int getAmountOff();
}
