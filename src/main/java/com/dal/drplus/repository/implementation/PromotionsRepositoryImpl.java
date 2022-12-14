package com.dal.drplus.repository.implementation;
import com.dal.drplus.model.entity.Promotions;
import com.dal.drplus.repository.configuration.DatabaseConfiguration;
import com.dal.drplus.repository.configuration.DatabaseConfigurationImpl;
import com.dal.drplus.repository.interfaces.IPromotionsRepository;
import org.springframework.stereotype.Repository;

import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

@Repository
public class PromotionsRepositoryImpl implements IPromotionsRepository {
    DatabaseConfiguration databaseConfiguration;
    public PromotionsRepositoryImpl() {
        this.databaseConfiguration = dbConfig();
    }
    private DatabaseConfiguration dbConfig() {
        return new DatabaseConfigurationImpl();
    }

    String INSERT_PROMOTION = "INSERT INTO Promotions (promotion_id, promotion_name, promotion_start_date, promotion_end_date,amount_off) VALUES(?,?,?,?,?)";
    String UPDATE_PROMOTION = "UPDATE Promotions SET promotion_name=?, promotion_start_date=?, promotion_end_date=?, amount_off=? WHERE promotion_id=?";
    String FIND_PROMOTION_BY_ID = "SELECT * FROM Promotions WHERE promotion_id=?";
    String DELETE_PROMOTION_BY_ID = "DELETE FROM Promotions WHERE promotion_id=?";
    String SELECT_ALL = "SELECT * from Promotions";
    String DELETE_ALL = "DELETE from Promotions";
    public IPromotionsRepository.StorageResult savePromotions(Promotions promotions) {

        try {
            PreparedStatement statement = databaseConfiguration.getDBConnection().prepareStatement(INSERT_PROMOTION);
            statement.setString(1,promotions.getPromotionId());
            statement.setString(2,promotions.getPromotionName());
            statement.setString(3,promotions.getPromotionStartDate());
            statement.setString(4,promotions.getPromotionEndDate());
            statement.setInt(5,promotions.getAmountOff());
            int result = statement.executeUpdate();
            if(result==1){
                return IPromotionsRepository.StorageResult.SUCCESS;
            }
            else {
                return IPromotionsRepository.StorageResult.FAILURE;
            }
        } catch (SQLException e) {
            return IPromotionsRepository.StorageResult.FAILURE;
        }
    }

    @Override
    public StorageResult updatePromotions(Promotions promotions) {
        try {
            PreparedStatement statement = databaseConfiguration.getDBConnection().prepareStatement(UPDATE_PROMOTION);
            statement.setString(1,promotions.getPromotionName());
            statement.setString(2,promotions.getPromotionStartDate());
            statement.setString(3,promotions.getPromotionEndDate());
            statement.setInt(4,promotions.getAmountOff());
            statement.executeUpdate();
            return IPromotionsRepository.StorageResult.SUCCESS;
        } catch (SQLException e) {
            return IPromotionsRepository.StorageResult.FAILURE;
        }
    }

    @Override
    public Promotions findById(String promotionId) {
        Promotions promotions = null;
        try {
            PreparedStatement statement = databaseConfiguration.getDBConnection().prepareStatement(FIND_PROMOTION_BY_ID);
            statement.setString(1,promotionId);
            ResultSet rs = statement.executeQuery();
            while (rs.next()){
                promotions = createPromotions(rs);
            }
            return promotions;
        } catch (SQLException e) {
            throw new RuntimeException(e);
        }
    }

    private Promotions createPromotions(ResultSet rs) throws SQLException {

        Promotions promotions= new Promotions();
        promotions.setPromotionName(rs.getString("promotion_name"));
        promotions.setPromotionId(rs.getString("promotion_id"));
        promotions.setPromotionStartDate(rs.getString("promotion_start_date"));
        promotions.setPromotionEndDate(rs.getString("promotion_end_date"));
        promotions.setAmountOff(rs.getInt("amount_off"));
        return promotions;
    }

    @Override
    public StorageResult deleteById(String promotion_id) {
        try {
            PreparedStatement statement = databaseConfiguration.getDBConnection().prepareStatement(DELETE_PROMOTION_BY_ID);
            statement.setString(1,promotion_id);
            int result = statement.executeUpdate();
            if(result == 1){
                return IPromotionsRepository.StorageResult.SUCCESS;
            }else{
                return IPromotionsRepository.StorageResult.FAILURE;
            }
        } catch (SQLException e) {
            return IPromotionsRepository.StorageResult.FAILURE;
        }
    }

    @Override
    public List<Promotions> findAll() {
        List<Promotions> promotions = new ArrayList<>();
        try {
            PreparedStatement statement = databaseConfiguration.getDBConnection().prepareStatement(SELECT_ALL);
            ResultSet rs = statement.executeQuery();
            while (rs.next()){
                promotions.add(createPromotions(rs));
            }
            return promotions;
        } catch (SQLException e) {
            throw new RuntimeException(e);
        }

    }

    @Override
    public int deleteAll() {
        try {
            PreparedStatement statement = databaseConfiguration.getDBConnection().prepareStatement(DELETE_ALL);
            return statement.executeUpdate();
        } catch (SQLException e) {
            return -1;
        }
    }

}
