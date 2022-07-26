package com.dal.drplus.model.entity;

import com.dal.drplus.model.factory.ModelFactory;
import org.junit.jupiter.api.BeforeAll;
import org.junit.jupiter.api.Test;

import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.time.LocalDate;
import java.util.Date;

import static org.junit.jupiter.api.Assertions.assertFalse;
import static org.junit.jupiter.api.Assertions.assertTrue;

public class BillingTest {
    private static Billing bill1 = (Billing)ModelFactory.instance().createBilling();
    private static Billing bill2 = (Billing)ModelFactory.instance().createBilling();
    @BeforeAll
    public static void init(){
        bill1.setBillId(1);
        String sDate1 = "2022-07-26";
        SimpleDateFormat formatter2=new SimpleDateFormat("yyyy-MM-dd");
        Date date1;
        try {
            date1=formatter2.parse(sDate1);
        } catch (ParseException e) {
            throw new RuntimeException(e);
        }
        bill1.setBillDate(date1);
        bill1.setBillAmount(57);
        bill1.setBillDescription("abc");

        bill2.setBillId(2);
        bill2.setBillDate(date1);
        bill2.setBillAmount(-1);
        bill2.setBillDescription("abc");
    }

    @Test
    public void validateBillAmountTrue(){
        boolean result = bill1.validateBillAmount();
        assertTrue(result);
    }

    @Test
    public void validateBillAmountFalse(){
        boolean result = bill2.validateBillAmount();
        assertFalse(result);
    }

    @Test
    public void validateDateTrue(){
        String date = "2023-07-26";
        boolean result = bill1.validateDate(date);
        assertTrue(result);
    }

    @Test
    public void validateDateFalse(){
        String date = "2022-07-24";
        boolean result = bill1.validateDate(date);
        assertFalse(result);
    }
}
