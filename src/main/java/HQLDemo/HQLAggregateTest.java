package HQLDemo;

import org.example.HibernateUtil;
import org.hibernate.Session;

public class HQLAggregateTest {

    public static void main(String[] args) {

        try (Session session =
                     HibernateUtil.getSessionFactory().openSession()) {

            // Total customers
            Long customerCount = session.createQuery(
                    "SELECT COUNT(c) FROM Customer c",
                    Long.class
            ).getSingleResult();

            // Total orders
            Long orderCount = session.createQuery(
                    "SELECT COUNT(o) FROM Order o",
                    Long.class
            ).getSingleResult();

            // Customer with minimum ID
            Integer minimumId = session.createQuery(
                    "SELECT MIN(c.id) FROM Customer c",
                    Integer.class
            ).getSingleResult();

            // Customer with maximum ID
            Integer maximumId = session.createQuery(
                    "SELECT MAX(c.id) FROM Customer c",
                    Integer.class
            ).getSingleResult();

            System.out.println("Total customers: " + customerCount);
            System.out.println("Total orders: " + orderCount);
            System.out.println("Minimum customer ID: " + minimumId);
            System.out.println("Maximum customer ID: " + maximumId);

        } finally {
            HibernateUtil.shutdown();
        }
    }
}