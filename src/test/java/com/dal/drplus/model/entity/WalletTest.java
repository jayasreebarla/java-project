package com.dal.drplus.model.entity;

import com.dal.drplus.model.factory.ModelFactory;
import org.junit.jupiter.api.BeforeAll;
import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.assertFalse;
import static org.junit.jupiter.api.Assertions.assertTrue;

public class WalletTest {

    private static Wallet wallet1 = (Wallet) ModelFactory.instance().createWallet();
    private static Wallet wallet2 = (Wallet) ModelFactory.instance().createWallet();

    @BeforeAll
    public static void init(){
        wallet1.setWalletId("w_1");
        wallet1.setAmount(30);

        wallet2.setWalletId("w_2");
        wallet2.setAmount(0);
    }

    @Test
    public void validateAmountTrue(){
        boolean result = wallet1.validateWalletAmount();
        assertTrue(result);
    }

    @Test
    public void validateAmountFalse(){
        boolean result = wallet2.validateWalletAmount();
        assertFalse(result);
    }
}
