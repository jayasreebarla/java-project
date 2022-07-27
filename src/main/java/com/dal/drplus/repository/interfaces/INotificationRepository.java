package com.dal.drplus.repository.interfaces;

import com.dal.drplus.model.entity.Appointment;
import java.util.List;

public interface INotificationRepository {
    public List<Appointment> notifyPatient(String patientId);
    public List<Appointment> notifyDoctor(String doctorId);
    public List<Appointment> notifyLab(String labId);
}
