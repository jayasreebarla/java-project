package com.dal.drplus.repository.implementation;

import com.dal.drplus.model.Promotions;
import com.dal.drplus.repository.interfaces.IPromotionsRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.dao.IncorrectResultSizeDataAccessException;
import org.springframework.jdbc.core.BeanPropertyRowMapper;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public class PromotionsRepositoryImpl implements IPromotionsRepository {

    @Autowired
    private JdbcTemplate jdbcTemplate;

    @Override
    public int savePromotions(Promotions promotions) {
        return jdbcTemplate.update("INSERT INTO Promotions (promotion_id, promotion_name, promotion_start_date, promotion_end_date) VALUES(?,?,?,?,?)",
                new Object[] { promotions.getPromotionId(), promotions.getPromotionName(), promotions.getPromotionStartDate(), promotions.getPromotionEndDate() });
    }

    @Override
    public int updatePromotions(Promotions promotions) {
        return jdbcTemplate.update("UPDATE Promotions SET promotion_name=?, promotion_start_date=?, promotion_end_date=? WHERE promotion_id=?",
                new Object[] {  promotions.getPromotionName(), promotions.getPromotionStartDate(), promotions.getPromotionEndDate() ,promotions.getPromotionId() });
    }

    @Override
    public Promotions findById(Long promotion_id) {
        try {
            Promotions promotions = jdbcTemplate.queryForObject("SELECT * FROM Promotions WHERE promotion_id=?",
                    BeanPropertyRowMapper.newInstance(Promotions.class), promotion_id);
            return promotions;
        } catch (IncorrectResultSizeDataAccessException e) {
            return null;
        }
    }

    @Override
    public int deleteById(Long promotion_id) {
        return jdbcTemplate.update("DELETE FROM Promotions WHERE promotions_id=?", promotion_id);
    }

    @Override
    public List<Promotions> findAll() {
        return jdbcTemplate.query("SELECT * from Promotions", BeanPropertyRowMapper.newInstance(Promotions.class));
    }

    @Override
    public int deleteAll() {
        return jdbcTemplate.update("DELETE from Promotions");
    }

}
