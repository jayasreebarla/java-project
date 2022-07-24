package com.dal.drplus.service;
import com.dal.drplus.model.entity.Prescription;
import com.dal.drplus.repository.implementation.PrescriptionRepositoryImpl;
import com.dal.drplus.repository.interfaces.IPrescriptionRepository;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.BeforeAll;
import org.junit.jupiter.api.Test;
import org.mockito.Mockito;

import java.io.FileNotFoundException;

public class PrescriptionServiceTest {

    private static Prescription prescription = new Prescription();

    private static PrescriptionService prescriptionService;
    private static IPrescriptionRepository prescriptionRepository;

    @BeforeAll
    public static void init() throws FileNotFoundException {

        prescriptionRepository = Mockito.mock(PrescriptionRepositoryImpl.class);
        prescriptionRepository = Mockito.mock(PrescriptionRepositoryImpl.class);
        Mockito.when(prescriptionRepository.uploadPrescription(prescription)).thenReturn(IPrescriptionRepository.StorageResult.SUCCESS);
        Mockito.when(prescriptionRepository.uploadPrescription(prescription)).thenReturn(IPrescriptionRepository.StorageResult.FAILURE);
        prescriptionService = new PrescriptionService(prescriptionRepository);
    }

    @Test
    public void uploadPrescriptionPass() throws FileNotFoundException {
        Mockito.when(prescriptionRepository.uploadPrescription(prescription)).thenReturn(IPrescriptionRepository.StorageResult.SUCCESS);
        Assertions.assertEquals(true, prescriptionService.uploadPrescription(prescription));
    }

    @Test
    public void uploadPrescriptionFail() throws FileNotFoundException {
        Mockito.when(prescriptionRepository.uploadPrescription(prescription)).thenReturn(IPrescriptionRepository.StorageResult.FAILURE);
        Assertions.assertEquals(false, prescriptionService.uploadPrescription(prescription));
    }

    @Test
    public void downloadPrescriptionPass()throws Exception{
            Mockito.when(prescriptionRepository.findById(1)).thenReturn(prescription);
            Assertions.assertEquals(prescription, prescriptionService.downloadPrescription(1));
    }

    public void downloadPrescriptionFail(){

    }

    public void deletePrescriptionPass(){

    }

    public void deletePrescriptionFail(){

    }

}
