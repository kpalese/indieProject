package edu.matc.persistence;

import edu.matc.entity.User;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import java.util.List;

import static org.junit.jupiter.api.Assertions.*;

class UserDaoTest {
    UserDao dao;

    /**
     * Run set up tasks before each test:
     * 1. execute sql which deletes everything from the table and inserts records)
     * 2. Create any objects needed in the tests
     */
    @BeforeEach
    void setUp() {

        edu.matc.test.util.Database database = edu.matc.test.util.Database.getInstance();
        database.runSQL("cleandb.sql");

        dao = new UserDao();
    }

    /**
     * Verify successful retrieval of a user
     */
    @Test
    void getByIdSuccess() {
        User retrievedUser = dao.getById(3);
        assertEquals("WaltD", retrievedUser.getUserName());
        assertEquals("321xyz", retrievedUser.getUserPassword());
    }

    /**
     * Verify successful update user
     */
    @Test
    void updateSuccess() {
        String newUserName = "Jane_Donaldson";
        User userToUpdate = dao.getById(2);
        userToUpdate.setUserName(newUserName);
        dao.saveOrUpdate(userToUpdate);
        User retrievedUser = dao.getById(2);
        assertEquals(newUserName, retrievedUser.getUserName());
    }

    /**
     * Verify successful insert of a user
     */
    @Test
    void insertSuccess() {

        User newUser = new User("FredFlintstone", "fflintstone55");
        int id = dao.insert(newUser);
        assertNotEquals(0,id);
        User insertedUser = dao.getById(id);
        assertEquals("FredFlintstone", insertedUser.getUserName());
        assertEquals("fflintstone55", insertedUser.getUserPassword());
        // Could continue comparing all values, but
        // it may make sense to use .equals()
        // TODO review .equals recommendations http://docs.jboss.org/hibernate/orm/5.2/userguide/html_single/Hibernate_User_Guide.html#mapping-model-pojo-equalshashcode
    }

    /**
     * Verify successful delete of user
     */
    @Test
    void deleteSuccess() {
        dao.delete(dao.getById(3));
        assertNull(dao.getById(3));
    }

    /**
     * Verify successful retrieval of all users
     */
    @Test
    void getAllSuccess() {
        List<User> users = dao.getAll();
        assertEquals(4, users.size());
    }

    /**
     * Verify successful get by property (equal match)
     */
    @Test
    void getByPropertyEqualSuccess() {
        List<User> users = dao.getByPropertyLike("userName", "Jane_Doe");
        assertEquals(1, users.size());
        assertEquals(2, users.get(0).getUserId());
    }

    /**
     * Verify successful get by property (like match)
     */
    @Test
    void getByPropertyLikeSuccess() {
        List<User> users = dao.getByPropertyLike("userName", "l");
        assertEquals(2, users.size());
    }
}