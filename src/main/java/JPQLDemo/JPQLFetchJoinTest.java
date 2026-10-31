package JPQLDemo;

import jakarta.persistence.EntityManager;
import jakarta.persistence.EntityManagerFactory;
import jakarta.persistence.Persistence;
import org.example.Customer;

import java.util.List;

public class JPQLFetchJoinTest {

    public static void main(String[] args) {

        EntityManagerFactory emf =
                Persistence.createEntityManagerFactory("orm-practice");

        EntityManager em = emf.createEntityManager();

        try {

            List<Customer> customers = em.createQuery(
                    "SELECT DISTINCT c FROM Customer c " +
                            "LEFT JOIN FETCH c.orders",
                    Customer.class
            ).getResultList();

            for (Customer customer : customers) {

                System.out.println(
                        "Customer: " + customer.getName()
                );

                customer.getOrders().forEach(order ->
                        System.out.println(
                                "  Product: " + order.getProductName()
                        )
                );
            }

        } finally {
            em.close();
            emf.close();
        }
    }
}
