package Fetching;

import org.example.Customer;
import org.example.HibernateUtil;
import org.hibernate.Session;

public class FetchTest {

    public static void main(String[] args) {

        try (Session session =
                     HibernateUtil.getSessionFactory().openSession()) {

            Customer customer =
                    session.get(Customer.class, 201);

            System.out.println("Customer: " + customer.getName());

            System.out.println("Fetching orders now...");

            System.out.println(
                    "Order count: " + customer.getOrders().size()
            );

        } finally {
            HibernateUtil.shutdown();
        }
    }
}