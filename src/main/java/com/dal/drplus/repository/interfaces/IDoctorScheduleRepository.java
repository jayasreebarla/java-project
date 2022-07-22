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
    public DoctorSchedule findScheduleBySlotID(int id);
    public List<DoctorSchedule> listUnbookedSchedulesbyDoctorID(String doctorId);
    public StorageResult updateSlotStatus(boolean status, int slotId);
    public StorageResult deleteScheduleByDoctorID(String id);
    public StorageResult deleteScheduleBySlotID(int id);
    public StorageResult deleteAllSchedules();
    public List<Integer> getAllSlotIds(String slotDate);

}
