package com.dal.drplus.model.entity;

public class Appointment {

    private int appointmentId;
    private String appointmentDate;
    private String appointmentTime;
    private String appointmentDescription;
    private double appointmentFee;
    private String patientId;
    private String doctorId;
    private int billId;
    private int slotId;
    private String labId;

    private String appointmentType;

    public Appointment() {
    }

    public Appointment(int appointmentId, String appointmentDate, String appointmentTime, String appointmentDescription, double appointmentFee, String patientId, String doctorId, int billId, int slotId, String labId, String appointmentType) {
        this.appointmentId = appointmentId;
        this.appointmentDate = appointmentDate;
        this.appointmentTime = appointmentTime;
        this.appointmentDescription = appointmentDescription;
        this.appointmentFee = appointmentFee;
        this.patientId = patientId;
        this.doctorId = doctorId;
        this.billId = billId;
        this.slotId = slotId;
        this.labId = labId;
        this.appointmentType = appointmentType;
    }

    public String getLabId() {
        return labId;
    }

    public void setLabId(String labId) {
        this.labId = labId;
    }

    public String getAppointmentType() {
        return appointmentType;
    }

    public void setAppointmentType(String appointmentType) {
        this.appointmentType = appointmentType;
    }

    public int getAppointmentId() {
        return appointmentId;
    }

    public void setAppointmentId(int appointmentId) {
        this.appointmentId = appointmentId;
    }

    public String getAppointmentDate() {
        return appointmentDate;
    }

    public void setAppointmentDate(String appointmentDate) {
        this.appointmentDate = appointmentDate;
    }

    public String getAppointmentTime() {
        return appointmentTime;
    }

    public void setAppointmentTime(String appointmentTime) {
        this.appointmentTime = appointmentTime;
    }

    public String getAppointmentDescription() {
        return appointmentDescription;
    }

    public void setAppointmentDescription(String appointmentDescription) {
        this.appointmentDescription = appointmentDescription;
    }

    public double getAppointmentFee() {
        return appointmentFee;
    }

    public void setAppointmentFee(double appointmentFee) {
        this.appointmentFee = appointmentFee;
    }

    public String getPatientId() {
        return patientId;
    }

    public void setPatientId(String patientId) {
        this.patientId = patientId;
    }

    public String getDoctorId() {
        return doctorId;
    }

    public void setDoctorId(String doctorId) {
        this.doctorId = doctorId;
    }

    public int getBillId() {
        return billId;
    }

    public void setBillId(int billId) {
        this.billId = billId;
    }

    public int getSlotId() {
        //slot id logic to be added
        return slotId;
    }
    public void setSlotId(int slotId) {
        this.slotId = slotId;
    }
}

