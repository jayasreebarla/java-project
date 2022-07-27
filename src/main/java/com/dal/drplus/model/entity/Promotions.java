package com.dal.drplus.model.entity;
import com.dal.drplus.model.IBuilder.IPromotionsBuilder;
import com.dal.drplus.model.IEntity.IPromotions;

public class Promotions extends IPromotions {

    public Promotions(){
    }

    public Promotions(IPromotionsBuilder builder){
        this.promotionId = builder.getPromotionsId();
        this.promotionName = builder.getPromotionsName();
        this.promotionStartDate = builder.getPromotionsStartDate();
        this.promotionEndDate = builder.getPromotionsEndDate();
        this.amountOff = builder.getAmountOff();
    }

    public Promotions(String promotionId,String promotionName,String promotionStartDate,String promotionEndDate,int amountOff){
        this.promotionId = promotionId;
        this.promotionName = promotionName;
        this.promotionStartDate = promotionStartDate;
        this.promotionEndDate = promotionEndDate;
        this.amountOff = amountOff;
    }

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
