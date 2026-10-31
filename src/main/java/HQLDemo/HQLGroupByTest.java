package HQLDemo;

import org.example.HibernateUtil;
import org.hibernate.Session;

import java.util.List;

public class HQLGroupByTest {

    public static void main(String[] args) {

        try (Session session =
                     HibernateUtil.getSessionFactory().openSession()) {

            List<Object[]> results = session.createQuery(
                    "SELECT c.name, COUNT(o) " +
                            "FROM Customer c " +
                            "JOIN c.orders o " +
                            "GROUP BY c.id, c.name " +
                            "HAVING COUNT(o) > 1",
                    Object[].class
            ).getResultList();

            for (Object[] row : results) {
                System.out.println(
                        "Customer: " + row[0] +
                                " | Total orders: " + row[1]
                );
            }

        } finally {
            HibernateUtil.shutdown();
        }
    }
}