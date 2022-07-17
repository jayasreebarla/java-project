package com.dal.drplus.service;

import com.dal.drplus.model.Doctor;
import com.dal.drplus.repository.implementation.DoctorRepositoryImpl;
import com.dal.drplus.repository.interfaces.IDoctorRepository;
import org.junit.jupiter.api.BeforeAll;
import org.junit.jupiter.api.Test;
import org.mockito.Mockito;

import static org.junit.jupiter.api.Assertions.*;


public class DoctorLoginSignupServiceTest {
    static Doctor doctor = new Doctor("d1", "d1", "d1", "deepthisiromani@gmail.com", "9029898179", "F",30, "d1","Cardiologist", "d1","d1",10000);
    static Doctor doctor1 = new Doctor("d2", "d2", "d2", "deepthisiromani@gmail.com", "9029898179", "F",28,"d2", "Ophthalmologist", "d2", "d2", 20938);
    String confirmPassword = "d1";
    String confirmPassword1 = "d2";
    private static DoctorLoginSignupService doctorLoginSignupService;
    private static IDoctorRepository doctorRepository;

    @BeforeAll
    public static void init(){
        doctorRepository = Mockito.mock(DoctorRepositoryImpl.class);
        Mockito.when(doctorRepository.saveDoctor(doctor)).thenReturn(IDoctorRepository.StorageResult.SUCCESS);
        Mockito.when(doctorRepository.saveDoctor(doctor1)).thenReturn(IDoctorRepository.StorageResult.FAILURE);
        Mockito.when(doctorRepository.getDoctorPasswordById("d1")).thenReturn("d1");
        doctorLoginSignupService = new DoctorLoginSignupService(doctorRepository);
    }

    @Test
    void registerDoctorTrue(){
        boolean result = doctorLoginSignupService.registerDoctor(doctor,confirmPassword);
        assertTrue(result);
    }

    @Test
    void registerDoctorFalseWhenStorageResultFailure(){
        boolean result = doctorLoginSignupService.registerDoctor(doctor1,confirmPassword1);
        assertFalse(result);
    }

    @Test
    void registerFalseWhenConfirmPasswordNotEqualsPassword(){
        boolean result = doctorLoginSignupService.registerDoctor(doctor,confirmPassword1);
        assertFalse(result);
    }

    @Test
    void isDoctorCredentialsValidTrue(){
        boolean result = doctorLoginSignupService.isDoctorCredentialValid("d1",confirmPassword);
        assertTrue(result);
    }

    @Test
    void isDoctorCredentialsValidFalse(){
        boolean result = doctorLoginSignupService.isDoctorCredentialValid("d1",confirmPassword1);
        assertFalse(result);
    }
}
