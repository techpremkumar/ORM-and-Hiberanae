package ManyToMany;

import org.example.HibernateUtil;
import org.hibernate.Session;
import org.hibernate.Transaction;

public class ManyToManyTest {

    public static void main(String[] args) {

        Transaction tx = null;

        try (Session session =
                     HibernateUtil.getSessionFactory().openSession()) {

            tx = session.beginTransaction();

            Student student1 = new Student("Arun");
            Student student2 = new Student("Rahul");

            Course java = new Course("Java");
            Course python = new Course("Python");

            student1.addCourse(java);
            student1.addCourse(python);

            student2.addCourse(java);

            session.persist(java);
            session.persist(python);

            session.persist(student1);
            session.persist(student2);

            tx.commit();

            System.out.println("Students and courses saved!");

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