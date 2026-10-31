package HQLDemo;

import org.hibernate.Session;
import org.hibernate.Transaction;
import org.example.HibernateUtil;

public class HQLDeleteTest {

    public static void main(String[] args) {

        Transaction tx = null;

        try (Session session =
                     HibernateUtil.getSessionFactory().openSession()) {

            tx = session.beginTransaction();

            int deletedRows = session.createMutationQuery(
                            "DELETE FROM Customer c WHERE c.id = :id"
                    )
                    .setParameter("id", 401)
                    .executeUpdate();

            tx.commit();

            System.out.println("Deleted rows: " + deletedRows);

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