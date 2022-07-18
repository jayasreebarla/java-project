package com.dal.drplus.service;
import com.dal.drplus.model.Prescription;
import com.dal.drplus.repository.implementation.PrescriptionRepositoryImpl;
import com.dal.drplus.repository.interfaces.IPrescriptionRepository;
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
    public void uploadPrescriptionPass(){

    }

    public void uploadPrescriptionFail(){

    }

    public void downloadPrescriptionPass(){

    }

    public void downloadPrescriptionFail(){

    }

    public void deletePrescriptionPass(){

    }

    public void deletePrescriptionFail(){

    }

}
