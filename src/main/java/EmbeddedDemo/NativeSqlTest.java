package EmbeddedDemo;

import jakarta.persistence.EntityManager;
import jakarta.persistence.EntityManagerFactory;
import jakarta.persistence.Persistence;

import java.util.List;

public class NativeSqlTest {

    public static void main(String[] args) {

        EntityManagerFactory emf =
                Persistence.createEntityManagerFactory("orm-practice");

        EntityManager em = emf.createEntityManager();

        List<Customer> customers = em.createNativeQuery(
                        "SELECT * FROM embedded_customers WHERE name = ?",
                        Customer.class
                )
                .setParameter(1, "Arun")
                .getResultList();

        for (Customer customer : customers) {
            System.out.println(
                    customer.getId() + " " + customer.getName()
            );
        }

        em.close();
        emf.close();
    }
}