package com.tasktracker.persistence;

import com.calendarific.HolidaysItem;
import com.tasktracker.util.PropertiesLoader;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.junit.Test;

import java.util.List;
import java.util.Properties;
import static org.junit.jupiter.api.Assertions.assertEquals;

/**
 * TODO: comments
 */
public class CalendarificAPITest implements PropertiesLoader {
    private final Logger logger = LogManager.getLogger(this.getClass());

    public CalendarificAPITest() throws Exception {
    }


    @Test
    public void testCalendarificResults() throws Exception {

        CalendarificAPI api = new CalendarificAPI();
        HolidaysItem retrievedHoliday = api.getCalendarificResults("2020").getResponse().getHolidays().get(0);

        assertEquals("New Year's Day", retrievedHoliday.getName());
        //assertEquals("New Year's Day is the first day of the Gregorian calendar, which is widely used in many countries such as the USA.", retrievedHoliday.getDescription());
    }
}
