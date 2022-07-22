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
    Appointment findAppointmentById(int appointmentId);
    List<Appointment> findAppointmentByDoctorId(String doctorId);
    List<Appointment> findAppointmentByLabId(String labId);
    List<Appointment> findAppointmentByPatientId(String patientId);
    List<Appointment> findAppointmentByDoctorIdNDate(String doctorId, String date);
    List<Appointment> findAppointmentByLabIdNDate(String labId, String date);
    List<Appointment> findAppointmentByPatientIdNDate(String patientId, String date);
    List<Appointment> findAppointmentByDate(String date);
    IAppointmentRepository.StorageResult deleteAppointmentById(int appointmentId);
    List<Appointment> findAll();
    StorageResult deleteAll();
    StorageResult deleteAppointmentbyLabID(String labId);
    StorageResult updateSlotId(int slotId, int appointmentId);
    StorageResult deleteAppointmentbyPatientID(String patientId);
    StorageResult deleteAppointmentbyDoctorID(String doctorId);
    StorageResult isAppointmentConflict(String slotDate, String slotTime, String patientId);
    String getPatientIdBySlotId(int slotId);

}
