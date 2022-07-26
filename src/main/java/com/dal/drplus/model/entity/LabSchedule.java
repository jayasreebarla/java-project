package com.dal.drplus.model.entity;

import com.dal.drplus.model.Builder.LabScheduleBuilder;
import com.dal.drplus.model.IBuilder.ILabScheduleBuilder;
import com.dal.drplus.model.IEntity.ILabSchedule;

public class LabSchedule extends ILabSchedule {
    private int slotId;
    private String slotTiming;
    private String slotDate;
    private String labId;
    private Boolean status;

    public LabSchedule(int slotId, String slotTiming, String slotDate, String labId, Boolean status){
        this.slotId = slotId;
        this.slotTiming = slotTiming;
        this.slotDate = slotDate;
        this.labId = labId;
        this.status = status;
    }

    public LabSchedule(ILabScheduleBuilder builder){
        this.slotId = builder.getSlotId();
        this.slotTiming = builder.getSlotTiming();
        this.slotDate = builder.getSlotDate();
        this.labId = builder.getLabId();
        this.status = builder.getStatus();
    }

    public LabSchedule(){
        this.slotId = 0;
        this.slotTiming = null;
        this.slotDate = null;
        this.labId = null;
        this.status = null;
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

    public String getLabId() {
        return this.labId;
    }

    public void setLabId(String labId) {
        this.labId = labId;
    }

    public Boolean getStatus() {
        return this.status;
    }

    public void setStatus(Boolean status) {
        this.status = status;
    }
}
