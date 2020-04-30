package com.tasktracker.persistence;

import com.tasktracker.entity.Task;
import com.tasktracker.entity.Todo;
import com.tasktracker.entity.User;
import com.tasktracker.test.util.Database;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import java.time.LocalDate;
import java.util.List;

import static org.junit.jupiter.api.Assertions.*;

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

    /**
     * Verify successful insert of a to-do item
     */
    @Test
    void insertSuccess() {
        String name = "A new todo!";
        String notes = "Here's some notes about this fantastic todo item";
        GenericDao userDao = new GenericDao(User.class);
        User user = (User)userDao.getById(1);

        Todo newTodo = new Todo(name, notes, user);
        int id = genericDao.insert(newTodo);
        assertNotEquals(0,id);
        Todo insertedTodo = (Todo)genericDao.getById(id);
        assertEquals(newTodo, insertedTodo);
    }

    /**
     * Verify successful delete of a to-do item
     */
    @Test
    void deleteSuccess() {
        genericDao.delete(genericDao.getById(3));
        assertNull(genericDao.getById(3));
    }

    /**
     * Verify successful retrieval of all to-do items
     */
    @Test
    void getAllSuccess() {
        List<Todo> todos = genericDao.getAll();
        assertEquals(3, todos.size());
    }

    /**
     * Verify successful get by property (equal match)
     */
    @Test
    void getByPropertyEqualSuccess() {
        List<Todo> todos = genericDao.getByPropertyEqual("user", 1);
        assertEquals(2, todos.size());
        assertEquals(1, todos.get(0).getId());
    }

    /**
     * Verify successful get by property (like match)
     */
    @Test
    void getByPropertyLikeSuccess() {
        List<Todo> todos = genericDao.getByPropertyLike("name", "p");
        assertEquals(2, todos.size());
        assertEquals(1, todos.get(0).getId());
        assertEquals(2, todos.get(1).getId());
    }
}
