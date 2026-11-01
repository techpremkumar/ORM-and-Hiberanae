package BatchDemo;

import jakarta.persistence.*;

public class BatchInsertTest {

    public static void main(String[] args) {

        EntityManagerFactory emf =
                Persistence.createEntityManagerFactory("orm-practice");

        EntityManager em = emf.createEntityManager();

        EntityTransaction tx = em.getTransaction();

        try {
            tx.begin();

            int batchSize = 25;

            for (int i = 1; i <= 100; i++) {

                BatchProduct product = new BatchProduct(
                        (long) i,
                        "Product-" + i,
                        1000 + i
                );

                em.persist(product);

                if (i % batchSize == 0) {
                    em.flush();
                    em.clear();

                    System.out.println("Inserted: " + i);
                }
            }

            tx.commit();

            System.out.println("Batch insertion completed!");

        } catch (Exception e) {

            if (tx.isActive()) {
                tx.rollback();
            }

            e.printStackTrace();

        } finally {
            em.close();
            emf.close();
        }
    }
}