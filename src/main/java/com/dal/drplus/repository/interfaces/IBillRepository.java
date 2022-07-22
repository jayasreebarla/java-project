package com.dal.drplus.repository.interfaces;

import com.dal.drplus.model.Billing;

public interface IBillRepository {

    enum StorageResult{
        SUCCESS,
        FAILURE
    }

    int insertBill(double amount,String description);
    double getBillAmount(int billId);
    Billing getBill(int billId);
    StorageResult UpdateBillAmount(int billId,double amount);
}
