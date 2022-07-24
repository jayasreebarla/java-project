package com.dal.drplus.repository.interfaces;

import com.dal.drplus.model.entity.LabSchedule;

import java.util.List;

public interface ILabScheduleRepository {
    enum StorageResult{
        SUCCESS,
        FAILURE
    }
    public StorageResult saveLabSchedule(LabSchedule labSchedule);
    public StorageResult updateLabSchedule(LabSchedule labSchedule);
    public List<LabSchedule> findScheduleByLabID(String id);
    public List<LabSchedule> findAll();
    public LabSchedule findScheduleBySlotID(String id);
    public List<LabSchedule> listUnbookedSlotsbyLabId(String labId);
    public StorageResult updateSlotStatus(boolean status, int slotId);
    public StorageResult deleteScheduleByLabID(String id);
    public StorageResult deleteScheduleBySlotID(int id);
    public int deleteAllSchedules();

}
