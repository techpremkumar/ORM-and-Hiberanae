package BatchDemo;

import jakarta.persistence.*;

@Entity
@Table(name = "batch_products")
public class BatchProduct {

    @Id
    private Long id;

    private String name;

    private double price;

    public BatchProduct() {}

    public BatchProduct(Long id, String name, double price) {
        this.id = id;
        this.name = name;
        this.price = price;
    }
}