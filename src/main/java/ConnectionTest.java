package org.example;

import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.example.HibernateUtil;
public class ConnectionTest {

    public static void main(String[] args) {

        SessionFactory factory = null;

        try {
            factory = HibernateUtil.getSessionFactory();

            try (Session session = factory.openSession()) {
                session.doWork(connection -> {
                    System.out.println(
                            "Database: " +
                                    connection.getCatalog()
                    );
                    System.out.println(
                            "Connection valid: " +
                                    connection.isValid(5)
                    );
                });
            }

            System.out.println(
                    "Hibernate connected successfully!"
            );

        } catch (Exception e) {
            System.err.println(
                    "Hibernate connection failed:"
            );
            e.printStackTrace();

        } finally {
            if (factory != null && !factory.isClosed()) {
                HibernateUtil.shutdown();
            }
        }
    }
}
