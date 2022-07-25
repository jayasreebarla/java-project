package com.dal.drplus.service;
import com.dal.drplus.model.entity.Appointment;
import com.dal.drplus.repository.configuration.DatabaseConfiguration;
import com.dal.drplus.repository.configuration.DatabaseConfigurationImpl;
import com.dal.drplus.repository.interfaces.*;

import java.time.LocalDate;
import java.util.List;

public class NotificationsService {

    INotificationRepository notificationRepository;
    ILabScheduleRepository labScheduleRepository;
    IDoctorScheduleRepository doctorScheduleRepository;
    IPatientRepository patientRepository;
    IAppointmentRepository appointmentRepository;


    private DatabaseConfiguration dbConfig(){
        return new DatabaseConfigurationImpl();
    }
    DatabaseConfiguration databaseConfiguration;
    LocalDate today = LocalDate.now();

    public NotificationsService(INotificationRepository notificationRepository){
       this.notificationRepository = notificationRepository;
    }
//
//    String NOTIFICATION_PATIENT = "(select b.*, a.slot_date, a.slot_timing from Doc_schedule a, Appointment b where slot_date= ? "+
//    " and b.slot_id = a.slot_id and b.appointment_type='DOCTOR' and b.patient_id= ?) union " +
//            " (select b.*, a.slot_date, a.slot_timing from Lab_schedule a, Appointment b where slot_date=? "+
//                    " and b.slot_id = a.slot_id and b.appointment_type='LAB' and b.patient_id=?)";


    public List<Appointment> notifyPatient(String patientId) {
        return notificationRepository.notifyPatient(patientId);
    }

    public List<Appointment> notifyDoctor(String doctorId) {
        return notificationRepository.notifyDoctor(doctorId);
    }
    public List<Appointment> notifyLab(String labId) {
        return notificationRepository.notifyLab(labId);
    }


}
