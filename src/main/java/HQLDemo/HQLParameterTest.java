package HQLDemo;

import org.example.Customer;
import org.example.HibernateUtil;
import org.hibernate.Session;

import java.util.List;

public class HQLParameterTest {

    public static void main(String[] args) {

        try (Session session =
                     HibernateUtil.getSessionFactory().openSession()) {

            int customerId = 201;

            List<Customer> customers =
                    session.createQuery(
                                    "FROM Customer c WHERE c.id = :id",
                                    Customer.class
                            )
                            .setParameter("id", customerId)
                            .getResultList();

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