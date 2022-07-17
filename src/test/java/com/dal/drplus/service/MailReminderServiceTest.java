package com.dal.drplus.service;

import com.dal.drplus.model.Patient;
import com.dal.drplus.repository.implementation.*;
import com.dal.drplus.repository.interfaces.IAppointmentRepository;
import com.dal.drplus.repository.interfaces.IDoctorScheduleRepository;
import com.dal.drplus.repository.interfaces.ILabScheduleRepository;
import com.dal.drplus.repository.interfaces.IPatientRepository;
import org.junit.jupiter.api.BeforeAll;
import org.junit.jupiter.api.Test;
import org.mockito.Mock;
import org.mockito.Mockito;
import static org.junit.jupiter.api.Assertions.*;
import java.util.ArrayList;
import java.util.List;


public class MailReminderServiceTest {

    static Patient patient1 = new Patient("d1", "d1", 10, "connect.parulraich@gmail.com", "9840984983", "d1", "d1", "d1", false);
    static Patient patient2 = new Patient("d2", "d2", 10, "connect.parulraich@gmail.com", "9840984983", "d2", "d2", "d2", false);
    static Patient patient3 = new Patient("P12", "P12", 10, "mandapativijay43@gmail.com", "9840984983", "P12", "P12", "P12", false);

    private static ILabScheduleRepository labScheduleRepository;
    private static IDoctorScheduleRepository doctorScheduleRepository;
    private static IPatientRepository patientRepository;

    private static IAppointmentRepository appointmentRepository;

    private static MailReminderService mailReminderService;
    @BeforeAll
    public static void init(){
        labScheduleRepository = Mockito.mock(LabScheduleRepositoryImpl.class);
        doctorScheduleRepository = Mockito.mock(DoctorScheduleRepositoryImpl.class);
        patientRepository = Mockito.mock(PatientRepositoryImpl.class);
        appointmentRepository = Mockito.mock(AppointmentRepositoryImpl.class);
        List<Integer> list = new ArrayList<>();
        list.add(1);
        list.add(2);
        list.add(4);

        List<Integer> emptyList = new ArrayList<>();
        Mockito.when(doctorScheduleRepository.getAllSlotIds("2022-07-18")).thenReturn(list);
        Mockito.when(doctorScheduleRepository.getAllSlotIds("2022-07-19")).thenReturn(emptyList);
        Mockito.when(appointmentRepository.getPatientIdBySlotId(1)).thenReturn("d1");
        Mockito.when(appointmentRepository.getPatientIdBySlotId(2)).thenReturn("d2");
        Mockito.when(appointmentRepository.getPatientIdBySlotId(4)).thenReturn("P12");
        Mockito.when(patientRepository.findPatientById("d1")).thenReturn(patient1);
        Mockito.when(patientRepository.findPatientById("d2")).thenReturn(patient2);
        Mockito.when(patientRepository.findPatientById("P12")).thenReturn(patient3);
        mailReminderService = new MailReminderService(labScheduleRepository,doctorScheduleRepository,appointmentRepository,patientRepository);
    }

    @Test
    void getPatientIdsToMailSuccess(){
        List<Patient> expected_patients = new ArrayList<>();
        expected_patients.add(patient1);
        expected_patients.add(patient2);
        expected_patients.add(patient3);
        List<Patient> patients = mailReminderService.getPatientIdsToMail("2022-07-18");
        assertIterableEquals(expected_patients,patients);
    }

    @Test
    void getPatientIdsToMailIsEmpty(){
        List<Patient> patients = mailReminderService.getPatientIdsToMail("2022-07-19");
        assertEquals(0,patients.size());
    }

}
