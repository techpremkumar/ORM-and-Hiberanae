
package JPQLDemo;

import jakarta.persistence.EntityManager;
import jakarta.persistence.EntityManagerFactory;
import jakarta.persistence.Persistence;
import org.example.Customer;

import java.util.List;

public class JPQLJoinTest {

    public static void main(String[] args) {

        EntityManagerFactory emf =
                Persistence.createEntityManagerFactory("orm-practice");

        EntityManager em = emf.createEntityManager();

        try {
            List<Object[]> results = em.createQuery(
                    "SELECT c.name, o.productName " +
                            "FROM Customer c JOIN c.orders o",
                    Object[].class
            ).getResultList();

            for (Object[] row : results) {
                System.out.println(
                        "Customer: " + row[0] +
                                " | Product: " + row[1]
                );
            }

        } finally {
            em.close();
            emf.close();
        }
    }
}
