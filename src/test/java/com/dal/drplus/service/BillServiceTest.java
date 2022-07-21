package com.dal.drplus.service;

import com.dal.drplus.model.Billing;
import com.dal.drplus.repository.implementation.BillRepositoryImpl;
import com.dal.drplus.repository.interfaces.IBillRepository;
import org.junit.jupiter.api.BeforeAll;
import org.junit.jupiter.api.Test;
import org.mockito.Mockito;

import java.time.LocalDate;
import java.time.format.DateTimeFormatter;
import static org.junit.jupiter.api.Assertions.*;

public class BillServiceTest {

    private static BillService billService;
    private static IBillRepository billRepository;
    private static Billing bill_initial;

    @BeforeAll
    public static void init(){
        bill_initial = new Billing();
        bill_initial.setBillId(1);
        bill_initial.setBillAmount(34);
        bill_initial.setBillDescription("xyz");
        //bill.setBillDate(LocalDate.now().format(DateTimeFormatter.ISO_DATE));
        billRepository = Mockito.mock(BillRepositoryImpl.class);
        billService = new BillService(billRepository);
        Mockito.when(billRepository.insertBill(34,"xyz")).thenReturn(1);
        Mockito.when(billRepository.getBillAmount(1)).thenReturn(34.0);
        Mockito.when(billRepository.getBill(1)).thenReturn(bill_initial);
        Mockito.when(billRepository.getBill(2)).thenReturn(null);
    }

    @Test
    void generateBill(){
        int result = billService.generateBill(34,"xyz");
        assertEquals(1,result);
    }

    @Test
    void getBillAmount(){
        double result = billService.getBillAmount(1);
    }

    @Test
    void getBillSuccess(){
        Billing bill = billService.getBill(1);
        assertEquals(bill_initial,bill);
    }

    @Test
    void getBillFailure(){
        Billing bill = billService.getBill(2);
        assertNull(bill);
    }


}