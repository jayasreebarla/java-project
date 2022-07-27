package com.dal.drplus.repository.implementation;

import com.dal.drplus.model.IBuilder.ILabScheduleBuilder;
import com.dal.drplus.model.IEntity.ILabSchedule;
import com.dal.drplus.model.entity.LabSchedule;
import com.dal.drplus.model.factory.ModelFactory;
import com.dal.drplus.repository.configuration.DatabaseConfiguration;
import com.dal.drplus.repository.configuration.DatabaseConfigurationImpl;
import com.dal.drplus.repository.interfaces.ILabScheduleRepository;
import org.springframework.stereotype.Repository;

import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

@Repository
public class LabScheduleRepositoryImpl implements ILabScheduleRepository {

    String INSERT_LAB_SCHEDULE = "INSERT into Lab_schedule (slot_timing,slot_date,lab_id,status) VALUES(?,?,?,?)";
    String UPDATE_LAB_SCHEDULE = "UPDATE Lab_schedule SET slot_timing=?,slot_date=?,lab_id=?,status=? WHERE slot_id=?";
    String FIND_SCHEDULE_BY_LAB_ID = "SELECT * FROM Lab_schedule WHERE lab_id=?";
    String FIND_SCHEDULE_BY_SLOT_ID = "SELECT * FROM Lab_schedule WHERE slot_id=?";

    String LIST_UNBOOKED_SLOT_SCHEDULES_BY_LAB_ID = "SELECT * FROM Lab_schedule WHERE lab_id=? "+
            " and status=false and slot_date>sysdate()";
    String UPDATE_SLOT_STATUS = "Update Lab_schedule set status = ? where slot_id = ?";
    String DELETE_SCHEDULE_BY_LAB_ID = "DELETE FROM Lab_schedule WHERE lab_id=?";
    String DELETE_SCHEDULE_BY_SLOT_ID = "DELETE FROM Lab_schedule WHERE slot_id=?";
    String DELETE_ALL = "DELETE FROM Lab_schedule";

    String FIND_ALL = "SELECT * FROM Lab_schedule";

    String GET_SLOT_IDS = "SELECT slot_id FROM Lab_schedule";

    DatabaseConfiguration databaseConfiguration;

    public LabScheduleRepositoryImpl() {
        this.databaseConfiguration = dbConfig();
    }

    private DatabaseConfiguration dbConfig(){
        return new DatabaseConfigurationImpl();
    }

    @Override
    public StorageResult saveLabSchedule(ILabSchedule labSchedule) {
        try {
            PreparedStatement statement = databaseConfiguration.getDBConnection().prepareStatement(INSERT_LAB_SCHEDULE);
            statement.setString(2, labSchedule.getSlotDate());
            statement.setString(1, labSchedule.getSlotTiming());
            statement.setString(3,labSchedule.getLabId());
            statement.setBoolean(4,labSchedule.getStatus());
            statement.executeUpdate();
            return StorageResult.SUCCESS;
        } catch (SQLException e) {
            return StorageResult.FAILURE;
        }
    }

    @Override
    public StorageResult updateLabSchedule(LabSchedule labSchedule) {
        PreparedStatement statement = null;
        try {
            statement = databaseConfiguration.getDBConnection().prepareStatement(UPDATE_LAB_SCHEDULE);
            statement.setString(1,labSchedule.getSlotTiming());
            statement.setString(2, labSchedule.getSlotDate());
            statement.setString(3,labSchedule.getLabId());
            statement.setBoolean(4,labSchedule.getStatus());
            statement.setInt(5,labSchedule.getSlotId());
            int result = statement.executeUpdate();
            if(result == 1){
                return StorageResult.SUCCESS;
            } else {
                return StorageResult.FAILURE;
            }
        } catch (SQLException e) {
            throw new RuntimeException(e);
        }
    }

    @Override
    public List<LabSchedule> findScheduleByLabID(String id) {
        List<LabSchedule> schedules = new ArrayList<>();
        try {
            PreparedStatement statement = databaseConfiguration.getDBConnection().prepareStatement(FIND_SCHEDULE_BY_LAB_ID);
            statement.setString(1,id);
            ResultSet rs = statement.executeQuery();
            while (rs.next()){
                schedules.add(createLabSchedule(rs));
            }
            return schedules;
        } catch (SQLException e) {
            throw new RuntimeException(e);
        }
    }

    @Override
    public List<LabSchedule> findAll() {
        List<LabSchedule> labScheduleList = new ArrayList<>();
        try {
            PreparedStatement statement = databaseConfiguration.getDBConnection().prepareStatement(FIND_ALL);
            ResultSet rs = statement.executeQuery();
            while (rs.next()){
                labScheduleList.add(createLabSchedule(rs));
            }
            return labScheduleList;
        } catch (SQLException e) {
            throw new RuntimeException(e);
        }
    }

    @Override
    public ILabSchedule findScheduleBySlotID(String id) {
        LabSchedule labSchedule=null;
        try {
            PreparedStatement statement = databaseConfiguration.getDBConnection().prepareStatement(FIND_SCHEDULE_BY_SLOT_ID);
            statement.setString(1,id);
            ResultSet rs = statement.executeQuery();
            while (rs.next()){
                labSchedule=createLabSchedule(rs);
            }
            return labSchedule;
        } catch (SQLException e) {
            throw new RuntimeException(e);
        }
    }

    @Override
    public List<LabSchedule> listUnbookedSlotsbyLabId(String labId) {
        List<LabSchedule> labScheduleList = new ArrayList<>();
        try {
            PreparedStatement statement = databaseConfiguration.getDBConnection().prepareStatement(LIST_UNBOOKED_SLOT_SCHEDULES_BY_LAB_ID);
            statement.setString(1,labId);
            ResultSet rs = statement.executeQuery();
            while (rs.next()){
                labScheduleList.add(createLabSchedule(rs));
            }
            return labScheduleList;
        } catch (SQLException e) {
            throw new RuntimeException(e);
        }
    }

    @Override
    public StorageResult updateSlotStatus(boolean status, int slotId) {

        PreparedStatement statement = null;
        try {
            statement = databaseConfiguration.getDBConnection().prepareStatement(UPDATE_SLOT_STATUS);
            statement.setBoolean(1,status);
            statement.setInt(2,slotId);
            int result = statement.executeUpdate();
            if(result == 1){
                return StorageResult.SUCCESS;
            }
            return StorageResult.FAILURE;

        } catch (SQLException e) {
            return StorageResult.FAILURE;
        }
    }

    @Override
    public StorageResult deleteScheduleByLabID(String id) {
        PreparedStatement statement = null;
        try {
            statement = databaseConfiguration.getDBConnection().prepareStatement(DELETE_SCHEDULE_BY_LAB_ID);
            statement.setString(1,id);
            if(statement.executeUpdate() == 1){
                return StorageResult.SUCCESS;
            }
            return StorageResult.FAILURE;

        } catch (SQLException e) {
            return StorageResult.FAILURE;
        }
    }

    @Override
    public StorageResult deleteScheduleBySlotID(int id) {
        PreparedStatement statement = null;
        try {
            statement = databaseConfiguration.getDBConnection().prepareStatement(DELETE_SCHEDULE_BY_SLOT_ID);
            statement.setInt(1,id);
            statement.executeUpdate();
            return StorageResult.SUCCESS;
        } catch (SQLException e) {
        return StorageResult.FAILURE;
        }
    }

    @Override
    public int deleteAllSchedules() {
    try {
           PreparedStatement statement = databaseConfiguration.getDBConnection().prepareStatement(DELETE_ALL);
           return statement.executeUpdate();
            } catch (SQLException e) {
                return -1;
        }
    }

    private LabSchedule createLabSchedule(ResultSet rs) throws SQLException {

        LabSchedule labSchedule = null;
        ILabScheduleBuilder labScheduleBuilder = ModelFactory.instance().createLabScheduleBuilder();

        labSchedule = labScheduleBuilder
                .addSlotId(rs.getInt("slot_id"))
                .addSlotTiming(rs.getString("slot_timing"))
                .addSlotDate(rs.getString("slot_date"))
                .addLabId(rs.getString("lab_id"))
                .addStatus(rs.getBoolean("status")).build();

        return labSchedule;
    }
}
