package com.dal.drplus.model;

import java.util.Date;

public class LabSchedule {
    private int slotId;
    private String slotTiming;
    private String slotDate;
    private String labId;
    private Boolean status;

    public LabSchedule(int slotId, String slotTiming, String slotDate, String doctorId, Boolean status){
        this.slotId = slotId;
        this.slotTiming = slotTiming;
        this.slotDate = slotDate;
        this.labId = doctorId;
        this.status = status;
    }

    public LabSchedule(){
        this.slotId = 0;
        this.slotTiming = null;
        this.slotDate = null;
        this.labId = null;
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

    public String getLabId() {
        return labId;
    }

    public void setLabId(String labId) {
        this.labId = labId;
    }

    public Boolean getStatus() {
        return status;
    }

    public void setStatus(Boolean status) {
        this.status = status;
    }
}
