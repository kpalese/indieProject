package com.tasktracker.persistence;

import com.tasktracker.entity.Task;
import com.tasktracker.entity.Todo;
import com.tasktracker.test.util.Database;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import java.time.LocalDate;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertNotNull;

public class TodoDaoTest {
    GenericDao genericDao;

    @BeforeEach
    void setUp() {

        Database database = Database.getInstance();
        database.runSQL("cleandb.sql");

        genericDao = new GenericDao(Todo.class);
    }

    /**
     * Verify successful retrieval of a to do item
     */
    @Test
    void getByIdSuccess() {
        Todo retrievedTodo = (Todo)genericDao.getById(2);
        assertNotNull(retrievedTodo);
        assertEquals("Schedule dentist appt", retrievedTodo.getName());
        String expectedNotes = "They said schedule sometime before September";
        assertEquals(expectedNotes, retrievedTodo.getNotes());
    }

    /**
     * Verify successful update of a to-do
     */
    @Test
    void updateSuccess() {
        String newNotes = "Schedule before September but after July";
        Todo todoToUpdate = (Todo)genericDao.getById(2);
        todoToUpdate.setNotes(newNotes);
        genericDao.saveOrUpdate(todoToUpdate);
        Todo retrievedTodo = (Todo)genericDao.getById(2);
        assertEquals(todoToUpdate, retrievedTodo);
    }

}
