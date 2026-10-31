package CascadeDemo;

import org.example.Customer;
import org.example.Order;
import org.example.HibernateUtil;
import org.hibernate.Session;
import org.hibernate.Transaction;

public class CascadeTest {

    public static void main(String[] args) {

        Transaction tx = null;

        try (Session session =
                     HibernateUtil.getSessionFactory().openSession()) {

            tx = session.beginTransaction();

            Customer customer =
                    new Customer(301, "Vijay", "vijay@example.com");

            Order order1 = new Order("Mobile", customer);
            Order order2 = new Order("Laptop", customer);

            customer.addOrder(order1);
            customer.addOrder(order2);

            session.persist(customer);

            tx.commit();

            System.out.println("Customer and orders saved using Cascade!");

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