package com.dal.drplus.model.Builder;

import com.dal.drplus.model.IBuilder.IBillingBuilder;
import com.dal.drplus.model.entity.Billing;
import com.dal.drplus.model.factory.ModelFactory;

import java.util.Date;
public class BillingBuilder implements IBillingBuilder {
    int billId;
    Date billDate;
    double billAmount;
    String billDescription;

    @Override
    public IBillingBuilder addBillId(int billId) {
        this.billId = billId;
        return this;
    }

    @Override
    public IBillingBuilder addBillDate(Date billDate) {
        this.billDate=billDate;
        return this;
    }

    @Override
    public IBillingBuilder addBillAmount(double billAmount) {
        this.billAmount=billAmount;
        return this;
    }

    @Override
    public IBillingBuilder addBillDescription(String billDescription) {
        this.billDescription=billDescription;
        return this;
    }

    @Override
    public Billing build() {
        Billing bill = ModelFactory.instance().createBillingUsingBuilder(this);
        return bill;
    }

    @Override
    public int getBillId() {
        return billId;
    }

    @Override
    public Date getBillDate() {
        return billDate;
    }

    @Override
    public double getBillAmount() {
        return billAmount;
    }

    @Override
    public String getBillDescription() {
        return billDescription;
    }
}
