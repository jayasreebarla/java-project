package com.dal.drplus.repository.interfaces;

import com.dal.drplus.model.DoctorSchedule;

import java.util.List;

public interface IDoctorScheduleRepository {
    enum StorageResult{
        SUCCESS,
        FAILURE
    }
    public StorageResult saveDoctorSchedule(DoctorSchedule doctorSchedule);
    public int updateDoctorSchedule(DoctorSchedule doctorSchedule);
    public List<DoctorSchedule> findScheduleByDoctorID(String id);
    public List<DoctorSchedule> findAll();
    public DoctorSchedule findScheduleBySlotID(String id);
    public int deleteScheduleByDoctorID(String id);
    public StorageResult deleteScheduleBySlotID(String id);
    public int deleteAllSchedules();

    public List<Integer> getAllSlotIds(String slotDate);
}
