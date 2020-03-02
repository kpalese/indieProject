package edu.matc.persistence;

import edu.matc.entity.User;
import edu.matc.test.util.Database;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import java.util.List;

import static org.junit.jupiter.api.Assertions.*;

class UserDaoTest {
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

        genericDao = new GenericDao(User.class);
    }

    /**
     * Verify successful retrieval of a user
     */
    @Test
    void getByIdSuccess() {
        User retrievedUser = (User)genericDao.getById(3);
        assertNotNull(retrievedUser);
        assertEquals("WaltD", retrievedUser.getUserName());
        assertEquals("321xyz", retrievedUser.getUserPassword());
    }

    /**
     * Verify successful update user
     */
    @Test
    void updateSuccess() {
        String newUserName = "Jane_Donaldson";
        User userToUpdate = (User)genericDao.getById(2);
        userToUpdate.setUserName(newUserName);
        genericDao.saveOrUpdate(userToUpdate);
        User retrievedUser = (User)genericDao.getById(2);
        assertEquals(userToUpdate, retrievedUser);
    }

    /**
     * Verify successful insert of a user
     */
    @Test
    void insertSuccess() {

        User newUser = new User("FredFlintstone", "fflintstone55", autoFwdIncompleteTasks);
        int id = genericDao.insert(newUser);
        assertNotEquals(0,id);
        User insertedUser = (User)genericDao.getById(id);
        assertEquals(newUser, insertedUser);
   }

    /**
     * Verify successful delete of user
     */
    @Test
    void deleteSuccess() {
        genericDao.delete(genericDao.getById(3));
        assertNull(genericDao.getById(3));
    }

    /**
     * Verify successful retrieval of all users
     */
    @Test
    void getAllSuccess() {
        List<User> users = genericDao.getAll();
        assertEquals(4, users.size());
    }

    /**
     * Verify successful get by property (equal match)
     */
    @Test
    void getByPropertyEqualSuccess() {
        List<User> users = genericDao.getByPropertyEqual("userName", "Jane_Doe");
        assertEquals(1, users.size());
        assertEquals(2, users.get(0).getUserId());
    }

    /**
     * Verify successful get by property (like match)
     */
    @Test
    void getByPropertyLikeSuccess() {
        List<User> users = genericDao.getByPropertyLike("userName", "l");
        assertEquals(2, users.size());
    }
}