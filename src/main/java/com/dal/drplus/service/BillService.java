package com.dal.drplus.service;

import com.dal.drplus.model.Billing;
import com.dal.drplus.repository.interfaces.IBillRepository;

public class BillService {
    private IBillRepository billRepository;

    public BillService(IBillRepository billRepository) {
        this.billRepository = billRepository;
    }

    public int generateBill(double amount,String description){
        //appointmentType from session
        //
        int bill_id = billRepository.insertBill(amount,description);
        return bill_id;
    }

    public double getBillAmount(int billId){
        return billRepository.getBillAmount(billId);
    }

    public Billing getBill(int billId){
        return billRepository.getBill(billId);
    }
}
