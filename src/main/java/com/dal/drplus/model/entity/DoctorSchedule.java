package com.dal.drplus.model.entity;

public class DoctorSchedule {
    private int slotId;
    private String slotTiming;
    private String slotDate;
    private String doctorId;
    private Boolean status;

    public DoctorSchedule(int slotId, String slotTiming, String slotDate, String doctorId, Boolean status){
        this.slotId = slotId;
        this.slotTiming = slotTiming;
        this.slotDate = slotDate;
        this.doctorId = doctorId;
        this.status = status;
    }

    public DoctorSchedule(){
        this.slotId = 0;
        this.slotTiming = null;
        this.slotDate = null;
        this.doctorId = null;
        this.status = null;
    }

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

    public Boolean getStatus() {
        return status;
    }

    public void setStatus(Boolean status) {
        this.status = status;
    }
}
