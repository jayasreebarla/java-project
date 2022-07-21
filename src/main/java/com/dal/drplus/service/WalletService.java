package com.dal.drplus.service;

import com.dal.drplus.model.Wallet;
import com.dal.drplus.repository.interfaces.IWalletRepository;

public class WalletService {

    IWalletRepository walletRepository;

    public WalletService(IWalletRepository walletRepository) {
        this.walletRepository = walletRepository;
    }

    public void addMoneyToWallet(double amount, String patientEmail){
        String wallet_id = "W_"+patientEmail;
        Wallet wallet = walletRepository.getWalletBalanceByWalletId(wallet_id);
        if(wallet==null){
            walletRepository.InsertIntoWallet(amount,wallet_id);
        }else{
            double amount_final = amount+ wallet.getAmount();
            walletRepository.UpdateIntoWallet(amount_final,wallet_id);
        }
    }

    public boolean deductMoneyFromWallet(double amount, String patientEmail){
        boolean return_result = false;
        String wallet_id = "W_"+patientEmail;
        Wallet wallet = walletRepository.getWalletBalanceByWalletId(wallet_id);
        if(wallet!=null){
            double amount_final = wallet.getAmount() - amount;
            IWalletRepository.StorageResult result=walletRepository.UpdateIntoWallet(amount_final,wallet_id);
            if(result.equals(IWalletRepository.StorageResult.SUCCESS)){
                return_result = true;
            }
        }
        System.out.println("return result inside wallet service"+return_result);
        return return_result;
    }

    public double getBalanceFromWallet(String patientEmail){
        String wallet_id = "W_"+patientEmail;
        Wallet wallet = walletRepository.getWalletBalanceByWalletId(wallet_id);
        if(wallet == null){
            return 0;
        }else{
        return wallet.getAmount() ;
        }
    }

}
