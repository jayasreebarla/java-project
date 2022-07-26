package com.dal.drplus.model.entity;

import com.dal.drplus.model.IBuilder.IBillingBuilder;
import com.dal.drplus.model.IEntity.IBilling;

import java.util.Date;

public class Billing extends IBilling {
    int billId;
    Date billDate;
    double billAmount;
    String billDescription;

    public Billing() {
    }

    public Billing(IBillingBuilder builder) {
        this.billAmount= builder.getBillAmount();
        this.billDate=builder.getBillDate();
        this.billDescription= builder.getBillDescription();
        this.billId = builder.getBillId();
    }

    public int getBillId() {
        return billId;
    }

    public void setBillId(int billId) {
        this.billId = billId;
    }

    public Date getBillDate() {
        return billDate;
    }

    public void setBillDate(Date billDate) {
        this.billDate = billDate;
    }

    public double getBillAmount() {
        return billAmount;
    }

    public void setBillAmount(double billAmount) {
        this.billAmount = billAmount;
    }

    public String getBillDescription() {
        return billDescription;
    }

    public void setBillDescription(String billDescription) {
        this.billDescription = billDescription;
    }

}
