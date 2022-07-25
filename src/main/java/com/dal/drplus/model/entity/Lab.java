package com.dal.drplus.model.entity;

public class Lab {
    private String labId;
    private String labPersonName;
    private String labPassword;
    //private String labConfirmPassword;
    private String labEmailId;
    private String labAddress;
    private String labContactInfo;
    private String labPincode;
    private double labFee;
    private int labRating;

    public int getLabRating() {
        return labRating;
    }

    public void setLabRating(int labRating) {
        this.labRating = labRating;
    }

    public double getLabFee() {
        return labFee;
    }
    public void setLabFee(double labFee) {
        this.labFee = labFee;
    }

    public Lab(){}

    public Lab(String labId, String labPersonName, String labPassword, String labEmailId, String labAddress, String labContactInfo, String labPincode) {
    this.labId = labId;
    this.labPersonName = labPersonName;
    this.labPassword = labPassword;
    this.labEmailId = labEmailId;
    this.labAddress = labAddress;
    this.labContactInfo = labContactInfo;
    this.labPincode = labPincode;
   }

    public String getLabId() {return labId;}
    public void setLabId(String labId) {this.labId = labId;}
    public String getLabPersonName() {return labPersonName;}
    public void setLabPersonName(String labPersonName) {this.labPersonName = labPersonName;}
    public String getLabPassword() {return labPassword;}
    public void setLabPassword(String labPassword) {this.labPassword = labPassword;}
//    public String getLabConfirmPassword() {return labConfirmPassword;}
//    public void setLabConfirmPassword(String labConfirmPassword) {this.labConfirmPassword = labConfirmPassword;}
    public String getLabEmailId() {return labEmailId;}
    public void setLabEmailId(String labEmailId) {this.labEmailId = labEmailId;}
    public String getLabAddress() {return labAddress;}
    public void setLabAddress(String labAddress) {this.labAddress = labAddress;}
    public String getLabContactInfo() {return labContactInfo;}
    public void setLabContactInfo(String labContactInfo) {this.labContactInfo = labContactInfo;}

    public String getLabPincode() {
        return labPincode;
    }

    public void setLabPincode(String labPincode) {
        this.labPincode = labPincode;
    }
}
