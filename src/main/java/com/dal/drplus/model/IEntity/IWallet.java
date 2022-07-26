package com.dal.drplus.model.IEntity;

public abstract class IWallet {
    protected String walletId;
    protected double amount;

    abstract public String getWalletId();

    abstract public void setWalletId(String walletId);

    abstract public double getAmount();

    abstract public void setAmount(double amount);
    abstract public boolean validateWalletAmount();
}
