package com.dal.drplus.model.entity;

import com.dal.drplus.model.IBuilder.IWalletBuilder;
import com.dal.drplus.model.IEntity.IWallet;

public class Wallet extends IWallet {
    private String walletId;
    private double amount;

    public Wallet(IWalletBuilder builder) {
        this.walletId=builder.getWalletId();
        this.amount= builder.getAmount();
    }
    public Wallet() {}

    public boolean validateWalletAmount(){
        return this.amount>0;
    }
    public String getWalletId() {
        return walletId;
    }
    public void setWalletId(String walletId) {
        this.walletId = walletId;
    }
    public double getAmount() {
        return amount;
    }
    public void setAmount(double amount) {
        this.amount = amount;
    }
}
