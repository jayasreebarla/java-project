package com.dal.drplus.service;

import com.dal.drplus.model.entity.Patient;
import com.dal.drplus.repository.implementation.PatientRepositoryImpl;
import com.dal.drplus.repository.interfaces.IAdminRepository;
import com.dal.drplus.repository.interfaces.IPatientRepository;
import org.junit.jupiter.api.BeforeAll;
import org.junit.jupiter.api.Test;
import org.mockito.Mockito;

import static org.junit.jupiter.api.Assertions.*;

public class PatientLoginSignupServiceTest {
/* */
    static Patient patient = new Patient("d1", "d1", 10, "connect.parulraich@gmail.com", "9840984983", "d1", "d1", "d1", false);
    static Patient patient1 = new Patient("d2", "d2", 10, "connect.parulraich@gmail.com", "9840984983", "d2", "d2", "d2", false);
    String confirmPassword = "d1";
    String confirmPassword1 = "d2";
    private static PatientLoginSignupService patientLoginSignupService;
    private static IPatientRepository patientRepository;

    @BeforeAll
    public static void init(){
        patientRepository = Mockito.mock(PatientRepositoryImpl.class);
        Mockito.when(patientRepository.savePatient(patient)).thenReturn(IPatientRepository.StorageResult.SUCCESS);
        Mockito.when(patientRepository.savePatient(patient1)).thenReturn(IPatientRepository.StorageResult.FAILURE);
        Mockito.when(patientRepository.getPatientPasswordById("d1")).thenReturn("d1");
        Mockito.when(patientRepository.isPatientIdExists(patient.getPatientId())).thenReturn(IPatientRepository.StorageResult.SUCCESS);
        Mockito.when(patientRepository.isPatientIdExists(patient1.getPatientId())).thenReturn(IPatientRepository.StorageResult.FAILURE);

        patientLoginSignupService = new PatientLoginSignupService(patientRepository);
    }

    @Test
    void registerPatientTrue(){
        boolean result = patientLoginSignupService.registerPatient(patient,confirmPassword);
        assertTrue(result);
    }

    @Test
    void registerPatientFalseWhenStorageResultFailure(){
        boolean result = patientLoginSignupService.registerPatient(patient1,confirmPassword1);
        assertFalse(result);
    }

    @Test
    void registerFalseWhenConfirmPasswordNotEqualsPassword(){
        boolean result = patientLoginSignupService.registerPatient(patient,confirmPassword1);
        assertFalse(result);
    }

    @Test
    void isPatientCredentialsValidTrue(){
        boolean result = patientLoginSignupService.isPatientCredentialValid("d1",confirmPassword);
        assertTrue(result);
    }

    @Test
    void isPatientCredentialsValidFalse(){
        boolean result = patientLoginSignupService.isPatientCredentialValid("d1",confirmPassword1);
        assertFalse(result);
    }

    @Test
    void isPatientIdExistsTrue(){
        boolean result = patientLoginSignupService.isPatientIdExists(patient.getPatientId());
        assertTrue(result);
    }

    @Test
    void isPatientIdExistsFalse(){
        boolean result = patientLoginSignupService.isPatientIdExists(patient1.getPatientId());
        assertFalse(result);
    }
}
