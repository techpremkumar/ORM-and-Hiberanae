
package JPQLDemo;

import jakarta.persistence.EntityManager;
import jakarta.persistence.EntityManagerFactory;
import jakarta.persistence.Persistence;

public class JPQLDeleteTest {

    public static void main(String[] args) {

        EntityManagerFactory emf =
                Persistence.createEntityManagerFactory("orm-practice");

        EntityManager em = emf.createEntityManager();

        try {
            em.getTransaction().begin();

            int deletedRows = em.createQuery(
                            "DELETE FROM Customer c WHERE c.id = :id"
                    )
                    .setParameter("id", 201)
                    .executeUpdate();

            em.getTransaction().commit();

            System.out.println("Deleted rows: " + deletedRows);

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
