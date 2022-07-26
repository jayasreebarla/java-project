package com.dal.drplus.model.IBuilder;

import com.dal.drplus.model.Builder.LabBuilder;
import com.dal.drplus.model.entity.Lab;

public interface ILabBuilder {
    ILabBuilder addLabId(String labId);
    ILabBuilder addLabPersonName(String labPersonName);
    ILabBuilder addLabPassword(String labPassword);
    LabBuilder addLabEmailId(String labEmailId);
    ILabBuilder addLabAddress(String labAddress);
    ILabBuilder addLabContactInfo(String labContactInfo);
    ILabBuilder addLabPincode(String labPincode);
    ILabBuilder addLabFee(double labFee);
    ILabBuilder addLabRating(int labRating);
    public Lab build();
    public String getLabId();
    public String getLabPersonName();
    public String getLabPassword();
    public String getLabEmailId();
    public String getLabAddress();
    public String getLabContactInfo();
    public String getLabPincode();
    public double getLabFee();
    public int getLabRating();
}
