package com.dal.drplus.service;

import com.dal.drplus.model.IEntity.ILabSchedule;
import com.dal.drplus.model.entity.LabSchedule;
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

    public List<LabSchedule> filterUnbookedSlotOnLabId(String labId){
        return labScheduleRepository.listUnbookedSlotsbyLabId(labId);
    }
    public boolean addLabSlot(ILabSchedule labSchedule){
        labSchedule.setStatus(false);
        ILabScheduleRepository.StorageResult result = labScheduleRepository.saveLabSchedule(labSchedule);
        if(result.equals(ILabScheduleRepository.StorageResult.SUCCESS)){
            return true;
        } else {
            return false;
        }
    }

    public boolean updateSlotStatus(boolean status, int slotId){
        ILabScheduleRepository.StorageResult result = labScheduleRepository.updateSlotStatus(status, slotId);
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

    public boolean deleteSlotByLabId(String labId){
        if(filterSlotOnLabId(labId).isEmpty()){
            return true;
        }
        ILabScheduleRepository.StorageResult result = labScheduleRepository.deleteScheduleByLabID(labId);
        if(ILabScheduleRepository.StorageResult.SUCCESS.equals(result)){
            return true;
        } else {
            return false;
        }
    }
}
