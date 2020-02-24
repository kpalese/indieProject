package edu.matc.entity;

import org.hibernate.annotations.GenericGenerator;
import javax.persistence.*;
import java.time.LocalDate;
import java.util.Objects;

/**
 * A java bean to represent an event
 * @author Kelly Palese
 */

@Entity(name = "Event")
@Table(name = "EVENT")
public class Event {
    @Id
    @Column(name = "id")
    @GeneratedValue(strategy= GenerationType.AUTO, generator="native")
    @GenericGenerator(name = "native",strategy = "native")
    private int id;

    @Column(name = "name")
    private String name;

    //TODO: Figure out date and time issues and then update variables, getter and setters, constructor, equals() and hashCode()
//    @Column(name = "date")
//    private LocalDate date;

    //start and end times

    @Column(name = "notes")
    private String notes;

    @Column(name = "user")
    private int userId;

    /**
     * Instantiates a new Event (empty constructor)
     */
    public Event() {
    }

    /**
     * Instantiates a new Event.
     *
     * @param name   the name
     * @param notes  the notes
     * @param userId the user id
     */
    public Event(String name, String notes, int userId) {
        this.name = name;
        this.notes = notes;
        this.userId = userId;
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getNotes() {
        return notes;
    }

    public void setNotes(String notes) {
        this.notes = notes;
    }

    public int getUserId() {
        return userId;
    }

    public void setUserId(int userId) {
        this.userId = userId;
    }

    //TODO: Needs to be updated once I figure out the date and time issue
    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        Event event = (Event) o;
        return id == event.id &&
                userId == event.userId &&
                Objects.equals(name, event.name) &&
                Objects.equals(notes, event.notes);
    }

    //TODO: Needs to be updated once I figure out the date and time issue
    @Override
    public int hashCode() {
        return Objects.hash(id, name, notes, userId);
    }
}
