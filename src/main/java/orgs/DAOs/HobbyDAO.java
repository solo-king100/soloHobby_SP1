package orgs.DAOs;

import jakarta.persistence.EntityManager;
import jakarta.persistence.EntityManagerFactory;
import orgs.Config.HibernateConfig;
import orgs.Entities.Hobby;
import orgs.Entities.Profile;

public class HobbyDAO {
        private EntityManagerFactory emf = HibernateConfig.getEntityManagerFactoryConfig();

        public Hobby save(Hobby hobby) {
            EntityManager entityManager = emf.createEntityManager();
            entityManager.getTransaction().begin();
            entityManager.persist(hobby);
            entityManager.getTransaction().commit();
            entityManager.close();

            return hobby;
        }

        public Hobby updateHobby(Hobby hobby) {
            EntityManager entityManager = emf.createEntityManager();
            entityManager.getTransaction().begin();
            Hobby updatedHobby = entityManager.merge(hobby);
            entityManager.getTransaction().commit();
            entityManager.close();

            return updatedHobby;

        }

        public Hobby findById(int id) {
            EntityManager em = emf.createEntityManager();
            Hobby foundHobby = em.find(Hobby.class, id);
            em.close();
            return foundHobby;
        }

        public void deleteUser(int id) {
            EntityManager entityManager = emf.createEntityManager();
            entityManager.getTransaction().begin();
            Hobby hobby = findById(id);
            if (hobby != null) {
                entityManager.remove(hobby);
            }
            entityManager.getTransaction().commit();
            entityManager.close();

        }

        public void close() {
            emf.close();
        }
    }
