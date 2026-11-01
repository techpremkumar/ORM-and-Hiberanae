package LockingDemo;

import jakarta.persistence.*;

public class PessimisticLockingTest {

    public static void main(String[] args) {

        EntityManagerFactory emf =
                Persistence.createEntityManagerFactory("orm-practice");

        EntityManager em = emf.createEntityManager();

        try {
            em.getTransaction().begin();

            Product product = em.find(
                    Product.class,
                    1L,
                    LockModeType.PESSIMISTIC_WRITE
            );

            if (product == null) {
                throw new IllegalStateException("Product not found");
            }

            System.out.println("Product: " + product.getName());
            System.out.println("Lock acquired!");

            product.setPrice(product.getPrice() + 100);

            em.getTransaction().commit();

            System.out.println("Product updated successfully.");

        } catch (Exception e) {

            if (em.getTransaction().isActive()) {
                em.getTransaction().rollback();
            }

            e.printStackTrace();

        } finally {
            em.close();
            emf.close();
        }
    }
}