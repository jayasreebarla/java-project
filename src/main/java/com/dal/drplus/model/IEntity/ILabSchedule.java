package com.dal.drplus.model.IEntity;

public abstract class ILabSchedule {
    protected int slotId;
    protected String slotTiming;
    protected String slotDate;
    protected String labId;
    protected Boolean status;

    public ILabSchedule(int slotId, String slotTiming, String slotDate, String doctorId, Boolean status){
        this.slotId = slotId;
        this.slotTiming = slotTiming;
        this.slotDate = slotDate;
        this.labId = doctorId;
        this.status = status;
    }

    public ILabSchedule(){
        this.slotId = 0;
        this.slotTiming = null;
        this.slotDate = null;
        this.labId = null;
        this.status = null;
    }
    abstract public boolean validateSlotDateFormat(String slotDate);
    abstract public int getSlotId();

    abstract public void setSlotId(int slotId);

    abstract public String getSlotTiming();

    abstract public void setSlotTiming(String slotTiming);

    abstract public String getSlotDate();

    abstract public void setSlotDate(String slotDate);

    abstract public String getLabId();

    abstract public void setLabId(String labId);

    abstract public Boolean getStatus();

    abstract public void setStatus(Boolean status);

}
