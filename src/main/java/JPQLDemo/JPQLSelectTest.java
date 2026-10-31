package JPQLDemo;

import jakarta.persistence.EntityManager;
import jakarta.persistence.EntityManagerFactory;
import jakarta.persistence.Persistence;

public class JPQLSelectTest {

    public static void main(String[] args) {

        EntityManagerFactory emf =
                Persistence.createEntityManagerFactory("orm-practice");

        EntityManager em = emf.createEntityManager();

        try {
            var customers = em.createQuery(
                    "SELECT c FROM Customer c",
                    org.example.Customer.class
            ).getResultList();

            customers.forEach(customer ->
                    System.out.println(
                            customer.getId() + " " + customer.getName()
                    )
            );

        } finally {
            em.close();
            emf.close();
        }
    }
}