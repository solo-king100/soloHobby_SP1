package DAOs;

import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import orgs.Config.HibernateConfig;
import orgs.DAOs.HobbyDAO;
import orgs.Entities.Hobby;

import jakarta.persistence.EntityManager;

import static org.junit.jupiter.api.Assertions.*;

class HobbyDAOTest {

    private EntityManager em;
    private HobbyDAO hobbyDAO;

    @BeforeEach
    void setUp() {
        em = HibernateConfig.getEntityManagerFactoryConfig().createEntityManager();
        hobbyDAO = new HobbyDAO();
        em.getTransaction().begin();
    }

    @AfterEach
    void tearDown() {
        em.getTransaction().rollback();
        em.close();
    }

    @Test
    void testSaveAndFindHobby() {
        Hobby hobby = new Hobby("Hiking", "Climbing Very large mountains for adrenaline");
        Hobby savedHobby = hobbyDAO.save(hobby);

        Hobby foundHobby = hobbyDAO.findById(savedHobby.getId());
        assertEquals("Hiking", foundHobby.getName());
        assertEquals("Climbing Very large mountains for adrenaline", foundHobby.getDescription());
    }
}
