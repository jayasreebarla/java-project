package com.dal.drplus.model.entity;

import com.dal.drplus.model.factory.ModelFactory;
import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.assertFalse;
import static org.junit.jupiter.api.Assertions.assertTrue;

public class LabTest {

    private static Lab lab = (Lab) ModelFactory.instance().createLab();


    @Test
    void validateLabPersonNameFormatTrue(){
        boolean result = lab.validateLabPersonNameFormat("Sherlock Holmes");
        assertTrue(result);
    }

    @Test
    void validateLabPincodeFormatTrue(){
        boolean result = lab.validateLabPincodeFormat("234567");
        assertTrue(result);
    }

    @Test
    void validateLabEmailIdFormatTrue(){
        boolean result = lab.validateLabEmailIdFormat("brainydeveloper4@gmail.com");
        assertTrue(result);
    }

    @Test
    void validateLabContactInfoFormatTrue(){
        boolean result = lab.validateLabContactInfoFormat("9099099999");
        assertTrue(result);
    }



}
