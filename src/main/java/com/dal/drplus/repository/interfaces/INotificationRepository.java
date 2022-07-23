package com.dal.drplus.repository.interfaces;

import com.dal.drplus.model.Appointment;

import java.util.List;

public interface INotificationRepository {
    public List<Appointment> NotifyPatient(String patientId);
}
