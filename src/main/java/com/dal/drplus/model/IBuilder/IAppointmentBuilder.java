package com.dal.drplus.model.IBuilder;

import com.dal.drplus.model.entity.Appointment;

public interface IAppointmentBuilder {
    IAppointmentBuilder addAppointmentId(int appointmentId);
    IAppointmentBuilder addAppointmentDate(String appointmentDate);
    IAppointmentBuilder addAppointmentTime(String appointmentTime);
    IAppointmentBuilder addAppointmentDescription(String appointmentDescription);
    IAppointmentBuilder addAppointmentFee(double appointmentFee);
    IAppointmentBuilder addPatientId(String patientId);
    IAppointmentBuilder addDoctorId(String doctorId);
    IAppointmentBuilder addBillId(int billId);
    IAppointmentBuilder addSlotId(int slotId);
    IAppointmentBuilder addLabId(String labId);
    IAppointmentBuilder addAppointmentType(String appointmentType);

    Appointment build();
    int getAppointmentId();
    String getAppointmentDate();
    String getAppointmentTime();
    String getAppointmentDescription();
    double getAppointmentFee();
    String getPatientId();
    String getDoctorId();
    int getBillId();
    int getSlotId();
    String getLabId();
    String getAppointmentType();
}
