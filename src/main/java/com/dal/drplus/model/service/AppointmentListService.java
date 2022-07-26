package com.dal.drplus.model.service;

import com.dal.drplus.model.entity.Appointment;
import com.dal.drplus.repository.interfaces.IAppointmentRepository;

import java.util.List;

public class AppointmentListService {
    IAppointmentRepository appointmentRepository;

    public AppointmentListService(IAppointmentRepository appointmentRepository) {
        this.appointmentRepository = appointmentRepository;
    }

    public List<Appointment> listAppointmentbyDoctor(String doctorId) {
        return appointmentRepository.findAppointmentByDoctorId(doctorId);
    }

    public List<Appointment> listAppointmentbyPatient(String patientId) {
        return appointmentRepository.findAppointmentByPatientId(patientId);
    }

    public List<Appointment> listAppointmentbyLab(String labId) {
        return appointmentRepository.findAppointmentByLabId(labId);
    }

    public List<Appointment> listAppointmentbyDoctorNDate(String doctorId, String date) {
        return appointmentRepository.findAppointmentByDoctorIdNDate(doctorId, date);
    }

    public List<Appointment> listAppointmentbyPatientNDate(String patientId, String date) {
        return appointmentRepository.findAppointmentByPatientIdNDate(patientId, date);
    }

    public List<Appointment> listAppointmentbyLabNDate(String labId, String date) {
        return appointmentRepository.findAppointmentByLabIdNDate(labId, date);
    }

    public List<Appointment> listAppointmentAll() {
        return appointmentRepository.findAll();
    }


}
