package CascadeDemo;

import org.example.Customer;
import org.example.HibernateUtil;
import org.hibernate.Session;
import org.hibernate.Transaction;

public class CascadeRefreshTest {

    public static void main(String[] args) {

        Transaction tx = null;

        try (Session session =
                     HibernateUtil.getSessionFactory().openSession()) {

            tx = session.beginTransaction();

            Customer customer =
                    session.get(Customer.class, 201);

            if (customer != null) {

                System.out.println(
                        "Original email: " + customer.getEmail()
                );

                customer.setEmail("temporary@example.com");

                session.refresh(customer);

                System.out.println(
                        "After refresh: " + customer.getEmail()
                );
            }

            tx.commit();

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