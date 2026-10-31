package HQLDemo;

import org.example.HibernateUtil;
import org.hibernate.Session;

import java.util.List;

public class HQLJoinTest {

    public static void main(String[] args) {

        try (Session session =
                     HibernateUtil.getSessionFactory().openSession()) {

            List<Object[]> results =
                    session.createQuery(
                            "SELECT c.name, o.productName " +
                                    "FROM Customer c " +
                                    "JOIN c.orders o",
                            Object[].class
                    ).getResultList();

            for (Object[] row : results) {
                System.out.println(
                        "Customer: " + row[0] +
                                " | Product: " + row[1]
                );
            }

        } finally {
            HibernateUtil.shutdown();
        }
    }
}