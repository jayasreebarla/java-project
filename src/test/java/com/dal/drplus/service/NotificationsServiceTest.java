
package com.dal.drplus.service;
import com.dal.drplus.model.entity.Appointment;
import com.dal.drplus.repository.implementation.NotificationsRepositoryImpl;
import com.dal.drplus.repository.interfaces.INotificationRepository;
import com.dal.drplus.service.interfaces.NotificationsService;
import org.junit.jupiter.api.BeforeAll;
import org.junit.jupiter.api.Test;
import org.mockito.Mockito;

import java.util.ArrayList;
import java.util.List;

import static org.junit.jupiter.api.Assertions.*;

public class NotificationsServiceTest {

    private static NotificationsService notificationsService;
    private static INotificationRepository notificationsRepository;


    static Appointment appointment1 = new Appointment(100,"2022-07-17","10:00-11:00","",200,"p1","d1",1,2,"","DOCTOR");
    static Appointment appointment2 = new Appointment(7,"2022-07-18","10:00-11:00","",200,"p2","d1",1,2,"","DOCTOR");
    static Appointment appointment3 = new Appointment(101,"2022-07-19","02:00-04:00","",200,"p1","",1,2,"l1","LAB");
    static Appointment appointment4 = new Appointment(102,"2022-07-17","02:00-04:00","",200,"p2","",1,2,"l1","LAB");


    @BeforeAll
    public static void init(){
        notificationsRepository = Mockito.mock(NotificationsRepositoryImpl.class);
        notificationsService = new NotificationsService(notificationsRepository);
    }

    @Test
    public  void testNotifyPatient()
    {
        List<Appointment> appointmentListPatient = new ArrayList<>();
        appointmentListPatient.add(appointment1);
        appointmentListPatient.add(appointment3);
        Mockito.when(notificationsRepository.notifyPatient(appointment1.getPatientId()))
                .thenReturn(appointmentListPatient);

        List<Appointment> appointmentList = notificationsService.notifyPatient(appointment1.getPatientId());
        List<Appointment> expectedAppointementList = new ArrayList<>();
        expectedAppointementList.add(appointment1);
        expectedAppointementList.add(appointment3);

        assertIterableEquals(expectedAppointementList,appointmentListPatient);
    }



    @Test
    public  void testNotifyDoctorPass()
    {
        List<Appointment> appointmentListDoctor = new ArrayList<>();
        appointmentListDoctor.add(appointment1);
        appointmentListDoctor.add(appointment2);
        Mockito.when(notificationsRepository.notifyDoctor(appointment1.getDoctorId()))
                .thenReturn(appointmentListDoctor);

        List<Appointment> appointmentList = notificationsService.notifyDoctor(appointment2.getDoctorId());
        List<Appointment> expectedAppointementList = new ArrayList<>();
        expectedAppointementList.add(appointment1);
        expectedAppointementList.add(appointment2);

        assertIterableEquals(expectedAppointementList,appointmentListDoctor);
    }


    @Test
    public  void testNotifyLabPass()
    {
        List<Appointment> appointmentListLab = new ArrayList<>();
        appointmentListLab.add(appointment3);
        appointmentListLab.add(appointment4);
        Mockito.when(notificationsRepository.notifyLab(appointment3.getLabId()))
                .thenReturn(appointmentListLab);

        List<Appointment> appointmentList = notificationsService.notifyLab(appointment3.getLabId());
        List<Appointment> expectedAppointementList = new ArrayList<>();
        expectedAppointementList.add(appointment3);
        expectedAppointementList.add(appointment4);

        assertIterableEquals(expectedAppointementList,appointmentListLab);
    }


}
