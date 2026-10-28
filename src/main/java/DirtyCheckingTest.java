

import org.example.HibernateUtil;
import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.hibernate.Transaction;

public class DirtyCheckingTest {

    public static void main(String[] args) {
        SessionFactory factory = null;

        try {
            factory = HibernateUtil.getSessionFactory();

            try (Session session = factory.openSession()) {
                Transaction transaction = session.beginTransaction();

                Customer customer = session.find(Customer.class, 101);

                if (customer != null) {
                    System.out.println("Before: " + customer.getEmail());

                    customer.setEmail("dirtycheck@example.com");

                    System.out.println("After: " + customer.getEmail());

                    transaction.commit();

                    System.out.println("Transaction committed.");
                } else {
                    transaction.rollback();
                    System.out.println("Customer not found.");
                }
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
