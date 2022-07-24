package com.dal.drplus.repository.interfaces;

import com.dal.drplus.model.entity.Billing;

import java.util.List;

public interface IBillingRepository {

    int saveBill(Billing billing);

    int updateBill(Billing billing);

    Billing findById(Long id);

    int deleteById(Long bill_id);

    List<Billing> findAll();

    int deleteAll();
}
