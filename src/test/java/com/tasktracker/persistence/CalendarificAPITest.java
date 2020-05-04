package com.tasktracker.persistence;

import com.calendarific.HolidaysItem;
import com.tasktracker.util.PropertiesLoader;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.junit.Test;

import java.util.List;
import static org.junit.jupiter.api.Assertions.assertEquals;

/**
 * TODO: comments
 */
public class CalendarificAPITest implements PropertiesLoader {
    private final Logger logger = LogManager.getLogger(this.getClass());


    @Test
    public void testCalendarificResponse() throws Exception {
        CalendarificAPI api = new CalendarificAPI();

        List <HolidaysItem> retrievedHolidays = api.getCalendarificResponse("2020").getResponse().getHolidays();
        HolidaysItem firstHoliday = retrievedHolidays.get(0);

        assertEquals("New Year's Day", firstHoliday.getName());
        assertEquals("New Year's Day is the first day of the Gregorian calendar, which is widely used in many countries such as the USA.", firstHoliday.getDescription());
    }
}
