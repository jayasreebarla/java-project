package com.dal.drplus.service;

import com.dal.drplus.model.Wallet;
import com.dal.drplus.repository.implementation.WalletRepositoryImpl;
import com.dal.drplus.repository.interfaces.IWalletRepository;
import org.junit.jupiter.api.BeforeAll;
import org.junit.jupiter.api.Test;
import org.mockito.Mockito;
import static org.junit.jupiter.api.Assertions.*;

public class WalletServiceTest {
    private static Wallet wallet1;
    private static Wallet wallet2;

    private static WalletService walletService;
    private static IWalletRepository walletRepository;

    @BeforeAll
    public static void init(){
        walletRepository = Mockito.mock(WalletRepositoryImpl.class);
        walletService = new WalletService(walletRepository);
        wallet1 = new Wallet();
        wallet1.setWalletId("W_connect.parulraich@gmail.com");
        wallet1.setAmount(34);
        wallet2 = new Wallet();
        wallet2.setWalletId("W_abc@gmail.com");
        wallet2.setAmount(54);
        String wallet1_id = "W_connect.parulraich@gmail.com";
        String wallet2_id = "W_abc@gmail.com";
        Mockito.when(walletRepository.getWalletBalanceByWalletId(wallet1_id)).thenReturn(wallet1);
        Mockito.when(walletRepository.getWalletBalanceByWalletId(wallet2_id)).thenReturn(null);
        Mockito.when(walletRepository.UpdateIntoWallet(30,wallet1_id)).thenReturn(IWalletRepository.StorageResult.SUCCESS);
        Mockito.when(walletRepository.UpdateIntoWallet(30,wallet2_id)).thenReturn(IWalletRepository.StorageResult.FAILURE);
        walletRepository = Mockito.mock(WalletRepositoryImpl.class);

    }

    @Test
    void deductMoneyFromWalletTrue(){
        boolean result = walletService.deductMoneyFromWallet(4,"connect.parulraich@gmail.com");
        assertTrue(result);
    }

    @Test
    void deductMoneyFromWalletFalseWhenWalletDoesNotExistsForPatientId(){
        boolean result = walletService.deductMoneyFromWallet(20,"abc@gmail.com");
        assertFalse(result);
    }

    @Test
    void deductMoneyFromWalletFalseWhenStorageResultIsFailure(){
        boolean result = walletService.deductMoneyFromWallet(20,"abc@gmail.com");
        assertFalse(result);
    }

    @Test
    void getBalanceFromWalletZero(){
        double result = walletService.getBalanceFromWallet("abc@gmail.com");
        assertEquals(0,result);
    }

    @Test
    void getBalanceFromWalletPositive(){
        double result = walletService.getBalanceFromWallet("connect.parulraich@gmail.com");
        assertEquals(wallet1.getAmount(),result);
    }
}
