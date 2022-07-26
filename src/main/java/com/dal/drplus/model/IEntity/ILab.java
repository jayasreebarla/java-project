package com.dal.drplus.model.IEntity;

public abstract class ILab {
    protected String labId;
    protected String labPersonName;
    protected String labPassword;
    protected String labEmailId;
    protected String labAddress;
    protected String labContactInfo;
    protected String labPincode;
    protected double labFee;
    protected int labRating;

    public ILab(){}

    public ILab(String labId,String labPersonName,String labPassword, String labEmailId,String labAddress,String labContactInfo,String labPincode,double labFee,int labRating){
        this.labId = labId;
        this.labPersonName = labPersonName;
        this.labEmailId = labEmailId;
        this.labAddress = labAddress;
        this.labContactInfo = labContactInfo;
        this.labPincode = labPincode;
        this.labFee = labFee;
        this.labRating = labRating;
    }
    abstract public String getLabId();

    abstract public void setLabId(String labId);

    abstract public String getLabPersonName();

    abstract public void setLabPersonName(String labPersonName);

    abstract public String getLabPassword();

    abstract public void setLabPassword(String labPassword);

    abstract public String getLabEmailId();

    abstract public void setLabEmailId(String labEmailId);

    abstract public String getLabAddress();

    abstract public void setLabAddress(String labAddress);

    abstract public String getLabContactInfo();

    abstract public void setLabContactInfo(String labContactInfo);

    abstract public String getLabPincode();

    abstract public void setLabPincode(String labPincode);

    abstract public double getLabFee();

    abstract public void setLabFee(double labFee);

    abstract public int getLabRating();

    abstract public void setLabRating(int labRating);
}
