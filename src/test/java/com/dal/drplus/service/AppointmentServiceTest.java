package com.dal.drplus.service;

import com.dal.drplus.model.entity.Appointment;
import com.dal.drplus.repository.implementation.AppointmentRepositoryImpl;
import com.dal.drplus.repository.interfaces.IAppointmentRepository;
import org.junit.jupiter.api.BeforeAll;
import org.junit.jupiter.api.Test;
import org.mockito.Mockito;

import java.util.ArrayList;
import java.util.List;

import static org.junit.jupiter.api.Assertions.assertFalse;
import static org.junit.jupiter.api.Assertions.assertTrue;

 public class AppointmentServiceTest {

    static Appointment appointment1 = new Appointment(100,"2022-07-17","10:00-11:00","",200,"p1","d1",1,2,"","DOCTOR");
    static Appointment appointment2 = new Appointment(7,"2022-07-17","10:00-11:00","",200,"p1","d1",1,2,"","DOCTOR");
    static Appointment appointment3 = new Appointment(101,"2022-07-17","02:00-04:00","",200,"p1","",1,2,"l1","LAB");
    static Appointment appointment4 = new Appointment(102,"2022-07-18","02:00-04:00","",200,"p2","",1,2,"l1","LAB");
    static int slotId1 = 20;
    static int slotId2 = 30;

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

        Mockito.when(appointmentRepository.deleteAppointmentbyLabID(appointment1.getLabId()))
                .thenReturn(IAppointmentRepository.StorageResult.SUCCESS);
        Mockito.when(appointmentRepository.deleteAppointmentbyLabID(appointment3.getLabId()))
                .thenReturn(IAppointmentRepository.StorageResult.FAILURE);

        Mockito.when(appointmentRepository.deleteAppointmentbyDoctorID(appointment1.getDoctorId()))
                .thenReturn(IAppointmentRepository.StorageResult.SUCCESS);
        Mockito.when(appointmentRepository.deleteAppointmentbyDoctorID(appointment3.getDoctorId()))
                .thenReturn(IAppointmentRepository.StorageResult.FAILURE);

        Mockito.when(appointmentRepository.deleteAppointmentbyPatientID(appointment1.getPatientId()))
                .thenReturn(IAppointmentRepository.StorageResult.SUCCESS);
        Mockito.when(appointmentRepository.deleteAppointmentbyPatientID(appointment4.getPatientId()))
                .thenReturn(IAppointmentRepository.StorageResult.FAILURE);

        Mockito.when(appointmentRepository.updateSlotId(slotId1,appointment1.getAppointmentId()))
                .thenReturn(IAppointmentRepository.StorageResult.SUCCESS);
        Mockito.when(appointmentRepository.updateSlotId(slotId2,appointment2.getAppointmentId()))
                .thenReturn(IAppointmentRepository.StorageResult.FAILURE);
        Mockito.when(appointmentRepository.updateAppointmentFee(1,34)).thenReturn(IAppointmentRepository.StorageResult.SUCCESS);
        Mockito.when(appointmentRepository.updateAppointmentFee(2,34)).thenReturn(IAppointmentRepository.StorageResult.FAILURE);

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
    void cancelAppointmentbyIdTrue(){
        boolean result = appointmentService.cancelAppointment(appointment2.getAppointmentId());
        assertTrue(result);
    }

    @Test
    void cancelAppointmentbyIdFail(){
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

    @Test
    void isAppointmentexistsforLabIdTrue(){
        List<Appointment> appointmentList = new ArrayList<>();
        appointmentList.add(appointment3);
        Mockito.when(appointmentRepository.findAppointmentByLabId(appointment3.getLabId()))
                .thenReturn(appointmentList);
        boolean result = appointmentService.isAppointmentexistsforLabId(appointment3.getLabId());
        assertTrue(result);
    }

    @Test
    void isAppointmentexistsforLabIdFalse(){
        List<Appointment> appointmentList = new ArrayList<>();
//        appointmentList.add(appointment2);
        Mockito.when(appointmentRepository.findAppointmentByLabId(appointment2.getLabId()))
                .thenReturn(appointmentList);
        boolean result = appointmentService.isAppointmentexistsforLabId(appointment2.getLabId());
        assertFalse(result);
    }

    @Test
    void deleteAppointmentbyLabIdTrue(){
        List<Appointment> appointmentList = new ArrayList<>();
        appointmentList.add(appointment1);
        Mockito.when(appointmentRepository.findAppointmentByLabId(appointment1.getLabId()))
                .thenReturn(appointmentList);

        boolean result = appointmentService.deleteAppointmentbyLabId(appointment1.getLabId());
        assertTrue(result);
    }

    @Test
    void deleteAppointmentbyLabIdFalse(){
        List<Appointment> appointmentList = new ArrayList<>();
        appointmentList.add(appointment3);
        Mockito.when(appointmentRepository.findAppointmentByLabId(appointment3.getLabId()))
                .thenReturn(appointmentList);

        boolean result = appointmentService.deleteAppointmentbyLabId(appointment3.getLabId());
        assertFalse(result);
    }

    @Test
     void isAppointmentexistsforPatientIdTrue(){
        List<Appointment> appointmentList = new ArrayList<>();
        appointmentList.add(appointment1);
        Mockito.when(appointmentRepository.findAppointmentByPatientId(appointment1.getLabId()))
                .thenReturn(appointmentList);
        boolean result = appointmentService.isAppointmentexistsforPatientId(appointment1.getLabId());
        assertTrue(result);
     }

     @Test
     void isAppointmentexistsforPatientIdFalse(){
         List<Appointment> appointmentList = new ArrayList<>();
         Mockito.when(appointmentRepository.findAppointmentByPatientId(appointment3.getLabId()))
                 .thenReturn(appointmentList);
         boolean result = appointmentService.isAppointmentexistsforPatientId(appointment3.getLabId());
         assertFalse(result);
     }

     @Test
     void deleteAppointmentbyPatientIdTrue(){
         List<Appointment> appointmentList = new ArrayList<>();
         appointmentList.add(appointment1);
         Mockito.when(appointmentRepository.findAppointmentByPatientId(appointment1.getLabId()))
                 .thenReturn(appointmentList);
         boolean result = appointmentService.deleteAppointmentbyPatientId(appointment1.getPatientId());
         assertTrue(result);
     }

     @Test
     void deleteAppointmentbyPatientIdFalse(){
         List<Appointment> appointmentList = new ArrayList<>();
         appointmentList.add(appointment4);
         Mockito.when(appointmentRepository.findAppointmentByPatientId(appointment4.getPatientId()))
                 .thenReturn(appointmentList);
         boolean result = appointmentService.deleteAppointmentbyPatientId(appointment4.getPatientId());
         assertFalse(result);
     }

     @Test
     void isAppointmentexistsforDoctorTrue(){
         List<Appointment> appointmentList = new ArrayList<>();
         appointmentList.add(appointment3);
         Mockito.when(appointmentRepository.findAppointmentByDoctorId(appointment3.getLabId()))
                 .thenReturn(appointmentList);
         boolean result = appointmentService.isAppointmentexistsforDoctor(appointment3.getLabId());
         assertTrue(result);
     }

     @Test
     void isAppointmentexistsforDoctorFalse(){
         List<Appointment> appointmentList = new ArrayList<>();
//         appointmentList.add(appointment4);
         Mockito.when(appointmentRepository.findAppointmentByDoctorId(appointment4.getLabId()))
                 .thenReturn(appointmentList);
         boolean result = appointmentService.isAppointmentexistsforDoctor(appointment4.getLabId());
         assertFalse(result);
     }

     @Test
     void deleteAppointmentbyDoctorIdTrue(){
         List<Appointment> appointmentList = new ArrayList<>();
         appointmentList.add(appointment1);
         Mockito.when(appointmentRepository.findAppointmentByDoctorId(appointment1.getLabId()))
                 .thenReturn(appointmentList);

         boolean result = appointmentService.deleteAppointmentbyDoctorId(appointment1.getDoctorId());
         assertTrue(result);
     }

     @Test
     void deleteAppointmentbyDoctorIdFalse(){
         List<Appointment> appointmentList = new ArrayList<>();
         appointmentList.add(appointment3);
         Mockito.when(appointmentRepository.findAppointmentByDoctorId(appointment3.getDoctorId()))
                 .thenReturn(appointmentList);
         boolean result = appointmentService.deleteAppointmentbyDoctorId(appointment3.getDoctorId());
         assertFalse(result);
     }

     @Test
     void rescheduleAppointmentTrue(){
        boolean result = appointmentService.rescheduleAppointment(slotId1, appointment1.getAppointmentId());
        assertTrue(result);
     }

     @Test
     void rescheduleAppointmentFalse(){
         boolean result = appointmentService.rescheduleAppointment(slotId2, appointment2.getAppointmentId());
         assertFalse(result);
     }

     @Test
     void updateAppointmentByBillIdTrue(){
        boolean result = appointmentService.updateAppointmentByBillId(1,34);
        assertTrue(result);
     }

     @Test
     void updateAppointmentByBillIdfalse(){
         boolean result = appointmentService.updateAppointmentByBillId(2,34);
         assertFalse(result);
     }
}
