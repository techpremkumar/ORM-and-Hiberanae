
package InheritanceDemo;

import jakarta.persistence.*;

@Entity
@DiscriminatorValue("CONTRACT")
public class ContractEmployee extends Employee {

    private double hourlyRate;

    public ContractEmployee() {}

    public ContractEmployee(
            String name,
            String email,
            double hourlyRate) {

        super(name, email);
        this.hourlyRate = hourlyRate;
    }
}
