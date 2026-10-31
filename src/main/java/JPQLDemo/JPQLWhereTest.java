
package JPQLDemo;

import jakarta.persistence.EntityManager;
import jakarta.persistence.EntityManagerFactory;
import jakarta.persistence.Persistence;
import org.example.Customer;

public class JPQLWhereTest {

    public static void main(String[] args) {

        EntityManagerFactory emf =
                Persistence.createEntityManagerFactory("orm-practice");

        EntityManager em = emf.createEntityManager();

        try {
            int customerId = 201;

            Customer customer = em.createQuery(
                            "SELECT c FROM Customer c WHERE c.id = :id",
                            Customer.class
                    )
                    .setParameter("id", customerId)
                    .getSingleResult();

            System.out.println("Customer ID: " + customer.getId());
            System.out.println("Customer Name: " + customer.getName());
            System.out.println("Customer Email: " + customer.getEmail());

        } finally {
            em.close();
            emf.close();
        }
    }
}
