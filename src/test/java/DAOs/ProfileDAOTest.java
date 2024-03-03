package DAOs;

import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import orgs.Config.HibernateConfig;
import orgs.DAOs.ProfileDAO;
import orgs.Entities.Profile;

import jakarta.persistence.EntityManager;

import static org.junit.jupiter.api.Assertions.*;

class ProfileDAOTest {

    private EntityManager em;
    private ProfileDAO profileDAO;

    @BeforeEach
    void setUp() {
        em = HibernateConfig.getEntityManagerFactoryConfig().createEntityManager();
        profileDAO = new ProfileDAO();
        em.getTransaction().begin();
    }

    @AfterEach
    void tearDown() {
        em.getTransaction().rollback(); // Rollback to avoid affecting other tests
        em.close();
    }

    @Test
    void testSaveAndFindProfile() {
        Profile profile = new Profile("Aang Baldy", 98989834, null);
        Profile savedProfile = profileDAO.save(profile);
        Profile foundProfile = profileDAO.findById(savedProfile.getId());
        assertEquals("Aang Baldy", foundProfile.getFullName());
    }
}
