package com.dal.drplus.repository.interfaces;

import com.dal.drplus.model.Appointment;

import java.util.List;

public interface IAppointmentRepository {
    enum StorageResult{
        SUCCESS,
        FAILURE
    }

    IAppointmentRepository.StorageResult saveAppointment(Appointment appointment);
    IAppointmentRepository.StorageResult updateAppointment(Appointment appointment);
    Appointment findAppointmentById(String appointmentId);
    Appointment findAppointmentByDoctorId(String doctorId);
    Appointment findAppointmentByPatientId(String patientId);
    IAppointmentRepository.StorageResult deleteAppointmentById(String appointmentId);
    List<Appointment> findAll();
    int deleteAll();
}
