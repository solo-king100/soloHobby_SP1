package orgs;

import jakarta.persistence.EntityManager;
import orgs.Config.HibernateConfig;
import orgs.Entities.Address;
import orgs.Entities.Profile;
import orgs.Entities.Hobby;
import orgs.Entities.User;

public class Main {
    public static void main(String[] args) {
        // Initialize EntityManagerFactory using HibernateConfig
        EntityManager entityManager = HibernateConfig.getEntityManagerFactoryConfig().createEntityManager();

        try {
            // Start a transaction
            entityManager.getTransaction().begin();

            // Create and persist several new Users
            User user1 = new User("Martin", "paspswppw323", "Martin@example.com");
            User user2 = new User("Alexa", "mcecwqpwI0", "Alexa@example.com");
            entityManager.persist(user1);
            entityManager.persist(user2);

            // Create and persist several new Profiles
            Profile profile1 = new Profile("Martin Falder", 43848002, new Address("Lyngby", 3480));
            Profile profile2 = new Profile("Alexa Trinity", 40503034, new Address("Copenhagen", 2769));
            entityManager.persist(profile1.getAddress());
            entityManager.persist(profile2.getAddress());
            entityManager.persist(profile1);
            entityManager.persist(profile2);

            // Create and persist several new Hobbies
            Hobby hobby1 = new Hobby("Gardening", "Tending to gardens and plants");
            Hobby hobby2 = new Hobby("Photography", "Taking and editing photos");
            entityManager.persist(hobby1);
            entityManager.persist(hobby2);

            // Create and persist several new Addresses
            Address address1 = new Address("Lyngby", 3480);
            Address address2 = new Address("Copenhagen", 2769);
            entityManager.persist(address1);
            entityManager.persist(address2);

            // Example of setting relationships
            //profile1.getHobbies().add(hobby1); // Assuming Profile has a method to get hobbies list
            //profile2.getHobbies().add(hobby2);
            //hobby1.getProfiles().add(profile1); // Assuming Hobby has a method to get profiles list
            //hobby2.getProfiles().add(profile2);

            // Commit the transaction
            entityManager.getTransaction().commit();

            System.out.println("Entities successfully persisted.");

        } catch (Exception e) {
            entityManager.getTransaction().rollback();
            e.printStackTrace();
        } finally {
            entityManager.close();
        }
    }
}