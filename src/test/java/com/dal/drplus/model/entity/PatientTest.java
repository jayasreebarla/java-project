package com.dal.drplus.model.entity;

import com.dal.drplus.model.factory.ModelFactory;
import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.assertFalse;
import static org.junit.jupiter.api.Assertions.assertTrue;

public class PatientTest {
    private static Patient patient = (Patient) ModelFactory.instance().createPatient();

    private static String patientName1 = "deepthi";
    private static String patientName2 = "@l*pg";

    private static String patientPincode1 = "B3J2K9";
    private static String patientPincode2 = "@B*B-8";

    private static String patientEmail1 = "niceandsimple@gmail.com";
    private static String patientEmail2 = "waitwy@shd.com";

    private static String patientPhoneNumber1 = "902-989-8179";
    private static String patientPhoneNumber2 = "7826))98123";

    private static int patientAge1 = 20;
    private static int patientAge2 = -9;

    @Test
    void validatePatientNameTrue(){
        boolean result = patient.validatePatientNameFormat(patientName1);
        assertTrue(result);
    }

    @Test
    void validatePatientNameFalse(){
        boolean result = patient.validatePatientNameFormat(patientName2);
        assertFalse(result);
    }

    @Test
    void validatePatientPincodeTrue(){
        boolean result = patient.validatePatientPincodeFormat(patientPincode1);
        assertTrue(result);
    }

    @Test
    void validatePatientPincodeFalse(){
        boolean result = patient.validatePatientPincodeFormat(patientPincode2);
        assertFalse(result);
    }

    @Test
    void validatePatientEmailTrue(){
        boolean result = patient.validatePatientEmailFormat(patientEmail1);
        assertTrue(result);
    }

    @Test
    void validatePatientEmailFalse(){
        boolean result = patient.validatePatientEmailFormat(patientEmail2);
        assertFalse(result);
    }

    @Test
    void validatePatientPhoneNumberTrue(){
        boolean result = patient.validatePatientPhoneNumberFormat(patientPhoneNumber1);
        assertTrue(result);
    }

    @Test
    void validatePatientPhoneNumberFalse(){
        boolean result = patient.validatePatientPhoneNumberFormat(patientPhoneNumber2);
        assertFalse(result);
    }

    @Test
    void validatePatientAgeTrue(){
        boolean result = patient.validatePatientAgeFormat(patientAge1);
        assertTrue(result);
    }

    @Test
    void validatePatientAgeFalse(){
        boolean result = patient.validatePatientAgeFormat(patientAge2);
        assertFalse(result);
    }

}
