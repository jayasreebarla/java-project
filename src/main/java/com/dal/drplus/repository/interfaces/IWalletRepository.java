package com.dal.drplus.repository.interfaces;

import com.dal.drplus.model.Wallet;

public interface IWalletRepository {
    enum StorageResult{
        SUCCESS,
        FAILURE
    }
    StorageResult InsertIntoWallet(double amount,String wallet_id);
    StorageResult UpdateIntoWallet(double amount,String wallet_id);
    Wallet getWalletBalanceByWalletId(String wallet_id);
}
