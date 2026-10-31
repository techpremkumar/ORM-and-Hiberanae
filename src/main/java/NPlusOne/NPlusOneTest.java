package NPlusOne;

import org.example.Customer;
import org.example.HibernateUtil;
import org.hibernate.Session;

import java.util.List;

public class NPlusOneTest {

    public static void main(String[] args) {

        try (Session session =
                     HibernateUtil.getSessionFactory().openSession()) {

            List<Customer> customers =
                    session.createQuery(
                            "FROM Customer", Customer.class
                    ).getResultList();

            for (Customer customer : customers) {

                System.out.println(
                        customer.getName() +
                                " -> " +
                                customer.getOrders().size() +
                                " orders"
                );
            }

        } finally {
            HibernateUtil.shutdown();
        }
    }
}