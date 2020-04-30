package com.tasktracker.entity;

import org.hibernate.annotations.GenericGenerator;

import javax.persistence.*;
import java.util.Objects;

/**
 * A java bean to represent a o do list item
 *
 * @author Kelly Palese
 */
@Entity(name = "Todo")
@Table(name = "TODO")
public class Todo {
    @Id
    @Column(name = "id")
    @GeneratedValue(strategy= GenerationType.AUTO, generator="native")
    @GenericGenerator(name = "native",strategy = "native")
    private int id;

    @Column(name = "name")
    private String name;

    @Column(name = "notes")
    private String notes;

    @ManyToOne
    private User user;

    /**
     * Instantiates a new to-do (empty constructor)
     */
    public Todo() {
    }

    /**
     * Instantiates a new To-do.
     *
     * @param name  the name
     * @param notes the notes
     * @param user  the user
     */
    public Todo(String name, String notes, User user) {
        this.name = name;
        this.notes = notes;
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
        Todo todo = (Todo) o;
        return id == todo.id &&
                Objects.equals(name, todo.name) &&
                Objects.equals(notes, todo.notes) &&
                Objects.equals(user, todo.user);
    }

    @Override
    public int hashCode() {
        return Objects.hash(id, name, notes);
    }
}
