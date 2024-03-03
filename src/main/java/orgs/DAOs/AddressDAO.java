package orgs.DAOs;


import jakarta.persistence.EntityManager;
import jakarta.persistence.EntityManagerFactory;
import orgs.Config.HibernateConfig;
import orgs.Entities.Address;
import orgs.Entities.Profile;
import orgs.Entities.User;

public class AddressDAO {
    private EntityManagerFactory emf = HibernateConfig.getEntityManagerFactoryConfig();

    public Address save(Address address) {
        EntityManager entityManager = emf.createEntityManager();
        entityManager.getTransaction().begin();
        entityManager.persist(address);
        entityManager.getTransaction().commit();
        entityManager.close();

        return address;
    }

    public Address updateAddress(Address address) {
        EntityManager entityManager = emf.createEntityManager();
        entityManager.getTransaction().begin();
        Address updatedAddress= entityManager.merge(address);
        entityManager.getTransaction().commit();
        entityManager.close();

        return updatedAddress;

    }

    public Address findById(int id) {
        EntityManager em = emf.createEntityManager();
        Address foundAddress = em.find(Address.class, id);
        em.close();
        return foundAddress;
    }

    public void deleteUser(int id) {
        EntityManager entityManager = emf.createEntityManager();
        entityManager.getTransaction().begin();
        Address address = findById(id);
        if (address != null) {
            entityManager.remove(address);
        }
        entityManager.getTransaction().commit();
        entityManager.close();

    }

    public void close() {
        emf.close();
    }
}

