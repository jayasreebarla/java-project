package com.dal.drplus.model.IBuilder;

import com.dal.drplus.model.Builder.DoctorScheduleBuilder;
import com.dal.drplus.model.entity.DoctorSchedule;
import com.dal.drplus.model.factory.ModelFactory;

public interface IDoctorScheduleBuilder {

    IDoctorScheduleBuilder addSlotId(int slotId);
    IDoctorScheduleBuilder addSlotTiming(String slotTiming);
    IDoctorScheduleBuilder addSlotDate(String slotDate);
    IDoctorScheduleBuilder addDoctorId(String doctorId);

    IDoctorScheduleBuilder addStatus(Boolean status);

    public DoctorSchedule build();

    public int getSlotId();

    public String getSlotTiming();

    public String getSlotDate();

    public String getDoctorId();

    public Boolean getStatus();
}
