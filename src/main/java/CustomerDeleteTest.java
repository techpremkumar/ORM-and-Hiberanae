import org.example.Customer;
import org.example.HibernateUtil;
import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.hibernate.Transaction;

public class CustomerDeleteTest {

    public static void main(String[] args) {
        SessionFactory factory = null;

        try {
            factory = HibernateUtil.getSessionFactory();

            try (Session session = factory.openSession()) {
                Transaction transaction = session.beginTransaction();

                Customer customer = session.find(Customer.class, 101);

                if (customer != null) {
                    session.remove(customer);
                    transaction.commit();

                    System.out.println("Customer deleted successfully!");
                } else {
                    transaction.rollback();
                    System.out.println("Customer not found!");
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
