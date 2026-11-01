
package EmbeddedDemo;

import jakarta.persistence.*;

import org.hibernate.annotations.Cache;
import org.hibernate.annotations.CacheConcurrencyStrategy;

@Entity(name = "EmbeddedCustomer")
@Table(name = "embedded_customers")
@Cacheable
@Cache(usage = CacheConcurrencyStrategy.READ_WRITE)

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

    public Customer(String name,
                    Address currentAddress,
                    Address permanentAddress) {

        this.name = name;
        this.currentAddress = currentAddress;
        this.permanentAddress = permanentAddress;
    }

    public Long getId() {
        return id;
    }

    public String getName() {
        return name;
    }

    public Address getCurrentAddress() {
        return currentAddress;
    }

    public Address getPermanentAddress() {
        return permanentAddress;
    }
}
