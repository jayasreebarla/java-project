package com.dal.drplus.model.IEntity;

import java.util.Date;

public abstract class IBilling {
    protected int billId;
    protected Date billDate;
    protected double billAmount;
    protected String billDescription;

    abstract public boolean validateBillAmount();

    abstract public boolean validateDate(String date);
    abstract public int getBillId();
    abstract public void setBillId(int billId);
    abstract public Date getBillDate();
    abstract public void setBillDate(Date billDate);
    abstract public double getBillAmount();
    abstract public void setBillAmount(double billAmount);
    abstract public String getBillDescription();
    abstract public void setBillDescription(String billDescription);

}
