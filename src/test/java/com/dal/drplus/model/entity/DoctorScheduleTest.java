package com.dal.drplus.model.entity;

import com.dal.drplus.model.factory.ModelFactory;
import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.assertFalse;
import static org.junit.jupiter.api.Assertions.assertTrue;

public class DoctorScheduleTest {
    private static DoctorSchedule doctorSchedule = (DoctorSchedule) ModelFactory.instance().createDoctorSchedule();
    private static String date1 = "2022-09-11";
    private static String date2 = "2022-07-10";
    @Test
    void validateSlotDateFormatTrue(){
        boolean result = doctorSchedule.validateSlotDateFormat(date1);
        assertTrue(result);
    }

    @Test
    void validateSlotDateFormatFalse(){
        boolean result = doctorSchedule.validateSlotDateFormat(date2);
        assertFalse(result);
    }

}
