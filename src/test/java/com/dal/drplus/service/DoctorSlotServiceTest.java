package com.dal.drplus.service;

import com.dal.drplus.model.Doctor;
import com.dal.drplus.model.DoctorSchedule;
import com.dal.drplus.repository.implementation.DoctorRepositoryImpl;
import com.dal.drplus.repository.implementation.DoctorScheduleRepositoryImpl;
import com.dal.drplus.repository.interfaces.IDoctorRepository;
import com.dal.drplus.repository.interfaces.IDoctorScheduleRepository;
import org.junit.jupiter.api.BeforeAll;
import org.junit.jupiter.api.Test;
import org.mockito.Mockito;

import java.util.ArrayList;
import java.util.List;

import static org.junit.jupiter.api.Assertions.*;


public class DoctorSlotServiceTest {

    static DoctorSchedule doctorSchedule = new DoctorSchedule(1, "10:00 - 12:00", "2022-07-17", "d1", true );
    static DoctorSchedule doctorSchedule1 = new DoctorSchedule(2, "12:00 - 2:00", "2022-08-11", "d2",true);
    static DoctorSchedule doctorSchedule2 = new DoctorSchedule(3, "3:00 - 1:00", "2022-09-11", "d3",true);

    private static DoctorSlotService doctorSlotService;
    private static IDoctorScheduleRepository doctorScheduleRepository;

    @BeforeAll
    public static void init(){
        doctorScheduleRepository = Mockito.mock(DoctorScheduleRepositoryImpl.class);

        Mockito.when(doctorScheduleRepository.deleteScheduleBySlotID(24)).thenReturn(IDoctorScheduleRepository.StorageResult.FAILURE);
        Mockito.when(doctorScheduleRepository.deleteScheduleBySlotID(3)).thenReturn(IDoctorScheduleRepository.StorageResult.SUCCESS);
        Mockito.when(doctorScheduleRepository.deleteScheduleByDoctorID(doctorSchedule.getDoctorId())).thenReturn(IDoctorScheduleRepository.StorageResult.SUCCESS);
        Mockito.when(doctorScheduleRepository.deleteScheduleByDoctorID(doctorSchedule1.getDoctorId())).thenReturn(IDoctorScheduleRepository.StorageResult.FAILURE);

        doctorSlotService = new DoctorSlotService(doctorScheduleRepository);
    }

    @Test
    void deleteSlotByIdFalseWhenSlotIdInvalid(){
       IDoctorScheduleRepository.StorageResult result = doctorScheduleRepository.deleteScheduleBySlotID(24);
       assert(result == IDoctorScheduleRepository.StorageResult.FAILURE);
    }

    @Test
    void deleteSlotByIdTrueWhenSlotIdValid(){
        IDoctorScheduleRepository.StorageResult result = doctorScheduleRepository.deleteScheduleBySlotID(3);
        assert(result == IDoctorScheduleRepository.StorageResult.SUCCESS);
    }

    @Test
    void deleteSlotByDoctorIdFalseWhenDoctorIdInvalid(){
        IDoctorScheduleRepository.StorageResult result = doctorScheduleRepository.deleteScheduleByDoctorID(doctorSchedule1.getDoctorId());
        assert(result == IDoctorScheduleRepository.StorageResult.FAILURE);
    }

    @Test
    void deleteSlotByDoctorIdTrueWhenSlotIdValid(){
        IDoctorScheduleRepository.StorageResult result = doctorScheduleRepository.deleteScheduleByDoctorID(doctorSchedule.getDoctorId());
        assert(result == IDoctorScheduleRepository.StorageResult.SUCCESS);
    }

    @Test
    void ListAllSlotsWhenDoctorListNull(){
        Mockito.when(doctorScheduleRepository.findAll()).thenReturn(null);
        List<DoctorSchedule> doctorSchedules = doctorSlotService.listAllDoctorSlots();
        assertIterableEquals(null,doctorSchedules);
    }

    @Test
    void ListAllDoctorSchedulesWhenDoctorListExists(){
        List<DoctorSchedule> doctorScheduleList = new ArrayList<>();
        doctorScheduleList.add(doctorSchedule);
        doctorScheduleList.add(doctorSchedule1);
        doctorScheduleList.add(doctorSchedule2);

        Mockito.when(doctorScheduleRepository.findAll()).thenReturn(doctorScheduleList);

        List<DoctorSchedule> doctorSchedules = doctorSlotService.listAllDoctorSlots();
        List<DoctorSchedule> expectDoctorSchedules = new ArrayList<>();
        expectDoctorSchedules.add(doctorSchedule);
        expectDoctorSchedules.add(doctorSchedule1);
        expectDoctorSchedules.add(doctorSchedule2);
        assertIterableEquals(expectDoctorSchedules, doctorSchedules);
    }

    @Test
    void ListAllDoctorSchedulesWhenDoctorListEmpty(){

        ArrayList<DoctorSchedule> doctorSchedules = new ArrayList<>();
        Mockito.when(doctorScheduleRepository.findAll()).thenReturn(doctorSchedules);

        List<DoctorSchedule> doctors_result = doctorSlotService.listAllDoctorSlots();
        assertEquals(0,doctors_result.size());
    }
}


