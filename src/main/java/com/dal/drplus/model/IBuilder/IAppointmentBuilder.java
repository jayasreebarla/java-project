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

    public Appointment build();

    public int getAppointmentId();
    public String getAppointmentDate();
    public String getAppointmentTime();
    public String getAppointmentDescription();
    public double getAppointmentFee();
    public String getPatientId();
    public String getDoctorId();
    public int getBillId();
    public int getSlotId();
    public String getLabId();
    public String getAppointmentType();
}
