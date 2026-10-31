
import org.example.Customer;

import org.example.HibernateUtil;
import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.hibernate.Transaction;

public class FlushTest {

    public static void main(String[] args) {
        SessionFactory factory = HibernateUtil.getSessionFactory();

        try (Session session = factory.openSession()) {
            Transaction tx = session.beginTransaction();

            try {
                Customer customer = session.find(Customer.class, 101);

                if (customer == null) {
                    throw new IllegalStateException(
                            "Customer ID 101 not found. Insert a customer first."
                    );
                }

                customer.setEmail("arun.flush@example.com");

                session.flush();
                System.out.println("Changes flushed to the database.");

                tx.commit();
                System.out.println("Transaction committed successfully.");

            } catch (Exception e) {
                if (tx.isActive()) {
                    tx.rollback();
                }
                throw e;
            }
        } finally {
            HibernateUtil.shutdown();
        }
    }
}