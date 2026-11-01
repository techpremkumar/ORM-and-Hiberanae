package LockingDemo;

import jakarta.persistence.*;
import java.util.List;

public class DTOProjectionTest {

    public static void main(String[] args) {

        EntityManagerFactory emf =
                Persistence.createEntityManagerFactory("orm-practice");

        EntityManager em = emf.createEntityManager();

        List<ProductDTO> products = em.createQuery(
                "SELECT new LockingDemo.ProductDTO(p.name, p.price) " +
                        "FROM Product p",
                ProductDTO.class
        ).getResultList();

        for (ProductDTO product : products) {
            System.out.println(
                    product.getName() + " : " + product.getPrice()
            );
        }

        em.close();
        emf.close();
    }
}