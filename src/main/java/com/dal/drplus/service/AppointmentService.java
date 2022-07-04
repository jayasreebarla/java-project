package com.dal.drplus.service;
import com.dal.drplus.model.Appointment;
import com.dal.drplus.repository.interfaces.IAppointmentRepository;

public class AppointmentService {
    IAppointmentRepository appointmentRepository;

    public AppointmentService(IAppointmentRepository appointmentRepository) {
        this.appointmentRepository = appointmentRepository;
    }

    public boolean isAppointmentConflict(Appointment appointment) {
        String slotDate = appointment.getAppointmentDate();
        String slotTime = appointment.getAppointmentTime();
        String patientId = appointment.getPatientId();
        IAppointmentRepository.StorageResult resConflict = appointmentRepository.isAppointmentConflict(slotDate, slotTime,patientId);
        if(resConflict.equals(IAppointmentRepository.StorageResult.SUCCESS)) {
            return true;
        } else {
            return false;
        }
    }

    public boolean bookAppointment(Appointment appointment) {
        IAppointmentRepository.StorageResult result = appointmentRepository.saveAppointment(appointment);
        if(result.equals(IAppointmentRepository.StorageResult.SUCCESS)){
            return true;
        } else {
            return false;
        }
    }

    public boolean cancelAppointment(int appointmentId) {
        IAppointmentRepository.StorageResult result = appointmentRepository.deleteAppointmentById(appointmentId);
        if(result.equals(IAppointmentRepository.StorageResult.SUCCESS)){
            return true;
        } else {
            return false;
        }
    }


}
