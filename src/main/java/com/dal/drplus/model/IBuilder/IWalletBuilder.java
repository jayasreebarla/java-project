package com.dal.drplus.model.IBuilder;

import com.dal.drplus.model.entity.Wallet;

public interface IWalletBuilder {
    IWalletBuilder addWalletId(String walletId);
    IWalletBuilder addWalletAmount(double amount);
    Wallet build();
    public String getWalletId();
    public double getAmount();
}
