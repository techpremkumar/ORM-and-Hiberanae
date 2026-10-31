package CascadeDemo;

import org.example.Customer;
import org.example.HibernateUtil;
import org.example.Order;
import org.hibernate.Session;

public class CascadeDetachTest {

    public static void main(String[] args) {

        try (Session session =
                     HibernateUtil.getSessionFactory().openSession()) {

            Customer customer =
                    session.get(Customer.class, 201);

            if (customer != null) {

                // Initialize the LAZY collection
                customer.getOrders().size();

                System.out.println(
                        "Customer managed: " +
                                session.contains(customer)
                );

                for (Order order : customer.getOrders()) {
                    System.out.println(
                            "Order managed: " +
                                    session.contains(order)
                    );
                }

                session.detach(customer);

                System.out.println(
                        "After detach - Customer managed: " +
                                session.contains(customer)
                );

                for (Order order : customer.getOrders()) {
                    System.out.println(
                            "After detach - Order managed: " +
                                    session.contains(order)
                    );
                }
            }

        } finally {
            HibernateUtil.shutdown();
        }
    }
}