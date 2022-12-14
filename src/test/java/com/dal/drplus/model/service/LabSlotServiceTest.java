package com.dal.drplus.model.service;

import com.dal.drplus.model.entity.LabSchedule;
import com.dal.drplus.model.service.LabSlotService;
import com.dal.drplus.repository.implementation.LabScheduleRepositoryImpl;
import com.dal.drplus.repository.interfaces.ILabScheduleRepository;
import org.junit.jupiter.api.BeforeAll;
import org.junit.jupiter.api.Test;
import org.mockito.Mockito;
import java.util.ArrayList;
import java.util.List;
import static org.junit.jupiter.api.Assertions.*;


public class LabSlotServiceTest {

    static LabSchedule labSchedule = new LabSchedule(1, "10:00 - 12:00", "2022-07-17", "l1", false );
    static LabSchedule labSchedule1 = new LabSchedule(2, "12:00 - 2:00", "2022-08-11", "l2",true);
    static LabSchedule labSchedule2 = new LabSchedule(3, "3:00 - 1:00", "2022-09-11", "l3",true);

    private static LabSlotService labSlotService;
    private static ILabScheduleRepository labScheduleRepository;

    @BeforeAll
    public static void init(){
        labScheduleRepository = Mockito.mock(LabScheduleRepositoryImpl.class);

        Mockito.when(labScheduleRepository.deleteScheduleBySlotID(24)).thenReturn(ILabScheduleRepository.StorageResult.FAILURE);
        Mockito.when(labScheduleRepository.deleteScheduleBySlotID(3)).thenReturn(ILabScheduleRepository.StorageResult.SUCCESS);
        Mockito.when(labScheduleRepository.deleteScheduleByLabID(labSchedule.getLabId()))
                .thenReturn(ILabScheduleRepository.StorageResult.SUCCESS);
        Mockito.when(labScheduleRepository.deleteScheduleByLabID(labSchedule1.getLabId()))
                .thenReturn(ILabScheduleRepository.StorageResult.FAILURE);

        Mockito.when(labScheduleRepository.updateSlotStatus(true,labSchedule.getSlotId()))
                .thenReturn(ILabScheduleRepository.StorageResult.SUCCESS);
        Mockito.when(labScheduleRepository.updateSlotStatus(false,labSchedule1.getSlotId()))
                .thenReturn(ILabScheduleRepository.StorageResult.FAILURE);
        labSlotService = new LabSlotService(labScheduleRepository);
    }

    @Test
    void deleteSlotByIdFalseWhenSlotIdInvalid(){
        ILabScheduleRepository.StorageResult result = labScheduleRepository.deleteScheduleBySlotID(24);
        assert(result == ILabScheduleRepository.StorageResult.FAILURE);
    }

    @Test
    void deleteSlotByIdTrueWhenSlotIdValid(){
        ILabScheduleRepository.StorageResult result = labScheduleRepository.deleteScheduleBySlotID(3);
        assert(result == ILabScheduleRepository.StorageResult.SUCCESS);
    }

    @Test
    void deleteSlotByDoctorIdFalseWhenDoctorIdInvalid(){
        ILabScheduleRepository.StorageResult result = labScheduleRepository.deleteScheduleByLabID(labSchedule1.getLabId());
        assert(result == ILabScheduleRepository.StorageResult.FAILURE);
    }

    @Test
    void deleteSlotByDoctorIdTrueWhenSlotIdValid(){
        ILabScheduleRepository.StorageResult result = labScheduleRepository.deleteScheduleByLabID(labSchedule.getLabId());
        assert(result == ILabScheduleRepository.StorageResult.SUCCESS);
    }

    @Test
    void ListAllSlotsWhenDoctorListNull(){
        Mockito.when(labScheduleRepository.findAll()).thenReturn(null);
        List<LabSchedule> labSchedules = labSlotService.listAllLabSlots();
        assertIterableEquals(null,labSchedules);
    }

    @Test
    void ListAllDoctorSchedulesWhenDoctorListExists(){
        List<LabSchedule> labScheduleList = new ArrayList<>();
        labScheduleList.add(labSchedule);
        labScheduleList.add(labSchedule1);
        labScheduleList.add(labSchedule2);

        Mockito.when(labScheduleRepository.findAll()).thenReturn(labScheduleList);

        List<LabSchedule> labSchedules = labSlotService.listAllLabSlots();
        List<LabSchedule> expectLabSchedules = new ArrayList<>();
        expectLabSchedules.add(labSchedule);
        expectLabSchedules.add(labSchedule1);
        expectLabSchedules.add(labSchedule2);
        assertIterableEquals(expectLabSchedules, labSchedules);
    }

    @Test
    void ListAllDoctorSchedulesWhenDoctorListEmpty(){

        ArrayList<LabSchedule> labSchedules = new ArrayList<>();
        Mockito.when(labScheduleRepository.findAll()).thenReturn(labSchedules);

        List<LabSchedule> doctors_result = labSlotService.listAllLabSlots();
        assertEquals(0,doctors_result.size());
    }

    @Test
    void listAllUnbookedLabSchedulebyLabId(){
        List<LabSchedule> labScheduleList = new ArrayList<>();
        labScheduleList.add(labSchedule);

        Mockito.when(labScheduleRepository.listUnbookedSlotsbyLabId(labSchedule.getLabId()))
                .thenReturn(labScheduleList);

        List<LabSchedule> labSchedules = labSlotService.filterUnbookedSlotOnLabId(labSchedule.getLabId());
        List<LabSchedule> expectLabSchedules = new ArrayList<>();
        expectLabSchedules.add(labSchedule);
        assertIterableEquals(expectLabSchedules, labSchedules);
    }

    @Test
    void deleteSlotByLabIdTrue(){
        List<LabSchedule> labScheduleList = new ArrayList<>();
        labScheduleList.add(labSchedule);

        Mockito.when(labScheduleRepository.findScheduleByLabID(labSchedule.getLabId()))
                .thenReturn(labScheduleList);
        boolean result = labSlotService.deleteSlotByLabId(labSchedule.getLabId());
        assertTrue(result);
    }

    @Test
    void deleteSlotByLabIdFalse(){
        List<LabSchedule> labScheduleList = new ArrayList<>();
        labScheduleList.add(labSchedule1);

        Mockito.when(labScheduleRepository.findScheduleByLabID(labSchedule1.getLabId()))
                .thenReturn(labScheduleList);
        boolean result = labSlotService.deleteSlotByLabId(labSchedule1.getLabId());
        assertFalse(result);
    }

    @Test
    void updateSlotStatusTrue(){
        boolean result = labSlotService.updateSlotStatus(true, labSchedule.getSlotId());
        assertTrue(result);
    }

    @Test
    void updateSlotStatusFalse(){
        boolean result = labSlotService.updateSlotStatus(false, labSchedule1.getSlotId());
        assertFalse(result);
    }
}


