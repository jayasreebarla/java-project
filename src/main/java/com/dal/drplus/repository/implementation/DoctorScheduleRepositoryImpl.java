package com.dal.drplus.repository.implementation;

import com.dal.drplus.model.DoctorSchedule;
import com.dal.drplus.repository.configuration.DatabaseConfiguration;
import com.dal.drplus.repository.configuration.DatabaseConfigurationImpl;
import com.dal.drplus.repository.interfaces.IDoctorScheduleRepository;
import org.springframework.stereotype.Repository;

import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

@Repository
public class DoctorScheduleRepositoryImpl implements IDoctorScheduleRepository {

    String GET_SLOT_IDS = "SELECT slot_id FROM Doc_schedule WHERE slot_date=?";
    //String GET_SLOT_IDS = "SELECT slot_id FROM Doc_schedule";
    DatabaseConfiguration databaseConfiguration;
    private DatabaseConfiguration DbConfig(){
        return new DatabaseConfigurationImpl();
    }

    public DoctorScheduleRepositoryImpl() {
        this.databaseConfiguration = DbConfig();
    }

    @Override
    public StorageResult saveDoctorSchedule(DoctorSchedule doctorSchedule) {

        PreparedStatement statement = null;
        try {
            statement = databaseConfiguration.getDBConnection().prepareStatement("INSERT into Doc_schedule( slot_timing, slot_date, doctor_id, status) VALUES (?,?,?,?)");
//            statement.setInt(1,doctorSchedule.getSlotId());
            statement.setString(1, doctorSchedule.getSlotTiming());
            statement.setString(2, doctorSchedule.getSlotDate());
            statement.setString(3,doctorSchedule.getDoctorId());
            statement.setBoolean(4, doctorSchedule.getStatus());
            statement.executeUpdate();

            return StorageResult.SUCCESS;
        } catch (SQLException e) {
//            throw new RuntimeException(e);
        return StorageResult.FAILURE;
        }

    }

    @Override
    public int updateDoctorSchedule(DoctorSchedule doctorSchedule) {

        PreparedStatement statement = null;
        try {
            statement = databaseConfiguration.getDBConnection().prepareStatement("UPDATE Doc_schedule set slot_timing=?, slot_date=?,doctor_id=?, status=? WHERE slot_id=?");
            statement.setString(1, doctorSchedule.getSlotTiming());
            statement.setString(2,doctorSchedule.getSlotDate());
            statement.setString(3, doctorSchedule.getDoctorId());
            statement.setInt(4,doctorSchedule.getSlotId());
            return statement.executeUpdate();

        } catch (SQLException e) {
            throw new RuntimeException(e);
        }

    }

    @Override
    public List<DoctorSchedule> findScheduleByDoctorID(String id) {
        System.out.println("Doctor"+id);
        List<DoctorSchedule> doctorSchedules = new ArrayList<>();
        PreparedStatement statement = null;
        try {
            statement = databaseConfiguration.getDBConnection().prepareStatement("Select * from Doc_schedule where doctor_id = ?");
            statement.setString(1,id);

            ResultSet rs = statement.executeQuery();
            while (rs.next()){
                DoctorSchedule doctorSchedule = new DoctorSchedule();
                doctorSchedule.setSlotId(rs.getInt("slot_id"));
                doctorSchedule.setSlotTiming(rs.getString("slot_timing"));
                doctorSchedule.setSlotDate(rs.getString("slot_date"));
                doctorSchedule.setDoctorId(rs.getString("doctor_id"));
                doctorSchedule.setStatus(rs.getBoolean("status"));
                doctorSchedules.add(doctorSchedule);
            }
        } catch (SQLException e) {
            throw new RuntimeException(e);
        }

        return doctorSchedules;
    }

    @Override
    public List<DoctorSchedule> findAll() {
        List<DoctorSchedule> doctorSchedules = new ArrayList<>();
        PreparedStatement statement = null;
        try {
            statement = databaseConfiguration.getDBConnection().prepareStatement("Select * from Doc_schedule");

            ResultSet rs = statement.executeQuery();
            while (rs.next()){
                DoctorSchedule doctorSchedule = new DoctorSchedule();
                doctorSchedule.setSlotId(rs.getInt("slot_id"));
                doctorSchedule.setSlotTiming(rs.getString("slot_timing"));
                doctorSchedule.setSlotDate(rs.getString("slot_date"));
                doctorSchedule.setDoctorId(rs.getString("doctor_id"));
                doctorSchedule.setStatus(rs.getBoolean("status"));
                doctorSchedules.add(doctorSchedule);
            }
        } catch (SQLException e) {
            throw new RuntimeException(e);
        }

        return doctorSchedules;
    }

    @Override
    public DoctorSchedule findScheduleBySlotID(int id) {

        DoctorSchedule doctorSchedule = new DoctorSchedule();
        PreparedStatement statement = null;
        try {
            statement = databaseConfiguration.getDBConnection().prepareStatement("Select * from Doc_schedule where slot_id = ?");
            statement.setInt(1,id);
            ResultSet rs = statement.executeQuery();
            doctorSchedule.setSlotId(rs.getInt("slot_id"));
            doctorSchedule.setSlotTiming(rs.getString("slot_timing"));
            doctorSchedule.setSlotDate(rs.getString("slot_date"));
            doctorSchedule.setDoctorId(rs.getString("doctor_id"));
            doctorSchedule.setStatus(rs.getBoolean("status"));
        } catch (SQLException e) {
            throw new RuntimeException(e);
        }
        return doctorSchedule;
    }

    @Override
    public StorageResult deleteScheduleByDoctorID(String id) {

        PreparedStatement statement = null;
        try {
            statement = databaseConfiguration.getDBConnection().prepareStatement("Delete from Doc_schedule where doctor_id = ?");
            statement.setString(1,id);
            if(statement.executeUpdate() == 1){
                return StorageResult.SUCCESS;
            }
            return StorageResult.FAILURE;

        } catch (SQLException e) {
//            throw new RuntimeException(e);
        return StorageResult.FAILURE;
        }
    }

    @Override
    public StorageResult deleteScheduleBySlotID(int id) {

        PreparedStatement statement = null;
        try {
            statement = databaseConfiguration.getDBConnection().prepareStatement("Delete from Doc_schedule where slot_id = ?");
            statement.setInt(1,id);
            statement.executeUpdate();
            return StorageResult.SUCCESS;
        } catch (SQLException e) {
//            throw new RuntimeException(e);
            return StorageResult.FAILURE;
        }
    }

    @Override
    public List<DoctorSchedule> listUnbookedSchedulesbyDoctorID(String doctorId) {
        List<DoctorSchedule> doctorScheduleList = new ArrayList<>();
        PreparedStatement statement = null;
        String SELECT_UNBOOKED_SLOTS_BY_DOCTOR_ID = "Select * from Doc_schedule where doctor_id = ? "+
                "and status=false and slot_date>sysdate()";

        try {
            statement = databaseConfiguration.getDBConnection().prepareStatement(SELECT_UNBOOKED_SLOTS_BY_DOCTOR_ID);
            statement.setString(1, doctorId);
            ResultSet rs = statement.executeQuery();
            while(rs.next()) {
                DoctorSchedule doctorSchedule = new DoctorSchedule();
                doctorSchedule.setSlotId(rs.getInt("slot_id"));
                doctorSchedule.setSlotTiming(rs.getString("slot_timing"));
                doctorSchedule.setSlotDate(rs.getString("slot_date"));
                doctorSchedule.setDoctorId(rs.getString("doctor_id"));
                doctorSchedule.setStatus(rs.getBoolean("status"));
                doctorScheduleList.add(doctorSchedule);
            }
        } catch (SQLException e) {
            throw new RuntimeException(e);
        }
        return doctorScheduleList;
    }

    @Override
    public StorageResult updateSlotStatus(boolean status, int slotId) {
        PreparedStatement statement = null;
        try {
            statement = databaseConfiguration.getDBConnection().prepareStatement("Update Doc_schedule set status = ? where slot_id = ?");
            statement.setBoolean(1,true);
            statement.setInt(2,slotId);
            int result = statement.executeUpdate();
            if(result == 1) {
                return StorageResult.SUCCESS;
            } else {
                return StorageResult.FAILURE;
            }
        } catch (SQLException e) {
//            throw new RuntimeException(e);
            return StorageResult.FAILURE;
        }
    }

    @Override
    public StorageResult deleteAllSchedules() {

        PreparedStatement statement = null;
        try {
            statement = databaseConfiguration.getDBConnection().prepareStatement("Delete from Doc_schedule");
            int result =  statement.executeUpdate();
            if(result == 1){
                return IDoctorScheduleRepository.StorageResult.SUCCESS;
            } else {
                return IDoctorScheduleRepository.StorageResult.FAILURE;
            }
        } catch (SQLException e) {
            throw new RuntimeException(e);
        }
    }

    @Override
    public List<Integer> getAllSlotIds(String slotDate) {
        List<Integer> slot_id_list;
        try {
            slot_id_list = new ArrayList<>();
            PreparedStatement statement = databaseConfiguration.getDBConnection().prepareStatement(GET_SLOT_IDS);
            statement.setString(1,slotDate);
            ResultSet rs = statement.executeQuery();
            while (rs.next()){
                slot_id_list.add(rs.getInt("slot_id"));
            }
        } catch (SQLException e) {
            throw new RuntimeException(e);
        }
        return slot_id_list;
    }


}
