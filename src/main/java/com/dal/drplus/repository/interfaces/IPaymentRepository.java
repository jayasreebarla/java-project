package com.dal.drplus.repository.interfaces;

import com.dal.drplus.model.Payment;

public interface IPaymentRepository {

    public int addPayment(Payment payment);

    public Payment getPayment(String paymentId);

    public int updatePayment(Payment payment);

    public int revertPaymentAppointmentCancelled(Payment payment);
}
