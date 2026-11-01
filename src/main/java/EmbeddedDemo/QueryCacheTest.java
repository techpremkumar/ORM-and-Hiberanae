package EmbeddedDemo;

import jakarta.persistence.EntityManager;
import jakarta.persistence.EntityManagerFactory;
import jakarta.persistence.Persistence;
import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.hibernate.stat.Statistics;

import java.util.List;

public class QueryCacheTest {

    public static void main(String[] args) {

        EntityManagerFactory emf =
                Persistence.createEntityManagerFactory("orm-practice");

        SessionFactory sf = emf.unwrap(SessionFactory.class);

        Statistics stats = sf.getStatistics();
        stats.setStatisticsEnabled(true);

        // First execution
        EntityManager em1 = emf.createEntityManager();

        List<Customer> customers1 = em1.unwrap(Session.class)
                .createQuery("FROM EmbeddedCustomer", Customer.class)
                .setCacheable(true)
                .getResultList();

        System.out.println("First execution: " + customers1.size());

        em1.close();

        // Second execution
        EntityManager em2 = emf.createEntityManager();

        List<Customer> customers2 = em2.unwrap(Session.class)
                .createQuery("FROM EmbeddedCustomer", Customer.class)
                .setCacheable(true)
                .getResultList();

        System.out.println("Second execution: " + customers2.size());

        em2.close();

        System.out.println("Query Cache Hits: "
                + stats.getQueryCacheHitCount());

        System.out.println("Query Cache Misses: "
                + stats.getQueryCacheMissCount());

        emf.close();
    }
}