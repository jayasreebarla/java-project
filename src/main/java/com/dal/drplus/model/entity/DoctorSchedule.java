package com.dal.drplus.model.entity;

import com.dal.drplus.model.Builder.DoctorScheduleBuilder;
import com.dal.drplus.model.IBuilder.IDoctorScheduleBuilder;
import com.dal.drplus.model.IEntity.IDoctorSchedule;

public class DoctorSchedule extends IDoctorSchedule {
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

    public DoctorSchedule(IDoctorScheduleBuilder builder){
        this.slotId = builder.getSlotId();
        this.slotTiming = builder.getSlotTiming();
        this.slotDate = builder.getSlotDate();
        this.doctorId = builder.getDoctorId();
        this.status = builder.getStatus();
    }

    public int getSlotId() {
        return this.slotId;
    }

    public void setSlotId(int slotId) {
        this.slotId = slotId;
    }

    public String getSlotTiming() {
        return this.slotTiming;
    }

    public void setSlotTiming(String slotTiming) {
        this.slotTiming = slotTiming;
    }

    public String getSlotDate() {
        return this.slotDate;
    }

    public void setSlotDate(String slotDate) {
        this.slotDate = slotDate;
    }

    public String getDoctorId() {
        return this.doctorId;
    }

    public void setDoctorId(String doctorId) {
        this.doctorId = doctorId;
    }

    public Boolean getStatus() {
        return this.status;
    }

    public void setStatus(Boolean status) {
        this.status = status;
    }
}
