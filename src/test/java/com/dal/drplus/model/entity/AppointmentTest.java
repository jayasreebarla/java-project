package com.dal.drplus.model.entity;

import com.dal.drplus.model.factory.ModelFactory;
import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.assertFalse;
import static org.junit.jupiter.api.Assertions.assertTrue;

public class AppointmentTest {
    private static Appointment appointment = (Appointment) ModelFactory.instance().createAppointment();
    private static String appointmentDate1 = "2022-07-16";
    private static String appointmentDate2 = "2023-07-16";

    @Test
    void validateAdminAccessKeyTrue(){
        boolean result = appointment.validateAppointmentDate(appointmentDate2);
        assertTrue(result);
    }

    @Test
    void validateAdminAccessKeyFalse(){
        boolean result = appointment.validateAppointmentDate(appointmentDate1);
        assertFalse(result);
    }
}
