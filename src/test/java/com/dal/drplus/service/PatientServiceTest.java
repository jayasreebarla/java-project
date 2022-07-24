package com.dal.drplus.service;

import com.dal.drplus.model.entity.Patient;
import com.dal.drplus.repository.implementation.PatientRepositoryImpl;
import com.dal.drplus.repository.interfaces.IPatientRepository;
import org.junit.jupiter.api.BeforeAll;
import org.junit.jupiter.api.Test;
import org.mockito.Mockito;

import java.util.ArrayList;
import java.util.List;

import static org.junit.jupiter.api.Assertions.*;
public class PatientServiceTest {

    static Patient patient1 = new Patient("d1", "d1", 10, "connect.parulraich@gmail.com", "9840984983", "d1", "d1", "d1", false);
    static Patient patient2 = new Patient("d2", "d2", 10, "connect.parulraich@gmail.com", "9840984983", "d2", "d2", "d2", false);
    static Patient patient3 = new Patient("d3", "d3", 10, "connect.parulraich@gmail.com", "9840984983", "d3", "d3", "d3", false);

    private static PatientService patientService;
    private static IPatientRepository patientRepository;

    @BeforeAll
    public static void init(){
        patientRepository = Mockito.mock(PatientRepositoryImpl.class);
        Mockito.when(patientRepository.findPatientById(patient1.getPatientId())).thenReturn(patient1);
        //Mockito.when(patientRepository.findPatientById(patient1.getPatientId())).thenReturn(null);
        Mockito.when(patientRepository.deletePatientById(patient1.getPatientId())).thenReturn(IPatientRepository.StorageResult.SUCCESS);
        Mockito.when(patientRepository.deletePatientById(patient2.getPatientId())).thenReturn(IPatientRepository.StorageResult.FAILURE);
        patientService = new PatientService(patientRepository);
    }

    @Test
    void listAllPatientsWhenListExists(){
        List<Patient> patients = new ArrayList<>();
        patients.add(patient1);
        patients.add(patient2);
        patients.add(patient3);
        Mockito.when(patientRepository.findAll()).thenReturn(patients);
        List<Patient> patients_result = patientService.listAllPatients();
        List<Patient> expectedPatients = new ArrayList<>();
        expectedPatients.add(patient1);
        expectedPatients.add(patient2);
        expectedPatients.add(patient3);
        assertIterableEquals(expectedPatients,patients_result);
    }

    @Test
    void listAllPatientsWhenListNull(){
        Mockito.when(patientRepository.findAll()).thenReturn(null);
        List<Patient> patients_result = patientService.listAllPatients();
        assertIterableEquals(null,patients_result);
    }

    @Test
    void listAllPatientsWhenListEmpty(){
        List<Patient> patients = new ArrayList<>();
        Mockito.when(patientRepository.findAll()).thenReturn(patients);
        assertEquals(0,patients.size());
    }

    @Test
    void getPatientByIdPass(){
        Patient patient_result =  patientService.getPatientById(patient1.getPatientId());
        assertEquals(patient1,patient_result);
    }

    @Test
    void getPatientByIdFailNull(){
        Patient patient_result =  patientService.getPatientById(patient2.getPatientId());
        assertNull(patient_result);
    }

    @Test
    void deletePatientByIdTrue(){
        boolean result = patientService.deletePatientById(patient1.getPatientId());
        assertTrue(result);
    }

    @Test
    void deletePatientByIdFalse(){
        boolean result = patientService.deletePatientById(patient2.getPatientId());
        assertFalse(result);
    }
}
