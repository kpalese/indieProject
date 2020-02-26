package edu.matc.persistence;

import edu.matc.entity.Event;
import edu.matc.entity.User;
import edu.matc.test.util.Database;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import java.time.LocalDate;
import java.time.LocalTime;
import java.util.List;

import static org.junit.jupiter.api.Assertions.*;
import static org.junit.jupiter.api.Assertions.assertEquals;

public class EventDaoTest {
    GenericDao genericDao;

    /**
     * Run set up tasks before each test:
     * 1. execute sql which deletes everything from the table and inserts records)
     * 2. Create any objects needed in the tests
     */
    @BeforeEach
    void setUp() {

        Database database = Database.getInstance();
        database.runSQL("cleandb.sql");

        genericDao = new GenericDao(Event.class);
    }

    /**
     * Verify successful retrieval of an event
     */
    @Test
    void getByIdSuccess() {
        Event retrievedEvent = (Event)genericDao.getById(2);
        assertNotNull(retrievedEvent);
        assertEquals("Dentist", retrievedEvent.getName());
    }

    /**
     * Verify successful update event
     */
    @Test
    void updateSuccess() {
        String newEventName = "Coffee meeting";
        Event eventToUpdate = (Event)genericDao.getById(5);
        eventToUpdate.setName(newEventName);
        genericDao.saveOrUpdate(eventToUpdate);
        Event retrievedEvent = (Event)genericDao.getById(5);
        assertEquals(eventToUpdate, retrievedEvent);
    }

    /**
     * Verify successful insert of an event
     */
    @Test
    void insertSuccess() {
        String name = "Testing a new event";
        LocalDate date = LocalDate.parse("2020-05-23");
        LocalTime startTime = LocalTime.parse("11:30");
        LocalTime endTime = LocalTime.parse("13:45");
        String notes = "Here are some new notes for my new event.";
        GenericDao userDao = new GenericDao(User.class);
        User user = (User)userDao.getById(1);

        Event newEvent = new Event(name, date, startTime, endTime, notes, user);
        int id = genericDao.insert(newEvent);
        assertNotEquals(0,id);
        Event insertedEvent = (Event)genericDao.getById(id);
        assertEquals(newEvent, insertedEvent);
    }

    /**
     * Verify successful delete of event
     */
    @Test
    void deleteSuccess() {
        genericDao.delete(genericDao.getById(3));
        assertNull(genericDao.getById(3));
    }

    /**
     * Verify successful retrieval of all events
     */
    @Test
    void getAllSuccess() {
        List<Event> events = genericDao.getAll();
        assertEquals(5, events.size());
    }

    /**
     * Verify successful get by property (equal match)
     */
    @Test
    void getByPropertyEqualSuccess() {
        List<Event> events = genericDao.getByPropertyEqual("name", "Dentist");
        assertEquals(1, events.size());
        assertEquals(2, events.get(0).getId());
    }

    /**
     * Verify successful get by property (like match)
     */
    @Test
    void getByPropertyLikeSuccess() {
        List<Event> events = genericDao.getByPropertyLike("name", "ti");
        assertEquals(2, events.size());
        assertEquals(1, events.get(0).getId());
        assertEquals(2, events.get(1).getId());
    }
}
