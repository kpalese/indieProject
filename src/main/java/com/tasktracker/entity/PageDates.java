package com.tasktracker.entity;

import java.time.LocalDate;
import java.time.format.DateTimeFormatter;
import java.time.temporal.TemporalField;
import java.time.temporal.WeekFields;
import java.util.Locale;

/**
 * Calculates the calendar dates and days needed for each page in the planner
 */
public class PageDates {
    private LocalDate firstDateOfWeek;

    /**
     * Instantiates a new PageDates
     *
     * @param firstDateOfWeek the first date of week
     */
    public PageDates(LocalDate firstDateOfWeek) {
        this.firstDateOfWeek = firstDateOfWeek;
    }

    /**
     * Gets first date of week.
     *
     * @return the first date of week
     */
    public LocalDate getFirstDateOfWeek() {
        return firstDateOfWeek;
    }

    /**
     * Sets first date of week.
     *
     * @param firstDateOfWeek the first date of week
     */
    public void setFirstDateOfWeek(LocalDate firstDateOfWeek) {
        this.firstDateOfWeek = firstDateOfWeek;
    }

    public LocalDate getLastDateOfWeek() {
        TemporalField fieldUS = WeekFields.of(Locale.US).dayOfWeek();
        return getFirstDateOfWeek().with(fieldUS, 7);
    }

    public String getLocalDateToMDDYYYY(LocalDate localDate) {
        DateTimeFormatter dtf = DateTimeFormatter.ofPattern("M/dd/yyyy");
        return dtf.format(localDate);
    }
}
