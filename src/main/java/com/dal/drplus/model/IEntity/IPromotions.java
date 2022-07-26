package com.dal.drplus.model.IEntity;

public abstract class IPromotions {

    protected String promotionId;
    protected String promotionName;
    protected String promotionStartDate;
    protected String promotionEndDate;
    protected int amountOff;

    public IPromotions(){}

    public IPromotions(String promotionId,String promotionName,String promotionStartDate,String promotionEndDate,int amountOff){
        this.promotionId = promotionId;
        this.promotionName = promotionName;
        this.promotionStartDate = promotionStartDate;
        this.promotionEndDate = promotionEndDate;
    }

    abstract public String getPromotionId();

    abstract public void setPromotionId(String promotionId);

    abstract public String getPromotionName();

    abstract public void setPromotionName(String promotionName);


    abstract public String getPromotionStartDate();

    abstract public void setPromotionStartDate(String promotionStartDate);


    abstract public String getPromotionEndDate();

    abstract public void setPromotionEndDate(String promotionEndDate);


    abstract public int getAmountOff();

    abstract public void setAmountOff(int amountOff);

}
