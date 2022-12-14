package com.dal.drplus.model.IBuilder;

import com.dal.drplus.model.entity.LabSchedule;

public interface ILabScheduleBuilder {

    ILabScheduleBuilder addSlotId(int slotId);

    ILabScheduleBuilder addSlotTiming(String slotTiming);

    ILabScheduleBuilder addSlotDate(String slotDate);

    ILabScheduleBuilder addLabId(String labId);

    ILabScheduleBuilder addStatus(Boolean status);

    public LabSchedule build();

    public int getSlotId();

    public String getSlotTiming();

    public String getSlotDate();

    public String getLabId();

    public Boolean getStatus();
}
