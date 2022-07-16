package com.dal.drplus.repository.implementation;

import com.dal.drplus.model.Appointment;
import com.dal.drplus.repository.configuration.DatabaseConfiguration;
import com.dal.drplus.repository.configuration.DatabaseConfigurationImpl;
import com.dal.drplus.repository.interfaces.IAppointmentRepository;
import org.springframework.stereotype.Repository;

import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

@Repository
public class AppointmentRepositoryImpl implements IAppointmentRepository{

    String INSERT_APPOINTMENT= "INSERT into Appointment " +
            "(appointment_id, slot_id, appointment_type, appointment_description, appointment_fee, " +
            " patient_id, doctor_id, bill_id, lab_id) VALUES (?,?,?,?,?,?,?,?,?)";

    String UPDATE_APPOINTMENT = "UPDATE Appointment SET slot_id=?, appointment_type=?, "+
        " appointment_description=?, appointment_fee=?, patient_id=?, doctor_id=?, "+
            " bill_id=?, lab_id=? where appointment_id=? ";

    String SELECT_APPOINTMENT_BY_ID = "SELECT appointment_id, appointment_description, appointment_type, "+
            " appointment_fee, patient_id, bill_id, lab_id, doctor_id, "+
            " case when appointment_type = 'DOCTOR' then (select slot_date from Doc_schedule D where D.slot_id=A.slot_id )"+
            " else (select slot_date from Lab_schedule L where L.slot_id=A.slot_id)  end as slot_date ,"+
            " case when appointment_type = 'DOCTOR' then (select slot_timing from Doc_schedule D where D.slot_id=A.slot_id)"+
            " else (select slot_timing from Lab_schedule L where L.slot_id=A.slot_id)  end as slot_timing"+
            " FROM Appointment A WHERE appointment_id=?";

    String SELECT_APPOINTMENT_BY_PATIENT_ID = "SELECT appointment_id, appointment_description, appointment_type, "+
            " appointment_fee, patient_id, bill_id, lab_id, doctor_id, slot_id, "+
            " case when appointment_type = 'DOCTOR' then (select slot_date from Doc_schedule D where D.slot_id=A.slot_id)"+
            " else (select slot_date from Lab_schedule L where L.slot_id=A.slot_id)  end as slot_date ,"+
            " case when appointment_type = 'DOCTOR' then (select slot_timing from Doc_schedule D where D.slot_id=A.slot_id)"+
            " else (select slot_timing from Lab_schedule L where L.slot_id=A.slot_id)  end as slot_timing,"+
            " case when appointment_type = 'DOCTOR' then doctor_id"+
            " else lab_id  end as ID "+
            " FROM Appointment A WHERE A.patient_id=?";

    String SELECT_APPOINTMENT_BY_DOCTOR_ID = "SELECT appointment_id, appointment_description, appointment_type, "+
            " appointment_fee, patient_id, bill_id, lab_id, doctor_id, slot_timing, slot_date  FROM Appointment, Doc_schedule"+
            " WHERE Appointment.slot_id = Doc_schedule.slot_id and doctor_id=?";

    String SELECT_APPOINTMENT_BY_LAB_ID = "SELECT appointment_id, appointment_description, appointment_type, "+
            " appointment_fee, patient_id, bill_id, lab_id, doctor_id, slot_timing, slot_date  FROM Appointment, Lab_schedule"+
            " WHERE Appointment.slot_id = Lab_schedule.slot_id and lab_id=?";

    String SELECT_APPOINTMENT_BY_PATIENT_ID_N_DATE = "SELECT appointment_id, appointment_description, appointment_type, "+
            " appointment_fee, patient_id, bill_id, lab_id, doctor_id, "+
            " case when appointment_type = 'DOCTOR' then "+
            "(select slot_date from Doc_schedule D where D.slot_id=A.slot_id and slot_date=?) "+
            " else (select slot_date from Lab_schedule L where L.slot_id=A.slot_id and slot_date=?)  end as slot_date ,"+
            " case when appointment_type = 'DOCTOR' then "+
            "(select slot_timing from Doc_schedule D where D.slot_id=A.slot_id and slot_date=?) "+
            " else (select slot_timing from Lab_schedule L where L.slot_id=A.slot_id and slot_date=?)  end as slot_timing"+
            " FROM Appointment A WHERE patient_id=? ";

    String SELECT_APPOINTMENT_BY_DOCTOR_ID_N_DATE = "SELECT appointment_id, appointment_description, appointment_type, "+
            " appointment_fee, patient_id, bill_id, lab_id, doctor_id, slot_timing, slot_date  FROM Appointment, Doc_schedule"+
            " WHERE Appointment.slot_id = Doc_schedule.slot_id and doctor_id=? and slot_date=?";

    String SELECT_APPOINTMENT_BY_LAB_ID_N_DATE = "SELECT appointment_id, appointment_description, appointment_type, "+
            " appointment_fee, patient_id, bill_id, lab_id, doctor_id, slot_timing, slot_date  FROM Appointment, Lab_schedule"+
            " WHERE Appointment.slot_id = Lab_schedule.slot_id and lab_id=? and slot_date=?";

    String SELECT_APPOINTMENT_BY_DATE = "SELECT appointment_id, appointment_description, appointment_type, "+
            " appointment_fee, patient_id, bill_id, lab_id, doctor_id, "+
            " case when appointment_type = 'DOCTOR' then "+
            "(select slot_date from Doc_schedule D where D.slot_id=A.slot_id and slot_date=?)"+
            " else (select slot_date from Lab_schedule L where L.slot_id=A.slot_id and slot_date=?)  end as slot_date,"+
            " case when appointment_type = 'DOCTOR' then "+
            "(select slot_timing from Doc_schedule D where D.slot_id=A.slot_id and slot_date=?)"+
            " else (select slot_timing from Lab_schedule L where L.slot_id=A.slot_id and slot_date=?)  end as slot_timing"+
            " FROM Appointment A";

    String DELETE_APPOINTMENT_BY_ID = "DELETE from Appointment where appointment_id=? ";

    String SELECT_APPOINTMENT_CONFLICT = "select nvl(d.slot_date,l.slot_date), nvl(d.slot_timing,l.slot_timing) "+
            " from Appointment a, Doc_schedule d, Lab_schedule l" +
            " where (a.slot_id=d.slot_id and a.appointment_type='DOCTOR' and d.slot_date=? and d.slot_timing=?) " +
            " or (a.slot_id=l.slot_id and a.appointment_type='LAB' and l.slot_date=? and l.slot_timing=?)" +
            " and patient_id=?";

    String SELECT_ALL = "SELECT * from Appointment";
    String DELETE_ALL = "DELETE from Appointment";

    String SELECT_PATIENT_BY_SLOT_ID_DOCTOR = "SELECT patient_id from Appointment WHERE slot_id=? and appointment_type='DOCTOR' ";

    DatabaseConfiguration databaseConfiguration;

    public AppointmentRepositoryImpl() {
        this.databaseConfiguration = dbConfig();
    }

    private DatabaseConfiguration dbConfig(){
        return new DatabaseConfigurationImpl();
    }

    @Override
    public StorageResult saveAppointment(Appointment appointment) {

        try {
            PreparedStatement statement = databaseConfiguration.getDBConnection().prepareStatement(INSERT_APPOINTMENT);
            statement.setInt(1,appointment.getAppointmentId());
            //slot id logic to be added
            statement.setInt(2,appointment.getSlotId());
            statement.setString(3,appointment.getAppointmentType());
            statement.setString(4,appointment.getAppointmentDescription());
            statement.setDouble(5,appointment.getAppointmentFee());
            statement.setString(6,appointment.getPatientId());
            statement.setString(7,appointment.getDoctorId());
            statement.setInt(8,appointment.getBillId());
            statement.setString(9,appointment.getLabId());
            statement.executeUpdate();
            return IAppointmentRepository.StorageResult.SUCCESS;
        } catch (SQLException e) {
           // throw new RuntimeException(e);
            return IAppointmentRepository.StorageResult.FAILURE;
        }
    }

    @Override
    public StorageResult updateAppointment(Appointment appointment) {
        try {
            PreparedStatement statement = databaseConfiguration.getDBConnection().prepareStatement(UPDATE_APPOINTMENT);
            statement.setInt(9,appointment.getAppointmentId());
            //slot id logic to be added
            statement.setInt(1,appointment.getSlotId());
            statement.setString(2,appointment.getAppointmentType());
            statement.setString(3,appointment.getAppointmentDescription());
            statement.setDouble(4,appointment.getAppointmentFee());
            statement.setString(5,appointment.getPatientId());
            statement.setString(6,appointment.getDoctorId());
            statement.setInt(7,appointment.getBillId());
            statement.setString(8,appointment.getLabId());
            statement.executeUpdate();
            return IAppointmentRepository.StorageResult.SUCCESS;
        } catch (SQLException e) {
            // throw new RuntimeException(e);
            return IAppointmentRepository.StorageResult.FAILURE;
        }
    }

    @Override
    public Appointment findAppointmentById(String appointmentId) {
        Appointment appointment = null;
        try {
            PreparedStatement statement = databaseConfiguration.getDBConnection().prepareStatement(SELECT_APPOINTMENT_BY_ID);
            statement.setString(1,appointmentId);
            ResultSet rs = statement.executeQuery();
            while (rs.next()){
                appointment = createAppointment(rs);
            }
            return appointment;
        } catch (SQLException e) {
            throw new RuntimeException(e);
        }
    }

    @Override
    public List<Appointment> findAppointmentByDoctorId(String doctorId) {
        List<Appointment> appointmentList = new ArrayList();
        try {
            PreparedStatement statement = databaseConfiguration.getDBConnection().prepareStatement(SELECT_APPOINTMENT_BY_DOCTOR_ID);
            statement.setString(1,doctorId);
            ResultSet rs = statement.executeQuery();
            while (rs.next()){
                Appointment appointment = null;
                appointment = createAppointment(rs);
                appointmentList.add(appointment);
            }
            return appointmentList;
        } catch (SQLException e) {
            throw new RuntimeException(e);
        }
    }

    @Override
    public List<Appointment> findAppointmentByLabId(String labId) {
        List<Appointment> appointmentList = new ArrayList();
        try {
            PreparedStatement statement = databaseConfiguration.getDBConnection().prepareStatement(SELECT_APPOINTMENT_BY_LAB_ID);
            statement.setString(1,labId);
            ResultSet rs = statement.executeQuery();
            while (rs.next()){
                Appointment appointment = null;
                appointment = createAppointment(rs);
                appointmentList.add(appointment);
            }
            return appointmentList;
        } catch (SQLException e) {
            throw new RuntimeException(e);
        }
    }

    @Override
    public List<Appointment> findAppointmentByPatientId(String patientId) {
        List<Appointment> appointmentList = new ArrayList();
        try {
            PreparedStatement statement = databaseConfiguration.getDBConnection().prepareStatement(SELECT_APPOINTMENT_BY_PATIENT_ID);
            statement.setString(1,patientId);
            ResultSet rs = statement.executeQuery();
            while (rs.next()){
                Appointment appointment = null;
                appointment = createAppointment(rs);
                appointmentList.add(appointment);
            }
            return appointmentList;
        } catch (SQLException e) {
            throw new RuntimeException(e);
        }
    }

    @Override
    public List<Appointment> findAppointmentByDoctorIdNDate(String doctorId, String date) {
        List<Appointment> appointmentList = new ArrayList();
        try {
            PreparedStatement statement = databaseConfiguration.getDBConnection().prepareStatement(SELECT_APPOINTMENT_BY_DOCTOR_ID_N_DATE);
            statement.setString(1,doctorId);
            statement.setString(2,date);
            ResultSet rs = statement.executeQuery();
            while (rs.next()){
                Appointment appointment = null;
                appointment = createAppointment(rs);
                appointmentList.add(appointment);
            }
            return appointmentList;
        } catch (SQLException e) {
            throw new RuntimeException(e);
        }
    }

    @Override
    public List<Appointment> findAppointmentByPatientIdNDate(String patientId, String date) {
        List<Appointment> appointmentList = new ArrayList();
        try {
            PreparedStatement statement = databaseConfiguration.getDBConnection().prepareStatement(SELECT_APPOINTMENT_BY_PATIENT_ID_N_DATE);
            statement.setString(1,date);
            statement.setString(2,date);
            statement.setString(3,date);
            statement.setString(4,date);
            statement.setString(5,patientId);

            ResultSet rs = statement.executeQuery();
            while (rs.next()){
                Appointment appointment = null;
                appointment = createAppointment(rs);
                appointmentList.add(appointment);
            }
            return appointmentList;
        } catch (SQLException e) {
            throw new RuntimeException(e);
        }
    }

    @Override
    public List<Appointment> findAppointmentByLabIdNDate(String labId, String date) {
        List<Appointment> appointmentList = new ArrayList();
        try {
            PreparedStatement statement = databaseConfiguration.getDBConnection().prepareStatement(SELECT_APPOINTMENT_BY_LAB_ID_N_DATE);
            statement.setString(1,labId);
            statement.setString(2,date);
            ResultSet rs = statement.executeQuery();
            while (rs.next()){
                Appointment appointment = null;
                appointment = createAppointment(rs);
                appointmentList.add(appointment);
            }
            return appointmentList;
        } catch (SQLException e) {
            throw new RuntimeException(e);
        }
    }

    @Override
    public List<Appointment> findAppointmentByDate(String date) {
        List<Appointment> appointmentList = new ArrayList();
        try {
            PreparedStatement statement = databaseConfiguration.getDBConnection().prepareStatement(SELECT_APPOINTMENT_BY_DATE);
            statement.setString(1,date);
            statement.setString(2,date);
            statement.setString(3,date);
            statement.setString(4,date);
            ResultSet rs = statement.executeQuery();
            while (rs.next()){
                Appointment appointment = null;
                appointment = createAppointment(rs);
                appointmentList.add(appointment);
            }
            return appointmentList;
        } catch (SQLException e) {
            throw new RuntimeException(e);
        }
    }

    @Override
    public StorageResult deleteAppointmentById(int appointmentId) {
        try {
            PreparedStatement statement = databaseConfiguration.getDBConnection().prepareStatement(DELETE_APPOINTMENT_BY_ID);
            statement.setInt(1,appointmentId);
            statement.executeUpdate();
            return IAppointmentRepository.StorageResult.SUCCESS;
        } catch (SQLException e) {
            // throw new RuntimeException(e);
            return IAppointmentRepository.StorageResult.FAILURE;
        }
    }

    @Override
    public List<Appointment> findAll() {
        List<Appointment> appointmentList = new ArrayList();
        try {
            PreparedStatement statement = databaseConfiguration.getDBConnection().prepareStatement(SELECT_ALL);
            ResultSet rs = statement.executeQuery();
            while (rs.next()){
                Appointment appointment = null;
                appointment = createAppointment(rs);
                appointmentList.add(appointment);
            }
            return appointmentList;
        } catch (SQLException e) {
            throw new RuntimeException(e);
        }
    }

    @Override
    public StorageResult deleteAll() {
        try {
            PreparedStatement statement = databaseConfiguration.getDBConnection().prepareStatement(DELETE_ALL);
            statement.executeUpdate();
            return IAppointmentRepository.StorageResult.SUCCESS;
        } catch (SQLException e) {
            // throw new RuntimeException(e);
            return IAppointmentRepository.StorageResult.FAILURE;
        }
    }

    @Override
    public StorageResult isAppointmentConflict(String slotDate, String slotTime, String patientId) {
        String slotDateDB="";
        String slotTimeDB="";
        try {
            PreparedStatement statement = databaseConfiguration.getDBConnection().prepareStatement(SELECT_APPOINTMENT_CONFLICT);
            statement.setString(1,slotDate);
            statement.setString(2,slotTime);
            statement.setString(3,slotDate);
            statement.setString(4,slotTime);
            statement.setString(5,patientId);

            ResultSet rs = statement.executeQuery();
            while (rs.next()){
                slotDateDB = rs.getString(1);
                slotTimeDB = rs.getString(2);
            }
            if((slotDateDB!=slotDate) && (slotTimeDB!=slotTime)) {
                return IAppointmentRepository.StorageResult.FAILURE;
            }
            return IAppointmentRepository.StorageResult.SUCCESS;
        } catch (SQLException e) {
            throw new RuntimeException(e);
        }
    }

    @Override
    public String getPatientIdBySlotId(int slotId) {
        String patientId = null;
        try {
            PreparedStatement statement = databaseConfiguration.getDBConnection().prepareStatement(SELECT_PATIENT_BY_SLOT_ID_DOCTOR);
            statement.setInt(1,slotId);
            ResultSet rs = statement.executeQuery();
            while (rs.next()){
                patientId = rs.getString("patient_id");
            }
        } catch (SQLException e) {
            throw new RuntimeException(e);
        }
        return patientId;
    }

    private Appointment createAppointment(ResultSet rs) throws SQLException {
        Appointment appointment= new Appointment();
        appointment.setAppointmentId(rs.getInt("appointment_id"));
        appointment.setSlotId(rs.getInt("slot_id"));
        appointment.setAppointmentType(rs.getString("appointment_type"));
        appointment.setAppointmentDescription(rs.getString("appointment_description"));
        appointment.setAppointmentFee(rs.getDouble("appointment_fee"));
        appointment.setPatientId(rs.getString("patient_id"));
        appointment.setDoctorId(rs.getString("doctor_id"));
        appointment.setBillId(rs.getInt("bill_id"));
        appointment.setLabId(rs.getString("lab_id"));
        appointment.setAppointmentDate(rs.getString("slot_date"));
        appointment.setAppointmentTime(rs.getString("slot_timing"));
        return appointment;
    }

}
