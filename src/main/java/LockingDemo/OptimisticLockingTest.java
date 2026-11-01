package LockingDemo;

import jakarta.persistence.*;

public class OptimisticLockingTest {

    public static void main(String[] args) {

        EntityManagerFactory emf =
                Persistence.createEntityManagerFactory("orm-practice");

        // Transaction A reads the product
        EntityManager em1 = emf.createEntityManager();

        // Transaction B reads the same product
        EntityManager em2 = emf.createEntityManager();

        Product productA = em1.find(Product.class, 1L);
        Product productB = em2.find(Product.class, 1L);

        System.out.println("Admin A version: " + productA.getVersion());
        System.out.println("Admin B version: " + productB.getVersion());

        // Admin A updates the price
        em1.getTransaction().begin();
        productA.setPrice(600);
        em1.getTransaction().commit();

        System.out.println("Admin A updated successfully.");

        // Admin B tries to update using the old version
        em2.getTransaction().begin();

        try {
            productB.setPrice(550);
            em2.getTransaction().commit();

            System.out.println("Admin B updated successfully.");

        } catch (OptimisticLockException e) {

            if (em2.getTransaction().isActive()) {
                em2.getTransaction().rollback();
            }

            System.out.println("Optimistic locking conflict detected!");
            System.out.println("Admin B's outdated update was rejected.");

        } finally {
            em1.close();
            em2.close();
            emf.close();
        }
    }
}