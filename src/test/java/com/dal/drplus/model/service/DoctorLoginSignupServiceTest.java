package com.dal.drplus.model.service;

import com.dal.drplus.model.IEntity.IDoctor;
import com.dal.drplus.model.factory.ModelFactory;
import com.dal.drplus.model.service.DoctorLoginSignupService;
import com.dal.drplus.repository.implementation.DoctorRepositoryImpl;
import com.dal.drplus.repository.interfaces.IDoctorRepository;
import org.junit.jupiter.api.BeforeAll;
import org.junit.jupiter.api.Test;
import org.mockito.Mockito;
import static org.junit.jupiter.api.Assertions.*;


public class DoctorLoginSignupServiceTest {
    static IDoctor doctor = ModelFactory.instance().createDoctor();
    static IDoctor doctor1 = ModelFactory.instance().createDoctor();
    String confirmPassword = "d1";
    String confirmPassword1 = "d2";
    private static DoctorLoginSignupService doctorLoginSignupService;
    private static IDoctorRepository doctorRepository;

    @BeforeAll
    public static void init(){
        doctor.setDoctorId("d1");
        doctor.setDoctorName("d1");
        doctor.setDoctorPassword("d1");
        doctor.setDoctorEmail("deepthisiromani@gmail.com");
        doctor.setDoctorPhoneNo("9029898179");
        doctor.setDoctorGender("F");
        doctor.setDoctorAge(30);
        doctor.setDoctorCredentials("d1");
        doctor.setDoctorSpecialization("Cardiologist");
        doctor.setDoctorClinicAddress("d1");
        doctor.setDoctorPincode("d1");
        doctor.setDoctorRating(5);
        doctor.setDoctorFee(10000);

        doctor1.setDoctorId("d2");
        doctor1.setDoctorName("d2");
        doctor1.setDoctorPassword("d2");
        doctor1.setDoctorEmail("deepthisiromani@gmail.com");
        doctor1.setDoctorPhoneNo("9029898179");
        doctor1.setDoctorGender("F");
        doctor1.setDoctorAge(30);
        doctor1.setDoctorCredentials("d2");
        doctor1.setDoctorSpecialization("Cardiologist");
        doctor1.setDoctorClinicAddress("d2");
        doctor1.setDoctorPincode("d2");
        doctor1.setDoctorRating(5);
        doctor1.setDoctorFee(10000);

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
