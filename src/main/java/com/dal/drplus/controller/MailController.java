package com.dal.drplus.controller;

import com.dal.drplus.repository.implementation.AppointmentRepositoryImpl;
import com.dal.drplus.repository.implementation.DoctorScheduleRepositoryImpl;
import com.dal.drplus.repository.implementation.LabScheduleRepositoryImpl;
import com.dal.drplus.repository.implementation.PatientRepositoryImpl;
import com.dal.drplus.service.MailReminderService;
import com.dal.drplus.service.interfaces.MailService;

public class MailController {
    private MailService mailService;
    public MailController() {
        this.mailService = new MailReminderService(new LabScheduleRepositoryImpl(),new DoctorScheduleRepositoryImpl(),new AppointmentRepositoryImpl(),new PatientRepositoryImpl());
    }
    public void triggerMailService(){
        mailService.sendMail();
    }
}
