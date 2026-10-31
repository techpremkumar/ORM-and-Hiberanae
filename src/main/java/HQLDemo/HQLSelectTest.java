package HQLDemo;

import org.example.Customer;
import org.example.HibernateUtil;
import org.hibernate.Session;

import java.util.List;

public class HQLSelectTest {

    public static void main(String[] args) {

        try (Session session =
                     HibernateUtil.getSessionFactory().openSession()) {
            List<Customer> customers =
                    session.createQuery(
                            "FROM Customer c ORDER BY c.id DESC",
                            Customer.class
                    ).getResultList();

            for (Customer customer : customers) {

                System.out.println(
                        customer.getId() + " " +
                                customer.getName() + " " +
                                customer.getEmail()
                );
            }

        } finally {
            HibernateUtil.shutdown();
        }
    }
}