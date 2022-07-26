package com.dal.drplus.model.IEntity;

public abstract class IAppointment {
    protected int appointmentId;
    protected String appointmentDate;
    protected String appointmentTime;
    protected String appointmentDescription;
    protected double appointmentFee;
    protected String patientId;
    protected String doctorId;
    protected int billId;
    protected int slotId;
    protected String labId;
    protected String appointmentType;

    public IAppointment(){

    }

    public IAppointment(int appointmentId, String appointmentDate, String appointmentTime, String appointmentDescription, double appointmentFee, String patientId, String doctorId, int billId, int slotId, String labId, String appointmentType) {
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

    abstract public boolean validateAppointmentDate(String appointmentDate);
    abstract public int getAppointmentId();

    abstract public void setAppointmentId(int appointmentId);

    abstract public String getAppointmentDate();

    abstract public void setAppointmentDate(String appointmentDate);

    abstract public String getAppointmentTime();

    abstract public void setAppointmentTime(String appointmentTime);

    abstract public String getAppointmentDescription();

    abstract public void setAppointmentDescription(String appointmentDescription);

    abstract public double getAppointmentFee();

    abstract public void setAppointmentFee(double appointmentFee);

    abstract public String getPatientId();

    abstract public void setPatientId(String patientId);

    abstract public String getDoctorId();

    abstract public void setDoctorId(String doctorId);

    abstract public int getBillId();

    abstract public void setBillId(int billId);

    abstract public int getSlotId();

    abstract public void setSlotId(int slotId);

    abstract public String getLabId();

    abstract public void setLabId(String labId);

    abstract public String getAppointmentType();

    abstract public void setAppointmentType(String appointmentType);
}
