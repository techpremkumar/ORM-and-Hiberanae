package HQLDemo;

import org.example.Customer;
import org.example.HibernateUtil;
import org.hibernate.Session;

import java.util.List;

public class HQLOrderByPaginationTest {

    public static void main(String[] args) {

        try (Session session =
                     HibernateUtil.getSessionFactory().openSession()) {

            List<Customer> customers = session.createQuery(
                    "FROM Customer c ORDER BY c.name ASC",
                    Customer.class
            ).getResultList();

            for (Customer customer : customers) {
                System.out.println(
                        customer.getId() + " " + customer.getName()
                );
            }
        } finally {
            HibernateUtil.shutdown();
        }
    }
}