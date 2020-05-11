package com.tasktracker.entity;

import java.time.DayOfWeek;
import java.time.LocalDate;
import java.time.format.DateTimeFormatter;
import java.time.temporal.TemporalField;

/**
 * Calculates the calendar dates and days needed for each page in the planner
 *
 * @author Kelly Palese
 */
public class PageDates {
    private LocalDate firstDateOfWeek;
    private TemporalField field;

    /**
     * Instantiates a new PageDates (empty constructor)
     */
    public PageDates() {
    }

    /**
     * Instantiates a new PageDates based on the first date of the week
     *
     * @param firstDateOfWeek the first date of week
     * @param field           the field
     */
    public PageDates(LocalDate firstDateOfWeek, TemporalField field) {
        this.firstDateOfWeek = firstDateOfWeek;
        this.field = field;
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

    /**
     * Gets a date based on its position in the week
     *
     * @param positionInWeek the position in week
     * @return the date of week
     */
    public LocalDate getDateOfWeek(int positionInWeek) {
        return getFirstDateOfWeek().with(field, positionInWeek);
    }

    /**
     * Gets date of a String based on its position in the week.
     *
     * @param stringPositionInWeek the string position in week
     * @return the date of week from string
     */
    public LocalDate getDateOfWeekFromString(String stringPositionInWeek) {
        int positionInWeek = Integer.parseInt(stringPositionInWeek);
        return getFirstDateOfWeek().with(field, positionInWeek);
    }

    /**
     * Gets the day of the week based on the date.
     *
     * @param dateOfWeek the date of week
     * @return the day of week
     */
    public DayOfWeek getDayOfWeek(LocalDate dateOfWeek) {
        return dateOfWeek.getDayOfWeek();
    }

    /**
     * Converts a local date to mddyyyy.
     *
     * @param localDate the local date
     * @return the local date to mddyyyy
     */
    public String getLocalDateToMDDYYYY(LocalDate localDate) {
        DateTimeFormatter dtf = DateTimeFormatter.ofPattern("M/dd/yyyy");
        return dtf.format(localDate);
    }

    /**
     * Converts a local date to mdd.
     *
     * @param localDate the local date
     * @return the local date to mdd
     */
    public String getLocalDateToMDD(LocalDate localDate) {
        DateTimeFormatter dtf = DateTimeFormatter.ofPattern("M/dd");
        return dtf.format(localDate);
    }
}
