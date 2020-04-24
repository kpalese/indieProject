package com.tasktracker.persistence;

import com.tasktracker.entity.Task;
import com.tasktracker.entity.User;
import com.tasktracker.test.util.Database;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import java.time.LocalDate;
import java.util.List;

import static org.junit.jupiter.api.Assertions.*;

public class TaskDaoTest {
    GenericDao genericDao;

    @BeforeEach
    void setUp() {

        Database database = Database.getInstance();
        database.runSQL("cleandb.sql");

        genericDao = new GenericDao(Task.class);
    }

    /**
     * Verify successful retrieval of a task
     */
    @Test
    void getByIdSuccess() {
        Task retrievedTask = (Task)genericDao.getById(2);
        assertNotNull(retrievedTask);
        assertEquals("Call Mom", retrievedTask.getName());
        LocalDate expectedDate = LocalDate.parse("2020-05-01");
        assertEquals(expectedDate, retrievedTask.getDate());
    }

    /**
     * Verify successful update of a task
     */
    @Test
    void updateSuccess() {
        String newTaskName = "Mow lawn";
        Task taskToUpdate = (Task)genericDao.getById(1);
        taskToUpdate.setName(newTaskName);
        genericDao.saveOrUpdate(taskToUpdate);
        Task retrievedTask = (Task)genericDao.getById(1);
        assertEquals(taskToUpdate, retrievedTask);
    }

    /**
     * Verify successful insert of a task
     */
    @Test
    void insertSuccess() {
        String name = "Testing a new task";
        LocalDate date = LocalDate.parse("2020-05-23");
        String frequency = "once";
        String notes = "This new task is going to be great!";
        GenericDao userDao = new GenericDao(User.class);
        User user = (User)userDao.getById(1);

        Task newTask = new Task(name, date, frequency, notes, user);
        int id = genericDao.insert(newTask);
        assertNotEquals(0,id);
        Task insertedTask = (Task)genericDao.getById(id);
        assertEquals(newTask, insertedTask);
    }

    /**
     * Verify successful delete of a task
     */
    @Test
    void deleteSuccess() {
        genericDao.delete(genericDao.getById(3));
        assertNull(genericDao.getById(3));
    }

    /**
     * Verify successful retrieval of all tasks
     */
    @Test
    void getAllSuccess() {
        List<Task> tasks = genericDao.getAll();
        assertEquals(3, tasks.size());
    }

    /**
     * Verify successful get by property (equal match)
     */
    @Test
    void getByPropertyEqualSuccess() {
        List<Task> events = genericDao.getByPropertyEqual("user", 1);
        assertEquals(2, events.size());
        assertEquals(1, events.get(0).getId());
    }

    /**
     * Verify successful get by property (like match)
     */
    @Test
    void getByPropertyLikeSuccess() {
        List<Task> tasks = genericDao.getByPropertyLike("name", "er");
        assertEquals(2, tasks.size());
        assertEquals(1, tasks.get(0).getId());
        assertEquals(3, tasks.get(1).getId());
    }
}
