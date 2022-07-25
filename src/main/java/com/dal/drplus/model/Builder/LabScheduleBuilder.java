package com.dal.drplus.model.Builder;

import com.dal.drplus.model.entity.Doctor;
import com.dal.drplus.model.entity.LabSchedule;
import com.dal.drplus.model.factory.ModelFactory;

public class LabScheduleBuilder {
    private int slotId;
    private String slotTiming;
    private String slotDate;
    private String labId;
    private Boolean status;

    public LabScheduleBuilder addSlotId(int slotId) {
        this.slotId = slotId;
        return this;
    }

    public LabScheduleBuilder addSlotTiming(String slotTiming) {
        this.slotTiming = slotTiming;
        return this;
    }
    public LabScheduleBuilder addSlotDate(String slotDate) {
        this.slotDate = slotDate;
        return this;
    }

    public LabScheduleBuilder addLabId(String labId) {
        this.labId = labId;
        return this;
    }

    public LabScheduleBuilder addStatus(Boolean status) {
        this.status = status;
        return this;
    }

    public LabSchedule build() {
        LabSchedule labSchedule = (LabSchedule) ModelFactory.instance().createLabScheduleBuilder(this);
        return labSchedule;
    }

    public int getSlotId() {
        return slotId;
    }

    public String getSlotTiming() {
        return slotTiming;
    }

    public String getSlotDate() {
        return slotDate;
    }


    public String getLabId() {
        return labId;
    }

    public Boolean getStatus() {
        return status;
    }
}