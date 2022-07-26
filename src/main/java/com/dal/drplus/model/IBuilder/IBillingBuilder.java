package com.dal.drplus.model.IBuilder;

import com.dal.drplus.model.entity.Billing;

import java.util.Date;

public interface IBillingBuilder {

    IBillingBuilder addBillId(int billId);
    IBillingBuilder addBillDate(Date billDate);
    IBillingBuilder addBillAmount(double billAmount);
    IBillingBuilder addBillDescription(String billDescription);
    public Billing build();
    public int getBillId();
    public Date getBillDate();
    public double getBillAmount();
    public String getBillDescription();


}
