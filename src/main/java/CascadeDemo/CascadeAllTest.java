package CascadeDemo;

import org.example.Customer;
import org.example.Order;
import org.example.HibernateUtil;
import org.hibernate.Session;
import org.hibernate.Transaction;

public class CascadeAllTest {

    public static void main(String[] args) {

        Transaction tx = null;

        try (Session session =
                     HibernateUtil.getSessionFactory().openSession()) {

            tx = session.beginTransaction();

            Customer customer =
                    new Customer(401, "Kumar", "kumar@example.com");

            Order order1 = new Order("Keyboard", customer);
            Order order2 = new Order("Mouse", customer);

            customer.addOrder(order1);
            customer.addOrder(order2);

            session.persist(customer);

            tx.commit();

            System.out.println("Cascade ALL completed successfully!");

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