
package InheritanceDemo;

import jakarta.persistence.*;

public class InheritanceTest {

    public static void main(String[] args) {

        EntityManagerFactory emf =
                Persistence.createEntityManagerFactory("orm-practice");

        EntityManager em = emf.createEntityManager();

        try {
            em.getTransaction().begin();

            Employee e1 = new FullTimeEmployee(
                    "Arun",
                    "arun@example.com",
                    50000
            );

            Employee e2 = new ContractEmployee(
                    "Vijay",
                    "vijay@example.com",
                    800
            );

            em.persist(e1);
            em.persist(e2);

            em.getTransaction().commit();

            System.out.println("Employees saved successfully");

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
