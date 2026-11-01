package EmbeddedDemo;

import jakarta.persistence.*;
import jakarta.persistence.criteria.*;

import java.util.List;

public class CriteriaApiTest {

    public static void main(String[] args) {

        EntityManagerFactory emf =
                Persistence.createEntityManagerFactory("orm-practice");

        EntityManager em = emf.createEntityManager();

        CriteriaBuilder cb = em.getCriteriaBuilder();

        CriteriaQuery<Customer> cq =
                cb.createQuery(Customer.class);

        Root<Customer> root = cq.from(Customer.class);

        cq.select(root)
                .where(cb.equal(root.get("name"), "Arun"));

        List<Customer> customers =
                em.createQuery(cq).getResultList();

        for (Customer customer : customers) {
            System.out.println(customer.getName());
        }

        em.close();
        emf.close();
    }
}