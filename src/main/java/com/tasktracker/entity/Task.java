package com.tasktracker.entity;

import org.hibernate.annotations.GenericGenerator;

import javax.persistence.*;
import java.time.LocalDate;
import java.util.Objects;

/**
 * A java bean to represent a task
 *
 * @author Kelly Palese
 */
@Entity(name = "Task")
@Table(name = "TASK")
public class Task implements Comparable<Task> {
    @Id
    @Column(name = "id")
    @GeneratedValue(strategy= GenerationType.AUTO, generator="native")
    @GenericGenerator(name = "native",strategy = "native")
    private int id;

    @Column(name = "name")
    private String name;

    @Column(name = "date")
    private LocalDate date;

    @Column(name = "frequency")
    private String frequency;

    @Column(name = "weekly_DOW")
    private String weeklyTaskDayOfWeek;

    @Column(name = "notes")
    private String notes;

    @Column(name="last_date_completed")
    private LocalDate lastDateCompleted;

    @ManyToOne
    private User user;

    /**
     * Instantiates a new Task (empty constructor)
     */
    public Task() {
    }

    /**
     * Instantiates a new Task.
     * @param name      the name
     * @param date      the date
     * @param frequency the frequency
     * @param notes     the notes
     * @param lastDateCompleted the date the task was last completed
     * @param user      the user
     */
    public Task(String name, LocalDate date, String frequency, String weeklyTaskDayOfWeek, String notes, LocalDate lastDateCompleted, User user) {
        this.name = name;
        this.date = date;
        this.frequency = frequency;
        this.weeklyTaskDayOfWeek = weeklyTaskDayOfWeek;
        this.notes = notes;
        this.lastDateCompleted = lastDateCompleted;
        this.user = user;
    }

    /**
     * Gets id.
     *
     * @return the id
     */
    public int getId() {
        return id;
    }

    /**
     * Sets id.
     *
     * @param id the id
     */
    public void setId(int id) {
        this.id = id;
    }

    /**
     * Gets name.
     *
     * @return the name
     */
    public String getName() {
        return name;
    }

    /**
     * Sets name.
     *
     * @param name the name
     */
    public void setName(String name) {
        this.name = name;
    }

    /**
     * Gets date.
     *
     * @return the date
     */
    public LocalDate getDate() {
        return date;
    }

    /**
     * Sets date.
     *
     * @param date the date
     */
    public void setDate(LocalDate date) {
        this.date = date;
    }

    /**
     * Gets frequency.
     *
     * @return the frequency
     */
    public String getFrequency() {
        return frequency;
    }

    /**
     * Sets frequency.
     *
     * @param frequency the frequency
     */
    public void setFrequency(String frequency) {
        this.frequency = frequency;
    }

    /**
     * Gets weekly task day of week.
     *
     * @return the day of the week that weekly tasks occur
     */
    public String getWeeklyTaskDayOfWeek() {
        return weeklyTaskDayOfWeek;
    }

    /**
     * Sets weekly task day of week.
     *
     * @param weeklyTaskDayOfWeek the day of the week that weekly tasks occur
     */
    public void setWeeklyTaskDayOfWeek(String weeklyTaskDayOfWeek) {
        this.weeklyTaskDayOfWeek = weeklyTaskDayOfWeek;
    }

    /**
     * Gets notes.
     *
     * @return the notes
     */
    public String getNotes() {
        return notes;
    }

    /**
     * Sets notes.
     *
     * @param notes the notes
     */
    public void setNotes(String notes) {
        this.notes = notes;
    }

    /**
     * Gets last date completed.
     *
     * @return the last date completed
     */
    public LocalDate getLastDateCompleted() {
        return lastDateCompleted;
    }

    /**
     * Sets last date completed.
     *
     * @param lastDateCompleted the last date completed
     */
    public void setLastDateCompleted(LocalDate lastDateCompleted) {
        this.lastDateCompleted = lastDateCompleted;
    }

    /**
     * Gets user.
     *
     * @return the user
     */
    public User getUser() {
        return user;
    }

    /**
     * Sets user.
     *
     * @param user the user
     */
    public void setUser(User user) {
        this.user = user;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        Task task = (Task) o;
        return id == task.id &&
                Objects.equals(name, task.name) &&
                Objects.equals(date, task.date) &&
                Objects.equals(frequency, task.frequency) &&
                Objects.equals(weeklyTaskDayOfWeek, task.weeklyTaskDayOfWeek) &&
                Objects.equals(notes, task.notes) &&
                Objects.equals(lastDateCompleted, task.lastDateCompleted) &&
                Objects.equals(user, task.user);
    }

    @Override
    public int hashCode() {
        return Objects.hash(id, name, date, frequency, weeklyTaskDayOfWeek, notes, lastDateCompleted);
    }

    @Override
    public int compareTo(Task t) {
        if (getLastDateCompleted() == null || t.getLastDateCompleted() == null) {
            return 0;
        }
        return getLastDateCompleted().compareTo(t.getLastDateCompleted());
    }
}