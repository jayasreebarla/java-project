package com.dal.drplus.model.IEntity;

public abstract class IDoctorSchedule {
    protected int slotId;
    protected String slotTiming;
    protected String slotDate;
    protected String doctorId;
    protected Boolean status;

    public IDoctorSchedule(int slotId, String slotTiming, String slotDate, String doctorId, Boolean status){
        this.slotId = slotId;
        this.slotTiming = slotTiming;
        this.slotDate = slotDate;
        this.doctorId = doctorId;
        this.status = status;
    }

    public IDoctorSchedule(){
        this.slotId = 0;
        this.slotTiming = null;
        this.slotDate = null;
        this.doctorId = null;
        this.status = null;
    }
    abstract public boolean validateSlotDateFormat(String slotDate);

    abstract public int getSlotId();

    abstract public void setSlotId(int slotId);

    abstract public String getSlotTiming();

    abstract public void setSlotTiming(String slotTiming);

    abstract public String getSlotDate();

    abstract public void setSlotDate(String slotDate);

    abstract public String getDoctorId();

    abstract public void setDoctorId(String doctorId);

    abstract public Boolean getStatus();

    abstract public void setStatus(Boolean status);

}
