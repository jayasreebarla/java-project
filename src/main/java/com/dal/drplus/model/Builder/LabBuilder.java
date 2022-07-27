package com.dal.drplus.model.Builder;

import com.dal.drplus.model.IBuilder.ILabBuilder;
import com.dal.drplus.model.entity.Lab;
import com.dal.drplus.model.factory.ModelFactory;

public class LabBuilder implements ILabBuilder {
    private String labId;
    private String labPersonName;
    private String labPassword;
    private String labEmailId;
    private String labAddress;
    private String labContactInfo;
    private String labPincode;
    private double labFee;
    private int labRating;

    public LabBuilder addLabId(String labId){
        this.labId = labId;
        return this;
    }

    public LabBuilder addLabPersonName(String labPersonName){
        this.labPersonName = labPersonName;
        return this;
    }

    public LabBuilder addLabPassword(String labPassword){
        this.labPassword = labPassword;
        return this;
    }

    @Override
    public LabBuilder addLabEmailId(String labEmailId) {
        this.labEmailId = labEmailId;
        return this;
    }

    @Override
    public LabBuilder addLabAddress(String labAddress){
        this.labAddress = labAddress;
        return this;
    }

    public LabBuilder addLabContactInfo(String labContactInfo){
        this.labContactInfo = labContactInfo;
        return this;
    }

    public LabBuilder addLabPincode(String labPincode){
        this.labPincode = labPincode;
        return this;
    }

    public LabBuilder addLabFee(double labFee){
        this.labFee = labFee;
        return this;
    }

    public LabBuilder addLabRating(int labRating){
        this.labRating = labRating;
        return this;
    }

    public Lab build() {
        Lab lab = ModelFactory.instance().createLabUsingBuilder(this);
        return lab;
    }

    public String getLabId(){ return labId;}
    public String getLabPersonName(){return labPersonName;}
    public String getLabPassword(){return labPassword;}
    public String getLabEmailId(){return labEmailId;}
    public String getLabAddress(){return labAddress;}
    public String getLabContactInfo(){return labContactInfo;}
    public String getLabPincode(){return labPincode;}
    public double getLabFee(){return labFee;}
    public int getLabRating(){return labRating;}
}
