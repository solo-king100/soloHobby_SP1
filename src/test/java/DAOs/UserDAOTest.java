package DAOs;


import jakarta.persistence.EntityManager;
import jakarta.persistence.EntityManagerFactory;
import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import orgs.Config.HibernateConfig;
import orgs.DAOs.UserDAO;
import orgs.Entities.User;

import static org.junit.jupiter.api.Assertions.*;

class UserDAOTest {
    private EntityManagerFactory emf;
    private UserDAO userDAO;
    private EntityManager em;

    @BeforeEach
    void setUp() {
        emf = HibernateConfig.getEntityManagerFactoryConfig();
        userDAO = new UserDAO();
        em = emf.createEntityManager();
    }

    @AfterEach
    void tearDown() {
        if (em.isOpen()) {
            em.close();
        }
        userDAO.close();
    }

  @Test
    void testSaveFindUpdateAndDeleteUser() {
        // Save a new user
        User newUser = new User("tester1", "Copper", "testEmail@example.com");
        User savedUser = userDAO.save(newUser);

        // Find user
        User foundUser = userDAO.findById(savedUser.getId());
        assertEquals("tester1", foundUser.getUserName(), "Username should match what was saved");

        // Update user
        foundUser.setUserName("updatedUser");
        User updatedUser = userDAO.updateUser(foundUser);
        assertEquals("updatedUser", updatedUser.getUserName(), "Username should be updated");

        // Delete user
        userDAO.deleteUser(updatedUser.getId());
        User deletedUser = userDAO.findById(updatedUser.getId());
        assertNull(deletedUser, "User should be null after deletion");
    }
}
