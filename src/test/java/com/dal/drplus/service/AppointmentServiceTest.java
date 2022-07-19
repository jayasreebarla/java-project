package com.dal.drplus.service;

import com.dal.drplus.model.Appointment;
import com.dal.drplus.repository.implementation.AppointmentRepositoryImpl;
import com.dal.drplus.repository.interfaces.IAppointmentRepository;
import org.junit.jupiter.api.BeforeAll;
import org.junit.jupiter.api.Test;
import org.mockito.Mockito;

import static org.junit.jupiter.api.Assertions.assertFalse;
import static org.junit.jupiter.api.Assertions.assertTrue;

 public class AppointmentServiceTest {

    static Appointment appointment1 = new Appointment(100,"2022-07-17","10:00-11:00","",200,"p1","d1",1,2,"","DOCTOR");
    static Appointment appointment2 = new Appointment(7,"2022-07-17","10:00-11:00","",200,"p1","d1",1,2,"","DOCTOR");
    static Appointment appointment3 = new Appointment(101,"2022-07-17","02:00-04:00","",200,"p1","",1,2,"l1","LAB");
    static Appointment appointment4 = new Appointment(102,"2022-07-18","02:00-04:00","",200,"p1","",1,2,"l1","LAB");

    private static AppointmentService appointmentService;
    private static IAppointmentRepository appointmentRepository;

    @BeforeAll
    public static void init(){
        appointmentRepository = Mockito.mock((AppointmentRepositoryImpl.class));

        Mockito.when(appointmentRepository.saveAppointment(appointment1)).thenReturn(IAppointmentRepository.StorageResult.SUCCESS);
        Mockito.when(appointmentRepository.deleteAppointmentById(appointment1.getAppointmentId())).thenReturn(IAppointmentRepository.StorageResult.FAILURE);
        Mockito.when(appointmentRepository.deleteAppointmentById(appointment2.getAppointmentId())).thenReturn(IAppointmentRepository.StorageResult.SUCCESS);
        Mockito.when(appointmentRepository.isAppointmentConflict(appointment3.getAppointmentDate(), appointment3.getAppointmentTime(), appointment3.getPatientId()))
                .thenReturn(IAppointmentRepository.StorageResult.SUCCESS);
        Mockito.when(appointmentRepository.isAppointmentConflict(appointment4.getAppointmentDate(), appointment4.getAppointmentTime(), appointment4.getPatientId()))
                .thenReturn(IAppointmentRepository.StorageResult.FAILURE);
        appointmentService = new AppointmentService(appointmentRepository);
    }

    @Test
    void bookAppointmentTrue(){
        boolean result = appointmentService.bookAppointment(appointment1);
        assertTrue(result);
    }

    @Test
    void bookAppointmentFail(){
        boolean result = appointmentService.bookAppointment(appointment2);
        assertFalse(result);
    }

    @Test
    void deleteAppointmentbyIdTrue(){
        boolean result = appointmentService.cancelAppointment(appointment2.getAppointmentId());
        assertTrue(result);
    }

    @Test
    void deleteAppointmentbyIdFail(){
        boolean result = appointmentService.cancelAppointment(appointment3.getAppointmentId());
        assertFalse(result);
    }

    @Test
    void isConflictTrue(){
        boolean result = appointmentService.isAppointmentConflict(appointment3);
        assertTrue(result);
    }

    @Test
    void isConflictFalse(){
        boolean result = appointmentService.isAppointmentConflict(appointment4);
        assertFalse(result);
    }
}
