package CascadeDemo;

import org.example.Customer;
import org.example.HibernateUtil;
import org.hibernate.Session;
import org.hibernate.Transaction;

public class CascadeRemoveTest {

    public static void main(String[] args) {

        Transaction tx = null;

        try (Session session =
                     HibernateUtil.getSessionFactory().openSession()) {

            tx = session.beginTransaction();

            Customer customer =
                    session.get(Customer.class, 301);

            if (customer != null) {
                session.remove(customer);
                System.out.println("Customer deletion requested!");
            }

            tx.commit();

            System.out.println("Customer and associated orders deleted!");

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