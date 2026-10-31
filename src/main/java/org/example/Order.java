
package org.example;

import jakarta.persistence.*;

@Entity
@Table(name = "orders")
public class Order {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private int id;

    @Column(name = "product_name")
    private String productName;

    @ManyToOne
    @JoinColumn(name = "customer_id")
    private Customer customer;

    public Order() {
    }

    public Order(String productName, Customer customer) {
        this.productName = productName;
        this.customer = customer;
    }

    public int getId() {
        return id;
    }

    public String getProductName() {
        return productName;
    }

    public Customer getCustomer() {
        return customer;
    }
}
