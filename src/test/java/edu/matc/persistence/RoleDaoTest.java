package edu.matc.persistence;

import edu.matc.entity.Role;
import edu.matc.entity.User;
import edu.matc.test.util.Database;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import java.util.List;

import static org.junit.jupiter.api.Assertions.*;
import static org.junit.jupiter.api.Assertions.assertEquals;

public class RoleDaoTest {
    GenericDao genericDao;

    @BeforeEach
    void setUp() {

        Database database = Database.getInstance();
        database.runSQL("cleandb.sql");

        genericDao = new GenericDao(Role.class);
    }

    /**
     * Verify successful retrieval of a role
     */
    @Test
    void getByIdSuccess() {
        GenericDao userDao = new GenericDao(User.class);
        User user = (User)userDao.getById(2);
        Role role = (Role)genericDao.getById(2);
        assertNotNull(role);
        assertEquals(user, role.getUser());
    }

    /**
     * Verify successful insert of a role
     */
    @Test
    void insertSuccess() {
        GenericDao userDao = new GenericDao(User.class);
        User user = (User)userDao.getById(4);
        Role newRole = new Role(user, "user", "FlowerDaisy");
        user.addRole(newRole);

        int id = genericDao.insert(newRole);
        Role insertedRole = (Role)genericDao.getById(id);
        assertNotEquals(0,id);
        assertEquals(user, insertedRole.getUser());
    }

    /**
     * Verify successful delete of a user's role
     */
    @Test
    void deleteSuccess() {
        genericDao.delete(genericDao.getById(1));

        GenericDao userDao = new GenericDao(User.class);
        User userWithDeletedRole = (User)userDao.getById(1);

        assertNull(genericDao.getById(1));
        assertEquals(0, userWithDeletedRole.getRoles().size());
    }

    /**
     * Verify successful retrieval of all roles
     */
    @Test
    void getAllSuccess() {
        List<Role> roles = genericDao.getAll();
        assertEquals(4, roles.size());
    }

    /**
     * Verify successful get by property (equal match)
     */
    @Test
    void getByPropertyEqualSuccess() {
        List<Role> roles = genericDao.getByPropertyEqual("roleName", "user");
        assertEquals(4, roles.size());
        assertEquals(1, roles.get(0).getId());
    }

    /**
     * Verify successful get by property (like match)
     */
    @Test
    void getByPropertyLikeSuccess() {
        List<Role> roles = genericDao.getByPropertyLike("roleName", "u");
        assertEquals(4, roles.size());
        assertEquals(1, roles.get(0).getId());
    }
}
