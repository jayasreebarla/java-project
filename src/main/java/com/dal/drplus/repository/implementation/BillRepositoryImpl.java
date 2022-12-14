package com.dal.drplus.repository.implementation;

import com.dal.drplus.model.IBuilder.IBillingBuilder;
import com.dal.drplus.model.IEntity.IBilling;
import com.dal.drplus.model.factory.ModelFactory;
import com.dal.drplus.repository.configuration.DatabaseConfiguration;
import com.dal.drplus.repository.configuration.DatabaseConfigurationImpl;
import com.dal.drplus.repository.interfaces.IBillRepository;
import org.springframework.stereotype.Repository;

import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.time.LocalDate;
import java.time.format.DateTimeFormatter;

@Repository
public class BillRepositoryImpl implements IBillRepository {
    String INSERT_BILL = "INSERT INTO Bill (bill_date,bill_amount,bill_description) VALUES(?,?,?)";
    String GET_AMOUNT_BILL = "SELECT bill_amount FROM Bill where bill_id=?";
    String UPADTE_BILL = "UPDATE Bill SET bill_amount = ? where bill_id=?";
    String GET_BILL = "SELECT * FROM Bill where  bill_id=?";
    DatabaseConfiguration databaseConfiguration;

    private DatabaseConfiguration dbConfig(){
        return new DatabaseConfigurationImpl();
    }

    public BillRepositoryImpl() {
        this.databaseConfiguration = dbConfig();
    }
    @Override
    public int insertBill(double amount,String description) {
        int id = 0;
        try {
            PreparedStatement statement = databaseConfiguration.getDBConnection().prepareStatement(INSERT_BILL, Statement.RETURN_GENERATED_KEYS);
            statement.setString(1, LocalDate.now().format(DateTimeFormatter.ISO_DATE));
            statement.setDouble(2,amount);
            statement.setString(3,description);
            statement.execute();
            ResultSet rs = statement.getGeneratedKeys();
            while (rs.next()){
               id = rs.getInt(1);
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }

        return id;
    }

    @Override
    public double getBillAmount(int billId) {
        double amount = 0;
        try {
            PreparedStatement statement = databaseConfiguration.getDBConnection().prepareStatement(GET_AMOUNT_BILL);
            statement.setInt(1,billId);
            ResultSet rs = statement.executeQuery();
            while (rs.next()){
                amount = rs.getDouble("bill_amount");
            }
        } catch (SQLException e) {
            throw new RuntimeException(e);
        }
        return amount;
    }

    @Override
    public StorageResult UpdateBillAmount(int billId,double amount){
        try {
            PreparedStatement statement = databaseConfiguration.getDBConnection().prepareStatement(UPADTE_BILL);
            statement.setDouble(1,amount);
            statement.setInt(2,billId);
            if(statement.executeUpdate() == 1){
                return StorageResult.SUCCESS;
            }else{
                return StorageResult.FAILURE;
            }
        } catch (SQLException e) {
            return StorageResult.FAILURE;
        }
    }

    @Override
    public IBilling getBill(int billId) {
        IBilling bill = null;
        try {
            PreparedStatement statement = databaseConfiguration.getDBConnection().prepareStatement(GET_BILL);
            statement.setInt(1,billId);
            ResultSet rs = statement.executeQuery();
            IBillingBuilder builder = ModelFactory.instance().createBillingBuilder();
            while (rs.next()){
                bill = builder.addBillId(rs.getInt("bill_id")).addBillAmount(rs.getDouble("bill_amount")).addBillDescription(rs.getString("bill_description")).build();
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return bill;
    }
}
