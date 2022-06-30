package com.dal.drplus.repository.interfaces;

import com.dal.drplus.model.DoctorSchedule;

import java.util.List;

public interface IDoctorScheduleRepository {
    public int saveDoctorSchedule(DoctorSchedule doctorSchedule);
    public int updateDoctorSchedule(DoctorSchedule doctorSchedule);
    public List<DoctorSchedule> findScheduleByDoctorID(String id);
    public DoctorSchedule findScheduleBySlotID(String id);
    public int deleteScheduleByDoctorID(String id);
    public int deleteScheduleBySlotID(String id);
    public int deleteAllSchedules();
}
