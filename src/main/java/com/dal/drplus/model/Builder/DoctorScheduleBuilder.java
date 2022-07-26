package com.dal.drplus.model.Builder;

import com.dal.drplus.model.IBuilder.IDoctorScheduleBuilder;
import com.dal.drplus.model.entity.DoctorSchedule;
import com.dal.drplus.model.factory.ModelFactory;

public class DoctorScheduleBuilder implements IDoctorScheduleBuilder {
    private int slotId;
    private String slotTiming;
    private String slotDate;
    private String doctorId;
    private Boolean status;

    public DoctorScheduleBuilder addSlotId(int slotId) {
        this.slotId = slotId;
        return this;
    }
    public DoctorScheduleBuilder addSlotTiming(String slotTiming) {
        this.slotTiming = slotTiming;
        return this;
    }
    public DoctorScheduleBuilder addSlotDate(String slotDate) {
        this.slotDate = slotDate;
        return this;
    }
    public DoctorScheduleBuilder addDoctorId(String doctorId) {
        this.doctorId = doctorId;
        return this;
    }

    public DoctorScheduleBuilder addStatus(Boolean status) {
        this.status = status;
        return this;
    }

    public DoctorSchedule build() {
        DoctorSchedule doctorSchedule = (DoctorSchedule) ModelFactory.instance().createDoctorScheduleUsingBuilder(this);
        return doctorSchedule;
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

    public String getDoctorId() {
        return doctorId;
    }

    public Boolean getStatus() {
        return status;
    }
}
