package com.dal.drplus.service;

import com.dal.drplus.model.Appointment;
import com.dal.drplus.repository.implementation.AppointmentRepositoryImpl;
import com.dal.drplus.repository.interfaces.IAppointmentRepository;
import org.junit.jupiter.api.BeforeAll;
import org.junit.jupiter.api.Test;
import org.mockito.Mockito;

import java.util.ArrayList;
import java.util.List;

import static org.junit.jupiter.api.Assertions.assertIterableEquals;

public class AppointmentListServiceTest {

    static Appointment appointment1 = new Appointment(100,"2022-07-17","10:00-11:00","",200,"p1","d1",1,2,"","DOCTOR");
    static Appointment appointment2 = new Appointment(7,"2022-07-18","10:00-11:00","",200,"p2","d1",1,2,"","DOCTOR");
    static Appointment appointment3 = new Appointment(101,"2022-07-19","02:00-04:00","",200,"p1","",1,2,"l1","LAB");
    static Appointment appointment4 = new Appointment(102,"2022-07-17","02:00-04:00","",200,"p2","",1,2,"l1","LAB");

    private static AppointmentListService appointmentListService;
    private static IAppointmentRepository appointmentRepository;

    @BeforeAll
    public static void init(){
        appointmentRepository = Mockito.mock(AppointmentRepositoryImpl.class);
        appointmentListService = new AppointmentListService(appointmentRepository);
    }

    @Test
    void listAppointmentByDoctorTest(){
        List<Appointment> appointmentListDoctor = new ArrayList<>();
        appointmentListDoctor.add(appointment1);
        appointmentListDoctor.add(appointment2);
        Mockito.when(appointmentRepository.findAppointmentByDoctorId(appointment1.getDoctorId()))
                .thenReturn(appointmentListDoctor);

        List<Appointment> appointmentList = appointmentListService.listAppointmentbyDoctor(appointment1.getDoctorId());
        List<Appointment> expectedAppointementList = new ArrayList<>();
        expectedAppointementList.add(appointment1);
        expectedAppointementList.add(appointment2);

        assertIterableEquals(expectedAppointementList,appointmentListDoctor);
    }

    @Test
    void listAppointmentByPatientTest(){
        List<Appointment> appointmentListPatient = new ArrayList<>();
        appointmentListPatient.add(appointment1);
        appointmentListPatient.add(appointment3);
        Mockito.when(appointmentRepository.findAppointmentByPatientId(appointment1.getPatientId()))
                .thenReturn(appointmentListPatient);

        List<Appointment> appointmentList = appointmentListService.listAppointmentbyPatient(appointment1.getPatientId());
        List<Appointment> expectedAppointementList = new ArrayList<>();
        expectedAppointementList.add(appointment1);
        expectedAppointementList.add(appointment3);

        assertIterableEquals(expectedAppointementList,appointmentListPatient);
    }

    @Test
    void listAppointmentByLabTest(){
        List<Appointment> appointmentListLab = new ArrayList<>();
        appointmentListLab.add(appointment3);
        appointmentListLab.add(appointment4);
        Mockito.when(appointmentRepository.findAppointmentByLabId(appointment3.getLabId()))
                .thenReturn(appointmentListLab);

        List<Appointment> appointmentList = appointmentListService.listAppointmentbyLab(appointment3.getLabId());
        List<Appointment> expectedAppointementList = new ArrayList<>();
        expectedAppointementList.add(appointment3);
        expectedAppointementList.add(appointment4);

        assertIterableEquals(expectedAppointementList,appointmentListLab);
    }

    @Test
    void listAppointmentAll(){
        List<Appointment> appointmentListAll = new ArrayList<>();
        appointmentListAll.add(appointment1);
        appointmentListAll.add(appointment2);
        appointmentListAll.add(appointment3);
        appointmentListAll.add(appointment4);
        Mockito.when(appointmentRepository.findAll())
                .thenReturn(appointmentListAll);

        List<Appointment> appointmentList = appointmentListService.listAppointmentAll();
        List<Appointment> expectedAppointementList = new ArrayList<>();
        expectedAppointementList.add(appointment1);
        expectedAppointementList.add(appointment2);
        expectedAppointementList.add(appointment3);
        expectedAppointementList.add(appointment4);

        assertIterableEquals(expectedAppointementList,appointmentListAll);
    }

    @Test
    void listAppointmentbyDoctorNDate(){
        List<Appointment> appointmentListDocNDate = new ArrayList<>();
        appointmentListDocNDate.add(appointment1);
        Mockito.when(appointmentRepository.findAppointmentByDoctorIdNDate(appointment1.getDoctorId(), appointment1.getAppointmentDate()))
                .thenReturn(appointmentListDocNDate);

        List<Appointment> appointmentList = appointmentListService.listAppointmentbyDoctorNDate(appointment1.getDoctorId(), appointment1.getAppointmentDate());
        List<Appointment> expectedAppointementList = new ArrayList<>();
        expectedAppointementList.add(appointment1);
        assertIterableEquals(expectedAppointementList,appointmentListDocNDate);
    }

    @Test
    void listAppointmentbyLabNDate(){
        List<Appointment> appointmentListLabNDate = new ArrayList<>();
        appointmentListLabNDate.add(appointment3);
        Mockito.when(appointmentRepository.findAppointmentByLabIdNDate(appointment3.getLabId(), appointment3.getAppointmentDate()))
                .thenReturn(appointmentListLabNDate);

        List<Appointment> appointmentList = appointmentListService.listAppointmentbyLabNDate(appointment3.getLabId(), appointment3.getAppointmentDate());
        List<Appointment> expectedAppointementList = new ArrayList<>();
        expectedAppointementList.add(appointment3);
        assertIterableEquals(expectedAppointementList,appointmentListLabNDate);
    }

    @Test
    void listAppointmentbyPatientNDate(){
        List<Appointment> appointmentListPatNDate = new ArrayList<>();
        appointmentListPatNDate.add(appointment2);
        Mockito.when(appointmentRepository.findAppointmentByPatientIdNDate(appointment2.getPatientId(), appointment2.getAppointmentDate()))
                .thenReturn(appointmentListPatNDate);

        List<Appointment> appointmentList = appointmentListService.listAppointmentbyPatientNDate(appointment2.getPatientId(), appointment2.getAppointmentDate());
        List<Appointment> expectedAppointementList = new ArrayList<>();
        expectedAppointementList.add(appointment2);
        assertIterableEquals(expectedAppointementList,appointmentListPatNDate);
    }


}
