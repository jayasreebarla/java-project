package com.dal.drplus.model.Builder;

import com.dal.drplus.model.IBuilder.IAppointmentBuilder;
import com.dal.drplus.model.entity.Appointment;
import com.dal.drplus.model.factory.ModelFactory;

public class AppointmentBuilder implements IAppointmentBuilder {

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

    @Override
    public IAppointmentBuilder addAppointmentId(int appointmentId) {
        this.appointmentId = appointmentId;
        return this;
    }

    @Override
    public IAppointmentBuilder addAppointmentDate(String appointmentDate) {
        this.appointmentDate = appointmentDate;
        return this;
    }

    @Override
    public IAppointmentBuilder addAppointmentTime(String appointmentTime) {
        this.appointmentTime = appointmentTime;
        return this;
    }

    @Override
    public IAppointmentBuilder addAppointmentDescription(String appointmentDescription) {
        this.appointmentDescription = appointmentDescription;
        return this;
    }

    @Override
    public IAppointmentBuilder addAppointmentFee(double appointmentFee) {
        this.appointmentFee = appointmentFee;
        return this;
    }

    @Override
    public IAppointmentBuilder addPatientId(String patientId) {
        this.patientId = patientId;
        return this;
    }

    @Override
    public IAppointmentBuilder addDoctorId(String doctorId) {
        this.doctorId = doctorId;
        return this;
    }

    @Override
    public IAppointmentBuilder addBillId(int billId) {
        this.billId = billId;
        return this;
    }

    @Override
    public IAppointmentBuilder addSlotId(int slotId) {
        this.slotId = slotId;
        return this;
    }

    @Override
    public IAppointmentBuilder addLabId(String labId) {
        this.labId = labId;
        return this;
    }

    @Override
    public IAppointmentBuilder addAppointmentType(String appointmentType) {
        this.appointmentType = appointmentType;
        return this;
    }

    @Override
    public Appointment build() {
        Appointment appointment = ModelFactory.instance().createAppointmentUsingBuilder(this);
        return appointment;
    }

    @Override
    public int getAppointmentId() {
        return appointmentId;
    }

    @Override
    public String getAppointmentDate() {
        return appointmentDate;
    }

    @Override
    public String getAppointmentTime() {
        return appointmentTime;
    }

    @Override
    public String getAppointmentDescription() {
        return appointmentDescription;
    }

    @Override
    public double getAppointmentFee() {
        return appointmentFee;
    }

    @Override
    public String getPatientId() {
        return patientId;
    }

    @Override
    public String getDoctorId() {
        return doctorId;
    }

    @Override
    public int getBillId() {
        return billId;
    }

    @Override
    public int getSlotId() {
        return slotId;
    }

    @Override
    public String getLabId() {
        return labId;
    }

    @Override
    public String getAppointmentType() {
        return appointmentType;
    }
}
