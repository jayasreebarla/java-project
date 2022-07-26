package com.dal.drplus.model.entity;

import com.dal.drplus.model.factory.ModelFactory;
import org.junit.jupiter.api.BeforeAll;
import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.assertFalse;
import static org.junit.jupiter.api.Assertions.assertTrue;

public class DoctorTest {
    private static Doctor doctor1 = (Doctor) ModelFactory.instance().createDoctor();
    private static Doctor doctor2 = (Doctor) ModelFactory.instance().createDoctor();

    @BeforeAll
    public static void init(){
        doctor1.setDoctorId("d1");
        doctor1.setDoctorName("d1");
        doctor1.setDoctorCredentials("d1");
        doctor1.setDoctorEmail("q@gmail.com");
        doctor1.setDoctorPincode("d1");
        doctor1.setDoctorPhoneNo("79798798798");
        doctor1.setDoctorAge(57);
        doctor1.setDoctorFee(57);

        doctor2.setDoctorId("d2");
        doctor2.setDoctorName("*");
        doctor2.setDoctorCredentials("*");
        doctor2.setDoctorEmail("q@gamil.com");
        doctor2.setDoctorPincode("*");
        doctor2.setDoctorPhoneNo("aaaa");
        doctor2.setDoctorAge(0);
        doctor2.setDoctorFee(0);
    }

    @Test
    public void validateDoctorCredentialsTrue(){
        boolean result = doctor1.validateDoctorCredentials();
        assertTrue(result);
    }

    @Test
    public void validateDoctorCredentialsFalse(){
        boolean result = doctor2.validateDoctorCredentials();
        assertFalse(result);
    }

    @Test
    public void validateDoctorNameTrue(){
        boolean result = doctor1.validateDoctorName();
        assertTrue(result);

    }

    @Test
    public void validateDoctorNameFalse(){
        boolean result = doctor2.validateDoctorName();
        assertFalse(result);

    }

    @Test
    public void validateDoctorPincodeTrue(){
        boolean result = doctor1.validateDoctorPincode();
        assertTrue(result);

    }

    @Test
    public void validateDoctorPincodeFalse(){
        boolean result = doctor2.validateDoctorPincode();
        assertFalse(result);

    }

    @Test
    public void validateDoctorEmailTrue(){
        boolean result = doctor1.validateDoctorEmail();
        assertTrue(result);

    }

    @Test
    public void validateDoctorEmailFalse(){
        boolean result = doctor2.validateDoctorEmail();
        assertFalse(result);

    }

    @Test
    public void validatePhoneNumberTrue(){
        boolean result = doctor1.validatePhoneNumber();
        assertTrue(result);

    }

    @Test
    public void validatePhoneNumberFalse(){
        boolean result = doctor2.validatePhoneNumber();
        assertFalse(result);

    }

    @Test
    public void validateDoctorAgeTrue(){
        boolean result = doctor1.validateDoctorAge();
        assertTrue(result);

    }

    @Test
    public void validateDoctorAgeFalse(){
        boolean result = doctor2.validateDoctorAge();
        assertFalse(result);

    }

    @Test
    public void validateDoctorFeeTrue(){
        boolean result = doctor1.validateDoctorFee();
        assertTrue(result);

    }

    @Test
    public void validateDoctorFeeFalse(){
        boolean result = doctor2.validateDoctorFee();
        assertFalse(result);

    }
}
