package com.dal.drplus.model.entity;

public class Payment {
    private String paymentId;
    private String billId;
    private String paymentType;
    private String paymentDetails;
    private boolean paymentStatusEnabled;

    public String getPaymentId() {
        return paymentId;
    }

    public void setPaymentId(String paymentId) {
        this.paymentId = paymentId;
    }

    public String getBillId() {
        return billId;
    }

    public void setBillId(String billId) {
        this.billId = billId;
    }

    public String getPaymentType() {
        return paymentType;
    }

    public void setPaymentType(String paymentType) {
        this.paymentType = paymentType;
    }

    public String getPaymentDetails() {
        return paymentDetails;
    }

    public void setPaymentDetails(String paymentDetails) {
        this.paymentDetails = paymentDetails;
    }

    public boolean isPaymentStatusEnabled() {
        return paymentStatusEnabled;
    }

    public void setPaymentStatusEnabled(boolean paymentStatusEnabled) {
        this.paymentStatusEnabled = paymentStatusEnabled;
    }
}
