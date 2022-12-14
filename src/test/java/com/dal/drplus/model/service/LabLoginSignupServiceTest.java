package com.dal.drplus.model.service;

import com.dal.drplus.model.entity.Lab;
import com.dal.drplus.model.service.LabLoginSignupService;
import com.dal.drplus.repository.implementation.LabRepositoryImpl;
import com.dal.drplus.repository.interfaces.ILabRepository;
import org.junit.jupiter.api.BeforeAll;
import org.junit.jupiter.api.Test;
import org.mockito.Mockito;

import static org.junit.jupiter.api.Assertions.*;

public class LabLoginSignupServiceTest {

    static Lab lab1 = new Lab ("lab1", "lab1", "lab1", "labtest1@gmail.com", "lab1", "123456", "123456",34,4);
    static Lab lab2 = new Lab ("lab2", "lab2", "lab2", "labtest2@gmail.com", "lab2", "123123", "123123",54,3);

    String labPassword = "lab1";
    String labPassword1 = "lab2";
    private static LabLoginSignupService labLoginSignupService;
    private static ILabRepository labRepository;

    @BeforeAll
    public static void init(){
        labRepository = Mockito.mock(LabRepositoryImpl.class);

        Mockito.when(labRepository.saveLab(lab1)).thenReturn(ILabRepository.StorageResult.SUCCESS);
        Mockito.when(labRepository.saveLab(lab2)).thenReturn(ILabRepository.StorageResult.FAILURE);
        Mockito.when(labRepository.getLabPasswordById("lab1")).thenReturn("lab1");
        labLoginSignupService = new LabLoginSignupService(labRepository);
    }

    @Test
    void registerLabFalseWhenStorageResultSuccess() {
        boolean result = labLoginSignupService.registerLab(lab1);
        assertTrue(result);
    }

    @Test
    void registerLabFalseWhenStorageResultFailure(){
        boolean result = labLoginSignupService.registerLab(lab2);
        assertFalse(result);
    }


    @Test
    void isLabCredentialsValidTrue(){
        boolean result = labLoginSignupService.isLabCredentialValid("lab1",labPassword);
        assertTrue(result);
    }

    @Test
    void isLabCredentialsValidFalse(){
        boolean result = labLoginSignupService.isLabCredentialValid("lab1",labPassword1);
        assertFalse(result);
    }
}
