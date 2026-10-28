
import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.hibernate.Transaction;

public class CustomerInsertTest {

    public static void main(String[] args) {

        SessionFactory factory = null;

        try {
            factory = HibernateUtil.getSessionFactory();

            try (Session session = factory.openSession()) {

                Transaction transaction = session.beginTransaction();

                Customer customer = new Customer(
                        101,
                        "Arun",
                        "arun@example.com"
                );

                session.persist(customer);

                transaction.commit();

                System.out.println("Customer saved successfully!");
            }

        } catch (Exception e) {
            e.printStackTrace();

        } finally {
            if (factory != null && !factory.isClosed()) {
                HibernateUtil.shutdown();
            }
        }
    }
}
