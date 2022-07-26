package com.dal.drplus.model.entity;

import com.dal.drplus.model.IBuilder.ILabBuilder;
import com.dal.drplus.model.IEntity.ILab;

public class Lab extends ILab {

//    private String labId;
//    private String labPersonName;
//    private String labPassword;
//    //private String labConfirmPassword;
//    private String labEmailId;
//    private String labAddress;
//    private String labContactInfo;
//    private String labPincode;
//    private double labFee;
//    private int labRating;

    public Lab() {

    }

    public Lab(ILabBuilder builder){
        this.labId = builder.getLabId();
        this.labPersonName = builder.getLabPersonName();
        this.labPassword = builder.getLabPassword();
        this.labEmailId = builder.getLabEmailId();
        this.labAddress = builder.getLabAddress();
        this.labContactInfo = builder.getLabContactInfo();
        this.labPincode = builder.getLabPincode();
        this.labFee = builder.getLabFee();
        this.labRating = builder.getLabRating();
    }

    public Lab(String labId, String labPersonName, String labPassword, String labEmailId, String labAddress, String labContactInfo, String labPincode,double labFee, int labRating) {
        this.labId = labId;
        this.labPersonName = labPersonName;
        this.labPassword = labPassword;
        this.labEmailId = labEmailId;
        this.labAddress = labAddress;
        this.labContactInfo = labContactInfo;
        this.labPincode = labPincode;
        this.labFee = labFee;
        this.labRating = labRating;
    }


    @Override
    public String getLabId() {
        return this.labId;
    }

    @Override
    public void setLabId(String labId) {
        this.labId = labId;
    }

    @Override
    public String getLabPersonName() {
        return this.labPersonName;
    }

    @Override
    public void setLabPersonName(String labPersonName) {
        this.labPersonName = labPersonName;
    }

    @Override
    public String getLabPassword() {
        return labPassword;
    }

    @Override
    public void setLabPassword(String labPassword) {
        this.labPassword = labPassword;
    }

    @Override
    public String getLabEmailId() {
        return labEmailId;
    }

    @Override
    public void setLabEmailId(String labEmailId) {
        this.labEmailId = labEmailId;
    }

    @Override
    public String getLabAddress() {
        return labAddress;
    }

    @Override
    public void setLabAddress(String labAddress) {
        this.labAddress = labAddress;
    }

    @Override
    public String getLabContactInfo() {
        return labContactInfo;
    }

    @Override
    public void setLabContactInfo(String labContactInfo) {
        this.labContactInfo = labContactInfo;
    }

    @Override
    public String getLabPincode() {
        return labPincode;
    }

    @Override
    public void setLabPincode(String labPincode) {
        this.labPincode = labPincode;
    }

    @Override
    public double getLabFee() {
        return labFee;
    }

    @Override
    public void setLabFee(double labFee) {
        this.labFee = labFee;
    }

    @Override
    public int getLabRating() {
        return labRating;
    }

    @Override
    public void setLabRating(int labRating) {
        this.labRating = labRating;
    }

    @Override
    public boolean validateLabPersonNameFormat(String labPersonName) {
        return labPersonName.matches("^[a-zA-Z0-9]*$");
    }

    @Override
    public boolean validateLabPincodeFormat(String labPincode) {
        return labPincode.matches("^[a-zA-Z0-9]*$");
    }

    @Override
    public boolean validateLabEmailIdFormat(String labEmailId) {
        return labEmailId.matches("\"^[a-zA-Z0-9_.+-]+@[a-zA-Z0-9-]+\\.[a-zA-Z0-9-.]+$\"");
    }

    @Override
    public boolean validateLabContactInfoFormat(String labContactInfo) {
        return labContactInfo.matches("^(\\+\\d{1,2}\\s)?\\(?\\d{3}\\)?[\\s.-]\\d{3}[\\s.-]\\d{4}$");
    }

}
