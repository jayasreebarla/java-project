package com.dal.drplus.repository.implementation;

import com.dal.drplus.model.IBuilder.IWalletBuilder;
import com.dal.drplus.model.IEntity.IWallet;
import com.dal.drplus.model.factory.ModelFactory;
import com.dal.drplus.repository.configuration.DatabaseConfiguration;
import com.dal.drplus.repository.configuration.DatabaseConfigurationImpl;
import com.dal.drplus.repository.interfaces.IWalletRepository;
import org.springframework.stereotype.Repository;

import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;

@Repository
public class WalletRepositoryImpl implements IWalletRepository {

    String INSERT_INTO_WALLET= "INSERT into Wallet (wallet_id,amount) VALUES(?,?)";
    String UPDATE_WALLET = "UPDATE Wallet SET amount=? WHERE wallet_id=?";
    String SELECT_WALLET = "SELECT * FROM Wallet WHERE wallet_id=?";
    DatabaseConfiguration databaseConfiguration;

    private DatabaseConfiguration dbConfig(){
        return new DatabaseConfigurationImpl();
    }

    public WalletRepositoryImpl() {
        this.databaseConfiguration = dbConfig();
    }

    @Override
    public StorageResult InsertIntoWallet(double amount, String wallet_id) {
        try {
            PreparedStatement statement = databaseConfiguration.getDBConnection().prepareStatement(INSERT_INTO_WALLET);
            statement.setString(1,wallet_id);
            statement.setDouble(2,amount);
            int result = statement.executeUpdate();
            if(result ==1){
                return StorageResult.SUCCESS;
            }else{
                return StorageResult.FAILURE;
            }
        } catch (SQLException e) {
            return StorageResult.FAILURE;
        }
    }

    @Override
    public StorageResult UpdateIntoWallet(double amount, String wallet_id) {
        try {
            PreparedStatement statement = databaseConfiguration.getDBConnection().prepareStatement(UPDATE_WALLET);
            statement.setDouble(1,amount);
            statement.setString(2,wallet_id);
            int result = statement.executeUpdate();
            if(result ==1){
                return StorageResult.SUCCESS;
            }else{
                return StorageResult.FAILURE;
            }
        } catch (SQLException e) {
            return StorageResult.FAILURE;
        }
    }

    @Override
    public IWallet getWalletBalanceByWalletId(String wallet_id) {

        IWallet wallet = null;
        try {
            PreparedStatement statement = databaseConfiguration.getDBConnection().prepareStatement(SELECT_WALLET);
            statement.setString(1,wallet_id);
            ResultSet rs = statement.executeQuery();
            while (rs.next()){
                IWalletBuilder builder = ModelFactory.instance().createWalletBuilder();
                wallet=builder.addWalletId(wallet_id).addWalletAmount(rs.getDouble("amount")).build();
            }

        } catch (SQLException e) {
            e.printStackTrace();
        }
        return wallet;
    }
}
