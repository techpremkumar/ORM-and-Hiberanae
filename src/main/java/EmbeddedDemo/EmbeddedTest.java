package EmbeddedDemo;

import jakarta.persistence.EntityManager;
import jakarta.persistence.EntityManagerFactory;
import jakarta.persistence.Persistence;
public class EmbeddedTest  {

    public static void main(String[] args) {

        EntityManagerFactory emf =
                Persistence.createEntityManagerFactory("orm-practice");

        EntityManager em = emf.createEntityManager();

        em.getTransaction().begin();

        Address current = new Address(
                "Anna Nagar", "Chennai", "600040"
        );

        Address permanent = new Address(
                "Gandhi Street", "Mayiladuthurai", "609001"
        );

        Customer customer = new Customer(
                "Arun", current, permanent
        );

        em.persist(customer);

        em.getTransaction().commit();

        em.close();
        emf.close();
    }
}