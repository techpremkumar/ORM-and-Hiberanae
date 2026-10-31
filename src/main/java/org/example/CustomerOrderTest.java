
package org.example;
import org.example.Customer;
import org.example.HibernateUtil;
import org.hibernate.Session;
import org.hibernate.Transaction;

public class CustomerOrderTest {

    public static void main(String[] args) {

        Transaction tx = null;

        try (Session session =
                     HibernateUtil.getSessionFactory().openSession()) {

            tx = session.beginTransaction();

            Customer customer =
                    new Customer(201, "Arun", "arun@example.com");

            session.persist(customer);

            Order order1 = new Order("Laptop", customer);
            Order order2 = new Order("Headphones", customer);

            session.persist(order1);
            session.persist(order2);

            tx.commit();

            System.out.println("Customer and orders saved!");

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
