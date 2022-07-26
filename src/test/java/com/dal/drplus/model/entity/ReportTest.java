package com.dal.drplus.model.entity;

import com.dal.drplus.model.factory.ModelFactory;
import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.assertFalse;
import static org.junit.jupiter.api.Assertions.assertTrue;

public class ReportTest {
    private static Report report = (Report) ModelFactory.instance().createReport();
    private static String reportDetails1 = "abcd.txt";
    private static String reportDetails2 = "abcd.pdf";

    @Test
    void validateAdminAccessKeyTrue(){
        boolean result = report.validateReportDetails(reportDetails2);
        assertTrue(result);
    }

    @Test
    void validateAdminAccessKeyFalse(){
        boolean result = report.validateReportDetails(reportDetails1);
        assertFalse(result);
    }
}
