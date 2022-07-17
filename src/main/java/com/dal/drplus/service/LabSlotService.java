package com.dal.drplus.service;

import com.dal.drplus.model.Lab;
import com.dal.drplus.model.LabSchedule;
import com.dal.drplus.repository.interfaces.IDoctorScheduleRepository;
import com.dal.drplus.repository.interfaces.ILabScheduleRepository;

import java.util.List;

public class LabSlotService {
    ILabScheduleRepository labScheduleRepository;

    public LabSlotService(ILabScheduleRepository labScheduleRepository) {
        this.labScheduleRepository = labScheduleRepository;
    }

    public List<LabSchedule> listAllLabSlots(){
        return labScheduleRepository.findAll();
    }

    public List<LabSchedule> filterSlotOnLabId(String labId){
        return labScheduleRepository.findScheduleByLabID(labId);
    }

    public boolean addLabSlot(LabSchedule labSchedule){
        labSchedule.setStatus(false);
        ILabScheduleRepository.StorageResult result = labScheduleRepository.saveLabSchedule(labSchedule);
        if(result.equals(ILabScheduleRepository.StorageResult.SUCCESS)){
            return true;
        } else {
            return false;
        }
    }

    public boolean deleteSlotById(int slotId){
        ILabScheduleRepository.StorageResult result = labScheduleRepository.deleteScheduleBySlotID(slotId);
        if(result.equals(ILabScheduleRepository.StorageResult.SUCCESS)){
            return true;
        } else {
            return false;
        }
    }
}
