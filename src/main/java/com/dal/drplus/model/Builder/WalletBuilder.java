package com.dal.drplus.model.Builder;

import com.dal.drplus.model.IBuilder.IWalletBuilder;
import com.dal.drplus.model.entity.Wallet;
import com.dal.drplus.model.factory.ModelFactory;

public class WalletBuilder implements IWalletBuilder {
    private String walletId;
    private double amount;
    @Override
    public IWalletBuilder addWalletId(String walletId) {
        this.walletId=walletId;
        return this;
    }

    @Override
    public IWalletBuilder addWalletAmount(double amount) {
        this.amount=amount;
        return this;
    }

    @Override
    public Wallet build() {
        Wallet wallet = ModelFactory.instance().createWalletUsingBuilder(this);
        return wallet;
    }

    @Override
    public String getWalletId() {
        return walletId;
    }

    @Override
    public double getAmount() {
        return amount;
    }
}
