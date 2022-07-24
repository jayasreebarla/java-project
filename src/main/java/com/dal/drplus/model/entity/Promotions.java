package com.dal.drplus.model.entity;

public class Promotions {

    String promotionId;
    String promotionName;
    String promotionStartDate;
    String promotionEndDate;
    int amountOff;


    public String getPromotionId() {
        return promotionId;
    }

    public void setPromotionId(String promotionId) {
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

    public int getAmountOff() {
        return amountOff;
    }

    public void setAmountOff(int amountOff) {
        this.amountOff = amountOff;
    }
}
