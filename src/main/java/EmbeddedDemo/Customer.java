package EmbeddedDemo;

import jakarta.persistence.*;

@Entity(name = "EmbeddedCustomer")
@Table(name = "embedded_customers")
public class Customer {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    private String name;

    @Embedded
    @AttributeOverrides({
            @AttributeOverride(name = "street",
                    column = @Column(name = "current_street")),
            @AttributeOverride(name = "city",
                    column = @Column(name = "current_city")),
            @AttributeOverride(name = "pincode",
                    column = @Column(name = "current_pincode"))
    })
    private Address currentAddress;

    @Embedded
    @AttributeOverrides({
            @AttributeOverride(name = "street",
                    column = @Column(name = "permanent_street")),
            @AttributeOverride(name = "city",
                    column = @Column(name = "permanent_city")),
            @AttributeOverride(name = "pincode",
                    column = @Column(name = "permanent_pincode"))
    })
    private Address permanentAddress;

    public Customer() {}

    public Customer(String name, Address currentAddress,
                    Address permanentAddress) {
        this.name = name;
        this.currentAddress = currentAddress;
        this.permanentAddress = permanentAddress;
    }
}