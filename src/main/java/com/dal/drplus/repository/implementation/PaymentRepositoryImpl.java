package com.dal.drplus.repository.implementation;

import com.dal.drplus.model.Payment;
import com.dal.drplus.repository.interfaces.IPaymentRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.jdbc.core.BeanPropertyRowMapper;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.stereotype.Repository;

@Repository
public class PaymentRepositoryImpl implements IPaymentRepository {

    @Autowired
    private JdbcTemplate jdbcTemplate;

    @Override
    public int addPayment(Payment payment) {
        String query = "INSERT INTO Payment (Payment_id, Bill_id, Payment_type, Payment_desc, Payment_status_enabled) " +
                "VALUES (?,?,?,?,?)";

        return jdbcTemplate.update (query, new Object[] {payment.getPaymentId(),
                payment.getBillId(),
                payment.getPaymentType(),
                payment.getPaymentDetails(),
                payment.isPaymentStatusEnabled()});
    }

    @Override
    public Payment getPayment(String paymentId) {
        String query = "Select * FROM Payment WHERE Payment_id = ?";
        Payment payment = jdbcTemplate.queryForObject(query, BeanPropertyRowMapper.newInstance(Payment.class), paymentId);

        return payment;
    }

    @Override
    public int updatePayment(Payment payment) {
        String query = "Update Payment set  Bill_id=?, Payment_type=?, Payment_desc=?, Payment_status_enabled=? " +
                " where Payment_id=?";

        return jdbcTemplate.update (query, new Object[] {payment.getBillId(),
                payment.getPaymentType(),
                payment.getPaymentDetails(),
                payment.isPaymentStatusEnabled(),
                payment.getPaymentId()});
    }

    @Override
    public int revertPaymentAppointmentCancelled(Payment payment) {
        String query = "";

        return jdbcTemplate.update(query);
    }
}
