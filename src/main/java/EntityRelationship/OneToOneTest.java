package EntityRelationship;

import org.example.HibernateUtil;
import org.example.HibernateUtil;
import org.hibernate.Session;
import org.hibernate.Transaction;

public class OneToOneTest {

    public static void main(String[] args) {

        try (Session session =
                     HibernateUtil.getSessionFactory().openSession()) {

            Transaction tx = session.beginTransaction();

            User user = new User("Arun");
            session.persist(user);

            UserProfile profile =
                    new UserProfile("Chennai, Tamil Nadu", user);

            session.persist(profile);

            tx.commit();

            System.out.println("User and profile saved successfully!");
        } finally {
            HibernateUtil.shutdown();
        }
    }
}
