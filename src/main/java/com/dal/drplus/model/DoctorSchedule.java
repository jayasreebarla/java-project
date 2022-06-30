package com.dal.drplus.model;

public class DoctorSchedule {
    private int slotId;
    private String slotTiming;
    private String slotDate;
    private String doctorId;
    private String status;

    public int getSlotId() {
        return slotId;
    }

    public void setSlotId(int slotId) {
        this.slotId = slotId;
    }

    public String getSlotTiming() {
        return slotTiming;
    }

    public void setSlotTiming(String slotTiming) {
        this.slotTiming = slotTiming;
    }

    public String getSlotDate() {
        return slotDate;
    }

    public void setSlotDate(String slotDate) {
        this.slotDate = slotDate;
    }

    public String getDoctorId() {
        return doctorId;
    }

    public void setDoctorId(String doctorId) {
        this.doctorId = doctorId;
    }

    public String getStatus() {
        return status;
    }

    public void setStatus(String status) {
        this.status = status;
    }
}
