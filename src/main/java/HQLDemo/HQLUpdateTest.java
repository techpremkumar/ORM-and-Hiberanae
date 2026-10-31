package HQLDemo;

import org.example.HibernateUtil;
import org.hibernate.Session;
import org.hibernate.Transaction;

public class HQLUpdateTest {

    public static void main(String[] args) {

        Transaction tx = null;

        try (Session session =
                     HibernateUtil.getSessionFactory().openSession()) {

            tx = session.beginTransaction();

            int updatedRows = session.createMutationQuery(
                            "UPDATE Customer c " +
                                    "SET c.email = :email " +
                                    "WHERE c.id = :id"
                    )
                    .setParameter("email", "kumar.updated@example.com")
                    .setParameter("id", 401)
                    .executeUpdate();

            tx.commit();

            System.out.println("Updated rows: " + updatedRows);

        } catch (Exception e) {

            if (tx != null && tx.isActive()) {
                tx.rollback();
            }

            e.printStackTrace();

        } finally {
            HibernateUtil.shutdown();
        }
    }
}