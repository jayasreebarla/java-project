package com.dal.drplus.model.entity;
import com.dal.drplus.model.factory.ModelFactory;
import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.assertFalse;
import static org.junit.jupiter.api.Assertions.assertTrue;

public class PrescriptionTest {

    private static Prescription prescription = (Prescription) ModelFactory.instance().createPrescription();
    private static String prescriptionDetails1 = "abcd.txt";
    private static String prescriptionDetails2 = "abcd.pdf";

    @Test
    void validatePrescriptionDetailsTrue(){
        boolean result = prescription.validatePrescriptionDetails(prescriptionDetails2);
        assertTrue(result);
    }

    @Test
    void validatePrescriptionDetailsFalse(){
        boolean result = prescription.validatePrescriptionDetails(prescriptionDetails1);
        assertFalse(result);
    }
}
