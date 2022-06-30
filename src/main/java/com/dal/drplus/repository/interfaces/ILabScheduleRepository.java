package com.dal.drplus.repository.interfaces;

import com.dal.drplus.model.LabSchedule;

import java.util.List;

public interface ILabScheduleRepository {
    public int saveLabSchedule(LabSchedule labSchedule);
    public int updateLabSchedule(LabSchedule labSchedule);
    public List<LabSchedule> findScheduleByLabID(String id);
    public LabSchedule findScheduleBySlotID(String id);
    public int deleteScheduleByLabID(String id);
    public int deleteScheduleBySlotID(String id);
    public int deleteAllSchedules();

}
