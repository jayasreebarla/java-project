package com.dal.drplus.repository.implementation;


import com.dal.drplus.model.Billing;
import com.dal.drplus.repository.interfaces.IBillingRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.dao.IncorrectResultSizeDataAccessException;
import org.springframework.jdbc.core.BeanPropertyRowMapper;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public class BillingRepositoryImpl implements IBillingRepository {

    @Autowired
    private JdbcTemplate jdbcTemplate;

    @Override
    public int saveBill(Billing billing) {
        return jdbcTemplate.update("INSERT INTO Bill (bill_id, bill_date, bill_amount, bill_description, bill_Status) VALUES(?,?,?,?,?)",
                new Object[] { billing.getBillId(), billing.getBillDate(), billing.getBillAmount(), billing.getBillDescription(), billing.getBillStatus() });
    }

    @Override
    public int updateBill(Billing billing) {
        return jdbcTemplate.update("UPDATE Bill SET  bill_date=?, bill_amount=?, bill_description=?, bill_Status=? WHERE bill_id=?",
                new Object[] { billing.getBillDate(), billing.getBillAmount(), billing.getBillDescription(), billing.getBillStatus() ,billing.getBillId()  });
    }

    @Override
    public Billing findById(Long bill_id) {
        try {
            Billing billing = jdbcTemplate.queryForObject("SELECT * FROM Bill WHERE bill_id=?",
                    BeanPropertyRowMapper.newInstance(Billing.class), bill_id);
            return billing;
        } catch (IncorrectResultSizeDataAccessException e) {
            return null;
        }
    }

    @Override
    public int deleteById(Long bill_id) {
        return jdbcTemplate.update("DELETE FROM Bill WHERE bill_id=?", bill_id);
    }

    @Override
    public List<Billing> findAll() {
        return jdbcTemplate.query("SELECT * from Bill", BeanPropertyRowMapper.newInstance(Billing.class));
    }

    @Override
    public int deleteAll() {
        return jdbcTemplate.update("DELETE from Bill");
    }
}
