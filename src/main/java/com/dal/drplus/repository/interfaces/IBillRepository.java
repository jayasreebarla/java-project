package com.dal.drplus.repository.interfaces;

import com.dal.drplus.model.IEntity.IBilling;

public interface IBillRepository {

    enum StorageResult{
        SUCCESS,
        FAILURE
    }

    int insertBill(double amount,String description);
    double getBillAmount(int billId);
    IBilling getBill(int billId);
    StorageResult UpdateBillAmount(int billId,double amount);
}
