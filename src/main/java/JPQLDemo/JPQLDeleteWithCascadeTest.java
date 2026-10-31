
package JPQLDemo;

import jakarta.persistence.EntityManager;
import jakarta.persistence.EntityManagerFactory;
import jakarta.persistence.Persistence;
import org.example.Customer;

public class JPQLDeleteWithCascadeTest {

    public static void main(String[] args) {

        EntityManagerFactory emf =
                Persistence.createEntityManagerFactory("orm-practice");

        EntityManager em = emf.createEntityManager();

        try {
            em.getTransaction().begin();

            Customer customer = em.find(Customer.class, 201);

            if (customer != null) {
                em.remove(customer);
                System.out.println("Customer marked for deletion");
            } else {
                System.out.println("Customer not found");
            }

            em.getTransaction().commit();

        } catch (Exception e) {

            if (em.getTransaction().isActive()) {
                em.getTransaction().rollback();
            }

            e.printStackTrace();

        } finally {
            em.close();
            emf.close();
        }
    }
}
