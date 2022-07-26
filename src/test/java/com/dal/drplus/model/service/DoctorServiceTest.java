package com.dal.drplus.model.service;

import com.dal.drplus.model.IEntity.IDoctor;
import com.dal.drplus.model.entity.Doctor;
import com.dal.drplus.model.service.DoctorService;
import com.dal.drplus.repository.implementation.DoctorRepositoryImpl;
import com.dal.drplus.repository.interfaces.IDoctorRepository;
import org.junit.jupiter.api.BeforeAll;
import org.junit.jupiter.api.Test;
import org.mockito.Mockito;

import static org.junit.jupiter.api.Assertions.*;

import java.util.ArrayList;
import java.util.List;

public class DoctorServiceTest {

    static Doctor doctor1 = new Doctor("A1", "A1", "A1", "A1", "A1", "M", 7, "A1", "Cardiologist", "A1", "AP1",10000);
    static Doctor doctor2 = new Doctor("B1", "B1", "B1", "B1", "B1", "M", 7, "B1", "Dermatologist", "B1", "15",12938);
    static Doctor doctor3 = new Doctor("C1", "C1", "C1", "C1", "C1", "M", 7, "C1", "Cardiologist", "C1", "AP1",18726);

    private static DoctorService doctorService;

    private static IDoctorRepository doctorRepository;

    static List<Doctor> unsortedList;
    @BeforeAll
    public static void init(){
        doctorRepository = Mockito.mock(DoctorRepositoryImpl.class);
        unsortedList = new ArrayList<>();
        unsortedList.add(doctor1);
        unsortedList.add(doctor2);
        unsortedList.add(doctor3);
        Mockito.when(doctorRepository.deleteDoctorById(doctor1.getDoctorId())).thenReturn(IDoctorRepository.StorageResult.SUCCESS);
        Mockito.when(doctorRepository.deleteDoctorById(doctor2.getDoctorId())).thenReturn(IDoctorRepository.StorageResult.FAILURE);
        Mockito.when(doctorRepository.findDoctorById(doctor1.getDoctorId())).thenReturn(doctor1);

        doctorService = new DoctorService(doctorRepository);
    }

    @Test
    void ListAllDoctorWhenDoctorListExists(){
        List<Doctor> doctorList = new ArrayList<>();
        doctorList.add(doctor1);
        doctorList.add(doctor2);
        doctorList.add(doctor3);
        Mockito.when(doctorRepository.findAllDoctors()).thenReturn(doctorList);
        List<Doctor> doctors = doctorService.listAllDoctor();
        List<Doctor> expectDoctors = new ArrayList<>();
        expectDoctors.add(doctor1);
        expectDoctors.add(doctor2);
        expectDoctors.add(doctor3);
        assertIterableEquals(expectDoctors, doctors);
    }

    @Test
    void ListAllDoctorsWhenDoctorListNull(){
        Mockito.when(doctorRepository.findAllDoctors()).thenReturn(null);
        List<Doctor> doctors = doctorService.listAllDoctor();
        assertIterableEquals(null,doctors);
    }

    @Test
    void ListAllDoctorsWhenDoctorListEmpty(){
        ArrayList<Doctor> doctors = new ArrayList<>();
        Mockito.when(doctorRepository.findAllDoctors()).thenReturn(doctors);
        List<Doctor> doctors_result = doctorService.listAllDoctor();
        assertEquals(0,doctors_result.size());
    }

    @Test
    void deleteDoctorByIdTrue(){
        boolean result = doctorService.deleteDoctorbyId(doctor1.getDoctorId());
        assertTrue(result);
    }

    @Test
    void deleteDoctorByIdFalse(){
        boolean result = doctorService.deleteDoctorbyId(doctor2.getDoctorId());
        assertFalse(result);
    }

    @Test
    void filterDoctorOnPincodeAndSpecializationWhenBothExists(){
        List<Doctor> doctorListBySpecializationAndPincode = new ArrayList<>();
        doctorListBySpecializationAndPincode.add(doctor1);
        doctorListBySpecializationAndPincode.add(doctor3);
        Mockito.when(doctorRepository.findAllDoctorsBySpecializationAndPincode("Cardiologist","AP1")).thenReturn(doctorListBySpecializationAndPincode);
        List<Doctor> doctorListResult = doctorService.filterDoctorOnPincodeAndSpecialization("AP1","Cardiologist");
        assertIterableEquals(doctorListBySpecializationAndPincode,doctorListResult);
    }

    @Test
    void filterDoctorOnPincodeAndSpecializationWhenBothNotExists(){
        Mockito.when(doctorRepository.findAllDoctorsBySpecializationAndPincode("","")).thenReturn(null);
        List<Doctor> doctorListResult = doctorService.filterDoctorOnPincodeAndSpecialization("","");
        assertNull(doctorListResult);
    }

    @Test
    void filterDoctorOnPincodeAndSpecializationWhenPincodeNotExists(){
        Mockito.when(doctorRepository.findAllDoctorsBySpecializationAndPincode("Cardiologist","")).thenReturn(null);
        List<Doctor> doctorListResult = doctorService.filterDoctorOnPincodeAndSpecialization("","Cardiologist");
        assertNull(doctorListResult);
    }

    @Test
    void filterDoctorOnPincodeAndSpecializationWhenSpecializationNotExists(){
        Mockito.when(doctorRepository.findAllDoctorsBySpecializationAndPincode("","AP1")).thenReturn(null);
        List<Doctor> doctorListResult = doctorService.filterDoctorOnPincodeAndSpecialization("AP1","");
        assertNull(doctorListResult);
    }

    @Test
    void getDoctorByIdWhenIdExists(){
        IDoctor doctor_result = doctorService.getDoctorById(doctor1.getDoctorId());
        assertEquals(doctor1,doctor_result);
    }

    @Test
    void getDoctorByIdWhenIdNotExists(){
        IDoctor doctor_result = doctorService.getDoctorById(doctor2.getDoctorId());
        assertNull(doctor_result);
    }

    @Test
    public void sortLabs(){
        List<Doctor> result = doctorService.sortDoctorList(unsortedList);
        assertIterableEquals(unsortedList,result);
    }
}
