package com.dal.drplus.service;

import com.dal.drplus.model.IEntity.IBilling;
import com.dal.drplus.repository.interfaces.IBillRepository;

public class BillService {
    private IBillRepository billRepository;

    public BillService(IBillRepository billRepository) {
        this.billRepository = billRepository;
    }

    public int generateBill(double amount,String description){

        int bill_id = billRepository.insertBill(amount,description);
        return bill_id;
    }

    public double getBillAmount(int billId){
        if(getBill(billId).validateBillAmount()){
            return billRepository.getBillAmount(billId);
        }else{
            return 0;
        }
    }

    public IBilling getBill(int billId){
        return billRepository.getBill(billId);
    }

    public boolean updateBill(int id,double amount){
        IBillRepository.StorageResult result = billRepository.UpdateBillAmount(id,amount);
        if(result.equals(IBillRepository.StorageResult.SUCCESS)){
            return true;
        }else{
            return false;
        }
    }
}
