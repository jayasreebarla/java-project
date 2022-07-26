package com.dal.drplus.service;

import com.dal.drplus.model.IEntity.IBilling;
import com.dal.drplus.model.entity.Billing;
import com.dal.drplus.model.factory.ModelFactory;
import com.dal.drplus.repository.implementation.BillRepositoryImpl;
import com.dal.drplus.repository.interfaces.IBillRepository;
import org.junit.jupiter.api.BeforeAll;
import org.junit.jupiter.api.Test;
import org.mockito.Mockito;

import static org.junit.jupiter.api.Assertions.*;

public class BillServiceTest {

    private static BillService billService;
    private static IBillRepository billRepository;
    private static IBilling bill_initial;

    @BeforeAll
    public static void init(){
        bill_initial = ModelFactory.instance().createBilling();
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
        Mockito.when(billRepository.UpdateBillAmount(1,34)).thenReturn(IBillRepository.StorageResult.SUCCESS);
        Mockito.when(billRepository.UpdateBillAmount(2,34)).thenReturn(IBillRepository.StorageResult.FAILURE);

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
        IBilling bill = billService.getBill(1);
        assertEquals(bill_initial,bill);
    }

    @Test
    void getBillFailure(){
        IBilling bill = billService.getBill(2);
        assertNull(bill);
    }

    @Test
    void updateBillTrue(){
        boolean result = billService.updateBill(1,34);
        assertTrue(result);
    }

    @Test
    void updateBillFalse(){
        boolean result = billService.updateBill(2,34);
        assertFalse(result);
    }


}
