package orgs.DAOs;

import jakarta.persistence.EntityManager;
import jakarta.persistence.EntityManagerFactory;
import orgs.Config.HibernateConfig;
import orgs.Entities.User;

public class UserDAO {
    private EntityManagerFactory emf = HibernateConfig.getEntityManagerFactoryConfig();

    public User save(User user) {
        EntityManager entityManager = emf.createEntityManager();
        entityManager.getTransaction().begin();
        entityManager.persist(user);
        entityManager.getTransaction().commit();
        entityManager.close();

        return user;
    }

    public User updateUser(User user) {
        EntityManager entityManager = emf.createEntityManager();
        entityManager.getTransaction().begin();
        User updatedUser = entityManager.merge(user);
        entityManager.getTransaction().commit();
        entityManager.close();

        return updatedUser;

    }

    public User findById(int id) {
        EntityManager em = emf.createEntityManager();
        User foundUser = em.find(User.class, id);
        em.close();
        return foundUser;
    }

    public void deleteUser(int id) {
        EntityManager entityManager = emf.createEntityManager();
        entityManager.getTransaction().begin();
        User user = findById(id);
        if (user != null) {
            entityManager.remove(user);
        }
        entityManager.getTransaction().commit();
        entityManager.close();

    }

    public void close(){emf.close();}

}
