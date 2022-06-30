package com.dal.drplus.model;

public class LabSchedule {
    private int slotId;
    private String slotTiming;
    private String slotDate;
    private String labId;
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

    public String getLabId() {
        return labId;
    }

    public void setLabId(String labId) {
        this.labId = labId;
    }

    public String getStatus() {
        return status;
    }

    public void setStatus(String status) {
        this.status = status;
    }
}
