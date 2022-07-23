package com.dal.drplus.repository.implementation;


import com.dal.drplus.model.Appointment;
import com.dal.drplus.repository.configuration.DatabaseConfiguration;
import com.dal.drplus.repository.configuration.DatabaseConfigurationImpl;
import com.dal.drplus.repository.interfaces.INotificationRepository;
import org.springframework.stereotype.Repository;

import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.time.LocalDate;
import java.time.format.DateTimeFormatter;
import java.util.ArrayList;
import java.util.List;

@Repository
public class NotificationsRepositoryImpl implements INotificationRepository {

    DatabaseConfiguration databaseConfiguration;
    private DatabaseConfiguration dbConfig(){
        return new DatabaseConfigurationImpl();
    }

    LocalDate today = LocalDate.now();

    String NOTIFICATION_PATIENT = "(select b.*, a.slot_date, a.slot_timing from Doc_schedule a, Appointment b where slot_date= ? "+
            " and b.slot_id = a.slot_id and b.appointment_type='DOCTOR' and b.patient_id= ?) union " +
            " (select b.*, a.slot_date, a.slot_timing from Lab_schedule a, Appointment b where slot_date=? "+
            " and b.slot_id = a.slot_id and b.appointment_type='LAB' and b.patient_id=?)";

//    String NOTIFICATION_DOCTOR = "(select a.*, b.* from Doc_schedule a, Appointment b "+"where slot_date= ? "
//    +"and b.slot_id = a.slot_id and b.doctor_id= ?)";

    String NOTIFICATION_DOCTOR = "( select a.*, b.appointment_id, b.patient_id from Doc_schedule a, Appointment b where slot_date= ? " + " and b.slot_id = a.slot_id and b.doctor_id= ? and b.appointment_type = 'DOCTOR')";

//    String NOTIFICATION_LAB = "(select a.*, b.* from Lab_schedule a, Appointment b where "+ "slot_date= ?"
//          +"  and b.slot_id = a.slot_id and b.lab_id= ?)";
String NOTIFICATION_LAB =  "(select a.*, b.* from Lab_schedule a, Appointment b where slot_date= ? and b.slot_id = a.slot_id and b.lab_id= ? and b.appointment_type = 'LAB')";

    public NotificationsRepositoryImpl() {
        this.databaseConfiguration = dbConfig();
    }

    public List<Appointment> notifyPatient(String patientId)
    {
        List<Appointment> appointmentList = new ArrayList();

        try {
            for (int i =0; i<3; i++) {
                String nextAppDate = (today.plusDays(i)).format(DateTimeFormatter.ISO_DATE);
                System.out.println(nextAppDate);
                System.out.println(patientId);
                PreparedStatement statement = databaseConfiguration.getDBConnection().prepareStatement(NOTIFICATION_PATIENT);
                statement.setString(1,nextAppDate);
                statement.setString(2,patientId);
                statement.setString(3,nextAppDate);
                statement.setString(4,patientId);
                ResultSet rs = statement.executeQuery();
                while (rs.next()) {
                    Appointment appointment;
                    appointment = createAppointment(rs);
                    if(appointment!=null){
                        appointmentList.add(appointment);
                    }
                }
            }
            return appointmentList;
        } catch (SQLException e) {
            throw new RuntimeException(e);
        }
    }

    public List<Appointment> notifyDoctor(String doctorId)
    {
        List<Appointment> appointmentList = new ArrayList();

        try {
            for (int i =0; i<3; i++) {
                String nextAppDate = (today.plusDays(i)).format(DateTimeFormatter.ISO_DATE);
                System.out.println(nextAppDate);
                System.out.println(doctorId);
                PreparedStatement statement = databaseConfiguration.getDBConnection().prepareStatement(NOTIFICATION_DOCTOR);
                statement.setString(1,nextAppDate);
                statement.setString(2,doctorId);
                ResultSet rs = statement.executeQuery();
                while (rs.next()) {
                    Appointment appointment;
                    appointment = createAppointment(rs);
                    appointmentList.add(appointment);
                }
            }
            return appointmentList;
        } catch (SQLException e) {
            throw new RuntimeException(e);
        }
    }


    public List<Appointment> notifyLab(String labId)
    {
        List<Appointment> appointmentList = new ArrayList<>();

        try {
            for (int i =0; i<3; i++) {
                String nextAppDate = (today.plusDays(i)).format(DateTimeFormatter.ISO_DATE);
                System.out.println(nextAppDate);
                System.out.println(labId);
                PreparedStatement statement = databaseConfiguration.getDBConnection().prepareStatement(NOTIFICATION_LAB);
                statement.setString(1,nextAppDate);
                statement.setString(2,labId);
                ResultSet rs = statement.executeQuery();
                while (rs.next()) {
                    Appointment appointment;
                    appointment = createAppointment(rs);
                    appointmentList.add(appointment);
                }
            }
            return appointmentList;
        } catch (SQLException e) {
            throw new RuntimeException(e);
        }
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
