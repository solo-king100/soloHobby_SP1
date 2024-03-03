package DAOs;

import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import orgs.Config.HibernateConfig;
import orgs.DAOs.AddressDAO;
import orgs.Entities.Address;

import jakarta.persistence.EntityManager;

import static org.junit.jupiter.api.Assertions.*;

class AddressDAOTest {

    private EntityManager em;
    private AddressDAO addressDAO;

    @BeforeEach
    void setUp() {
        em = HibernateConfig.getEntityManagerFactoryConfig().createEntityManager();
        addressDAO = new AddressDAO();
        em.getTransaction().begin();
    }

    @AfterEach
    void tearDown() {
        em.getTransaction().rollback();
        em.close();
    }

    @Test
    void testSaveAndFindAddress() {
        Address address = new Address("Kastrup", 2770);
        Address savedAddress = addressDAO.save(address);
        Address foundAddress = addressDAO.findById(savedAddress.getId());
        assertEquals("Kastrup", foundAddress.getCityName());
        assertEquals(2770, foundAddress.getPostCode());
    }
}
