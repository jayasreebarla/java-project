package com.dal.drplus.service;

import com.dal.drplus.model.Doctor;
import com.dal.drplus.model.DoctorSchedule;
import com.dal.drplus.repository.interfaces.IDoctorRepository;
import com.dal.drplus.repository.interfaces.IDoctorScheduleRepository;

import java.util.List;

public class DoctorSlotService {
    IDoctorScheduleRepository doctorScheduleRepository;

    public DoctorSlotService(IDoctorScheduleRepository doctorScheduleRepository) {
        this.doctorScheduleRepository = doctorScheduleRepository;
    }

    public List<DoctorSchedule> filterSlotOnDoctorId(String doctorId){
        return doctorScheduleRepository.findScheduleByDoctorID(doctorId);
    }

    public List<DoctorSchedule> filterUnbookedSlotOnDoctorId(String doctorId){
        return doctorScheduleRepository.listUnbookedSchedulesbyDoctorID(doctorId);
    }

    public boolean updateSlotStatus(boolean status, int slotId){
        IDoctorScheduleRepository.StorageResult result = doctorScheduleRepository.updateSlotStatus(status, slotId);
        if(result.equals(IDoctorScheduleRepository.StorageResult.SUCCESS)){
            return true;
        } else {
            return false;
        }
    }

    public List<DoctorSchedule> listAllDoctorSlots(){
        return doctorScheduleRepository.findAll();
    }

    public boolean deleteSlotbyId(int slotId){
        IDoctorScheduleRepository.StorageResult result = doctorScheduleRepository.deleteScheduleBySlotID(slotId);
        if(result.equals(IDoctorScheduleRepository.StorageResult.SUCCESS)){
            return true;
        } else {
            return false;
        }
    }

    public boolean deleteSlotbyDoctorId(String doctorId){
        if(filterSlotOnDoctorId(doctorId).isEmpty()){
            return true;
        }
        IDoctorScheduleRepository.StorageResult result = doctorScheduleRepository.deleteScheduleByDoctorID(doctorId);
        if(IDoctorScheduleRepository.StorageResult.SUCCESS.equals(result)){
            return true;
        } else {
            return false;
        }
    }

    public boolean addDoctorSlot(DoctorSchedule doctorSchedule) {
        doctorSchedule.setStatus(false);
        IDoctorScheduleRepository.StorageResult result = doctorScheduleRepository.saveDoctorSchedule(doctorSchedule);
        if(result.equals(IDoctorScheduleRepository.StorageResult.SUCCESS)){
            return true;
        } else {
            return false;
        }
    }

}

