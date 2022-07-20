package com.dal.drplus.model;

public class Promotions {

    int promotionId;
    String promotionName;
    String promotionStartDate;
    String promotionEndDate;
    int amount_off;


    public int getPromotionId() {
        return promotionId;
    }

    public void setPromotionId(int promotionId) {
        this.promotionId = promotionId;
    }

    public String getPromotionName() {
        return promotionName;
    }

    public void setPromotionName(String promotionName) {
        this.promotionName = promotionName;
    }

    public String getPromotionStartDate() {
        return promotionStartDate;
    }

    public void setPromotionStartDate(String promotionStartDate) {
        this.promotionStartDate = promotionStartDate;
    }

    public String getPromotionEndDate() {
        return promotionEndDate;
    }

    public void setPromotionEndDate(String promotionEndDate) {
        this.promotionEndDate = promotionEndDate;
    }

    public int getAmount_off() {
        return amount_off;
    }

    public void setAmount_off(int amount_off) {
        this.amount_off = amount_off;
    }
}
