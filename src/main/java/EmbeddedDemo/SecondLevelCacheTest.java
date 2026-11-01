package EmbeddedDemo;

import jakarta.persistence.EntityManager;
import jakarta.persistence.EntityManagerFactory;
import jakarta.persistence.Persistence;

import org.hibernate.SessionFactory;
import org.hibernate.stat.Statistics;

public class SecondLevelCacheTest {

    public static void main(String[] args) {

        EntityManagerFactory emf =
                Persistence.createEntityManagerFactory("orm-practice");

        SessionFactory sessionFactory =
                emf.unwrap(SessionFactory.class);

        Statistics stats = sessionFactory.getStatistics();
        stats.setStatisticsEnabled(true);

        // First session
        EntityManager em1 = emf.createEntityManager();

        Customer customer1 = em1.find(Customer.class, 1L);

        System.out.println("First session: " + customer1.getName());

        em1.close();

        // Second session
        EntityManager em2 = emf.createEntityManager();

        Customer customer2 = em2.find(Customer.class, 1L);

        System.out.println("Second session: " + customer2.getName());

        em2.close();

        // Cache statistics
        System.out.println("L2 Cache Hits: "
                + stats.getSecondLevelCacheHitCount());

        System.out.println("L2 Cache Misses: "
                + stats.getSecondLevelCacheMissCount());

        System.out.println("L2 Cache Puts: "
                + stats.getSecondLevelCachePutCount());

        emf.close();
    }
}