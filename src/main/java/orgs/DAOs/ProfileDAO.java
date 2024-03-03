package orgs.DAOs;

import jakarta.persistence.EntityManager;
import jakarta.persistence.EntityManagerFactory;
import orgs.Config.HibernateConfig;
import orgs.Entities.Profile;
import orgs.Entities.User;

import java.util.List;

public class ProfileDAO {
    private EntityManagerFactory emf = HibernateConfig.getEntityManagerFactoryConfig();

    public Profile save(Profile profile) {
        EntityManager entityManager = emf.createEntityManager();
        entityManager.getTransaction().begin();
        entityManager.persist(profile);
        entityManager.getTransaction().commit();
        entityManager.close();

        return profile;
    }

    public Profile updateProfile(Profile profile) {
        EntityManager entityManager = emf.createEntityManager();
        entityManager.getTransaction().begin();
        Profile updatedProfile = entityManager.merge(profile);
        entityManager.getTransaction().commit();
        entityManager.close();

        return updatedProfile;

    }

    public Profile findById(int id) {
        EntityManager em = emf.createEntityManager();
        Profile foundProfile = em.find(Profile.class, id);
        em.close();
        return foundProfile;
    }

    public void deleteUser(int id) {
        EntityManager entityManager = emf.createEntityManager();
        entityManager.getTransaction().begin();
        Profile profile = findById(id);
        if (profile != null) {
            entityManager.remove(profile);
        }
        entityManager.getTransaction().commit();
        entityManager.close();

    }

    /*public List<Profile> findProfilesByCityName(String cityName) {

        try(EntityManager em = emf.createEntityManager()) {
            return em.createQuery(
                            "SELECT p FROM Profile p WHERE p.address.cityName = :cityName", Profile.class)
                    .setParameter("cityName", cityName)
                    .getResultList();
        } finally {
            em.close();
        }
    }*/

    public void close() {
        emf.close();
    }
}
