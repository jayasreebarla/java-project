package com.dal.drplus.model.entity;

import com.dal.drplus.model.factory.ModelFactory;
import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.assertFalse;
import static org.junit.jupiter.api.Assertions.assertTrue;

public class LabScheduleTest {
    private static LabSchedule labSchedule = (LabSchedule) ModelFactory.instance().createLabSchedule();
    private static String date1 = "2022-09-11";
    private static String date2 = "2020-10-10";
    @Test
    void validateSlotDateFormatTrue(){
        boolean result = labSchedule.validateSlotDateFormat(date1);
        assertTrue(result);
    }

    @Test
    void validateSlotDateFormatFalse(){
        boolean result = labSchedule.validateSlotDateFormat(date2);
        assertFalse(result);
    }

}
