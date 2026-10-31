package CascadeDemo;

import org.example.Customer;
import org.example.HibernateUtil;
import org.hibernate.Session;
import org.hibernate.Transaction;

public class CascadeMergeTest {

    public static void main(String[] args) {

        Customer detachedCustomer;

        // Step 1: Retrieve customer
        try (Session session =
                     HibernateUtil.getSessionFactory().openSession()) {

            detachedCustomer =
                    session.get(Customer.class, 201);
        }

        // Step 2: Modify detached entity
        detachedCustomer.setEmail("updated@example.com");

        // Step 3: Merge detached entity
        Transaction tx = null;

        try (Session session =
                     HibernateUtil.getSessionFactory().openSession()) {

            tx = session.beginTransaction();

            Customer updatedCustomer =
                    session.merge(detachedCustomer);

            tx.commit();

            System.out.println(
                    "Updated email: " + updatedCustomer.getEmail()
            );

        } catch (Exception e) {

            if (tx != null && tx.isActive()) {
                tx.rollback();
            }

            e.printStackTrace();

        } finally {
            HibernateUtil.shutdown();
        }
    }
}