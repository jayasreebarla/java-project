package com.dal.drplus.service;

import com.dal.drplus.model.LabSchedule;
import com.dal.drplus.repository.interfaces.ILabScheduleRepository;

import java.util.List;

public class LabSlotService {
    ILabScheduleRepository labScheduleRepository;

    public LabSlotService(ILabScheduleRepository labScheduleRepository) {
        this.labScheduleRepository = labScheduleRepository;
    }

    public List<LabSchedule> filterSlotOnLabId(String labId){
        return labScheduleRepository.findScheduleByLabID(labId);
    }
}
