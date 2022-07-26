package com.dal.drplus.model.Builder;
import com.dal.drplus.model.IBuilder.IPromotionsBuilder;
import com.dal.drplus.model.entity.Promotions;
import com.dal.drplus.model.factory.ModelFactory;

public class PromotionsBuilder implements IPromotionsBuilder{

    private String promotionId;
    private String promotionName;
    private String promotionStartDate;
    private String promotionEndDate;
    private int amountOff;

    public PromotionsBuilder addPromotionsId(String promotionId){
        this.promotionId = promotionId;
        return this;
    }

    public PromotionsBuilder addPromotionsName(String promotionName){
        this.promotionName = promotionName;
        return this;
    }

    public PromotionsBuilder addPromotionsStartDate(String promotionStartDate){
        this.promotionStartDate = promotionStartDate;
        return this;
    }

    public PromotionsBuilder addPromotionsEndDate(String promotionEndDate){
        this.promotionEndDate = promotionEndDate;
        return this;
    }

    public PromotionsBuilder addAmountOff(int amountOff){
        this.amountOff = amountOff;
        return this;
    }

    public Promotions build(){
        Promotions promotions = ModelFactory.instance().createPromotionsUsingBuilder(this);
        return  promotions;
    }

    public String getPromotionsId() {return promotionId;}
    public String getPromotionsName() {return promotionName;}
    public String getPromotionsStartDate() {return promotionStartDate;}
    public String getPromotionsEndDate() {return promotionEndDate;}
    public int getAmountOff() {return  amountOff;}

}
