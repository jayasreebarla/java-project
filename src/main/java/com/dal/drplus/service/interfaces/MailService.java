package com.dal.drplus.service.interfaces;

import com.dal.drplus.model.Patient;

public interface MailService {
    void sendMail();
    String returnTomorrowDate();
//    String populateMailMessageForDoctorAppointment(Patient patient);
}
