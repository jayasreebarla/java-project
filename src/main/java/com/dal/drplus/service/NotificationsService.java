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
